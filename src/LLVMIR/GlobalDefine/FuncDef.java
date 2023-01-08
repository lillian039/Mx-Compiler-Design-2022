package LLVMIR.GlobalDefine;

import AST.Statement.FunDefStmtNode;
import LLVMIR.BasicBlock;
import LLVMIR.IRInstruction;
import LLVMIR.IRType.ClassType;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.Value.Register;

import java.util.ArrayList;

public class FuncDef extends GlobalDef {

    //是否是内建函数（比如print之类的）
    public boolean isInner;
    public int label;
    public String name;
    public ArrayList<Register> parameterList = new ArrayList<>();
    public FunDefStmtNode originFunc;
    public ArrayList<BasicBlock> basicBlocks = new ArrayList<>();
    public BasicBlock Entry = null;

    public Register retval = null;
    public BasicBlock returnBlock = null;
    public BasicBlock allocate = new BasicBlock();

    public ClassType classType = null;
    public IRBaseType irReturnType;

    public FuncDef(BasicBlock block, String name, IRBaseType irReturnType) {
        Entry = block;
        this.name = name;
        this.label = 1;
        this.irReturnType = irReturnType;
    }

    public FuncDef(String name, IRBaseType irReturnType,boolean isInner) {
        this.name = name;
        this.irReturnType = irReturnType;
        this.isInner = isInner;
    }


    public void addAllocate(IRInstruction instruction) {
        allocate.push_back(instruction);
    }

    public void addBlock(BasicBlock block) {
        basicBlocks.add(block);
    }

    @Override
    public void print() {
        if (isInner) {
            printDeclare();
            return;
        }
        System.out.print("define " + irReturnType.typeToString() + " @" + name + " (");
        for (int i = 0; i < parameterList.size(); i++) {
            Register reg = parameterList.get(i);
            System.out.print(reg.IRType.typeToString() + " " + reg.valueToString());
            if (i < parameterList.size() - 1) System.out.print(", ");
        }
        System.out.println(") {");
        Entry.print();
        System.out.println();
        for (var block : basicBlocks) {
            block.print();
            System.out.println();
        }
        if (returnBlock != null) returnBlock.print();
        System.out.println("}");
    }


    public void printDeclare() {
        System.out.print("declare " + irReturnType.typeToString() + " @" + name + " (");
        for (int i = 0; i < parameterList.size(); i++) {
            Register reg = parameterList.get(i);
            System.out.print(reg.IRType.typeToString());
            if (i < parameterList.size() - 1) System.out.print(", ");
        }
        System.out.println(") #0");
    }
}
