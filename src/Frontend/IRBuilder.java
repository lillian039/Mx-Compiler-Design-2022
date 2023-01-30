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
import LLVMIR.RootIR;
import LLVMIR.Terminal.Branch;
import LLVMIR.Terminal.Jump;
import LLVMIR.Terminal.Ret;
import LLVMIR.Value.*;
import Util.Scope.*;
import Util.Type;

import java.util.ArrayList;
import java.util.HashMap;

//new
//this 指针
public class IRBuilder implements ASTVisitor {
    GlobalScope gScope;

    Scope currentScope;
    BasicBlock currentBlock;
    int regCnt;
    FuncDef currentFunc;
    IRBaseType i32Type;
    RootNode root;

    RootIR rootIR;

    HashMap<String, Integer> renameTable = new HashMap<>();

    int inCircuit = 0;

    public IRBuilder(GlobalScope gScope, RootIR rootIR) {
        this.gScope = gScope;
        this.gScope.IRInitial();
        this.gScope.FuncInitial();
        this.rootIR = rootIR;
        i32Type = gScope.getIRType("int");
    }

    @Override
    public void visit(RootNode node) {
        root = node;
        rootIR.addVar(new VarDef(gScope));
        currentScope = gScope;

        for (var classDef : root.classDef) {
            classDef.accept(this);
        }

        //放入main函数
        BasicBlock basicBlock = new BasicBlock("entry", 0);
        FuncDef mainFuncDef = new FuncDef(basicBlock, "main", i32Type);
        gScope.globalFunc.put("main", mainFuncDef);

        //用来初始化全局变量
        FuncDef initializeVar = rootIR.varDefs.initializeVar;
        gScope.globalFunc.put(initializeVar.name, initializeVar);
        rootIR.funcDefs.add(initializeVar);
        ArrayList<SingleVarDefNode> varDef = rootIR.varDefs.varDef;
        currentBlock = initializeVar.Entry;
        currentFunc = initializeVar;
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

        //内建函数
        rootIR.InnerFunc = gScope.externalFunc;

        //malloc 添加
        addMallocGlobalInner();

        //str相关自定义内建函数
        addStrGlobalInner();

        //将IR版本的内建函数放进GScope
        for (int i = 0; i < rootIR.InnerFunc.size(); i++) {
            FuncDef cur = rootIR.InnerFunc.get(i);
            if (gScope.getFunc(cur.name) != null) continue;
            gScope.addIRFunc(cur);
        }

        //跑每个function
        for (var funcDef : node.funcDef) {
            funcDef.accept(this);
        }


    }

    void addMallocGlobalInner() {
        IRBaseType i8Type = new IntType(8, "i8");
        Register reg1 = new Register(i32Type, "size");
        FuncDef malloc = new FuncDef("__malloc", new PtrType(i8Type), true);
        malloc.parameterList.add(reg1);
        rootIR.funcDefs.add(malloc);
    }

    void addStrGlobalInner() {
        ClassType stringType = (ClassType) gScope.getIRType("string");
        IntType charType = new IntType(8, "char");
        Register regS1 = new Register(stringType, "s1");
        Register regS2 = new Register(stringType, "s2");
        ArrayList<FuncDef> strFunc = new ArrayList<>();

        FuncDef str_eq = new FuncDef("___str_eq", charType, true);
        strFunc.add(str_eq);
        FuncDef str_ne = new FuncDef("___str_ne", charType, true);
        strFunc.add(str_ne);
        FuncDef str_slt = new FuncDef("___str_slt", charType, true);
        strFunc.add(str_slt);
        FuncDef str_sle = new FuncDef("___str_sle", charType, true);
        strFunc.add(str_sle);
        FuncDef str_sgt = new FuncDef("___str_sgt", charType, true);
        strFunc.add(str_sgt);
        FuncDef str_sge = new FuncDef("___str_sge", charType, true);
        strFunc.add(str_sge);

        FuncDef str_add = new FuncDef("___str_add", stringType, true);
        strFunc.add(str_add);

        for (int i = 0; i < strFunc.size(); i++) {
            FuncDef cur = strFunc.get(i);
            cur.parameterList.add(regS1);
            cur.parameterList.add(regS2);
            rootIR.funcDefs.add(cur);
        }
    }

