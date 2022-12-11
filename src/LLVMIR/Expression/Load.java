package LLVMIR.Expression;

import LLVMIR.IRInstruction;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

public class Load extends IRInstruction {
    public Register ls;
    public IRValue rs;

    public Load(Register ls, IRValue rs) {
        this.ls = ls;
        this.rs = rs;
    }

    @Override
    public void printInstruct() {
        System.out.println(ls.valueToString() + " = load " + ls.IRType.typeToString() + ", ptr " + rs.valueToString() + ", align " + rs.IRType.size() / 4);
    }
}
