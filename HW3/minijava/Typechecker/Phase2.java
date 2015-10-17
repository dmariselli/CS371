/* This file was generated by the Amherst version of SableCC */

package minijava.Typechecker;

import minijava.node.*;
import minijava.Type.*;

public class Phase2
{

    private Typechecker typechecker;

    Phase2(Typechecker typechecker) {
        this.typechecker = typechecker;
    }

    void process(Node n) {
    	 throw new RuntimeException(this.getClass() + ": no process method available for " 
	     + n.getClass());
    }

    ///////////////////////////////////////////////////////////////
    void process(Start n) {
         process(n.getPProgram());
    }

    ///////////////////////////////////////////////////////////////
    void process(PProgram n) {
        if (n instanceof AProgram) process((AProgram)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PProgram)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AProgram n) {
        n.getPublic();				// yields TPublic
        n.getClasstok();				// yields TClasstok
        n.getId();				// yields TId
        n.getLbrace();				// yields TLbrace
	for (PMaindecl p : n.getMaindecl())
	    process(p);				// process(PMaindecl)
        n.getRbrace();				// yields TRbrace

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(PMaindecl n) {
        if (n instanceof AVarMaindecl) process((AVarMaindecl)n);
	else if (n instanceof AMethodMaindecl) process((AMethodMaindecl)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PMaindecl)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AVarMaindecl n) {
        process(n.getPrivacy());			// process(PPrivacy)
        n.getStatic();				// yields TStatic
        process(n.getType());			// process(PType)
        n.getId();				// yields TId
        n.getSemi();				// yields TSemi

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AMethodMaindecl n) {
        process(n.getPrivacy());			// process(PPrivacy)
        n.getStatic();				// yields TStatic
        process(n.getType());			// process(PType)
        n.getId();				// yields TId
        n.getLparen();				// yields TLparen
        process(n.getParamlist());			// process(PParamlist)
        n.getRparen();				// yields TRparen
        n.getLbrace();				// yields TLbrace
	for (PStmt p : n.getStmt())
	    process(p);				// process(PStmt)
        n.getRbrace();				// yields TRbrace

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(PParamlist n) {
        if (n instanceof AListParamlist) process((AListParamlist)n);
	else if (n instanceof AEmptyParamlist) process((AEmptyParamlist)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PParamlist)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AListParamlist n) {
        process(n.getType());			// process(PType)
        n.getId();				// yields TId
	for (PParam p : n.getParam())
	    process(p);				// process(PParam)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AEmptyParamlist n) {

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(PParam n) {
        if (n instanceof AParam) process((AParam)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PParam)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AParam n) {
        n.getComma();				// yields TComma
        process(n.getType());			// process(PType)
        n.getId();				// yields TId

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(PPrivacy n) {
        if (n instanceof APublicPrivacy) process((APublicPrivacy)n);
	else if (n instanceof ABlankPrivacy) process((ABlankPrivacy)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PPrivacy)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(APublicPrivacy n) {
        n.getPublic();				// yields TPublic

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(ABlankPrivacy n) {

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(PType n) {
        if (n instanceof AType) process((AType)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PType)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AType n) {
        n.getId();				// yields TId
	for (PEmptydim p : n.getEmptydim())
	    process(p);				// process(PEmptydim)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(PStmt n) {
        if (n instanceof AWhileStmt) process((AWhileStmt)n);
	else if (n instanceof ADeclStmt) process((ADeclStmt)n);
	else if (n instanceof ABlockStmt) process((ABlockStmt)n);
	else if (n instanceof AIfStmt) process((AIfStmt)n);
	else if (n instanceof AExprStmt) process((AExprStmt)n);
	else if (n instanceof AReturnStmt) process((AReturnStmt)n);
	else if (n instanceof APrintStmt) process((APrintStmt)n);
	else if (n instanceof AEmptyStmt) process((AEmptyStmt)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PStmt)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AWhileStmt n) {
        n.getWhile();				// yields TWhile
        n.getLparen();				// yields TLparen
        process(n.getExpr());			// process(PExpr)
        n.getRparen();				// yields TRparen
        process(n.getStmt());			// process(PStmt)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(ADeclStmt n) {
        process(n.getType());			// process(PType)
        n.getId();				// yields TId
        n.getSemi();				// yields TSemi

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(ABlockStmt n) {
        n.getLbrace();				// yields TLbrace
	for (PStmt p : n.getStmt())
	    process(p);				// process(PStmt)
        n.getRbrace();				// yields TRbrace

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AIfStmt n) {
        n.getIf();				// yields TIf
        n.getLparen();				// yields TLparen
        process(n.getExpr());			// process(PExpr)
        n.getRparen();				// yields TRparen
        process(n.getThenclause());			// process(PStmt)
        n.getElse();				// yields TElse
        process(n.getElseclause());			// process(PStmt)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AExprStmt n) {
        process(n.getExpr());			// process(PExpr)
        n.getSemi();				// yields TSemi

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AReturnStmt n) {
        n.getReturn();				// yields TReturn
        if (n.getExpr() != null)
            process(n.getExpr());		// process(PExpr)
        n.getSemi();				// yields TSemi

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(APrintStmt n) {
        n.getPrint();				// yields TPrint
        n.getLparen();				// yields TLparen
        process(n.getExpr());			// process(PExpr)
        n.getRparen();				// yields TRparen
        n.getSemi();				// yields TSemi

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AEmptyStmt n) {
        n.getSemi();				// yields TSemi

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    // Everything below this should return ExprType
    // return new ExprType (null, *type*);
    ///////////////////////////////////////////////////////////////
    ExprType process(PExpr n) {
        if (n instanceof AAssignExpr) return process((AAssignExpr)n);
	else if (n instanceof AExprExpr) return process((AExprExpr)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PExpr)");
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AAssignExpr n) {
        process(n.getLhs());			// process(PLhs)
        n.getAssign();				// yields TAssign
        process(n.getExpr());			// process(PExpr)
        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AExprExpr n) {
        process(n.getExpr10());			// process(PExpr10)
        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PExpr10 n) {
        if (n instanceof AOrExpr10) process((AOrExpr10)n);
	else if (n instanceof AExprExpr10) process((AExprExpr10)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PExpr10)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AOrExpr10 n) {
        process(n.getLeft());			// process(PExpr10)
        n.getOr();				// yields TOr
        process(n.getRight());			// process(PExpr20)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AExprExpr10 n) {
        process(n.getExpr20());			// process(PExpr20)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PExpr20 n) {
        if (n instanceof AAndExpr20) process((AAndExpr20)n);
	else if (n instanceof AExprExpr20) process((AExprExpr20)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PExpr20)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AAndExpr20 n) {
        process(n.getLeft());			// process(PExpr20)
        n.getAnd();				// yields TAnd
        process(n.getRight());			// process(PExpr30)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AExprExpr20 n) {
        process(n.getExpr30());			// process(PExpr30)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PExpr30 n) {
        if (n instanceof AEqExpr30) process((AEqExpr30)n);
	else if (n instanceof ANeExpr30) process((ANeExpr30)n);
	else if (n instanceof AExprExpr30) process((AExprExpr30)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PExpr30)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AEqExpr30 n) {
        process(n.getLeft());			// process(PExpr30)
        n.getEq();				// yields TEq
        process(n.getRight());			// process(PExpr40)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ANeExpr30 n) {
        process(n.getLeft());			// process(PExpr30)
        n.getNe();				// yields TNe
        process(n.getRight());			// process(PExpr40)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AExprExpr30 n) {
        process(n.getExpr40());			// process(PExpr40)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PExpr40 n) {
        if (n instanceof ALtExpr40) process((ALtExpr40)n);
	else if (n instanceof ALeExpr40) process((ALeExpr40)n);
	else if (n instanceof AGeExpr40) process((AGeExpr40)n);
	else if (n instanceof AGtExpr40) process((AGtExpr40)n);
	else if (n instanceof AExprExpr40) process((AExprExpr40)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PExpr40)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ALtExpr40 n) {
        process(n.getLeft());			// process(PExpr40)
        n.getLt();				// yields TLt
        process(n.getRight());			// process(PExpr50)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ALeExpr40 n) {
        process(n.getLeft());			// process(PExpr40)
        n.getLe();				// yields TLe
        process(n.getRight());			// process(PExpr50)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AGeExpr40 n) {
        process(n.getLeft());			// process(PExpr40)
        n.getGe();				// yields TGe
        process(n.getRight());			// process(PExpr50)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AGtExpr40 n) {
        process(n.getLeft());			// process(PExpr40)
        n.getGt();				// yields TGt
        process(n.getRight());			// process(PExpr50)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AExprExpr40 n) {
        process(n.getExpr50());			// process(PExpr50)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PExpr50 n) {
        if (n instanceof APlusExpr50) process((APlusExpr50)n);
	else if (n instanceof AMinusExpr50) process((AMinusExpr50)n);
	else if (n instanceof ATermExpr50) process((ATermExpr50)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PExpr50)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(APlusExpr50 n) {
        process(n.getLeft());			// process(PExpr50)
        n.getPlus();				// yields TPlus
        process(n.getRight());			// process(PTerm)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AMinusExpr50 n) {
        process(n.getLeft());			// process(PExpr50)
        n.getMinus();				// yields TMinus
        process(n.getRight());			// process(PTerm)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ATermExpr50 n) {
        process(n.getTerm());			// process(PTerm)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PTerm n) {
        if (n instanceof ATimesTerm) process((ATimesTerm)n);
	else if (n instanceof ADivTerm) process((ADivTerm)n);
	else if (n instanceof AModTerm) process((AModTerm)n);
	else if (n instanceof AFactorTerm) process((AFactorTerm)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PTerm)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ATimesTerm n) {
        process(n.getLeft());			// process(PTerm)
        n.getTimes();				// yields TTimes
        process(n.getRight());			// process(PFactor)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ADivTerm n) {
        process(n.getLeft());			// process(PTerm)
        n.getDiv();				// yields TDiv
        process(n.getRight());			// process(PFactor)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AModTerm n) {
        process(n.getLeft());			// process(PTerm)
        n.getMod();				// yields TMod
        process(n.getRight());			// process(PFactor)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AFactorTerm n) {
        process(n.getFactor());			// process(PFactor)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PFactor n) {
        if (n instanceof APrimaryFactor) process((APrimaryFactor)n);
	else if (n instanceof AIdFactor) process((AIdFactor)n);
	else if (n instanceof ALengthFactor) process((ALengthFactor)n);
	else if (n instanceof ALength2Factor) process((ALength2Factor)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PFactor)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(APrimaryFactor n) {
        process(n.getPrimary());			// process(PPrimary)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AIdFactor n) {
        n.getId();				// yields TId

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ALengthFactor n) {
        n.getId();				// yields TId
        n.getDot();				// yields TDot
        n.getLength();				// yields TLength

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ALength2Factor n) {
        n.getId();				// yields TId
        n.getDot();				// yields TDot
        n.getLength();				// yields TLength
        n.getLparen();				// yields TLparen
        n.getRparen();				// yields TRparen

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PPrimary n) {
        if (n instanceof ANewarrayPrimary) process((ANewarrayPrimary)n);
	else if (n instanceof APrimary2Primary) process((APrimary2Primary)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PPrimary)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ANewarrayPrimary n) {
        n.getNew();				// yields TNew
        n.getId();				// yields TId
        n.getLbrack();				// yields TLbrack
        process(n.getExpr());			// process(PExpr)
        n.getRbrack();				// yields TRbrack
	for (PEmptydim p : n.getEmptydim())
	    process(p);				// process(PEmptydim)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(APrimary2Primary n) {
        process(n.getPrimary2());			// process(PPrimary2)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PPrimary2 n) {
        if (n instanceof AIconstPrimary2) process((AIconstPrimary2)n);
	else if (n instanceof ASconstPrimary2) process((ASconstPrimary2)n);
	else if (n instanceof ANullPrimary2) process((ANullPrimary2)n);
	else if (n instanceof ATruePrimary2) process((ATruePrimary2)n);
	else if (n instanceof AFalsePrimary2) process((AFalsePrimary2)n);
	else if (n instanceof AParensPrimary2) process((AParensPrimary2)n);
	else if (n instanceof ACallPrimary2) process((ACallPrimary2)n);
	else if (n instanceof AArrayrefPrimary2) process((AArrayrefPrimary2)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PPrimary2)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AIconstPrimary2 n) {
        n.getIconst();				// yields TIconst

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ASconstPrimary2 n) {
        n.getSconst();				// yields TSconst

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ANullPrimary2 n) {
        n.getNull();				// yields TNull

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ATruePrimary2 n) {
        n.getTrue();				// yields TTrue

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AFalsePrimary2 n) {
        n.getFalse();				// yields TFalse

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AParensPrimary2 n) {
        n.getLparen();				// yields TLparen
        process(n.getExpr());			// process(PExpr)
        n.getRparen();				// yields TRparen

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ACallPrimary2 n) {
        n.getId();				// yields TId
        n.getLparen();				// yields TLparen
        if (n.getArglist() != null)
            process(n.getArglist());		// process(PArglist)
        n.getRparen();				// yields TRparen

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AArrayrefPrimary2 n) {
        process(n.getArrayref());			// process(PArrayref)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PArrayref n) {
        if (n instanceof ANameArrayref) process((ANameArrayref)n);
	else if (n instanceof APrimaryArrayref) process((APrimaryArrayref)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PArrayref)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ANameArrayref n) {
        n.getId();				// yields TId
        n.getLbrack();				// yields TLbrack
        process(n.getExpr());			// process(PExpr)
        n.getRbrack();				// yields TRbrack

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(APrimaryArrayref n) {
        process(n.getPrimary2());			// process(PPrimary2)
        n.getLbrack();				// yields TLbrack
        process(n.getExpr());			// process(PExpr)
        n.getRbrack();				// yields TRbrack

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PLhs n) {
        if (n instanceof AIdLhs) process((AIdLhs)n);
	else if (n instanceof AArrayrefLhs) process((AArrayrefLhs)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PLhs)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AIdLhs n) {
        n.getId();				// yields TId
        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AArrayrefLhs n) {
        process(n.getArrayref());			// process(PArrayref)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    // ignore this one
    ///////////////////////////////////////////////////////////////
    void process(PArglist n) {
        if (n instanceof AListArglist) process((AListArglist)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PArglist)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }
    // ignore this one
    ///////////////////////////////////////////////////////////////
    void process(AListArglist n) {
        process(n.getExpr());			// process(PExpr)
	for (PArg p : n.getArg())
	    process(p);				// process(PArg)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PArg n) {
        if (n instanceof AArg) process((AArg)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PArg)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AArg n) {
        n.getComma();				// yields TComma
        process(n.getExpr());			// process(PExpr)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }
    // ignore this one
    ///////////////////////////////////////////////////////////////
    void process(PEmptydim n) {
        if (n instanceof AEmptydim) process((AEmptydim)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PEmptydim)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }
    // ignore this one
    ///////////////////////////////////////////////////////////////
    void process(AEmptydim n) {
        n.getLbrack();				// yields TLbrack
        n.getRbrack();				// yields TRbrack

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

}
