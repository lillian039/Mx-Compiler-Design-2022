package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.Value.Entity;
import LLVMIR.Value.Register;

public class Binary extends IRInstruction {
    public Entity rs1, rs2;
    public Register ls;
    public String op;

    public Binary(Entity rs1,Entity rs2,Register ls,String op){
        this.rs1=rs1;
        this.rs2=rs2;
        this.ls=ls;
        this.op=op;
    }

}
