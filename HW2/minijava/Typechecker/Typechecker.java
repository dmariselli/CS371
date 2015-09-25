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
		Object o = (new Phase1(this));
                //.process(root);
    }

    public void createClassVar(String name, Type type, Token tok) {
	throw new UnsupportedOperationException();
    }

    public void createMethod(String name, Type returnType, 
			     List<Type> paramTypes, Token tok) {
	throw new UnsupportedOperationException();
    }

    public Type getType(TId idToken) {
	throw new UnsupportedOperationException();
    }

    public Type makeArrayType(Type t, Token tok) {
	throw new UnsupportedOperationException();
    }
	
}
