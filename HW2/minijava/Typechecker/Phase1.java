/* This file was generated by the Amherst version of SableCC */

package minijava.Typechecker;

import minijava.node.*;
import minijava.Type.*;
import java.util.List;
import java.util.ArrayList;

public class Phase1
{
    private Typechecker typechecker;
    Phase1(Typechecker typechecker) {
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
        process((AProgram)n);
    }

    ///////////////////////////////////////////////////////////////
    void process(AProgram n) {
        n.getId();				// yields TId
	for (PMaindecl p : n.getMaindecl())
	    process(p);				// process(PMaindecl)
    }

    ///////////////////////////////////////////////////////////////
    void process(PMaindecl n) {
        if (n instanceof AVarMaindecl) process((AVarMaindecl)n);
	    else process((AMethodMaindecl)n);
    }

    ///////////////////////////////////////////////////////////////
    void process(AVarMaindecl n) {
//        process(n.getPrivacy());			// process(PPrivacy)
        n.getStatic();				// yields TStatic
        typechecker.createClassVar(n.getId().getText(), process(n.getType()), null);
    }

    ///////////////////////////////////////////////////////////////
    void process(AMethodMaindecl n) {
        // TODO: what is the point?
//        process(n.getPrivacy());			// process(PPrivacy)
        n.getStatic();				// yields TStatic
        typechecker.createMethod(n.getId().getText(), process(n.getType()), process(n.getParamlist()), null);
    }

    ///////////////////////////////////////////////////////////////
    List<Type> process(PParamlist n) {
        if (n instanceof AListParamlist) return process((AListParamlist)n);
	    else return process((AEmptyParamlist)n);
    }

    ///////////////////////////////////////////////////////////////
    List<Type> process(AListParamlist n) {
        List<Type> typeList = new ArrayList<>();
        typeList.add(process(n.getType()));			// process(PType)
	    for (PParam p : n.getParam())
            typeList.add(process(p));				// process(PParam)
        return typeList;
    }

    ///////////////////////////////////////////////////////////////
    List<Type> process(AEmptyParamlist n) {
        List<Type> typeList = new ArrayList<>();
        return typeList;
    }

    ///////////////////////////////////////////////////////////////
    Type process(PParam n) {
        return process((AParam)n);
    }

    ///////////////////////////////////////////////////////////////
    Type process(AParam n) {
        return process(n.getType());			// process(PType)
    }

    ///////////////////////////////////////////////////////////////
    void process(PPrivacy n) {
        if (n instanceof APublicPrivacy) process((APublicPrivacy)n);
	    else process((ABlankPrivacy)n);
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
    Type process(PType n) {
        return process((AType)n);
    }

    ///////////////////////////////////////////////////////////////
    Type process(AType n) {
	    for (PEmptydim p : n.getEmptydim())
	        process(p);				// process(PEmptydim)
        return typechecker.getType(n.getId());
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
    }

    ///////////////////////////////////////////////////////////////
    void process(ADeclStmt n) {
        typechecker.createClassVar(n.getId().getText(), process(n.getType()), null);
    }

    ///////////////////////////////////////////////////////////////
    void process(AIdFactor n) {
        n.getId();				// yields TId

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(PPrimary n) {
        if (n instanceof ANewarrayPrimary) process((ANewarrayPrimary)n);
	else if (n instanceof APrimary2Primary) process((APrimary2Primary)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PPrimary)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(ANewarrayPrimary n) {
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
    void process(APrimary2Primary n) {
        process(n.getPrimary2());			// process(PPrimary2)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(PPrimary2 n) {
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
    void process(AIconstPrimary2 n) {
        n.getIconst();				// yields TIconst

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(ASconstPrimary2 n) {
        n.getSconst();				// yields TSconst

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(ANullPrimary2 n) {
        n.getNull();				// yields TNull

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(ATruePrimary2 n) {
        n.getTrue();				// yields TTrue

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AFalsePrimary2 n) {
        n.getFalse();				// yields TFalse

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AParensPrimary2 n) {
        n.getLparen();				// yields TLparen
        process(n.getExpr());			// process(PExpr)
        n.getRparen();				// yields TRparen

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(ACallPrimary2 n) {
        n.getId();				// yields TId
        n.getLparen();				// yields TLparen
        if (n.getArglist() != null)
            process(n.getArglist());		// process(PArglist)
        n.getRparen();				// yields TRparen

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AArrayrefPrimary2 n) {
        process(n.getArrayref());			// process(PArrayref)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(PArrayref n) {
        if (n instanceof ANameArrayref) process((ANameArrayref)n);
	else if (n instanceof APrimaryArrayref) process((APrimaryArrayref)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PArrayref)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(ANameArrayref n) {
        n.getId();				// yields TId
        n.getLbrack();				// yields TLbrack
        process(n.getExpr());			// process(PExpr)
        n.getRbrack();				// yields TRbrack

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(APrimaryArrayref n) {
        process(n.getPrimary2());			// process(PPrimary2)
        n.getLbrack();				// yields TLbrack
        process(n.getExpr());			// process(PExpr)
        n.getRbrack();				// yields TRbrack

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(PLhs n) {
        if (n instanceof AIdLhs) process((AIdLhs)n);
	else if (n instanceof AArrayrefLhs) process((AArrayrefLhs)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PLhs)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AIdLhs n) {
        n.getId();				// yields TId

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AArrayrefLhs n) {
        process(n.getArrayref());			// process(PArrayref)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(PArglist n) {
        if (n instanceof AListArglist) process((AListArglist)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PArglist)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AListArglist n) {
        process(n.getExpr());			// process(PExpr)
	for (PArg p : n.getArg())
	    process(p);				// process(PArg)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(PArg n) {
        if (n instanceof AArg) process((AArg)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PArg)");

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(AArg n) {
        n.getComma();				// yields TComma
        process(n.getExpr());			// process(PExpr)

        throw new UnsupportedOperationException ();     // remove when method is complete
    }

    ///////////////////////////////////////////////////////////////
    void process(PEmptydim n) {
        if (n instanceof AEmptydim) process((AEmptydim)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PEmptydim)");
    }

    ///////////////////////////////////////////////////////////////
    void process(AEmptydim n) {
        n.getLbrack();				// yields TLbrack
        n.getRbrack();				// yields TRbrack
    }

}
