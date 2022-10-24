package AST.Statement;

import AST.ASTVisitor;
import AST.Atom.SingleVarDefNode;
import AST.StmtNode;
import Util.Position;
import Util.Type;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
public class ClassDefStmtNode extends StmtNode {
    public String name;
    public HashMap<String, SingleVarDefNode> memberDef = new HashMap<>();
    public HashMap<String,FunDefStmtNode> funcDef = new HashMap<>();

    public FunDefStmtNode constructor=null;

    public BlockStmtNode classBody;

    public ClassDefStmtNode(Position pos) {
        super(pos);
    }

    public ClassDefStmtNode(String name, Position pos) {
        super(pos);
        this.name = name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
