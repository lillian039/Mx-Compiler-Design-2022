package LLVMIR.IRType;

public abstract class IRBaseType {

    public String name;

    public abstract String typeToString();

    public abstract boolean isSameType(IRBaseType irBaseType);

    public boolean isClassIRType() {
        boolean isClassButNotString = (this instanceof PtrType) && (((PtrType) this).type instanceof ClassType && !((PtrType)this).type.name.equals("string"));
        return isClassButNotString;
    }

    public abstract int size();
}
