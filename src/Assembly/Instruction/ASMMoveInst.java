package Assembly.Instruction;

import Assembly.ASMVisitor;
import Assembly.Operand.ASMReg;

public class ASMMoveInst extends ASMInst {
    public ASMMoveInst(ASMReg rd, ASMReg rs1) {
        this.rd = rd;
        this.rs1 = rs1;
    }

    @Override
    public void printASMInst() {
        System.out.println("mv  " + "\t" + rd.toString() + ", " + rs1.toString());
    }

    @Override
    public void accept(ASMVisitor visitor) {
        visitor.visit(this);
    }
}
