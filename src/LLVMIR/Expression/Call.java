package LLVMIR.Expression;

import LLVMIR.GlobalDefine.FuncDef;
import LLVMIR.IRInstruction;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

import java.util.ArrayList;

public class Call extends IRInstruction {
        ArrayList<IRValue> parameterList=new ArrayList<>();
        public FuncDef target;
        public Register caller=null;
        public String name;
        public Call(String functionName){
                this.name=functionName;
        }

        public Call(String functionName,Register caller){
                this.name=functionName;
                this.caller=caller;
        }
        public void addParameter(IRValue value){
            parameterList.add(value);
        }

        @Override
        public void printInstruct() {

        }
}
