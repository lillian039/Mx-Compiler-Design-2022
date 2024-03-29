package LLVMIR.Value;

import AST.Atom.TypeNode;
import LLVMIR.IRType.*;

public class ConstString extends IRValue {
    String string;
    public int number;

    public String originStr;

    public String strName;
    int size;
    ArrType arrPtrType;

    public ConstString(String string, int number) {
        originStr = string;
        this.string = ReplaceString(string);
        this.number = number;
        this.size = this.string.length() + 1;
        arrPtrType = new ArrType(this.size, new IntType(8, "char"));
        this.IRType = new ClassType("string");
    }

    String ReplaceString(String str){
        return str.replace("\\\\","\\").replace("\\n","\n")
                .replace("\\t","\t").replace("\\\"","\"");
    }

    String ConvertForIr(String str){
        return  str.replace("\\", "\\5C").replace("\n", "\\0A")
                .replace("\t", "\\09").replace("\"", "\\22");
    }

    @Override
    public String valueToString() {
        String name;
        if (number != 0) name = ".str." + number;
        else name = ".str";
        return "getelementptr inbounds ([" + size + " x i8], [" + size + " x i8]* @" + name + ", i32 0, i32 0)";
    }

    public String getStr(){
        return string;
    }

    public String toAllocate() {
        return "private unnamed_addr constant " + arrPtrType.typeToString() + " c \"" + ConvertForIr(string) + "\\00\"";
        //+ ", align " + (size * 8 + 7) / 8;
    }
}