    //默认构造函数？
    @Override
    public void visit(NewClassExprNode node) {
        IRBaseType baseClassType = gScope.getIRType(node.type.type.name);
        node.irBaseType = new PtrType(baseClassType);


        IRBaseType i8ptr = new PtrType(new IntType(8, "char"));
        Register register = new Register(regCnt++, i8ptr);
        Malloc malloc = new Malloc(register, new ConstInt((baseClassType.size() + 7) / 8), gScope.getGlobalFunc("__malloc"));
        currentBlock.push_back(malloc);

        Register classReg = new Register(regCnt++, node.irBaseType);
        Bitcast bitcast = new Bitcast(i8ptr, node.irBaseType, classReg, register);
        currentBlock.push_back(bitcast);

        if (node.type.type.classDef.constructor != null) {
            Call call = new Call("__" + node.type.type.name + "_" + node.type.type.name, null, new VoidType());
            call.addParameter(classReg);
            currentBlock.push_back(call);
        }
        node.irValue = classReg;
    }

    @Override
    public void visit(DotVarExprNode node) {
        node.irBaseType = new PtrType(toIRType(node.type));
        if (!node.type.NotClass()) {
            node.irBaseType = new PtrType(node.irBaseType);
        }
        node.lhs.accept(this);
        Register ls = (Register) LoadVal(node.lhs);

        Register newLs = new Register(regCnt++, node.irBaseType);

        ClassType structBaseType = (ClassType) gScope.getIRType(node.lhs.type.type.name);
        ConstInt number = new ConstInt(structBaseType.getMap(((VarExprNode) node.rhs).name));
        GetElementPtr getPtr = new GetElementPtr(newLs, ls, number, true);
        currentBlock.push_back(getPtr);

        if (inCircuit(((PtrType) node.irBaseType).type)) loadVarInCirCuit(newLs);

        node.irValue = newLs;
    }

    @Override
    public void visit(NewArrDemNode node) {
        if (node.arrDimension != null) {
            node.arrDimension.accept(this);
        }
    }

    @Override
    public void visit(SingleVarDefNode node) {
        IRBaseType irType = toIRType(node.typeNode);
        if (!node.typeNode.NotClass()) irType = new PtrType(irType);
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
        // currentBlock.push_back(allocateInstruction);

        if (node.expression != null) {
            node.expression.accept(this);
            if (node.expression.isAssignable) {
                Register loadReg = new Register(regCnt++, ((PtrType) node.expression.irValue.IRType).type);
                Load loadExp = new Load(loadReg, (Register) node.expression.irValue);
                currentBlock.push_back(loadExp);
                allocReg.value = loadReg;
            } else allocReg.value = node.expression.irValue;
            Store store = new Store(allocReg.value, allocReg);
            currentBlock.push_back(store);
        }
    }

    //varexpr 返回原始地址 然后留给下一级判断要不要load！！！！
    @Override
    public void visit(VarExprNode node) {
        Register var = currentScope.getReg(node.name);
        if (var == null) {
            //in scope turn to this
            Register thisReg = currentScope.getReg("this");
            Register loadThisReg = new Register(regCnt++, ((PtrType) thisReg.IRType).type);
            Load loadThis = new Load(loadThisReg, thisReg);
            currentBlock.push_back(loadThis);

            IRBaseType nodeType = new PtrType(toIRType(node.type));
            if (!node.type.NotClass()) nodeType = new PtrType(nodeType);
            Register valReg = new Register(regCnt++, nodeType);
            ClassType structType = (ClassType) gScope.getIRType(currentScope.getCurrentClassScope().ClassType.name);
            ConstInt number = new ConstInt(structType.getMap(node.name));
            GetElementPtr getPtr = new GetElementPtr(valReg, loadThisReg, number, true);
            currentBlock.push_back(getPtr);
            var = valReg;
        }
        node.irBaseType = var.IRType;
        node.irValue = var;

        if (inCircuit(((PtrType) var.IRType).type)) loadVarInCirCuit(var);
    }

    @Override
    public void visit(DelayCellExpr node) {
        node.expression.accept(this);

        Register originAddr = (Register) node.expression.irValue;
        Register cellData = new Register(regCnt++, ((PtrType) originAddr.IRType).type);
        Load loadData = new Load(cellData, originAddr);
        currentBlock.push_back(loadData);

        //a++ 的值
        Register currentEntity = new Register(regCnt++, cellData.IRType);
        String option = node.option.equals("++") ? "add" : "sub nsw";
        Binary binary = new Binary(cellData, new ConstInt(1), currentEntity, option);
        currentBlock.push_back(binary);

        Store store = new Store(currentEntity, originAddr);
        currentBlock.push_back(store);
        node.irValue = cellData;
        node.irBaseType = cellData.IRType;
    }

