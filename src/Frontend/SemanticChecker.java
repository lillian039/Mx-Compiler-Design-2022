package Frontend;

import AST.*;

import AST.Atom.*;
import AST.Expression.*;
import AST.Statement.*;
import Util.Err.SemanticError;
import Util.Err.SyntaxError;
import Util.Scope.*;
import Util.Type;

import java.util.ArrayList;

public class SemanticChecker implements ASTVisitor {

    private Scope currentScope;

    final private Type boolType, voidType, intType, nullType, stringType;
    final private GlobalScope gScope;

    public SemanticChecker(GlobalScope gScope_) {
        currentScope = gScope = gScope_;
        boolType = gScope.getType("bool");
        voidType = gScope.getType("void");
        intType = gScope.getType("int");
        nullType = gScope.getType("null");
        stringType = gScope.getType("string");
    }

    @Override
    public void visit(RootNode node) {
        for (StmtNode stmt : node.statements) {
            stmt.accept(this);
        }
    }


    @Override
    public void visit(AssignExprNode node) {
        node.rhs.accept(this);
        node.lhs.accept(this);
        if (!node.lhs.isAssignable()) throw new SemanticError("Not assignable", node.pos);
        Type rType = node.rhs.type.type;
        Type lType = node.lhs.type.type;
        if (!node.lhs.type.sameType(node.rhs.type) && (rType != nullType))
            throw new SemanticError("type not match", node.pos);
        if (rType == nullType && !node.lhs.type.isArr &&
                (lType == intType || lType == boolType || lType == voidType || lType == nullType))
            throw new SemanticError("type not match", node.pos);
        node.type = node.lhs.type;
    }

    @Override
    public void visit(NewArrNode node) {
        node.type.accept(this);
        boolean flag = false;
        for (NewArrDemNode dem : node.arrList) {
            dem.accept(this);
            if (dem.arrDimension == null) flag = true;
            if (flag && dem.arrDimension != null) throw new SemanticError("wrong in new class", node.pos);
        }
    }

    @Override
    public void visit(VarExprNode node) {
        SingleVarDefNode var = currentScope.getVar(node.name);
        if (var == null) throw new SemanticError("variable undefine", node.pos);
        node.type = var.typeNode;
    }

    @Override
    public void visit(ArrExprNode node) {
        node.ls.accept(this);
        node.type = new TypeNode(node.pos, node.ls.type);
        for (ExprNode expr : node.arrDimension) {
            expr.accept(this);
            node.type.layer--;
            if (expr.type.type != intType || expr.type.isArr)
                throw new SemanticError("expression in [] can only be int", node.pos);
        }
        if (node.type.layer < 1) throw new SemanticError("[] not match", node.pos);
        if (node.type.layer == 1) node.type.isArr = false;
    }

    @Override
    public void visit(DelayCellExpr node) {
        node.expression.accept(this);
        node.type = node.expression.type;
        if (!node.expression.isAssignable()) throw new SemanticError("not assignable", node.pos);
        if (node.type.type != intType || node.type.isArr) throw new SemanticError("only int can do this", node.pos);
    }

    @Override
    public void visit(CellExprNode node) {
        node.expression.accept(this);
        node.type = node.expression.type;
        node.isAssignable=node.expression.isAssignable;
        if (!node.expression.isAssignable() && (node.option.equals("++") || node.option.equals("--")))
            throw new SemanticError("not assignable", node.pos);
        if (node.type.isArr) throw new SemanticError("no arr", node.pos);
        if (node.isInt() && node.type.type != intType)
            throw new SemanticError("only int can do" + node.option, node.pos);
        if (node.isBool() && node.type.type != boolType)
            throw new SemanticError("only bool can do" + node.option, node.pos);
    }

