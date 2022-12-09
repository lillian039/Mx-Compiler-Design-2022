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
import LLVMIR.GlobalDefine.VarDef;
import LLVMIR.IRInstruction;
import LLVMIR.IRType.*;
import LLVMIR.Terminal.Branch;
import LLVMIR.Terminal.Jump;
import LLVMIR.Value.*;
import Util.Scope.*;

import java.util.ArrayList;

public class IRBuilder implements ASTVisitor {
    GlobalScope gScope;

    Scope currentScope;
    BasicBlock currentBlock;
    int regCnt;
    //   int labelNumber;
    FuncDef mainFunc, currentFunc;
    BasicBlock targetBlock;
    IRBaseType i32Type;
    RootNode root;

    public IRBuilder(GlobalScope gScope) {
        this.gScope = gScope;
        currentBlock = new BasicBlock();
        mainFunc = new FuncDef(currentBlock, "main", i32Type);
        i32Type = gScope.getIRType("int");
    }

    //默认构造函数？
    @Override
    public void visit(NewClassExprNode node) {
        node.irBaseType = gScope.getIRType(node.type.type.name);
        Register register = new Register(regCnt++, node.irBaseType);
        Call call = new Call("_" + node.type.type.name + "." + node.type.type.name, register);
        currentBlock.push_back(call);
    }

    @Override
    public void visit(DotVarExprNode node) {
        node.irBaseType = gScope.getIRType(node.type.type.name);
        node.lhs.accept(this);
        Register ls = (Register) node.lhs.irValue;
        Register newLs = new Register(regCnt++, ls.IRType);
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
        IRBaseType irType = gScope.getIRType(node.typeNode.type.name);
        if (node.typeNode.layer > 0) irType = new PtrType(node.typeNode.layer, irType);
        Register allocReg = new Register(regCnt++, irType, node.name);
        Alloca allocateInstruction = new Alloca(allocReg, irType);
        currentScope.addReg(node.name, allocReg);
        currentFunc.addAllocate(allocateInstruction);
        currentBlock.push_back(allocateInstruction);
        if (node.expression != null) {
            node.expression.accept(this);
            allocReg.value = node.expression.irValue;
            Store store = new Store(node.expression.irValue, allocReg);
            currentBlock.push_back(store);
        }
    }

    @Override
    public void visit(VarExprNode node) {
        Register var = currentScope.getReg(node.name);
        Register newReg = new Register(regCnt++, var.IRType);
        node.irBaseType = var.IRType;
        newReg.value = var;
        Load loadInstruction = new Load(newReg, var);
        currentBlock.push_back(loadInstruction);
        node.irValue = newReg;
    }

    @Override
    public void visit(DelayCellExpr node) {
        node.expression.accept(this);
        Register newLs = new Register(regCnt++, node.irValue.IRType);
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
        node.expression.accept(this);
        IRValue currentEntity = node.expression.irValue;
        Register newLs = new Register(regCnt++, node.irValue.IRType);
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
        node.irValue = newLs;

    }

    @Override
    public void visit(RootNode node) {
        root = node;
        root.addVar(new VarDef(gScope));

    }

