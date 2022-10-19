package Util.Err;

import Util.Position;

public class SemanticError extends Error {
    public SemanticError(String msg, Position pos) {
        super("SemanticError: " + msg, pos);
    }
}
