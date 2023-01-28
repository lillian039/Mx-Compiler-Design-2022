__mx_initialize_globalVar:
	addi	sp, sp, -12
	sw  	ra, 20(sp)
	sw  	s0, 16(sp)
	lw  	a0, -12(s0)
	ret
	lw  	s0, 20(sp)
	lw  	ra, 16(sp)
	addi	sp, sp, 12
main:
	addi	sp, sp, -263
	sw  	ra, 271(sp)
	sw  	s0, 267(sp)
	call	__mx_initialize_globalVar
	call	getInt
	sw  	a0, -20(s0)
	la  	%4, n
	sw  	%3, 0(%4)
	call	getInt
	sw  	a0, -28(s0)
	la  	%6, p
	sw  	%5, 0(%6)
	call	getInt
	sw  	a0, -36(s0)
	la  	%8, k
	sw  	%7, 0(%8)
	la  	%10, p
	lw  	%9, 0(%10)
	la  	%12, k
	lw  	%11, 0(%12)
	sub 	%13, %9, %11
	li  	%15, 1
	slt 	%14, %15, %13
	mv  	%14, %16
	mv  	%16, %17
	bnez	%17, main_if.then1
	j    	main_if.end1
main_if.then1:
	lw  	a0, -69(s0)
	call	print
	j    	main_if.end1
main_if.end1:
	la  	%20, p
	lw  	%19, 0(%20)
	la  	%22, k
	lw  	%21, 0(%22)
	sub 	%23, %19, %21
	la  	%24, i
	sw  	%23, 0(%24)
	j    	main_for.begin2
main_for.begin2:
	la  	%26, i
	lw  	%25, 0(%26)
	la  	%28, p
	lw  	%27, 0(%28)
	la  	%30, k
	lw  	%29, 0(%30)
	add 	%31, %27, %29
	slt 	%32, %31, %25
	xori	%32, %32, 1
	mv  	%32, %33
	mv  	%33, %34
	bnez	%34, main_for.body2
	j    	main_for.end2
main_for.body2:
	la  	%36, i
	lw  	%35, 0(%36)
	li  	%38, 1
	slt 	%37, %35, %38
	xori	%37, %37, 1
	mv  	%37, %39
	sb  	%39, -16(s0)
	bnez	%40, main_and.rhs3
	j    	main_and.end3
main_and.rhs3:
	la  	%42, i
	lw  	%41, 0(%42)
	la  	%44, n
	lw  	%43, 0(%44)
	slt 	%45, %43, %41
	xori	%45, %45, 1
	mv  	%45, %46
	sb  	%46, -16(s0)
	mv  	%39, %40
	j    	main_and.end3
main_and.end3:
	lbu  	%47, -16(s0)
	mv  	%47, %48
	bnez	%48, main_if.then4
	j    	main_if.end4
main_if.then4:
	la  	%50, i
	lw  	%49, 0(%50)
	la  	%52, p
	lw  	%51, 0(%52)
	xor 	%53, %49, %51
	seqz	%53, %53
	mv  	%53, %54
	mv  	%54, %55
	bnez	%55, main_if.then5
	j    	main_if.else5
main_if.then5:
	lw  	a0, -174(s0)
	call	print
	la  	%58, i
	lw  	%57, 0(%58)
	lw  	a0, -178(s0)
	call	toString
	sw  	a0, -186(s0)
	lw  	a0, -186(s0)
	call	print
	lw  	a0, -186(s0)
	call	print
	j    	main_if.end5
main_if.else5:
	la  	%62, i
	lw  	%61, 0(%62)
	lw  	a0, -190(s0)
	call	printInt
	lw  	a0, -198(s0)
	call	print
	j    	main_if.end5
main_if.end5:
	j    	main_if.end4
main_if.end4:
	la  	%65, i
	lw  	%64, 0(%65)
	li  	%67, 1
	add 	%66, %64, %67
	la  	%68, i
	sw  	%66, 0(%68)
	j    	main_for.begin2
main_for.end2:
	la  	%70, p
	lw  	%69, 0(%70)
	la  	%72, k
	lw  	%71, 0(%72)
	add 	%73, %69, %71
	la  	%75, n
	lw  	%74, 0(%75)
	slt 	%76, %73, %74
	mv  	%76, %77
	mv  	%77, %78
	bnez	%78, main_if.then6
	j    	main_if.end6
main_if.then6:
	lw  	a0, -251(s0)
	call	print
	j    	main_if.end6
main_if.end6:
	li  	%80, 0
	sw  	%80, -12(s0)
	j    	main_return
main_return:
	lw  	%81, -12(s0)
	lw  	a0, -259(s0)
	ret
	lw  	s0, 271(sp)
	lw  	ra, 267(sp)
	addi	sp, sp, 263
.str:
       .asciz    "<< "
.str.1:
       .asciz    "("
.str.2:
       .asciz    ") "
.str.3:
       .asciz    " "
.str.4:
       .asciz    ">> "
n:
       .word    0
p:
       .word    0
k:
       .word    0
i:
       .word    0
