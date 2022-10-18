package AST.Statement;

import AST.*;
import Util.*;

public class ReturnStmtNode extends StmtNode {
    public Type returnTpye;
    public ReturnStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
