package Assembly.Instruction;

import Assembly.Operand.ASMImm;
import Assembly.Operand.ASMReg;

public class ASMLoadInst extends ASMInst {
    public ASMReg rd, rs1;
    public ASMImm imm;

    public String loadName;

    public ASMLoadInst(ASMReg rd, ASMReg rs1, ASMImm imm, String loadName) {
        this.rd = rd;
        this.rs1 = rs1;
        this.imm = imm;
        this.loadName = loadName;
    }

    @Override
    public void printASMInst() {
        System.out.println(loadName + "  " + "      " + rs1.toString() + ", " + imm.toString() + "(" + rd.toString() + ")");
    }
}

