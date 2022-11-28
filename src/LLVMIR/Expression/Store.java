package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

public class Store extends IRInstruction {
    Register ls;
    IRValue rs;
    public Store(Register ls,IRValue rs){
        this.ls=ls;
        this.rs=rs;
    }
}
