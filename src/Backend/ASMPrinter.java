package Backend;

import Assembly.ASMFn;

public class ASMPrinter {
    ASMFn asmFn;
    public ASMPrinter(ASMFn asmFn){
        this.asmFn = asmFn;
    }

    public void printOrigin(){
        for(var func:asmFn.asmFuncs){
            func.printFunc();
        }
        for(var vars:asmFn.globalDefs){
            vars.print();
        }
    }

    public void printAlloc(){
        for(var func:asmFn.allocFuncs){
            func.printFunc();
        }
        for(var vars:asmFn.globalDefs){
            vars.print();
        }
    }
}
