# Mx-Compiler-Design-2022😶‍🌫️

进行时.....

🌱TODO LIST：

- [x] 完成 Mx.g4 
- [x] 完成 AST 的架构
- [x] 完成SymbolCollector
- [x] 完成 SemanticCheck
- [x] 完成 IRBuilder
- [x] 完成 Codegen
- [x] 完成 Mem2Reg 优化
- [x] 完成图染色优化
- [x] 完成 const 优化（但是 opt2 一分都没多）
- [x] 完成删除冗余指令优化（但是 opt2 一分都没有多）

[TOC]

#### 🌻**AST（abstract syntax code）架构：**

ASTNode：抽象函数，用作AST节点的基类

- ASTBuiler：用于初步建树，把节点从根节点开始串起来

- Utility：

  - position类：记录每个语句每个单词所在的位置

  - Scope大类：记录作用范围内定义的变量，与上一层定义的变量

    GlobalScope类：继承Scope，记录所有定义的type，以及全局变量

  - Type类：记录基础变量形式，以及新定义的classNode

- ASTNode大类：

  - ASTNode：基类虚函数，每个节点都含有连向接口 `ASTVisitor` 的`accept`函数，`accept`函数按照节点不同的类型调用不同的 `ASTVisitor` 接口的 `visitor` 函数，实现不同方法
  - RootNode：整个程序的根节点
  - StmtNode：语句节点，基类虚函数，包含 position 用于找到词所在位置

    - blockStmt：引入一个新的花括号作用域，该class里面需要包含这个作用域里头的所有statements
    - varDefStmt：type+name+expression
    - classDefStmt：name+memberlist+functionlist
    - funDefStmt：name+returnType+parameterlist+funcBodyBlock
    - ifStmt：condition+thenStmt+IfStmt
    - forStmt：(Stmt+condition+expression) +forBodyBlock
    - whileStmt：condition+whileBodyBlock
    - returnStmt：Type
    - exprStmt：expression
    - ctrlStmt：break+continue
  - ExprNode：表达式节点，基类虚函数，包含类型 Type 以及值实例 entity

    - atomExpr：基本量
      - funcExpr：lamda 全局函数 class方法
      - ConstExpr：int string bool
      - varExpr：identifier class classmember this this.member
    - binaryExpr：二元运算 包含 符号 ls 与 rs
    - cellExpr：一元运算 包含符号
    - delayCellExpr：一元运算，a++，a--
    - assignExpr：赋值运算 包含ls 与 rs
    - newExpr：new 语句 数组的new 与 class 的 new
    - dotVarExpr：`.` 语句，用于递归调用classmember
    - dotFuncExpr：`.` 语句，用于递归调用classmethod
  - Atom类：一些更为基础的小节点
    - NewArrDemNode：记录new[ Express？]方括号内的内容
    - SingleVarDefNode：记录单个变量定义
    - TypeNode：记录变量类型，包括是否为数组

- ASTVisitor接口：一种遍历树的方式，里面函数全部名为 `visit`，对不同的ASTNode调用不同的方法

  - SymbolCollector：从根节点开始跑树，将所有定义的class收集到gScope的types中，同时收集function 
  - SemanticCheck：从根节点开始跑树，进行语义检查

- Error类：
  - error：虚函数，基类，记录错误提示与错误位置
  - SemanticError：语义错误
  - SyntaxError：语法错误



#### 🌻**SymbolChecker 架构：**

要注意：ClassDef 和 FunctionDef 支持前向引用

- firstVisit

  先遍历所有ClassDef，定义所有Type，判断是否重名后丢件gScope的types内

  接着遍历将所有全局函数，更新返回值，参数列表，并将其放进funDef内

- secondVisit

  第二次遍历，收集所有Class内的member与method，method同样只更新返回值与参数列表

至此，除上述已更新的值外，其余vardef的type均还未更新，要注意！！！

参考资料：

- Tutorial of Yx
- Antlr 介绍：[(127条消息) ANTLR4_pourtheworld的博客-CSDN博客](https://blog.csdn.net/pourtheworld/category_10336918.html)

#### **🌻IR 架构**

Type 类的修改：

赋予每个Type

#### 🌻Codegen 架构

完成寄存器分配

一些tips：

block 的 use def 与 stmt 的 use def 求法不同，有细节

offset的选取是基于全局的

caller保护：call处添加def冲突

callee保护：mv到虚拟寄存器中
