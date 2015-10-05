package minijava.Typechecker;

import minijava.Type.*;
import minijava.node.*;

import java.lang.Override;
import java.util.List;

public class Method {

    String name;
    Type returnType;
    List<Type> paramTypes;
    Token tok;

    public Method(String name, Type returnType, List<Type> paramTypes, Token tok) {
        this.name = name;
        this.returnType = returnType;
        this.paramTypes = paramTypes;
        this.tok = tok;
    }

    @Override
    public String toString(){
        return name + " " + returnType + " " + paramTypes.toString() + " " + tok;
    }

}

	
