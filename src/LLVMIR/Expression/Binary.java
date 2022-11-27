package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

public class Binary extends IRInstruction {
    public IRValue rs1, rs2;
    public Register ls;
    public String op;

    public Binary(IRValue rs1, IRValue rs2, Register ls, String op){
        this.rs1=rs1;
        this.rs2=rs2;
        this.ls=ls;
        this.op=op;
    }

}
