package Assembly.Instruction;

import Assembly.Operand.ASMReg;

public class ASMMoveInst extends ASMInst {
    public ASMReg rd, rs1;

    public ASMMoveInst(ASMReg rd, ASMReg rs1) {
        this.rd = rd;
        this.rs1 = rs1;
    }

    @Override
    public void printASMInst() {
        System.out.println("mv  " + "     " + rd.toString() + ", " + rs1.toString());
    }
}
