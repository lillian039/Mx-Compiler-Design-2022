package LLVMIR.Expression;

import AST.Atom.TypeNode;
import LLVMIR.BasicBlock;
import LLVMIR.IRInstruction;
import LLVMIR.Value.Register;

public class Allocate extends IRInstruction {
    public Register reg;

    public TypeNode typeNode;

    public Allocate(Register register,TypeNode typeNode){
        this.reg=register;
        this.typeNode=typeNode;
    }
}
