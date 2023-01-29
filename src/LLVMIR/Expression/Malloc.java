package LLVMIR.Expression;

import AST.Atom.TypeNode;
import LLVMIR.GlobalDefine.FuncDef;
import LLVMIR.IRInstruction;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.IRVisitor;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

public class Malloc extends IRInstruction {
    public Register ptrStart;
    public IRValue size;

    FuncDef malloc;


    public Malloc(Register ptrStart, IRValue size,FuncDef malloc) {
        this.ptrStart = ptrStart;
        this.size = size;
        this.malloc = malloc;
    }

    @Override
    public void printInstruct() {
        System.out.println(ptrStart.valueToString() + " = call i8* @__malloc(" + size.IRType.typeToString()+" "+size.valueToString() + ")");
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
