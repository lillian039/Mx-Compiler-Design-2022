package Assembly.Instruction;

import Assembly.Operand.ASMImm;
import Assembly.Operand.ASMReg;

public class ASMBinaryReg extends ASMInst {
    public ASMReg rd, rs1, rs2;

    public String typeName;

    public  ASMBinaryReg(ASMReg rd, ASMReg rs1,ASMReg rs2,String typeName){
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.typeName = typeName;
    }

    @Override
    public void printASMInst() {
        System.out.print(typeName);
        int length = typeName.length();
        while (length++ != 4) System.out.print(" ");
        System.out.println("    "+rd.toString() + ", " + rs1.toString() + ", " + rs2.toString());
    }
}
