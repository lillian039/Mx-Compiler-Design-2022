package Assembly.Instruction;

import Assembly.ASMVisitor;
import Assembly.Operand.ASMVirReg;

public class ASMLaInst extends ASMInst {
    ASMVirReg rd;
    String name;

    public ASMLaInst(ASMVirReg virReg, String name) {
        this.rd = virReg;
        this.name = name;
    }

    @Override
    public void printASMInst() {
        System.out.println("la  " + "\t" + rd.toString() + ", " + name);
    }

    @Override
    public void accept(ASMVisitor visitor) {
        visitor.visit(this);
    }
}
