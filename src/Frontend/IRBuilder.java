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
import LLVMIR.Terminal.Ret;
import LLVMIR.Value.*;
import Util.Scope.*;

import java.util.ArrayList;
import java.util.HashMap;

//new
//this 指针
public class IRBuilder implements ASTVisitor {
    GlobalScope gScope;

    Scope currentScope;
    BasicBlock currentBlock;
    int regCnt;
    //   int labelNumber;
    FuncDef currentFunc;
    IRBaseType i32Type;
    RootNode root;

    HashMap<String, Integer> renameTable = new HashMap<>();

    public IRBuilder(GlobalScope gScope) {
        this.gScope = gScope;
        this.gScope.IRInitial();
        this.gScope.FuncInitial();
        i32Type = gScope.getIRType("int");
    }

    @Override
    public void visit(RootNode node) {
        root = node;
        root.addVar(new VarDef(gScope));
        currentScope = gScope;
        for (var classDef : root.classDef) {
            classDef.accept(this);
        }

        //放入main函数
        BasicBlock basicBlock = new BasicBlock("entry", 0);
        FuncDef mainFuncDef = new FuncDef(basicBlock, "main", i32Type);
        gScope.globalFunc.put("main", mainFuncDef);

        //用来初始化全局变量
        FuncDef initializeVar = root.varDefs.initializeVar;
        gScope.globalFunc.put(initializeVar.name, initializeVar);
        root.funcDefs.add(initializeVar);
        ArrayList<SingleVarDefNode> varDef = root.varDefs.varDef;
        currentBlock = initializeVar.Entry;
        for (var vars : varDef) {
            if (vars.expression != null) {
                AssignExprNode assignExprNode = new AssignExprNode(new VarExprNode(vars.name, vars.pos), vars.expression, vars.pos);
                assignExprNode.accept(this);
            }
        }
        currentBlock.tailStmt = new Ret(new Register(new VoidType(), "ret"));
        Call callInitializeVar = new Call(initializeVar.name, initializeVar.irReturnType);

        //main函数第一句话调用初始化构造全局变量的函数
        mainFuncDef.Entry.push_back(callInitializeVar);

        //构造存短路求值的寄存器phi
        Register phi = new Register(new PtrType(gScope.getIRType("bool")), ".phi");
        mainFuncDef.Entry.push_back(new Alloca(phi));
        gScope.addReg(".phi", phi);

        //内建函数
        root.InnerFunc = gScope.externalFunc;
        for (int i = 0; i < root.InnerFunc.size(); i++) {
            root.InnerFunc.get(i).originFunc.accept(this);
        }

        //str相关自定义内建函数
        addStrGlobalInner();

        //将IR版本的内建函数放进GScope
        for (int i = 0; i < root.InnerFunc.size(); i++) {
            FuncDef cur = root.InnerFunc.get(i);
            if (gScope.getFunc(cur.name) != null) continue;
            gScope.addIRFunc(cur);
        }

        //跑每个function
        for (var funcDef : node.funcDef) {
            funcDef.accept(this);
        }


    }

    void addStrGlobalInner() {
        ClassType stringType = (ClassType) gScope.getIRType("string");
        IntType boolType = (IntType) gScope.getIRType("bool");
        Register regS1 = new Register(stringType, "s1");
        Register regS2 = new Register(stringType, "s2");
        ArrayList<FuncDef> strFunc = new ArrayList<>();

        FuncDef str_eq = new FuncDef("__str_eq", boolType, true);
        strFunc.add(str_eq);
        FuncDef str_ne = new FuncDef("__str_ne", boolType, true);
        strFunc.add(str_ne);
        FuncDef str_slt = new FuncDef("__str_slt", boolType, true);
        strFunc.add(str_slt);
        FuncDef str_sle = new FuncDef("__str_sle", boolType, true);
        strFunc.add(str_sle);
        FuncDef str_sgt = new FuncDef("__str_sgt", boolType, true);
        strFunc.add(str_sgt);
        FuncDef str_sge = new FuncDef("__str_sge", boolType, true);
        strFunc.add(str_sge);

        FuncDef str_add = new FuncDef("__str_add", stringType, true);
        strFunc.add(str_add);

        for (int i = 0; i < strFunc.size(); i++) {
            FuncDef cur = strFunc.get(i);
            cur.parameterList.add(regS1);
            cur.parameterList.add(regS2);
            root.InnerFunc.add(cur);
        }
    }

