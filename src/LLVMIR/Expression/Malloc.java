package LLVMIR.Expression;

import AST.Atom.TypeNode;
import LLVMIR.IRInstruction;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

public class Malloc extends IRInstruction {
    TypeNode typeNode;
    Register ptrStart;
    IRValue size;

    public Malloc(TypeNode typeNode,Register ptrStart,IRValue size){
        this.typeNode=typeNode;
        this.ptrStart=ptrStart;
        this.size=size;
    }
}
