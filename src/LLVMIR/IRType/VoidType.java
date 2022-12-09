package LLVMIR.IRType;

public class VoidType extends IRBaseType {
    @Override
    public boolean isSameType(IRBaseType irBaseType) {
        return (irBaseType instanceof VoidType);
    }

    @Override
    public String typeToString() {
        return "void";
    }

    @Override
    public int size() {
        return 0;
    }
}
