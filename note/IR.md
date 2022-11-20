# IR(Intermediate Representation)

#### 基本块(Basic Block)

基本块是满足下列性质的，最大的连续指令的有序集合。

1. 只能有一个入口，为其第一条指令，不存在从另一个位置进入BB的控制流

2. 只能由一个出口，为其最后一条指令，不存在从BB中间跳出去的情况

特别注意，满足这两条性质的Block，只要不是最大的连续指令集，就不能成为BB，所以实际程序中每条指令最终所在的BB总是情况唯一的。

#### BB的构建思路

1. 如果一条指令是程序中某个跳转的目标，那么它一定是一个BB的入口，否则会违反规则1。

2. 如果一条指令包含跳转操作，那么它一定是一个BB的出口，否则会违反规则2。

如果一条指令紧跟着一条含有跳转操作的指令，那么它一定是一个BB的入口，因为一条指令是BB出口，它的下一条指令一定是下一个BB的入口。

#### BB的构建算法

先找整个程序中BB开始的指令，标记为Leader：

1. 程序第一条指令
2. 跳转的目标指令
3. 带跳转的指令的下一条指令

接着从每个Leader开始往下，直到下一个Leader或者程序结尾之前，合起来是一个BB。

<img src="https://img-blog.csdnimg.cn/20200308021534767.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NIVTE1MTIxODU2,size_1,color_FFFFFF,t_0" alt="img" style="zoom:33%;" />

图中标红的就是找出的Leader。

####  完整 $CFG$ 的构建

$CFG$: (control flow graph)

接下来就是在前面构造好的BB之间添加边，很直观：

1. 两个BB顺次相连，加边

2. 从A无条件跳转B，加边

3. 从A条件跳转到B，加边

注意，这里规则1有例外，如果前一个BB的最后一条指令是无条件的goto，那么不要按照规则1加边。

在构建边的同时，将指令中，goto的目标从指令标号变换成BB的名字：

<img src="https://img-blog.csdnimg.cn/20200308022436349.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NIVTE1MTIxODU2,size_1,color_FFFFFF,t_0" alt="在这里插入图片描述" style="zoom:20%;" />

最终添加`Entry`和`Exit`两个特殊结点，即得到这小节最开始图上的CFG。

[(133条消息) 【软件分析学习笔记】4：中间表示(Intermediate Representation)_LauZyHou的博客-CSDN博客](https://blog.csdn.net/SHU15121856/article/details/104711426)

### $ASM$ 内嵌汇编语法

SSA 静态单一变量赋值

### LLVM

LLVM function definitions consist of the “`define`” keyword, an optional [linkage type](https://releases.llvm.org/11.0.0/docs/LangRef.html#linkage), an optional [runtime preemption specifier](https://releases.llvm.org/11.0.0/docs/LangRef.html#runtime-preemption-model), an optional [visibility style](https://releases.llvm.org/11.0.0/docs/BitCodeFormat.html#visibility), an optional [DLL storage class](https://releases.llvm.org/11.0.0/docs/LangRef.html#dllstorageclass), an optional [calling convention](https://releases.llvm.org/11.0.0/docs/LangRef.html#callingconv), an optional `unnamed_addr` attribute, a return type, an optional [parameter attribute](https://releases.llvm.org/11.0.0/docs/LangRef.html#paramattrs) for the return type, a function name, a (possibly empty) argument list (each with optional [parameter attributes](https://releases.llvm.org/11.0.0/docs/LangRef.html#paramattrs)), optional [function attributes](https://releases.llvm.org/11.0.0/docs/LangRef.html#fnattrs), an optional address space, an optional section, an optional alignment, an optional [comdat](https://releases.llvm.org/11.0.0/docs/LangRef.html#langref-comdats), an optional [garbage collector name](https://releases.llvm.org/11.0.0/docs/LangRef.html#gc), an optional [prefix](https://releases.llvm.org/11.0.0/docs/LangRef.html#prefixdata), an optional [prologue](https://releases.llvm.org/11.0.0/docs/LangRef.html#prologuedata), an optional [personality](https://releases.llvm.org/11.0.0/docs/LangRef.html#personalityfn), an optional list of attached [metadata](https://releases.llvm.org/11.0.0/docs/LangRef.html#metadata), an opening curly brace, a list of basic blocks, and a closing curly brace.



A function definition contains a list of basic blocks, forming the CFG (Control Flow Graph) for the function. Each basic block may optionally **start with a label** (giving the basic block a symbol table entry), contains a list of instructions, and **ends with a [terminator](https://releases.llvm.org/11.0.0/docs/LangRef.html#terminators)** instruction (such as a branch or function return). If an explicit label name is not provided, a block is assigned an implicit numbered label, using the next value from the same counter as used for unnamed temporaries ([see above](https://releases.llvm.org/11.0.0/docs/LangRef.html#identifiers)). For example, if a function entry block does not have an explicit label, it will be assigned label “%0”, then the first unnamed temporary in that block will be “%1”, etc. If a numeric label is explicitly specified, it must match the numeric label that would be used implicitly.

看上去函数需要一个label？

The first basic block in a function is special in two ways: it is immediately executed on entrance to the function, and it is not allowed to have predecessor basic blocks (i.e. there can not be any branches to the entry block of a function). Because the block can have no predecessors, it also cannot have any [PHI nodes](https://releases.llvm.org/11.0.0/docs/LangRef.html#i-phi).

```
define [linkage] [PreemptionSpecifier] [visibility] [DLLStorageClass]
       [cconv] [ret attrs]
       <ResultType> @<FunctionName> ([argument list])
       [(unnamed_addr|local_unnamed_addr)] [AddrSpace] [fn Attrs]
       [section "name"] [comdat [($name)]] [align N] [gc] [prefix Constant]
       [prologue Constant] [personality Constant] (!name !N)* { ... }
```

```
<type> [parameter Attrs] [name]
```

- "ret" Instruction

  ```
  ret <type> <value>       ; Return a value from a non-void function
  ret void                 ; Return from void function
  ```

- "br" Instruction

- 