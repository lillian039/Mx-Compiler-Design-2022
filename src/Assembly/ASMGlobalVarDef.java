package Assembly;

import LLVMIR.IRType.ClassType;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.IRType.IntType;
import LLVMIR.IRType.PtrType;
import LLVMIR.Value.ConstString;
import LLVMIR.Value.Register;

public class ASMGlobalVarDef {
    public String varName;
    String varType;
    int value;
    String strVal;

    public ASMGlobalVarDef(Register val) {
        IRBaseType type = ((PtrType) val.IRType).type;
        varName = val.name;
        if (type.isSameType(new PtrType(new IntType(8, "char")))) {
            strVal = "\"" + ((ConstString) val.value).originStr + "\"";
            varType = ".asciz";
        } else if (type instanceof IntType) {
            varType = type.size() == 32 ? ".word" : ".byte";
            value = 0;
        } else {//ptr type
            varType = ".word";
            value = 0;

        }
    }

    public void print() {
        System.out.println(varName + ":");
        if (strVal == null) System.out.println("\t" + varType + "\t" + value);
        else System.out.println("\t" + varType + "\t" + strVal);
    }
}