    @Override
    public void visit(CellExprNode node) {
        node.expression.accept(this);
        IRValue currentEntity = node.expression.irValue;
        if (node.expression.isAssignable) {
            Register loadReg = new Register(regCnt++, ((PtrType) currentEntity.IRType).type);
            Load loadVal = new Load(loadReg, (Register) node.expression.irValue);
            currentBlock.push_back(loadVal);
            currentEntity = loadReg;
        }
        if (node.option.equals("!")) {
            Register conditionI1 = new Register(regCnt++, new IntType(1, "i1"));
            Trunc i8Toi1 = new Trunc(gScope.getIRType("bool"), new IntType(1, "i1"), conditionI1, currentEntity);
            currentBlock.push_back(i8Toi1);

            Register xorBinary = new Register(regCnt++, new IntType(1, "i1"));
            Binary binary = new Binary(conditionI1, new ConstBool(true), xorBinary, "xor");
            currentBlock.push_back(binary);

            Register conditionI8 = new Register(regCnt++, gScope.getIRType("bool"));
            Zext i1Toi8 = new Zext(new IntType(1, "i1"), gScope.getIRType("bool"), conditionI8, xorBinary);
            currentBlock.push_back(i1Toi8);

            node.irValue = conditionI8;
            if (inCircuit != 0) {
                Store store = new Store(conditionI8, currentScope.getReg(".phi"));
                currentBlock.push_back(store);
            }
        } else {
            Register newLs = new Register(regCnt++, currentEntity.IRType);
            if (node.option.equals("-")) {
                Binary binary = new Binary(new ConstInt(0), currentEntity, newLs, "sub nsw");
                currentBlock.push_back(binary);
                node.irValue = newLs;
            } else if (node.option.equals("~")) {
                Binary binary = new Binary(currentEntity, new ConstInt(-1), newLs, "xor");
                currentBlock.push_back(binary);
                node.irValue = newLs;
            } else if (node.option.equals("++") || node.option.equals("--")) {
                String option = node.option.equals("++") ? "add" : "sub nsw";
                Binary binary = new Binary(currentEntity, new ConstInt(1), newLs, option);
                currentBlock.push_back(binary);
                Register originReg = (Register) node.expression.irValue;
                Store store = new Store(newLs, originReg);
                currentBlock.push_back(store);
                node.irValue = originReg;
            }
        }
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


        if (node.condition.isAssignable) {
            Register loadReg = new Register(regCnt++, ((PtrType) node.condition.irValue.IRType).type);
            Load load = new Load(loadReg, (Register) node.condition.irValue);
            currentBlock.push_back(load);
            ConditionValue = loadReg;
        }
        Register conditionI1 = new Register(regCnt++, new IntType(1, "i1"));
        Trunc i8Toi1 = new Trunc(gScope.getIRType("bool"), new IntType(1, "i1"), conditionI1, ConditionValue);
        currentBlock.push_back(i8Toi1);
        Branch branch = new Branch(conditionI1, thenBlock, target);
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

        //origin length
        Register dimenLen = new Register(regCnt++, i32Type);
        Binary mulDimen = new Binary(dimen, new ConstInt(ptrType.size() / 8), dimenLen, "mul");
        currentBlock.push_back(mulDimen);

        //预留4 byte 空间来记录ptr size
        Register length = new Register(regCnt++, i32Type);
        Binary addSize = new Binary(dimenLen, new ConstInt(i32Type.size() / 8), length, "add");
        currentBlock.push_back(addSize);

        PtrType i8ptr = new PtrType(new IntType(8, "_char"));
        //mallocConv 最终要返回的ptr
        Register mallocOrigin = new Register(regCnt++, i8ptr);
        Malloc mallocArr = new Malloc(mallocOrigin, length, gScope.getGlobalFunc("__malloc"));
        currentBlock.push_back(mallocArr);


        //把size存到开头
        Register arrLength = new Register(regCnt++, new PtrType(i32Type));
        Bitcast bitcastToPtrInt = new Bitcast(i8ptr, arrLength.IRType, arrLength, mallocOrigin); //将头的*i8 转成*i32
        currentBlock.push_back(bitcastToPtrInt);
        Store storeArrLength = new Store(dimen, arrLength);
        currentBlock.push_back(storeArrLength);

        Register mallocConv = new Register(regCnt++, ptrType);
        Bitcast bitcastMallocType = new Bitcast(mallocOrigin.IRType, ptrType, mallocConv, mallocOrigin);
        currentBlock.push_back(bitcastMallocType);

        //返回的指针要挪到size后一位，并且类型转化
        Register mallocPtr = new Register(regCnt++, ptrType);
        GetElementPtr getArrPtr = new GetElementPtr(mallocPtr, mallocConv, new ConstInt(1));
        currentBlock.push_back(getArrPtr);


        //最后一层 直接返回
        if (layer == dimensions.size() - 1) return mallocPtr;

        //捏一个for循环
        int label = currentFunc.label++;
        BasicBlock conditionBlock = new BasicBlock("array.for.begin" + label, label);
        BasicBlock bodyBlock = new BasicBlock("array.for.body" + label, label);
        BasicBlock endBlock = new BasicBlock("array.for.end" + label, label);

        Register iReg = new Register(new PtrType(i32Type), "_new_i." + layer + "." + label);
        Alloca allocaIReg = new Alloca(iReg);
        currentFunc.addAllocate(allocaIReg);
        //currentBlock.push_back(allocaIReg);
        Store storeiReg = new Store(new ConstInt(0), iReg);
        currentBlock.push_back(storeiReg);
        currentBlock.push_back(new Jump(conditionBlock));
        currentBlock = conditionBlock;
        currentFunc.addBlock(conditionBlock);

        Register loadIReg = new Register(regCnt++, i32Type);
        Register cmpI = new Register(regCnt++, gScope.getIRType("bool"));
        Load loadI = new Load(loadIReg, iReg);
        currentBlock.push_back(loadI);

        Icmp icmpI = new Icmp(loadIReg, dimen, cmpI, "slt"); // i < dimenLength
        conditionBlock.push_back(icmpI);
        Branch branchBody = new Branch(cmpI, bodyBlock, endBlock);
        currentBlock.push_back(branchBody);
        currentBlock = bodyBlock;
        currentFunc.addBlock(bodyBlock);
        //body
        PtrType ptr = (PtrType) ptrType;
        IRBaseType nextLayerType = ptr.type;

        Register loadiRegLayer = new Register(regCnt++, i32Type);
        Register lowerReg = new Register(regCnt++, ptrType);
        Load loadiLayer = new Load(loadiRegLayer, iReg);
        currentBlock.push_back(loadiLayer);
        GetElementPtr getElementPtr = new GetElementPtr(lowerReg, mallocConv, loadiRegLayer);
        currentBlock.push_back(getElementPtr);
        Register newLayerReg = newArrCreate(dimensions, layer + 1, nextLayerType);

        Store store = new Store(newLayerReg, lowerReg);
        currentBlock.push_back(store);

        //i=i+1
        Register tmpI = new Register(regCnt++, i32Type);
        Register tmpnewI = new Register(regCnt++, i32Type);
        Load loadtmpI = new Load(tmpI, iReg);
        currentBlock.push_back(loadtmpI);
        Binary binaryAdd = new Binary(tmpI, new ConstInt(1), tmpnewI, "add");
        currentBlock.push_back(binaryAdd);
        Store storeI = new Store(tmpnewI, iReg);
        currentBlock.push_back(storeI);

        //go to condition
        Jump jumpOut = new Jump(conditionBlock);
        currentBlock.push_back(jumpOut);
        currentBlock = endBlock;
        currentFunc.addBlock(endBlock);
        return mallocConv;
    }

