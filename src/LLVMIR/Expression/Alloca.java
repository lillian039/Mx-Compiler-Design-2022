package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.Value.Register;

public class Alloca extends IRInstruction {
    public Register reg;

    public IRBaseType irType;

    public Alloca(Register register, IRBaseType irType) {
        this.reg = register;
        this.irType = irType;
    }

    @Override
    public void printInstruct() {
        System.out.println(reg.valueToString() + " = alloca " + irType.typeToString() + ", align " + (irType.size() + 7) / 8);
    }

    public void printGlobalInstruct() {
        System.out.println("@" + reg.name + " = global " + irType.typeToString() + ", align " + (irType.size() + 7) / 8);
    }
}

