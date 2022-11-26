package LLVMIR.GlobalDefine;

import LLVMIR.BasicBlock;
import LLVMIR.IRInstruction;
import LLVMIR.Value.Register;

import java.util.ArrayList;

public class FuncDef extends GlobalDef {
    public int label;
    public ArrayList<Register> parameterList = new ArrayList<>();
    public ArrayList<BasicBlock> basicBlocks = new ArrayList<>();
    public BasicBlock Entry = null;
    public BasicBlock allocate = new BasicBlock();

    public FuncDef(BasicBlock block) {
        Entry = block;
    }

    public void addAllocate(IRInstruction instruction) {
        allocate.push_back(instruction);
    }

    public void addBlock(BasicBlock block){
        basicBlocks.add(block);
    }
}
