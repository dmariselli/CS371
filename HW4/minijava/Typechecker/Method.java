package minijava.Typechecker;

import minijava.Type.*;
import minijava.node.*;
import minijava.Temp.*;
import minijava.Machine.*;
import minijava.Frame.*;
import minijava.Translate.*;
import minijava.Tree.*;

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
    Stm body;

    public Method(String name, Type returnType, List<Type> paramTypes, Token tok) {
        this.name = name;
        this.returnType = returnType;
        this.paramTypes = paramTypes;
        this.tok = tok;
        this.hidden = new Var("hidden", returnType, null);
    }

    public void startTree(Stm s) {
        body = s;
    }
    //@TODO initialize hidden...how do we make an access for it?
    public void addToTree(Stm s){

        body = new SEQ(body, s);
    }

    public Stm getTree(){ return body; }

    public Var getHidden(){return hidden;}

    public void setMethodLabel(Label label) {
        this.methodLabel = label;
    }

    public Label getMethodLabel() {
        return this.methodLabel;
    }

    public void setExitLabel(Label label) {
        this.exitLabel = label;
    }

    public Label getExitLabel() {
        return this.exitLabel;
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
        this.frame = machine.makeFrame(methodLabel);
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

    public String createICode() {
        Stm b = frame.procEntryExit1(body);
        return "method " + methodLabel + " "
                + b.idString() + " "
                + frame.getFrameInfo() + "\n"
                + new ICode(b, frame)
                + "endMethod\n\n";
    }

}

	
