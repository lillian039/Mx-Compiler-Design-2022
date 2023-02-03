package LLVMIR;

import LLVMIR.Terminal.TerminalStmt;

import java.util.LinkedList;

public class BasicBlock {

    public int label;

    public String labelName;

    public LinkedList<BasicBlock> prev = new LinkedList<>();
    public LinkedList<IRInstruction> stmts = new LinkedList<>();
    public TerminalStmt tailStmt = null;

    public BasicBlock() {
    }

    public BasicBlock(String name, int label) {
        this.labelName = name;
        this.label = label;
    }

    public void push_back(IRInstruction stmt) {
        if (stmt instanceof TerminalStmt) {
            if (tailStmt == null) tailStmt = (TerminalStmt) stmt;
        } else stmts.add(stmt);
    }

    public void add_prev(BasicBlock basicBlock) {
        prev.add(basicBlock);
    }

    public void print() {
        if (labelName != null && !labelName.equals("entry")) System.out.println(labelName + ":");
//        for (int i = 0; i < prev.size(); i++) {
//            System.out.print(prev.get(i).labelName);
//            if (i != prev.size() - 1) System.out.print(", ");
//        }
//        System.out.println(" ");
        for (var stmt : stmts) {
            System.out.print("  ");
            stmt.printInstruct();
        }
        if (tailStmt != null) {
            System.out.print("  ");
            tailStmt.printInstruct();
        }
    }

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
