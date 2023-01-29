package Assembly.Instruction;

import Assembly.ASMVisitor;
import Assembly.Operand.ASMImm;
import Assembly.Operand.ASMReg;

import java.util.ArrayList;
import java.util.Arrays;

public class ASMCmpInst extends ASMInst {

    public String op;

    //rd == rs1||imm snez xxx

    public static ArrayList<String> opName = new ArrayList<>(Arrays.asList(
            "snez", "seqz"));

    public ASMCmpInst(ASMReg rd, ASMReg rs1, ASMImm imm, String op) {
        this.rd = rd;
        this.rs1 = rs1;
        this.imm = imm;
        this.op = op;
    }

    @Override
    public void printASMInst() {
        System.out.println(op + "\t" + rd.toString() + ", " + rs1.toString());
    }

    @Override
    public void accept(ASMVisitor visitor) {
        visitor.visit(this);
    }
}
