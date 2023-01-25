package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.IRVisitor;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

//trunc 截断
public class Trunc extends IRInstruction {
    public IRBaseType fromType;
    public IRBaseType toType;
    public Register storeReg;
    public IRValue value;

    public Trunc(IRBaseType fromType, IRBaseType toType, Register storeReg, IRValue value) {
        this.fromType = fromType;
        this.toType = toType;
        this.storeReg = storeReg;
        this.value = value;
    }

    @Override
    public void printInstruct() {
        System.out.println(storeReg.valueToString() + " = trunc " + fromType.typeToString() + " " + value.valueToString() + " to " + toType.typeToString());
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