    //默认构造函数？
    @Override
    public void visit(NewClassExprNode node) {
        node.irBaseType = gScope.getIRType(node.type.type.name);
        Register register = new Register(regCnt++, node.irBaseType);
        Malloc malloc = new Malloc(register, new ConstInt(node.irBaseType.size()));
        currentBlock.push_back(malloc);
        Call call = new Call("_" + node.type.type.name + "." + node.type.type.name, register, node.irBaseType);
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
        currentBlock.push_back(getPtr);
        if (inCircuit && node.irBaseType.isSameType(gScope.getIRType("bool"))) {
            Store store = new Store(newLs, gScope.getReg(".phi"));
            currentBlock.push_back(store);
        }
        node.irValue = newLs;
    }

    @Override
    public void visit(NewArrDemNode node) {
        if (node.arrDimension != null) node.arrDimension.accept(this);
    }

    @Override
    public void visit(SingleVarDefNode node) {
        IRBaseType irType = toIRType(node.typeNode);
        irType = new PtrType(irType);
        Register allocReg = new Register(irType, node.name);
        if (renameTable.containsKey(node.name)) {
            int num = renameTable.get(node.name);
            allocReg.rename = "_" + node.name + num;
            renameTable.remove(node.name);
            renameTable.put(node.name, num + 1);
        } else renameTable.put(node.name, 1);
        Alloca allocateInstruction = new Alloca(allocReg);
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
        node.irBaseType = var.IRType;
        node.irValue = var;
        if (inCircuit && node.irBaseType.isSameType(gScope.getIRType("bool"))) {
            Store store = new Store(var, gScope.getReg(".phi"));
            currentBlock.push_back(store);
        }
    }

    @Override
    public void visit(DelayCellExpr node) {
        node.expression.accept(this);
        Register newLs = new Register(regCnt++, node.expression.irBaseType);
        String option = node.option.equals("++") ? "add" : "sub nsw";
        Register currentEntity = (Register) node.expression.irValue;
        Binary binary = new Binary(currentEntity, new ConstInt(1), newLs, option);
        currentBlock.push_back(binary);
        Register originReg = currentScope.getReg(currentEntity.name);
        Store store = new Store(originReg, newLs);
        currentBlock.push_back(store);
        node.irValue = currentEntity;
        node.irBaseType = node.irValue.IRType;
    }

    @Override
    public void visit(CellExprNode node) {
        node.expression.accept(this);
        IRValue currentEntity = node.expression.irValue;
        Register newLs = new Register(regCnt++, node.irValue.IRType);
        if (node.option.equals("!")) {
            Trunc trunc = new Trunc(currentEntity.IRType, new IntType(1, "bool"), newLs, currentEntity);
            currentBlock.push_back(trunc);
            Register reg2 = new Register(regCnt++, new IntType(1, "bool"));
            Binary binary = new Binary(newLs, new ConstBool(true), reg2, "xor");
            currentBlock.push_back(binary);
            Register reg3 = new Register(regCnt++, new IntType(8, "bool"));
            Zext zext = new Zext(new IntType(1, "bool"), currentEntity.IRType, reg2, reg3);
            currentBlock.push_back(zext);
            node.irValue = reg3;
            if (inCircuit) {
                Store store = new Store(reg3, gScope.getReg(".phi"));
                currentBlock.push_back(store);
            }
            return;
        } else if (node.option.equals("-")) {
            Binary binary = new Binary(new ConstInt(0), currentEntity, newLs, "sub nsw");
            currentBlock.push_back(binary);
        } else if (node.option.equals("~")) {
            Binary binary = new Binary(currentEntity, new ConstInt(-1), newLs, "xor");
            currentBlock.push_back(binary);
        } else if (node.option.equals("++") || node.option.equals("--")) {
            String option = node.option.equals("++") ? "add" : "sub nsw";
            Binary binary = new Binary(currentEntity, new ConstInt(1), newLs, option);
            currentBlock.push_back(binary);
            Register originReg = currentScope.getReg(((Register) currentEntity).name);
            Store store = new Store(originReg, newLs);
            currentBlock.push_back(store);
        }
        node.irValue = newLs;

    }

