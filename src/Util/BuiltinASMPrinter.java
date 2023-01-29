package Util;

import java.io.FileOutputStream;
import java.io.IOException;

public class BuiltinASMPrinter {
    String builtin = "\t.file\t\"builtin.c\"\n" +
            "\t.option nopic\n" +
            "\t.attribute arch, \"rv32i2p0_m2p0_a2p0\"\n" +
            "\t.attribute unaligned_access, 0\n" +
            "\t.attribute stack_align, 16\n" +
            "\t.text\n" +
            "\t.globl\tBUFFER_LENGTH\n" +
            "\t.section\t.srodata,\"a\"\n" +
            "\t.align\t2\n" +
            "\t.type\tBUFFER_LENGTH, @object\n" +
            "\t.size\tBUFFER_LENGTH, 4\n" +
            "BUFFER_LENGTH:\n" +
            "\t.word\t256\n" +
            "\t.section\t.rodata\n" +
            "\t.align\t2\n" +
            ".LC0:\n" +
            "\t.string\t\"%s\"\n" +
            "\t.text\n" +
            "\t.align\t2\n" +
            "\t.globl\tprint\n" +
            "\t.type\tprint, @function\n" +
            "print:\n" +
            "\taddi\tsp,sp,-32\n" +
            "\tsw\tra,28(sp)\n" +
            "\tsw\ts0,24(sp)\n" +
            "\taddi\ts0,sp,32\n" +
            "\tsw\ta0,-20(s0)\n" +
            "\tlw\ta1,-20(s0)\n" +
            "\tlui\ta5,%hi(.LC0)\n" +
            "\taddi\ta0,a5,%lo(.LC0)\n" +
            "\tcall\tprintf\n" +
            "\tnop\n" +
            "\tlw\tra,28(sp)\n" +
            "\tlw\ts0,24(sp)\n" +
            "\taddi\tsp,sp,32\n" +
            "\tjr\tra\n" +
            "\t.size\tprint, .-print\n" +
            "\t.align\t2\n" +
            "\t.globl\tprintln\n" +
            "\t.type\tprintln, @function\n" +
            "println:\n" +
            "\taddi\tsp,sp,-32\n" +
            "\tsw\tra,28(sp)\n" +
            "\tsw\ts0,24(sp)\n" +
            "\taddi\ts0,sp,32\n" +
            "\tsw\ta0,-20(s0)\n" +
            "\tlw\ta0,-20(s0)\n" +
            "\tcall\tputs\n" +
            "\tnop\n" +
            "\tlw\tra,28(sp)\n" +
            "\tlw\ts0,24(sp)\n" +
            "\taddi\tsp,sp,32\n" +
            "\tjr\tra\n" +
            "\t.size\tprintln, .-println\n" +
            "\t.section\t.rodata\n" +
            "\t.align\t2\n" +
            ".LC1:\n" +
            "\t.string\t\"%d\"\n" +
            "\t.text\n" +
            "\t.align\t2\n" +
            "\t.globl\tprintInt\n" +
            "\t.type\tprintInt, @function\n" +
            "printInt:\n" +
            "\taddi\tsp,sp,-32\n" +
            "\tsw\tra,28(sp)\n" +
            "\tsw\ts0,24(sp)\n" +
            "\taddi\ts0,sp,32\n" +
            "\tsw\ta0,-20(s0)\n" +
            "\tlw\ta1,-20(s0)\n" +
            "\tlui\ta5,%hi(.LC1)\n" +
            "\taddi\ta0,a5,%lo(.LC1)\n" +
            "\tcall\tprintf\n" +
            "\tnop\n" +
            "\tlw\tra,28(sp)\n" +
            "\tlw\ts0,24(sp)\n" +
            "\taddi\tsp,sp,32\n" +
            "\tjr\tra\n" +
            "\t.size\tprintInt, .-printInt\n" +
            "\t.section\t.rodata\n" +
            "\t.align\t2\n" +
            ".LC2:\n" +
            "\t.string\t\"%d\\n\"\n" +
            "\t.text\n" +
            "\t.align\t2\n" +
            "\t.globl\tprintlnInt\n" +
            "\t.type\tprintlnInt, @function\n" +
            "printlnInt:\n" +
            "\taddi\tsp,sp,-32\n" +
            "\tsw\tra,28(sp)\n" +
            "\tsw\ts0,24(sp)\n" +
            "\taddi\ts0,sp,32\n" +
            "\tsw\ta0,-20(s0)\n" +
            "\tlw\ta1,-20(s0)\n" +
            "\tlui\ta5,%hi(.LC2)\n" +
            "\taddi\ta0,a5,%lo(.LC2)\n" +
            "\tcall\tprintf\n" +
            "\tnop\n" +
            "\tlw\tra,28(sp)\n" +
            "\tlw\ts0,24(sp)\n" +
            "\taddi\tsp,sp,32\n" +
            "\tjr\tra\n" +
            "\t.size\tprintlnInt, .-printlnInt\n" +
            "\t.align\t2\n" +
            "\t.globl\tgetInt\n" +
            "\t.type\tgetInt, @function\n" +
            "getInt:\n" +
            "\taddi\tsp,sp,-32\n" +
            "\tsw\tra,28(sp)\n" +
            "\tsw\ts0,24(sp)\n" +
            "\taddi\ts0,sp,32\n" +
            "\taddi\ta5,s0,-20\n" +
            "\tmv\ta1,a5\n" +
            "\tlui\ta5,%hi(.LC1)\n" +
            "\taddi\ta0,a5,%lo(.LC1)\n" +
            "\tcall\tscanf\n" +
            "\tlw\ta5,-20(s0)\n" +
            "\tmv\ta0,a5\n" +
            "\tlw\tra,28(sp)\n" +
            "\tlw\ts0,24(sp)\n" +
            "\taddi\tsp,sp,32\n" +
            "\tjr\tra\n" +
            "\t.size\tgetInt, .-getInt\n" +
            "\t.align\t2\n" +
            "\t.globl\tgetString\n" +
            "\t.type\tgetString, @function\n" +
            "getString:\n" +
            "\taddi\tsp,sp,-32\n" +
            "\tsw\tra,28(sp)\n" +
            "\tsw\ts0,24(sp)\n" +
            "\taddi\ts0,sp,32\n" +
            "\tli\ta5,256\n" +
            "\tmv\ta0,a5\n" +
            "\tcall\tmalloc\n" +
            "\tmv\ta5,a0\n" +
            "\tsw\ta5,-20(s0)\n" +
            "\tlw\ta1,-20(s0)\n" +
            "\tlui\ta5,%hi(.LC0)\n" +
            "\taddi\ta0,a5,%lo(.LC0)\n" +
            "\tcall\tscanf\n" +
            "\tlw\ta5,-20(s0)\n" +
            "\tmv\ta0,a5\n" +
            "\tlw\tra,28(sp)\n" +
            "\tlw\ts0,24(sp)\n" +
            "\taddi\tsp,sp,32\n" +
            "\tjr\tra\n" +
            "\t.size\tgetString, .-getString\n" +
            "\t.align\t2\n" +
            "\t.globl\ttoString\n" +
            "\t.type\ttoString, @function\n" +
            "toString:\n" +
            "\taddi\tsp,sp,-48\n" +
            "\tsw\tra,44(sp)\n" +
            "\tsw\ts0,40(sp)\n" +
            "\taddi\ts0,sp,48\n" +
            "\tsw\ta0,-36(s0)\n" +
            "\tli\ta5,256\n" +
            "\tmv\ta0,a5\n" +
            "\tcall\tmalloc\n" +
            "\tmv\ta5,a0\n" +
            "\tsw\ta5,-20(s0)\n" +
            "\tlw\ta2,-36(s0)\n" +
            "\tlui\ta5,%hi(.LC1)\n" +
            "\taddi\ta1,a5,%lo(.LC1)\n" +
            "\tlw\ta0,-20(s0)\n" +
            "\tcall\tsprintf\n" +
            "\tlw\ta5,-20(s0)\n" +
            "\tmv\ta0,a5\n" +
            "\tlw\tra,44(sp)\n" +
            "\tlw\ts0,40(sp)\n" +
            "\taddi\tsp,sp,48\n" +
            "\tjr\tra\n" +
            "\t.size\ttoString, .-toString\n" +
            "\t.align\t2\n" +
            "\t.globl\t___str_eq\n" +
            "\t.type\t___str_eq, @function\n" +
            "___str_eq:\n" +
            "\taddi\tsp,sp,-32\n" +
            "\tsw\tra,28(sp)\n" +
            "\tsw\ts0,24(sp)\n" +
            "\taddi\ts0,sp,32\n" +
            "\tsw\ta0,-20(s0)\n" +
            "\tsw\ta1,-24(s0)\n" +
            "\tlw\ta1,-24(s0)\n" +
            "\tlw\ta0,-20(s0)\n" +
            "\tcall\tstrcmp\n" +
            "\tmv\ta5,a0\n" +
            "\tseqz\ta5,a5\n" +
            "\tandi\ta5,a5,0xff\n" +
            "\tmv\ta0,a5\n" +
            "\tlw\tra,28(sp)\n" +
            "\tlw\ts0,24(sp)\n" +
            "\taddi\tsp,sp,32\n" +
            "\tjr\tra\n" +
            "\t.size\t___str_eq, .-___str_eq\n" +
            "\t.align\t2\n" +
            "\t.globl\t___str_ne\n" +
            "\t.type\t___str_ne, @function\n" +
            "___str_ne:\n" +
            "\taddi\tsp,sp,-32\n" +
            "\tsw\tra,28(sp)\n" +
            "\tsw\ts0,24(sp)\n" +
            "\taddi\ts0,sp,32\n" +
            "\tsw\ta0,-20(s0)\n" +
            "\tsw\ta1,-24(s0)\n" +
            "\tlw\ta1,-24(s0)\n" +
            "\tlw\ta0,-20(s0)\n" +
            "\tcall\tstrcmp\n" +
            "\tmv\ta5,a0\n" +
            "\tsnez\ta5,a5\n" +
            "\tandi\ta5,a5,0xff\n" +
            "\tmv\ta0,a5\n" +
            "\tlw\tra,28(sp)\n" +
            "\tlw\ts0,24(sp)\n" +
            "\taddi\tsp,sp,32\n" +
            "\tjr\tra\n" +
            "\t.size\t___str_ne, .-___str_ne\n" +
            "\t.align\t2\n" +
            "\t.globl\t___str_slt\n" +
            "\t.type\t___str_slt, @function\n" +
            "___str_slt:\n" +
            "\taddi\tsp,sp,-32\n" +
            "\tsw\tra,28(sp)\n" +
            "\tsw\ts0,24(sp)\n" +
            "\taddi\ts0,sp,32\n" +
            "\tsw\ta0,-20(s0)\n" +
            "\tsw\ta1,-24(s0)\n" +
            "\tlw\ta1,-24(s0)\n" +
            "\tlw\ta0,-20(s0)\n" +
            "\tcall\tstrcmp\n" +
            "\tmv\ta5,a0\n" +
            "\tsrli\ta5,a5,31\n" +
            "\tandi\ta5,a5,0xff\n" +
            "\tmv\ta0,a5\n" +
            "\tlw\tra,28(sp)\n" +
            "\tlw\ts0,24(sp)\n" +
            "\taddi\tsp,sp,32\n" +
            "\tjr\tra\n" +
            "\t.size\t___str_slt, .-___str_slt\n" +
            "\t.align\t2\n" +
            "\t.globl\t___str_sle\n" +
            "\t.type\t___str_sle, @function\n" +
            "___str_sle:\n" +
            "\taddi\tsp,sp,-32\n" +
            "\tsw\tra,28(sp)\n" +
            "\tsw\ts0,24(sp)\n" +
            "\taddi\ts0,sp,32\n" +
            "\tsw\ta0,-20(s0)\n" +
            "\tsw\ta1,-24(s0)\n" +
            "\tlw\ta1,-24(s0)\n" +
            "\tlw\ta0,-20(s0)\n" +
            "\tcall\tstrcmp\n" +
            "\tmv\ta5,a0\n" +
            "\tslti\ta5,a5,1\n" +
            "\tandi\ta5,a5,0xff\n" +
            "\tmv\ta0,a5\n" +
            "\tlw\tra,28(sp)\n" +
            "\tlw\ts0,24(sp)\n" +
            "\taddi\tsp,sp,32\n" +
            "\tjr\tra\n" +
            "\t.size\t___str_sle, .-___str_sle\n" +
            "\t.align\t2\n" +
            "\t.globl\t___str_sgt\n" +
            "\t.type\t___str_sgt, @function\n" +
            "___str_sgt:\n" +
            "\taddi\tsp,sp,-32\n" +
            "\tsw\tra,28(sp)\n" +
            "\tsw\ts0,24(sp)\n" +
            "\taddi\ts0,sp,32\n" +
            "\tsw\ta0,-20(s0)\n" +
            "\tsw\ta1,-24(s0)\n" +
            "\tlw\ta1,-24(s0)\n" +
            "\tlw\ta0,-20(s0)\n" +
            "\tcall\tstrcmp\n" +
            "\tmv\ta5,a0\n" +
            "\tsgt\ta5,a5,zero\n" +
            "\tandi\ta5,a5,0xff\n" +
            "\tmv\ta0,a5\n" +
            "\tlw\tra,28(sp)\n" +
            "\tlw\ts0,24(sp)\n" +
            "\taddi\tsp,sp,32\n" +
            "\tjr\tra\n" +
            "\t.size\t___str_sgt, .-___str_sgt\n" +
            "\t.align\t2\n" +
            "\t.globl\t___str_sge\n" +
            "\t.type\t___str_sge, @function\n" +
            "___str_sge:\n" +
            "\taddi\tsp,sp,-32\n" +
            "\tsw\tra,28(sp)\n" +
            "\tsw\ts0,24(sp)\n" +
            "\taddi\ts0,sp,32\n" +
            "\tsw\ta0,-20(s0)\n" +
            "\tsw\ta1,-24(s0)\n" +
            "\tlw\ta1,-24(s0)\n" +
            "\tlw\ta0,-20(s0)\n" +
            "\tcall\tstrcmp\n" +
            "\tmv\ta5,a0\n" +
            "\tnot\ta5,a5\n" +
            "\tsrli\ta5,a5,31\n" +
            "\tandi\ta5,a5,0xff\n" +
            "\tmv\ta0,a5\n" +
            "\tlw\tra,28(sp)\n" +
            "\tlw\ts0,24(sp)\n" +
            "\taddi\tsp,sp,32\n" +
            "\tjr\tra\n" +
            "\t.size\t___str_sge, .-___str_sge\n" +
            "\t.align\t2\n" +
            "\t.globl\t___str_add\n" +
            "\t.type\t___str_add, @function\n" +
            "___str_add:\n" +
            "\taddi\tsp,sp,-48\n" +
            "\tsw\tra,44(sp)\n" +
            "\tsw\ts0,40(sp)\n" +
            "\tsw\ts1,36(sp)\n" +
            "\taddi\ts0,sp,48\n" +
            "\tsw\ta0,-36(s0)\n" +
            "\tsw\ta1,-40(s0)\n" +
            "\tlw\ta0,-36(s0)\n" +
            "\tcall\tstrlen\n" +
            "\tmv\ts1,a0\n" +
            "\tlw\ta0,-40(s0)\n" +
            "\tcall\tstrlen\n" +
            "\tmv\ta5,a0\n" +
            "\tadd\ta5,s1,a5\n" +
            "\taddi\ta5,a5,1\n" +
            "\tmv\ta0,a5\n" +
            "\tcall\tmalloc\n" +
            "\tmv\ta5,a0\n" +
            "\tsw\ta5,-20(s0)\n" +
            "\tlw\ta1,-36(s0)\n" +
            "\tlw\ta0,-20(s0)\n" +
            "\tcall\tstrcpy\n" +
            "\tlw\ta1,-40(s0)\n" +
            "\tlw\ta0,-20(s0)\n" +
            "\tcall\tstrcat\n" +
            "\tlw\ta5,-20(s0)\n" +
            "\tmv\ta0,a5\n" +
            "\tlw\tra,44(sp)\n" +
            "\tlw\ts0,40(sp)\n" +
            "\tlw\ts1,36(sp)\n" +
            "\taddi\tsp,sp,48\n" +
            "\tjr\tra\n" +
            "\t.size\t___str_add, .-___str_add\n" +
            "\t.align\t2\n" +
            "\t.globl\t__string_substring\n" +
            "\t.type\t__string_substring, @function\n" +
            "__string_substring:\n" +
            "\taddi\tsp,sp,-48\n" +
            "\tsw\tra,44(sp)\n" +
            "\tsw\ts0,40(sp)\n" +
            "\taddi\ts0,sp,48\n" +
            "\tsw\ta0,-36(s0)\n" +
            "\tsw\ta1,-40(s0)\n" +
            "\tsw\ta2,-44(s0)\n" +
            "\tlw\ta4,-40(s0)\n" +
            "\tlw\ta5,-36(s0)\n" +
            "\tsub\ta5,a4,a5\n" +
            "\taddi\ta5,a5,1\n" +
            "\tsw\ta5,-20(s0)\n" +
            "\tlw\ta5,-20(s0)\n" +
            "\tmv\ta0,a5\n" +
            "\tcall\tmalloc\n" +
            "\tmv\ta5,a0\n" +
            "\tsw\ta5,-24(s0)\n" +
            "\tlw\ta5,-36(s0)\n" +
            "\tlw\ta4,-44(s0)\n" +
            "\tadd\ta5,a4,a5\n" +
            "\tlw\ta4,-20(s0)\n" +
            "\tmv\ta2,a4\n" +
            "\tmv\ta1,a5\n" +
            "\tlw\ta0,-24(s0)\n" +
            "\tcall\tmemcpy\n" +
            "\tlw\ta5,-20(s0)\n" +
            "\taddi\ta5,a5,-1\n" +
            "\tlw\ta4,-24(s0)\n" +
            "\tadd\ta5,a4,a5\n" +
            "\tsb\tzero,0(a5)\n" +
            "\tlw\ta5,-24(s0)\n" +
            "\tmv\ta0,a5\n" +
            "\tlw\tra,44(sp)\n" +
            "\tlw\ts0,40(sp)\n" +
            "\taddi\tsp,sp,48\n" +
            "\tjr\tra\n" +
            "\t.size\t__string_substring, .-__string_substring\n" +
            "\t.align\t2\n" +
            "\t.globl\t__string_length\n" +
            "\t.type\t__string_length, @function\n" +
            "__string_length:\n" +
            "\taddi\tsp,sp,-32\n" +
            "\tsw\tra,28(sp)\n" +
            "\tsw\ts0,24(sp)\n" +
            "\taddi\ts0,sp,32\n" +
            "\tsw\ta0,-20(s0)\n" +
            "\tlw\ta0,-20(s0)\n" +
            "\tcall\tstrlen\n" +
            "\tmv\ta5,a0\n" +
            "\tmv\ta0,a5\n" +
            "\tlw\tra,28(sp)\n" +
            "\tlw\ts0,24(sp)\n" +
            "\taddi\tsp,sp,32\n" +
            "\tjr\tra\n" +
            "\t.size\t__string_length, .-__string_length\n" +
            "\t.align\t2\n" +
            "\t.globl\t__string_parseInt\n" +
            "\t.type\t__string_parseInt, @function\n" +
            "__string_parseInt:\n" +
            "\taddi\tsp,sp,-48\n" +
            "\tsw\tra,44(sp)\n" +
            "\tsw\ts0,40(sp)\n" +
            "\taddi\ts0,sp,48\n" +
            "\tsw\ta0,-36(s0)\n" +
            "\taddi\ta5,s0,-20\n" +
            "\tmv\ta2,a5\n" +
            "\tlui\ta5,%hi(.LC1)\n" +
            "\taddi\ta1,a5,%lo(.LC1)\n" +
            "\tlw\ta0,-36(s0)\n" +
            "\tcall\tsscanf\n" +
            "\tlw\ta5,-20(s0)\n" +
            "\tmv\ta0,a5\n" +
            "\tlw\tra,44(sp)\n" +
            "\tlw\ts0,40(sp)\n" +
            "\taddi\tsp,sp,48\n" +
            "\tjr\tra\n" +
            "\t.size\t__string_parseInt, .-__string_parseInt\n" +
            "\t.align\t2\n" +
            "\t.globl\t__string_ord\n" +
            "\t.type\t__string_ord, @function\n" +
            "__string_ord:\n" +
            "\taddi\tsp,sp,-32\n" +
            "\tsw\ts0,28(sp)\n" +
            "\taddi\ts0,sp,32\n" +
            "\tsw\ta0,-20(s0)\n" +
            "\tsw\ta1,-24(s0)\n" +
            "\tlw\ta5,-20(s0)\n" +
            "\tlw\ta4,-24(s0)\n" +
            "\tadd\ta5,a4,a5\n" +
            "\tlbu\ta5,0(a5)\n" +
            "\tmv\ta0,a5\n" +
            "\tlw\ts0,28(sp)\n" +
            "\taddi\tsp,sp,32\n" +
            "\tjr\tra\n" +
            "\t.size\t__string_ord, .-__string_ord\n" +
            "\t.align\t2\n" +
            "\t.globl\t__malloc\n" +
            "\t.type\t__malloc, @function\n" +
            "__malloc:\n" +
            "\taddi\tsp,sp,-32\n" +
            "\tsw\tra,28(sp)\n" +
            "\tsw\ts0,24(sp)\n" +
            "\taddi\ts0,sp,32\n" +
            "\tsw\ta0,-20(s0)\n" +
            "\tlw\ta5,-20(s0)\n" +
            "\tmv\ta0,a5\n" +
            "\tcall\tmalloc\n" +
            "\tmv\ta5,a0\n" +
            "\tmv\ta0,a5\n" +
            "\tlw\tra,28(sp)\n" +
            "\tlw\ts0,24(sp)\n" +
            "\taddi\tsp,sp,32\n" +
            "\tjr\tra\n" +
            "\t.size\t__malloc, .-__malloc\n" +
            "\t.ident\t\"GCC: (GNU) 10.1.0\"\n";

    public BuiltinASMPrinter(String dstFileName) throws IOException{
        FileOutputStream out = new FileOutputStream(dstFileName);
        out.write(builtin.getBytes());
    }
}
