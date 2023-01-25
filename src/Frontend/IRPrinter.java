package Frontend;

import AST.RootNode;
import LLVMIR.RootIR;

public class IRPrinter {
    public RootIR root;

    public IRPrinter(RootIR root) {
        this.root = root;
    }

    public void print() {
        System.out.println("target datalayout = \"e-m:e-p:32:32-p270:32:32-p271:32:32-p272:64:64-f64:32:64-f80:32-n8:16:32-S128\"\n" +
                "target triple = \"i386-pc-linux-gnu\"");
        System.out.println();
        for (var classDef : root.classDefs) {
            classDef.print();
            System.out.println();
        }
        root.varDefs.print();
        System.out.println();
        for (var funcInner : root.InnerFunc) {
            funcInner.print();
            System.out.println();
        }
        for (var funcDef : root.funcDefs) {
            funcDef.print();
            System.out.println();
        }
    }
}
