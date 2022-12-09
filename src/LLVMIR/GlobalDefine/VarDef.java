package LLVMIR.GlobalDefine;

import AST.Atom.SingleVarDefNode;
import LLVMIR.BasicBlock;
import LLVMIR.Expression.Alloca;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.IRType.VoidType;
import LLVMIR.Value.Register;
import Util.Scope.GlobalScope;

import java.util.ArrayList;

public class VarDef extends GlobalDef {
    public String name;

    public GlobalScope globalScope;
    public ArrayList<SingleVarDefNode> varDef;

    public FuncDef initializeVar;

    public BasicBlock GlobalVar=new BasicBlock();
    public VarDef(GlobalScope globalScope) {
        this.globalScope=globalScope;
        varDef=globalScope.globalVarOrder;
        for(var vars:varDef){
            IRBaseType irBaseType= globalScope.getIRType(vars.name);
            Register reg=new Register(0,irBaseType,vars.name);
            Alloca alloca=new Alloca(reg,irBaseType);
            GlobalVar.push_back(alloca);
        }
        BasicBlock basicBlock=new BasicBlock("entry",0);
        initializeVar=new FuncDef(basicBlock,"__mx_initialize_globalVar",new VoidType());
    }

    @Override
    public void print() {
        for(var def:GlobalVar.stmts){
            if(def instanceof Alloca)((Alloca)def).printGlobalInstruct();
            else def.printInstruct();
        }
    }
}
