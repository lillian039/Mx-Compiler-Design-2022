package AST.Statement;

import AST.ASTVisitor;
import AST.Atom.FunctionParameterListNode;
import AST.Atom.SingleVarDefNode;
import AST.Atom.TypeNode;
import AST.StmtNode;
import Util.Position;


public class FunDefStmtNode extends StmtNode {
    public String name;

    public boolean isInner = false;
    public TypeNode returnTypeNode;

    public FunctionParameterListNode parameterList;

    public BlockStmtNode funcBody;

    public FunDefStmtNode(Position pos) {
        super(pos);
        // parameterList=new FunctionParameterListNode(pos);
    }

    public FunDefStmtNode(Position pos, String name, TypeNode returnTypeNode) {
        super(pos);
        this.name = name;
        this.returnTypeNode = returnTypeNode;
        // parameterList=new FunctionParameterListNode(pos);
    }

    public void add(SingleVarDefNode var) {
        if (parameterList == null) parameterList = new FunctionParameterListNode(var.pos);
        parameterList.add(var);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
