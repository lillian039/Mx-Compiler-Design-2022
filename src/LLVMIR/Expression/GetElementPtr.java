package LLVMIR.Expression;

import AST.Atom.TypeNode;
import LLVMIR.IRInstruction;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

public class GetElementPtr extends IRInstruction {
    Register startPointer;
    Register tmpStore;
    IRValue elementNum;

    public GetElementPtr(Register startPointer, Register tmpStore, IRValue elementNum) {
        this.startPointer = startPointer;
        this.tmpStore = tmpStore;
        this.elementNum = elementNum;
    }

}
