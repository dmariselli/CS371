package minijava.BackEnd.Arch.Linux64;

import minijava.BackEnd.Assem.*;

import minijava.Temp.Temp;
import minijava.Temp.TempList;
import minijava.Temp.Label;

import minijava.Tree.Stm;
import minijava.Tree.ESTM;
import minijava.Tree.MOVE;
import minijava.Tree.JUMP;
import minijava.Tree.CJUMP;
import minijava.Tree.LABEL;
import minijava.Tree.ExpList;
import minijava.Tree.Exp;
import minijava.Tree.TEMP;
import minijava.Tree.MEM;
import minijava.Tree.NAME;
import minijava.Tree.BINOP;
import minijava.Tree.CONST;
import minijava.Tree.CALL;

import java.lang.UnsupportedOperationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/** An instruction selector for the Pentium architecture.
 */
class CodeGen {

    private PFrame frame;

    private InstrList ilist;

    private void emit (Instr inst) {
	if (ilist == null) 
	    ilist = new InstrList();
	ilist.add(inst);
    }

    /**
     * Class constructor
     * @param f the PFrame for the method for which code will be generated.
     */
    CodeGen (PFrame f) {frame = f;}
    
    private static String tab = "\t";
    

    /**
     * Returns an instruction list for a given intermediate tree.
     * @param s an Stm
     * @return an instruction list
     */

    InstrList codegen (Stm s) {
	
	munchStm (s);
	return ilist;
    }

    private void munchStm (Stm s) {
	if (s == null) return;
	else if (s instanceof ESTM) munchStm ((ESTM) s);
	else if (s instanceof JUMP) munchStm ((JUMP) s);
	else if (s instanceof CJUMP) munchStm ((CJUMP) s);
	else if (s instanceof LABEL) munchStm ((LABEL) s);
	else if (s instanceof MOVE) munchStm ((MOVE) s);
	else throw new RuntimeException ("unknown Stm");
    }
    
    private void munchStm (ESTM s) {
	munchExp (s.exp);
    }

    // Wrote
    private void munchStm (MOVE s) {
        if (s.dst instanceof TEMP) {
            emit ( new MOVEInstr(tab + "movq\t`s0, `d0\n", ((TEMP) s.dst).temp, munchExp(s.src)));
        } else if (s.dst instanceof MEM) {
            Temp underMEM = munchExp(((MEM) s.dst).exp);
            emit(new OPERInstr(tab + "movq\t`s0, (`s1)\n", null, new TempList(munchExp(s.src), underMEM)));
        } else {
            throw new UnsupportedOperationException("MOVE");
        }
    }

    private void munchStm (LABEL s) {
	
	emit (new LABELInstr (s.label.toString() + ":\n", s.label));
	
    }
      
    private void munchStm (JUMP s) {
	emit (new OPERInstr (tab + "jmp\t" + s.target + "\n", null, null, s.target));
    }

    // Wrote
    private void munchStm (CJUMP s) {
	    emit (new OPERInstr (tab + "cmpq\t`s1, `s0\n" + tab + CJUMPHelper(s) + "\t" + s.iftrue + "\n", new TempList(), new TempList(munchExp(s.left), munchExp(s.right)), s.iftrue, s.iffalse));
    }

    // Wrote
    private String CJUMPHelper(CJUMP s) {
        String[] command = {"je", "jne", "jl", "jg", "jle", "jge"};
        return command[s.relop];
    }

    private Temp munchExp (Exp e) {
	if (e instanceof CONST) return munchExp((CONST) e);
	else if (e instanceof NAME) return munchExp((NAME) e);
	else if (e instanceof TEMP) return munchExp((TEMP) e);
	else if (e instanceof BINOP) return munchExp((BINOP) e);
	else if (e instanceof MEM) return munchExp((MEM) e);
	else if (e instanceof CALL) return munchExp((CALL) e);
	else throw new RuntimeException ("unknown Exp");
    }

    private Temp munchExp (CONST e) {
	Temp t = new Temp();

	emit (new OPERInstr (tab + "movq\t$" + e.value + ",`d0\n", new TempList(t), null));
	    return t;
    }

    private Temp munchExp (TEMP e) {
	return e.temp;
    }

    private Temp munchExp (NAME e) {      // the expression is a pointer
        Temp t = new Temp();
        emit (new OPERInstr (tab + "leaq\t" + e.label + "(%rip),`d0\n",
                new TempList(t), null));
        return t;
    }

    // Wrote
    private Temp munchExp (MEM e) {
	    Temp t = new Temp();
	    emit (new OPERInstr (tab + "movq\t(`s0), `d0\n", new TempList(t), new TempList(munchExp(e.exp))));
	    return t;
    }

    // Wrote
    private Temp munchExp (BINOP e) {
    	Temp t = new Temp();
        Temp left = munchExp(e.left);
        emit (new OPERInstr (tab + "movq\t`s0, `d0\n", new TempList(t), new TempList(left)));
        if (BINOPHelper(e).equalsIgnoreCase("idivq") || BINOPHelper(e).equalsIgnoreCase("modq")) {
            emit (new MOVEInstr (tab + "movq\t`s0, `d0\n", frame.RAX, t));
            emit (new OPERInstr (tab + "movq\t$0,`d0\n", new TempList(frame.RDX), null));
            emit (new OPERInstr (tab + "idivq\t`s0, `d0\n", new TempList(frame.RAX, frame.RDX), new TempList(munchExp(e.right), frame.RAX, frame.RDX)));
            if (BINOPHelper(e).equalsIgnoreCase("idivq")) {
                return frame.RAX;
            } else {
                return frame.RDX;
            }
        }
        emit (new OPERInstr (tab + BINOPHelper(e) + "\t`s0, `d0\n", new TempList(t), new TempList(munchExp(e.right), t)));
	    return t;
    }

    // Wrote
    private String BINOPHelper(BINOP e) {
        // modq is not a real instruction. It is only used to help differentiate between a regular div and a mod
        String[] command = {"addq", "subq", "imulq", "idivq", "andq", "orq", "shlq", "shrq", "sarq", "xorq", "modq"};
        return command[e.binop];
    }

    // Wrote
    private Temp munchExp (CALL e) {
        if (e.args.length() > 6) {
            throw new UnsupportedOperationException("CALL");
        }
        Iterator<Exp> iterator = e.args.iterator();
        TempList regTemps = new TempList();
        for (int i = 0; i < e.args.length(); i++) {
            Temp src = munchExp(iterator.next());
            regTemps = new TempList(regTemps, new TempList(frame.parameterRegs[i]));
            emit (new MOVEInstr (tab + "movq\t`s0, `d0\n", regTemps.get(i), src));
        }
        TempList dstList = new TempList();
        for (Temp temp : frame.callersaves) {
            dstList = new TempList(dstList, new TempList(temp));
        }
        emit (new OPERInstr (tab + "call\t" + ((NAME) e.func).label + "\n", dstList, regTemps));
	    return frame.RV();
    }
}
