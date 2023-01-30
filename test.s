	.text
	.globl	__mx_initialize_globalVar
__mx_initialize_globalVar:
	addi	sp, sp, -60
	sw  	ra, 56(sp)
	sw  	s0, 52(sp)
	addi	s0, sp, 60
	lw  	t0, -16(s0)
	li  	t0, 4
	sw  	t0, -16(s0)
	lw  	t0, -20(s0)
	li  	t0, 4
	sw  	t0, -20(s0)
	lw  	t0, -12(s0)
	lw  	t1, -16(s0)
	lw  	t2, -20(s0)
	mul 	t0, t1, t2
	sw  	t0, -12(s0)
	lw  	t0, -28(s0)
	li  	t0, 4
	sw  	t0, -28(s0)
	lw  	t0, -24(s0)
	lw  	t1, -12(s0)
	lw  	t2, -28(s0)
	add 	t0, t1, t2
	sw  	t0, -24(s0)
	lw  	t1, -24(s0)
	mv  	a0, t1
	call	__malloc
	lw  	t0, -32(s0)
	mv  	t0, a0
	sw  	t0, -32(s0)
	lw  	t0, -36(s0)
	lw  	t1, -32(s0)
	mv  	t0, t1
	sw  	t0, -36(s0)
	lw  	t0, -40(s0)
	li  	t0, 4
	sw  	t0, -40(s0)
	lw  	t0, -40(s0)
	lw  	t2, -36(s0)
	sw  	t0, 0(t2)
	sw  	t0, -40(s0)
	lw  	t0, -44(s0)
	lw  	t1, -32(s0)
	mv  	t0, t1
	sw  	t0, -44(s0)
	lw  	t0, -52(s0)
	li  	t0, 4
	sw  	t0, -52(s0)
	lw  	t0, -48(s0)
	lw  	t1, -52(s0)
	lw  	t2, -44(s0)
	add 	t0, t1, t2
	sw  	t0, -48(s0)
	lw  	t0, -56(s0)
	la  	t0, a
	sw  	t0, -56(s0)
	lw  	t0, -48(s0)
	lw  	t2, -56(s0)
	sw  	t0, 0(t2)
	sw  	t0, -48(s0)
	lw  	s0, 52(sp)
	lw  	ra, 56(sp)
	addi	sp, sp, 60
	ret
	.globl	main
