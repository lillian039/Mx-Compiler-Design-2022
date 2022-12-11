package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

public class Store extends IRInstruction {
    IRValue value;
    Register storeReg;

    public Store(IRValue value, Register storeReg) {
        this.value = value;
        this.storeReg = storeReg;
    }

    //store from to
    @Override
    public void printInstruct() {
        if (this.storeReg == null) System.out.println("Store ???");
        else
            System.out.println("store " + value.IRType.typeToString() + " " + value.valueToString() +
                    ", ptr " + storeReg.valueToString() + ", align " + (storeReg.IRType.size() + 7) / 8);
    }
}
