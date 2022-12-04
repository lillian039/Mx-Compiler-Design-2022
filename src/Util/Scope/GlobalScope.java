package Util.Scope;

import AST.Atom.SingleVarDefNode;
import AST.Atom.TypeNode;
import AST.Statement.ClassDefStmtNode;
import AST.Statement.FunDefStmtNode;
import LLVMIR.IRType.*;
import Util.Position;
import Util.Type;

import java.util.HashMap;

public class GlobalScope extends Scope {
    private HashMap<String, Type> types = new HashMap<>();
    private HashMap<String, FunDefStmtNode> classFunc = new HashMap<>();
    private HashMap<String, Integer> stringCollect = new HashMap<>();
    private HashMap<String, IRBaseType> IRTypes = new HashMap<>();


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

        ClassDefStmtNode stringClassDefNode = new ClassDefStmtNode(innerPos);
        stringClassDefNode.classBody = null;

        FunDefStmtNode stringLength = new FunDefStmtNode(innerPos, "length", intType);
        stringClassDefNode.funcDef.put("length", stringLength);

        FunDefStmtNode stringSubString = new FunDefStmtNode(innerPos, "substring", stringType);
        stringSubString.add(new SingleVarDefNode(innerPos, "left", intType));
        stringSubString.add(new SingleVarDefNode(innerPos, "right", intType));
        stringClassDefNode.funcDef.put("substring", stringSubString);

        FunDefStmtNode stringParseInt = new FunDefStmtNode(innerPos, "parseInt", intType);
        stringClassDefNode.funcDef.put("parseInt", stringParseInt);

        FunDefStmtNode stringOrd = new FunDefStmtNode(innerPos, "ord", intType);
        stringOrd.add(new SingleVarDefNode(innerPos, "pos", intType));
        stringClassDefNode.funcDef.put("ord", stringOrd);

        stringClass.classDef = stringClassDefNode;
        types.put("string", stringClass);


        FunDefStmtNode innerPrint = new FunDefStmtNode(innerPos, "print", voidType);
        innerPrint.add(new SingleVarDefNode(innerPos, "str", stringType));
        addFunDefine("print", innerPrint);

        FunDefStmtNode innerPrintln = new FunDefStmtNode(innerPos, "println", voidType);
        innerPrintln.add(new SingleVarDefNode(innerPos, "str", stringType));
        addFunDefine("println", innerPrintln);

        FunDefStmtNode innerPrintInt = new FunDefStmtNode(innerPos, "printInt", voidType);
        innerPrintInt.add(new SingleVarDefNode(innerPos, "n", intType));
        addFunDefine("printInt", innerPrintInt);

        FunDefStmtNode innerPrintlnInt = new FunDefStmtNode(innerPos, "printlnInt", voidType);
        innerPrintlnInt.add(new SingleVarDefNode(innerPos, "n", intType));
        addFunDefine("printlnInt", innerPrintlnInt);

        FunDefStmtNode innerGetString = new FunDefStmtNode(innerPos, "getString", stringType);
        addFunDefine("getString", innerGetString);

        FunDefStmtNode innerGetInt = new FunDefStmtNode(innerPos, "getInt", intType);
        addFunDefine("getInt", innerGetInt);

        FunDefStmtNode innerToString = new FunDefStmtNode(innerPos, "toString", stringType);
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

    public int getString(String str) {
        return stringCollect.get(str);
    }

    public FunDefStmtNode getClassFunc(String name) {
        return classFunc.get(name);
    }

    public void IRInitial() {
        NullType nullType = new NullType();
        VoidType voidType = new VoidType();
        IntType intType = new IntType(32);
        IntType boolType = new IntType(8);
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
                    IRBaseType memType = IRTypes.get(classDef.classDef.memberDef.get(memDef).typeNode.type.name);
                    ((ClassType) classType).members.add(memType);
                }
            }
        }


    }
}
