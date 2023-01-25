package Assembly.Instruction;

import Assembly.ASMBlock;

public class ASMJumpInst extends ASMInst {
    public ASMBlock dest;

    public ASMJumpInst(ASMBlock dest) {
        this.dest = dest;
    }

    @Override
    public void printASMInst() {
        System.out.println("j   " + "     " + dest.blockName);
    }
}