    @Override
    public void visit(IfStmtNode node) {
        node.condition.accept(this);
        IRValue ConditionValue = node.condition.irValue;
        BasicBlock thenBlock = new BasicBlock("if then", currentFunc.label++);
        BasicBlock elseBlock = new BasicBlock("if else", currentFunc.label++);
        BasicBlock newBlock = new BasicBlock("if end", currentFunc.label++);
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
        node.irBaseType = gScope.getIRType(node.type.type.name);
        ArrayList<IRValue> newArrExpr = new ArrayList<>();
        newArrExpr.add(new ConstInt(1));
        int layer = 0;
        for (NewArrDemNode arrNode : node.arrList) {
            arrNode.accept(this);
            if (arrNode.arrDimension != null) {
                layer++;
                newArrExpr.add(arrNode.arrDimension.irValue);
            }
        }
        if (layer == 1) {
            Register regArr = new Register(regCnt++, new PtrType(layer, node.irBaseType));
            Malloc malloc = new Malloc(node.irBaseType, regArr, newArrExpr.get(1));
            currentBlock.push_back(malloc);
            node.irValue = regArr;
            return;
        }

        //%1 = call ptr @malloc(i32 of layer+1)
        node.irBaseType = gScope.getIRType(node.type.type.name);
        Register newDemReg = new Register(regCnt++, new PtrType(1, i32Type));
        Malloc malloc = new Malloc(node.irBaseType, newDemReg, new ConstInt((layer + 1) * 32));//手动加第一层为1
        currentBlock.push_back(malloc);
        for (int i = 0; i <= layer; i++) {
            Register loadPtr = new Register(regCnt++, newDemReg.IRType);
            Load load = new Load(loadPtr, newDemReg);
            currentBlock.push_back(load);
            Register getEle = new Register(regCnt++, i32Type);
            GetElementPtr getElementPtr = new GetElementPtr(getEle, loadPtr, new ConstInt(i));
            currentBlock.push_back(getElementPtr);
            Store store = new Store(newArrExpr.get(i), getEle);
            currentBlock.push_back(store);
        }
        //拥有了层数
        //ptr arr tmp
        //i32 sum=1
        Register regArr = new Register(regCnt++, new PtrType(layer, node.irBaseType));
        Alloca allocaarr = new Alloca(regArr, regArr.IRType);
        currentBlock.push_back(allocaarr);
        Register regTmp = new Register(regCnt++, new PtrType(layer - 1, node.irBaseType));
        Alloca allocatmp = new Alloca(regTmp, regTmp.IRType);
        currentBlock.push_back(allocatmp);
        Register regSum = new Register(regCnt++, i32Type);
        Alloca allocaSum = new Alloca(regSum, i32Type);
        currentBlock.push_back(allocaSum);
        Store storeSum = new Store(new ConstInt(1), regSum);
        currentBlock.push_back(storeSum);

        //开始下一层for循环
        //先 alloca i和j
        Register regi = new Register(regCnt++, new IntType(32, "int"));
        Alloca allocai = new Alloca(regi, regi.IRType);
        currentFunc.addAllocate(allocai);
        Register regj = new Register(regCnt++, new IntType(32, "int"));
        Alloca allocaj = new Alloca(regj, regj.IRType);
        currentFunc.addAllocate(allocaj);
        Store storei = new Store(new ConstInt(0), regi);
        currentBlock.push_back(storei);
        BasicBlock newBlock = new BasicBlock("forLoop", currentFunc.label++);
        Jump jump = new Jump(newBlock);
        currentBlock.push_back(jump);
        currentBlock = newBlock;
        currentFunc.addBlock(newBlock);

        //i < layer
        Register cmpi = new Register(regCnt++, i32Type);
        Icmp icmpi = new Icmp(regi, new ConstInt(layer), cmpi, "slt");
        currentBlock.push_back(icmpi);
        BasicBlock trueBlockI = new BasicBlock("forTrue", currentFunc.label++);
        BasicBlock falseBlockI = new BasicBlock("forFalse", currentFunc.label++);
        Branch branchi = new Branch(cmpi, trueBlockI, falseBlockI);
        currentBlock.push_back(branchi);
        currentBlock = trueBlockI;

        //sum=sum*p[i+1];
        Register regMul = new Register(regCnt++, i32Type);
        Register regStep = new Register(regCnt++, i32Type);
        Register addIPlusOne = new Register(regCnt++, i32Type);
        Binary binaryIPlusOne = new Binary(new ConstInt(1), regi, addIPlusOne, "add nsw");
        GetElementPtr getElementPtr = new GetElementPtr(regStep, newDemReg, addIPlusOne);
        currentBlock.push_back(binaryIPlusOne);
        currentBlock.push_back(getElementPtr);
        Binary binaryMul = new Binary(regSum, regStep, regMul, "mul");
        currentBlock.push_back(binaryMul);
        Store storeMul = new Store(regMul, regSum);
        currentBlock.push_back(storeMul);

        //tmp=(T*)malloc(sum*sizeof(T));
        Malloc mallocT = new Malloc(regTmp.IRType, regTmp, regSum);
        currentBlock.push_back(mallocT);
        Store storej = new Store(new ConstInt(0), regj);
        currentBlock.push_back(storej);
        newBlock = new BasicBlock("forTrue", currentFunc.label++);
        jump = new Jump(newBlock);
        currentBlock.push_back(jump);

        //j<p[i]
        Register Pi = new Register(regCnt++, i32Type);
        getElementPtr = new GetElementPtr(Pi, newDemReg, regi);
        currentBlock.push_back(getElementPtr);
        Register cmpj = new Register(regCnt++, i32Type);
        Icmp icmpj = new Icmp(regj, Pi, cmpj, "slt");
        currentBlock.push_back(icmpj);
        BasicBlock trueBlockJ = new BasicBlock("forTrue", currentFunc.label++);
        Branch branchj = new Branch(cmpj, trueBlockJ, falseBlockI);
        currentBlock.push_back(branchj);
        currentBlock = trueBlockJ;


        //怎么 new 呀我不会写qwq
    }

    @Override
    public void visit(ArrExprNode node) {
        node.ls.accept(this);
        Register ls = (Register) node.ls.irValue;
        IRBaseType irBaseType = ls.IRType;
        for (ExprNode exp : node.arrDimension) {
            exp.accept(this);
            IRValue elementNum = exp.irValue;
            if (((PtrType) irBaseType).dimension > 1)
                irBaseType = new PtrType(((PtrType) irBaseType).dimension - 1, ((PtrType) irBaseType).type);
            else irBaseType = ((PtrType) irBaseType).type;
            Register newReg = new Register(regCnt++, irBaseType);
            GetElementPtr getElementPtr = new GetElementPtr(newReg, ls, elementNum);
            currentBlock.push_back(getElementPtr);
            ls = newReg;
        }
        node.irValue = ls;
    }

