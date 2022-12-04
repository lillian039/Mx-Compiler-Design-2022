package LLVMIR.IRType;

public class PtrType extends IRBaseType {
    public int dimension;
    public IRBaseType type;

    @Override
    public boolean isSameType(IRBaseType irBaseType) {
        if (!(irBaseType instanceof PtrType)) return false;
        return dimension == ((PtrType) irBaseType).dimension && type.isSameType(((PtrType) irBaseType).type);
    }

    @Override
    public String typeToString() {
        return "ptr";
    }
}
