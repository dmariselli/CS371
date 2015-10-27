package minijava.Typechecker;

import minijava.Type.*;

public class ExprType {
    static class Expr {}
    Expr expr;
    Type type;
    ExprType (Expr e, Type t) {
        expr = e;
        type = t;
    }

}