package minijava.Typechecker;

import minijava.node.*;
import minijava.Type.*;

import java.lang.Object;
import java.util.*;
import java.util.ArrayList;

public class Typechecker {

    Start root;

    Map<String, Type> globalST = new HashMap<>();
    LocalST localST = new LocalST();

    public class LocalST {
        List<String> symbolString = new ArrayList<String>();
        List<Type> symbolType = new ArrayList<Type>();
        Map<String, Type> localSTMap = new HashMap<>();
        List<List<String>> scope = new ArrayList<>();

        public Type lookup(String name) {
            return localSTMap.get(name);
        }
        public void increaseScope() {
            scope.add(new ArrayList<String>());
        }
        void decreaseScope(){
            List<String> inner = scope.get(scope.size()-1);
            for (String n : inner) {
                localSTMap.remove(n);
            }
            scope.remove(scope.size()-1);
        }
        boolean declareLocal(String s, Type v){
            if (localSTMap.containsKey(s)) {
                return false;
            }
            localSTMap.put(s, v);
            symbolString.add(s);
            symbolType.add(v);
            scope.get(scope.size()-1).add(s);
            return true;
        }
    }

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
    public void phase2() {
        (new Phase2(this)).process(root);
    }

    public void printLocalST() {
        System.out.println("Called");
        for (int i = 0; i < localST.symbolString.size(); i++) {
            System.out.println(localST.symbolString.get(i) + " - " + localST.symbolType.get(i));
        }
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

    public List<Method> findMethods(String name, List<Type> typeList) {
        List<Method> foundMethods = new ArrayList<>();
        for (Method method : methodList) {
            if (method.getName().equals(name)) {
                boolean isCompatible = true;
                List<Type> methodTypes = method.getParamTypes();
                for (int i = 0; i < typeList.size(); i++) {
                    if (!typeList.get(i).canAssignTo(methodTypes.get(i))) {
                        isCompatible = false;
                    }
                }
                if (isCompatible) {
                    foundMethods.add(method);
                }
            }
        }
        return foundMethods;
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
