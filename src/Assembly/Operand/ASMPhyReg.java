package Assembly.Operand;

public class ASMPhyReg extends ASMReg {
    String regName;

    public ASMPhyReg(String regName) {
        this.regName = regName;
    }

    @Override
    public String toString() {
        return regName;
    }
}
