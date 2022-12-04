package LLVMIR.Value;

import AST.Atom.TypeNode;
import LLVMIR.IRType.IntType;

public class ConstInt extends IRValue {
    int val;
    public ConstInt(int val){
        this.val=val;
        this.IRType=new IntType(32);
    }
}
