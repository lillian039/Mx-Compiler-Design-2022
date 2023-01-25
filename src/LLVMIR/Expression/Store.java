package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.IRVisitor;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

public class Store extends IRInstruction {
    IRValue value;
    Register storeAddr;

    public Store(IRValue value, Register storeAddr) {
        if(value==null){
            System.out.println("??");
        }
        this.value = value;
        this.storeAddr = storeAddr;
    }

    //store from to
    @Override
    public void printInstruct() {
        if (this.storeAddr == null) System.out.println("Store ???");
        if (this.value == null) System.out.println("Store Value???");

        else
            System.out.println("store " + value.IRType.typeToString() + " " + value.valueToString() +
                    ", "+ storeAddr.IRType.typeToString()+" "+ storeAddr.valueToString() ) ;
        //+ ", align " + (value.IRType.size() + 7) / 8);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
