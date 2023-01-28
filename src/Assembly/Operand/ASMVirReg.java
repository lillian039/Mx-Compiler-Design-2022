package Assembly.Operand;

public class ASMVirReg extends ASMReg{

    public int index;

    public int offset;
    public ASMVirReg(int index,int offset){
        this.index = index;
        this.offset = offset;
    }
    @Override
    public String toString() {
        return "%"+index;
    }
}
