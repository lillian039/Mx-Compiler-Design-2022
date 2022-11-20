package Util.Err;

import Util.Position;

public class IrError extends Error {
    public IrError(String msg, Position pos) {
        super("IRError: " + msg, pos);
    }
}
