package Frontend;

import AST.ASTVisitor;
import AST.Atom.*;
import AST.ExprNode;
import AST.Expression.*;
import AST.RootNode;
import AST.Statement.*;
import AST.StmtNode;
import LLVMIR.BasicBlock;
import LLVMIR.Expression.Allocate;
import LLVMIR.Expression.Binary;
import LLVMIR.Expression.Load;
import LLVMIR.GlobalDefine.FuncDef;
import LLVMIR.IRInstruction;
import LLVMIR.Terminal.Branch;
import LLVMIR.Expression.CallFunction;
import LLVMIR.Terminal.Jump;
import LLVMIR.Value.*;
import Util.Entity;
import Util.Scope.*;

import java.util.ArrayList;

public class IRBuilder implements ASTVisitor {
    GlobalScope gScope;

    Scope currentScope;
    BasicBlock currentBlock;
    int cnt;
    FuncDef mainFunc, currentFunc;
    IRValue currentEntity;
    BasicBlock trueTargetBlock;
    BasicBlock falseTargetBlock;

    ClassDefStmtNode classNode = null;

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
        node.lhs.accept(this);
        Register ls = (Register) currentEntity;

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
        currentScope.addReg(node.name, allocReg);
        currentFunc.addAllocate(allocateInstruction);

    }

    @Override
    public void visit(VarExprNode node) {
        Register newReg = new Register(cnt++, node.type);
        Register var = currentScope.getReg(node.name);
        newReg.value = var;
        Load loadInstruction = new Load(newReg, var);
        currentBlock.push_back(loadInstruction);
        currentEntity = newReg;
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
        BasicBlock thenBlock = new BasicBlock();
        BasicBlock elseBlock = new BasicBlock();
        BasicBlock newBlock = new BasicBlock();
        trueTargetBlock = thenBlock;
        if (node.elseStmt != null) falseTargetBlock = elseBlock;
        else falseTargetBlock = newBlock;
        node.condition.accept(this);
        if (node.thenStmt != null) {
            currentScope = new BlockScope(currentScope);
            node.thenStmt.accept(this);
            currentScope = currentScope.parentScope;
            Jump jumpBr = new Jump(newBlock);
            currentBlock.push_back(jumpBr);
        }
        if (node.elseStmt != null) {
            currentScope = new BlockScope(currentScope);
            currentBlock = elseBlock;
            elseBlock = new BasicBlock();
            node.elseStmt.accept(this);
            currentScope = currentScope.parentScope;
            Jump jumpBr = new Jump(newBlock);
            currentBlock.push_back(jumpBr);
            currentFunc.addBlock(elseBlock);
        }
        currentBlock = newBlock;
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
        LoopScope loopScope= currentScope.getLoopScope();
        if (node.isBreak) {
            Jump jumpIns = new Jump(loopScope.breakLoop);
            currentBlock.push_back(jumpIns);
        }
        else if(node.isContinue){
            Jump jumpIns=new Jump(loopScope.continueLoop);
            currentBlock.push_back(jumpIns);
        }
    }

    @Override
    public void visit(ExprStmtNode node) {
        node.expression.accept(this);
    }

    @Override
    public void visit(FuncExprNode node) {
        CallFunction callFunction = new CallFunction();
        ArrayList<ExprNode> value = node.parameterValueNode.parameters;
        if (classNode == null) callFunction.target = gScope.getFunc(node.name);
        else {
            callFunction.target = gScope.getFunc(classNode.name + '.' + node.name);
            callFunction.caller = (Register) currentEntity;
        }
        for (int i = 0; i < value.size(); i++) {
            value.get(i).accept(this);
            callFunction.addParameter(currentEntity);
        }
        if (!node.type.type.name.equals("void")) {
            Register newVal = new Register(cnt++, node.type);
            newVal.funcVal = callFunction;
            currentEntity = newVal;
        }
        currentBlock.push_back(callFunction);
    }

    @Override
    public void visit(BlockStmtNode node) {
        currentScope = new BlockScope(currentScope);
        if (node.statements != null) {
            for (StmtNode stmt : node.statements) {
                if (stmt != null) stmt.accept(this);
            }
        }
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(ConstExprNode node) {
        IRValue newVal;
        if (node.value.isBoolVal) {
            newVal = new ConstBool(node.value.boolVal);

        } else if (node.value.isStringVal) {
            newVal = new ConstString(node.value.stringVal);
        } else if (node.value.isIntVal) {
            newVal = new ConstInt(node.value.intVal);
        } else newVal = new Null();
        currentEntity = newVal;
    }

    @Override
    public void visit(WhileStmtNode node) {

    }

    @Override
    public void visit(AssignExprNode node) {

    }

    @Override
    public void visit(BinaryExprNode node) {
        if (node.option.equals("||")) {
            node.ls.accept(this);
            BasicBlock newBlock = new BasicBlock();
            Branch orBr = new Branch(currentEntity, trueTargetBlock, newBlock);
            currentBlock.push_back(orBr);
            currentBlock = newBlock;
            node.rs.accept(this);
        } else if (node.option.equals("&&")) {
            node.ls.accept(this);
            BasicBlock newBlock = new BasicBlock();
            Branch andBr = new Branch(currentEntity, newBlock, falseTargetBlock);
            currentBlock.push_back(andBr);
            currentBlock = newBlock;
            node.rs.accept(this);
        } else {
            node.ls.accept(this);
            IRValue lvalue = currentEntity;
            node.rs.accept(this);
            IRValue rvalue = currentEntity;
            Register reg = new Register(cnt++, node.ls.type);
            IRInstruction currentInstruction = new Binary(lvalue, rvalue, reg, node.option);
            currentBlock.push_back(currentInstruction);
        }
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
        node.lhs.accept(this);
        Register ls = (Register) currentEntity;
        classNode = ls.typeNode.type.classDef;
        node.rhs.accept(this);
        classNode = null;
    }

    @Override
    public void visit(ClassDefStmtNode node) {

    }

    @Override
    public void visit(FunctionParameterListNode node) {

    }
}
