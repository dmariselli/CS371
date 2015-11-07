package minijava.Translate;

import minijava.Type.Type;

public class ExprType {
    Expr expr;
    Type type;
    
    ExprType (Expr e, Type t) {
	expr = e;
	type = t;
    }
}
