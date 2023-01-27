package Assembly;

import Assembly.Instruction.ASMInst;

import java.util.ArrayList;

public class ASMBlock {
    public ArrayList<ASMInst> insts = new ArrayList<>();
    public String blockName;

    public ASMBlock(String name) {
        this.blockName = name;
    }

    public void push_back(ASMInst inst){
        insts.add(inst);
    }

    public void print() {
        System.out.println(blockName + ":");
        for (var stmt : insts) {
            System.out.print("  ");
            stmt.printASMInst();
        }
    }


}
