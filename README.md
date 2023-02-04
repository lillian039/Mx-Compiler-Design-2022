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

赋予每个Type 对应的 IRType

`align` 之类的特性完全可以不写（尝试一下哪些是可以不要的 删掉 能省则省）

##### 短路求值细节：

- 每个`||` `&&` 都建一个块进行跳转
- 不使用 `phi` 结点，用一个虚拟寄存器，设计在 `||` 过程中的赋值语句都存到 `phi` 中去，最后 `branch`使用 `phi` 中的值

##### new arr细节：

- 在IR层面递归展开每一层的 `malloc` 过程
- 用IR语言在每一层捏一个 `for` 循环进行 `malloc `
- `malloc` 出来的地址大小需要在前面留 `i32` 的大小用于存 `size`，让后移到 `i32`之后用作正常的指针，之后的 `arr.size()` 操作就 `getelement ptr -1` 来取出

#### **🌻Codegen 架构**

将对应的IR指令转化为ASM指令，由虚拟寄存器代替栈上的空间

将所有变量的大小 `bool int ptr` 大小统一为 `i32`

为每个指令添加 `rd rs1 rs2 imm` 以便后续转化虚拟寄存器

`rd` 为 `def`，`rs1 rs2` 为 `use`

对于全局变量，地址不在栈空间中，要 `la` 出真实地址 

函数参数列表放在 `a0,....,a7` 中，超出 8 个的从 `sp` 向上放参数列表

`a0` 存函数返回值

##### 压栈

直接将所有寄存器都放到栈上 每次用的时候lw 算完有rd的话就sw

#### **🌻Optimize 系列**

##### 图染色

进行活跃性分析，建立冲突图，将冲突边两点染上不同的颜色

假设有K个颜色

- **simplify：**去掉颜色小于K的与move无关的结点（不影响染色）

- **coalesce：**按照规则，将可以合并的move进行合并

- **freeze：**放弃合并一些不可合并的点，并再次尝试是否可以简化

- **spill：**挑选度数较高的点溢出，删去次结点后，再次尝试是否可以简化

**assign color：**

最后进行图染色，尝试溢出结点能否被染色（比如两个冲突边同色）

**rewrite program：**

若是失败，则将溢出结点放在栈上，用虚拟寄存器代替，每次使用lw 有rd的进行sw 并再次进行这个过程

重复该过程，直到染色成功

**一些图染色的 tips：**

参考虎书，有伪代码！

block 的 use def 与 stmt 的 use def 求法不同，有细节

offset的选取是基于全局的

caller保护：call处添加def冲突

callee保护：mv到虚拟寄存器中

##### mem2Reg

将IR `alloca` 出来的东西转化为正常的虚拟寄存器（防止在栈上二次解引用地址）

最后在 asm 只要判断 load store 两边的地址是否是同一个 type

若为同一个 type 则需要得到真实地址，否则直接 move

##### const优化

把所有常量计算预先在 IRBuilder 的时候利用表达式树算掉

##### 删除冗余指令优化

利用活跃性分析，找出无用的赋值语句删掉（赋值后无use）
