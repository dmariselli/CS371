package minijava.Translate;

import minijava.Temp.*;
import minijava.Type.Type;

public class ExprType {
    Expr expr;
    Type type;

    public ExprType (Expr e, Type t) {
        expr = e;
        type = t;
    }
    public Type getType() {
        return this.type;
    }
    public Expr getExpr() {
        return this.expr;
    }
}
