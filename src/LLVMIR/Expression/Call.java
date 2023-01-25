package LLVMIR.Expression;

import LLVMIR.GlobalDefine.FuncDef;
import LLVMIR.IRInstruction;
import LLVMIR.IRType.IRBaseType;
import LLVMIR.IRVisitor;
import LLVMIR.Value.IRValue;
import LLVMIR.Value.Register;

import java.util.ArrayList;

public class Call extends IRInstruction {
        ArrayList<IRValue> parameterList=new ArrayList<>();
        public FuncDef target;

        public IRBaseType returnType;
        public Register caller=null;
        public String name;
        public Call(String functionName,IRBaseType returnType){
                this.name=functionName;
                this.returnType=returnType;
        }

        public Call(String functionName,Register caller,IRBaseType returnType){
                this.name=functionName;
                this.caller=caller;
                this.returnType=returnType;
        }
        public void addParameter(IRValue value){
            parameterList.add(value);
        }

        @Override
        public void printInstruct() {
                if(caller!=null)System.out.print(caller.valueToString()+" = ");
                System.out.print("call "+returnType.typeToString()+" @"+name+"(");
                for(int i=0;i<parameterList.size();i++){
                        IRValue value=parameterList.get(i);
                        if (value.IRType == null) {
                                System.out.println("!!!");
                                return;
                        }
                        else System.out.print(value.IRType.typeToString()+" "+value.valueToString());
                        if(i<parameterList.size()-1)System.out.print(", ");
                }
                System.out.print(")");
                System.out.println();
        }

        @Override
        public void accept(IRVisitor visitor) {
                visitor.visit(this);
        }
}
