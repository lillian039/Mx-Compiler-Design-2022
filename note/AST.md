AST：abstract syntax code 抽象语法树

ASTNode：抽象函数，用作AST节点的基类

- ASTBuiler：用于初步建树，把节点从根节点开始串起来

- Utility：

  - position类：记录每个语句每个单词所在的位置

  - Scope大类：记录作用范围内定义的变量，与上一层定义的变量

    GlobalScope类：继承Scope，记录所有定义的type，以及全局变量

  - Type类：记录基础变量形式，以及新定义的class，和class里面的函数，变量。（？可不可以把数组也看成一个type？）

- ASTNode大类：

  - ASTNode：基类虚函数，每个节点都含有连向接口 `ASTVisitor` 的`accept`函数，`accept`函数按照节点不同的类型调用不同的 `ASTVisitor` 接口的 `visitor` 函数，实现不同方法
  - RootNode：整个程序的根节点
  - MainNode：main函数节点
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
    - binaryExpr：二元运算 包含 ls 与 rs
    - assignExpr：赋值运算 包含ls 与 rs
    - newExpr：new 语句 数组的new 与 class 的 new
    - dotExpr：`.` 语句，用于递归调用classmember或classmethod

- ASTVisitor接口：一种遍历树的方式，里面函数全部名为 `visit`，对不同的ASTNode调用不同的方法

  - SymbolCollector：从根节点开始跑树，将所有定义的class，收集到gScope的types中 //怎么感觉没有必要？
  - SemanticCheck：从根节点开始跑树，进行语义检查

