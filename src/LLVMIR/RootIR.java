package LLVMIR;

import AST.StmtNode;
import LLVMIR.GlobalDefine.ClassDef;
import LLVMIR.GlobalDefine.FuncDef;
import LLVMIR.GlobalDefine.VarDef;

import java.util.ArrayList;

public class RootIR {
    public VarDef varDefs;

    public ArrayList<ClassDef> classDefs = new ArrayList<>();
    public ArrayList<FuncDef> funcDefs = new ArrayList<>();

    public ArrayList<FuncDef> InnerFunc = new ArrayList<>();

    public void addFunc(FuncDef funcDef) {
        funcDefs.add(funcDef);
    }


    public void addVar(VarDef varDef) {
        this.varDefs = varDef;
    }


    public ClassDef getClass(String name) {
        for (var classDef : classDefs) {
            if (classDef.name.equals(name)) return classDef;
        }
        return null;
    }
}
