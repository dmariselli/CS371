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

    public void printClassVarMap() {
        for (String vars : classVarMap.keySet()) {
            System.out.println(classVarMap.get(vars));
        }
    }

    public void printMethodList() {
        for (Method method : methodList) {
            System.out.println(method);
        }
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
	    return typeMap.get(idToken.getText());
    }

    public Type makeArrayType(Type t, Token tok) {
        Type arrayType = new ArrayType(t);
        return arrayType;
    }

    public boolean checkVarType(TId idToken) {
        Type type = getType(idToken);
        if (type!=null){
            if (type!=Type.voidType) {
                //Returns true if legal
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public boolean checkMethodType(TId idToken) {
        Type type = getType(idToken);
        if (type!=null){
                return true;
        }
        else{
            return false;
        }
    }

    public boolean checkIfVarDeclared(TId idToken){
        if (classVarMap.get(idToken.getText())!=null){
            //Already declared
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkIfMethodDeclared(String name, Type returnType,
                                         List<Type> paramTypes, Token tok){
        Method temp = new Method(name, returnType, paramTypes, tok);
        for (int i=0; i<methodList.size(); i++) {
            if (methodList.get(i).equals(temp)) {
                //Already declared
                return false;
            }
        }
        return true;
    }

}
