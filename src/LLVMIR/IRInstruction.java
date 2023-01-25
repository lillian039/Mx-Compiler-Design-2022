package LLVMIR;

public abstract class IRInstruction {
    public int label;

    public abstract void printInstruct();

    public abstract void accept(IRVisitor visitor);
}

