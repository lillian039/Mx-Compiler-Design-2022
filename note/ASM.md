#### PART1 将 builtin.c 编译成 builtin.s

` /home/lillian/homework/riscv-toolchain/bin/riscv32-unknown-elf-gcc  -march=rv32ima -mabi=ilp32 -S builtin.c -o builtin.s`

`/home/lillian/homework/riscv-toolchain/` 此为 toolchain 所在地址

加上 -O2 进行优化



#### PART2 函数的栈帧

栈指针：`sp` ( stack pointer )

帧指针：`fp`（frame pointer）

栈被用来传递函数参数、存储返回信息、临时保存寄存器原有值以备恢复以及用来存储局部数据。

单个函数调用操作所使用的栈部分被称为栈帧（stack frame）结构，其一般结构如下图所示。

栈帧结构的两端由两个指针来指定。寄存器 fp 通常用做帧指针（frame pointer），而 sp则用作栈指针（stack pointer）。

栈指针 `sp` 会随着数据的入栈和出栈而移动，因此函数中对大部分数据的访问都基于帧指针 `fp` 进行。



#### PART3 Pseudo Instruction

http://t.csdn.cn/niAst

`call` 指令由 AUIPC + JALR 组成

`ret` 指令用 JALR 组成

4-6个function argument `a0,...,a5`

返回值：`a0`

