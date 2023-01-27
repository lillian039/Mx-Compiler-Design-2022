package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.IRVisitor;
import LLVMIR.Value.ConstInt;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

import java.util.ArrayList;
import java.util.Arrays;

public class Icmp extends IRInstruction {
    public IRValue rs1, rs2;
    public Register ls;
    public String op;

    public static ArrayList<String> opName = new ArrayList<>(Arrays.asList(
            "slt", "sgt", "sle", "sge", "eq", "ne"));

    public Icmp(IRValue rs1, IRValue rs2, Register ls, String op) {
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.ls = ls;
        this.op = op;
    }


    void swapR(){
        IRValue tmp = rs1;
        rs1 = rs2;
        rs2 = tmp;
    }

    public void swapInst() {
        if (op.equals("sgt")) {
            op = "slt";
            swapR();
        }
        else if (op.equals("sle")) {
            op = "sge";
            swapR();
        }

    }

    @Override
    public void printInstruct() {
        System.out.println(ls.valueToString() + " = icmp " + op + " " +
                rs1.IRType.typeToString() + " " + rs1.valueToString() + ", " + rs2.valueToString());
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
