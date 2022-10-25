package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Util.Position;

public class CellExprNode extends ExprNode {

    public ExprNode expression;

    public String option;

    public CellExprNode(Position pos){
        super(pos);
    }

    public CellExprNode(ExprNode expression,String symbol,Position pos){
        super(pos);
        this.expression=expression;
        this.option=symbol;
    }

    public boolean isInt(){
        return (option.equals("++")||option.equals("--")||option.equals("-")||option.equals("~"));
    }

    public boolean isBool(){
        return (option.equals("!"));
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
