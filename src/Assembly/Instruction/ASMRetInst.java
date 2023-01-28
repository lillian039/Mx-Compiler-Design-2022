package Assembly.Instruction;

import Assembly.ASMVisitor;

public class ASMRetInst extends ASMInst {
    @Override
    public void printASMInst() {
        System.out.println("ret");
    }

    @Override
    public void accept(ASMVisitor visitor) {
        visitor.visit(this);
    }
}
