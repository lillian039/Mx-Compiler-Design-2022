package Assembly.Instruction;

import Assembly.ASMBlock;
import Assembly.ASMVisitor;

public class ASMJumpInst extends ASMInst {
    public String dest;

    public ASMJumpInst(String dest) {
        this.dest = dest;
    }

    @Override
    public void printASMInst() {
        System.out.println("j    " + "\t" + dest);
    }

    @Override
    public void accept(ASMVisitor visitor) {
        visitor.visit(this);
    }
}
