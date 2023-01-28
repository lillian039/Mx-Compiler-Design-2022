package Assembly.Instruction;

import Assembly.ASMVisitor;
import Assembly.Operand.ASMAddress;
import Assembly.Operand.ASMReg;

public class ASMMemoryInst extends ASMInst {
    public ASMReg rd;
    public ASMAddress address;

    public String loadName;

    public ASMMemoryInst(ASMReg rd, ASMAddress address, String loadName) {
        this.rd = rd;
        this.address = address;
        this.loadName = loadName;
    }

    @Override
    public void printASMInst() {
        System.out.println(loadName + "  " + "\t" + rd.toString() + ", " + address.toString());
    }

    @Override
    public void accept(ASMVisitor visitor) {
        visitor.visit(this);
    }
}

