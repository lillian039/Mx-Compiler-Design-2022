package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.IRType.IntType;
import LLVMIR.IRType.NullType;
import LLVMIR.Value.ConstInt;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

public class Binary extends IRInstruction {
    public IRValue rs1, rs2;
    public Register ls;
    public String op;
    public IRBaseType irBaseType;

    public Binary(IRValue rs1, IRValue rs2, Register ls, String op) {
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.ls = ls;
        this.op = op;
        if (rs1.IRType instanceof NullType) irBaseType = rs2.IRType;
        else irBaseType = rs1.IRType;
    }

    @Override
    public void printInstruct() {
        // System.out.print("Binay: ");
        System.out.println(ls.valueToString() + " = " + op + " " + irBaseType.typeToString() +
                " " + rs1.valueToString() + ", " + rs2.valueToString());
    }
}
