package Assembly.Instruction;

import Assembly.ASMBlock;

public class ASMCallInst extends ASMInst {
    public ASMBlock func;

    public ASMCallInst(ASMBlock func) {
        this.func = func;
    }

    @Override
    public void printASMInst() {
        System.out.println("call    " + func.blockName);
    }
}
