package LLVMIR.IRType;

public abstract class IRBaseType {

    public String name;
    public abstract String typeToString();

    public abstract boolean isSameType(IRBaseType irBaseType);
    public abstract int size();
}
