package LLVMIR.IRType;

import java.util.ArrayList;

public class ClassType extends IRBaseType {
    public ArrayList<IRBaseType> members = new ArrayList<>();

    public ClassType(String name) {
        this.name = name;
    }

    @Override
    public boolean isSameType(IRBaseType irBaseType) {
        return irBaseType == this;
    }

    @Override
    public String typeToString() {
        return "%struct." + name;
    }
}
