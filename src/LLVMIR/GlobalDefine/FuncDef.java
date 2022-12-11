package LLVMIR.GlobalDefine;

import LLVMIR.BasicBlock;
import LLVMIR.IRInstruction;
import LLVMIR.IRType.ClassType;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.Value.Register;

import java.util.ArrayList;

public class FuncDef extends GlobalDef {
    public boolean isDefault;
    public int label;
    public String name;
    public ArrayList<Register> parameterList = new ArrayList<>();
    public ArrayList<BasicBlock> basicBlocks = new ArrayList<>();
    public BasicBlock Entry = null;

    public Register retval=null;
    public BasicBlock returnBlock=null;
    public BasicBlock allocate = new BasicBlock();

    public ClassType classType=null;

    public IRBaseType irReturnType;

    public FuncDef(BasicBlock block, String name, IRBaseType irReturnType) {
        Entry = block;
        this.name = name;
        this.label = 2;
        this.irReturnType = irReturnType;
    }


    public void addAllocate(IRInstruction instruction) {
        allocate.push_back(instruction);
    }

    public void addBlock(BasicBlock block) {
        basicBlocks.add(block);
    }

    @Override
    public void print() {
        System.out.print("define noundef " + irReturnType.typeToString() + " @_" + name + " (");
        for (int i = 0; i < parameterList.size(); i++) {
            Register reg = parameterList.get(i);
            System.out.print(reg.IRType.typeToString() + " " + reg.toString());
            if (i < parameterList.size() - 1) System.out.print(", ");
        }
        System.out.println(") {");
        Entry.print();
        for (var block : basicBlocks) {
            block.print();
            System.out.println();
        }
        System.out.println("}");
    }
}
