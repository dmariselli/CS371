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

    private void munchStm (MOVE s) {

	// fill this in

    }

    private void munchStm (LABEL s) {
	
	emit (new LABELInstr (s.label.toString() + ":\n", s.label));
	
    }
      
    private void munchStm (JUMP s) {
	emit (new OPERInstr (tab + "jmp " + s.target + "\n", null, null, s.target));
    }

    private void munchStm (CJUMP s) {
	
	// fill this in
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

	emit (new OPERInstr (tab + "movq $" + e.value + ",`d0\n", new TempList(t), null));
	return t;
    }

    private Temp munchExp (TEMP e) {
	return e.temp;
    }

    private Temp munchExp (NAME e) {      // the expression is a pointer
	Temp t = new Temp();
	
	emit (new OPERInstr (tab + "leaq " + e.label + "(%rip),`d0\n", 
			new TempList(t), null));
	return t;
    }

    private Temp munchExp (MEM e) {
	Temp t = new Temp();
	
	// stuff needed here

	return t;
    }

    private Temp munchExp (BINOP e) {
	Temp t = new Temp();
	
	// stuff needed here

	return t;
    }

    private Temp munchExp (CALL e) {

	// stuff needed here

	return frame.RV();
    }
}
