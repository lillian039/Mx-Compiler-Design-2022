# IR(Intermediate Representation)

IR编译指令：

`clang++ -S -emit-llvm main.cpp`

带符号名字版本：

`clang++ -S -emit-llvm main.cpp -fno-discard-value-names`

测试llvm：

`clang builtin.ll test.ll -o code`

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

编译 lang：

c 语言

`clang -S -emit-llvm A.c`

 c++ 语言

`clang++ -S -emit-llvm A.cpp`

#### Type System

- Void Type

  no size no value

- Function Type

  ```
  <returntype> (<parameter list>)
  ```

- Array Type

  The array type requires a size (number of elements) and an underlying data type.

  ```
  [<# elements> x <elementtype>]
  ```

  但是貌似 Mx 的 Array 要动态分配

- Structure Type

  Structures in memory are accessed using ‘`load`’ and ‘`store`’ by getting a pointer to a field with the ‘`getelementptr`’ instruction. Structures in registers are accessed using the ‘`extractvalue`’ and ‘`insertvalue`’ instructions.

  ```
  %T1 = type { <type list> }     ; Identified normal struct type
  %T2 = type <{ <type list> }>   ; Identified packed struct type
  ```

- "ret" Instruction

  ```
  ret <type> <value>       ; Return a value from a non-void function
  ret void                 ; Return from void function
  ```

- "br" Instruction

- "sext" Instruction

  The ‘`sext`’ sign extends `value` to the type `ty2`.

  ```
  <result> = sext <ty> <value> to <ty2>             ; yields ty2
  ```

- "zext" Instruction

  The ‘`zext`’ instruction zero extends its operand to type `ty2`.

  ```
  <result> = zext <ty> <value> to <ty2>             ; yields ty2
  ```

#### SSA

mem to reg

1+a+1 -> a+2

### [Runtime Preemption Specifiers](https://releases.llvm.org/11.0.0/docs/LangRef.html#id1222)

```
dso_local
```

The compiler may assume that a function or variable marked as `dso_local` will resolve to a symbol within the same linkage unit. Direct access will be generated even if the definition is not within this compilation unit.

### [Structure Types](https://releases.llvm.org/11.0.0/docs/LangRef.html#id1223)

```
%mytype = type { %mytype*, i32 }
```