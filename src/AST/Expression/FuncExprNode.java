package AST.Expression;

import AST.ASTVisitor;
import AST.Atom.FunctionParameterValueNode;
import AST.Atom.SingleVarDefNode;
import AST.ExprNode;
import AST.Statement.BlockStmtNode;
import Util.Position;
import Util.Type;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.Vector;

public class FuncExprNode extends ExprNode {
    public String name;

    public FunctionParameterValueNode parameterValueNode;

    public FuncExprNode(Position pos) {
        super(pos);
    }

    public FuncExprNode(String name,Position pos) {
        super(pos);
        this.name=name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
