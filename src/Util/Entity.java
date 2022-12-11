package Util;

import java.util.ArrayList;
import java.util.Vector;

public class Entity {
    public boolean boolVal;
    public int intVal;
    public String stringVal;

    public boolean isBoolVal = false;
    public boolean isStringVal = false;
    public boolean isIntVal = false;

    public Entity() {
    }

    public Entity(boolean boolVal) {
        this.boolVal = boolVal;
        isBoolVal = true;
    }

    public Entity(int intVal) {
        this.intVal = intVal;
        isIntVal = true;
    }

    public Entity(String stringVal) {
        this.stringVal = stringVal.substring(1, stringVal.length() - 1);
        isStringVal = true;
    }


}
