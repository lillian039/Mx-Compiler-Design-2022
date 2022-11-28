package LLVMIR;

import LLVMIR.Terminal.TerminalStmt;

import java.util.LinkedList;

public class BasicBlock {

    public int label;

    public String labelName;

    public LinkedList<BasicBlock> prev = new LinkedList<>();
    private LinkedList<IRInstruction> stmts = new LinkedList<>();
    private TerminalStmt tailStmt = null;

    public BasicBlock() {
    }

    public BasicBlock(String name) {
        this.labelName = name;
    }

    public void push_back(IRInstruction stmt) {
        if (stmt instanceof TerminalStmt) {
            if (tailStmt != null) System.out.print("double tail!");
            tailStmt = (TerminalStmt) stmt;
        } else stmts.add(stmt);
    }

    public void add_prev(BasicBlock basicBlock) {
        prev.add(basicBlock);
    }
}
