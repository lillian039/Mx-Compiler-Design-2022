package Assembly.Instruction;

import Assembly.ASMBlock;
import Assembly.Operand.ASMReg;

public class ASMBranchInst extends ASMInst {
    public String typeName;
    public ASMReg rs1, rs2;
    public ASMBlock dest;

    public ASMBranchInst(ASMReg rs1, ASMReg rs2, ASMBlock dest, String typeName) {
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.dest = dest;
        this.typeName = typeName;

    }

    @Override
    public void printASMInst() {
        System.out.print(typeName);
        int length = typeName.length();
        while (length++ != 4) System.out.print(" ");
        System.out.println("    " + rs1.toString() + ", " + rs2.toString() + ", " + dest.blockName);
    }
}
