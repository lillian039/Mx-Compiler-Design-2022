	.text
	.globl	__mx_initialize_globalVar
__mx_initialize_globalVar:
	addi	sp, sp, -36
	sw  	ra, 32(sp)
	sw  	s0, 28(sp)
	addi	s0, sp, 36
	lw  	t0, -12(s0)
	li  	t0, 48271
	sw  	t0, -12(s0)
	lw  	t0, -16(s0)
	la  	t0, A
	sw  	t0, -16(s0)
	lw  	t0, -12(s0)
	lw  	t2, -16(s0)
	sw  	t0, 0(t2)
	sw  	t0, -12(s0)
	lw  	t0, -20(s0)
	li  	t0, 2147483647
	sw  	t0, -20(s0)
	lw  	t0, -24(s0)
	la  	t0, M
	sw  	t0, -24(s0)
	lw  	t0, -20(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -20(s0)
	lw  	t0, -28(s0)
	li  	t0, 1
	sw  	t0, -28(s0)
	lw  	t0, -32(s0)
	la  	t0, seed
	sw  	t0, -32(s0)
	lw  	t0, -28(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -28(s0)
	lw  	s0, 28(sp)
	lw  	ra, 32(sp)
	addi	sp, sp, 36
	ret
	.globl	random
random:
	addi	sp, sp, -164
	sw  	ra, 160(sp)
	sw  	s0, 156(sp)
	addi	s0, sp, 164
	lw  	t0, -16(s0)
	addi	t0, s0, -12
	sw  	t0, -16(s0)
	lw  	t0, -24(s0)
	addi	t0, s0, -20
	sw  	t0, -24(s0)
	lw  	t0, -32(s0)
	addi	t0, s0, -28
	sw  	t0, -32(s0)
	lw  	t0, -40(s0)
	la  	t0, A
	sw  	t0, -40(s0)
	lw  	t0, -36(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -36(s0)
	lw  	t0, -48(s0)
	la  	t0, seed
	sw  	t0, -48(s0)
	lw  	t0, -44(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -44(s0)
	lw  	t0, -56(s0)
	la  	t0, Q
	sw  	t0, -56(s0)
	lw  	t0, -52(s0)
	lw  	t2, -56(s0)
	lw  	t0, 0(t2)
	sw  	t0, -52(s0)
	lw  	t0, -60(s0)
	lw  	t1, -44(s0)
	lw  	t2, -52(s0)
	rem 	t0, t1, t2
	sw  	t0, -60(s0)
	lw  	t0, -64(s0)
	lw  	t1, -36(s0)
	lw  	t2, -60(s0)
	mul 	t0, t1, t2
	sw  	t0, -64(s0)
	lw  	t0, -72(s0)
	la  	t0, R
	sw  	t0, -72(s0)
	lw  	t0, -68(s0)
	lw  	t2, -72(s0)
	lw  	t0, 0(t2)
	sw  	t0, -68(s0)
	lw  	t0, -80(s0)
	la  	t0, seed
	sw  	t0, -80(s0)
	lw  	t0, -76(s0)
	lw  	t2, -80(s0)
	lw  	t0, 0(t2)
	sw  	t0, -76(s0)
	lw  	t0, -88(s0)
	la  	t0, Q
	sw  	t0, -88(s0)
	lw  	t0, -84(s0)
	lw  	t2, -88(s0)
	lw  	t0, 0(t2)
	sw  	t0, -84(s0)
	lw  	t0, -92(s0)
	lw  	t1, -76(s0)
	lw  	t2, -84(s0)
	div 	t0, t1, t2
	sw  	t0, -92(s0)
	lw  	t0, -96(s0)
	lw  	t1, -68(s0)
	lw  	t2, -92(s0)
	mul 	t0, t1, t2
	sw  	t0, -96(s0)
	lw  	t0, -100(s0)
	lw  	t1, -64(s0)
	lw  	t2, -96(s0)
	sub 	t0, t1, t2
	sw  	t0, -100(s0)
	lw  	t0, -100(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -100(s0)
	lw  	t0, -104(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -104(s0)
	lw  	t0, -112(s0)
	li  	t0, 0
	sw  	t0, -112(s0)
	lw  	t0, -108(s0)
	lw  	t1, -104(s0)
	lw  	t2, -112(s0)
	slt 	t0, t1, t2
	sw  	t0, -108(s0)
	lw  	t0, -108(s0)
	lw  	t1, -108(s0)
	xori	t0, t1, 1
	sw  	t0, -108(s0)
	lw  	t0, -116(s0)
	lw  	t1, -108(s0)
	mv  	t0, t1
	sw  	t0, -116(s0)
	lw  	t0, -120(s0)
	lw  	t1, -116(s0)
	mv  	t0, t1
	sw  	t0, -120(s0)
	lw  	t1, -120(s0)
	bnez	t1, random_if.then1
	j    	random_if.else1
random_if.then1:
	lw  	t0, -124(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -124(s0)
	lw  	t0, -128(s0)
	la  	t0, seed
	sw  	t0, -128(s0)
	lw  	t0, -124(s0)
	lw  	t2, -128(s0)
	sw  	t0, 0(t2)
	sw  	t0, -124(s0)
	j    	random_if.end1
random_if.else1:
	lw  	t0, -132(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -132(s0)
	lw  	t0, -140(s0)
	la  	t0, M
	sw  	t0, -140(s0)
	lw  	t0, -136(s0)
	lw  	t2, -140(s0)
	lw  	t0, 0(t2)
	sw  	t0, -136(s0)
	lw  	t0, -144(s0)
	lw  	t1, -132(s0)
	lw  	t2, -136(s0)
	add 	t0, t1, t2
	sw  	t0, -144(s0)
	lw  	t0, -148(s0)
	la  	t0, seed
	sw  	t0, -148(s0)
	lw  	t0, -144(s0)
	lw  	t2, -148(s0)
	sw  	t0, 0(t2)
	sw  	t0, -144(s0)
	j    	random_if.end1
random_if.end1:
	lw  	t0, -156(s0)
	la  	t0, seed
	sw  	t0, -156(s0)
	lw  	t0, -152(s0)
	lw  	t2, -156(s0)
	lw  	t0, 0(t2)
	sw  	t0, -152(s0)
	lw  	t0, -152(s0)
	lw  	t2, -16(s0)
	sw  	t0, 0(t2)
	sw  	t0, -152(s0)
	j    	random_return
random_return:
	lw  	t0, -160(s0)
	lw  	t2, -16(s0)
	lw  	t0, 0(t2)
	sw  	t0, -160(s0)
	lw  	t1, -160(s0)
	mv  	a0, t1
	lw  	s0, 156(sp)
	lw  	ra, 160(sp)
	addi	sp, sp, 164
	ret
	.globl	initialize
initialize:
	addi	sp, sp, -40
	sw  	ra, 36(sp)
	sw  	s0, 32(sp)
	addi	s0, sp, 40
	lw  	t0, -12(s0)
	mv  	t0, a0
	sw  	t0, -12(s0)
	lw  	t0, -20(s0)
	addi	t0, s0, -16
	sw  	t0, -20(s0)
	lw  	t0, -28(s0)
	addi	t0, s0, -24
	sw  	t0, -28(s0)
	lw  	t0, -12(s0)
	lw  	t2, -28(s0)
	sw  	t0, 0(t2)
	sw  	t0, -12(s0)
	lw  	t0, -32(s0)
	lw  	t2, -28(s0)
	lw  	t0, 0(t2)
	sw  	t0, -32(s0)
	lw  	t0, -36(s0)
	la  	t0, seed
	sw  	t0, -36(s0)
	lw  	t0, -32(s0)
	lw  	t2, -36(s0)
	sw  	t0, 0(t2)
	sw  	t0, -32(s0)
	j    	initialize_return
initialize_return:
	lw  	s0, 32(sp)
	lw  	ra, 36(sp)
	addi	sp, sp, 40
	ret
	.globl	swap
swap:
	addi	sp, sp, -160
	sw  	ra, 156(sp)
	sw  	s0, 152(sp)
	addi	s0, sp, 160
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
	lw  	t0, -12(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -12(s0)
	lw  	t0, -16(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -16(s0)
	lw  	t0, -56(s0)
	la  	t0, a
	sw  	t0, -56(s0)
	lw  	t0, -52(s0)
	lw  	t2, -56(s0)
	lw  	t0, 0(t2)
	sw  	t0, -52(s0)
	lw  	t0, -60(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -60(s0)
	lw  	t0, -72(s0)
	li  	t0, 4
	sw  	t0, -72(s0)
	lw  	t0, -68(s0)
	lw  	t1, -60(s0)
	lw  	t2, -72(s0)
	mul 	t0, t1, t2
	sw  	t0, -68(s0)
	lw  	t0, -64(s0)
	lw  	t1, -68(s0)
	lw  	t2, -52(s0)
	add 	t0, t1, t2
	sw  	t0, -64(s0)
	lw  	t0, -76(s0)
	lw  	t2, -64(s0)
	lw  	t0, 0(t2)
	sw  	t0, -76(s0)
	lw  	t0, -76(s0)
	lw  	t2, -48(s0)
	sw  	t0, 0(t2)
	sw  	t0, -76(s0)
	lw  	t0, -84(s0)
	la  	t0, a
	sw  	t0, -84(s0)
	lw  	t0, -80(s0)
	lw  	t2, -84(s0)
	lw  	t0, 0(t2)
	sw  	t0, -80(s0)
	lw  	t0, -88(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -88(s0)
	lw  	t0, -100(s0)
	li  	t0, 4
	sw  	t0, -100(s0)
	lw  	t0, -96(s0)
	lw  	t1, -88(s0)
	lw  	t2, -100(s0)
	mul 	t0, t1, t2
	sw  	t0, -96(s0)
	lw  	t0, -92(s0)
	lw  	t1, -96(s0)
	lw  	t2, -80(s0)
	add 	t0, t1, t2
	sw  	t0, -92(s0)
	lw  	t0, -108(s0)
	la  	t0, a
	sw  	t0, -108(s0)
	lw  	t0, -104(s0)
	lw  	t2, -108(s0)
	lw  	t0, 0(t2)
	sw  	t0, -104(s0)
	lw  	t0, -112(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -112(s0)
	lw  	t0, -124(s0)
	li  	t0, 4
	sw  	t0, -124(s0)
	lw  	t0, -120(s0)
	lw  	t1, -112(s0)
	lw  	t2, -124(s0)
	mul 	t0, t1, t2
	sw  	t0, -120(s0)
	lw  	t0, -116(s0)
	lw  	t1, -120(s0)
	lw  	t2, -104(s0)
	add 	t0, t1, t2
	sw  	t0, -116(s0)
	lw  	t0, -128(s0)
	lw  	t2, -116(s0)
	lw  	t0, 0(t2)
	sw  	t0, -128(s0)
	lw  	t0, -128(s0)
	lw  	t2, -92(s0)
	sw  	t0, 0(t2)
	sw  	t0, -128(s0)
	lw  	t0, -136(s0)
	la  	t0, a
	sw  	t0, -136(s0)
	lw  	t0, -132(s0)
	lw  	t2, -136(s0)
	lw  	t0, 0(t2)
	sw  	t0, -132(s0)
	lw  	t0, -140(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -140(s0)
	lw  	t0, -152(s0)
	li  	t0, 4
	sw  	t0, -152(s0)
	lw  	t0, -148(s0)
	lw  	t1, -140(s0)
	lw  	t2, -152(s0)
	mul 	t0, t1, t2
	sw  	t0, -148(s0)
	lw  	t0, -144(s0)
	lw  	t1, -148(s0)
	lw  	t2, -132(s0)
	add 	t0, t1, t2
	sw  	t0, -144(s0)
	lw  	t0, -156(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -156(s0)
	lw  	t0, -156(s0)
	lw  	t2, -144(s0)
	sw  	t0, 0(t2)
	sw  	t0, -156(s0)
	j    	swap_return
swap_return:
	lw  	s0, 152(sp)
	lw  	ra, 156(sp)
	addi	sp, sp, 160
	ret
	.globl	pd
pd:
	addi	sp, sp, -148
	sw  	ra, 144(sp)
	sw  	s0, 140(sp)
	addi	s0, sp, 148
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
	lw  	t0, -12(s0)
	lw  	t2, -36(s0)
	sw  	t0, 0(t2)
	sw  	t0, -12(s0)
	j    	pd_for.begin1
pd_for.begin1:
	lw  	t0, -44(s0)
	la  	t0, h
	sw  	t0, -44(s0)
	lw  	t0, -40(s0)
	lw  	t2, -44(s0)
	lw  	t0, 0(t2)
	sw  	t0, -40(s0)
	lw  	t0, -48(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -48(s0)
	lw  	t0, -52(s0)
	lw  	t1, -48(s0)
	lw  	t2, -40(s0)
	slt 	t0, t1, t2
	sw  	t0, -52(s0)
	lw  	t0, -52(s0)
	lw  	t1, -52(s0)
	xori	t0, t1, 1
	sw  	t0, -52(s0)
	lw  	t0, -56(s0)
	lw  	t1, -52(s0)
	mv  	t0, t1
	sw  	t0, -56(s0)
	lw  	t0, -60(s0)
	lw  	t1, -56(s0)
	mv  	t0, t1
	sw  	t0, -60(s0)
	lw  	t1, -60(s0)
	bnez	t1, pd_for.body1
	j    	pd_for.end1
pd_for.body1:
	lw  	t0, -64(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -64(s0)
	lw  	t0, -72(s0)
	la  	t0, h
	sw  	t0, -72(s0)
	lw  	t0, -68(s0)
	lw  	t2, -72(s0)
	lw  	t0, 0(t2)
	sw  	t0, -68(s0)
	lw  	t0, -80(s0)
	la  	t0, h
	sw  	t0, -80(s0)
	lw  	t0, -76(s0)
	lw  	t2, -80(s0)
	lw  	t0, 0(t2)
	sw  	t0, -76(s0)
	lw  	t0, -88(s0)
	li  	t0, 1
	sw  	t0, -88(s0)
	lw  	t0, -84(s0)
	lw  	t1, -76(s0)
	lw  	t2, -88(s0)
	add 	t0, t1, t2
	sw  	t0, -84(s0)
	lw  	t0, -92(s0)
	lw  	t1, -68(s0)
	lw  	t2, -84(s0)
	mul 	t0, t1, t2
	sw  	t0, -92(s0)
	lw  	t0, -100(s0)
	li  	t0, 2
	sw  	t0, -100(s0)
	lw  	t0, -96(s0)
	lw  	t1, -92(s0)
	lw  	t2, -100(s0)
	div 	t0, t1, t2
	sw  	t0, -96(s0)
	lw  	t0, -104(s0)
	lw  	t1, -64(s0)
	lw  	t2, -96(s0)
	xor 	t0, t1, t2
	sw  	t0, -104(s0)
	lw  	t0, -104(s0)
	lw  	t1, -104(s0)
	seqz	t0, t1
	sw  	t0, -104(s0)
	lw  	t0, -108(s0)
	lw  	t1, -104(s0)
	mv  	t0, t1
	sw  	t0, -108(s0)
	lw  	t0, -112(s0)
	lw  	t1, -108(s0)
	mv  	t0, t1
	sw  	t0, -112(s0)
	lw  	t1, -112(s0)
	bnez	t1, pd_if.then2
	j    	pd_if.end2
pd_if.then2:
	lw  	t0, -116(s0)
	li  	t0, 1
	sw  	t0, -116(s0)
	lw  	t0, -116(s0)
	lw  	t2, -20(s0)
	sw  	t0, 0(t2)
	sw  	t0, -116(s0)
	j    	pd_return
pd_if.end2:
	lw  	t0, -124(s0)
	la  	t0, h
	sw  	t0, -124(s0)
	lw  	t0, -120(s0)
	lw  	t2, -124(s0)
	lw  	t0, 0(t2)
	sw  	t0, -120(s0)
	lw  	t0, -132(s0)
	li  	t0, 1
	sw  	t0, -132(s0)
	lw  	t0, -128(s0)
	lw  	t1, -120(s0)
	lw  	t2, -132(s0)
	add 	t0, t1, t2
	sw  	t0, -128(s0)
	lw  	t0, -136(s0)
	la  	t0, h
	sw  	t0, -136(s0)
	lw  	t0, -128(s0)
	lw  	t2, -136(s0)
	sw  	t0, 0(t2)
	sw  	t0, -128(s0)
	j    	pd_for.begin1
pd_for.end1:
	lw  	t0, -140(s0)
	li  	t0, 0
	sw  	t0, -140(s0)
	lw  	t0, -140(s0)
	lw  	t2, -20(s0)
	sw  	t0, 0(t2)
	sw  	t0, -140(s0)
	j    	pd_return
pd_return:
	lw  	t0, -144(s0)
	lw  	t2, -20(s0)
	lw  	t0, 0(t2)
	sw  	t0, -144(s0)
	lw  	t1, -144(s0)
	mv  	a0, t1
	lw  	s0, 140(sp)
	lw  	ra, 144(sp)
	addi	sp, sp, 148
	ret
	.globl	show
show:
	addi	sp, sp, -112
	sw  	ra, 108(sp)
	sw  	s0, 104(sp)
	addi	s0, sp, 112
	lw  	t0, -16(s0)
	addi	t0, s0, -12
	sw  	t0, -16(s0)
	lw  	t0, -24(s0)
	addi	t0, s0, -20
	sw  	t0, -24(s0)
	lw  	t0, -28(s0)
	li  	t0, 0
	sw  	t0, -28(s0)
	lw  	t0, -28(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -28(s0)
	j    	show_for.begin1
show_for.begin1:
	lw  	t0, -32(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -32(s0)
	lw  	t0, -40(s0)
	la  	t0, now
	sw  	t0, -40(s0)
	lw  	t0, -36(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -36(s0)
	lw  	t0, -44(s0)
	lw  	t1, -32(s0)
	lw  	t2, -36(s0)
	slt 	t0, t1, t2
	sw  	t0, -44(s0)
	lw  	t0, -48(s0)
	lw  	t1, -44(s0)
	mv  	t0, t1
	sw  	t0, -48(s0)
	lw  	t0, -52(s0)
	lw  	t1, -48(s0)
	mv  	t0, t1
	sw  	t0, -52(s0)
	lw  	t1, -52(s0)
	bnez	t1, show_for.body1
	j    	show_for.end1
show_for.body1:
	lw  	t0, -60(s0)
	la  	t0, a
	sw  	t0, -60(s0)
	lw  	t0, -56(s0)
	lw  	t2, -60(s0)
	lw  	t0, 0(t2)
	sw  	t0, -56(s0)
	lw  	t0, -64(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -64(s0)
	lw  	t0, -76(s0)
	li  	t0, 4
	sw  	t0, -76(s0)
	lw  	t0, -72(s0)
	lw  	t1, -64(s0)
	lw  	t2, -76(s0)
	mul 	t0, t1, t2
	sw  	t0, -72(s0)
	lw  	t0, -68(s0)
	lw  	t1, -72(s0)
	lw  	t2, -56(s0)
	add 	t0, t1, t2
	sw  	t0, -68(s0)
	lw  	t0, -80(s0)
	lw  	t2, -68(s0)
	lw  	t0, 0(t2)
	sw  	t0, -80(s0)
	lw  	t1, -80(s0)
	mv  	a0, t1
	call	toString
	lw  	t0, -84(s0)
	mv  	t0, a0
	sw  	t0, -84(s0)
	lw  	t1, -84(s0)
	mv  	a0, t1
	lw  	t0, -88(s0)
	la  	t0, .str.6
	sw  	t0, -88(s0)
	lw  	t1, -88(s0)
	mv  	a1, t1
	call	___str_add
	lw  	t0, -92(s0)
	mv  	t0, a0
	sw  	t0, -92(s0)
	lw  	t1, -92(s0)
	mv  	a0, t1
	call	print
	lw  	t0, -96(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -96(s0)
	lw  	t0, -104(s0)
	li  	t0, 1
	sw  	t0, -104(s0)
	lw  	t0, -100(s0)
	lw  	t1, -96(s0)
	lw  	t2, -104(s0)
	add 	t0, t1, t2
	sw  	t0, -100(s0)
	lw  	t0, -100(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -100(s0)
	j    	show_for.begin1
show_for.end1:
	lw  	t0, -108(s0)
	la  	t0, .str.7
	sw  	t0, -108(s0)
	lw  	t1, -108(s0)
	mv  	a0, t1
	call	println
	j    	show_return
show_return:
	lw  	s0, 104(sp)
	lw  	ra, 108(sp)
	addi	sp, sp, 112
	ret
	.globl	win
win:
	addi	sp, sp, -572
	sw  	ra, 568(sp)
	sw  	s0, 564(sp)
	addi	s0, sp, 572
	lw  	t0, -16(s0)
	addi	t0, s0, -12
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
	lw  	t0, -64(s0)
	li  	t0, 100
	sw  	t0, -64(s0)
	lw  	t0, -68(s0)
	li  	t0, 4
	sw  	t0, -68(s0)
	lw  	t0, -60(s0)
	lw  	t1, -64(s0)
	lw  	t2, -68(s0)
	mul 	t0, t1, t2
	sw  	t0, -60(s0)
	lw  	t0, -76(s0)
	li  	t0, 4
	sw  	t0, -76(s0)
	lw  	t0, -72(s0)
	lw  	t1, -60(s0)
	lw  	t2, -76(s0)
	add 	t0, t1, t2
	sw  	t0, -72(s0)
	lw  	t1, -72(s0)
	mv  	a0, t1
	call	__malloc
	lw  	t0, -80(s0)
	mv  	t0, a0
	sw  	t0, -80(s0)
	lw  	t0, -84(s0)
	lw  	t1, -80(s0)
	mv  	t0, t1
	sw  	t0, -84(s0)
	lw  	t0, -88(s0)
	li  	t0, 100
	sw  	t0, -88(s0)
	lw  	t0, -88(s0)
	lw  	t2, -84(s0)
	sw  	t0, 0(t2)
	sw  	t0, -88(s0)
	lw  	t0, -92(s0)
	lw  	t1, -80(s0)
	mv  	t0, t1
	sw  	t0, -92(s0)
	lw  	t0, -100(s0)
	li  	t0, 4
	sw  	t0, -100(s0)
	lw  	t0, -96(s0)
	lw  	t1, -100(s0)
	lw  	t2, -92(s0)
	add 	t0, t1, t2
	sw  	t0, -96(s0)
	lw  	t0, -96(s0)
	lw  	t2, -48(s0)
	sw  	t0, 0(t2)
	sw  	t0, -96(s0)
	lw  	t0, -108(s0)
	la  	t0, now
	sw  	t0, -108(s0)
	lw  	t0, -104(s0)
	lw  	t2, -108(s0)
	lw  	t0, 0(t2)
	sw  	t0, -104(s0)
	lw  	t0, -116(s0)
	la  	t0, h
	sw  	t0, -116(s0)
	lw  	t0, -112(s0)
	lw  	t2, -116(s0)
	lw  	t0, 0(t2)
	sw  	t0, -112(s0)
	lw  	t0, -120(s0)
	lw  	t1, -104(s0)
	lw  	t2, -112(s0)
	xor 	t0, t1, t2
	sw  	t0, -120(s0)
	lw  	t0, -120(s0)
	lw  	t1, -120(s0)
	snez	t0, t1
	sw  	t0, -120(s0)
	lw  	t0, -124(s0)
	lw  	t1, -120(s0)
	mv  	t0, t1
	sw  	t0, -124(s0)
	lw  	t0, -128(s0)
	lw  	t1, -124(s0)
	mv  	t0, t1
	sw  	t0, -128(s0)
	lw  	t1, -128(s0)
	bnez	t1, win_if.then1
	j    	win_if.end1
win_if.then1:
	lw  	t0, -132(s0)
	li  	t0, 0
	sw  	t0, -132(s0)
	lw  	t0, -132(s0)
	lw  	t2, -16(s0)
	sw  	t0, 0(t2)
	sw  	t0, -132(s0)
	j    	win_return
win_if.end1:
	lw  	t0, -136(s0)
	li  	t0, 0
	sw  	t0, -136(s0)
	lw  	t0, -136(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -136(s0)
	j    	win_for.begin2
win_for.begin2:
	lw  	t0, -140(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -140(s0)
	lw  	t0, -148(s0)
	la  	t0, now
	sw  	t0, -148(s0)
	lw  	t0, -144(s0)
	lw  	t2, -148(s0)
	lw  	t0, 0(t2)
	sw  	t0, -144(s0)
	lw  	t0, -152(s0)
	lw  	t1, -140(s0)
	lw  	t2, -144(s0)
	slt 	t0, t1, t2
	sw  	t0, -152(s0)
	lw  	t0, -156(s0)
	lw  	t1, -152(s0)
	mv  	t0, t1
	sw  	t0, -156(s0)
	lw  	t0, -160(s0)
	lw  	t1, -156(s0)
	mv  	t0, t1
	sw  	t0, -160(s0)
	lw  	t1, -160(s0)
	bnez	t1, win_for.body2
	j    	win_for.end2
win_for.body2:
	lw  	t0, -164(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -164(s0)
	lw  	t0, -168(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -168(s0)
	lw  	t0, -180(s0)
	li  	t0, 4
	sw  	t0, -180(s0)
	lw  	t0, -176(s0)
	lw  	t1, -168(s0)
	lw  	t2, -180(s0)
	mul 	t0, t1, t2
	sw  	t0, -176(s0)
	lw  	t0, -172(s0)
	lw  	t1, -176(s0)
	lw  	t2, -164(s0)
	add 	t0, t1, t2
	sw  	t0, -172(s0)
	lw  	t0, -188(s0)
	la  	t0, a
	sw  	t0, -188(s0)
	lw  	t0, -184(s0)
	lw  	t2, -188(s0)
	lw  	t0, 0(t2)
	sw  	t0, -184(s0)
	lw  	t0, -192(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -192(s0)
	lw  	t0, -204(s0)
	li  	t0, 4
	sw  	t0, -204(s0)
	lw  	t0, -200(s0)
	lw  	t1, -192(s0)
	lw  	t2, -204(s0)
	mul 	t0, t1, t2
	sw  	t0, -200(s0)
	lw  	t0, -196(s0)
	lw  	t1, -200(s0)
	lw  	t2, -184(s0)
	add 	t0, t1, t2
	sw  	t0, -196(s0)
	lw  	t0, -208(s0)
	lw  	t2, -196(s0)
	lw  	t0, 0(t2)
	sw  	t0, -208(s0)
	lw  	t0, -208(s0)
	lw  	t2, -172(s0)
	sw  	t0, 0(t2)
	sw  	t0, -208(s0)
	lw  	t0, -212(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -212(s0)
	lw  	t0, -220(s0)
	li  	t0, 1
	sw  	t0, -220(s0)
	lw  	t0, -216(s0)
	lw  	t1, -212(s0)
	lw  	t2, -220(s0)
	add 	t0, t1, t2
	sw  	t0, -216(s0)
	lw  	t0, -216(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -216(s0)
	j    	win_for.begin2
win_for.end2:
	lw  	t0, -224(s0)
	li  	t0, 0
	sw  	t0, -224(s0)
	lw  	t0, -224(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -224(s0)
	j    	win_for.begin3
win_for.begin3:
	lw  	t0, -228(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -228(s0)
	lw  	t0, -236(s0)
	la  	t0, now
	sw  	t0, -236(s0)
	lw  	t0, -232(s0)
	lw  	t2, -236(s0)
	lw  	t0, 0(t2)
	sw  	t0, -232(s0)
	lw  	t0, -244(s0)
	li  	t0, 1
	sw  	t0, -244(s0)
	lw  	t0, -240(s0)
	lw  	t1, -232(s0)
	lw  	t2, -244(s0)
	sub 	t0, t1, t2
	sw  	t0, -240(s0)
	lw  	t0, -248(s0)
	lw  	t1, -228(s0)
	lw  	t2, -240(s0)
	slt 	t0, t1, t2
	sw  	t0, -248(s0)
	lw  	t0, -252(s0)
	lw  	t1, -248(s0)
	mv  	t0, t1
	sw  	t0, -252(s0)
	lw  	t0, -256(s0)
	lw  	t1, -252(s0)
	mv  	t0, t1
	sw  	t0, -256(s0)
	lw  	t1, -256(s0)
	bnez	t1, win_for.body3
	j    	win_for.end3
win_for.body3:
	lw  	t0, -260(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -260(s0)
	lw  	t0, -268(s0)
	li  	t0, 1
	sw  	t0, -268(s0)
	lw  	t0, -264(s0)
	lw  	t1, -260(s0)
	lw  	t2, -268(s0)
	add 	t0, t1, t2
	sw  	t0, -264(s0)
	lw  	t0, -264(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -264(s0)
	j    	win_for.begin4
win_for.begin4:
	lw  	t0, -272(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -272(s0)
	lw  	t0, -280(s0)
	la  	t0, now
	sw  	t0, -280(s0)
	lw  	t0, -276(s0)
	lw  	t2, -280(s0)
	lw  	t0, 0(t2)
	sw  	t0, -276(s0)
	lw  	t0, -284(s0)
	lw  	t1, -272(s0)
	lw  	t2, -276(s0)
	slt 	t0, t1, t2
	sw  	t0, -284(s0)
	lw  	t0, -288(s0)
	lw  	t1, -284(s0)
	mv  	t0, t1
	sw  	t0, -288(s0)
	lw  	t0, -292(s0)
	lw  	t1, -288(s0)
	mv  	t0, t1
	sw  	t0, -292(s0)
	lw  	t1, -292(s0)
	bnez	t1, win_for.body4
	j    	win_for.end4
win_for.body4:
	lw  	t0, -296(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -296(s0)
	lw  	t0, -300(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -300(s0)
	lw  	t0, -312(s0)
	li  	t0, 4
	sw  	t0, -312(s0)
	lw  	t0, -308(s0)
	lw  	t1, -300(s0)
	lw  	t2, -312(s0)
	mul 	t0, t1, t2
	sw  	t0, -308(s0)
	lw  	t0, -304(s0)
	lw  	t1, -308(s0)
	lw  	t2, -296(s0)
	add 	t0, t1, t2
	sw  	t0, -304(s0)
	lw  	t0, -316(s0)
	lw  	t2, -304(s0)
	lw  	t0, 0(t2)
	sw  	t0, -316(s0)
	lw  	t0, -320(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -320(s0)
	lw  	t0, -324(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -324(s0)
	lw  	t0, -336(s0)
	li  	t0, 4
	sw  	t0, -336(s0)
	lw  	t0, -332(s0)
	lw  	t1, -324(s0)
	lw  	t2, -336(s0)
	mul 	t0, t1, t2
	sw  	t0, -332(s0)
	lw  	t0, -328(s0)
	lw  	t1, -332(s0)
	lw  	t2, -320(s0)
	add 	t0, t1, t2
	sw  	t0, -328(s0)
	lw  	t0, -340(s0)
	lw  	t2, -328(s0)
	lw  	t0, 0(t2)
	sw  	t0, -340(s0)
	lw  	t0, -344(s0)
	lw  	t1, -340(s0)
	lw  	t2, -316(s0)
	slt 	t0, t1, t2
	sw  	t0, -344(s0)
	lw  	t0, -348(s0)
	lw  	t1, -344(s0)
	mv  	t0, t1
	sw  	t0, -348(s0)
	lw  	t0, -352(s0)
	lw  	t1, -348(s0)
	mv  	t0, t1
	sw  	t0, -352(s0)
	lw  	t1, -352(s0)
	bnez	t1, win_if.then5
	j    	win_if.end5
win_if.then5:
	lw  	t0, -356(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -356(s0)
	lw  	t0, -360(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -360(s0)
	lw  	t0, -372(s0)
	li  	t0, 4
	sw  	t0, -372(s0)
	lw  	t0, -368(s0)
	lw  	t1, -360(s0)
	lw  	t2, -372(s0)
	mul 	t0, t1, t2
	sw  	t0, -368(s0)
	lw  	t0, -364(s0)
	lw  	t1, -368(s0)
	lw  	t2, -356(s0)
	add 	t0, t1, t2
	sw  	t0, -364(s0)
	lw  	t0, -376(s0)
	lw  	t2, -364(s0)
	lw  	t0, 0(t2)
	sw  	t0, -376(s0)
	lw  	t0, -376(s0)
	lw  	t2, -56(s0)
	sw  	t0, 0(t2)
	sw  	t0, -376(s0)
	lw  	t0, -380(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -380(s0)
	lw  	t0, -384(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -384(s0)
	lw  	t0, -396(s0)
	li  	t0, 4
	sw  	t0, -396(s0)
	lw  	t0, -392(s0)
	lw  	t1, -384(s0)
	lw  	t2, -396(s0)
	mul 	t0, t1, t2
	sw  	t0, -392(s0)
	lw  	t0, -388(s0)
	lw  	t1, -392(s0)
	lw  	t2, -380(s0)
	add 	t0, t1, t2
	sw  	t0, -388(s0)
	lw  	t0, -400(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -400(s0)
	lw  	t0, -404(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -404(s0)
	lw  	t0, -416(s0)
	li  	t0, 4
	sw  	t0, -416(s0)
	lw  	t0, -412(s0)
	lw  	t1, -404(s0)
	lw  	t2, -416(s0)
	mul 	t0, t1, t2
	sw  	t0, -412(s0)
	lw  	t0, -408(s0)
	lw  	t1, -412(s0)
	lw  	t2, -400(s0)
	add 	t0, t1, t2
	sw  	t0, -408(s0)
	lw  	t0, -420(s0)
	lw  	t2, -408(s0)
	lw  	t0, 0(t2)
	sw  	t0, -420(s0)
	lw  	t0, -420(s0)
	lw  	t2, -388(s0)
	sw  	t0, 0(t2)
	sw  	t0, -420(s0)
	lw  	t0, -424(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -424(s0)
	lw  	t0, -428(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -428(s0)
	lw  	t0, -440(s0)
	li  	t0, 4
	sw  	t0, -440(s0)
	lw  	t0, -436(s0)
	lw  	t1, -428(s0)
	lw  	t2, -440(s0)
	mul 	t0, t1, t2
	sw  	t0, -436(s0)
	lw  	t0, -432(s0)
	lw  	t1, -436(s0)
	lw  	t2, -424(s0)
	add 	t0, t1, t2
	sw  	t0, -432(s0)
	lw  	t0, -444(s0)
	lw  	t2, -56(s0)
	lw  	t0, 0(t2)
	sw  	t0, -444(s0)
	lw  	t0, -444(s0)
	lw  	t2, -432(s0)
	sw  	t0, 0(t2)
	sw  	t0, -444(s0)
	j    	win_if.end5
win_if.end5:
	lw  	t0, -448(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -448(s0)
	lw  	t0, -456(s0)
	li  	t0, 1
	sw  	t0, -456(s0)
	lw  	t0, -452(s0)
	lw  	t1, -448(s0)
	lw  	t2, -456(s0)
	add 	t0, t1, t2
	sw  	t0, -452(s0)
	lw  	t0, -452(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -452(s0)
	j    	win_for.begin4
win_for.end4:
	lw  	t0, -460(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -460(s0)
	lw  	t0, -468(s0)
	li  	t0, 1
	sw  	t0, -468(s0)
	lw  	t0, -464(s0)
	lw  	t1, -460(s0)
	lw  	t2, -468(s0)
	add 	t0, t1, t2
	sw  	t0, -464(s0)
	lw  	t0, -464(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -464(s0)
	j    	win_for.begin3
win_for.end3:
	lw  	t0, -472(s0)
	li  	t0, 0
	sw  	t0, -472(s0)
	lw  	t0, -472(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -472(s0)
	j    	win_for.begin6
win_for.begin6:
	lw  	t0, -476(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -476(s0)
	lw  	t0, -484(s0)
	la  	t0, now
	sw  	t0, -484(s0)
	lw  	t0, -480(s0)
	lw  	t2, -484(s0)
	lw  	t0, 0(t2)
	sw  	t0, -480(s0)
	lw  	t0, -488(s0)
	lw  	t1, -476(s0)
	lw  	t2, -480(s0)
	slt 	t0, t1, t2
	sw  	t0, -488(s0)
	lw  	t0, -492(s0)
	lw  	t1, -488(s0)
	mv  	t0, t1
	sw  	t0, -492(s0)
	lw  	t0, -496(s0)
	lw  	t1, -492(s0)
	mv  	t0, t1
	sw  	t0, -496(s0)
	lw  	t1, -496(s0)
	bnez	t1, win_for.body6
	j    	win_for.end6
win_for.body6:
	lw  	t0, -500(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -500(s0)
	lw  	t0, -504(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -504(s0)
	lw  	t0, -516(s0)
	li  	t0, 4
	sw  	t0, -516(s0)
	lw  	t0, -512(s0)
	lw  	t1, -504(s0)
	lw  	t2, -516(s0)
	mul 	t0, t1, t2
	sw  	t0, -512(s0)
	lw  	t0, -508(s0)
	lw  	t1, -512(s0)
	lw  	t2, -500(s0)
	add 	t0, t1, t2
	sw  	t0, -508(s0)
	lw  	t0, -520(s0)
	lw  	t2, -508(s0)
	lw  	t0, 0(t2)
	sw  	t0, -520(s0)
	lw  	t0, -524(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -524(s0)
	lw  	t0, -532(s0)
	li  	t0, 1
	sw  	t0, -532(s0)
	lw  	t0, -528(s0)
	lw  	t1, -524(s0)
	lw  	t2, -532(s0)
	add 	t0, t1, t2
	sw  	t0, -528(s0)
	lw  	t0, -536(s0)
	lw  	t1, -520(s0)
	lw  	t2, -528(s0)
	xor 	t0, t1, t2
	sw  	t0, -536(s0)
	lw  	t0, -536(s0)
	lw  	t1, -536(s0)
	snez	t0, t1
	sw  	t0, -536(s0)
	lw  	t0, -540(s0)
	lw  	t1, -536(s0)
	mv  	t0, t1
	sw  	t0, -540(s0)
	lw  	t0, -544(s0)
	lw  	t1, -540(s0)
	mv  	t0, t1
	sw  	t0, -544(s0)
	lw  	t1, -544(s0)
	bnez	t1, win_if.then7
	j    	win_if.end7
win_if.then7:
	lw  	t0, -548(s0)
	li  	t0, 0
	sw  	t0, -548(s0)
	lw  	t0, -548(s0)
	lw  	t2, -16(s0)
	sw  	t0, 0(t2)
	sw  	t0, -548(s0)
	j    	win_return
win_if.end7:
	lw  	t0, -552(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -552(s0)
	lw  	t0, -560(s0)
	li  	t0, 1
	sw  	t0, -560(s0)
	lw  	t0, -556(s0)
	lw  	t1, -552(s0)
	lw  	t2, -560(s0)
	add 	t0, t1, t2
	sw  	t0, -556(s0)
	lw  	t0, -556(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -556(s0)
	j    	win_for.begin6
win_for.end6:
	lw  	t0, -564(s0)
	li  	t0, 1
	sw  	t0, -564(s0)
	lw  	t0, -564(s0)
	lw  	t2, -16(s0)
	sw  	t0, 0(t2)
	sw  	t0, -564(s0)
	j    	win_return
win_return:
	lw  	t0, -568(s0)
	lw  	t2, -16(s0)
	lw  	t0, 0(t2)
	sw  	t0, -568(s0)
	lw  	t1, -568(s0)
	mv  	a0, t1
	lw  	s0, 564(sp)
	lw  	ra, 568(sp)
	addi	sp, sp, 572
	ret
	.globl	merge
merge:
	addi	sp, sp, -312
	sw  	ra, 308(sp)
	sw  	s0, 304(sp)
	addi	s0, sp, 312
	lw  	t0, -16(s0)
	addi	t0, s0, -12
	sw  	t0, -16(s0)
	lw  	t0, -24(s0)
	addi	t0, s0, -20
	sw  	t0, -24(s0)
	lw  	t0, -32(s0)
	addi	t0, s0, -28
	sw  	t0, -32(s0)
	lw  	t0, -36(s0)
	li  	t0, 0
	sw  	t0, -36(s0)
	lw  	t0, -36(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -36(s0)
	j    	merge_for.begin1
merge_for.begin1:
	lw  	t0, -40(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -40(s0)
	lw  	t0, -48(s0)
	la  	t0, now
	sw  	t0, -48(s0)
	lw  	t0, -44(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -44(s0)
	lw  	t0, -52(s0)
	lw  	t1, -40(s0)
	lw  	t2, -44(s0)
	slt 	t0, t1, t2
	sw  	t0, -52(s0)
	lw  	t0, -56(s0)
	lw  	t1, -52(s0)
	mv  	t0, t1
	sw  	t0, -56(s0)
	lw  	t0, -60(s0)
	lw  	t1, -56(s0)
	mv  	t0, t1
	sw  	t0, -60(s0)
	lw  	t1, -60(s0)
	bnez	t1, merge_for.body1
	j    	merge_for.end1
merge_for.body1:
	lw  	t0, -68(s0)
	la  	t0, a
	sw  	t0, -68(s0)
	lw  	t0, -64(s0)
	lw  	t2, -68(s0)
	lw  	t0, 0(t2)
	sw  	t0, -64(s0)
	lw  	t0, -72(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -72(s0)
	lw  	t0, -84(s0)
	li  	t0, 4
	sw  	t0, -84(s0)
	lw  	t0, -80(s0)
	lw  	t1, -72(s0)
	lw  	t2, -84(s0)
	mul 	t0, t1, t2
	sw  	t0, -80(s0)
	lw  	t0, -76(s0)
	lw  	t1, -80(s0)
	lw  	t2, -64(s0)
	add 	t0, t1, t2
	sw  	t0, -76(s0)
	lw  	t0, -88(s0)
	lw  	t2, -76(s0)
	lw  	t0, 0(t2)
	sw  	t0, -88(s0)
	lw  	t0, -96(s0)
	li  	t0, 0
	sw  	t0, -96(s0)
	lw  	t0, -92(s0)
	lw  	t1, -88(s0)
	lw  	t2, -96(s0)
	xor 	t0, t1, t2
	sw  	t0, -92(s0)
	lw  	t0, -92(s0)
	lw  	t1, -92(s0)
	seqz	t0, t1
	sw  	t0, -92(s0)
	lw  	t0, -100(s0)
	lw  	t1, -92(s0)
	mv  	t0, t1
	sw  	t0, -100(s0)
	lw  	t0, -104(s0)
	lw  	t1, -100(s0)
	mv  	t0, t1
	sw  	t0, -104(s0)
	lw  	t1, -104(s0)
	bnez	t1, merge_if.then2
	j    	merge_if.end2
merge_if.then2:
	lw  	t0, -108(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -108(s0)
	lw  	t0, -116(s0)
	li  	t0, 1
	sw  	t0, -116(s0)
	lw  	t0, -112(s0)
	lw  	t1, -108(s0)
	lw  	t2, -116(s0)
	add 	t0, t1, t2
	sw  	t0, -112(s0)
	lw  	t0, -112(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -112(s0)
	j    	merge_for.begin3
merge_for.begin3:
	lw  	t0, -120(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -120(s0)
	lw  	t0, -128(s0)
	la  	t0, now
	sw  	t0, -128(s0)
	lw  	t0, -124(s0)
	lw  	t2, -128(s0)
	lw  	t0, 0(t2)
	sw  	t0, -124(s0)
	lw  	t0, -132(s0)
	lw  	t1, -120(s0)
	lw  	t2, -124(s0)
	slt 	t0, t1, t2
	sw  	t0, -132(s0)
	lw  	t0, -136(s0)
	lw  	t1, -132(s0)
	mv  	t0, t1
	sw  	t0, -136(s0)
	lw  	t0, -140(s0)
	lw  	t1, -136(s0)
	mv  	t0, t1
	sw  	t0, -140(s0)
	lw  	t1, -140(s0)
	bnez	t1, merge_for.body3
	j    	merge_for.end3
merge_for.body3:
	lw  	t0, -148(s0)
	la  	t0, a
	sw  	t0, -148(s0)
	lw  	t0, -144(s0)
	lw  	t2, -148(s0)
	lw  	t0, 0(t2)
	sw  	t0, -144(s0)
	lw  	t0, -152(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -152(s0)
	lw  	t0, -164(s0)
	li  	t0, 4
	sw  	t0, -164(s0)
	lw  	t0, -160(s0)
	lw  	t1, -152(s0)
	lw  	t2, -164(s0)
	mul 	t0, t1, t2
	sw  	t0, -160(s0)
	lw  	t0, -156(s0)
	lw  	t1, -160(s0)
	lw  	t2, -144(s0)
	add 	t0, t1, t2
	sw  	t0, -156(s0)
	lw  	t0, -168(s0)
	lw  	t2, -156(s0)
	lw  	t0, 0(t2)
	sw  	t0, -168(s0)
	lw  	t0, -176(s0)
	li  	t0, 0
	sw  	t0, -176(s0)
	lw  	t0, -172(s0)
	lw  	t1, -168(s0)
	lw  	t2, -176(s0)
	xor 	t0, t1, t2
	sw  	t0, -172(s0)
	lw  	t0, -172(s0)
	lw  	t1, -172(s0)
	snez	t0, t1
	sw  	t0, -172(s0)
	lw  	t0, -180(s0)
	lw  	t1, -172(s0)
	mv  	t0, t1
	sw  	t0, -180(s0)
	lw  	t0, -184(s0)
	lw  	t1, -180(s0)
	mv  	t0, t1
	sw  	t0, -184(s0)
	lw  	t1, -184(s0)
	bnez	t1, merge_if.then4
	j    	merge_if.end4
merge_if.then4:
	lw  	t0, -188(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -188(s0)
	lw  	t0, -192(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -192(s0)
	lw  	t1, -188(s0)
	mv  	a0, t1
	lw  	t1, -192(s0)
	mv  	a1, t1
	call	swap
	j    	merge_for.end3
merge_if.end4:
	lw  	t0, -196(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -196(s0)
	lw  	t0, -204(s0)
	li  	t0, 1
	sw  	t0, -204(s0)
	lw  	t0, -200(s0)
	lw  	t1, -196(s0)
	lw  	t2, -204(s0)
	add 	t0, t1, t2
	sw  	t0, -200(s0)
	lw  	t0, -200(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -200(s0)
	j    	merge_for.begin3
merge_for.end3:
	j    	merge_if.end2
merge_if.end2:
	lw  	t0, -208(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -208(s0)
	lw  	t0, -216(s0)
	li  	t0, 1
	sw  	t0, -216(s0)
	lw  	t0, -212(s0)
	lw  	t1, -208(s0)
	lw  	t2, -216(s0)
	add 	t0, t1, t2
	sw  	t0, -212(s0)
	lw  	t0, -212(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -212(s0)
	j    	merge_for.begin1
merge_for.end1:
	lw  	t0, -220(s0)
	li  	t0, 0
	sw  	t0, -220(s0)
	lw  	t0, -220(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -220(s0)
	j    	merge_for.begin5
merge_for.begin5:
	lw  	t0, -224(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -224(s0)
	lw  	t0, -232(s0)
	la  	t0, now
	sw  	t0, -232(s0)
	lw  	t0, -228(s0)
	lw  	t2, -232(s0)
	lw  	t0, 0(t2)
	sw  	t0, -228(s0)
	lw  	t0, -236(s0)
	lw  	t1, -224(s0)
	lw  	t2, -228(s0)
	slt 	t0, t1, t2
	sw  	t0, -236(s0)
	lw  	t0, -240(s0)
	lw  	t1, -236(s0)
	mv  	t0, t1
	sw  	t0, -240(s0)
	lw  	t0, -244(s0)
	lw  	t1, -240(s0)
	mv  	t0, t1
	sw  	t0, -244(s0)
	lw  	t1, -244(s0)
	bnez	t1, merge_for.body5
	j    	merge_for.end5
merge_for.body5:
	lw  	t0, -252(s0)
	la  	t0, a
	sw  	t0, -252(s0)
	lw  	t0, -248(s0)
	lw  	t2, -252(s0)
	lw  	t0, 0(t2)
	sw  	t0, -248(s0)
	lw  	t0, -256(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -256(s0)
	lw  	t0, -268(s0)
	li  	t0, 4
	sw  	t0, -268(s0)
	lw  	t0, -264(s0)
	lw  	t1, -256(s0)
	lw  	t2, -268(s0)
	mul 	t0, t1, t2
	sw  	t0, -264(s0)
	lw  	t0, -260(s0)
	lw  	t1, -264(s0)
	lw  	t2, -248(s0)
	add 	t0, t1, t2
	sw  	t0, -260(s0)
	lw  	t0, -272(s0)
	lw  	t2, -260(s0)
	lw  	t0, 0(t2)
	sw  	t0, -272(s0)
	lw  	t0, -280(s0)
	li  	t0, 0
	sw  	t0, -280(s0)
	lw  	t0, -276(s0)
	lw  	t1, -272(s0)
	lw  	t2, -280(s0)
	xor 	t0, t1, t2
	sw  	t0, -276(s0)
	lw  	t0, -276(s0)
	lw  	t1, -276(s0)
	seqz	t0, t1
	sw  	t0, -276(s0)
	lw  	t0, -284(s0)
	lw  	t1, -276(s0)
	mv  	t0, t1
	sw  	t0, -284(s0)
	lw  	t0, -288(s0)
	lw  	t1, -284(s0)
	mv  	t0, t1
	sw  	t0, -288(s0)
	lw  	t1, -288(s0)
	bnez	t1, merge_if.then6
	j    	merge_if.end6
merge_if.then6:
	lw  	t0, -292(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -292(s0)
	lw  	t0, -296(s0)
	la  	t0, now
	sw  	t0, -296(s0)
	lw  	t0, -292(s0)
	lw  	t2, -296(s0)
	sw  	t0, 0(t2)
	sw  	t0, -292(s0)
	j    	merge_for.end5
merge_if.end6:
	lw  	t0, -300(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -300(s0)
	lw  	t0, -308(s0)
	li  	t0, 1
	sw  	t0, -308(s0)
	lw  	t0, -304(s0)
	lw  	t1, -300(s0)
	lw  	t2, -308(s0)
	add 	t0, t1, t2
	sw  	t0, -304(s0)
	lw  	t0, -304(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -304(s0)
	j    	merge_for.begin5
merge_for.end5:
	j    	merge_return
merge_return:
	lw  	s0, 304(sp)
	lw  	ra, 308(sp)
	addi	sp, sp, 312
	ret
	.globl	move
move:
	addi	sp, sp, -160
	sw  	ra, 156(sp)
	sw  	s0, 152(sp)
	addi	s0, sp, 160
	lw  	t0, -16(s0)
	addi	t0, s0, -12
	sw  	t0, -16(s0)
	lw  	t0, -24(s0)
	addi	t0, s0, -20
	sw  	t0, -24(s0)
	lw  	t0, -28(s0)
	li  	t0, 0
	sw  	t0, -28(s0)
	lw  	t0, -28(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -28(s0)
	j    	move_for.begin1
move_for.begin1:
	lw  	t0, -32(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -32(s0)
	lw  	t0, -40(s0)
	la  	t0, now
	sw  	t0, -40(s0)
	lw  	t0, -36(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -36(s0)
	lw  	t0, -44(s0)
	lw  	t1, -32(s0)
	lw  	t2, -36(s0)
	slt 	t0, t1, t2
	sw  	t0, -44(s0)
	lw  	t0, -48(s0)
	lw  	t1, -44(s0)
	mv  	t0, t1
	sw  	t0, -48(s0)
	lw  	t0, -52(s0)
	lw  	t1, -48(s0)
	mv  	t0, t1
	sw  	t0, -52(s0)
	lw  	t1, -52(s0)
	bnez	t1, move_for.body1
	j    	move_for.end1
move_for.body1:
	lw  	t0, -60(s0)
	la  	t0, a
	sw  	t0, -60(s0)
	lw  	t0, -56(s0)
	lw  	t2, -60(s0)
	lw  	t0, 0(t2)
	sw  	t0, -56(s0)
	lw  	t0, -64(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -64(s0)
	lw  	t0, -76(s0)
	li  	t0, 4
	sw  	t0, -76(s0)
	lw  	t0, -72(s0)
	lw  	t1, -64(s0)
	lw  	t2, -76(s0)
	mul 	t0, t1, t2
	sw  	t0, -72(s0)
	lw  	t0, -68(s0)
	lw  	t1, -72(s0)
	lw  	t2, -56(s0)
	add 	t0, t1, t2
	sw  	t0, -68(s0)
	lw  	t0, -80(s0)
	lw  	t2, -68(s0)
	lw  	t0, 0(t2)
	sw  	t0, -80(s0)
	lw  	t0, -88(s0)
	li  	t0, 1
	sw  	t0, -88(s0)
	lw  	t0, -84(s0)
	lw  	t1, -80(s0)
	lw  	t2, -88(s0)
	sub 	t0, t1, t2
	sw  	t0, -84(s0)
	lw  	t0, -84(s0)
	lw  	t2, -68(s0)
	sw  	t0, 0(t2)
	sw  	t0, -84(s0)
	lw  	t0, -92(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -92(s0)
	lw  	t0, -100(s0)
	li  	t0, 1
	sw  	t0, -100(s0)
	lw  	t0, -96(s0)
	lw  	t1, -92(s0)
	lw  	t2, -100(s0)
	add 	t0, t1, t2
	sw  	t0, -96(s0)
	lw  	t0, -96(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -96(s0)
	j    	move_for.begin1
move_for.end1:
	lw  	t0, -108(s0)
	la  	t0, a
	sw  	t0, -108(s0)
	lw  	t0, -104(s0)
	lw  	t2, -108(s0)
	lw  	t0, 0(t2)
	sw  	t0, -104(s0)
	lw  	t0, -116(s0)
	la  	t0, now
	sw  	t0, -116(s0)
	lw  	t0, -112(s0)
	lw  	t2, -116(s0)
	lw  	t0, 0(t2)
	sw  	t0, -112(s0)
	lw  	t0, -128(s0)
	li  	t0, 4
	sw  	t0, -128(s0)
	lw  	t0, -124(s0)
	lw  	t1, -112(s0)
	lw  	t2, -128(s0)
	mul 	t0, t1, t2
	sw  	t0, -124(s0)
	lw  	t0, -120(s0)
	lw  	t1, -124(s0)
	lw  	t2, -104(s0)
	add 	t0, t1, t2
	sw  	t0, -120(s0)
	lw  	t0, -136(s0)
	la  	t0, now
	sw  	t0, -136(s0)
	lw  	t0, -132(s0)
	lw  	t2, -136(s0)
	lw  	t0, 0(t2)
	sw  	t0, -132(s0)
	lw  	t0, -132(s0)
	lw  	t2, -120(s0)
	sw  	t0, 0(t2)
	sw  	t0, -132(s0)
	lw  	t0, -144(s0)
	la  	t0, now
	sw  	t0, -144(s0)
	lw  	t0, -140(s0)
	lw  	t2, -144(s0)
	lw  	t0, 0(t2)
	sw  	t0, -140(s0)
	lw  	t0, -152(s0)
	li  	t0, 1
	sw  	t0, -152(s0)
	lw  	t0, -148(s0)
	lw  	t1, -140(s0)
	lw  	t2, -152(s0)
	add 	t0, t1, t2
	sw  	t0, -148(s0)
	lw  	t0, -156(s0)
	la  	t0, now
	sw  	t0, -156(s0)
	lw  	t0, -148(s0)
	lw  	t2, -156(s0)
	sw  	t0, 0(t2)
	sw  	t0, -148(s0)
	j    	move_return
move_return:
	lw  	s0, 152(sp)
	lw  	ra, 156(sp)
	addi	sp, sp, 160
	ret
	.globl	main
main:
	addi	sp, sp, -644
	sw  	ra, 640(sp)
	sw  	s0, 636(sp)
	addi	s0, sp, 644
	lw  	t0, -16(s0)
	addi	t0, s0, -12
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
	call	__mx_initialize_globalVar
	lw  	t0, -52(s0)
	li  	t0, 0
	sw  	t0, -52(s0)
	lw  	t0, -52(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -52(s0)
	lw  	t0, -56(s0)
	li  	t0, 0
	sw  	t0, -56(s0)
	lw  	t0, -56(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -56(s0)
	lw  	t0, -60(s0)
	li  	t0, 0
	sw  	t0, -60(s0)
	lw  	t0, -60(s0)
	lw  	t2, -48(s0)
	sw  	t0, 0(t2)
	sw  	t0, -60(s0)
	lw  	t0, -68(s0)
	li  	t0, 3
	sw  	t0, -68(s0)
	lw  	t0, -72(s0)
	li  	t0, 7
	sw  	t0, -72(s0)
	lw  	t0, -64(s0)
	lw  	t1, -68(s0)
	lw  	t2, -72(s0)
	mul 	t0, t1, t2
	sw  	t0, -64(s0)
	lw  	t0, -80(s0)
	li  	t0, 10
	sw  	t0, -80(s0)
	lw  	t0, -76(s0)
	lw  	t1, -64(s0)
	lw  	t2, -80(s0)
	mul 	t0, t1, t2
	sw  	t0, -76(s0)
	lw  	t0, -84(s0)
	la  	t0, n
	sw  	t0, -84(s0)
	lw  	t0, -76(s0)
	lw  	t2, -84(s0)
	sw  	t0, 0(t2)
	sw  	t0, -76(s0)
	lw  	t0, -88(s0)
	li  	t0, 0
	sw  	t0, -88(s0)
	lw  	t0, -92(s0)
	la  	t0, h
	sw  	t0, -92(s0)
	lw  	t0, -88(s0)
	lw  	t2, -92(s0)
	sw  	t0, 0(t2)
	sw  	t0, -88(s0)
	lw  	t0, -100(s0)
	li  	t0, 100
	sw  	t0, -100(s0)
	lw  	t0, -104(s0)
	li  	t0, 4
	sw  	t0, -104(s0)
	lw  	t0, -96(s0)
	lw  	t1, -100(s0)
	lw  	t2, -104(s0)
	mul 	t0, t1, t2
	sw  	t0, -96(s0)
	lw  	t0, -112(s0)
	li  	t0, 4
	sw  	t0, -112(s0)
	lw  	t0, -108(s0)
	lw  	t1, -96(s0)
	lw  	t2, -112(s0)
	add 	t0, t1, t2
	sw  	t0, -108(s0)
	lw  	t1, -108(s0)
	mv  	a0, t1
	call	__malloc
	lw  	t0, -116(s0)
	mv  	t0, a0
	sw  	t0, -116(s0)
	lw  	t0, -120(s0)
	lw  	t1, -116(s0)
	mv  	t0, t1
	sw  	t0, -120(s0)
	lw  	t0, -124(s0)
	li  	t0, 100
	sw  	t0, -124(s0)
	lw  	t0, -124(s0)
	lw  	t2, -120(s0)
	sw  	t0, 0(t2)
	sw  	t0, -124(s0)
	lw  	t0, -128(s0)
	lw  	t1, -116(s0)
	mv  	t0, t1
	sw  	t0, -128(s0)
	lw  	t0, -136(s0)
	li  	t0, 4
	sw  	t0, -136(s0)
	lw  	t0, -132(s0)
	lw  	t1, -136(s0)
	lw  	t2, -128(s0)
	add 	t0, t1, t2
	sw  	t0, -132(s0)
	lw  	t0, -140(s0)
	la  	t0, a
	sw  	t0, -140(s0)
	lw  	t0, -132(s0)
	lw  	t2, -140(s0)
	sw  	t0, 0(t2)
	sw  	t0, -132(s0)
	lw  	t0, -148(s0)
	la  	t0, M
	sw  	t0, -148(s0)
	lw  	t0, -144(s0)
	lw  	t2, -148(s0)
	lw  	t0, 0(t2)
	sw  	t0, -144(s0)
	lw  	t0, -156(s0)
	la  	t0, A
	sw  	t0, -156(s0)
	lw  	t0, -152(s0)
	lw  	t2, -156(s0)
	lw  	t0, 0(t2)
	sw  	t0, -152(s0)
	lw  	t0, -160(s0)
	lw  	t1, -144(s0)
	lw  	t2, -152(s0)
	div 	t0, t1, t2
	sw  	t0, -160(s0)
	lw  	t0, -164(s0)
	la  	t0, Q
	sw  	t0, -164(s0)
	lw  	t0, -160(s0)
	lw  	t2, -164(s0)
	sw  	t0, 0(t2)
	sw  	t0, -160(s0)
	lw  	t0, -172(s0)
	la  	t0, M
	sw  	t0, -172(s0)
	lw  	t0, -168(s0)
	lw  	t2, -172(s0)
	lw  	t0, 0(t2)
	sw  	t0, -168(s0)
	lw  	t0, -180(s0)
	la  	t0, A
	sw  	t0, -180(s0)
	lw  	t0, -176(s0)
	lw  	t2, -180(s0)
	lw  	t0, 0(t2)
	sw  	t0, -176(s0)
	lw  	t0, -184(s0)
	lw  	t1, -168(s0)
	lw  	t2, -176(s0)
	rem 	t0, t1, t2
	sw  	t0, -184(s0)
	lw  	t0, -188(s0)
	la  	t0, R
	sw  	t0, -188(s0)
	lw  	t0, -184(s0)
	lw  	t2, -188(s0)
	sw  	t0, 0(t2)
	sw  	t0, -184(s0)
	lw  	t0, -196(s0)
	la  	t0, n
	sw  	t0, -196(s0)
	lw  	t0, -192(s0)
	lw  	t2, -196(s0)
	lw  	t0, 0(t2)
	sw  	t0, -192(s0)
	lw  	t1, -192(s0)
	mv  	a0, t1
	call	pd
	lw  	t0, -200(s0)
	mv  	t0, a0
	sw  	t0, -200(s0)
	lw  	t0, -204(s0)
	lw  	t1, -200(s0)
	mv  	t0, t1
	sw  	t0, -204(s0)
	lw  	t0, -212(s0)
	li  	t0, 1
	sw  	t0, -212(s0)
	lw  	t0, -208(s0)
	lw  	t1, -204(s0)
	lw  	t2, -212(s0)
	xor 	t0, t1, t2
	sw  	t0, -208(s0)
	lw  	t0, -216(s0)
	lw  	t1, -208(s0)
	mv  	t0, t1
	sw  	t0, -216(s0)
	lw  	t0, -220(s0)
	lw  	t1, -216(s0)
	mv  	t0, t1
	sw  	t0, -220(s0)
	lw  	t1, -220(s0)
	bnez	t1, main_if.then1
	j    	main_if.end1
main_if.then1:
	lw  	t0, -224(s0)
	la  	t0, .str
	sw  	t0, -224(s0)
	lw  	t1, -224(s0)
	mv  	a0, t1
	call	println
	lw  	t0, -228(s0)
	li  	t0, 1
	sw  	t0, -228(s0)
	lw  	t0, -228(s0)
	lw  	t2, -16(s0)
	sw  	t0, 0(t2)
	sw  	t0, -228(s0)
	j    	main_return
main_if.end1:
	lw  	t0, -232(s0)
	la  	t0, .str.1
	sw  	t0, -232(s0)
	lw  	t1, -232(s0)
	mv  	a0, t1
	call	println
	lw  	t0, -236(s0)
	li  	t0, 3654898
	sw  	t0, -236(s0)
	lw  	t1, -236(s0)
	mv  	a0, t1
	call	initialize
	call	random
	lw  	t0, -240(s0)
	mv  	t0, a0
	sw  	t0, -240(s0)
	lw  	t0, -248(s0)
	li  	t0, 10
	sw  	t0, -248(s0)
	lw  	t0, -244(s0)
	lw  	t1, -240(s0)
	lw  	t2, -248(s0)
	rem 	t0, t1, t2
	sw  	t0, -244(s0)
	lw  	t0, -256(s0)
	li  	t0, 1
	sw  	t0, -256(s0)
	lw  	t0, -252(s0)
	lw  	t1, -244(s0)
	lw  	t2, -256(s0)
	add 	t0, t1, t2
	sw  	t0, -252(s0)
	lw  	t0, -260(s0)
	la  	t0, now
	sw  	t0, -260(s0)
	lw  	t0, -252(s0)
	lw  	t2, -260(s0)
	sw  	t0, 0(t2)
	sw  	t0, -252(s0)
	lw  	t0, -268(s0)
	la  	t0, now
	sw  	t0, -268(s0)
	lw  	t0, -264(s0)
	lw  	t2, -268(s0)
	lw  	t0, 0(t2)
	sw  	t0, -264(s0)
	lw  	t1, -264(s0)
	mv  	a0, t1
	call	toString
	lw  	t0, -272(s0)
	mv  	t0, a0
	sw  	t0, -272(s0)
	lw  	t1, -272(s0)
	mv  	a0, t1
	call	println
	j    	main_for.begin2
main_for.begin2:
	lw  	t0, -276(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -276(s0)
	lw  	t0, -284(s0)
	la  	t0, now
	sw  	t0, -284(s0)
	lw  	t0, -280(s0)
	lw  	t2, -284(s0)
	lw  	t0, 0(t2)
	sw  	t0, -280(s0)
	lw  	t0, -292(s0)
	li  	t0, 1
	sw  	t0, -292(s0)
	lw  	t0, -288(s0)
	lw  	t1, -280(s0)
	lw  	t2, -292(s0)
	sub 	t0, t1, t2
	sw  	t0, -288(s0)
	lw  	t0, -296(s0)
	lw  	t1, -276(s0)
	lw  	t2, -288(s0)
	slt 	t0, t1, t2
	sw  	t0, -296(s0)
	lw  	t0, -300(s0)
	lw  	t1, -296(s0)
	mv  	t0, t1
	sw  	t0, -300(s0)
	lw  	t0, -304(s0)
	lw  	t1, -300(s0)
	mv  	t0, t1
	sw  	t0, -304(s0)
	lw  	t1, -304(s0)
	bnez	t1, main_for.body2
	j    	main_for.end2
main_for.body2:
	lw  	t0, -312(s0)
	la  	t0, a
	sw  	t0, -312(s0)
	lw  	t0, -308(s0)
	lw  	t2, -312(s0)
	lw  	t0, 0(t2)
	sw  	t0, -308(s0)
	lw  	t0, -316(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -316(s0)
	lw  	t0, -328(s0)
	li  	t0, 4
	sw  	t0, -328(s0)
	lw  	t0, -324(s0)
	lw  	t1, -316(s0)
	lw  	t2, -328(s0)
	mul 	t0, t1, t2
	sw  	t0, -324(s0)
	lw  	t0, -320(s0)
	lw  	t1, -324(s0)
	lw  	t2, -308(s0)
	add 	t0, t1, t2
	sw  	t0, -320(s0)
	call	random
	lw  	t0, -332(s0)
	mv  	t0, a0
	sw  	t0, -332(s0)
	lw  	t0, -340(s0)
	li  	t0, 10
	sw  	t0, -340(s0)
	lw  	t0, -336(s0)
	lw  	t1, -332(s0)
	lw  	t2, -340(s0)
	rem 	t0, t1, t2
	sw  	t0, -336(s0)
	lw  	t0, -348(s0)
	li  	t0, 1
	sw  	t0, -348(s0)
	lw  	t0, -344(s0)
	lw  	t1, -336(s0)
	lw  	t2, -348(s0)
	add 	t0, t1, t2
	sw  	t0, -344(s0)
	lw  	t0, -344(s0)
	lw  	t2, -320(s0)
	sw  	t0, 0(t2)
	sw  	t0, -344(s0)
	j    	main_while.begin3
main_while.begin3:
	lw  	t0, -356(s0)
	la  	t0, a
	sw  	t0, -356(s0)
	lw  	t0, -352(s0)
	lw  	t2, -356(s0)
	lw  	t0, 0(t2)
	sw  	t0, -352(s0)
	lw  	t0, -360(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -360(s0)
	lw  	t0, -372(s0)
	li  	t0, 4
	sw  	t0, -372(s0)
	lw  	t0, -368(s0)
	lw  	t1, -360(s0)
	lw  	t2, -372(s0)
	mul 	t0, t1, t2
	sw  	t0, -368(s0)
	lw  	t0, -364(s0)
	lw  	t1, -368(s0)
	lw  	t2, -352(s0)
	add 	t0, t1, t2
	sw  	t0, -364(s0)
	lw  	t0, -376(s0)
	lw  	t2, -364(s0)
	lw  	t0, 0(t2)
	sw  	t0, -376(s0)
	lw  	t0, -380(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -380(s0)
	lw  	t0, -384(s0)
	lw  	t1, -376(s0)
	lw  	t2, -380(s0)
	add 	t0, t1, t2
	sw  	t0, -384(s0)
	lw  	t0, -392(s0)
	la  	t0, n
	sw  	t0, -392(s0)
	lw  	t0, -388(s0)
	lw  	t2, -392(s0)
	lw  	t0, 0(t2)
	sw  	t0, -388(s0)
	lw  	t0, -396(s0)
	lw  	t1, -388(s0)
	lw  	t2, -384(s0)
	slt 	t0, t1, t2
	sw  	t0, -396(s0)
	lw  	t0, -400(s0)
	lw  	t1, -396(s0)
	mv  	t0, t1
	sw  	t0, -400(s0)
	lw  	t0, -404(s0)
	lw  	t1, -400(s0)
	mv  	t0, t1
	sw  	t0, -404(s0)
	lw  	t1, -404(s0)
	bnez	t1, main_while.body3
	j    	main_while.end3
main_while.body3:
	lw  	t0, -412(s0)
	la  	t0, a
	sw  	t0, -412(s0)
	lw  	t0, -408(s0)
	lw  	t2, -412(s0)
	lw  	t0, 0(t2)
	sw  	t0, -408(s0)
	lw  	t0, -416(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -416(s0)
	lw  	t0, -428(s0)
	li  	t0, 4
	sw  	t0, -428(s0)
	lw  	t0, -424(s0)
	lw  	t1, -416(s0)
	lw  	t2, -428(s0)
	mul 	t0, t1, t2
	sw  	t0, -424(s0)
	lw  	t0, -420(s0)
	lw  	t1, -424(s0)
	lw  	t2, -408(s0)
	add 	t0, t1, t2
	sw  	t0, -420(s0)
	call	random
	lw  	t0, -432(s0)
	mv  	t0, a0
	sw  	t0, -432(s0)
	lw  	t0, -440(s0)
	li  	t0, 10
	sw  	t0, -440(s0)
	lw  	t0, -436(s0)
	lw  	t1, -432(s0)
	lw  	t2, -440(s0)
	rem 	t0, t1, t2
	sw  	t0, -436(s0)
	lw  	t0, -448(s0)
	li  	t0, 1
	sw  	t0, -448(s0)
	lw  	t0, -444(s0)
	lw  	t1, -436(s0)
	lw  	t2, -448(s0)
	add 	t0, t1, t2
	sw  	t0, -444(s0)
	lw  	t0, -444(s0)
	lw  	t2, -420(s0)
	sw  	t0, 0(t2)
	sw  	t0, -444(s0)
	j    	main_while.begin3
main_while.end3:
	lw  	t0, -452(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -452(s0)
	lw  	t0, -460(s0)
	la  	t0, a
	sw  	t0, -460(s0)
	lw  	t0, -456(s0)
	lw  	t2, -460(s0)
	lw  	t0, 0(t2)
	sw  	t0, -456(s0)
	lw  	t0, -464(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -464(s0)
	lw  	t0, -476(s0)
	li  	t0, 4
	sw  	t0, -476(s0)
	lw  	t0, -472(s0)
	lw  	t1, -464(s0)
	lw  	t2, -476(s0)
	mul 	t0, t1, t2
	sw  	t0, -472(s0)
	lw  	t0, -468(s0)
	lw  	t1, -472(s0)
	lw  	t2, -456(s0)
	add 	t0, t1, t2
	sw  	t0, -468(s0)
	lw  	t0, -480(s0)
	lw  	t2, -468(s0)
	lw  	t0, 0(t2)
	sw  	t0, -480(s0)
	lw  	t0, -484(s0)
	lw  	t1, -452(s0)
	lw  	t2, -480(s0)
	add 	t0, t1, t2
	sw  	t0, -484(s0)
	lw  	t0, -484(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -484(s0)
	lw  	t0, -488(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -488(s0)
	lw  	t0, -496(s0)
	li  	t0, 1
	sw  	t0, -496(s0)
	lw  	t0, -492(s0)
	lw  	t1, -488(s0)
	lw  	t2, -496(s0)
	add 	t0, t1, t2
	sw  	t0, -492(s0)
	lw  	t0, -492(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -492(s0)
	j    	main_for.begin2
main_for.end2:
	lw  	t0, -504(s0)
	la  	t0, a
	sw  	t0, -504(s0)
	lw  	t0, -500(s0)
	lw  	t2, -504(s0)
	lw  	t0, 0(t2)
	sw  	t0, -500(s0)
	lw  	t0, -512(s0)
	la  	t0, now
	sw  	t0, -512(s0)
	lw  	t0, -508(s0)
	lw  	t2, -512(s0)
	lw  	t0, 0(t2)
	sw  	t0, -508(s0)
	lw  	t0, -520(s0)
	li  	t0, 1
	sw  	t0, -520(s0)
	lw  	t0, -516(s0)
	lw  	t1, -508(s0)
	lw  	t2, -520(s0)
	sub 	t0, t1, t2
	sw  	t0, -516(s0)
	lw  	t0, -532(s0)
	li  	t0, 4
	sw  	t0, -532(s0)
	lw  	t0, -528(s0)
	lw  	t1, -516(s0)
	lw  	t2, -532(s0)
	mul 	t0, t1, t2
	sw  	t0, -528(s0)
	lw  	t0, -524(s0)
	lw  	t1, -528(s0)
	lw  	t2, -500(s0)
	add 	t0, t1, t2
	sw  	t0, -524(s0)
	lw  	t0, -540(s0)
	la  	t0, n
	sw  	t0, -540(s0)
	lw  	t0, -536(s0)
	lw  	t2, -540(s0)
	lw  	t0, 0(t2)
	sw  	t0, -536(s0)
	lw  	t0, -544(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -544(s0)
	lw  	t0, -548(s0)
	lw  	t1, -536(s0)
	lw  	t2, -544(s0)
	sub 	t0, t1, t2
	sw  	t0, -548(s0)
	lw  	t0, -548(s0)
	lw  	t2, -524(s0)
	sw  	t0, 0(t2)
	sw  	t0, -548(s0)
	call	show
	call	merge
	j    	main_while.begin4
main_while.begin4:
	call	win
	lw  	t0, -552(s0)
	mv  	t0, a0
	sw  	t0, -552(s0)
	lw  	t0, -556(s0)
	lw  	t1, -552(s0)
	mv  	t0, t1
	sw  	t0, -556(s0)
	lw  	t0, -564(s0)
	li  	t0, 1
	sw  	t0, -564(s0)
	lw  	t0, -560(s0)
	lw  	t1, -556(s0)
	lw  	t2, -564(s0)
	xor 	t0, t1, t2
	sw  	t0, -560(s0)
	lw  	t0, -568(s0)
	lw  	t1, -560(s0)
	mv  	t0, t1
	sw  	t0, -568(s0)
	lw  	t0, -572(s0)
	lw  	t1, -568(s0)
	mv  	t0, t1
	sw  	t0, -572(s0)
	lw  	t1, -572(s0)
	bnez	t1, main_while.body4
	j    	main_while.end4
main_while.body4:
	lw  	t0, -576(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -576(s0)
	lw  	t0, -584(s0)
	li  	t0, 1
	sw  	t0, -584(s0)
	lw  	t0, -580(s0)
	lw  	t1, -576(s0)
	lw  	t2, -584(s0)
	add 	t0, t1, t2
	sw  	t0, -580(s0)
	lw  	t0, -580(s0)
	lw  	t2, -48(s0)
	sw  	t0, 0(t2)
	sw  	t0, -580(s0)
	lw  	t0, -588(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -588(s0)
	lw  	t1, -588(s0)
	mv  	a0, t1
	call	toString
	lw  	t0, -592(s0)
	mv  	t0, a0
	sw  	t0, -592(s0)
	lw  	t0, -596(s0)
	la  	t0, .str.2
	sw  	t0, -596(s0)
	lw  	t1, -596(s0)
	mv  	a0, t1
	lw  	t1, -592(s0)
	mv  	a1, t1
	call	___str_add
	lw  	t0, -600(s0)
	mv  	t0, a0
	sw  	t0, -600(s0)
	lw  	t1, -600(s0)
	mv  	a0, t1
	lw  	t0, -604(s0)
	la  	t0, .str.3
	sw  	t0, -604(s0)
	lw  	t1, -604(s0)
	mv  	a1, t1
	call	___str_add
	lw  	t0, -608(s0)
	mv  	t0, a0
	sw  	t0, -608(s0)
	lw  	t1, -608(s0)
	mv  	a0, t1
	call	println
	call	move
	call	merge
	call	show
	j    	main_while.begin4
main_while.end4:
	lw  	t0, -612(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -612(s0)
	lw  	t1, -612(s0)
	mv  	a0, t1
	call	toString
	lw  	t0, -616(s0)
	mv  	t0, a0
	sw  	t0, -616(s0)
	lw  	t0, -620(s0)
	la  	t0, .str.4
	sw  	t0, -620(s0)
	lw  	t1, -620(s0)
	mv  	a0, t1
	lw  	t1, -616(s0)
	mv  	a1, t1
	call	___str_add
	lw  	t0, -624(s0)
	mv  	t0, a0
	sw  	t0, -624(s0)
	lw  	t1, -624(s0)
	mv  	a0, t1
	lw  	t0, -628(s0)
	la  	t0, .str.5
	sw  	t0, -628(s0)
	lw  	t1, -628(s0)
	mv  	a1, t1
	call	___str_add
	lw  	t0, -632(s0)
	mv  	t0, a0
	sw  	t0, -632(s0)
	lw  	t1, -632(s0)
	mv  	a0, t1
	call	println
	lw  	t0, -636(s0)
	li  	t0, 0
	sw  	t0, -636(s0)
	lw  	t0, -636(s0)
	lw  	t2, -16(s0)
	sw  	t0, 0(t2)
	sw  	t0, -636(s0)
	j    	main_return
main_return:
	lw  	t0, -640(s0)
	lw  	t2, -16(s0)
	lw  	t0, 0(t2)
	sw  	t0, -640(s0)
	lw  	t1, -640(s0)
	mv  	a0, t1
	lw  	s0, 636(sp)
	lw  	ra, 640(sp)
	addi	sp, sp, 644
	ret
	.section	.data
	.globl	n
n:
	.word	0
	.section	.data
	.globl	h
h:
	.word	0
	.section	.data
	.globl	now
now:
	.word	0
	.section	.data
	.globl	a
a:
	.word	0
	.section	.data
	.globl	A
A:
	.word	0
	.section	.data
	.globl	M
M:
	.word	0
	.section	.data
	.globl	Q
Q:
	.word	0
	.section	.data
	.globl	R
R:
	.word	0
	.section	.data
	.globl	seed
seed:
	.word	0
	.section	.rodata
	.globl	.str
.str:
	.asciz	"Sorry, the number n must be a number s.t. there exists i satisfying n=1+2+...+i"
	.section	.rodata
	.globl	.str.1
.str.1:
	.asciz	"Let's start!"
	.section	.rodata
	.globl	.str.2
.str.2:
	.asciz	"step "
	.section	.rodata
	.globl	.str.3
.str.3:
	.asciz	":"
	.section	.rodata
	.globl	.str.4
.str.4:
	.asciz	"Total: "
	.section	.rodata
	.globl	.str.5
.str.5:
	.asciz	" step(s)"
	.section	.rodata
	.globl	.str.6
.str.6:
	.asciz	" "
	.section	.rodata
	.globl	.str.7
.str.7:
	.asciz	""
