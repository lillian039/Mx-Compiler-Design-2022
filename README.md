# Mx-Compiler-Design-2022😶‍🌫️

🌱TODO LIST：

- [x] Finish Mx.g4 
- [x] Finish AST Architecture
- [x] FinishSymbolCollector
- [x] Finish SemanticCheck
- [x] Finish IRBuilder
- [x] Finish Codegen
- [x] Finish Mem2Reg Optimization
- [x] Finish graph coloring optimization
- [x] Finish const optimization
- [x] Finish redundant instruction removal optimization (but opt2 didn’t gain any points)

[TOC]

#### 🌻**AST（abstract syntax code）Architecture：**

**ASTNode**: Abstract class serving as the base for all AST nodes.

**Core Components**

**ASTBuilder**
- Responsible for initial tree construction, linking nodes starting from the root node.

**Utility**

- **Position Class**: Tracks the position of each word in every statement.
- **Scope Class**: Records variables defined within a scope and those inherited from the previous level.
  - **GlobalScope**: Inherits from `Scope`. Tracks all defined types and global variables.
- **Type Class**: Records basic variable forms and newly defined `classNode`.

**ASTNode Hierarchy**

**1. ASTNode (Base Class)**
- Virtual functions for all nodes.
- Each node contains an `accept` function connected to the `ASTVisitor` interface. The `accept` function calls different `visitor` functions in `ASTVisitor` based on the node type, enabling diverse methods.

**2. RootNode**
- Represents the root of the entire program.

**3. StmtNode (Statement Node)**
- Base class for all statement nodes.
- Includes a `position` field to locate words.

  **Subtypes:**
  - blockStmt: Introduces a new curly-brace scope, containing all statements within the scope.
  - varDefStmt: Represents `type + name + expression`.
  - classDefStmt: Represents `name + memberList + functionList`.
  - funDefStmt: Represents `name + returnType + parameterList + funcBodyBlock`.
  - ifStmt: Represents `condition + thenStmt + elseStmt`.
  - forStmt: Represents `(initStmt + condition + updateExpr) + forBodyBlock`.
  - whileStmt: Represents `condition + whileBodyBlock`.
  - returnStmt: Represents `Type`.
  - exprStmt: Represents an expression statement.
  - ctrlStmt: Includes `break` and `continue`.

**4. ExprNode (Expression Node)**

- Base class for all expression nodes.
- Includes `Type` for the expression type and `entity` for the instance value.

  **Subtypes:**
  - **atomExpr**: Atomic values.
    - **funcExpr**: Includes lambdas, global functions, and class methods.
    - **ConstExpr**: Includes constants like `int`, `string`, `bool`.
    - **varExpr**: Includes identifiers, classes, class members, `this`, and `this.member`.
  - **binaryExpr**: Binary operations including `operator`, `leftOperand`, and `rightOperand`.
  - **cellExpr**: Unary operations with a single operator.
  - **delayCellExpr**: Postfix unary operations like `a++`, `a--`.
  - **assignExpr**: Assignment operations with `leftOperand` and `rightOperand`.
  - **newExpr**: Represents `new` statements for arrays and classes.
  - **dotVarExpr**: Dot notation for accessing class members.
  - **dotFuncExpr**: Dot notation for calling class methods.

**5. Atom Class**
- More fundamental nodes.
  **Subtypes:**
  - **NewArrDemNode**: Records content inside `new[Expression?]` brackets.
  - **SingleVarDefNode**: Records a single variable definition.
  - **TypeNode**: Records variable types, including whether it is an array.

**ASTVisitor Interface**

- Provides a way to traverse the AST, with functions named `visit`.
- Calls specific methods based on the type of `ASTNode`.

  **Subtypes:**
  - **SymbolCollector**: Traverses the tree from the root, collecting all defined classes into `gScope.types` and gathering functions.
  - **SemanticCheck**: Traverses the tree from the root, performing semantic checks.

**Error Class**

- **Error (Base Class)**: Virtual function to record error messages and locations.
  - **SemanticError**: Represents semantic errors.
  - **SyntaxError**: Represents syntax errors.


#### 🌻**SymbolChecker 架构：**

ClassDef and FunctionDef support forward references.

- firstVisit  
  Traverse all ClassDef nodes first, define all types, check for duplicate names, and add them to gScope.types.  
  Next, traverse all global functions, update their return types and parameter lists, and store them in funDef.

- secondVisit  
  Traverse all ClassDef nodes again to collect class members and methods. For methods, only update their return types and parameter lists.

At this stage, aside from the already updated values (types in ClassDef and FunctionDef), the types of other vardef nodes have **not yet been updated**. Be mindful of this!

Reference materials:  
(Provide relevant links or references here)

- Tutorial of Yx
- Antlr 介绍：[(127条消息) ANTLR4_pourtheworld的博客-CSDN博客](https://blog.csdn.net/pourtheworld/category_10336918.html)

#### **🌻IR Architecture**

Type Class Modifications:

- Assign an `IRType` corresponding to each `Type`.
- Features like `align` can be omitted entirely (try to identify which ones are unnecessary and remove them to keep the implementation lightweight). Optimize wherever possible.

##### Short-circuit evaluation details:

- Create a new block for each `||` and `&&` operation to handle the control flow jumps.  
- Avoid using `phi` nodes directly. Instead, use a virtual register to store all assignment statements that occur during the `||` process. Finally, the `branch` instruction will use the value stored in the virtual register (acting as the `phi` node).

##### new arr Details：

- Recursively expand each level of the `malloc` process at the IR layer.  
- For each level, construct a `for` loop in IR to perform `malloc`.  
- The allocated address must reserve an `i32` space at the beginning to store the `size`. Shift the pointer forward by the size of `i32` to use it as a normal pointer. For subsequent `arr.size()` operations, use `getelementptr -1` to retrieve

#### **🌻Codegen Architecture**

- Convert corresponding IR instructions into ASM instructions, replacing stack space with virtual registers.  
- Standardize the size of all variables (`bool`, `int`, `ptr`) to `i32`.  
- Add `rd`, `rs1`, `rs2`, and `imm` fields to each instruction to facilitate the mapping to virtual registers:  
  - `rd` represents `def` (the destination of the instruction).  
  - `rs1` and `rs2` represent `use` (source operands).  

- For global variables, the address is not located in stack space. Use `la` to load the actual address.  
- Place function arguments in registers `a0, ..., a7`. If there are more than 8 arguments, store the overflow in the stack starting from `sp` upwards.  
- Use `a0` to store the function return value.
##### Stack Operations

Push all registers onto the stack. Use `lw` to load values when needed, and use `sw` to store values if there is a result in `rd` after computation.

#### **🌻 Optimize Series**

##### Graph Coloring

Perform liveness analysis, build an interference graph, and assign different colors to conflicting nodes.

Assume there are K colors:

- **simplify:** Remove nodes with degrees less than K that are unrelated to `move` (does not affect coloring).
- **coalesce:** Merge `move` instructions that can be combined according to the rules.
- **freeze:** Give up merging certain unmergeable nodes and try simplifying again.
- **spill:** Select high-degree nodes to spill, remove these nodes, and attempt simplification again.

**assign color:**

Perform graph coloring and check if spilled nodes can be colored (e.g., two conflict edges having the same color).

**rewrite program:**

If coloring fails, place spilled nodes onto the stack, use virtual registers instead, perform `lw` when needed, and store (`sw`) if there is an `rd`. Repeat the process until coloring succeeds.

**Tips for graph
