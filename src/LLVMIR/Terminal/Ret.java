package LLVMIR.Terminal;

import LLVMIR.Value.Register;

public class Ret extends TerminalStmt {
    Register returnReg;

    public Ret(Register reg) {
        returnReg = reg;
    }

    @Override
    public void printInstruct() {

    }
}
