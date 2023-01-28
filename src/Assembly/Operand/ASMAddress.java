package Assembly.Operand;

public class ASMAddress extends ASMOperand {
    public boolean isGlobal;
    public ASMReg asmReg;
    public ASMOperand offset;

    public ASMAddress(ASMOperand offset, ASMReg startReg) {
        this.asmReg = startReg;
        this.offset = offset;
    }

    @Override
    public String toString() {
        return offset.toString() + "(" + asmReg.toString() + ")";
    }
}
