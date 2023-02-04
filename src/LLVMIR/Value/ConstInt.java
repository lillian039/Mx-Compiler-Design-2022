package LLVMIR.Value;

import AST.Atom.TypeNode;
import LLVMIR.IRType.IntType;

public class ConstInt extends ConstVal {
    public ConstInt(int val){
        this.value=val;
        this.IRType=new IntType(32,"int");
        this.isConst = true;
    }

    @Override
    public String valueToString() {
        return ""+value;
    }
}
