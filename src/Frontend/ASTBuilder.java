package Frontend;

import AST.*;
import Util.GlobalScope;
import gen.MxBaseVisitor;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {
    private GlobalScope gScope;

    public ASTBuilder(GlobalScope gScope_){gScope=gScope_;}

}
