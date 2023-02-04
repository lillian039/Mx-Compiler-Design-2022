package Assembly.Instruction;

import Assembly.ASMBlock;
import Assembly.ASMVisitor;
import Assembly.Operand.ASMImm;
import Assembly.Operand.ASMReg;

import java.util.HashSet;

public abstract class ASMInst {
    public ASMReg rd, rs1, rs2;
    public ASMImm imm;

    public boolean unoptimizable = false;

    public HashSet<ASMReg> use = new HashSet<>();
    public HashSet<ASMReg> def = new HashSet<>();

    public abstract void printASMInst();

    public abstract void accept(ASMVisitor visitor);

}