    @Override
    public void visit(NewArrNode node) {
        PtrType ptr = (PtrType) toIRType(node.type);
        if (!node.type.NotClass()) ptr = new PtrType(ptr);
        node.irBaseType = ptr;
        ArrayList<IRValue> newArrExpr = new ArrayList<>();

        for (NewArrDemNode arrNode : node.arrList) {
            arrNode.accept(this);
            if (arrNode.arrDimension != null) {
                IRValue demVal = arrNode.arrDimension.irValue;
                if (arrNode.arrDimension.isAssignable) {
                    Register loadDemReg = new Register(regCnt++, (((PtrType) demVal.IRType)).type);
                    Load loadDem = new Load(loadDemReg, (Register) demVal);
                    currentBlock.push_back(loadDem);
                    newArrExpr.add(loadDemReg);
                } else newArrExpr.add(arrNode.arrDimension.irValue);
            }
        }

        node.irValue = newArrCreate(newArrExpr, 0, ptr);


    }

    @Override
    public void visit(ArrExprNode node) {//todo 应该取指针
        node.ls.accept(this);
        Register ls = (Register) node.ls.irValue;
        IRBaseType irBaseType = ls.IRType;
        for (ExprNode exp : node.arrDimension) {
            Register ptrStart = new Register(regCnt++, ((PtrType) ls.IRType).type);
            Load loadPtrStart = new Load(ptrStart, ls);
            currentBlock.push_back(loadPtrStart);

            exp.accept(this);
            IRValue elementNum = exp.irValue;
            irBaseType = ((PtrType) irBaseType).type;
            if (exp.isAssignable) {
                Register loadReg = new Register(regCnt++, ((PtrType) elementNum.IRType).type);
                Load loadVar = new Load(loadReg, (Register) elementNum);
                currentBlock.push_back(loadVar);
                elementNum = loadReg;
            }

            Register newReg = new Register(regCnt++, ptrStart.IRType);
            GetElementPtr getElementPtr = new GetElementPtr(newReg, ptrStart, elementNum);//todo get出来应该是个指针
            currentBlock.push_back(getElementPtr);
            ls = newReg;
        }
        node.irValue = ls;
        node.irBaseType = node.irValue.IRType;

        if (inCircuit(((PtrType) node.irBaseType).type)) loadVarInCirCuit(ls);
    }

