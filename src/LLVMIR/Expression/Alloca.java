package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.IRType.ClassType;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.IRType.PtrType;
import LLVMIR.Value.ConstString;
import LLVMIR.Value.Register;

public class Alloca extends IRInstruction {
    public Register reg;

    public IRBaseType irType;

    public Alloca(Register register) {
        this.reg = register;
        this.irType = ((PtrType) register.IRType).type;
    }

    @Override
    public void printInstruct() {
        System.out.println(reg.valueToString() + " = alloca " + irType.typeToString());
        //+ ", align " + (irType.size() + 7) / 8);
    }

    public void printGlobalInstruct() {
        String originVal = (irType instanceof PtrType || irType instanceof ClassType) ? "null" : "0";
        System.out.println("@" + reg.name + " = global " + irType.typeToString() + " " + originVal);
        // +", align "+ (irType.size() + 7) / 8);
    }

    public void printString() {
        System.out.print("@" + reg.name + " = " + ((ConstString) reg.value).toAllocate());
    }

    public Integer strNum() {
        return ((ConstString) reg.value).number;
    }
}

