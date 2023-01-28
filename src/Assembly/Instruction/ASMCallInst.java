package Assembly.Instruction;

import Assembly.ASMBlock;
import Assembly.ASMVisitor;

public class ASMCallInst extends ASMInst {
    public String funcName;

    public ASMCallInst(String funcName) {
        this.funcName = funcName;
    }

    @Override
    public void printASMInst() {
        System.out.println("call\t" + funcName);
    }

    @Override
    public void accept(ASMVisitor visitor) {
        visitor.visit(this);
    }
}
