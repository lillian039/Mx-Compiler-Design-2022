package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.Value.Register;

public class Load extends IRInstruction {
    public Register ls;
    public Register rs;
    public Load(Register ls,Register rs){
        this.ls=ls;
        this.rs=rs;
    }
}
