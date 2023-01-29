package Assembly.Instruction;

import Assembly.ASMVisitor;
import Assembly.Operand.ASMImm;
import Assembly.Operand.ASMReg;

public class ASMBinaryArith extends ASMInst {
    public String typeName;

    public ASMBinaryArith(ASMReg rd, ASMReg rs1, ASMReg rs2, ASMImm imm, String typeName) {
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.imm = imm;
        this.typeName = typeName;
    }

    @Override
    public void printASMInst() {
        System.out.print(typeName);
        int length = typeName.length();
        while (length++ != 4) System.out.print(" ");
        System.out.print("\t" + rd.toString() + ", " + rs1.toString() + ", ");
        if (imm == null) System.out.println(rs2.toString());
        else System.out.println(imm.toString());
    }

    @Override
    public void accept(ASMVisitor visitor) {
        visitor.visit(this);
    }
}
