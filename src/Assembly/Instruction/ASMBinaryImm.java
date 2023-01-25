package Assembly.Instruction;

import Assembly.Operand.ASMImm;
import Assembly.Operand.ASMReg;

public class ASMBinaryImm extends ASMInst {
    public ASMReg rd, rs1;
    public ASMImm imm;

    public String binaryType;

    public ASMBinaryImm(ASMReg rd, ASMReg rs1, ASMImm imm, String binaryType) {
        this.imm = imm;
        this.rd = rd;
        this.rs1 = rs1;
        this.binaryType = binaryType;
    }


    @Override
    public void printASMInst() {
        System.out.print(binaryType);
        int length = binaryType.length();
        while (length++ != 4) System.out.print(" ");
        System.out.println("    "+rd.toString() + ", " + rs1.toString() + ", " + imm.toString());
    }
}
