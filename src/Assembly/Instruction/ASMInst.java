package Assembly.Instruction;

import Assembly.ASMBlock;
import Assembly.ASMVisitor;
import Assembly.Operand.ASMImm;
import Assembly.Operand.ASMReg;

public abstract class ASMInst {
    public ASMReg rd,rs1,rs2;
    public ASMImm imm;
    public abstract void printASMInst();

    public abstract void accept(ASMVisitor visitor);

}
