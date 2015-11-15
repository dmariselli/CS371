package minijava.Typechecker;

import minijava.node.*;
import minijava.Type.*;
import minijava.Temp.*;
import minijava.Frame.*;
import java.lang.Override;

public class Var {

    String name;
    Type type;
    Token tok;
    Label label;
    Access access;

    public Var(String name, Type type, Token tok) {
        this.name = name;
        this.type = type;
        this.tok = tok;
    }

    public String getString() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Access getAccess(){
        return access;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
    public void setAccess(Access access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return name + " " + type + " " + tok;
    }

}