    @Override
    public void visit(ForStmtNode node) {
        if (node.initializeStmt != null) node.initializeStmt.accept(this);
        int label = currentFunc.label++;
        BasicBlock newBlock = new BasicBlock("for.begin" + label, label);
        currentFunc.addBlock(newBlock);
        currentBlock.push_back(new Jump(newBlock));
        currentBlock = newBlock;
        if (node.conditionNode != null) node.conditionNode.accept(this);
        LoopScope loopScope = new LoopScope(currentScope);
        loopScope.continueLoop = currentBlock;
        loopScope.breakLoop = new BasicBlock("for.end" + label, label);
        loopScope.stepNode = node.stepNode;
        BasicBlock body = new BasicBlock("for.body" + label, label);
        currentFunc.addBlock(body);

        if (node.conditionNode != null) {
            Register conditionI1 = new Register(regCnt++, new IntType(1, "i1"));
            Trunc i8Toi1 = new Trunc(gScope.getIRType("bool"), new IntType(1, "i1"), conditionI1, node.conditionNode.irValue);
            currentBlock.push_back(i8Toi1);
            currentBlock.push_back(new Branch(conditionI1, body, loopScope.breakLoop));
        } else {
            currentBlock.push_back(new Jump(body));
        }

        currentBlock = body;
        currentScope = loopScope;
        if (node.body != null) node.body.accept(this);
        if (node.stepNode != null) node.stepNode.accept(this);
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
            if (loopScope.stepNode != null) loopScope.stepNode.accept(this);
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
        if (!node.type.NotClass()) {
            node.irBaseType = new PtrType(node.irBaseType);
        }
        Call callFunction;
        Register caller;
        boolean isClassFunc = false;
        if (currentScope.getCurrentClassScope() != null && node.name.indexOf(0) != '_') {
            Type classType = currentScope.getCurrentClassScope().ClassType;
            if (classType.classDef.funcDef.containsKey(node.name)) {
                node.name = "__" + classType.name + "_" + node.name;
                isClassFunc = true;
            }
        }
        callFunction = new Call(node.name, node.irBaseType);

        if (node.parameterValueNode != null) {
            ArrayList<ExprNode> value = node.parameterValueNode.parameters;
            for (int i = 0; i < value.size(); i++) {
                value.get(i).accept(this);
                IRValue paraVal = value.get(i).irValue;
                if (value.get(i).isAssignable) {
                    Register loadReg = new Register(regCnt++, ((PtrType) paraVal.IRType).type);
                    Load loadVal = new Load(loadReg, (Register) paraVal);
                    currentBlock.push_back(loadVal);
                    paraVal = loadReg;
                }
                callFunction.addParameter(paraVal);
            }
        }
        if (isClassFunc) {
            Register thisReg = currentScope.getReg("this");
            Register loadThisReg = new Register(regCnt++, ((PtrType) thisReg.IRType).type);
            Load loadThis = new Load(loadThisReg, thisReg);
            currentBlock.push_back(loadThis);
            callFunction.addParameter(loadThisReg);
        }
        currentBlock.push_back(callFunction);
        if (!(node.irBaseType instanceof VoidType)) {
            caller = new Register(regCnt++, node.irBaseType);
            callFunction.caller = caller;
        } else caller = new Register(node.irBaseType, "tmpstore");

        if (inCircuit != 0 && node.irBaseType.isSameType(gScope.getIRType("bool"))) {
            Store storePhi = new Store(caller, currentScope.getReg(".phi"));
            currentBlock.push_back(storePhi);
        }

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
            if (inCircuit != 0) {
                Store store = new Store(newVal, currentScope.getReg(".phi"));
                currentBlock.push_back(store);
            }
        } else if (node.value.isStringVal) {
            String str = node.value.stringVal;
            int num = gScope.getString(str);
            String name;
            if (num == 0) name = ".str";
            else name = ".str." + num;
            Register constStringReg = gScope.getReg(name);

            newVal = constStringReg.value;

        } else if (node.value.isIntVal) {
            newVal = new ConstInt(node.value.intVal);
        } else newVal = new Null();
        node.irValue = newVal;
        node.irBaseType = newVal.IRType;
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

