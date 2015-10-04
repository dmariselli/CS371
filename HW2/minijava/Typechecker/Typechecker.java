package minijava.Typechecker;

import minijava.node.*;
import minijava.Type.*;

import java.lang.Object;
import java.util.*;

public class Typechecker {

    Start root;

    HashMap<String,Type> typeMap;
    HashMap<String,Var>  classVarMap;
    List<Method>         methodList;

    public Typechecker (Start s) {
	root = s;

	typeMap = new HashMap<String,Type>();
	typeMap.put ("int", Type.intType);
	typeMap.put ("String", Type.stringType);
	typeMap.put ("void", Type.voidType);
	typeMap.put ("boolean", Type.booleanType);

	classVarMap = new HashMap<String,Var>();
	methodList = new LinkedList<Method>();
    }

    public void phase1() {
        (new Phase1(this)).process(root);
    }

    public void createClassVar(String name, Type type, Token tok) {
        Var var = new Var(name, type, tok);
        classVarMap.put(name, var);
    }

    public void createMethod(String name, Type returnType, 
			     List<Type> paramTypes, Token tok) {
	    Method method = new Method(name, returnType, paramTypes, tok);
        methodList.add(method);
    }

    public Type getType(TId idToken) {
	    return typeMap.get(idToken.toString());
    }

    public Type makeArrayType(Type t, Token tok) {
	throw new UnsupportedOperationException();
    }
	
}