    @Override
    public void visit(BinaryExprNode node) {
        node.rs.accept(this);
        node.ls.accept(this);
        if (!node.rs.type.sameType(node.ls.type) && node.rs.type.type != nullType && node.ls.type.type != nullType)
            throw new SemanticError("type not match", node.pos);
        String option = node.option;
        Type lType = node.ls.type.type;
        Type rType = node.rs.type.type;

        SemanticError semanticError = new SemanticError("type not match", node.pos);

        if (option.equals("==") || option.equals("!=")) {
            if (node.ls.type.isArr) if (rType != nullType) throw semanticError;
            else if (node.rs.type.isArr) if (lType != nullType) throw semanticError;
            else {
                if (lType != boolType && lType != intType && lType != voidType) {
                    if (rType != nullType && rType != lType) throw semanticError;
                    if (lType == nullType) {
                        if (rType == intType || rType == boolType || rType == voidType) throw semanticError;
                    }
                } else if (lType == boolType) {
                    if (rType != boolType) throw semanticError;
                } else if (lType == intType) {
                    if (rType != intType) throw semanticError;
                }
            }
            node.type = new TypeNode(node.pos, boolType, false);
        } else if (option.equals("||") || option.equals("&&")) {
            if (node.ls.type.isArr || node.rs.type.isArr) {
                throw semanticError;
            }
            if (lType != boolType || rType != boolType) throw semanticError;
            node.type = new TypeNode(node.pos, boolType, false);

        } else if (option.equals("*") || option.equals("/") || option.equals("%") || option.equals("-") ||
                option.equals("<<") || option.equals(">>") || option.equals("&") || option.equals("|") || option.equals("^")) {
            if (node.ls.type.isArr || node.rs.type.isArr) {
                throw semanticError;
            }
            if (lType != intType || rType != intType) throw semanticError;
            node.type = new TypeNode(node.pos, intType, false);
        } else if (option.equals("+")) {
            if (node.ls.type.isArr || node.rs.type.isArr) {
                throw semanticError;
            }
            if (lType == intType && rType == intType) node.type = new TypeNode(node.pos, intType, false);
            else if (lType == stringType && rType == stringType) node.type = new TypeNode(node.pos, stringType, false);
            else throw semanticError;
        } else if (option.equals(">") || option.equals("<") || option.equals(">=") || option.equals("<=")) {
            if (node.ls.type.isArr || node.rs.type.isArr) {
                throw semanticError;
            }
            if (!(lType == intType && rType == intType) && !(lType == stringType && rType == stringType))
                throw semanticError;
            node.type = new TypeNode(node.pos, boolType, false);
        }
    }

    @Override
    public void visit(ConstExprNode node) {
        //nothing to do
    }

