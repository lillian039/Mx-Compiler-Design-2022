package Assembly;

import Assembly.Instruction.ASMInst;
import Assembly.Instruction.ASMMemoryInst;

import java.util.ArrayList;
import java.util.LinkedList;

public class ASMBlock {
    public LinkedList<ASMInst> insts = new LinkedList<>();
    public String blockName;

    public ASMBlock(String name) {
        this.blockName = name;
    }

    public void push_back(ASMInst inst) {
        insts.add(inst);
    }

    public void add_front(ASMInst inst) {
        insts.addFirst(inst);
    }


    public void print() {
        System.out.println(blockName + ":");
        for (var stmt : insts) {
            System.out.print("\t");
            stmt.printASMInst();
        }
    }


}
