package minijava.Typechecker;

import minijava.node.*;
import minijava.Type.*;
import minijava.Machine.*;
import minijava.Temp.*;
import minijava.Translate.*;

import java.lang.Object;
import java.util.*;
import java.util.ArrayList;

public class Typechecker {

    Start root;
    String fileBaseName;
    Machine machine;
    int methodCounter = -1;
    Map<String, Var> globalST = new HashMap<>();
    LocalST localST = new LocalST();
    Map<String, Label> sconstMap = new HashMap<>();
    Builtins builtins;

    public class LocalST {
        List<Var> symbolVar = new ArrayList<Var>();
        Map<String, Var> localSTMap = new HashMap<>();
        List<List<String>> scope = new ArrayList<>();

        public Var lookup(String name) {
            if (localSTMap.get(name) != null) {
                return localSTMap.get(name);
            }
            return null;
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
        boolean declareLocal(String name, Type type) {
            return declareLocal(name, type, null);
        }
        boolean declareLocal(String name, Type type, Token tok) {
            return declareLocal(name, new Var(name, type, tok));
        }
        boolean declareLocal(String s, Var v){
            if (localSTMap.containsKey(s)) {
                return false;
            }
            localSTMap.put(s, v);
            symbolVar.add(v);
            scope.get(scope.size()-1).add(s);
            return true;
        }
    }

    HashMap<String,Type> typeMap;
    List<Method>         methodList;

    public Typechecker (Start s, String fileBaseName, Machine machine) {
        root = s;
        this.fileBaseName = fileBaseName;
        this.machine = machine;

        this.builtins = new Builtins(machine);
        typeMap = new HashMap<String,Type>();
        typeMap.put ("int", Type.intType);
        typeMap.put ("String", Type.stringType);
        typeMap.put ("void", Type.voidType);
        typeMap.put ("boolean", Type.booleanType);

        methodList = new LinkedList<Method>();
    }

    public void phase1() {
        (new Phase1(this)).process(root);
    }
    public void phase2() {
        (new Phase2(this)).process(root);
    }

    public void printLocalST() {
        for (int i = 0; i < localST.symbolVar.size(); i++) {
            System.out.println(localST.symbolVar.get(i).getString() + " - " + localST.symbolVar.get(i).getType());
        }
    }

    public void printGlobalST() {
        for (String vars : globalST.keySet()) {
            System.out.println(vars + " - " + globalST.get(vars).getType());
        }
    }

    public void printMethodList() {
        for (Method method : methodList) {
            System.out.println(method);
        }
    }

    public Machine getMachine() {
        return machine;
    }

    public Method getCurrentMethod() {
        return methodList.get(methodCounter);
    }

    public void nextMethod() {
        methodCounter++;
    }

    public Var createClassVar(String name, Type type) {
        return createClassVar(name, type, null);
    }

    public Var createClassVar(String name, Type type, Token tok) {
        Var var = new Var(name, type, tok);
        globalST.put(name, var);
        return var;
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
        if (globalST.get(idToken.getText())!=null){
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
