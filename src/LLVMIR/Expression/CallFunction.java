package LLVMIR.Expression;

import LLVMIR.GlobalDefine.FuncDef;
import LLVMIR.IRInstruction;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

import java.util.ArrayList;

public class CallFunction extends IRInstruction {
        ArrayList<IRValue> parameterList=new ArrayList<>();
        public FuncDef target;
        public Register caller;
        public CallFunction(){
        }
        public void addParameter(IRValue value){
            parameterList.add(value);
        }
}
