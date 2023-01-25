package Backend;

import Assembly.ASMBlock;
import LLVMIR.Expression.*;
import LLVMIR.IRVisitor;
import LLVMIR.RootIR;
import LLVMIR.Terminal.Branch;
import LLVMIR.Terminal.Jump;
import LLVMIR.Terminal.Ret;

import java.util.HashMap;

public class InstructionSelector implements IRVisitor {
    RootIR rootIR;

    ASMBlock currentASMBlock;


    HashMap<String, ASMBlock> ASMBlockCollect = new HashMap<>();

    public InstructionSelector(RootIR rootIR) {
        this.rootIR = rootIR;
    }


    public void visit(Alloca it) {

    }

    public void visit(Binary it) {

    }

    public void visit(Bitcast it) {

    }

    public void visit(Call it) {

    }

    public void visit(GetElementPtr it) {

    }

    public void visit(Icmp it) {

    }

    public void visit(Load it) {

    }

    public void visit(Malloc it) {

    }

    public void visit(Store it) {

    }

    public void visit(Trunc it) {

    }

    public void visit(Zext it) {

    }

    public void visit(Branch it) {

    }

    public void visit(Jump it) {

    }

    public void visit(Ret it) {

    }
}
