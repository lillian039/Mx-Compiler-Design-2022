package Assembly.Instruction;

import Assembly.ASMVisitor;

public abstract class ASMInst {
    public abstract void printASMInst();

    public abstract void accept(ASMVisitor visitor);
}
