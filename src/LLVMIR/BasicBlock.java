package LLVMIR;

import LLVMIR.Terminal.TerminalStmt;

import java.util.LinkedList;

public class BasicBlock {

    public  int label;

    public LinkedList<BasicBlock> prev=new LinkedList<>();
    private LinkedList<IRInstruction> stmts=new LinkedList<>();
    private TerminalStmt tailStmt = null;

    public BasicBlock(){ }

    public void push_back(IRInstruction stmt){
        if(stmt instanceof TerminalStmt)tailStmt=(TerminalStmt)stmt;
        else  stmts.add(stmt);
    }
}
