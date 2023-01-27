package LLVMIR.Value;

import AST.Atom.TypeNode;
import LLVMIR.IRType.IntType;

public class ConstInt extends ConstVal {
    public int val;
    public ConstInt(int val){
        this.val=val;
        this.IRType=new IntType(32,"int");
    }

    @Override
    public String valueToString() {
        return ""+val;
    }
}
