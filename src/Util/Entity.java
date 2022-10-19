package Util;

import java.util.ArrayList;

public class Entity {
    public boolean boolVal;
    public int intVal;
    public String stringVal;
    public ArrayList<Entity> classVal;

    Entity(boolean boolVal){
        this.boolVal=boolVal;
    }

    Entity(int intVal){
        this.intVal=intVal;
    }

    Entity(String stringVal){
        this.stringVal=stringVal;
    }

    Entity(ArrayList<Entity> classVal){
        this.classVal=classVal;
    }


}
