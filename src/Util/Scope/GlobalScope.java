package Util.Scope;

import AST.Atom.SingleVarDefNode;
import AST.Atom.TypeNode;
import AST.Statement.ClassDefStmtNode;
import AST.Statement.FunDefStmtNode;
import LLVMIR.BasicBlock;
import LLVMIR.Expression.Alloca;
import LLVMIR.Expression.Call;
import LLVMIR.Expression.Store;
import LLVMIR.GlobalDefine.ClassDef;
import LLVMIR.GlobalDefine.FuncDef;
import LLVMIR.GlobalDefine.GlobalDef;
import LLVMIR.GlobalDefine.VarDef;
import LLVMIR.IRType.*;
import LLVMIR.Value.ConstInt;
import LLVMIR.Value.Null;
import LLVMIR.Value.Register;
import Util.Position;
import Util.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class GlobalScope extends Scope {
    private HashMap<String, Type> types = new HashMap<>();

    public HashMap<String, FuncDef> globalFunc = new HashMap<>();
    public HashMap<String, Integer> stringCollect = new HashMap<>();
    private HashMap<String, IRBaseType> IRTypes = new HashMap<>();

    public ArrayList<SingleVarDefNode> globalVarOrder = new ArrayList<>();

    public ArrayList<FuncDef> externalFunc = new ArrayList<>();

    public void initializeGlobalScope() {
        types.put("int", new Type("int"));
        types.put("bool", new Type("bool"));
        types.put("void", new Type("void"));
        types.put("null", new Type("null"));

        Position innerPos = new Position(0, 0);
        TypeNode intType = new TypeNode(innerPos, types.get("int"), false);
        TypeNode voidType = new TypeNode(innerPos, types.get("void"), false);

        Type stringClass = new Type("string");
        TypeNode stringType = new TypeNode(innerPos, stringClass, false);

        ClassDefStmtNode stringClassDefNode = new ClassDefStmtNode("string", innerPos);
        stringClassDefNode.classBody = null;

        //开始定义内建函数
        FunDefStmtNode stringLength = new FunDefStmtNode(innerPos, "length", intType);
        stringLength.isInner = true;
        stringClassDefNode.funcDef.put("length", stringLength);

        FunDefStmtNode stringSubString = new FunDefStmtNode(innerPos, "substring", stringType);
        stringSubString.add(new SingleVarDefNode(innerPos, "left", intType));
        stringSubString.add(new SingleVarDefNode(innerPos, "right", intType));
        stringSubString.isInner = true;
        stringClassDefNode.funcDef.put("substring", stringSubString);

        FunDefStmtNode stringParseInt = new FunDefStmtNode(innerPos, "parseInt", intType);
        stringParseInt.isInner = true;
        stringClassDefNode.funcDef.put("parseInt", stringParseInt);

        FunDefStmtNode stringOrd = new FunDefStmtNode(innerPos, "ord", intType);
        stringOrd.add(new SingleVarDefNode(innerPos, "pos", intType));
        stringOrd.isInner = true;
        stringClassDefNode.funcDef.put("ord", stringOrd);

        stringClass.classDef = stringClassDefNode;
        types.put("string", stringClass);


        FunDefStmtNode innerPrint = new FunDefStmtNode(innerPos, "print", voidType);
        innerPrint.isInner = true;
        innerPrint.add(new SingleVarDefNode(innerPos, "str", stringType));
        addFunDefine("print", innerPrint);

        FunDefStmtNode innerPrintln = new FunDefStmtNode(innerPos, "println", voidType);
        innerPrintln.isInner = true;
        innerPrintln.add(new SingleVarDefNode(innerPos, "str", stringType));
        addFunDefine("println", innerPrintln);

        FunDefStmtNode innerPrintInt = new FunDefStmtNode(innerPos, "printInt", voidType);
        innerPrintInt.isInner = true;
        innerPrintInt.add(new SingleVarDefNode(innerPos, "n", intType));
        addFunDefine("printInt", innerPrintInt);

        FunDefStmtNode innerPrintlnInt = new FunDefStmtNode(innerPos, "printlnInt", voidType);
        innerPrintlnInt.add(new SingleVarDefNode(innerPos, "n", intType));
        innerPrintlnInt.isInner = true;
        addFunDefine("printlnInt", innerPrintlnInt);

        FunDefStmtNode innerGetString = new FunDefStmtNode(innerPos, "getString", stringType);
        innerGetString.isInner = true;
        addFunDefine("getString", innerGetString);

        FunDefStmtNode innerGetInt = new FunDefStmtNode(innerPos, "getInt", intType);
        innerGetInt.isInner = true;
        addFunDefine("getInt", innerGetInt);

        FunDefStmtNode innerToString = new FunDefStmtNode(innerPos, "toString", stringType);
        innerToString.isInner = true;
        innerToString.add(new SingleVarDefNode(innerPos, "i", intType));
        addFunDefine("toString", innerToString);
    }

    public GlobalScope(Scope parentScope) {
        super(parentScope);
    }

    public void addType(Type newType) {
        types.put(newType.name, newType);
    }

    public boolean hasType(String name) {
        return types.containsKey(name);
    }

    public void addIRFunc(FuncDef newFunc) {
        globalFunc.put(newFunc.name, newFunc);
    }

    public Type getType(String name) {
        return types.get(name);
    }

    public IRBaseType getIRType(String name) {
        return IRTypes.get(name);
    }

    public void addString(String str) {
        if (stringCollect.containsKey(str)) return;
        stringCollect.put(str, stringCollect.size());
    }

    public void addGlobalVarOder(SingleVarDefNode var) {
        globalVarOrder.add(var);
    }

    public int getString(String str) {
        return stringCollect.get(str);
    }

    public FuncDef getGlobalFunc(String name) {
        return globalFunc.get(name);
    }

    public void IRInitial() {
        NullType nullType = new NullType();
        VoidType voidType = new VoidType();
        IntType intType = new IntType(32, "int");
        IntType boolType = new IntType(32, "bool");
        IntType charType = new IntType(8,"char");
        IRTypes.put("null", nullType);
        IRTypes.put("void", voidType);
        IRTypes.put("bool", boolType);
        IRTypes.put("int", intType);

        for (var classDef : types.values()) {
            if (classDef.classDef != null) {
                ClassType classType = new ClassType(classDef.name);
                IRTypes.put(classDef.name, classType);
            }
        }
        for (var classDef : types.values()) {
            if (classDef.classDef != null) {
                IRBaseType classType = IRTypes.get(classDef.name);
                for (var memDef : classDef.classDef.memberOrder) {
                    TypeNode classTypeNode=classDef.classDef.memberDef.get(memDef).typeNode;
                    IRBaseType memType = toIRType(classTypeNode);
                    if(!classTypeNode.NotClass())memType=new PtrType(memType);
                    ((ClassType) classType).members.add(memType);
                }
            }
        }
    }

    public void FuncInitial() {
        //初始化全局函数
        for (var func : funcMembers.values()) {
            BasicBlock basicBlock = new BasicBlock("entry", 0);
            IRBaseType returnType=toIRType(func.returnTypeNode);
            if(!func.returnTypeNode.NotClass()){
                returnType = new PtrType(returnType);
            }
            FuncDef funcDef = new FuncDef(basicBlock, func.name,returnType);

            funcDef.isInner = func.isInner;
            if (func.isInner) {
                externalFunc.add(funcDef);
                funcDef.originFunc = func;
                InitializeInnerPara(func, funcDef,false);
            }
            globalFunc.put(func.name, funcDef);
        }

        //将结构体成员函数初始化成全局函数
        for (var type : types.values()) {
            if (type.classDef == null) continue;
            //普通成员函数
            ClassDefStmtNode classDef = type.classDef;
            for (var func : classDef.funcDef.values()) {
                BasicBlock basicBlock = new BasicBlock("entry", 0);
                IRBaseType returnType=toIRType(func.returnTypeNode);
                if(!func.returnTypeNode.NotClass()){
                    returnType = new PtrType(returnType);
                }
                FuncDef funcDef = new FuncDef(basicBlock, "__" + classDef.name + "_" + func.name, returnType);
                funcDef.classType = (ClassType) getIRType(type.name);
                funcDef.isInner = func.isInner;
                //仅针对string
                if (func.isInner) {
                    InitializeInnerPara(func, funcDef,true);
                    externalFunc.add(funcDef);
                }
                globalFunc.put(funcDef.name, funcDef);
            }
            //构造函数
            BasicBlock basicBlock = new BasicBlock("entry", 0);
            if (classDef.constructor != null) {
                FuncDef funcDef = new FuncDef(basicBlock, "__" + classDef.name + "_" + classDef.name, new VoidType());
                funcDef.classType = (ClassType) getIRType(type.name);
                globalFunc.put(funcDef.name, funcDef);
            }
        }
    }

    IRBaseType toIRType(TypeNode node) {
        IRBaseType base = getIRType(node.type.name);
        IRBaseType newIRBaseType = base;
        int layer = node.layer;
        while (layer > 0) {
            layer--;
            newIRBaseType = new PtrType(newIRBaseType);
        }
        return newIRBaseType;
    }

    void InitializeInnerPara(FunDefStmtNode func, FuncDef funcDef, boolean isString) {

        if (func.parameterList != null && func.parameterList.parameterList != null) {
            ArrayList<SingleVarDefNode> originPara = func.parameterList.parameterList;
            for (int i = 0; i < originPara.size(); i++) {
                IRBaseType Type = toIRType(originPara.get(i).typeNode);
                Register register = new Register(Type, "var");
                funcDef.parameterList.add(register);
            }
        }

        if (isString) {
            IRBaseType type = new PtrType(new IntType(8, "char"));
            Register register = new Register(type, "str");
            funcDef.parameterList.add(register);
        }
    }

}

