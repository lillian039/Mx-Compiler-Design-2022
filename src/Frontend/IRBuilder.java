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
import LLVMIR.Value.*;
import Util.Scope.*;

import java.util.ArrayList;

public class IRBuilder implements ASTVisitor {
    GlobalScope gScope;

    Scope currentScope;
    BasicBlock currentBlock;
    int cnt;
    FuncDef mainFunc, currentFunc;
    ClassDef currentClass;
    BasicBlock targetBlock;

    ClassDefStmtNode classNode = null;

    RootNode root;

    TypeNode intType;

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
        Register ls = (Register) node.lhs.irValue;
        Register newLs = new Register(cnt++, ls.IRType);
        String className = ls.IRType.name;
        VarExprNode var = (VarExprNode) node.rhs;
        ConstInt number = new ConstInt(root.getClass(className).getMap(var.name));
        GetElementPtr getPtr = new GetElementPtr(ls, newLs, number);
        newLs.getElementPtr = getPtr;
        currentBlock.push_back(getPtr);
        node.irValue = newLs;
    }

    @Override
    public void visit(NewArrDemNode node) {
        if (node.arrDimension != null) node.arrDimension.accept(this);
    }

    @Override
    public void visit(SingleVarDefNode node) {
        Register allocReg = new Register(cnt++, gScope.getIRType(node.typeNode.type.name), node.name);
        if (node.expression != null) {
            node.expression.accept(this);
            allocReg.value = node.expression.irValue;
        }
        Allocate allocateInstruction = new Allocate(allocReg, node.typeNode);
        currentScope.addReg(node.name, allocReg);
        currentFunc.addAllocate(allocateInstruction);

    }

    @Override
    public void visit(VarExprNode node) {
        Register newReg = new Register(cnt++, node.irValue.IRType);
        Register var = currentScope.getReg(node.name);
        newReg.value = var;
        Load loadInstruction = new Load(newReg, var);
        currentBlock.push_back(loadInstruction);
        node.irValue = newReg;
    }

    @Override
    public void visit(DelayCellExpr node) {
        node.expression.accept(this);
        Register newLs = new Register(cnt++,node.irValue.IRType);
        String option = node.option.equals("++") ? "+" : "-";
        Register currentEntity = (Register) node.expression.irValue;
        Binary binary = new Binary(currentEntity, new ConstInt(1), newLs, option);
        currentBlock.push_back(binary);
        Register originReg = currentScope.getReg(currentEntity.name);
        Store store = new Store(originReg, newLs);
        currentBlock.push_back(store);
    }

    @Override
    public void visit(CellExprNode node) {
        String op = node.option;
        node.expression.accept(this);
        IRValue currentEntity = node.expression.irValue;
        Register newLs = new Register(cnt++, node.irValue.IRType);
        if (node.option.equals("!")) {

        } else if (node.option.equals("-")) {
            Binary binary = new Binary(new ConstInt(0), currentEntity, newLs, "-");
            currentBlock.push_back(binary);
        } else if (node.option.equals("~")) {
            Binary binary = new Binary(currentEntity, new ConstInt(-1), newLs, "^");
            currentBlock.push_back(binary);
        } else if (node.option.equals("++") || node.option.equals("--")) {
            String option = node.option.equals("++") ? "+" : "-";
            Binary binary = new Binary(currentEntity, new ConstInt(1), newLs, option);
            currentBlock.push_back(binary);
            Register originReg = currentScope.getReg(((Register) currentEntity).name);
            Store store = new Store(originReg, newLs);
            currentBlock.push_back(store);
        }
        currentEntity = newLs;

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
        IRValue ConditionValue = node.condition.irValue;
        BasicBlock thenBlock = new BasicBlock("if then" + currentFunc.label++);
        BasicBlock elseBlock = new BasicBlock("if else" + currentFunc.label++);
        BasicBlock newBlock = new BasicBlock("if end" + currentFunc.label++);
        BasicBlock target = node.elseStmt == null ? elseBlock : newBlock;
        Branch branch = new Branch(ConditionValue, thenBlock, target);
        currentBlock.push_back(branch);
        currentBlock = thenBlock;
        currentFunc.addBlock(currentBlock);
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
            currentFunc.addBlock(elseBlock);
            node.elseStmt.accept(this);
            currentScope = currentScope.parentScope;
            Jump jumpBr = new Jump(newBlock);
            currentBlock.push_back(jumpBr);
        }
        currentBlock = newBlock;
        currentFunc.addBlock(newBlock);
    }

    @Override
    public void visit(NewArrNode node) {
        ArrayList<Register> newArrExpr = new ArrayList<>();
        int layer = 0;
        for (NewArrDemNode arrNode : node.arrList) {
            arrNode.accept(this);
            if (arrNode.arrDimension != null) {
                layer++;
                newArrExpr.add((Register) arrNode.arrDimension.irValue);
            }
        }
        TypeNode newTypeNode = new TypeNode(node.pos, node.type);
        newTypeNode.layer--;
        Register newPtrReg = new Register(cnt++, newTypeNode);
        Malloc malloc = new Malloc(newTypeNode, newPtrReg, newArrExpr.get(0));
        currentBlock.push_back(malloc);
        Register preReg = new Register(cnt++, newTypeNode);
        Load load = new Load(preReg, new ConstInt(1));
        currentBlock.push_back(load);
        for (int i = 1; i < layer; i++) {
            Register dimension = new Register(cnt++, gScope.getIRType("int"));

            newTypeNode = new TypeNode(newTypeNode.pos, newTypeNode);
            newTypeNode.layer--;
            //要捏一个for循环
        }
        node.irValue = newPtrReg;
        //怎么 new 呀我不会写qwq
    }

    @Override
    public void visit(ArrExprNode node) {
        node.ls.accept(this);
        Register ls = (Register) node.ls.irValue;
        TypeNode newTypeNode = new TypeNode(ls.typeNode.pos, ls.typeNode);
        for (ExprNode exp : node.arrDimension) {
            exp.accept(this);
            IRValue elementNum = exp.irValue;
            newTypeNode = new TypeNode(newTypeNode.pos, newTypeNode);
            newTypeNode.layer--;
            Register newReg = new Register(cnt++, newTypeNode);
            GetElementPtr getElementPtr = new GetElementPtr(ls, newReg, elementNum);
            currentBlock.push_back(getElementPtr);
            ls = newReg;
        }
        node.irValue = ls;
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
        currentBlock.push_back(new Branch(node.conditionNode.irValue, body, loopScope.breakLoop));
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
        Call callFunction = new Call();
        ArrayList<ExprNode> value = node.parameterValueNode.parameters;
        if (classNode == null) callFunction.target = root.getFunc(node.name);
        else callFunction.target = root.getFunc(classNode.name + '.' + node.name);//caller in dot function
        for (int i = 0; i < value.size(); i++) {
            value.get(i).accept(this);
            callFunction.addParameter(value.get(i).irValue);
        }
        if (!node.type.type.name.equals("void")) {
            Register newVal = new Register(cnt++, node.irValue.IRType);
            newVal.funcVal = callFunction;
            node.irValue = newVal;
        } else node.irValue = null;
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
            String str = node.value.stringVal;
            newVal = new ConstString(str, gScope.getString(str),gScope.getIRType("string"));
        } else if (node.value.isIntVal) {
            newVal = new ConstInt(node.value.intVal);
        } else newVal = new Null();
        node.irValue = newVal;
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
        Branch branch = new Branch(node.condition.irValue, newBlock, falseBlock);
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
        Register ls = currentScope.getReg(((Register) node.lhs.irValue).name);
        node.rhs.accept(this);
        IRValue rs = node.rhs.irValue;
        Store storeIns = new Store(ls, rs);
        node.irValue = ls;
        currentBlock.push_back(storeIns);
    }

    @Override
    public void visit(BinaryExprNode node)
    //todo 很有问题啊
    {
        if (node.option.equals("||") || node.option.equals("&&")) {
            if (targetBlock == null) targetBlock = new BasicBlock("end" + currentFunc.label++);
            BasicBlock recordEnd = targetBlock;
            String name = node.option.equals("||") ? "or.rhs" : "and.rhs";
            BasicBlock rhsBlock = new BasicBlock(name + currentFunc.label++);
            targetBlock = rhsBlock;
            node.ls.accept(this);
            targetBlock = recordEnd;
            BasicBlock endBlock = targetBlock;
            endBlock.add_prev(currentBlock);
            rhsBlock.add_prev(currentBlock);
            if (node.option.equals("||")) {
                Branch orBr = new Branch(node.ls.irValue, endBlock, rhsBlock);
                currentBlock.push_back(orBr);
            } else if (node.option.equals("&&")) {
                Branch andBr = new Branch(node.ls.irValue, rhsBlock, endBlock);
                currentBlock.push_back(andBr);
            }
            currentBlock = rhsBlock;
            node.rs.accept(this);
            currentBlock = endBlock;
        } else {
            String op = null;
            switch (node.option) {
                case "+" -> op = "add";
                case "-" -> op = "sub nsw";
                case "*" -> op = "mul";
                case "/" -> op = "sdiv";
                case "%" -> op = "srem";
                case "<<" -> op = "shl nsw";
                case ">>" -> op = "ashr";
                case "&" -> op = "and";
                case "^" -> op = "xor";
                case "|" -> op = "or";
                case "<" -> op = "slt";
                case ">" -> op = "sgt";
                case "<=" -> op = "sle";
                case ">=" -> op = "sge";
                case "==" -> op = "eq";
                case "!=" -> op = "ne";
            }
            node.ls.accept(this);
            IRValue lvalue = node.ls.irValue;
            node.rs.accept(this);
            IRValue rvalue = node.rs.irValue;
            Register reg = new Register(cnt++, node.ls.irValue.IRType);
            IRInstruction currentInstruction = new Binary(lvalue, rvalue, reg, op);
            node.irValue = reg;
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
     /*   //todo 存疑
        if (node.returnExpr != null) {
            node.returnExpr.accept(this);
            IRValue currentEntity=node.returnExpr.irValue;
            Register returnReg = new Register(cnt++, currentEntity.typeNode, currentEntity);
        }
        Ret returnVal = new Ret((Register) node.returnExpr.irValue);
        currentBlock.push_back(returnVal);*/
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
        Register ls = (Register) node.lhs.irValue;
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