    @Override
    public void visit(ForStmtNode node) {
        if (node.initializeStmt != null) node.initializeStmt.accept(this);
        BasicBlock newBlock = new BasicBlock("whileBegin", currentFunc.label++);
        currentBlock.push_back(new Jump(newBlock));
        currentBlock = newBlock;
        node.stepNode.accept(this);
        node.conditionNode.accept(this);
        LoopScope loopScope = new LoopScope(currentScope);
        loopScope.continueLoop = currentBlock;
        loopScope.breakLoop = new BasicBlock("whileEnd", currentFunc.label++);
        BasicBlock body = new BasicBlock("whileBody", currentFunc.label++);
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
        node.irBaseType = gScope.getIRType(node.type.type.name);
        Call callFunction;
        Register caller;
        if (node.irBaseType instanceof VoidType) callFunction = new Call(node.name);
        else {
            caller = new Register(regCnt++, node.irBaseType);
            callFunction = new Call(node.name, caller);
        }
        ArrayList<ExprNode> value = node.parameterValueNode.parameters;
        for (int i = 0; i < value.size(); i++) {
            value.get(i).accept(this);
            callFunction.addParameter(value.get(i).irValue);
        }
        if (!node.type.type.name.equals("void")) {
            Register newVal = new Register(regCnt++, node.irValue.IRType);
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
            newVal = new ConstString(str, gScope.getString(str));
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
        Store storeIns = new Store(rs, ls);
        node.irValue = ls;
        currentBlock.push_back(storeIns);
    }

    @Override
    public void visit(BinaryExprNode node)
    //todo 很有问题啊
    {
        //把phi的值存在栈上 就可以不用phi指令了
        if (node.option.equals("||") || node.option.equals("&&")) {
            if (targetBlock == null) targetBlock = new BasicBlock("end", currentFunc.label++);
            BasicBlock recordEnd = targetBlock;
            String name = node.option.equals("||") ? "or.rhs" : "and.rhs";
            BasicBlock rhsBlock = new BasicBlock(name, currentFunc.label++);
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
            node.ls.accept(this);
            IRValue lvalue = node.ls.irValue;
            node.rs.accept(this);
            IRValue rvalue = node.rs.irValue;
            Register reg = new Register(regCnt++, node.ls.irValue.IRType);
            if (reg.IRType.isSameType(gScope.getIRType("bool"))) {
                String op = null;
                switch (node.option) {
                    case "<" -> op = "slt";
                    case ">" -> op = "sgt";
                    case "<=" -> op = "sle";
                    case ">=" -> op = "sge";
                    case "==" -> op = "eq";
                    case "!=" -> op = "ne";
                }
                IRInstruction currentInstruction = new Icmp(lvalue, rvalue, reg, op);
                node.irValue = reg;
                currentBlock.push_back(currentInstruction);
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
                }
                IRInstruction currentInstruction = new Binary(lvalue, rvalue, reg, op);
                node.irValue = reg;
                currentBlock.push_back(currentInstruction);
            }
        }
    }

    @Override
    public void visit(FunDefStmtNode node) {
        regCnt = 0;
        currentBlock = new BasicBlock();
        IRBaseType irBaseType = toIRType(node.returnTypeNode);
        currentFunc = new FuncDef(currentBlock, node.name, irBaseType);
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
            Register returnReg = new Register(regCnt++, currentEntity.typeNode, currentEntity);
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
        node.rhs.name = "_" + node.lhs.irBaseType.name + "." + node.rhs.name;
        node.rhs.accept(this);
    }

    @Override
    public void visit(ClassDefStmtNode node) {
        regCnt = 0;
        ClassType type = (ClassType) gScope.getIRType(node.name);
        ClassDef currentClass = new ClassDef(node.name,type);
        for (int i = 0; i < node.memberOrder.size(); i++) {
            currentClass.memberMap.put(node.memberOrder.get(i), i);
        }
        for (String name : node.functionOrder) {
            FunDefStmtNode FuncNode=node.funcDef.get(name);
            FuncNode.name="_"+type.name+"."+FuncNode.name;
            FuncNode.accept(this);
        }
        root.classDefs.add(currentClass);

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

    IRBaseType toIRType(TypeNode node) {
        IRBaseType base = gScope.getIRType(node.type.name);
        IRBaseType newIRBaseType;
        if (node.layer > 0) newIRBaseType = new PtrType(node.layer, base);
        else newIRBaseType = base;
        return newIRBaseType;
    }
}
