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

    public boolean equals(Method m){
        return (m.name.equals(this.name) && listCompare(m.paramTypes));
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

	
