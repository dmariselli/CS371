package minijava.Typechecker;

import minijava.node.*;
import minijava.Type.*;
import java.lang.Override;

public class Var {

    String name;
    Type type;
    Token tok;

    public Var(String name, Type type, Token tok) {
        this.name = name;
        this.type = type;
        this.tok = tok;
    }

    @Override
    public String toString() {
        return name + " " + type + " " + null;
    }

}
