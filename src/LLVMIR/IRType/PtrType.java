package LLVMIR.IRType;

public class PtrType extends IRBaseType {
    public IRBaseType type;

    @Override
    public boolean isSameType(IRBaseType irBaseType) {
        if (!(irBaseType instanceof PtrType)) return false;
        return type.isSameType(((PtrType) irBaseType).type);
    }

    @Override
    public String typeToString() {
        return type.typeToString()+"*";
    }

    public PtrType(IRBaseType type){
        this.type=type;
    }

    @Override
    public int size() {
        return 32;
    }
}
