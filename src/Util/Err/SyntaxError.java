package Util.Err;

import Util.Position;

public class SyntaxError extends Error {
    public SyntaxError(String msg, Position pos) {
        super("SyntaxError: " + msg, pos);
    }
}
