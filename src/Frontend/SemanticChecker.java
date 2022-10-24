package Frontend;

import AST.*;

import AST.Atom.*;
import AST.Expression.*;
import AST.Statement.*;
import Util.Err.SemanticError;
import Util.Err.SyntaxError;
import Util.GlobalScope;
import Util.Scope;
import Util.Type;

public class SemanticChecker implements ASTVisitor {

    private Scope currentScope;

    private GlobalScope gScope;

    public SemanticChecker(GlobalScope gScope_) {
        currentScope = gScope = gScope_;
    }

    @Override
    public void visit(RootNode node) {
        for(VarDefStmtNode stmt:node.varDef)stmt.accept(this);
        node.mainNode.accept(this);
    }


    @Override
    public void visit(AssignExprNode node) {

    }

    @Override
    public void visit(NewExprNode node) {

    }

    @Override
    public void visit(VarExprNode node) {

    }

    @Override
    public void visit(DelayCellExpr node) {

    }

    @Override
    public void visit(CellExprNode node) {

    }

    @Override
    public void visit(BinaryExprNode node) {

    }

    @Override
    public void visit(ConstExprNode node) {

    }

    @Override
    public void visit(FuncExprNode node) {

    }

    @Override
    public void visit(DotFuncExprNode node) {

    }

    @Override
    public void visit(BlockStmtNode node) {

    }

    @Override
    public void visit(VarDefStmtNode node) {

    }

    @Override
    public void visit(FunDefStmtNode node) {
        if(currentScope!=gScope||!currentScope.isClass)throw new SyntaxError("can't define function here",node.pos);
        if(currentScope.funcNameValid(node.name))throw new SemanticError("rename function",node.pos);
        currentScope=new Scope(currentScope);
        for(SingleVarDefNode varDef :node.parameterList.parameterList) {
            if(!currentScope.varNameValid(varDef.name))throw new SemanticError("rename variable",node.pos);
            currentScope.addVarDefine(varDef.name,varDef);
        }
        node.funcBody.accept(this);
        currentScope=currentScope.parentScope;
        currentScope.addFunDefine(node.name,node);
    }

    @Override
    public void visit(ClassDefStmtNode node) {
        if(currentScope!=gScope)throw new SyntaxError("Class only can be defined out of main", node.pos);
        if(gScope.hasType(node.name))throw new SyntaxError("Class name already exist", node.pos);
        Type newClassType = new Type(node.name);
        currentScope=new Scope(currentScope);
        currentScope.makeClassScope(newClassType.name);
        for (var stmt : node.classBody.statements) {
            if (stmt instanceof FunDefStmtNode) {
                stmt.accept(this);
            }
            else if (stmt instanceof VarDefStmtNode) {
                stmt.accept(this);
            }
            else throw new SyntaxError("Class only can have varder and fundef", node.pos);
        }
        currentScope=currentScope.parentScope;
    }

    @Override
    public void visit(IfStmtNode node) {

    }

    @Override
    public void visit(ForStmtNode node) {

    }

    @Override
    public void visit(WhileStmtNode node) {

    }

    @Override
    public void visit(CtrlStmtNode node) {

    }

    @Override
    public void visit(ExprStmtNode node) {

    }

    @Override
    public void visit(ReturnStmtNode node) {

    }

    @Override
    public void visit(SingleVarDefNode node) {

    }

    @Override
    public void visit(TypeNode node) {

    }

    @Override
    public void visit(NewArrDemNode node) {

    }

    @Override
    public void visit(DotVarExprNode node) {

    }

    @Override
    public void visit(LambdaExprNode node) {

    }

    @Override
    public void visit(FunctionParameterListNode node) {

    }

    @Override
    public void visit(FunctionParameterValueNode node) {

    }
}
