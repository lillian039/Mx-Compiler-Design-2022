package Frontend;

import AST.ASTVisitor;
import AST.Atom.*;
import AST.Expression.*;
import AST.RootNode;
import AST.Statement.*;
import AST.StmtNode;
import Util.Err.SemanticError;
import Util.Err.SyntaxError;
import Util.Scope.GlobalScope;
import Util.Type;

public class SymbolCollector implements ASTVisitor {
    private GlobalScope gScope;

    private ClassDefStmtNode TmpClass = null;

    public boolean firstVisit = true;

    public SymbolCollector(GlobalScope gScope_) {
        gScope = gScope_;

    }

    @Override
    public void visit(RootNode node) {
        for (ClassDefStmtNode classDef : node.classDef) {
            visit(classDef);
        }
        for (FunDefStmtNode funDef : node.funcDef) {
            visit(funDef);
            if (funDef.name.equals("main")) node.mainNode = funDef;
        }
        firstVisit = false;
        for (ClassDefStmtNode classDef : node.classDef) {
            visit(classDef);
        }

    }

    @Override
    public void visit(AssignExprNode node) {

    }

    @Override
    public void visit(NewArrNode node) {

    }

    @Override
    public void visit(ArrVarExprNode node) {

    }

    @Override
    public void visit(NewClassExprNode node) {

    }

    @Override
    public void visit(VarExprNode node) {

    }

    @Override
    public void visit(DelayCellExpr node) {

    }

    @Override
    public void visit(CellExprNode node) {

    }

    @Override
    public void visit(BinaryExprNode node) {

    }

    @Override
    public void visit(ConstExprNode node) {

    }

    @Override
    public void visit(FuncExprNode node) {
    }

    @Override
    public void visit(DotFuncExprNode node) {

    }

    @Override
    public void visit(BlockStmtNode node) {

    }

    @Override
    public void visit(VarDefStmtNode node) {
        if (!gScope.hasType(node.typeNode.type.name)) throw new SyntaxError("Type not exsit", node.pos);
        node.typeNode.type = gScope.getType(node.typeNode.type.name);
        for (SingleVarDefNode var : node.varDef) {
            var.typeNode.type = node.type;
        }
        if (TmpClass != null) {
            if (TmpClass.memberDef.containsKey(node.varDef.get(0).name))
                throw new SemanticError("renaming class member", node.pos);
            TmpClass.memberDef.put(node.varDef.get(0).name, node.varDef.get(0));
        }
    }

    @Override
    public void visit(FunDefStmtNode node) {
        if(TmpClass!=null&&TmpClass.name.equals(node.name)){
            if(TmpClass.constructor!=null)throw new SemanticError("Constructor already exist",node.pos);
            if(!node.parameterList.isEmpty())throw new SemanticError("Constructor cannot have parameter",node.pos);
            node.returnTypeNode=new TypeNode(node.pos,gScope.getType(TmpClass.name),false);
            TmpClass.constructor=node;
        }
        else if(node.name.equals("main")){
            if(!node.returnTypeNode.type.name.equals("int"))throw new SemanticError("main can only return int",node.pos);
            if(node.returnTypeNode.isArr)throw new SemanticError("main can not return arr",node.pos);
            if(!node.parameterList.isEmpty())throw new SemanticError("main function cannot have parameter",node.pos);
        }
        else {
            if (gScope.hasType(node.name)) throw new SyntaxError("Class name already exist", node.pos);
            if (!gScope.hasType(node.returnTypeNode.type.name))
                throw new SyntaxError("Return type not exist", node.pos);
            node.returnTypeNode.type = gScope.getType(node.returnTypeNode.type.name);
            for (SingleVarDefNode varDef : node.parameterList.parameterList) {
                if (!gScope.hasType(varDef.typeNode.type.name))
                    throw new SyntaxError("Variable type not exist", node.pos);
                varDef.typeNode.type = gScope.getType(varDef.typeNode.type.name);
            }
        if (firstVisit) {
            gScope.addFunDefine(node.name, node);
        } else if (TmpClass != null) {
            if (TmpClass.funcDef.containsKey(node.name))
                throw new SyntaxError("Func name already exist in class", node.pos);
            TmpClass.funcDef.put(node.name, node);
        }
        }
    }

    @Override
    public void visit(ClassDefStmtNode node) {
        if (firstVisit) {
            if (gScope.hasType(node.name)) throw new SyntaxError("Class name already exist", node.pos);
            Type newClassType = new Type(node.name);
            newClassType.classDef = node;
            gScope.addType(newClassType);
        } else {
            TmpClass = node;
            for (StmtNode stmt : node.classBody.statements) {
                if (stmt instanceof VarDefStmtNode) {
                    VarDefStmtNode varDefStmt = (VarDefStmtNode) stmt;
                    if (varDefStmt.varDef.size() > 1) throw new SemanticError("Only single def allowed", node.pos);
                    visit(varDefStmt);
                } else if (stmt instanceof FunDefStmtNode) {
                    visit((FunDefStmtNode) stmt);
                } else throw new SemanticError("Class can only have funcDef and varDef", node.pos);
            }
            TmpClass = null;
        }
    }

    @Override
    public void visit(FunctionParameterListNode node) {

    }

    @Override
    public void visit(FunctionParameterValueNode node) {

    }

    @Override
    public void visit(IfStmtNode node) {

    }

    @Override
    public void visit(ForStmtNode node) {

    }

    @Override
    public void visit(WhileStmtNode node) {

    }

    @Override
    public void visit(CtrlStmtNode node) {

    }

    @Override
    public void visit(ExprStmtNode node) {

    }

    @Override
    public void visit(ReturnStmtNode node) {

    }

    @Override
    public void visit(SingleVarDefNode node) {

    }

    @Override
    public void visit(TypeNode node) {

    }

    @Override
    public void visit(NewArrDemNode node) {

    }

    @Override
    public void visit(DotVarExprNode node) {

    }

    @Override
    public void visit(LambdaExprNode node) {

    }
}
