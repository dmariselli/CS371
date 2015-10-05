package minijava.Type;

import java.lang.Override;
import java.lang.StringBuffer;

public class ArrayType extends Type {
    Type baseType;

    public ArrayType(Type baseType) {
        this.baseType = baseType;
    }

    public Type getBaseType() {
        return this.baseType;
    }

    @Override
    public String toString() {
        Type base = baseType;
        StringBuffer stringBuffer = new StringBuffer();
        while (base instanceof ArrayType) {
            stringBuffer.append("[]");
            base = ((ArrayType) base).getBaseType();
        }
        return base + "[]" + stringBuffer.toString();
    }
}