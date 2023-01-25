package LLVMIR;

import LLVMIR.Expression.*;
import LLVMIR.Terminal.Branch;
import LLVMIR.Terminal.Jump;
import LLVMIR.Terminal.Ret;

public interface IRVisitor {
    void visit(Alloca it);

    void visit(Binary it);

    void visit(Bitcast it);

    void visit(Call it);

    void visit(GetElementPtr it);

    void visit(Icmp it);

    void visit(Load it);

    void visit(Malloc it);

    void visit(Store it);

    void visit(Trunc it);

    void visit(Zext it);

    void visit(Branch it);

    void visit(Jump it);

    void visit(Ret it);

}
