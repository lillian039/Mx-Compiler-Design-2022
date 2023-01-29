package LLVMIR.IRType;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassType extends IRBaseType {
    public ArrayList<IRBaseType> members = new ArrayList<>();

    public HashMap<String, Integer> memberMap = new HashMap<>();

    public int getMap(String name) {
        return memberMap.get(name);
    }

    public boolean isStr = false;

    public ClassType(String name) {
        this.name = name;
    }

    @Override
    public boolean isSameType(IRBaseType irBaseType) {
        return irBaseType == this;
    }

    @Override
    public String typeToString() {
        if (name.equals("string")) return "i8*";
        return "%struct." + name;
    }

    @Override
    public int size() {
        if(name.equals("string"))return 32;
        int sum = 0;
        for (var member : members) {
            sum += member.size();
        }
        //  while (size < sum) size = (size << 1);
        return sum;
    }
}
