package LLVMIR.GlobalDefine;

import LLVMIR.IRVisitor;

abstract public class GlobalDef {
    abstract public void print();

    abstract public void accept(IRVisitor visitor);
}
