package AST.Statement;

import AST.ASTVisitor;
import AST.ExprNode;
import AST.StmtNode;
import Util.Position;
import Util.Type;

public class VarDefStmtNode extends StmtNode {
    public String name;
    public Type type;
    public ExprNode expression=null;

    public VarDefStmtNode(Position pos) {
        super(pos);
    }

    public VarDefStmtNode(Type type,String name,Position pos) {
        super(pos);
        this.type=type;
        this.name=name;
    }

    public VarDefStmtNode(Type type,String name,ExprNode expression,Position pos) {
        super(pos);
        this.type=type;
        this.name=name;
        this.expression=expression;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
