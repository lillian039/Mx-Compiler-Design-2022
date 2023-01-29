package Assembly;

import Assembly.Operand.ASMFunc;
import Assembly.Operand.ASMPhyReg;

import java.util.*;

public class ASMFn {
    public static ArrayList<String> phyRegName = new ArrayList<>(Arrays.asList(
            "zero", "ra", "sp", "gp", "tp", "t0", "t1", "t2", "s0", "s1",
            "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7",
            "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "t3", "t4", "t5", "t6"));

    public ArrayList<ASMFunc> asmFuncs = new ArrayList<>();

    public ArrayList<ASMFunc> allocFuncs = new ArrayList<>();
    public HashMap<String, ASMPhyReg> phyRegs = new HashMap<>();

    public ASMPhyReg getReg(String name) {
        return phyRegs.get(name);
    }

    public ArrayList<ASMGlobalVarDef> globalDefs = new ArrayList<>();

    public ArrayList<ASMGlobalVarDef> globalStr = new ArrayList<>();

    public void addFunc(ASMFunc asmFunc) {
        asmFuncs.add(asmFunc);
    }

    public void addAllocFunc(ASMFunc asmFunc) {
        allocFuncs.add(asmFunc);
    }

    public void addGlobalVar(ASMGlobalVarDef var) {
        if (var.varType.equals(".asciz")) globalStr.add(var);
        else globalDefs.add(var);
    }

    public ASMFn() {
        for (int i = 0; i < 32; ++i) {
            phyRegs.put(phyRegName.get(i), new ASMPhyReg(phyRegName.get(i)));
        }
    }

}
