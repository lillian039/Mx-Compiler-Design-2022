package Frontend;

import AST.RootNode;

public class IRPrinter {
    public RootNode rootNode;

    public IRPrinter(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    public void print() {
        System.out.println("target datalayout = \"e-m:e-p:32:32-p270:32:32-p271:32:32-p272:64:64-f64:32:64-f80:32-n8:16:32-S128\"\n" +
                "target triple = \"i386-pc-linux-gnu\"");
        System.out.println();
        for (var classDef : rootNode.classDefs) {
            classDef.print();
            System.out.println();
        }
        rootNode.varDefs.print();
        System.out.println();
        for (var funcInner : rootNode.InnerFunc) {
            funcInner.print();
            System.out.println();
        }
        for (var funcDef : rootNode.funcDefs) {
            funcDef.print();
            System.out.println();
        }
    }
}
