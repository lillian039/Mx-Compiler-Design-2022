package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

//Zero extend
public class Zext extends IRInstruction {
    public IRBaseType fromType;
    public IRBaseType toType;
    public Register storeReg;
    public IRValue value;

    public Zext(IRBaseType fromType, IRBaseType toType, Register storeReg, IRValue value) {
        this.fromType = fromType;
        this.toType = toType;
        this.storeReg = storeReg;
        this.value = value;
    }

    @Override
    public void printInstruct() {
        System.out.println(storeReg.valueToString() + " = zext " + fromType.typeToString() + " " + value.valueToString() + " to " + toType.typeToString());
    }
}
