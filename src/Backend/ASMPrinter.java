package Backend;

import Assembly.ASMFn;

public class ASMPrinter {
    ASMFn asmFn;

    public ASMPrinter(ASMFn asmFn) {
        this.asmFn = asmFn;
    }

    public void printOrigin() {
        for (var func : asmFn.asmFuncs) {
            func.printFunc();
        }
        for (var vars : asmFn.globalDefs) {
            vars.print();
        }
    }

    public void printAlloc() {
        System.out.println("\t.text");
        for (var func : asmFn.allocFuncs) {
            System.out.println("\t.globl\t" + func.funcName);
            func.printFunc();
        }

        for (var vars : asmFn.globalDefs) {
            System.out.println("\t.section\t.data");
            System.out.println("\t.globl\t" + vars.varName);
            vars.print();
        }
        for (var vars : asmFn.globalStr) {
            System.out.println("\t.section\t.rodata");
            System.out.println("\t.globl\t" + vars.varName);
            vars.print();
        }
    }
}
