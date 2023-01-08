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
        if(name.equals("string"))return "i8*";
        return "%struct." + name;
    }

    @Override
    public int size() {
        int size = 1, sum = 0;
        for (var member : members) {
            sum += member.size();
        }
        while (size < sum) size = (size << 1);
        return size;
    }
}
