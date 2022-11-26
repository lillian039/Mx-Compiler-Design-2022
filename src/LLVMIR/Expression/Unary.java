package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.Value.Register;
import Util.Entity;

public class Unary extends IRInstruction {
    public enum opType{
        invert,addSelf,subSelf,not
    }

    Entity rs;
    Register ls;

}
