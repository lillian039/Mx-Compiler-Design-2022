	.text
	.globl	__mx_initialize_globalVar
__mx_initialize_globalVar:
	addi	sp, sp, -12
	sw  	ra, 8(sp)
	sw  	s0, 4(sp)
	addi	s0, sp, 12
	lw  	s0, 4(sp)
	lw  	ra, 8(sp)
	addi	sp, sp, 12
	ret
	.globl	init
init:
	addi	sp, sp, -28
	sw  	ra, 24(sp)
	sw  	s0, 20(sp)
	addi	s0, sp, 28
	lw  	t0, -16(s0)
	addi	t0, s0, -12
	sw  	t0, -16(s0)
	call	getInt
	lw  	t0, -20(s0)
	mv  	t0, a0
	sw  	t0, -20(s0)
	lw  	t0, -24(s0)
	la  	t0, n
	sw  	t0, -24(s0)
	lw  	t0, -20(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -20(s0)
	j    	init_return
init_return:
	lw  	s0, 20(sp)
	lw  	ra, 24(sp)
	addi	sp, sp, 28
	ret
	.globl	is_prime
is_prime:
	addi	sp, sp, -156
	sw  	ra, 152(sp)
	sw  	s0, 148(sp)
	addi	s0, sp, 156
	lw  	t0, -12(s0)
	mv  	t0, a0
	sw  	t0, -12(s0)
	lw  	t0, -20(s0)
	addi	t0, s0, -16
	sw  	t0, -20(s0)
	lw  	t0, -28(s0)
	addi	t0, s0, -24
	sw  	t0, -28(s0)
	lw  	t0, -36(s0)
	addi	t0, s0, -32
	sw  	t0, -36(s0)
	lw  	t0, -44(s0)
	addi	t0, s0, -40
	sw  	t0, -44(s0)
	lw  	t0, -12(s0)
	lw  	t2, -36(s0)
	sw  	t0, 0(t2)
	sw  	t0, -12(s0)
	lw  	t0, -48(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -48(s0)
	lw  	t0, -56(s0)
	li  	t0, 1
	sw  	t0, -56(s0)
	lw  	t0, -52(s0)
	lw  	t1, -48(s0)
	lw  	t2, -56(s0)
	xor 	t0, t1, t2
	sw  	t0, -52(s0)
	lw  	t0, -52(s0)
	lw  	t1, -52(s0)
	seqz	t0, t1
	sw  	t0, -52(s0)
	lw  	t0, -60(s0)
	lw  	t1, -52(s0)
	mv  	t0, t1
	sw  	t0, -60(s0)
	lw  	t0, -64(s0)
	lw  	t1, -60(s0)
	mv  	t0, t1
	sw  	t0, -64(s0)
	lw  	t1, -64(s0)
	bnez	t1, is_prime_if.then1
	j    	is_prime_if.end1
is_prime_if.then1:
	lw  	t0, -68(s0)
	li  	t0, 0
	sw  	t0, -68(s0)
	lw  	t0, -68(s0)
	lw  	t2, -20(s0)
	sw  	t0, 0(t2)
	sw  	t0, -68(s0)
	j    	is_prime_return
is_prime_if.end1:
	lw  	t0, -72(s0)
	li  	t0, 2
	sw  	t0, -72(s0)
	lw  	t0, -72(s0)
	lw  	t2, -44(s0)
	sw  	t0, 0(t2)
	sw  	t0, -72(s0)
	j    	is_prime_for.begin2
is_prime_for.begin2:
	lw  	t0, -76(s0)
	lw  	t2, -44(s0)
	lw  	t0, 0(t2)
	sw  	t0, -76(s0)
	lw  	t0, -80(s0)
	lw  	t2, -44(s0)
	lw  	t0, 0(t2)
	sw  	t0, -80(s0)
	lw  	t0, -84(s0)
	lw  	t1, -76(s0)
	lw  	t2, -80(s0)
	mul 	t0, t1, t2
	sw  	t0, -84(s0)
	lw  	t0, -88(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -88(s0)
	lw  	t0, -92(s0)
	lw  	t1, -88(s0)
	lw  	t2, -84(s0)
	slt 	t0, t1, t2
	sw  	t0, -92(s0)
	lw  	t0, -92(s0)
	lw  	t1, -92(s0)
	xori	t0, t1, 1
	sw  	t0, -92(s0)
	lw  	t0, -96(s0)
	lw  	t1, -92(s0)
	mv  	t0, t1
	sw  	t0, -96(s0)
	lw  	t0, -100(s0)
	lw  	t1, -96(s0)
	mv  	t0, t1
	sw  	t0, -100(s0)
	lw  	t1, -100(s0)
	bnez	t1, is_prime_for.body2
	j    	is_prime_for.end2
is_prime_for.body2:
	lw  	t0, -104(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -104(s0)
	lw  	t0, -108(s0)
	lw  	t2, -44(s0)
	lw  	t0, 0(t2)
	sw  	t0, -108(s0)
	lw  	t0, -112(s0)
	lw  	t1, -104(s0)
	lw  	t2, -108(s0)
	rem 	t0, t1, t2
	sw  	t0, -112(s0)
	lw  	t0, -120(s0)
	li  	t0, 0
	sw  	t0, -120(s0)
	lw  	t0, -116(s0)
	lw  	t1, -112(s0)
	lw  	t2, -120(s0)
	xor 	t0, t1, t2
	sw  	t0, -116(s0)
	lw  	t0, -116(s0)
	lw  	t1, -116(s0)
	seqz	t0, t1
	sw  	t0, -116(s0)
	lw  	t0, -124(s0)
	lw  	t1, -116(s0)
	mv  	t0, t1
	sw  	t0, -124(s0)
	lw  	t0, -128(s0)
	lw  	t1, -124(s0)
	mv  	t0, t1
	sw  	t0, -128(s0)
	lw  	t1, -128(s0)
	bnez	t1, is_prime_if.then3
	j    	is_prime_if.end3
is_prime_if.then3:
	lw  	t0, -132(s0)
	li  	t0, 0
	sw  	t0, -132(s0)
	lw  	t0, -132(s0)
	lw  	t2, -20(s0)
	sw  	t0, 0(t2)
	sw  	t0, -132(s0)
	j    	is_prime_return
is_prime_if.end3:
	lw  	t0, -136(s0)
	lw  	t2, -44(s0)
	lw  	t0, 0(t2)
	sw  	t0, -136(s0)
	lw  	t0, -144(s0)
	li  	t0, 1
	sw  	t0, -144(s0)
	lw  	t0, -140(s0)
	lw  	t1, -136(s0)
	lw  	t2, -144(s0)
	add 	t0, t1, t2
	sw  	t0, -140(s0)
	lw  	t0, -140(s0)
	lw  	t2, -44(s0)
	sw  	t0, 0(t2)
	sw  	t0, -140(s0)
	j    	is_prime_for.begin2
is_prime_for.end2:
	lw  	t0, -148(s0)
	li  	t0, 1
	sw  	t0, -148(s0)
	lw  	t0, -148(s0)
	lw  	t2, -20(s0)
	sw  	t0, 0(t2)
	sw  	t0, -148(s0)
	j    	is_prime_return
is_prime_return:
	lw  	t0, -152(s0)
	lw  	t2, -20(s0)
	lw  	t0, 0(t2)
	sw  	t0, -152(s0)
	lw  	t1, -152(s0)
	mv  	a0, t1
	lw  	s0, 148(sp)
	lw  	ra, 152(sp)
	addi	sp, sp, 156
	ret
	.globl	find
find:
	addi	sp, sp, -356
	sw  	ra, 352(sp)
	sw  	s0, 348(sp)
	addi	s0, sp, 356
	lw  	t0, -12(s0)
	mv  	t0, a0
	sw  	t0, -12(s0)
	lw  	t0, -16(s0)
	mv  	t0, a1
	sw  	t0, -16(s0)
	lw  	t0, -24(s0)
	addi	t0, s0, -20
	sw  	t0, -24(s0)
	lw  	t0, -32(s0)
	addi	t0, s0, -28
	sw  	t0, -32(s0)
	lw  	t0, -40(s0)
	addi	t0, s0, -36
	sw  	t0, -40(s0)
	lw  	t0, -48(s0)
	addi	t0, s0, -44
	sw  	t0, -48(s0)
	lw  	t0, -56(s0)
	addi	t0, s0, -52
	sw  	t0, -56(s0)
	lw  	t0, -12(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -12(s0)
	lw  	t0, -16(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -16(s0)
	lw  	t0, -60(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -60(s0)
	lw  	t1, -60(s0)
	mv  	a0, t1
	call	is_prime
	lw  	t0, -64(s0)
	mv  	t0, a0
	sw  	t0, -64(s0)
	lw  	t0, -68(s0)
	lw  	t1, -64(s0)
	mv  	t0, t1
	sw  	t0, -68(s0)
	lw  	t1, -68(s0)
	bnez	t1, find_if.then1
	j    	find_if.end1
find_if.then1:
	lw  	t0, -72(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -72(s0)
	lw  	t0, -80(s0)
	li  	t0, 0
	sw  	t0, -80(s0)
	lw  	t0, -76(s0)
	lw  	t1, -80(s0)
	lw  	t2, -72(s0)
	slt 	t0, t1, t2
	sw  	t0, -76(s0)
	lw  	t0, -84(s0)
	lw  	t1, -76(s0)
	mv  	t0, t1
	sw  	t0, -84(s0)
	lw  	t0, -88(s0)
	lw  	t1, -84(s0)
	mv  	t0, t1
	sw  	t0, -88(s0)
	lw  	t1, -88(s0)
	bnez	t1, find_if.then2
	j    	find_if.else2
find_if.then2:
	lw  	t0, -92(s0)
	la  	t0, .str
	sw  	t0, -92(s0)
	lw  	t1, -92(s0)
	mv  	a0, t1
	call	println
	lw  	t0, -96(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -96(s0)
	lw  	t1, -96(s0)
	mv  	a0, t1
	call	toString
	lw  	t0, -100(s0)
	mv  	t0, a0
	sw  	t0, -100(s0)
	lw  	t1, -100(s0)
	mv  	a0, t1
	lw  	t0, -104(s0)
	la  	t0, .str.1
	sw  	t0, -104(s0)
	lw  	t1, -104(s0)
	mv  	a1, t1
	call	___str_add
	lw  	t0, -108(s0)
	mv  	t0, a0
	sw  	t0, -108(s0)
	lw  	t0, -112(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -112(s0)
	lw  	t1, -112(s0)
	mv  	a0, t1
	call	toString
	lw  	t0, -116(s0)
	mv  	t0, a0
	sw  	t0, -116(s0)
	lw  	t1, -108(s0)
	mv  	a0, t1
	lw  	t1, -116(s0)
	mv  	a1, t1
	call	___str_add
	lw  	t0, -120(s0)
	mv  	t0, a0
	sw  	t0, -120(s0)
	lw  	t1, -120(s0)
	mv  	a0, t1
	call	println
	j    	find_if.end2
find_if.else2:
	lw  	t0, -124(s0)
	la  	t0, .str.2
	sw  	t0, -124(s0)
	lw  	t1, -124(s0)
	mv  	a0, t1
	call	println
	lw  	t0, -128(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -128(s0)
	lw  	t1, -128(s0)
	mv  	a0, t1
	call	toString
	lw  	t0, -132(s0)
	mv  	t0, a0
	sw  	t0, -132(s0)
	lw  	t1, -132(s0)
	mv  	a0, t1
	call	println
	j    	find_if.end2
find_if.end2:
	j    	find_return
find_if.end1:
	lw  	t0, -136(s0)
	li  	t0, 5
	sw  	t0, -136(s0)
	lw  	t0, -136(s0)
	lw  	t2, -48(s0)
	sw  	t0, 0(t2)
	sw  	t0, -136(s0)
	lw  	t0, -140(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -140(s0)
	lw  	t0, -148(s0)
	li  	t0, 0
	sw  	t0, -148(s0)
	lw  	t0, -152(s0)
	li  	t0, 1
	sw  	t0, -152(s0)
	lw  	t0, -144(s0)
	lw  	t1, -148(s0)
	lw  	t2, -152(s0)
	sub 	t0, t1, t2
	sw  	t0, -144(s0)
	lw  	t0, -156(s0)
	lw  	t1, -140(s0)
	lw  	t2, -144(s0)
	xor 	t0, t1, t2
	sw  	t0, -156(s0)
	lw  	t0, -156(s0)
	lw  	t1, -156(s0)
	seqz	t0, t1
	sw  	t0, -156(s0)
	lw  	t0, -160(s0)
	lw  	t1, -156(s0)
	mv  	t0, t1
	sw  	t0, -160(s0)
	lw  	t0, -164(s0)
	lw  	t1, -160(s0)
	mv  	t0, t1
	sw  	t0, -164(s0)
	lw  	t1, -164(s0)
	bnez	t1, find_if.then3
	j    	find_if.else3
find_if.then3:
	lw  	t0, -168(s0)
	li  	t0, 6
	sw  	t0, -168(s0)
	lw  	t0, -168(s0)
	lw  	t2, -56(s0)
	sw  	t0, 0(t2)
	sw  	t0, -168(s0)
	lw  	t0, -172(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -172(s0)
	lw  	t0, -180(s0)
	li  	t0, 2
	sw  	t0, -180(s0)
	lw  	t0, -176(s0)
	lw  	t1, -172(s0)
	lw  	t2, -180(s0)
	sub 	t0, t1, t2
	sw  	t0, -176(s0)
	lw  	t0, -176(s0)
	lw  	t2, -56(s0)
	sw  	t0, 0(t2)
	sw  	t0, -176(s0)
	j    	find_for.begin4
find_for.begin4:
	j    	find_for.body4
find_for.body4:
	lw  	t0, -184(s0)
	lw  	t2, -56(s0)
	lw  	t0, 0(t2)
	sw  	t0, -184(s0)
	lw  	t1, -184(s0)
	mv  	a0, t1
	call	is_prime
	lw  	t0, -188(s0)
	mv  	t0, a0
	sw  	t0, -188(s0)
	lw  	t0, -192(s0)
	lw  	t1, -188(s0)
	mv  	t0, t1
	sw  	t0, -192(s0)
	lw  	t1, -192(s0)
	bnez	t1, find_if.then5
	j    	find_if.end5
find_if.then5:
	lw  	t0, -196(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -196(s0)
	lw  	t0, -200(s0)
	lw  	t2, -56(s0)
	lw  	t0, 0(t2)
	sw  	t0, -200(s0)
	lw  	t0, -204(s0)
	lw  	t1, -196(s0)
	lw  	t2, -200(s0)
	sub 	t0, t1, t2
	sw  	t0, -204(s0)
	lw  	t0, -208(s0)
	lw  	t2, -56(s0)
	lw  	t0, 0(t2)
	sw  	t0, -208(s0)
	lw  	t1, -204(s0)
	mv  	a0, t1
	lw  	t1, -208(s0)
	mv  	a1, t1
	call	find
	j    	find_return
find_if.end5:
	lw  	t0, -212(s0)
	lw  	t2, -56(s0)
	lw  	t0, 0(t2)
	sw  	t0, -212(s0)
	lw  	t0, -220(s0)
	li  	t0, 1
	sw  	t0, -220(s0)
	lw  	t0, -216(s0)
	lw  	t1, -212(s0)
	lw  	t2, -220(s0)
	sub 	t0, t1, t2
	sw  	t0, -216(s0)
	lw  	t0, -216(s0)
	lw  	t2, -56(s0)
	sw  	t0, 0(t2)
	sw  	t0, -216(s0)
	j    	find_for.begin4
find_for.end4:
	j    	find_if.end3
find_if.else3:
	lw  	t0, -224(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -224(s0)
	lw  	t0, -232(s0)
	li  	t0, 1
	sw  	t0, -232(s0)
	lw  	t0, -228(s0)
	lw  	t1, -224(s0)
	lw  	t2, -232(s0)
	sub 	t0, t1, t2
	sw  	t0, -228(s0)
	lw  	t0, -228(s0)
	lw  	t2, -48(s0)
	sw  	t0, 0(t2)
	sw  	t0, -228(s0)
	j    	find_for.begin6
find_for.begin6:
	j    	find_for.body6
find_for.body6:
	lw  	t0, -236(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -236(s0)
	lw  	t0, -236(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -236(s0)
	lw  	t0, -240(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -240(s0)
	lw  	t1, -240(s0)
	mv  	a0, t1
	call	is_prime
	lw  	t0, -244(s0)
	mv  	t0, a0
	sw  	t0, -244(s0)
	lw  	t0, -244(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -244(s0)
	lw  	t0, -248(s0)
	lw  	t1, -244(s0)
	mv  	t0, t1
	sw  	t0, -248(s0)
	lw  	t1, -248(s0)
	bnez	t1, find_and.rhs7
	j    	find_and.end7
find_and.rhs7:
	lw  	t0, -252(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -252(s0)
	lw  	t0, -252(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -252(s0)
	lw  	t0, -256(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -256(s0)
	lw  	t0, -260(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -260(s0)
	lw  	t0, -260(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -260(s0)
	lw  	t0, -264(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -264(s0)
	lw  	t0, -268(s0)
	lw  	t1, -256(s0)
	lw  	t2, -264(s0)
	sub 	t0, t1, t2
	sw  	t0, -268(s0)
	lw  	t1, -268(s0)
	mv  	a0, t1
	call	is_prime
	lw  	t0, -272(s0)
	mv  	t0, a0
	sw  	t0, -272(s0)
	lw  	t0, -272(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -272(s0)
	j    	find_and.end7
find_and.end7:
	lw  	t0, -276(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -276(s0)
	lw  	t0, -280(s0)
	lw  	t1, -276(s0)
	mv  	t0, t1
	sw  	t0, -280(s0)
	lw  	t1, -280(s0)
	bnez	t1, find_if.then8
	j    	find_if.end8
find_if.then8:
	lw  	t0, -284(s0)
	la  	t0, .str.3
	sw  	t0, -284(s0)
	lw  	t1, -284(s0)
	mv  	a0, t1
	call	println
	lw  	t0, -288(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -288(s0)
	lw  	t1, -288(s0)
	mv  	a0, t1
	call	toString
	lw  	t0, -292(s0)
	mv  	t0, a0
	sw  	t0, -292(s0)
	lw  	t1, -292(s0)
	mv  	a0, t1
	lw  	t0, -296(s0)
	la  	t0, .str.1
	sw  	t0, -296(s0)
	lw  	t1, -296(s0)
	mv  	a1, t1
	call	___str_add
	lw  	t0, -300(s0)
	mv  	t0, a0
	sw  	t0, -300(s0)
	lw  	t0, -304(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -304(s0)
	lw  	t1, -304(s0)
	mv  	a0, t1
	call	toString
	lw  	t0, -308(s0)
	mv  	t0, a0
	sw  	t0, -308(s0)
	lw  	t1, -300(s0)
	mv  	a0, t1
	lw  	t1, -308(s0)
	mv  	a1, t1
	call	___str_add
	lw  	t0, -312(s0)
	mv  	t0, a0
	sw  	t0, -312(s0)
	lw  	t1, -312(s0)
	mv  	a0, t1
	lw  	t0, -316(s0)
	la  	t0, .str.1
	sw  	t0, -316(s0)
	lw  	t1, -316(s0)
	mv  	a1, t1
	call	___str_add
	lw  	t0, -320(s0)
	mv  	t0, a0
	sw  	t0, -320(s0)
	lw  	t0, -324(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -324(s0)
	lw  	t0, -328(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -328(s0)
	lw  	t0, -332(s0)
	lw  	t1, -324(s0)
	lw  	t2, -328(s0)
	sub 	t0, t1, t2
	sw  	t0, -332(s0)
	lw  	t1, -332(s0)
	mv  	a0, t1
	call	toString
	lw  	t0, -336(s0)
	mv  	t0, a0
	sw  	t0, -336(s0)
	lw  	t1, -320(s0)
	mv  	a0, t1
	lw  	t1, -336(s0)
	mv  	a1, t1
	call	___str_add
	lw  	t0, -340(s0)
	mv  	t0, a0
	sw  	t0, -340(s0)
	lw  	t1, -340(s0)
	mv  	a0, t1
	call	println
	j    	find_return
find_if.end8:
	lw  	t0, -344(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -344(s0)
	lw  	t0, -352(s0)
	li  	t0, 1
	sw  	t0, -352(s0)
	lw  	t0, -348(s0)
	lw  	t1, -344(s0)
	lw  	t2, -352(s0)
	sub 	t0, t1, t2
	sw  	t0, -348(s0)
	lw  	t0, -348(s0)
	lw  	t2, -48(s0)
	sw  	t0, 0(t2)
	sw  	t0, -348(s0)
	j    	find_for.begin6
find_for.end6:
	j    	find_if.end3
find_if.end3:
	j    	find_return
find_return:
	lw  	s0, 348(sp)
	lw  	ra, 352(sp)
	addi	sp, sp, 356
	ret
	.globl	work
work:
	addi	sp, sp, -40
	sw  	ra, 36(sp)
	sw  	s0, 32(sp)
	addi	s0, sp, 40
	lw  	t0, -16(s0)
	addi	t0, s0, -12
	sw  	t0, -16(s0)
	lw  	t0, -24(s0)
	la  	t0, n
	sw  	t0, -24(s0)
	lw  	t0, -20(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -20(s0)
	lw  	t0, -32(s0)
	li  	t0, 0
	sw  	t0, -32(s0)
	lw  	t0, -36(s0)
	li  	t0, 1
	sw  	t0, -36(s0)
	lw  	t0, -28(s0)
	lw  	t1, -32(s0)
	lw  	t2, -36(s0)
	sub 	t0, t1, t2
	sw  	t0, -28(s0)
	lw  	t1, -20(s0)
	mv  	a0, t1
	lw  	t1, -28(s0)
	mv  	a1, t1
	call	find
	j    	work_return
work_return:
	lw  	s0, 32(sp)
	lw  	ra, 36(sp)
	addi	sp, sp, 40
	ret
	.globl	main
main:
	addi	sp, sp, -36
	sw  	ra, 32(sp)
	sw  	s0, 28(sp)
	addi	s0, sp, 36
	lw  	t0, -16(s0)
	addi	t0, s0, -12
	sw  	t0, -16(s0)
	lw  	t0, -24(s0)
	addi	t0, s0, -20
	sw  	t0, -24(s0)
	call	__mx_initialize_globalVar
	call	init
	call	work
	lw  	t0, -28(s0)
	li  	t0, 0
	sw  	t0, -28(s0)
	lw  	t0, -28(s0)
	lw  	t2, -16(s0)
	sw  	t0, 0(t2)
	sw  	t0, -28(s0)
	j    	main_return
main_return:
	lw  	t0, -32(s0)
	lw  	t2, -16(s0)
	lw  	t0, 0(t2)
	sw  	t0, -32(s0)
	lw  	t1, -32(s0)
	mv  	a0, t1
	lw  	s0, 28(sp)
	lw  	ra, 32(sp)
	addi	sp, sp, 36
	ret
	.section	.data
	.globl	n
n:
	.word	0
	.section	.rodata
	.globl	.str
.str:
	.asciz	"2"
	.section	.rodata
	.globl	.str.1
.str.1:
	.asciz	" "
	.section	.rodata
	.globl	.str.2
.str.2:
	.asciz	"1"
	.section	.rodata
	.globl	.str.3
.str.3:
	.asciz	"3"
