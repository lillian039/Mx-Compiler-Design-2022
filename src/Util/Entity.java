package Util;

import java.util.ArrayList;
import java.util.Vector;

public class Entity {
    public boolean boolVal;
    public int intVal;
    public String stringVal;
    public ArrayList<Entity> classVal;

    public Vector<Boolean> arrBool;

    public Vector<Integer> arrInt;

    public Vector<String> arrString;


    public Entity (){}
    public Entity(boolean boolVal){
        this.boolVal=boolVal;
    }

    public Entity(int intVal){
        this.intVal=intVal;
    }

    public Entity(String stringVal){
        this.stringVal=stringVal;
    }

    public Entity(ArrayList<Entity> classVal){
        this.classVal=classVal;
    }


}
