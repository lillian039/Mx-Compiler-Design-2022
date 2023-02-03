package Assembly.Operand;

import Assembly.ASMBlock;
import Assembly.ASMVisitor;

import java.util.ArrayList;

public class ASMFunc {
    public String funcName;
    public ArrayList<ASMBlock> asmBlocks = new ArrayList<>();

    public ASMImm maxOffset;

    public int maxFuncParaOffset;

    public ASMFunc(String funcName) {
        this.funcName = funcName;
    }

    public void addBlock(ASMBlock block) {
        asmBlocks.add(block);
    }

    public ASMBlock getHead() {
        return asmBlocks.get(0);
    }

    public ASMBlock getTail() {
        return asmBlocks.get(asmBlocks.size() - 1);
    }

    public void printFunc(){
        for(var asmblock:asmBlocks){
            asmblock.print();
        }
    }

    public void accept(ASMVisitor visitor){
        visitor.visit(this);
    }

}
