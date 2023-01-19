package LLVMIR.Expression;

import AST.Atom.TypeNode;
import LLVMIR.IRInstruction;
import LLVMIR.IRType.PtrType;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

public class GetElementPtr extends IRInstruction {
    Register startPointer;
    Register tmpStore;
    IRValue elementNum;

    boolean isStruct = false;

    public GetElementPtr(Register tmpStore, Register startPointer, IRValue elementNum) {
        this.startPointer = startPointer;
        this.tmpStore = tmpStore;
        this.elementNum = elementNum;
    }

    public GetElementPtr(Register tmpStore, Register startPointer, IRValue elementNum,boolean isStruct) {
        this.startPointer = startPointer;
        this.tmpStore = tmpStore;
        this.elementNum = elementNum;
        this.isStruct = isStruct;
    }

    @Override
    public void printInstruct() {
        if(isStruct){
            System.out.println(tmpStore.valueToString() + " = getelementptr inbounds " +
                    ((PtrType) startPointer.IRType).type.typeToString() + ", " + startPointer.IRType.typeToString() + " " + startPointer.valueToString()
                    + ", i32 0, " + elementNum.IRType.typeToString() + " " + elementNum.valueToString());
        }
        else {
            System.out.println(tmpStore.valueToString() + " = getelementptr inbounds " +
                    ((PtrType) startPointer.IRType).type.typeToString() + ", " + startPointer.IRType.typeToString() + " " + startPointer.valueToString()
                    + ", " + elementNum.IRType.typeToString() + " " + elementNum.valueToString());
        }
    }
}
