package Util.Err;

import Util.Position;

abstract public class Error extends RuntimeException{
    private Position pos;
    private String message;

    public Error(String msg, Position pos){
        this.message=msg;
        this.pos=pos;
    }

    public String errorMsg(){
        return (message + ":" + pos.toString());
    }
}
