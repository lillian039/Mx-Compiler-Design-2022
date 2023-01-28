package Assembly.Instruction;

import Assembly.ASMBlock;
import Assembly.ASMVisitor;
import Assembly.Operand.ASMOperand;
import Assembly.Operand.ASMReg;

public class ASMBranchInst extends ASMInst {
    public String typeName;
    public ASMReg rs1;
    public String  dest;

    public ASMBranchInst(ASMReg rs1, String dest, String typeName) {
        this.rs1 = rs1;
        this.dest = dest;
        this.typeName = typeName;

    }

    @Override
    public void printASMInst() {
        System.out.print(typeName);
        int length = typeName.length();
        while (length++ != 4) System.out.print(" ");
        System.out.println("\t" + rs1.toString() + ", " + dest);
    }

    @Override
    public void accept(ASMVisitor visitor) {
        visitor.visit(this);
    }
}
