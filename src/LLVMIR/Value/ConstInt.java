package LLVMIR.Value;

import AST.Atom.TypeNode;

public class ConstInt extends IRValue {
    int val;
    public ConstInt(int val){
        this.val=val;
        this.typeNode=new TypeNode("int");
    }
}
