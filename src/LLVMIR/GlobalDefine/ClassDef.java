package LLVMIR.GlobalDefine;

import AST.Atom.TypeNode;
import LLVMIR.Value.Register;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassDef extends GlobalDef {
    ArrayList<TypeNode> memberType;

    HashMap<String, Integer> memberMap;
    public String name;

    public ClassDef(String name) {
        this.name = name;
    }

    public void addMember(TypeNode typeNode) {
        memberType.add(typeNode);
    }

    public int getMap(String name) {
        return memberMap.get(name);
    }

    public void addMap(String name, int num) {
        memberMap.put(name, num);
    }
}
