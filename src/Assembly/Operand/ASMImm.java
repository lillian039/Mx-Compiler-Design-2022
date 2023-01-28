package Assembly.Operand;

public class ASMImm extends ASMOperand {
    int imm;

    public ASMImm(int imm) {
        this.imm = imm;
    }


    @Override
    public String toString() {
        return "" + imm;
    }
}
