package AST.Expression;

import AST.ASTVisitor;
import AST.Atom.FunctionParameterListNode;
import AST.Atom.FunctionParameterValueNode;
import AST.ExprNode;
import AST.Statement.BlockStmtNode;
import Util.Position;

public class LambdaExprNode extends ExprNode {
    public FunctionParameterListNode functionParameterList;
    public FunctionParameterValueNode functionParameterValue;
    public BlockStmtNode funcBody;
    public boolean isGlobe=false;
    public LambdaExprNode(Position pos){
        super(pos);
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
