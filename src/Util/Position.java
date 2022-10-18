package Util;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Position {

    public int row,column;

    public Position(int row, int col){
        this.row=row;
        this.column=col;
    }

    public Position(Token token){
        this.row=token.getLine();
        this.column=token.getCharPositionInLine();
    }

    public Position(TerminalNode terminalNode){
        this(terminalNode.getSymbol());
    }

    public Position(ParserRuleContext ctx){
        this(ctx.getStart());
    }

    public String posToString(){
        return "row: "+row+","+"column:"+column;
    }
}
