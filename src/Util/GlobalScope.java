package Util;

import java.util.HashMap;
import java.util.HashSet;

public class GlobalScope extends Scope{

    private HashSet<Type> types=new HashSet<>();

    public GlobalScope (Scope parentScope){
        super(parentScope);
    }

    public void addType(String name){
        if(hasType(name))
        {
            System.out.println("multiple definition of"+name);
        }
        else
            types.add(new Type(name));
    }

    public boolean hasType(String name){
        return types.contains(name);
    }

}
