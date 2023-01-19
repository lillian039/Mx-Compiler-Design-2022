package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

public class Bitcast extends IRInstruction {

    public IRBaseType fromType;
    public IRBaseType toType;
    public Register storeReg;
    public IRValue value;

    public Bitcast(IRBaseType fromType, IRBaseType toType, Register storeReg, IRValue value) {
        this.fromType = fromType;
        this.toType = toType;
        this.storeReg = storeReg;
        this.value = value;
    }

    @Override
    public void printInstruct() {
        System.out.println(storeReg.valueToString()+" = bitcast "+fromType.typeToString()+" "+value.valueToString() + " to "+toType.typeToString());

    }
}
