package Assembly;

import Assembly.Instruction.ASMInst;
import Assembly.Instruction.ASMMemoryInst;
import Assembly.Operand.ASMReg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class ASMBlock {
    public LinkedList<ASMInst> insts = new LinkedList<>();
    public String blockName;

    public ArrayList<ASMBlock> pred = new ArrayList<>();
    public ArrayList<ASMBlock> succ = new ArrayList<>();

    public HashSet<ASMReg> use = new HashSet<>();

    public HashSet<ASMReg> def = new HashSet<>();
    public ASMBlock(String name) {
        this.blockName = name;
    }

    public void push_back(ASMInst inst) {
        insts.add(inst);
    }

    public void add_front(ASMInst inst) {
        insts.addFirst(inst);
    }

    public void add_pred(ASMBlock block){
        pred.add(block);
    }

    public void add_succ(ASMBlock block){
        succ.add(block);
    }

    public void print() {
        System.out.println(blockName + ":");
        for (var stmt : insts) {
            System.out.print("\t");
            stmt.printASMInst();
        }
    }


}
