package LLVMIR.Expression;

import AST.Atom.TypeNode;
import LLVMIR.IRInstruction;
import LLVMIR.Value.Register;

public class GetElementPtr extends IRInstruction {
    TypeNode typeNode;
    Register startPointer;
    int elementNum;

    public GetElementPtr(TypeNode typeNode, Register startPointer, int elementNum) {
        this.typeNode = typeNode;
        this.startPointer = startPointer;
        this.elementNum = elementNum;
    }

}
