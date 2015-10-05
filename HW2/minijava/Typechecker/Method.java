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
        System.out.println("Bools");
        System.out.println(m.name.equals(this.name));
        System.out.println(returnType.equals(m.returnType));
        System.out.println(paramTypes.equals(m.paramTypes));
        System.out.println("The thing");
        for (int i=0; i<m.paramTypes.size() && i<paramTypes.size(); i++){
            System.out.println(m.paramTypes.get(i).equals(this.paramTypes.get(i)));
        }
        System.out.println("Thing done");
        return (m.name.equals(this.name) && returnType.equals(m.returnType) && paramTypes.equals(m.paramTypes));
    }

}

	
