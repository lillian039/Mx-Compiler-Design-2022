package AST.Statement;

import AST.ASTVisitor;
import AST.StmtNode;
import Util.Position;

public class CtrlStmtNode extends StmtNode {
    public boolean isBreak = false;
    public boolean isContinue = false;

    public CtrlStmtNode(Position pos) {
        super(pos);
    }

    public CtrlStmtNode(String name, Position pos) {
        super(pos);
        if(name.equals("break"))isBreak=true;
        else isContinue=true;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
