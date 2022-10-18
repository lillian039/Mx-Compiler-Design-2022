package Util;

import org.antlr.v4.runtime.misc.Pair;

import java.util.HashMap;
import java.util.Vector;

public class Type {

    public boolean isInt=false,isBool=false,isString=false,isVoid=false;

    public String name;

    public HashMap<String,Type>classMember=null;

    public HashMap<String, Pair<Type, Vector<Type>>> funMember=null;//return type + parameter list

    //在Java中比较两个String对象的值，我们要用equals()，而不能用==
    // 因为它比较的是两个对象的引用地址，即内存地址是否相同
    // 而对于基本数据类型，如byte、short、int等，==比较的就是它们的值了。
    public Type(String name){
        if(name.equals("int"))isInt=true;
        else if (name.equals("bool"))isBool=true;
        else if(name.equals("string"))isString=true;
        else if(name.equals("void"))isVoid=true;
        this.name=name;
    }

    public Type(String name,HashMap<String,Type> classMember,HashMap<String, Pair<Type, Vector<Type>>> funMember){
        this.classMember=classMember;
        this.funMember=funMember;
        this.name=name;
    }

    public String getTypeName(){
        return name;
    }

}
