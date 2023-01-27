package Assembly;

import LLVMIR.IRType.ClassType;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.IRType.IntType;
import LLVMIR.IRType.PtrType;
import LLVMIR.Value.Register;

public class ASMGlobalVarDef {
    String varName;
    String varType;
    int value;
    String strVal;

    public ASMGlobalVarDef(Register val) {
        IRBaseType type = ((PtrType) val.IRType).type;
        varName = val.rename;
        if (type instanceof ClassType) {
            if (type.name.equals("string")) {
                strVal = val.value.valueToString();
                varType = ".asciz";
            }
        } else if (type instanceof PtrType) {
            varType = ".word";
            value = 0;

        } else if (type instanceof IntType) {
            varType = type.size() == 32 ? ".word" : ".byte";
            value = Integer.parseInt(val.value.valueToString());
        }
    }

    public void print() {
        System.out.println(varName + ":");
        if (strVal == null) System.out.println("       " + varType + "    " + value);
        else System.out.println("       " + varType + "    " + strVal);
    }
}
