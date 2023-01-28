package Assembly.Instruction;

import Assembly.ASMVisitor;
import Assembly.Operand.ASMImm;
import Assembly.Operand.ASMReg;

public class ASMLiInst extends ASMInst {
    public ASMReg rd;
    public ASMImm imm;

    public ASMLiInst(ASMReg rd, ASMImm imm) {
        this.imm = imm;
        this.rd = rd;
    }

    @Override
    public void printASMInst() {
            System.out.println("li  " + "\t" + rd.toString() + ", " + imm.toString());
    }

    @Override
    public void accept(ASMVisitor visitor) {
        visitor.visit(this);
    }
}
