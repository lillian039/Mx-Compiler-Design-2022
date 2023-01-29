package Assembly.Instruction;

import Assembly.ASMVisitor;
import Assembly.Operand.ASMImm;
import Assembly.Operand.ASMReg;

public class ASMMemoryInst extends ASMInst {

    public String name;

    // rd, imm(rs2) ----- rd, rs1(rs2)
    public ASMMemoryInst(ASMReg rd, ASMReg rs1, ASMReg rs2, ASMImm imm, String name) {
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.imm = imm;
        this.name = name;
    }

    @Override
    public void printASMInst() {
        System.out.print(name + "  " + "\t" + rd.toString() + ", " );
        if(imm == null)System.out.println(rs1.toString()+"("+rs2.toString()+")");
        else System.out.println(imm.toString()+"("+rs2.toString()+")");
    }

    @Override
    public void accept(ASMVisitor visitor) {
        visitor.visit(this);
    }
}

