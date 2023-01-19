package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.IRType.PtrType;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

public class Load extends IRInstruction {
    public Register desReg;
    public Register ptr;

    public IRBaseType loadType;

    public Load(Register desReg, Register ptr) {
        this.desReg = desReg;
        this.ptr = ptr;
        loadType = desReg.IRType;
    }

    @Override
    public void printInstruct() {
        System.out.println(desReg.valueToString() + " = load " + loadType.typeToString() + ", " + ptr.IRType.typeToString() + " " +
                ptr.valueToString());
        //", align " + (loadType.size() + 7) / 8);
    }
}
