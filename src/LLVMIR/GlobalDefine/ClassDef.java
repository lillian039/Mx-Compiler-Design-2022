package LLVMIR.GlobalDefine;

import AST.Atom.TypeNode;
import LLVMIR.IRType.ClassType;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.Value.Register;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassDef extends GlobalDef {
    public ClassType classType;

    public String name;

    public ClassDef(String name,ClassType classType) {
        this.name = name;
        this.classType=classType;
    }


    @Override
    public void print() {
    System.out.print(classType.typeToString()+" = type { ");
    for(int i=0;i<classType.members.size();i++){
        System.out.print(classType.members.get(i).typeToString());
        if(i<classType.members.size()-1)System.out.print(", ");
    }
    System.out.println(" }");
    }
}
