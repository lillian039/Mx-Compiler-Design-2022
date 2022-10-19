package AST;
import Util.*;

import java.util.ArrayList;

public class MainNode extends ASTNode{
    public ArrayList<StmtNode> stmts;

    public Type intType,boolType,stringType,voidType;
    public MainNode (Position pos){
        super(pos);
        intType=new Type("int");
        boolType=new Type("bool");
        stringType=new Type("string");
        voidType=new Type("void");
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
