package MIR;

import MIR.Terminal.TerminalStmt;
import Util.Err.IrError;

import java.util.LinkedList;

public class BasicBlock {
    private LinkedList<Statement> stmts=new LinkedList<>();

    private TerminalStmt tailStmt = null;

    public BasicBlock(){ }

    public void push_back(Statement stmt){
        if(tailStmt!=null)throw new IrError("duplicate terminate", stmt.pos);
        if(stmt instanceof TerminalStmt)tailStmt=(TerminalStmt)stmt;
        else  stmts.add(stmt);
    }
}
