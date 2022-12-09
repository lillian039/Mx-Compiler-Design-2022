package LLVMIR.IRType;

public class ArrType extends IRBaseType {
    public int dimension;
    public IRBaseType type;

    @Override
    public boolean isSameType(IRBaseType irBaseType) {
        if (!(irBaseType instanceof ArrType)) return false;
        return type.isSameType(((ArrType) irBaseType).type) && ((ArrType) irBaseType).dimension == dimension;
    }

    @Override
    public String typeToString() {
        return '[' + dimension + " x " + type.typeToString() + ']';
    }

    @Override
    public int size() {
        return 32;
    }
}