    @Override
    public void visit(IfStmtNode node) {
        node.condition.accept(this);
        IRValue ConditionValue = node.condition.irValue;
        int label = currentFunc.label++;
        BasicBlock thenBlock = new BasicBlock("if.then" + label, label);
        BasicBlock newBlock = new BasicBlock("if.end" + label, label);
        BasicBlock elseBlock = new BasicBlock("if.else" + label, label);
        BasicBlock target = node.elseStmt == null ? newBlock : elseBlock;
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

    Register newArrCreate(ArrayList<IRValue> dimensions, int layer, IRBaseType ptrType) {
        IRValue dimen = dimensions.get(layer);
        Register dimenLen = new Register(regCnt++, i32Type);
        Binary mulDimen = new Binary(dimen, new ConstInt(32), dimenLen, "mul");
        currentBlock.push_back(mulDimen);
        Register length = new Register(regCnt++, i32Type);
        Binary addSize = new Binary(dimenLen, new ConstInt(32), length, "add");
        currentBlock.push_back(addSize);
        //mallocNew 最终要返回的ptr
        Register mallocNew = new Register(regCnt++, new PtrType(ptrType));
        Malloc mallocArr = new Malloc(mallocNew, length);
        currentBlock.push_back(mallocArr);
        //将size存进第一位
        Register sizeReg = new Register(regCnt++, i32Type);
        GetElementPtr getSizePtr = new GetElementPtr(sizeReg, mallocNew, new ConstInt(0));
        currentBlock.push_back(getSizePtr);
        //最后一层 直接返回
        if (layer == dimensions.size() - 1) return mallocNew;
        //捏一个for循环
        int label = currentFunc.label++;
        BasicBlock conditionBlock = new BasicBlock("array.for.begin" + label, label);
        BasicBlock bodyBlock = new BasicBlock("array.for.body" + label, label);
        BasicBlock endBlock = new BasicBlock("array.for.end" + label, label);
        Register iReg = new Register(i32Type, "_new_i." + layer + "." + label);
        Store storeiReg = new Store(new ConstInt(1), iReg);
        currentBlock.push_back(storeiReg);
        currentBlock.push_back(new Jump(conditionBlock));
        currentBlock = conditionBlock;
        currentFunc.addBlock(conditionBlock);
        Register cmpI = new Register(regCnt++, i32Type);
        Icmp icmpI = new Icmp(iReg, length, cmpI, "slt"); // i < length
        conditionBlock.push_back(icmpI);
        Branch branchBody = new Branch(cmpI, bodyBlock, endBlock);
        currentBlock.push_back(branchBody);
        currentBlock = bodyBlock;
        currentFunc.addBlock(bodyBlock);
        //body
        PtrType ptr = (PtrType) ptrType;
        IRBaseType nextLayerType = ptr.type;
//        if (ptr.dimension == 1) {
//            nextLayerType = ptr.type;
//        } else {
//            nextLayerType = new PtrType(ptr.dimension - 1, ptr.type);
//        }
        Register lowerReg = new Register(regCnt++, nextLayerType);
        GetElementPtr getElementPtr = new GetElementPtr(lowerReg, mallocNew, iReg);
        currentBlock.push_back(getElementPtr);
        Register newLayerReg = newArrCreate(dimensions, layer + 1, nextLayerType);
        Store store = new Store(newLayerReg, lowerReg);
        currentBlock.push_back(store);

        //i=i+1
        Register tmpI = new Register(regCnt++, i32Type);
        Load load = new Load(tmpI, iReg);
        currentBlock.push_back(load);
        Binary binaryAdd = new Binary(iReg, new ConstInt(1), tmpI, "add");
        currentBlock.push_back(binaryAdd);

        //go to condition
        Jump jumpOut = new Jump(conditionBlock);
        currentBlock.push_back(jumpOut);
        currentBlock = endBlock;
        currentFunc.addBlock(endBlock);
        return mallocNew;
    }

    @Override
    public void visit(NewArrNode node) {
        PtrType ptr = (PtrType) toIRType(node.type);
        node.irBaseType = ptr;
        ArrayList<IRValue> newArrExpr = new ArrayList<>();
        newArrExpr.add(new ConstInt(1));
        for (NewArrDemNode arrNode : node.arrList) {
            arrNode.accept(this);
            if (arrNode.arrDimension != null) {
                newArrExpr.add(arrNode.arrDimension.irValue);
            }
        }
        Register regArr = new Register(regCnt++, ptr);
        Store store = new Store(newArrCreate(newArrExpr, 0, ptr), regArr);
        currentBlock.push_back(store);
        node.irValue = regArr;


    }

    @Override
    public void visit(ArrExprNode node) {
        node.ls.accept(this);
        Register ls = (Register) node.ls.irValue;
        IRBaseType irBaseType = ls.IRType;
        for (ExprNode exp : node.arrDimension) {
            exp.accept(this);
            IRValue elementNum = exp.irValue;
            irBaseType = ((PtrType) irBaseType).type;
           /* if (((PtrType) irBaseType).dimension > 1)
                irBaseType = new PtrType(((PtrType) irBaseType).dimension - 1, ((PtrType) irBaseType).type);
            else irBaseType = ((PtrType) irBaseType).type;*/
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
        int label = currentFunc.label++;
        BasicBlock newBlock = new BasicBlock("for.begin" + label, label);
        currentFunc.addBlock(newBlock);
        currentBlock.push_back(new Jump(newBlock));
        currentBlock = newBlock;
        node.stepNode.accept(this);
        node.conditionNode.accept(this);
        LoopScope loopScope = new LoopScope(currentScope);
        loopScope.continueLoop = currentBlock;
        loopScope.breakLoop = new BasicBlock("for.end" + label, label);
        BasicBlock body = new BasicBlock("for.body" + label, label);
        currentFunc.addBlock(body);
        currentBlock.push_back(new Branch(node.conditionNode.irValue, body, loopScope.breakLoop));
        currentBlock = body;
        currentScope = loopScope;
        if (node.body != null) node.body.accept(this);
        Jump jumpBegin = new Jump(newBlock);
        currentBlock.push_back(jumpBegin);
        currentScope = currentScope.parentScope;
        currentBlock = loopScope.breakLoop;
        currentFunc.addBlock(loopScope.breakLoop);
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
        node.irBaseType = toIRType(node.type);
        Call callFunction;
        Register caller;
        if (node.irBaseType instanceof VoidType) {
            callFunction = new Call(node.name, node.irBaseType);
            caller = new Register(node.irBaseType, "tmpstore");
        } else {
            caller = new Register(regCnt++, node.irBaseType);
            callFunction = new Call(node.name, caller, node.irBaseType);
        }
        if (node.parameterValueNode != null) {
            ArrayList<ExprNode> value = node.parameterValueNode.parameters;
            for (int i = 0; i < value.size(); i++) {
                value.get(i).accept(this);
                callFunction.addParameter(value.get(i).irValue);
            }
        }
        currentBlock.push_back(callFunction);
        caller.callFunc = callFunction;
        node.irValue = caller;
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
            if (inCircuit) {
                Store store = new Store(newVal, gScope.getReg(".phi"));
                currentBlock.push_back(store);
            }
        } else if (node.value.isStringVal) {
            String str = node.value.stringVal;
            int num = gScope.getString(str);
            String name;
            if (num == 0) name = ".str";
            else name = ".str." + num;
            newVal = gScope.getReg(name);
        } else if (node.value.isIntVal) {
            newVal = new ConstInt(node.value.intVal);
        } else newVal = new Null();
        node.irValue = newVal;
    }

    @Override
    public void visit(WhileStmtNode node) {
        int label = currentFunc.label++;
        BasicBlock newBlock = new BasicBlock("while.begin" + label, label);
        Jump jump = new Jump(newBlock);
        currentBlock.push_back(jump);
        currentBlock = newBlock;
        currentFunc.addBlock(currentBlock);
        node.condition.accept(this);
        BasicBlock trueBlock = new BasicBlock("while.body" + label, label);
        BasicBlock falseBlock = new BasicBlock("while.end" + label, label);
        Branch branch = new Branch(node.condition.irValue, trueBlock, falseBlock);
        currentBlock.push_back(branch);
        LoopScope newLoop = new LoopScope(currentScope);
        newLoop.breakLoop = falseBlock;
        newLoop.continueLoop = currentBlock;
        currentScope = newLoop;
        currentBlock = trueBlock;
        currentFunc.addBlock(currentBlock);
        if (node.body != null) node.body.accept(this);
        Jump jumpWhileStart = new Jump(newBlock);
        trueBlock.push_back(jumpWhileStart);
        currentScope = currentScope.parentScope;
        currentBlock = falseBlock;
        currentFunc.addBlock(currentBlock);
    }


    @Override
    public void visit(AssignExprNode node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
        IRValue rs = node.rhs.irValue;
        Register ls = (Register) node.lhs.irValue;
        Store storeIns = new Store(rs, ls);
        node.irValue = ls;
        currentBlock.push_back(storeIns);
    }

    boolean inCircuit;

    void BinaryString(BinaryExprNode node) {
        node.ls.accept(this);
        node.rs.accept(this);
        Call call = null;
        if (node.option.equals("+")) {
            Register register = new Register(regCnt++, gScope.getIRType("string"));
            call = new Call("__str_sge", register, gScope.getIRType("string"));
            call.addParameter(node.ls.irValue);
            call.addParameter(node.rs.irValue);
            currentBlock.push_back(call);
            node.irValue = register;
            return;
        }
        IRBaseType boolType = gScope.getIRType("bool");
        Register ls = new Register(regCnt++, boolType);
        if (node.option.equals("==")) {//比较两个字符串内容是否完全一致（不是内存地址）
            call = new Call("__str_eq", ls, boolType);
        } else if (node.option.equals("!=")) {
            call = new Call("__str_ne", ls, boolType);
        } else if (node.option.equals("<")) {
            call = new Call("__str_slt", ls, boolType);
        } else if (node.option.equals(">")) {
            call = new Call("__str_sgt", ls, boolType);
        } else if (node.option.equals(">=")) {
            call = new Call("__str_sge", ls, boolType);
        } else if (node.option.equals("<=")) {
            call = new Call("__str_sle", ls, boolType);
        }
        if (call == null) System.out.println("call null");
        else {
            call.addParameter(node.ls.irValue);
            call.addParameter(node.rs.irValue);
            currentBlock.push_back(call);
            node.irValue = ls;
        }
    }

    @Override
    public void visit(BinaryExprNode node) {
        if (node.ls.type.type.name.equals("string")) {
            BinaryString(node);
            return;
        }
        //把phi的值存在栈上 就可以不用phi指令了
        if (node.option.equals("||") || node.option.equals("&&")) {
            inCircuit = true;
            node.ls.accept(this);
            String name = node.option.equals("||") ? "or" : "and";
            int label = currentFunc.label++;
            BasicBlock befBlock = currentBlock;
            BasicBlock rhsBlock = new BasicBlock(name + ".rhs" + label, label);
            BasicBlock endBlock = new BasicBlock(name + ".end" + label, label);
            currentFunc.addBlock(rhsBlock);
            currentBlock = rhsBlock;
            node.rs.accept(this);
            currentBlock.push_back(new Jump(endBlock));
            if (node.option.equals("||")) {
                Branch orBr = new Branch(node.ls.irValue, endBlock, rhsBlock);
                befBlock.push_back(orBr);
            } else if (node.option.equals("&&")) {
                Branch andBr = new Branch(node.ls.irValue, rhsBlock, endBlock);
                befBlock.push_back(andBr);
            }
            currentFunc.addBlock(endBlock);
            currentBlock = endBlock;
            node.irValue = gScope.getReg(".phi");
            inCircuit = false;
        } else {
            node.ls.accept(this);
            IRValue lvalue = node.ls.irValue;
            if (node.ls instanceof VarExprNode) {
                Register lsReg = new Register(regCnt++, lvalue.IRType);
                Load lsLoad = new Load(lsReg, lvalue);
                currentBlock.push_back(lsLoad);
                lvalue = lsReg;
            }
            node.rs.accept(this);
            IRValue rvalue = node.rs.irValue;
            if (node.rs instanceof VarExprNode) {
                Register rsReg = new Register(regCnt++, lvalue.IRType);
                Load rsLoad = new Load(rsReg, rvalue);
                currentBlock.push_back(rsLoad);
                rvalue = rsReg;
            }
            Register reg = new Register(regCnt++, node.ls.irValue.IRType);
            if (node.option.equals("<") || node.option.equals(">") || node.option.equals("<=") || node.option.equals(">=")
                    || node.option.equals("==") || node.option.equals("!=")) {
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
                node.irValue.IRType = gScope.getIRType("bool");
                node.irBaseType = gScope.getIRType("bool");
                currentBlock.push_back(currentInstruction);
                if (inCircuit) {
                    Store storePhi = new Store(reg, gScope.getReg(".phi"));
                    currentBlock.push_back(storePhi);
                }
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
        currentFunc = gScope.getGlobalFunc(node.name);
        currentBlock = currentFunc.Entry;
        currentScope = new FuncScope(currentScope, node.returnTypeNode, node.parameterList);
        currentFunc.returnBlock = new BasicBlock("return", 0);
        Register retReg = new Register(new PtrType(currentFunc.irReturnType), "retval");
        if (!(currentFunc.irReturnType instanceof VoidType)) {
            Alloca allocaRet = new Alloca(retReg);
            currentBlock.push_back(allocaRet);
            currentFunc.retval = retReg;
        }
        Ret ret = new Ret(retReg);
        currentFunc.returnBlock.push_back(ret);
        if (!currentFunc.isInner) root.addFunc(currentFunc);

        //引用传参 指针？
        if (node.parameterList != null) {
            ArrayList<SingleVarDefNode> para = node.parameterList.parameterList;
            for (var vars : para) {
                IRBaseType varType = new PtrType(toIRType(vars.typeNode));
                Register register = new Register(varType, vars.name);
                currentFunc.parameterList.add(register);

                //addr
                Register allocParaReg = new Register(varType, vars.name + ".addr");
                Alloca allocPara = new Alloca(allocParaReg);
                currentBlock.push_back(allocPara);
                currentScope.addReg(vars.name, allocParaReg);
            }
        }
        if (currentFunc.classType != null) {
            PtrType thisType = new PtrType(currentFunc.classType);
            Register thisReg = new Register(thisType, "this");
            currentFunc.parameterList.add(thisReg);
            currentScope.addReg("this", thisReg);
        }
        if (node.funcBody != null) node.funcBody.accept(this);
        if (currentBlock.tailStmt == null) currentBlock.tailStmt = new Jump(currentFunc.returnBlock);
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(ReturnStmtNode node) {
        if (node.returnExpr != null) {
            node.returnExpr.accept(this);
            IRValue currentEntity = node.returnExpr.irValue;
            Store store = new Store(currentEntity, currentFunc.retval);
            currentBlock.push_back(store);
        }
        Jump jump = new Jump(currentFunc.returnBlock);
        currentBlock.push_back(jump);
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
        node.rhs.name = "_" + node.lhs.irBaseType.name + "." + node.rhs.name;
        node.rhs.accept(this);
        Call callfunc = ((Register) node.rhs.irValue).callFunc;
        callfunc.addParameter(node.lhs.irValue);
        node.irValue = node.rhs.irValue;
    }

    @Override
    public void visit(ClassDefStmtNode node) {
        regCnt = 0;
        ClassType type = (ClassType) gScope.getIRType(node.name);
        ClassDef currentClass = new ClassDef(node.name, type);
        for (int i = 0; i < node.memberOrder.size(); i++) {
            currentClass.memberMap.put(node.memberOrder.get(i), i);
        }
        for (String name : node.functionOrder) {
            FunDefStmtNode FuncNode = node.funcDef.get(name);
            FuncNode.name = "_" + type.name + "." + FuncNode.name;
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
        IRBaseType newIRBaseType = base;
        int layer = node.layer;
        while (layer > 0) {
            layer--;
            newIRBaseType = new PtrType(newIRBaseType);
        }
        //if (node.layer > 0) newIRBaseType = new PtrType(node.layer, base);
        //else newIRBaseType = base;
        return newIRBaseType;
    }
}
