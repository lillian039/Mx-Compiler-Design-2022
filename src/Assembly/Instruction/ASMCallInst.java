package Assembly.Instruction;

import Assembly.ASMBlock;

public class ASMCallInst extends ASMInst {
    public String funcName;

    public ASMCallInst(String funcName) {
        this.funcName = funcName;
    }

    @Override
    public void printASMInst() {
        System.out.println("call    " + funcName);
    }
}