        Register conditionI1 = new Register(regCnt++, new IntType(1, "i1"));
        Trunc i8Toi1 = new Trunc(gScope.getIRType("bool"), new IntType(1, "i1"), conditionI1, node.condition.irValue);
        currentBlock.push_back(i8Toi1);
        Branch branch = new Branch(conditionI1, trueBlock, falseBlock);
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
        if (currentBlock.tailStmt == null) {
            Jump jumpToWhile = new Jump(newBlock);
            currentBlock.push_back(jumpToWhile);
        }
        currentScope = currentScope.parentScope;
        currentBlock = falseBlock;
        currentFunc.addBlock(currentBlock);
    }


    @Override
    public void visit(AssignExprNode node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
        IRValue rs = node.rhs.irValue;
        if (node.rhs.isAssignable) {
            Register rsReg = new Register(regCnt++, ((PtrType) rs.IRType).type);
            Load loadRs = new Load(rsReg, (Register) rs);
            currentBlock.push_back(loadRs);

            rs = rsReg;
        }
        Register ls = (Register) node.lhs.irValue;
        Store storeIns = new Store(rs, ls);
        currentBlock.push_back(storeIns);

        node.irValue = node.lhs.irValue;
        node.irBaseType = node.lhs.irBaseType;
    }


    void BinaryString(BinaryExprNode node) {
        node.ls.accept(this);
        IRBaseType stringType = gScope.getIRType("string");
        IRValue lsStr = node.ls.irValue;
        if (node.ls.isAssignable) {
            Register lsStrLoad = new Register(regCnt++, stringType);
            Load loadLs = new Load(lsStrLoad, (Register) node.ls.irValue);
            currentBlock.push_back(loadLs);
            lsStr = lsStrLoad;
        }

        node.rs.accept(this);
        IRValue rsStr = node.rs.irValue;
        if (node.rs.isAssignable) {
            Register rsStrLoad = new Register(regCnt++, stringType);
            Load loadRs = new Load(rsStrLoad, (Register) node.rs.irValue);
            currentBlock.push_back(loadRs);
            rsStr = rsStrLoad;
        }

        Call call = null;
        if (node.option.equals("+")) {
            Register register = new Register(regCnt++, gScope.getIRType("string"));
            call = new Call("___str_add", register, gScope.getIRType("string"));
            call.addParameter(lsStr);
            call.addParameter(rsStr);
            currentBlock.push_back(call);
            node.irValue = register;
            return;
        }
        IRBaseType charType = new IntType(8, "char");
        Register ls = new Register(regCnt++, charType);
        if (node.option.equals("==")) {//比较两个字符串内容是否完全一致（不是内存地址）
            call = new Call("___str_eq", ls, charType);
        } else if (node.option.equals("!=")) {
            call = new Call("___str_ne", ls, charType);
        } else if (node.option.equals("<")) {
            call = new Call("___str_slt", ls, charType);
        } else if (node.option.equals(">")) {
            call = new Call("___str_sgt", ls, charType);
        } else if (node.option.equals(">=")) {
            call = new Call("___str_sge", ls, charType);
        } else if (node.option.equals("<=")) {
            call = new Call("___str_sle", ls, charType);
        }

        if (ls.IRType.isSameType(charType)) {
            Register newReg = new Register(regCnt++, i32Type);
            Zext zext = new Zext(charType, i32Type, newReg, ls);
            currentBlock.push_back(zext);
            ls = newReg;
        }
        call.addParameter(lsStr);
        call.addParameter(rsStr);
        currentBlock.push_back(call);
        node.irValue = ls;

    }

    @Override
    public void visit(BinaryExprNode node) {
        if (node.ls.type.type.name.equals("string")) {
            BinaryString(node);
            return;
        }
        //把phi的值存在栈上 就可以不用phi指令了
        if (node.option.equals("||") || node.option.equals("&&")) {
            inCircuit++;
            node.ls.accept(this);
            IRValue lsVal = node.ls.irValue;
            if (node.ls.isAssignable) {
                Register lsReg = new Register(regCnt++, ((PtrType) lsVal.IRType).type);
                Load loadLs = new Load(lsReg, (Register) lsVal);
                currentBlock.push_back(loadLs);
                lsVal = lsReg;
            }
            String name = node.option.equals("||") ? "or" : "and";
            int label = currentFunc.label++;
            BasicBlock befBlock = currentBlock;
            BasicBlock rhsBlock = new BasicBlock(name + ".rhs" + label, label);
            BasicBlock endBlock = new BasicBlock(name + ".end" + label, label);

            Register conditionI1 = new Register(regCnt++, new IntType(1, "i1"));
            Trunc i8Toi1 = new Trunc(gScope.getIRType("bool"), new IntType(1, "i1"), conditionI1, lsVal);
            befBlock.push_back(i8Toi1);

            currentFunc.addBlock(rhsBlock);
            currentBlock = rhsBlock;
            node.rs.accept(this);
            currentBlock.push_back(new Jump(endBlock));

            if (node.option.equals("||")) {
                Branch orBr = new Branch(conditionI1, endBlock, rhsBlock);
                befBlock.push_back(orBr);
            } else if (node.option.equals("&&")) {
                Branch andBr = new Branch(conditionI1, rhsBlock, endBlock);
                befBlock.push_back(andBr);
            }
            currentFunc.addBlock(endBlock);
            currentBlock = endBlock;
            Register loadPhiReg = new Register(regCnt++, gScope.getIRType("bool"));
            Load loadPhi = new Load(loadPhiReg, currentScope.getReg(".phi"));
            currentBlock.push_back(loadPhi);
            node.irValue = loadPhiReg;
            inCircuit--;
        } else {
            node.ls.accept(this);
            IRValue lvalue = node.ls.irValue;
            if (node.ls.isAssignable) {
                Register lsReg = new Register(regCnt++, ((PtrType) lvalue.IRType).type);
                Load loadLs = new Load(lsReg, (Register) lvalue);
                currentBlock.push_back(loadLs);
                lvalue = lsReg;
            }
            node.rs.accept(this);
            IRValue rvalue = node.rs.irValue;
            if (node.rs.isAssignable) {
                Register rsReg = new Register(regCnt++, ((PtrType) rvalue.IRType).type);
                Load loadRs = new Load(rsReg, (Register) rvalue);
                currentBlock.push_back(loadRs);
                rvalue = rsReg;
            }

            if (node.option.equals("<") || node.option.equals(">") || node.option.equals("<=") || node.option.equals(">=")
                    || node.option.equals("==") || node.option.equals("!=")) {
                Register i1Reg = new Register(regCnt++, new IntType(1, "i1"));
                Register reg = new Register(regCnt++, gScope.getIRType("bool"));
                String op = null;
                switch (node.option) {
                    case "<" -> op = "slt";
                    case ">" -> op = "sgt";
                    case "<=" -> op = "sle";
                    case ">=" -> op = "sge";
                    case "==" -> op = "eq";
                    case "!=" -> op = "ne";
                }
                IRInstruction currentInstruction = new Icmp(lvalue, rvalue, i1Reg, op);
                currentBlock.push_back(currentInstruction);

                Zext zextToi8 = new Zext(new IntType(1, "i1"), new IntType(32, "bool"), reg, i1Reg);
                currentBlock.push_back(zextToi8);

                node.irValue = reg;
                node.irBaseType = gScope.getIRType("bool");
                if (inCircuit != 0) {
                    Store storePhi = new Store(reg, currentScope.getReg(".phi"));
                    currentBlock.push_back(storePhi);
                }
            } else {
                Register reg = new Register(regCnt++, lvalue.IRType);
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
                node.irBaseType = reg.IRType;
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
            currentFunc.addAllocate(allocaRet);
            //currentBlock.push_back(allocaRet);
            currentFunc.retval = retReg;
        }

        //构造存短路求值的寄存器phi
        Register phi = new Register(new PtrType(gScope.getIRType("bool")), ".phi");
        currentFunc.addAllocate(new Alloca(phi));
        // currentFunc.Entry.push_back(new Alloca(phi));
        gScope.addReg(".phi", phi);

        rootIR.addFunc(currentFunc);

        //引用传参 指针？
        if (node.parameterList != null) {
            ArrayList<SingleVarDefNode> para = node.parameterList.parameterList;
            for (var vars : para) {
                IRBaseType varType = toIRType(vars.typeNode);
                if (!vars.typeNode.NotClass()) {
                    varType = new PtrType(varType);
                }
                Register a_addr = new Register(new PtrType(varType), vars.name + ".addr");
                Alloca allocPara = new Alloca(a_addr);
                currentFunc.addAllocate(allocPara);
                //  currentBlock.push_back(allocPara);
                Register a = new Register(varType, vars.name);
                currentFunc.parameterList.add(a);
                Store storeVal = new Store(a, a_addr);
                currentBlock.push_back(storeVal);
                currentScope.addReg(vars.name, a_addr);
            }
        }
        if (currentFunc.classType != null) {
            PtrType thisType = new PtrType(currentFunc.classType);
            Register thisReg = new Register(thisType, "this");
            currentFunc.parameterList.add(thisReg);
            Register allocThisReg = new Register(new PtrType(thisType), "this.addr");
            Alloca allocaThis = new Alloca(allocThisReg);
            currentFunc.addAllocate(allocaThis);
            //currentBlock.push_back(allocaThis);
            Store storeAlloc = new Store(thisReg, allocThisReg);
            currentBlock.push_back(storeAlloc);
            currentScope.addReg("this", allocThisReg);
        }
        if (node.funcBody != null) node.funcBody.accept(this);
        if (currentBlock.tailStmt == null) currentBlock.tailStmt = new Jump(currentFunc.returnBlock);
        Register loadRetReg = new Register(regCnt++, currentFunc.irReturnType);
        if (!(currentFunc.irReturnType instanceof VoidType)) {
            Load loadRet = new Load(loadRetReg, retReg);
            currentFunc.returnBlock.push_back(loadRet);
        }
        Ret ret = new Ret(loadRetReg);
        currentFunc.returnBlock.push_back(ret);
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(ReturnStmtNode node) {
        if (node.returnExpr != null) {
            node.returnExpr.accept(this);
            IRValue currentEntity = node.returnExpr.irValue;
            if (node.returnExpr.isAssignable) {
                if (!(currentEntity.IRType instanceof PtrType)) {
                    System.out.println("?");
                }
                Register loadVarReg = new Register(regCnt++, ((PtrType) currentEntity.IRType).type);
                Load loadVar = new Load(loadVarReg, (Register) currentEntity);
                currentBlock.push_back(loadVar);
                currentEntity = loadVarReg;
            }
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

    //???
    @Override
    public void visit(DotFuncExprNode node) {
        node.lhs.accept(this);
        IRValue lhsVal = LoadVal(node.lhs);

        IRBaseType charPtr = new PtrType(new IntType(8, "char"));
        IRBaseType i32Ptr = new PtrType(i32Type);
        IRValue rhsVal;

        if (node.rhs.name.equals("size") && !(lhsVal.IRType.isClassIRType())) {
            Register sizeRegPtr = new Register(regCnt++, i32Ptr);
            Bitcast bitcast = new Bitcast(lhsVal.IRType, i32Ptr, sizeRegPtr, lhsVal);
            currentBlock.push_back(bitcast);

            Register sizeReg = new Register(regCnt++, i32Ptr);
            GetElementPtr getElementPtr = new GetElementPtr(sizeReg, sizeRegPtr, new ConstInt(-1));
            currentBlock.push_back(getElementPtr);

            Register loadSizeReg = new Register(regCnt++, i32Type);
            Load loadSize = new Load(loadSizeReg, sizeReg);
            currentBlock.push_back(loadSize);

            rhsVal = loadSizeReg;

        } else {
            String className = lhsVal.IRType.isSameType(gScope.getIRType("string")) ? "string" : ((PtrType) lhsVal.IRType).type.name;

            node.rhs.name = "__" + className + "_" + node.rhs.name;
            node.rhs.accept(this);
            rhsVal = LoadVal(node.rhs);

            //把自身当做一个指针传进去（this）放在最后一个
            Call callfunc = ((Register) node.rhs.irValue).callFunc;
            callfunc.addParameter(lhsVal);
        }
        node.irValue = rhsVal;
    }

    @Override
    public void visit(ClassDefStmtNode node) {
        regCnt = 0;
        ClassType type = (ClassType) gScope.getIRType(node.name);
        ClassDef currentClass = new ClassDef(node.name, type);
        currentScope = new ClassScope(currentScope, gScope.getType(node.name));
        for (int i = 0; i < node.memberOrder.size(); i++) {
            type.memberMap.put(node.memberOrder.get(i), i);
        }

        for (var func : node.funcDef.values()) {
            FunDefStmtNode FuncNode = func;
            FuncNode.name = "__" + type.name + "_" + FuncNode.name;
            FuncNode.accept(this);
        }

        if (node.constructor != null) {
            node.constructor.name = "__" + type.name + "_" + type.name;
            node.constructor.accept(this);
        }
        rootIR.classDefs.add(currentClass);

        currentScope = currentScope.parentScope;

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
        int layer = node.originLayer;
        while (layer > 0) {
            layer--;
            newIRBaseType = new PtrType(newIRBaseType);
        }
        return newIRBaseType;
    }

    IRValue LoadVal(ExprNode expr) {
        if (!expr.isAssignable) return expr.irValue;

        Register loadReg = new Register(regCnt++, ((PtrType) expr.irValue.IRType).type);
        Load loadVal = new Load(loadReg, (Register) expr.irValue);
        currentBlock.push_back(loadVal);
        return loadReg;
    }

    void loadVarInCirCuit(Register irValue) {
        Register loadDataReg = new Register(regCnt++, ((PtrType) irValue.IRType).type);
        Load loadData = new Load(loadDataReg, irValue);
        currentBlock.push_back(loadData);

        Store store = new Store(loadDataReg, currentScope.getReg(".phi"));
        currentBlock.push_back(store);
    }

    boolean inCircuit(IRBaseType curType) {
        return inCircuit != 0 && curType.isSameType(gScope.getIRType("bool"));
    }
}
