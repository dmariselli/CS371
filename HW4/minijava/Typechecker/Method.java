package minijava.Typechecker;

import minijava.Type.*;
import minijava.node.*;
import minijava.Temp.*;
import minijava.Machine.*;
import minijava.Frame.*;

import java.lang.Override;
import java.util.List;

public class Method {

    String name;
    Type returnType;
    List<Type> paramTypes;
    Token tok;
    Label methodLabel;
    Frame frame;
    List<Access> parameterAccess;
    Var hidden;
    Label exitLabel;
    Stm tree;

    public Method(String name, Type returnType, List<Type> paramTypes, Token tok) {
        this.name = name;
        this.returnType = returnType;
        this.paramTypes = paramTypes;
        this.tok = tok;
    }

    public void setMethodLabel(Label label) {
        this.methodLabel = label;
    }

    public void setExitLabel(Label label) {
        this.exitLabel = label;
    }

    public String getName() {
        return this.name;
    }

    public Type getReturnType(){ return this.returnType; }

    public List<Type> getParamTypes() {
        return this.paramTypes;
    }

    @Override
    public String toString(){
        return name + " " + returnType + " " + paramTypes.toString() + " " + tok;
    }

    public boolean equals(Method m){
        return (m.name.equals(this.name) && listCompare(m.paramTypes));
    }

    public void makeFrame(Machine machine) {
        // TODO: Is this what we're supposed to do with the Access objects?
        this.frame = machine.makeFrame(label);
        this.parameterAccess = frame.createParameterAccesses(paramTypes.size());
    }

    public Frame getFrame() {
        return this.frame;
    }

    public boolean listCompare(List<Type> other) {
        if (paramTypes.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < paramTypes.size(); i++) {
            if(!paramTypes.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

}

	
