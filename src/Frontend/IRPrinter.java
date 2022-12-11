package Frontend;

import AST.RootNode;

public class IRPrinter {
    public RootNode rootNode;
    public IRPrinter(RootNode rootNode){
        this.rootNode=rootNode;
    }

    public void print(){
        for(var classDef:rootNode.classDefs){
            classDef.print();
            System.out.println();
        }
        rootNode.varDefs.print();
        System.out.println();
        for(var funcDef:rootNode.funcDefs){
            funcDef.print();
            System.out.println();
        }
    }
}