main:
	addi	sp, sp, -612
	sw  	ra, 608(sp)
	sw  	s0, 604(sp)
	addi	s0, sp, 612
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
	call	__mx_initialize_globalVar
	lw  	t0, -48(s0)
	li  	t0, 4
	sw  	t0, -48(s0)
	lw  	t0, -52(s0)
	li  	t0, 4
	sw  	t0, -52(s0)
	lw  	t0, -44(s0)
	lw  	t1, -48(s0)
	lw  	t2, -52(s0)
	mul 	t0, t1, t2
	sw  	t0, -44(s0)
	lw  	t0, -60(s0)
	li  	t0, 4
	sw  	t0, -60(s0)
	lw  	t0, -56(s0)
	lw  	t1, -44(s0)
	lw  	t2, -60(s0)
	add 	t0, t1, t2
	sw  	t0, -56(s0)
	lw  	t1, -56(s0)
	mv  	a0, t1
	call	__malloc
	lw  	t0, -64(s0)
	mv  	t0, a0
	sw  	t0, -64(s0)
	lw  	t0, -68(s0)
	lw  	t1, -64(s0)
	mv  	t0, t1
	sw  	t0, -68(s0)
	lw  	t0, -72(s0)
	li  	t0, 4
	sw  	t0, -72(s0)
	lw  	t0, -72(s0)
	lw  	t2, -68(s0)
	sw  	t0, 0(t2)
	sw  	t0, -72(s0)
	lw  	t0, -76(s0)
	lw  	t1, -64(s0)
	mv  	t0, t1
	sw  	t0, -76(s0)
	lw  	t0, -84(s0)
	li  	t0, 4
	sw  	t0, -84(s0)
	lw  	t0, -80(s0)
	lw  	t1, -84(s0)
	lw  	t2, -76(s0)
	add 	t0, t1, t2
	sw  	t0, -80(s0)
	lw  	t0, -80(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -80(s0)
	lw  	t0, -88(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -88(s0)
	lw  	t0, -96(s0)
	li  	t0, 0
	sw  	t0, -96(s0)
	lw  	t0, -92(s0)
	lw  	t1, -96(s0)
	lw  	t2, -88(s0)
	add 	t0, t1, t2
	sw  	t0, -92(s0)
	lw  	t0, -104(s0)
	la  	t0, a
	sw  	t0, -104(s0)
	lw  	t0, -100(s0)
	lw  	t2, -104(s0)
	lw  	t0, 0(t2)
	sw  	t0, -100(s0)
	lw  	t0, -100(s0)
	lw  	t2, -92(s0)
	sw  	t0, 0(t2)
	sw  	t0, -100(s0)
	lw  	t0, -108(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -108(s0)
	lw  	t0, -116(s0)
	li  	t0, 4
	sw  	t0, -116(s0)
	lw  	t0, -112(s0)
	lw  	t1, -116(s0)
	lw  	t2, -108(s0)
	add 	t0, t1, t2
	sw  	t0, -112(s0)
	lw  	t0, -124(s0)
	la  	t0, a
	sw  	t0, -124(s0)
	lw  	t0, -120(s0)
	lw  	t2, -124(s0)
	lw  	t0, 0(t2)
	sw  	t0, -120(s0)
	lw  	t0, -120(s0)
	lw  	t2, -112(s0)
	sw  	t0, 0(t2)
	sw  	t0, -120(s0)
	lw  	t0, -128(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -128(s0)
	lw  	t0, -136(s0)
	li  	t0, 8
	sw  	t0, -136(s0)
	lw  	t0, -132(s0)
	lw  	t1, -136(s0)
	lw  	t2, -128(s0)
	add 	t0, t1, t2
	sw  	t0, -132(s0)
	lw  	t0, -144(s0)
	la  	t0, a
	sw  	t0, -144(s0)
	lw  	t0, -140(s0)
	lw  	t2, -144(s0)
	lw  	t0, 0(t2)
	sw  	t0, -140(s0)
	lw  	t0, -140(s0)
	lw  	t2, -132(s0)
	sw  	t0, 0(t2)
	sw  	t0, -140(s0)
	lw  	t0, -148(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -148(s0)
	lw  	t0, -156(s0)
	li  	t0, 12
	sw  	t0, -156(s0)
	lw  	t0, -152(s0)
	lw  	t1, -156(s0)
	lw  	t2, -148(s0)
	add 	t0, t1, t2
	sw  	t0, -152(s0)
	lw  	t0, -164(s0)
	la  	t0, a
	sw  	t0, -164(s0)
	lw  	t0, -160(s0)
	lw  	t2, -164(s0)
	lw  	t0, 0(t2)
	sw  	t0, -160(s0)
	lw  	t0, -160(s0)
	lw  	t2, -152(s0)
	sw  	t0, 0(t2)
	sw  	t0, -160(s0)
	lw  	t0, -168(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -168(s0)
	lw  	t0, -172(s0)
	lw  	t1, -168(s0)
	mv  	t0, t1
	sw  	t0, -172(s0)
	lw  	t0, -180(s0)
	li  	t0, -4
	sw  	t0, -180(s0)
	lw  	t0, -176(s0)
	lw  	t1, -180(s0)
	lw  	t2, -172(s0)
	add 	t0, t1, t2
	sw  	t0, -176(s0)
	lw  	t0, -184(s0)
	lw  	t2, -176(s0)
	lw  	t0, 0(t2)
	sw  	t0, -184(s0)
	lw  	t1, -184(s0)
	mv  	a0, t1
	call	toString
	lw  	t0, -188(s0)
	mv  	t0, a0
	sw  	t0, -188(s0)
	lw  	t1, -188(s0)
	mv  	a0, t1
	call	println
	lw  	t0, -192(s0)
	li  	t0, 0
	sw  	t0, -192(s0)
	lw  	t0, -192(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -192(s0)
	j    	main_for.begin1
main_for.begin1:
	lw  	t0, -196(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -196(s0)
	lw  	t0, -200(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -200(s0)
	lw  	t0, -208(s0)
	li  	t0, 0
	sw  	t0, -208(s0)
	lw  	t0, -204(s0)
	lw  	t1, -208(s0)
	lw  	t2, -200(s0)
	add 	t0, t1, t2
	sw  	t0, -204(s0)
	lw  	t0, -212(s0)
	lw  	t2, -204(s0)
	lw  	t0, 0(t2)
	sw  	t0, -212(s0)
	lw  	t0, -216(s0)
	lw  	t1, -212(s0)
	mv  	t0, t1
	sw  	t0, -216(s0)
	lw  	t0, -224(s0)
	li  	t0, -4
	sw  	t0, -224(s0)
	lw  	t0, -220(s0)
	lw  	t1, -224(s0)
	lw  	t2, -216(s0)
	add 	t0, t1, t2
	sw  	t0, -220(s0)
	lw  	t0, -228(s0)
	lw  	t2, -220(s0)
	lw  	t0, 0(t2)
	sw  	t0, -228(s0)
	lw  	t0, -232(s0)
	lw  	t1, -196(s0)
	lw  	t2, -228(s0)
	slt 	t0, t1, t2
	sw  	t0, -232(s0)
	lw  	t0, -236(s0)
	lw  	t1, -232(s0)
	mv  	t0, t1
	sw  	t0, -236(s0)
	lw  	t0, -240(s0)
	lw  	t1, -236(s0)
	mv  	t0, t1
	sw  	t0, -240(s0)
	lw  	t1, -240(s0)
	bnez	t1, main_for.body1
	j    	main_for.end1
main_for.body1:
	lw  	t0, -244(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -244(s0)
	lw  	t0, -252(s0)
	li  	t0, 0
	sw  	t0, -252(s0)
	lw  	t0, -248(s0)
	lw  	t1, -252(s0)
	lw  	t2, -244(s0)
	add 	t0, t1, t2
	sw  	t0, -248(s0)
	lw  	t0, -256(s0)
	lw  	t2, -248(s0)
	lw  	t0, 0(t2)
	sw  	t0, -256(s0)
	lw  	t0, -260(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -260(s0)
	lw  	t0, -272(s0)
	li  	t0, 4
	sw  	t0, -272(s0)
	lw  	t0, -268(s0)
	lw  	t1, -260(s0)
	lw  	t2, -272(s0)
	mul 	t0, t1, t2
	sw  	t0, -268(s0)
	lw  	t0, -264(s0)
	lw  	t1, -268(s0)
	lw  	t2, -256(s0)
	add 	t0, t1, t2
	sw  	t0, -264(s0)
	call	getInt
	lw  	t0, -276(s0)
	mv  	t0, a0
	sw  	t0, -276(s0)
	lw  	t0, -276(s0)
	lw  	t2, -264(s0)
	sw  	t0, 0(t2)
	sw  	t0, -276(s0)
	lw  	t0, -280(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -280(s0)
	lw  	t0, -288(s0)
	li  	t0, 1
	sw  	t0, -288(s0)
	lw  	t0, -284(s0)
	lw  	t1, -280(s0)
	lw  	t2, -288(s0)
	add 	t0, t1, t2
	sw  	t0, -284(s0)
	lw  	t0, -284(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -284(s0)
	j    	main_for.begin1
main_for.end1:
	lw  	t0, -292(s0)
	li  	t0, 0
	sw  	t0, -292(s0)
	lw  	t0, -292(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -292(s0)
	j    	main_for.begin2
main_for.begin2:
	lw  	t0, -296(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -296(s0)
	lw  	t0, -300(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -300(s0)
	lw  	t0, -308(s0)
	li  	t0, 4
	sw  	t0, -308(s0)
	lw  	t0, -304(s0)
	lw  	t1, -308(s0)
	lw  	t2, -300(s0)
	add 	t0, t1, t2
	sw  	t0, -304(s0)
	lw  	t0, -312(s0)
	lw  	t2, -304(s0)
	lw  	t0, 0(t2)
	sw  	t0, -312(s0)
	lw  	t0, -316(s0)
	lw  	t1, -312(s0)
	mv  	t0, t1
	sw  	t0, -316(s0)
	lw  	t0, -324(s0)
	li  	t0, -4
	sw  	t0, -324(s0)
	lw  	t0, -320(s0)
	lw  	t1, -324(s0)
	lw  	t2, -316(s0)
	add 	t0, t1, t2
	sw  	t0, -320(s0)
	lw  	t0, -328(s0)
	lw  	t2, -320(s0)
	lw  	t0, 0(t2)
	sw  	t0, -328(s0)
	lw  	t0, -332(s0)
	lw  	t1, -296(s0)
	lw  	t2, -328(s0)
	slt 	t0, t1, t2
	sw  	t0, -332(s0)
	lw  	t0, -336(s0)
	lw  	t1, -332(s0)
	mv  	t0, t1
	sw  	t0, -336(s0)
	lw  	t0, -340(s0)
	lw  	t1, -336(s0)
	mv  	t0, t1
	sw  	t0, -340(s0)
	lw  	t1, -340(s0)
	bnez	t1, main_for.body2
	j    	main_for.end2
main_for.body2:
	lw  	t0, -344(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -344(s0)
	lw  	t0, -352(s0)
	li  	t0, 4
	sw  	t0, -352(s0)
	lw  	t0, -348(s0)
	lw  	t1, -352(s0)
	lw  	t2, -344(s0)
	add 	t0, t1, t2
	sw  	t0, -348(s0)
	lw  	t0, -356(s0)
	lw  	t2, -348(s0)
	lw  	t0, 0(t2)
	sw  	t0, -356(s0)
	lw  	t0, -360(s0)
	lw  	t2, -40(s0)
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
	lw  	t1, -376(s0)
	mv  	a0, t1
	call	toString
	lw  	t0, -380(s0)
	mv  	t0, a0
	sw  	t0, -380(s0)
	lw  	t1, -380(s0)
	mv  	a0, t1
	call	print
	lw  	t0, -384(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -384(s0)
	lw  	t0, -392(s0)
	li  	t0, 1
	sw  	t0, -392(s0)
	lw  	t0, -388(s0)
	lw  	t1, -384(s0)
	lw  	t2, -392(s0)
	add 	t0, t1, t2
	sw  	t0, -388(s0)
	lw  	t0, -388(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -388(s0)
	j    	main_for.begin2
main_for.end2:
	lw  	t0, -396(s0)
	la  	t0, .str
	sw  	t0, -396(s0)
	lw  	t1, -396(s0)
	mv  	a0, t1
	call	println
	lw  	t0, -400(s0)
	li  	t0, 0
	sw  	t0, -400(s0)
	lw  	t0, -400(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -400(s0)
	j    	main_for.begin3
main_for.begin3:
	lw  	t0, -404(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -404(s0)
	lw  	t0, -408(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -408(s0)
	lw  	t0, -416(s0)
	li  	t0, 8
	sw  	t0, -416(s0)
	lw  	t0, -412(s0)
	lw  	t1, -416(s0)
	lw  	t2, -408(s0)
	add 	t0, t1, t2
	sw  	t0, -412(s0)
	lw  	t0, -420(s0)
	lw  	t2, -412(s0)
	lw  	t0, 0(t2)
	sw  	t0, -420(s0)
	lw  	t0, -424(s0)
	lw  	t1, -420(s0)
	mv  	t0, t1
	sw  	t0, -424(s0)
	lw  	t0, -432(s0)
	li  	t0, -4
	sw  	t0, -432(s0)
	lw  	t0, -428(s0)
	lw  	t1, -432(s0)
	lw  	t2, -424(s0)
	add 	t0, t1, t2
	sw  	t0, -428(s0)
	lw  	t0, -436(s0)
	lw  	t2, -428(s0)
	lw  	t0, 0(t2)
	sw  	t0, -436(s0)
	lw  	t0, -440(s0)
	lw  	t1, -404(s0)
	lw  	t2, -436(s0)
	slt 	t0, t1, t2
	sw  	t0, -440(s0)
	lw  	t0, -444(s0)
	lw  	t1, -440(s0)
	mv  	t0, t1
	sw  	t0, -444(s0)
	lw  	t0, -448(s0)
	lw  	t1, -444(s0)
	mv  	t0, t1
	sw  	t0, -448(s0)
	lw  	t1, -448(s0)
	bnez	t1, main_for.body3
	j    	main_for.end3
main_for.body3:
	lw  	t0, -452(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -452(s0)
	lw  	t0, -460(s0)
	li  	t0, 8
	sw  	t0, -460(s0)
	lw  	t0, -456(s0)
	lw  	t1, -460(s0)
	lw  	t2, -452(s0)
	add 	t0, t1, t2
	sw  	t0, -456(s0)
	lw  	t0, -464(s0)
	lw  	t2, -456(s0)
	lw  	t0, 0(t2)
	sw  	t0, -464(s0)
	lw  	t0, -468(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -468(s0)
	lw  	t0, -480(s0)
	li  	t0, 4
	sw  	t0, -480(s0)
	lw  	t0, -476(s0)
	lw  	t1, -468(s0)
	lw  	t2, -480(s0)
	mul 	t0, t1, t2
	sw  	t0, -476(s0)
	lw  	t0, -472(s0)
	lw  	t1, -476(s0)
	lw  	t2, -464(s0)
	add 	t0, t1, t2
	sw  	t0, -472(s0)
	lw  	t0, -484(s0)
	li  	t0, 0
	sw  	t0, -484(s0)
	lw  	t0, -484(s0)
	lw  	t2, -472(s0)
	sw  	t0, 0(t2)
	sw  	t0, -484(s0)
	lw  	t0, -488(s0)
	lw  	t2, -40(s0)
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
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -492(s0)
	j    	main_for.begin3
main_for.end3:
	lw  	t0, -500(s0)
	li  	t0, 0
	sw  	t0, -500(s0)
	lw  	t0, -500(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -500(s0)
	j    	main_for.begin4
main_for.begin4:
	lw  	t0, -504(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -504(s0)
	lw  	t0, -508(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -508(s0)
	lw  	t0, -516(s0)
	li  	t0, 12
	sw  	t0, -516(s0)
	lw  	t0, -512(s0)
	lw  	t1, -516(s0)
	lw  	t2, -508(s0)
	add 	t0, t1, t2
	sw  	t0, -512(s0)
	lw  	t0, -520(s0)
	lw  	t2, -512(s0)
	lw  	t0, 0(t2)
	sw  	t0, -520(s0)
	lw  	t0, -524(s0)
	lw  	t1, -520(s0)
	mv  	t0, t1
	sw  	t0, -524(s0)
	lw  	t0, -532(s0)
	li  	t0, -4
	sw  	t0, -532(s0)
	lw  	t0, -528(s0)
	lw  	t1, -532(s0)
	lw  	t2, -524(s0)
	add 	t0, t1, t2
	sw  	t0, -528(s0)
	lw  	t0, -536(s0)
	lw  	t2, -528(s0)
	lw  	t0, 0(t2)
	sw  	t0, -536(s0)
	lw  	t0, -540(s0)
	lw  	t1, -504(s0)
	lw  	t2, -536(s0)
	slt 	t0, t1, t2
	sw  	t0, -540(s0)
	lw  	t0, -544(s0)
	lw  	t1, -540(s0)
	mv  	t0, t1
	sw  	t0, -544(s0)
	lw  	t0, -548(s0)
	lw  	t1, -544(s0)
	mv  	t0, t1
	sw  	t0, -548(s0)
	lw  	t1, -548(s0)
	bnez	t1, main_for.body4
	j    	main_for.end4
main_for.body4:
	lw  	t0, -552(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -552(s0)
	lw  	t0, -560(s0)
	li  	t0, 12
	sw  	t0, -560(s0)
	lw  	t0, -556(s0)
	lw  	t1, -560(s0)
	lw  	t2, -552(s0)
	add 	t0, t1, t2
	sw  	t0, -556(s0)
	lw  	t0, -564(s0)
	lw  	t2, -556(s0)
	lw  	t0, 0(t2)
	sw  	t0, -564(s0)
	lw  	t0, -568(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -568(s0)
	lw  	t0, -580(s0)
	li  	t0, 4
	sw  	t0, -580(s0)
	lw  	t0, -576(s0)
	lw  	t1, -568(s0)
	lw  	t2, -580(s0)
	mul 	t0, t1, t2
	sw  	t0, -576(s0)
	lw  	t0, -572(s0)
	lw  	t1, -576(s0)
	lw  	t2, -564(s0)
	add 	t0, t1, t2
	sw  	t0, -572(s0)
	lw  	t0, -584(s0)
	lw  	t2, -572(s0)
	lw  	t0, 0(t2)
	sw  	t0, -584(s0)
	lw  	t1, -584(s0)
	mv  	a0, t1
	call	toString
	lw  	t0, -588(s0)
	mv  	t0, a0
	sw  	t0, -588(s0)
	lw  	t1, -588(s0)
	mv  	a0, t1
	call	print
	lw  	t0, -592(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -592(s0)
	lw  	t0, -600(s0)
	li  	t0, 1
	sw  	t0, -600(s0)
	lw  	t0, -596(s0)
	lw  	t1, -592(s0)
	lw  	t2, -600(s0)
	add 	t0, t1, t2
	sw  	t0, -596(s0)
	lw  	t0, -596(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -596(s0)
	j    	main_for.begin4
main_for.end4:
	lw  	t0, -604(s0)
	li  	t0, 0
	sw  	t0, -604(s0)
	lw  	t0, -604(s0)
	lw  	t2, -16(s0)
	sw  	t0, 0(t2)
	sw  	t0, -604(s0)
	j    	main_return
main_return:
	lw  	t0, -608(s0)
	lw  	t2, -16(s0)
	lw  	t0, 0(t2)
	sw  	t0, -608(s0)
	lw  	t1, -608(s0)
	mv  	a0, t1
	lw  	s0, 604(sp)
	lw  	ra, 608(sp)
	addi	sp, sp, 612
	ret
	.section	.data
	.globl	a
a:
	.word	0
	.section	.rodata
	.globl	.str
.str:
	.asciz	""
