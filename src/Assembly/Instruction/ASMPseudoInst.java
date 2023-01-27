package Assembly.Instruction;

import Assembly.Operand.ASMOperand;
import Assembly.Operand.ASMReg;

import java.util.ArrayList;
import java.util.Arrays;

public class ASMPseudoInst extends ASMInst{

    String op;
    ASMReg rd;
    ASMOperand rs1;

    public static ArrayList<String> opName = new ArrayList<>(Arrays.asList(
            "snez","seqz"));

    public ASMPseudoInst(ASMReg rd,ASMOperand rs1,String op){
        this.rd = rd;
        this.rs1 = rs1;
        this.op = op;
    }
    @Override
    public void printASMInst() {

    }
}
