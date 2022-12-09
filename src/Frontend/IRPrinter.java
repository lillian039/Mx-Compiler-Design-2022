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
        }

        rootNode.varDefs.print();

        for(var funcDef:rootNode.funcDefs){
            funcDef.print();
        }
    }
}
