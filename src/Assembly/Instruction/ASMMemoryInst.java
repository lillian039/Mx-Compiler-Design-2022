package Assembly.Instruction;

import Assembly.ASMVisitor;
import Assembly.Operand.ASMImm;
import Assembly.Operand.ASMReg;

public class ASMMemoryInst extends ASMInst {

    public String name;

    // lw rd, imm(rs2)
    // sw rs1 imm(rs2)
    public ASMMemoryInst(ASMReg rd, ASMReg rs1, ASMReg rs2, ASMImm imm, String name) {
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.imm = imm;
        this.name = name;
    }

    @Override
    public void printASMInst() {
        if (rd != null) System.out.print(name + "  " + "\t" + rd.toString() + ", ");
        else System.out.print(name + "  " + "\t" + rs1.toString() + ", ");
        System.out.println(imm.toString() + "(" + rs2.toString() + ")");
    }

    @Override
    public void accept(ASMVisitor visitor) {
        visitor.visit(this);
    }
}

