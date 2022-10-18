package Util;

import org.antlr.v4.runtime.misc.Pair;

import java.util.HashMap;

public class Scope {
    private HashMap<String,Type> variableMembers;//<name,type>
    private HashMap<String, Pair<Type,ArrayLayer>> arrayMembers;//<name,type,layer>

    private Scope parentScope;

    public Scope(Scope parentScope_){
        arrayMembers=new HashMap<>();
        variableMembers=new HashMap<>();
        parentScope=parentScope_;
    }

    public void varDefine(String varName,Type varType){
        if(variableMembers.containsKey(varName)||arrayMembers.containsKey(varName)){
            System.out.println("err!");
        }
        else variableMembers.put(varName,varType);
    }

    public void arrDefine(String varName,Pair<Type,ArrayLayer> arr){
        if(variableMembers.containsKey(varName)||arrayMembers.containsKey(varName)){
            System.out.println("err");
        }
        else arrayMembers.put(varName,arr);
    }

}
