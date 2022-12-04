package LLVMIR.IRType;

public class NullType extends IRBaseType {
    @Override
    public boolean isSameType(IRBaseType irBaseType) {
        return (irBaseType instanceof NullType);
    }

    @Override
    public String typeToString() {
        return null;
    }
}
