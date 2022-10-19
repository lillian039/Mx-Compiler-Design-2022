package Util;

import org.antlr.v4.runtime.misc.Pair;

import java.util.HashMap;
import java.util.Vector;

public class Type {

    public boolean isInt=false,isBool=false,isString=false,isVoid=false,isNull=false;

    public boolean isArr=false;

    public int layer=1;

    public Vector<Integer> arrLayerSize=null;

    public String name;

    public HashMap<String,Type>classMember=null;

    //1、Vector是线程安全的，ArrayList不是线程安全的。
    //2、ArrayList在底层数组不够用时在原来的基础上扩展0.5倍，Vector是扩展1倍。
    public HashMap<String, Pair<Type, Vector<Type>>> funMember=null;//return type + parameter list

    //在Java中比较两个String对象的值，我们要用equals()，而不能用==
    // 因为它比较的是两个对象的引用地址，即内存地址是否相同
    // 而对于基本数据类型，如byte、short、int等，==比较的就是它们的值了。
    public Type(){

    }
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
