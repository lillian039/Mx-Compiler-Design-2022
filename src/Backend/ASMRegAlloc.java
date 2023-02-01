package Backend;

import Assembly.ASMBlock;
import Assembly.ASMFn;
import Assembly.Instruction.*;
import Assembly.Operand.*;

public class ASMRegAlloc {
    ASMFn asmFn;

    ASMBlock currentBlock;

    ASMPhyReg t0, t1, t2, t3;
    ASMPhyReg s0 = new ASMPhyReg("s0");


    public ASMRegAlloc(ASMFn asmFn) {
        this.asmFn = asmFn;
        t0 = asmFn.getReg("t0");
        t1 = asmFn.getReg("t1");
        t2 = asmFn.getReg("t2");
        t3 = asmFn.getReg("t3");
    }

    public void alloc() {
        for (var asmFunc : asmFn.asmFuncs) {
            visit(asmFunc);
        }
    }

    public void visit(ASMFunc it) {
        ASMFunc currentFunc = new ASMFunc(it.funcName);
        asmFn.addAllocFunc(currentFunc);
        for (var block : it.asmBlocks) {
            currentBlock = new ASMBlock(block.blockName);
            currentFunc.addBlock(currentBlock);
            for (var stmt : block.insts) {
                ASMReg rdRecord = stmt.rd;
                if (stmt.rd instanceof ASMVirReg) {
                    ASMMemoryInst lw = new ASMMemoryInst(t0, null, s0, new ASMImm(((ASMVirReg) stmt.rd).offset), "lw");
                    currentBlock.push_back(lw);
                    stmt.rd = t0;
                }
                if (stmt.rs1 instanceof ASMVirReg) {
                    ASMMemoryInst lw = new ASMMemoryInst(t1, null, s0, new ASMImm(((ASMVirReg) stmt.rs1).offset), "lw");
                    currentBlock.push_back(lw);
                    stmt.rs1 = t1;
                }
                if (stmt.rs2 instanceof ASMVirReg) {
                    ASMMemoryInst lw = new ASMMemoryInst(t2, null, s0, new ASMImm(((ASMVirReg) stmt.rs2).offset), "lw");
                    currentBlock.push_back(lw);
                    stmt.rs2 = t2;
                }

//                if (stmt instanceof ASMMemoryInst && stmt.imm == null) {
//                    ASMBinaryArith addAddr = new ASMBinaryArith(t1, stmt.rs1, stmt.rs2, null, "add");
//                    currentBlock.push_back(addAddr);
//                    stmt.rs2 = t1;
//                    stmt.imm = new ASMImm(0);
//                }

                currentBlock.push_back(stmt);
                if(rdRecord instanceof ASMVirReg){
                    ASMMemoryInst sw = new ASMMemoryInst(stmt.rd, null,s0, new ASMImm(((ASMVirReg) rdRecord).offset), "sw");
                    currentBlock.push_back(sw);
                }
            }
        }
    }
}
