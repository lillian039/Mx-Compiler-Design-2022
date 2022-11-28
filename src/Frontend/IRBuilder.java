package Frontend;

import AST.ASTVisitor;
import AST.Atom.*;
import AST.ExprNode;
import AST.Expression.*;
import AST.RootNode;
import AST.Statement.*;
import AST.StmtNode;
import LLVMIR.BasicBlock;
import LLVMIR.Expression.*;
import LLVMIR.GlobalDefine.ClassDef;
import LLVMIR.GlobalDefine.FuncDef;
import LLVMIR.IRInstruction;
import LLVMIR.Terminal.Branch;
import LLVMIR.Terminal.Jump;
import LLVMIR.Terminal.Ret;
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
    ClassDef currentClass;
    IRValue currentEntity;
    BasicBlock trueTargetBlock;
    BasicBlock falseTargetBlock;

    ClassDefStmtNode classNode = null;

    RootNode root;

    public IRBuilder(GlobalScope gScope) {
        this.gScope = gScope;
        currentBlock = new BasicBlock();
        mainFunc = new FuncDef(currentBlock, "main");
    }

    @Override
    public void visit(NewClassExprNode node) {

    }

    @Override
    public void visit(DotVarExprNode node) {
        node.lhs.accept(this);
        Register ls = (Register) currentEntity;
        Register newLs = new Register(cnt++, ls.typeNode);
        String className = ls.typeNode.type.name;
        VarExprNode var = (VarExprNode) node.rhs;
        ConstInt number = new ConstInt(root.getClass(className).getMap(var.name));
        GetElementPtr getPtr = new GetElementPtr(ls, newLs, number);
        newLs.getElementPtr = getPtr;
        currentBlock.push_back(getPtr);
        currentEntity = newLs;
    }

    @Override
    public void visit(NewArrDemNode node) {
        if (node.arrDimension != null) node.arrDimension.accept(this);
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
        String op = node.option;

        if (node.option.equals("!")) {

        } else if (node.option.equals("-")) {

        } else if (node.option.equals("~")) {

        } else if (node.option.equals("++") || node.option.equals("--")) {
            node.expression.accept(this);
            Register newLs = new Register(cnt++, node.type);
            String option = node.option.equals("++") ? "+" : "-";
            Binary binary = new Binary(currentEntity, new ConstInt(1), newLs, option);
            currentBlock.push_back(binary);
            Register originReg = currentScope.getReg(((Register) currentEntity).name);
            Store store = new Store(originReg, newLs);
            currentBlock.push_back(store);
        }

    }

    @Override
    public void visit(RootNode node) {
        root = node;
        for (StmtNode stmt : node.statements) {
            stmt.accept(this);
        }

    }

    @Override
    public void visit(IfStmtNode node) {
        node.condition.accept(this);

        BasicBlock thenBlock = new BasicBlock();
        BasicBlock elseBlock = new BasicBlock();
        BasicBlock newBlock = new BasicBlock();
        trueTargetBlock = thenBlock;
        if (node.elseStmt != null) falseTargetBlock = elseBlock;
        else falseTargetBlock = newBlock;
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
        ArrayList<Register> newArrExpr = new ArrayList<>();
        for (NewArrDemNode arrNode : node.arrList) {
            arrNode.accept(this);
            newArrExpr.add((Register) currentEntity);
        }
        //怎么 new 呀我不会写qwq
    }

    @Override
    public void visit(ArrExprNode node) {
        node.ls.accept(this);
        Register ls = (Register) currentEntity;
        TypeNode newTypeNode = new TypeNode(ls.typeNode.pos, ls.typeNode);
        for (ExprNode exp : node.arrDimension) {
            exp.accept(this);
            IRValue elementNum = currentEntity;
            newTypeNode = new TypeNode(newTypeNode.pos, newTypeNode);
            newTypeNode.layer--;
            Register newReg = new Register(cnt++, newTypeNode);
            GetElementPtr getElementPtr = new GetElementPtr(ls, newReg, elementNum);
            currentBlock.push_back(getElementPtr);
            ls = newReg;
        }
        currentEntity = ls;
    }

    @Override
    public void visit(ForStmtNode node) {
        if (node.initializeStmt != null) node.initializeStmt.accept(this);
        BasicBlock newBlock = new BasicBlock("whileBegin" + currentFunc.label++);
        currentBlock.push_back(new Jump(newBlock));
        currentBlock = newBlock;
        node.stepNode.accept(this);
        node.conditionNode.accept(this);
        LoopScope loopScope = new LoopScope(currentScope);
        loopScope.continueLoop = currentBlock;
        loopScope.breakLoop = new BasicBlock("whileEnd" + currentFunc.label++);
        BasicBlock body = new BasicBlock("whileBody" + currentFunc.label++);
        currentBlock.push_back(new Branch(currentEntity, body, loopScope.breakLoop));
        currentBlock = body;
        currentScope = loopScope;
        if (node.body != null) node.body.accept(this);
        currentScope = currentScope.parentScope;
        currentBlock = loopScope.breakLoop;
    }

    @Override
    public void visit(CtrlStmtNode node) {
        LoopScope loopScope = currentScope.getLoopScope();
        if (node.isBreak) {
            Jump jumpIns = new Jump(loopScope.breakLoop);
            currentBlock.push_back(jumpIns);
        } else if (node.isContinue) {
            Jump jumpIns = new Jump(loopScope.continueLoop);
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
        if (classNode == null) callFunction.target = root.getFunc(node.name);
        else {
            callFunction.target = root.getFunc(classNode.name + '.' + node.name);
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
        BasicBlock newBlock = new BasicBlock();
        Jump jump = new Jump(newBlock);
        currentBlock.push_back(jump);
        currentBlock = newBlock;
        node.condition.accept(this);
        BasicBlock trueBlock = new BasicBlock();
        BasicBlock falseBlock = new BasicBlock();
        Branch branch = new Branch(currentEntity, newBlock, falseBlock);
        currentBlock.push_back(branch);
        LoopScope newLoop = new LoopScope(currentScope);
        newLoop.breakLoop = falseBlock;
        newLoop.continueLoop = currentBlock;
        currentScope = newLoop;
        currentBlock = trueBlock;
        if (node.body != null) node.body.accept(this);
        Jump jumpWhileStart = new Jump(newBlock);
        trueBlock.push_back(jumpWhileStart);
        currentScope = currentScope.parentScope;
        currentBlock = falseBlock;
    }

    @Override
    public void visit(AssignExprNode node) {
        node.lhs.accept(this);
        Register ls = currentScope.getReg(((Register) currentEntity).name);
        node.rhs.accept(this);
        IRValue rs = currentEntity;
        Store storeIns = new Store(ls, rs);
        currentEntity = ls;
        currentBlock.push_back(storeIns);
    }

    @Override
    public void visit(BinaryExprNode node)
    //todo 很有问题啊
    {
        if (node.option.equals("||") || node.option.equals("&&")) {
            if (trueTargetBlock == null) trueTargetBlock = new BasicBlock("end" + currentFunc.label++);
            BasicBlock recordEnd = trueTargetBlock;
            String name = node.option.equals("||") ? "or.rhs" : "and.rhs";
            BasicBlock rhsBlock = new BasicBlock(name + currentFunc.label++);
            trueTargetBlock = rhsBlock;
            node.ls.accept(this);
            trueTargetBlock = recordEnd;
            BasicBlock endBlock = trueTargetBlock;
            endBlock.add_prev(currentBlock);
            rhsBlock.add_prev(currentBlock);
            if (node.option.equals("||")) {
                Branch orBr = new Branch(currentEntity, endBlock, rhsBlock);
                currentBlock.push_back(orBr);
            } else if (node.option.equals("&&")) {
                Branch andBr = new Branch(currentEntity, rhsBlock, endBlock);
                currentBlock.push_back(andBr);
            }
            currentBlock = rhsBlock;
            node.rs.accept(this);
            currentBlock = endBlock;
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
        cnt = 0;
        currentBlock = new BasicBlock();
        if (currentClass == null) currentFunc = new FuncDef(currentBlock, node.name);
        else currentFunc = new FuncDef(currentBlock, currentClass.name + '.' + node.name);
        root.addFunc(currentFunc);
        currentScope = new FuncScope(currentScope, node.returnTypeNode, node.parameterList);
        node.parameterList.accept(this);
        node.funcBody.accept(this);
        currentScope = currentScope.parentScope;

    }

    @Override
    public void visit(ReturnStmtNode node) {
        //todo 存疑
        if (node.returnExpr != null) {
            node.returnExpr.accept(this);
            Register returnReg = new Register(cnt++, currentEntity.typeNode, currentEntity);
        }
        Ret returnVal = new Ret((Register) currentEntity);
        currentBlock.push_back(returnVal);
    }

    @Override
    public void visit(VarDefStmtNode node) {
        for (SingleVarDefNode var : node.varDef) {
            var.accept(this);
        }
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
        cnt = 0;
        currentClass = new ClassDef(node.name);
        root.addClass(currentClass);
        for (String name : node.memberOrder) {
            currentClass.addMember(node.memberDef.get(name).typeNode);
        }//todo 如果有初始值 那么默认构造函数再把它赋值上去
        for (String name : node.functionOrder) {
            node.funcDef.get(name).accept(this);
        }
        currentClass = null;

    }

    @Override
    public void visit(FunctionParameterListNode node) {
        for (SingleVarDefNode var : node.parameterList) {
            var.accept(this);
        }
    }

    @Override
    public void visit(FunctionParameterValueNode node) {
        //empty
    }

    @Override
    public void visit(LambdaExprNode node) {
        //no lambda!
    }

    @Override
    public void visit(TypeNode node) {

    }

}
