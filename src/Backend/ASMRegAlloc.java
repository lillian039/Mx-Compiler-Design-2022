package Backend;

import Assembly.ASMBlock;
import Assembly.ASMFn;
import Assembly.ASMVisitor;
import Assembly.Instruction.*;
import Assembly.Operand.ASMFunc;

public class ASMRegAlloc implements ASMVisitor {
    ASMFn asmFn;

    ASMBlock currentBlock;

    public ASMRegAlloc(ASMFn asmFn) {
        this.asmFn = asmFn;
    }

    public void alloc() {
        for (var asmFunc : asmFn.asmFuncs) {

        }
    }

    public void visit(ASMFunc it) {
        ASMFunc currentFunc = new ASMFunc(it.funcName);
        asmFn.addAllocFunc(currentFunc);
        for (var block : it.asmBlocks) {
            currentBlock = new ASMBlock(block.blockName);
            currentFunc.addBlock(currentBlock);
            for (var stmt : block.insts) {
                stmt.accept(this);
            }
        }
    }

    public void visit(ASMBinaryArith it) {

    }

    public void visit(ASMBranchInst it) {

    }

    public void visit(ASMCallInst it) {

    }

    public void visit(ASMJumpInst it) {

    }

    public void visit(ASMLaInst it) {

    }

    public void visit(ASMLiInst it) {

    }

    public void visit(ASMMemoryInst it) {

    }

    public void visit(ASMMoveInst it) {

    }

    public void visit(ASMPseudoInst it) {

    }

    public void visit(ASMRetInst it) {

    }


}