    @Override
    public void visit(FuncExprNode node) {
        FunDefStmtNode func = currentScope.getFunc(node.name);
        if (func == null) throw new SemanticError("func not exist", node.pos);
        if (node.parameterValueNode != null) node.parameterValueNode.accept(this);
        if (node.parameterValueNode == null && func.parameterList == null) {
            node.type = func.returnTypeNode;
        } else if (node.parameterValueNode != null && func.parameterList != null) {
            ArrayList<SingleVarDefNode> list = func.parameterList.parameterList;
            ArrayList<ExprNode> value = node.parameterValueNode.parameters;
            if (list.size() != value.size())
                throw new SemanticError("parameter not match", node.pos);
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).typeNode.sameType(value.get(i).type) && value.get(i).type.type != nullType)
                    throw new SemanticError("parameter not match", node.pos);
                TypeNode paraType = list.get(i).typeNode;
                if (value.get(i).type.type == nullType && !paraType.isArr && (paraType.type == intType || paraType.type == boolType || paraType.type == nullType))
                    throw new SemanticError("parameter not match", node.pos);
            }
            node.type = func.returnTypeNode;
        } else throw new SemanticError("parameter not match", node.pos);
    }

    @Override
    public void visit(DotFuncExprNode node) {
        node.lhs.accept(this);
        Type varType = node.lhs.type.type;
        if (node.lhs.type.isArr) {
            if (node.rhs.parameterValueNode != null || !node.rhs.name.equals("size"))
                throw new SemanticError("No such method", node.pos);
            node.type = new TypeNode(node.pos, intType, false);
        } else {
            if (varType.classDef == null) throw new SemanticError("No such method", node.pos);
            currentScope = new ClassScope(currentScope, varType);
            node.rhs.accept(this);
            currentScope = currentScope.parentScope;
            node.type = node.rhs.type;
        }
    }

    @Override
    public void visit(DotVarExprNode node) {
        node.lhs.accept(this);
        Type varType = node.lhs.type.type;
        if (node.lhs.type.isArr) throw new SemanticError("No such member", node.pos);
        if (varType.classDef == null) throw new SemanticError("No such member", node.pos);
        currentScope = new ClassScope(currentScope, varType);
        node.rhs.accept(this);
        currentScope = currentScope.parentScope;
        node.type = node.rhs.type;
    }

    @Override
    public void visit(BlockStmtNode node) {
        currentScope = new BlockScope(currentScope);
        if (node.statements != null) {
            for (StmtNode stmt : node.statements) {
                if (stmt != null) stmt.accept(this);
            }
        }
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(VarDefStmtNode node) {
        node.typeNode.accept(this);
        for (SingleVarDefNode var : node.varDef) {
            var.typeNode = node.typeNode;
            var.accept(this);
            currentScope.addVarDefine(var.name, var);
        }
    }

    @Override
    public void visit(FunDefStmtNode node) {
        if (currentScope != gScope && !(currentScope instanceof ClassScope))
            throw new SyntaxError("can't define function here", node.pos);
        currentScope = new FuncScope(currentScope, node.returnTypeNode, node.parameterList);
        if (currentScope.parentScope instanceof ClassScope && node.name.equals(((ClassScope) currentScope.parentScope).ClassType.name))
            ((FuncScope) currentScope).isConstruction = true;
        for (var stmt : node.funcBody.statements) {
            if (stmt != null) stmt.accept(this);
        }
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(ClassDefStmtNode node) {
        Type classType = gScope.getType(node.name);
        currentScope = new ClassScope(currentScope, classType);
        currentScope.addVarDefine("this", new SingleVarDefNode(node.pos, "this", new TypeNode(node.pos, classType, false)));
        for (var funcDef : node.funcDef.values()) {
            funcDef.accept(this);
        }
        if (node.constructor != null) node.constructor.accept(this);
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(IfStmtNode node) {
        node.condition.accept(this);
        if (node.condition.type.type != boolType || node.condition.type.isArr)
            throw new SemanticError("only bool in condition", node.pos);
        if (node.thenStmt != null) {
            currentScope = new BlockScope(currentScope);
            node.thenStmt.accept(this);
            currentScope = currentScope.parentScope;
        }
        if (node.elseStmt != null) {
            currentScope = new BlockScope(currentScope);
            node.elseStmt.accept(this);
            currentScope = currentScope.parentScope;
        }
    }

    @Override
    public void visit(ForStmtNode node) {
        currentScope = new LoopScope(currentScope);
        if (node.initializeStmt != null) {
            if (!(node.initializeStmt instanceof VarDefStmtNode) && !(node.initializeStmt instanceof ExprStmtNode))
                throw new SyntaxError("only varDef and expr in for initial", node.pos);
            node.initializeStmt.accept(this);
        }
        if (node.conditionNode != null) {
            node.conditionNode.accept(this);
            if (node.conditionNode.type.type != boolType || node.conditionNode.type.isArr)
                throw new SemanticError("only bool in condition", node.pos);
        }
        if (node.stepNode != null) node.stepNode.accept(this);
        if (node.body != null) {
            if (node.body instanceof BlockStmtNode) {
                for (StmtNode stmt : ((BlockStmtNode) node.body).statements) {
                    stmt.accept(this);
                }
            } else node.body.accept(this);
        }
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(WhileStmtNode node) {
        node.condition.accept(this);
        if (node.condition.type.type != boolType || node.condition.type.isArr)
            throw new SemanticError("only bool in condition", node.pos);
        if (node.body != null) {
            currentScope = new LoopScope(currentScope);
            node.body.accept(this);
            currentScope = currentScope.parentScope;
        }
    }

    @Override
    public void visit(CtrlStmtNode node) {
        if (!currentScope.inLoop()) throw new SyntaxError("break continue only in loop!", node.pos);
    }

    @Override
    public void visit(ExprStmtNode node) {
        node.expression.accept(this);
    }

    @Override
    public void visit(ReturnStmtNode node) {
        if (node.returnExpr != null) {
            node.returnExpr.accept(this);
            node.returnType = node.returnExpr.type;
        } else node.returnType = new TypeNode(node.pos, gScope.getType("void"), false);
        Scope funcScope = currentScope.GetCurrentFuncScope();
        if (funcScope == null) throw new SyntaxError("return only in func", node.pos);
        if (funcScope instanceof FuncScope) {
            if (((FuncScope) funcScope).isConstruction) {
                if (node.returnExpr != null) throw new SemanticError("construction can not return value", node.pos);
            } else {
                if (!node.returnType.sameType(((FuncScope) funcScope).returnType) && node.returnType.type != nullType)
                    throw new SemanticError("returnType not match", node.pos);
                if (node.returnType.type == nullType) {
                    TypeNode funcType = (((FuncScope) funcScope).returnType);
                    if (!funcType.isArr && (funcType.type == intType || funcType.type == boolType))
                        throw new SemanticError("returnType not match", node.pos);
                }
            }
        } else {
            if (((LambdaScope) funcScope).returnType == null)
                ((LambdaScope) funcScope).returnType = node.returnType;
            else if (!((LambdaScope) funcScope).returnType.sameType(node.returnType) && node.returnType.type != nullType)
                throw new SemanticError("returnType not match", node.pos);
            else if (node.returnType.type == nullType) {
                TypeNode funcType = ((LambdaScope) funcScope).returnType;
                if (!funcType.isArr && (funcType.type == intType || funcType.type == boolType))
                    throw new SemanticError("returnType not match", node.pos);
            }
        }
    }

    @Override
    public void visit(SingleVarDefNode node) {
        if (node.expression != null) {
            node.expression.accept(this);
            Type lType = node.typeNode.type;
            Type rType = node.expression.type.type;
            if (!node.expression.type.sameType(node.typeNode) && node.expression.type.type != nullType)
                throw new SemanticError("type not match", node.pos);
            if (rType == nullType && !node.typeNode.isArr &&
                    (lType == intType || lType == boolType || lType == voidType || lType == nullType))
                throw new SemanticError("type not match", node.pos);
        }
        else  node.typeNode.accept(this);
    }

    @Override
    public void visit(TypeNode node) {
        if (!gScope.hasType(node.type.name)) throw new SemanticError("Type not exist", node.pos);
        node.type = gScope.getType(node.type.name);
    }

    @Override
    public void visit(NewArrDemNode node) {
        if (node.arrDimension != null) node.arrDimension.accept(this);
        if (node.arrDimension != null && (node.arrDimension.type.type != intType || node.arrDimension.type.isArr))
            throw new SemanticError("expression in [] can only be int", node.pos);
    }

    @Override
    public void visit(LambdaExprNode node) {
        currentScope = new LambdaScope(currentScope, node.isGlobe);
        if (node.functionParameterList != null) node.functionParameterList.accept(this);
        if (node.functionParameterValue != null) node.functionParameterValue.accept(this);
        if (node.functionParameterList != null && node.functionParameterValue != null) {
            ArrayList<SingleVarDefNode> parameterList = node.functionParameterList.parameterList;
            ArrayList<ExprNode> parameterValue = node.functionParameterValue.parameters;
            if (parameterValue.size() != parameterList.size())
                throw new SemanticError("parameter list not match", node.pos);
            for (int i = 0; i < parameterValue.size(); i++) {
                if (!parameterList.get(i).typeNode.sameType(parameterValue.get(i).type)) {
                    throw new SemanticError("parameter list not match", node.pos);
                }
            }
            node.funcBody.accept(this);
        } else if (node.functionParameterValue == null && node.functionParameterList == null) {
            node.funcBody.accept(this);
        } else throw new SemanticError("parameter list not match", node.pos);
        if (((LambdaScope) currentScope).returnType == null) {
            node.type = new TypeNode(node.pos, gScope.getType("void"), false);
        } else node.type = ((LambdaScope) currentScope).returnType;
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(FunctionParameterListNode node) {
        for (SingleVarDefNode var : node.parameterList) {
            var.accept(this);
            if (gScope.hasType(var.name)) throw new SemanticError("Class name exist", node.pos);
            currentScope.addVarDefine(var.name, var);
        }
    }

    @Override
    public void visit(FunctionParameterValueNode node) {
        for (ExprNode expr : node.parameters) {
            expr.accept(this);
        }
    }

    @Override
    public void visit(NewClassExprNode node) {
        node.type.accept(this);
    }

}
