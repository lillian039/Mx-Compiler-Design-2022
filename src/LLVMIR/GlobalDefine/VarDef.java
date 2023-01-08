package LLVMIR.GlobalDefine;

import AST.Atom.SingleVarDefNode;
import AST.RootNode;
import LLVMIR.BasicBlock;
import LLVMIR.Expression.Alloca;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.IRType.IntType;
import LLVMIR.IRType.PtrType;
import LLVMIR.IRType.VoidType;
import LLVMIR.Value.ConstString;
import LLVMIR.Value.Register;
import Util.Scope.GlobalScope;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class VarDef extends GlobalDef {
    public GlobalScope globalScope;
    public ArrayList<SingleVarDefNode> varDef;

    public HashMap<String, Integer> stringCollect = new HashMap<>();

    public FuncDef initializeVar;

    public BasicBlock GlobalVar = new BasicBlock();

    public ArrayList<Alloca> GlobalString = new ArrayList<>();

    public VarDef(GlobalScope globalScope) {
        this.globalScope = globalScope;
        varDef = globalScope.globalVarOrder;
        stringCollect = globalScope.stringCollect;
        for (var vars : varDef) {
            IRBaseType irBaseType = globalScope.getIRType(vars.typeNode.type.name);
            Register reg = new Register(new PtrType(irBaseType), vars.name);
            reg.isGlobe = true;
            this.globalScope.addReg(vars.name, reg);
            Alloca alloca = new Alloca(reg);
            GlobalVar.push_back(alloca);
        }
        stringCollect.forEach((key, value) -> {
            ConstString constString = new ConstString(key, value);
            IntType intType = new IntType(8, "char");
            PtrType ptrType = new PtrType(intType);
            String name;
            if (value == 0) name = ".str";
            else name = ".str." + value;
            Register register = new Register(ptrType, name);
            register.value = constString;
            Alloca alloca = new Alloca(register);
            globalScope.addReg(register.name, register);
            GlobalString.add(alloca);
        });
        GlobalString.sort(Comparator.comparing(Alloca::strNum));
        BasicBlock basicBlock = new BasicBlock("entry", 0);
        initializeVar = new FuncDef(basicBlock, "__mx_initialize_globalVar", new VoidType());

    }

    @Override
    public void print() {
        for (var def : GlobalVar.stmts) {
            if (def instanceof Alloca) ((Alloca) def).printGlobalInstruct();
            else def.printInstruct();
        }
        System.out.println();
        for (var strings : GlobalString) {
            strings.printString();
            System.out.println();
        }
    }
}
