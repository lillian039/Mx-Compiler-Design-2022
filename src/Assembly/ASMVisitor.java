package Assembly;

import Assembly.Instruction.*;
import Assembly.Operand.ASMFunc;

public interface ASMVisitor {
    void visit(ASMBinaryArith it);

    void visit(ASMBranchInst it);

    void visit(ASMCallInst it);

    void visit(ASMJumpInst it);

    void visit(ASMLaInst it);

    void visit(ASMLiInst it);

    void visit(ASMMemoryInst it);

    void visit(ASMMoveInst it);

    void visit(ASMPseudoInst it);

    void visit(ASMRetInst it);

    void visit(ASMFunc it);
}
