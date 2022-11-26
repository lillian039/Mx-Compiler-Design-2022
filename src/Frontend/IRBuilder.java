package Frontend;

import AST.ASTVisitor;
import AST.Atom.*;
import AST.Expression.*;
import AST.RootNode;
import AST.Statement.*;
import LLVMIR.BasicBlock;
import LLVMIR.Expression.Allocate;
import LLVMIR.Expression.Binary;
import LLVMIR.GlobalDefine.FuncDef;
import LLVMIR.IRInstruction;
import LLVMIR.Terminal.Branch;
import LLVMIR.Terminal.TerminalStmt;
import LLVMIR.Value.ConstBool;
import LLVMIR.Value.Entity;
import LLVMIR.Value.Register;
import Util.Scope.BlockScope;
import Util.Scope.GlobalScope;
import Util.Scope.Scope;

public class IRBuilder implements ASTVisitor {
    GlobalScope gScope;

    Scope currentScope;
    BasicBlock currentBlock;
    int cnt;
    FuncDef mainFunc, currentFunc;
    Entity currentEntity;
    boolean ifCondition;

    BasicBlock trueTargetBlock;
    BasicBlock falseTargetBlock;

    public IRBuilder(GlobalScope gScope) {
        this.gScope = gScope;
        currentBlock = new BasicBlock();
        mainFunc = new FuncDef(currentBlock);
    }

    @Override
    public void visit(NewClassExprNode node) {

    }

    @Override
    public void visit(FunctionParameterValueNode node) {

    }

    @Override
    public void visit(LambdaExprNode node) {

    }

    @Override
    public void visit(DotVarExprNode node) {

    }

    @Override
    public void visit(NewArrDemNode node) {

    }

    @Override
    public void visit(TypeNode node) {

    }

    @Override
    public void visit(SingleVarDefNode node) {
        Register allocReg = new Register(cnt++, node.typeNode, node.name);
        if (node.expression != null) {
            node.expression.accept(this);
            allocReg.value = currentEntity;
        }
        Allocate allocateInstruction = new Allocate(allocReg, node.typeNode);
        currentFunc.addAllocate(allocateInstruction);

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
    public void visit(RootNode node) {

    }

    @Override
    public void visit(IfStmtNode node) {
        BasicBlock thenBlock=new BasicBlock();
        BasicBlock elseBlock=new BasicBlock();
        BasicBlock newBlock=new BasicBlock();
        falseTargetBlock=elseBlock;
        ifCondition = true;
        node.condition.accept(this);
        ifCondition = false;
        if (node.thenStmt != null) {
            currentScope = new BlockScope(currentScope);
            node.thenStmt.accept(this);
            currentScope = currentScope.parentScope;
        }
        if (node.elseStmt != null) {
            currentScope = new BlockScope(currentScope);
            currentBlock=elseBlock;
            elseBlock=new BasicBlock();
            node.elseStmt.accept(this);
            currentScope = currentScope.parentScope;
            currentFunc.addBlock(elseBlock);
        }

        currentBlock=elseBlock;
    }

    @Override
    public void visit(NewArrNode node) {

    }

    @Override
    public void visit(ArrExprNode node) {

    }

    @Override
    public void visit(ForStmtNode node) {

    }

    @Override
    public void visit(CtrlStmtNode node) {

    }

    @Override
    public void visit(ExprStmtNode node) {

    }

    @Override
    public void visit(FuncExprNode node) {

    }

    @Override
    public void visit(BlockStmtNode node) {

    }

    @Override
    public void visit(ConstExprNode node) {
        if (ifCondition) {
            ConstBool newVal = new ConstBool(node.value.boolVal);
        }

    }

    @Override
    public void visit(WhileStmtNode node) {

    }

    @Override
    public void visit(AssignExprNode node) {

    }

    @Override
    public void visit(BinaryExprNode node) {
        node.ls.accept(this);
        Entity lvalue = currentEntity;
        node.rs.accept(this);
        Entity rvalue = currentEntity;

        Register reg = new Register(cnt++, node.ls.type);
        IRInstruction currentInstruction = new Binary(lvalue, rvalue, reg, node.option);
        currentBlock.push_back(currentInstruction);
    }

    @Override
    public void visit(FunDefStmtNode node) {

    }

    @Override
    public void visit(ReturnStmtNode node) {

    }

    @Override
    public void visit(VarDefStmtNode node) {

    }

    @Override
    public void visit(DotFuncExprNode node) {

    }

    @Override
    public void visit(ClassDefStmtNode node) {

    }

    @Override
    public void visit(FunctionParameterListNode node) {

    }
}
