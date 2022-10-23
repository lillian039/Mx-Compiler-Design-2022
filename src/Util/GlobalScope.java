package Util;

import AST.Atom.SingleVarDefNode;
import AST.Atom.TypeNode;
import AST.Statement.FunDefStmtNode;

import java.util.HashMap;
import java.util.HashSet;

public class GlobalScope extends Scope{

    private HashMap<String,Type> types=new HashMap<>();

    public void initializeGlobalScope(){
        types.put("int",new Type("int"));
        types.put("bool",new Type("bool"));
        types.put("string",new Type("string"));
        types.put("void",new Type("void"));
        types.put("null",new Type("null"));
        Position innerPos=new Position(0,0);
        TypeNode intType=new TypeNode(innerPos,types.get("int"),false);
        TypeNode voidType=new TypeNode(innerPos,types.get("void"),false);
        TypeNode stringType=new TypeNode(innerPos,types.get("string"),false);
        FunDefStmtNode innerPrint=new FunDefStmtNode(innerPos,"print", voidType);
        innerPrint.parameterList.add(new SingleVarDefNode(innerPos,"str",stringType));
        addFunDefine("print",innerPrint);
        FunDefStmtNode innerPrintln=new FunDefStmtNode(innerPos,"println",voidType);
        innerPrint.parameterList.add(new SingleVarDefNode(innerPos,"str",stringType));
        addFunDefine("println",innerPrintln);
        FunDefStmtNode innerPrintInt=new FunDefStmtNode(innerPos,"printInt",voidType);
        innerPrintInt.parameterList.add(new SingleVarDefNode(innerPos,"n",intType));
        addFunDefine("printInt",innerPrintInt);
        FunDefStmtNode innerPrintlnInt=new FunDefStmtNode(innerPos,"printlnInt",voidType);
        innerPrintlnInt.parameterList.add(new SingleVarDefNode(innerPos,"n",intType));
        addFunDefine("printlnInt",innerPrintlnInt);
        FunDefStmtNode innerGetString=new FunDefStmtNode(innerPos,"getString",stringType);
        addFunDefine("getString",innerGetString);
        FunDefStmtNode innerGetInt=new FunDefStmtNode(innerPos,"getInt",intType);
        addFunDefine("getInt",innerGetInt);
        FunDefStmtNode innerToString =new FunDefStmtNode(innerPos,"toString",stringType);
        innerToString.parameterList.add(new SingleVarDefNode(innerPos,"i",intType));
        addFunDefine("toString",innerToString);
    }

    public GlobalScope (Scope parentScope){
        super(parentScope);
    }

    public void addType(Type newType){
        if(hasType(newType.name))
        {
            System.out.println("multiple definition of"+newType.name);
        }
        else
            types.put(newType.name,newType);
    }

    public boolean hasType(String name){
        return types.containsKey(name);
    }

    public Type getType(String name){
        return types.get(name);
    }

}
