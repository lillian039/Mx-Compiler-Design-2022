package LLVMIR.IRType;

public class IntType extends IRBaseType{
    int size;

    public IntType(int size,String name){
        this.size=size;
        this.name=name;
    }
    @Override
    public boolean isSameType(IRBaseType irBaseType) {
        return false;
    }

    @Override
    public String typeToString() {
        return "i"+size;
    }

    @Override
    public int size() {
        return size;
    }
}
