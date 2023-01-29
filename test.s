	.text
	.globl	__mx_initialize_globalVar
__mx_initialize_globalVar:
	addi	sp, sp, -20
	sw  	ra, 16(sp)
	sw  	s0, 12(sp)
	addi	s0, sp, 20
	lw  	t0, -12(s0)
	li  	t0, 0
	sw  	t0, -12(s0)
	lw  	t0, -16(s0)
	la  	t0, ans
	sw  	t0, -16(s0)
	lw  	t0, -12(s0)
	lw  	t2, -16(s0)
	sw  	t0, 0(t2)
	sw  	t0, -12(s0)
	lw  	s0, 12(sp)
	lw  	ra, 16(sp)
	addi	sp, sp, 20
	ret
	.globl	qsort
qsort:
	addi	sp, sp, -568
	sw  	ra, 564(sp)
	sw  	s0, 560(sp)
	addi	s0, sp, 568
	lw  	t0, -12(s0)
	mv  	t0, a0
	sw  	t0, -12(s0)
	lw  	t0, -16(s0)
	mv  	t0, a1
	sw  	t0, -16(s0)
	lw  	t0, -20(s0)
	mv  	t0, a2
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
	lw  	t0, -52(s0)
	addi	t0, s0, -48
	sw  	t0, -52(s0)
	lw  	t0, -60(s0)
	addi	t0, s0, -56
	sw  	t0, -60(s0)
	lw  	t0, -68(s0)
	addi	t0, s0, -64
	sw  	t0, -68(s0)
	lw  	t0, -76(s0)
	addi	t0, s0, -72
	sw  	t0, -76(s0)
	lw  	t0, -12(s0)
	lw  	t2, -36(s0)
	sw  	t0, 0(t2)
	sw  	t0, -12(s0)
	lw  	t0, -16(s0)
	lw  	t2, -44(s0)
	sw  	t0, 0(t2)
	sw  	t0, -16(s0)
	lw  	t0, -20(s0)
	lw  	t2, -52(s0)
	sw  	t0, 0(t2)
	sw  	t0, -20(s0)
	lw  	t0, -80(s0)
	lw  	t2, -44(s0)
	lw  	t0, 0(t2)
	sw  	t0, -80(s0)
	lw  	t0, -84(s0)
	lw  	t2, -52(s0)
	lw  	t0, 0(t2)
	sw  	t0, -84(s0)
	lw  	t0, -88(s0)
	lw  	t1, -80(s0)
	lw  	t2, -84(s0)
	slt 	t0, t1, t2
	sw  	t0, -88(s0)
	lw  	t0, -92(s0)
	lw  	t1, -88(s0)
	mv  	t0, t1
	sw  	t0, -92(s0)
	lw  	t0, -96(s0)
	lw  	t1, -92(s0)
	mv  	t0, t1
	sw  	t0, -96(s0)
	lw  	t1, -96(s0)
	bnez	t1, qsort_if.then1
	j    	qsort_if.end1
qsort_if.then1:
	lw  	t0, -100(s0)
	lw  	t2, -44(s0)
	lw  	t0, 0(t2)
	sw  	t0, -100(s0)
	lw  	t0, -100(s0)
	lw  	t2, -60(s0)
	sw  	t0, 0(t2)
	sw  	t0, -100(s0)
	lw  	t0, -104(s0)
	lw  	t2, -52(s0)
	lw  	t0, 0(t2)
	sw  	t0, -104(s0)
	lw  	t0, -104(s0)
	lw  	t2, -68(s0)
	sw  	t0, 0(t2)
	sw  	t0, -104(s0)
	lw  	t0, -108(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -108(s0)
	lw  	t0, -112(s0)
	lw  	t2, -44(s0)
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
	lw  	t2, -108(s0)
	add 	t0, t1, t2
	sw  	t0, -116(s0)
	lw  	t0, -128(s0)
	lw  	t2, -116(s0)
	lw  	t0, 0(t2)
	sw  	t0, -128(s0)
	lw  	t0, -128(s0)
	lw  	t2, -76(s0)
	sw  	t0, 0(t2)
	sw  	t0, -128(s0)
	j    	qsort_while.begin2
qsort_while.begin2:
	lw  	t0, -132(s0)
	lw  	t2, -60(s0)
	lw  	t0, 0(t2)
	sw  	t0, -132(s0)
	lw  	t0, -136(s0)
	lw  	t2, -68(s0)
	lw  	t0, 0(t2)
	sw  	t0, -136(s0)
	lw  	t0, -140(s0)
	lw  	t1, -132(s0)
	lw  	t2, -136(s0)
	slt 	t0, t1, t2
	sw  	t0, -140(s0)
	lw  	t0, -144(s0)
	lw  	t1, -140(s0)
	mv  	t0, t1
	sw  	t0, -144(s0)
	lw  	t0, -148(s0)
	lw  	t1, -144(s0)
	mv  	t0, t1
	sw  	t0, -148(s0)
	lw  	t1, -148(s0)
	bnez	t1, qsort_while.body2
	j    	qsort_while.end2
qsort_while.body2:
	j    	qsort_while.begin3
qsort_while.begin3:
	lw  	t0, -152(s0)
	lw  	t2, -60(s0)
	lw  	t0, 0(t2)
	sw  	t0, -152(s0)
	lw  	t0, -156(s0)
	lw  	t2, -68(s0)
	lw  	t0, 0(t2)
	sw  	t0, -156(s0)
	lw  	t0, -160(s0)
	lw  	t1, -152(s0)
	lw  	t2, -156(s0)
	slt 	t0, t1, t2
	sw  	t0, -160(s0)
	lw  	t0, -164(s0)
	lw  	t1, -160(s0)
	mv  	t0, t1
	sw  	t0, -164(s0)
	lw  	t0, -164(s0)
	lw  	t2, -28(s0)
	sw  	t0, 0(t2)
	sw  	t0, -164(s0)
	lw  	t0, -168(s0)
	lw  	t1, -164(s0)
	mv  	t0, t1
	sw  	t0, -168(s0)
	lw  	t1, -168(s0)
	bnez	t1, qsort_and.rhs4
	j    	qsort_and.end4
qsort_and.rhs4:
	lw  	t0, -172(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -172(s0)
	lw  	t0, -176(s0)
	lw  	t2, -68(s0)
	lw  	t0, 0(t2)
	sw  	t0, -176(s0)
	lw  	t0, -188(s0)
	li  	t0, 4
	sw  	t0, -188(s0)
	lw  	t0, -184(s0)
	lw  	t1, -176(s0)
	lw  	t2, -188(s0)
	mul 	t0, t1, t2
	sw  	t0, -184(s0)
	lw  	t0, -180(s0)
	lw  	t1, -184(s0)
	lw  	t2, -172(s0)
	add 	t0, t1, t2
	sw  	t0, -180(s0)
	lw  	t0, -192(s0)
	lw  	t2, -180(s0)
	lw  	t0, 0(t2)
	sw  	t0, -192(s0)
	lw  	t0, -200(s0)
	li  	t0, 8
	sw  	t0, -200(s0)
	lw  	t0, -196(s0)
	lw  	t1, -200(s0)
	lw  	t2, -192(s0)
	add 	t0, t1, t2
	sw  	t0, -196(s0)
	lw  	t0, -204(s0)
	lw  	t2, -196(s0)
	lw  	t0, 0(t2)
	sw  	t0, -204(s0)
	lw  	t0, -208(s0)
	lw  	t2, -76(s0)
	lw  	t0, 0(t2)
	sw  	t0, -208(s0)
	lw  	t0, -216(s0)
	li  	t0, 8
	sw  	t0, -216(s0)
	lw  	t0, -212(s0)
	lw  	t1, -216(s0)
	lw  	t2, -208(s0)
	add 	t0, t1, t2
	sw  	t0, -212(s0)
	lw  	t0, -220(s0)
	lw  	t2, -212(s0)
	lw  	t0, 0(t2)
	sw  	t0, -220(s0)
	lw  	t0, -224(s0)
	lw  	t1, -204(s0)
	lw  	t2, -220(s0)
	slt 	t0, t1, t2
	sw  	t0, -224(s0)
	lw  	t0, -224(s0)
	lw  	t1, -224(s0)
	xori	t0, t1, 1
	sw  	t0, -224(s0)
	lw  	t0, -228(s0)
	lw  	t1, -224(s0)
	mv  	t0, t1
	sw  	t0, -228(s0)
	lw  	t0, -228(s0)
	lw  	t2, -28(s0)
	sw  	t0, 0(t2)
	sw  	t0, -228(s0)
	j    	qsort_and.end4
qsort_and.end4:
	lw  	t0, -232(s0)
	lw  	t2, -28(s0)
	lw  	t0, 0(t2)
	sw  	t0, -232(s0)
	lw  	t0, -236(s0)
	lw  	t1, -232(s0)
	mv  	t0, t1
	sw  	t0, -236(s0)
	lw  	t1, -236(s0)
	bnez	t1, qsort_while.body3
	j    	qsort_while.end3
qsort_while.body3:
	lw  	t0, -240(s0)
	lw  	t2, -68(s0)
	lw  	t0, 0(t2)
	sw  	t0, -240(s0)
	lw  	t0, -248(s0)
	li  	t0, 1
	sw  	t0, -248(s0)
	lw  	t0, -244(s0)
	lw  	t1, -240(s0)
	lw  	t2, -248(s0)
	sub 	t0, t1, t2
	sw  	t0, -244(s0)
	lw  	t0, -244(s0)
	lw  	t2, -68(s0)
	sw  	t0, 0(t2)
	sw  	t0, -244(s0)
	j    	qsort_while.begin3
qsort_while.end3:
	lw  	t0, -252(s0)
	lw  	t2, -60(s0)
	lw  	t0, 0(t2)
	sw  	t0, -252(s0)
	lw  	t0, -256(s0)
	lw  	t2, -68(s0)
	lw  	t0, 0(t2)
	sw  	t0, -256(s0)
	lw  	t0, -260(s0)
	lw  	t1, -252(s0)
	lw  	t2, -256(s0)
	slt 	t0, t1, t2
	sw  	t0, -260(s0)
	lw  	t0, -264(s0)
	lw  	t1, -260(s0)
	mv  	t0, t1
	sw  	t0, -264(s0)
	lw  	t0, -268(s0)
	lw  	t1, -264(s0)
	mv  	t0, t1
	sw  	t0, -268(s0)
	lw  	t1, -268(s0)
	bnez	t1, qsort_if.then5
	j    	qsort_if.end5
qsort_if.then5:
	lw  	t0, -272(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -272(s0)
	lw  	t0, -276(s0)
	lw  	t2, -60(s0)
	lw  	t0, 0(t2)
	sw  	t0, -276(s0)
	lw  	t0, -288(s0)
	li  	t0, 4
	sw  	t0, -288(s0)
	lw  	t0, -284(s0)
	lw  	t1, -276(s0)
	lw  	t2, -288(s0)
	mul 	t0, t1, t2
	sw  	t0, -284(s0)
	lw  	t0, -280(s0)
	lw  	t1, -284(s0)
	lw  	t2, -272(s0)
	add 	t0, t1, t2
	sw  	t0, -280(s0)
	lw  	t0, -292(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -292(s0)
	lw  	t0, -296(s0)
	lw  	t2, -68(s0)
	lw  	t0, 0(t2)
	sw  	t0, -296(s0)
	lw  	t0, -308(s0)
	li  	t0, 4
	sw  	t0, -308(s0)
	lw  	t0, -304(s0)
	lw  	t1, -296(s0)
	lw  	t2, -308(s0)
	mul 	t0, t1, t2
	sw  	t0, -304(s0)
	lw  	t0, -300(s0)
	lw  	t1, -304(s0)
	lw  	t2, -292(s0)
	add 	t0, t1, t2
	sw  	t0, -300(s0)
	lw  	t0, -312(s0)
	lw  	t2, -300(s0)
	lw  	t0, 0(t2)
	sw  	t0, -312(s0)
	lw  	t0, -312(s0)
	lw  	t2, -280(s0)
	sw  	t0, 0(t2)
	sw  	t0, -312(s0)
	lw  	t0, -316(s0)
	lw  	t2, -60(s0)
	lw  	t0, 0(t2)
	sw  	t0, -316(s0)
	lw  	t0, -324(s0)
	li  	t0, 1
	sw  	t0, -324(s0)
	lw  	t0, -320(s0)
	lw  	t1, -316(s0)
	lw  	t2, -324(s0)
	add 	t0, t1, t2
	sw  	t0, -320(s0)
	lw  	t0, -320(s0)
	lw  	t2, -60(s0)
	sw  	t0, 0(t2)
	sw  	t0, -320(s0)
	j    	qsort_if.end5
qsort_if.end5:
	j    	qsort_while.begin6
qsort_while.begin6:
	lw  	t0, -328(s0)
	lw  	t2, -60(s0)
	lw  	t0, 0(t2)
	sw  	t0, -328(s0)
	lw  	t0, -332(s0)
	lw  	t2, -68(s0)
	lw  	t0, 0(t2)
	sw  	t0, -332(s0)
	lw  	t0, -336(s0)
	lw  	t1, -328(s0)
	lw  	t2, -332(s0)
	slt 	t0, t1, t2
	sw  	t0, -336(s0)
	lw  	t0, -340(s0)
	lw  	t1, -336(s0)
	mv  	t0, t1
	sw  	t0, -340(s0)
	lw  	t0, -340(s0)
	lw  	t2, -28(s0)
	sw  	t0, 0(t2)
	sw  	t0, -340(s0)
	lw  	t0, -344(s0)
	lw  	t1, -340(s0)
	mv  	t0, t1
	sw  	t0, -344(s0)
	lw  	t1, -344(s0)
	bnez	t1, qsort_and.rhs7
	j    	qsort_and.end7
qsort_and.rhs7:
	lw  	t0, -348(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -348(s0)
	lw  	t0, -352(s0)
	lw  	t2, -60(s0)
	lw  	t0, 0(t2)
	sw  	t0, -352(s0)
	lw  	t0, -364(s0)
	li  	t0, 4
	sw  	t0, -364(s0)
	lw  	t0, -360(s0)
	lw  	t1, -352(s0)
	lw  	t2, -364(s0)
	mul 	t0, t1, t2
	sw  	t0, -360(s0)
	lw  	t0, -356(s0)
	lw  	t1, -360(s0)
	lw  	t2, -348(s0)
	add 	t0, t1, t2
	sw  	t0, -356(s0)
	lw  	t0, -368(s0)
	lw  	t2, -356(s0)
	lw  	t0, 0(t2)
	sw  	t0, -368(s0)
	lw  	t0, -376(s0)
	li  	t0, 8
	sw  	t0, -376(s0)
	lw  	t0, -372(s0)
	lw  	t1, -376(s0)
	lw  	t2, -368(s0)
	add 	t0, t1, t2
	sw  	t0, -372(s0)
	lw  	t0, -380(s0)
	lw  	t2, -372(s0)
	lw  	t0, 0(t2)
	sw  	t0, -380(s0)
	lw  	t0, -384(s0)
	lw  	t2, -76(s0)
	lw  	t0, 0(t2)
	sw  	t0, -384(s0)
	lw  	t0, -392(s0)
	li  	t0, 8
	sw  	t0, -392(s0)
	lw  	t0, -388(s0)
	lw  	t1, -392(s0)
	lw  	t2, -384(s0)
	add 	t0, t1, t2
	sw  	t0, -388(s0)
	lw  	t0, -396(s0)
	lw  	t2, -388(s0)
	lw  	t0, 0(t2)
	sw  	t0, -396(s0)
	lw  	t0, -400(s0)
	lw  	t1, -380(s0)
	lw  	t2, -396(s0)
	slt 	t0, t1, t2
	sw  	t0, -400(s0)
	lw  	t0, -404(s0)
	lw  	t1, -400(s0)
	mv  	t0, t1
	sw  	t0, -404(s0)
	lw  	t0, -404(s0)
	lw  	t2, -28(s0)
	sw  	t0, 0(t2)
	sw  	t0, -404(s0)
	j    	qsort_and.end7
qsort_and.end7:
	lw  	t0, -408(s0)
	lw  	t2, -28(s0)
	lw  	t0, 0(t2)
	sw  	t0, -408(s0)
	lw  	t0, -412(s0)
	lw  	t1, -408(s0)
	mv  	t0, t1
	sw  	t0, -412(s0)
	lw  	t1, -412(s0)
	bnez	t1, qsort_while.body6
	j    	qsort_while.end6
qsort_while.body6:
	lw  	t0, -416(s0)
	lw  	t2, -60(s0)
	lw  	t0, 0(t2)
	sw  	t0, -416(s0)
	lw  	t0, -424(s0)
	li  	t0, 1
	sw  	t0, -424(s0)
	lw  	t0, -420(s0)
	lw  	t1, -416(s0)
	lw  	t2, -424(s0)
	add 	t0, t1, t2
	sw  	t0, -420(s0)
	lw  	t0, -420(s0)
	lw  	t2, -60(s0)
	sw  	t0, 0(t2)
	sw  	t0, -420(s0)
	j    	qsort_while.begin6
qsort_while.end6:
	lw  	t0, -428(s0)
	lw  	t2, -60(s0)
	lw  	t0, 0(t2)
	sw  	t0, -428(s0)
	lw  	t0, -432(s0)
	lw  	t2, -68(s0)
	lw  	t0, 0(t2)
	sw  	t0, -432(s0)
	lw  	t0, -436(s0)
	lw  	t1, -428(s0)
	lw  	t2, -432(s0)
	slt 	t0, t1, t2
	sw  	t0, -436(s0)
	lw  	t0, -440(s0)
	lw  	t1, -436(s0)
	mv  	t0, t1
	sw  	t0, -440(s0)
	lw  	t0, -444(s0)
	lw  	t1, -440(s0)
	mv  	t0, t1
	sw  	t0, -444(s0)
	lw  	t1, -444(s0)
	bnez	t1, qsort_if.then8
	j    	qsort_if.end8
qsort_if.then8:
	lw  	t0, -448(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -448(s0)
	lw  	t0, -452(s0)
	lw  	t2, -68(s0)
	lw  	t0, 0(t2)
	sw  	t0, -452(s0)
	lw  	t0, -464(s0)
	li  	t0, 4
	sw  	t0, -464(s0)
	lw  	t0, -460(s0)
	lw  	t1, -452(s0)
	lw  	t2, -464(s0)
	mul 	t0, t1, t2
	sw  	t0, -460(s0)
	lw  	t0, -456(s0)
	lw  	t1, -460(s0)
	lw  	t2, -448(s0)
	add 	t0, t1, t2
	sw  	t0, -456(s0)
	lw  	t0, -468(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -468(s0)
	lw  	t0, -472(s0)
	lw  	t2, -60(s0)
	lw  	t0, 0(t2)
	sw  	t0, -472(s0)
	lw  	t0, -484(s0)
	li  	t0, 4
	sw  	t0, -484(s0)
	lw  	t0, -480(s0)
	lw  	t1, -472(s0)
	lw  	t2, -484(s0)
	mul 	t0, t1, t2
	sw  	t0, -480(s0)
	lw  	t0, -476(s0)
	lw  	t1, -480(s0)
	lw  	t2, -468(s0)
	add 	t0, t1, t2
	sw  	t0, -476(s0)
	lw  	t0, -488(s0)
	lw  	t2, -476(s0)
	lw  	t0, 0(t2)
	sw  	t0, -488(s0)
	lw  	t0, -488(s0)
	lw  	t2, -456(s0)
	sw  	t0, 0(t2)
	sw  	t0, -488(s0)
	lw  	t0, -492(s0)
	lw  	t2, -68(s0)
	lw  	t0, 0(t2)
	sw  	t0, -492(s0)
	lw  	t0, -500(s0)
	li  	t0, 1
	sw  	t0, -500(s0)
	lw  	t0, -496(s0)
	lw  	t1, -492(s0)
	lw  	t2, -500(s0)
	sub 	t0, t1, t2
	sw  	t0, -496(s0)
	lw  	t0, -496(s0)
	lw  	t2, -68(s0)
	sw  	t0, 0(t2)
	sw  	t0, -496(s0)
	j    	qsort_if.end8
qsort_if.end8:
	j    	qsort_while.begin2
qsort_while.end2:
	lw  	t0, -504(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -504(s0)
	lw  	t0, -508(s0)
	lw  	t2, -60(s0)
	lw  	t0, 0(t2)
	sw  	t0, -508(s0)
	lw  	t0, -520(s0)
	li  	t0, 4
	sw  	t0, -520(s0)
	lw  	t0, -516(s0)
	lw  	t1, -508(s0)
	lw  	t2, -520(s0)
	mul 	t0, t1, t2
	sw  	t0, -516(s0)
	lw  	t0, -512(s0)
	lw  	t1, -516(s0)
	lw  	t2, -504(s0)
	add 	t0, t1, t2
	sw  	t0, -512(s0)
	lw  	t0, -524(s0)
	lw  	t2, -76(s0)
	lw  	t0, 0(t2)
	sw  	t0, -524(s0)
	lw  	t0, -524(s0)
	lw  	t2, -512(s0)
	sw  	t0, 0(t2)
	sw  	t0, -524(s0)
	lw  	t0, -528(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -528(s0)
	lw  	t0, -532(s0)
	lw  	t2, -44(s0)
	lw  	t0, 0(t2)
	sw  	t0, -532(s0)
	lw  	t0, -536(s0)
	lw  	t2, -60(s0)
	lw  	t0, 0(t2)
	sw  	t0, -536(s0)
	lw  	t0, -544(s0)
	li  	t0, 1
	sw  	t0, -544(s0)
	lw  	t0, -540(s0)
	lw  	t1, -536(s0)
	lw  	t2, -544(s0)
	sub 	t0, t1, t2
	sw  	t0, -540(s0)
	lw  	t1, -528(s0)
	mv  	a0, t1
	lw  	t1, -532(s0)
	mv  	a1, t1
	lw  	t1, -540(s0)
	mv  	a2, t1
	call	qsort
	lw  	t0, -548(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -548(s0)
	lw  	t0, -552(s0)
	lw  	t2, -60(s0)
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
	lw  	t0, -564(s0)
	lw  	t2, -52(s0)
	lw  	t0, 0(t2)
	sw  	t0, -564(s0)
	lw  	t1, -548(s0)
	mv  	a0, t1
	lw  	t1, -556(s0)
	mv  	a1, t1
	lw  	t1, -564(s0)
	mv  	a2, t1
	call	qsort
	j    	qsort_if.end1
qsort_if.end1:
	j    	qsort_return
qsort_return:
	lw  	s0, 560(sp)
	lw  	ra, 564(sp)
	addi	sp, sp, 568
	ret
	.globl	init
init:
	addi	sp, sp, -236
	sw  	ra, 232(sp)
	sw  	s0, 228(sp)
	addi	s0, sp, 236
	lw  	t0, -16(s0)
	addi	t0, s0, -12
	sw  	t0, -16(s0)
	lw  	t0, -24(s0)
	addi	t0, s0, -20
	sw  	t0, -24(s0)
	lw  	t0, -32(s0)
	la  	t0, n
	sw  	t0, -32(s0)
	lw  	t0, -28(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -28(s0)
	lw  	t0, -40(s0)
	li  	t0, 1
	sw  	t0, -40(s0)
	lw  	t0, -36(s0)
	lw  	t1, -28(s0)
	lw  	t2, -40(s0)
	add 	t0, t1, t2
	sw  	t0, -36(s0)
	lw  	t0, -48(s0)
	li  	t0, 4
	sw  	t0, -48(s0)
	lw  	t0, -44(s0)
	lw  	t1, -36(s0)
	lw  	t2, -48(s0)
	mul 	t0, t1, t2
	sw  	t0, -44(s0)
	lw  	t0, -56(s0)
	li  	t0, 4
	sw  	t0, -56(s0)
	lw  	t0, -52(s0)
	lw  	t1, -44(s0)
	lw  	t2, -56(s0)
	add 	t0, t1, t2
	sw  	t0, -52(s0)
	lw  	t1, -52(s0)
	mv  	a0, t1
	call	__malloc
	lw  	t0, -60(s0)
	mv  	t0, a0
	sw  	t0, -60(s0)
	lw  	t0, -64(s0)
	lw  	t1, -60(s0)
	mv  	t0, t1
	sw  	t0, -64(s0)
	lw  	t0, -36(s0)
	lw  	t2, -64(s0)
	sw  	t0, 0(t2)
	sw  	t0, -36(s0)
	lw  	t0, -72(s0)
	li  	t0, 4
	sw  	t0, -72(s0)
	lw  	t0, -68(s0)
	lw  	t1, -72(s0)
	lw  	t2, -60(s0)
	add 	t0, t1, t2
	sw  	t0, -68(s0)
	lw  	t0, -76(s0)
	lw  	t1, -68(s0)
	mv  	t0, t1
	sw  	t0, -76(s0)
	lw  	t0, -80(s0)
	la  	t0, fa
	sw  	t0, -80(s0)
	lw  	t0, -76(s0)
	lw  	t2, -80(s0)
	sw  	t0, 0(t2)
	sw  	t0, -76(s0)
	lw  	t0, -88(s0)
	la  	t0, n
	sw  	t0, -88(s0)
	lw  	t0, -84(s0)
	lw  	t2, -88(s0)
	lw  	t0, 0(t2)
	sw  	t0, -84(s0)
	lw  	t0, -96(s0)
	li  	t0, 1
	sw  	t0, -96(s0)
	lw  	t0, -92(s0)
	lw  	t1, -84(s0)
	lw  	t2, -96(s0)
	add 	t0, t1, t2
	sw  	t0, -92(s0)
	lw  	t0, -104(s0)
	li  	t0, 4
	sw  	t0, -104(s0)
	lw  	t0, -100(s0)
	lw  	t1, -92(s0)
	lw  	t2, -104(s0)
	mul 	t0, t1, t2
	sw  	t0, -100(s0)
	lw  	t0, -112(s0)
	li  	t0, 4
	sw  	t0, -112(s0)
	lw  	t0, -108(s0)
	lw  	t1, -100(s0)
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
	lw  	t0, -92(s0)
	lw  	t2, -120(s0)
	sw  	t0, 0(t2)
	sw  	t0, -92(s0)
	lw  	t0, -128(s0)
	li  	t0, 4
	sw  	t0, -128(s0)
	lw  	t0, -124(s0)
	lw  	t1, -128(s0)
	lw  	t2, -116(s0)
	add 	t0, t1, t2
	sw  	t0, -124(s0)
	lw  	t0, -132(s0)
	lw  	t1, -124(s0)
	mv  	t0, t1
	sw  	t0, -132(s0)
	lw  	t0, -136(s0)
	la  	t0, rk
	sw  	t0, -136(s0)
	lw  	t0, -132(s0)
	lw  	t2, -136(s0)
	sw  	t0, 0(t2)
	sw  	t0, -132(s0)
	lw  	t0, -140(s0)
	li  	t0, 1
	sw  	t0, -140(s0)
	lw  	t0, -140(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -140(s0)
	j    	init_for.begin1
init_for.begin1:
	lw  	t0, -144(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -144(s0)
	lw  	t0, -152(s0)
	la  	t0, n
	sw  	t0, -152(s0)
	lw  	t0, -148(s0)
	lw  	t2, -152(s0)
	lw  	t0, 0(t2)
	sw  	t0, -148(s0)
	lw  	t0, -156(s0)
	lw  	t1, -148(s0)
	lw  	t2, -144(s0)
	slt 	t0, t1, t2
	sw  	t0, -156(s0)
	lw  	t0, -156(s0)
	lw  	t1, -156(s0)
	xori	t0, t1, 1
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
	bnez	t1, init_for.body1
	j    	init_for.end1
init_for.body1:
	lw  	t0, -172(s0)
	la  	t0, fa
	sw  	t0, -172(s0)
	lw  	t0, -168(s0)
	lw  	t2, -172(s0)
	lw  	t0, 0(t2)
	sw  	t0, -168(s0)
	lw  	t0, -176(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -176(s0)
	lw  	t0, -188(s0)
	li  	t0, 4
	sw  	t0, -188(s0)
	lw  	t0, -184(s0)
	lw  	t1, -176(s0)
	lw  	t2, -188(s0)
	mul 	t0, t1, t2
	sw  	t0, -184(s0)
	lw  	t0, -180(s0)
	lw  	t1, -184(s0)
	lw  	t2, -168(s0)
	add 	t0, t1, t2
	sw  	t0, -180(s0)
	lw  	t0, -192(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -192(s0)
	lw  	t0, -192(s0)
	lw  	t2, -180(s0)
	sw  	t0, 0(t2)
	sw  	t0, -192(s0)
	lw  	t0, -200(s0)
	la  	t0, rk
	sw  	t0, -200(s0)
	lw  	t0, -196(s0)
	lw  	t2, -200(s0)
	lw  	t0, 0(t2)
	sw  	t0, -196(s0)
	lw  	t0, -204(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -204(s0)
	lw  	t0, -216(s0)
	li  	t0, 4
	sw  	t0, -216(s0)
	lw  	t0, -212(s0)
	lw  	t1, -204(s0)
	lw  	t2, -216(s0)
	mul 	t0, t1, t2
	sw  	t0, -212(s0)
	lw  	t0, -208(s0)
	lw  	t1, -212(s0)
	lw  	t2, -196(s0)
	add 	t0, t1, t2
	sw  	t0, -208(s0)
	lw  	t0, -220(s0)
	li  	t0, 1
	sw  	t0, -220(s0)
	lw  	t0, -220(s0)
	lw  	t2, -208(s0)
	sw  	t0, 0(t2)
	sw  	t0, -220(s0)
	lw  	t0, -224(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -224(s0)
	lw  	t0, -232(s0)
	li  	t0, 1
	sw  	t0, -232(s0)
	lw  	t0, -228(s0)
	lw  	t1, -224(s0)
	lw  	t2, -232(s0)
	add 	t0, t1, t2
	sw  	t0, -228(s0)
	lw  	t0, -228(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -228(s0)
	j    	init_for.begin1
init_for.end1:
	j    	init_return
init_return:
	lw  	s0, 228(sp)
	lw  	ra, 232(sp)
	addi	sp, sp, 236
	ret
	.globl	find
find:
	addi	sp, sp, -152
	sw  	ra, 148(sp)
	sw  	s0, 144(sp)
	addi	s0, sp, 152
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
	lw  	t0, -40(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -40(s0)
	lw  	t0, -48(s0)
	la  	t0, fa
	sw  	t0, -48(s0)
	lw  	t0, -44(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -44(s0)
	lw  	t0, -52(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -52(s0)
	lw  	t0, -64(s0)
	li  	t0, 4
	sw  	t0, -64(s0)
	lw  	t0, -60(s0)
	lw  	t1, -52(s0)
	lw  	t2, -64(s0)
	mul 	t0, t1, t2
	sw  	t0, -60(s0)
	lw  	t0, -56(s0)
	lw  	t1, -60(s0)
	lw  	t2, -44(s0)
	add 	t0, t1, t2
	sw  	t0, -56(s0)
	lw  	t0, -68(s0)
	lw  	t2, -56(s0)
	lw  	t0, 0(t2)
	sw  	t0, -68(s0)
	lw  	t0, -72(s0)
	lw  	t1, -40(s0)
	lw  	t2, -68(s0)
	xor 	t0, t1, t2
	sw  	t0, -72(s0)
	lw  	t0, -72(s0)
	lw  	t1, -72(s0)
	seqz	t0, t1
	sw  	t0, -72(s0)
	lw  	t0, -76(s0)
	lw  	t1, -72(s0)
	mv  	t0, t1
	sw  	t0, -76(s0)
	lw  	t0, -80(s0)
	lw  	t1, -76(s0)
	mv  	t0, t1
	sw  	t0, -80(s0)
	lw  	t1, -80(s0)
	bnez	t1, find_if.then1
	j    	find_if.end1
find_if.then1:
	lw  	t0, -84(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -84(s0)
	lw  	t0, -84(s0)
	lw  	t2, -20(s0)
	sw  	t0, 0(t2)
	sw  	t0, -84(s0)
	j    	find_return
find_if.end1:
	lw  	t0, -92(s0)
	la  	t0, fa
	sw  	t0, -92(s0)
	lw  	t0, -88(s0)
	lw  	t2, -92(s0)
	lw  	t0, 0(t2)
	sw  	t0, -88(s0)
	lw  	t0, -96(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -96(s0)
	lw  	t0, -108(s0)
	li  	t0, 4
	sw  	t0, -108(s0)
	lw  	t0, -104(s0)
	lw  	t1, -96(s0)
	lw  	t2, -108(s0)
	mul 	t0, t1, t2
	sw  	t0, -104(s0)
	lw  	t0, -100(s0)
	lw  	t1, -104(s0)
	lw  	t2, -88(s0)
	add 	t0, t1, t2
	sw  	t0, -100(s0)
	lw  	t0, -112(s0)
	lw  	t2, -100(s0)
	lw  	t0, 0(t2)
	sw  	t0, -112(s0)
	lw  	t1, -112(s0)
	mv  	a0, t1
	call	find
	lw  	t0, -116(s0)
	mv  	t0, a0
	sw  	t0, -116(s0)
	lw  	t0, -116(s0)
	lw  	t2, -36(s0)
	sw  	t0, 0(t2)
	sw  	t0, -116(s0)
	lw  	t0, -124(s0)
	la  	t0, fa
	sw  	t0, -124(s0)
	lw  	t0, -120(s0)
	lw  	t2, -124(s0)
	lw  	t0, 0(t2)
	sw  	t0, -120(s0)
	lw  	t0, -128(s0)
	lw  	t2, -36(s0)
	lw  	t0, 0(t2)
	sw  	t0, -128(s0)
	lw  	t0, -140(s0)
	li  	t0, 4
	sw  	t0, -140(s0)
	lw  	t0, -136(s0)
	lw  	t1, -128(s0)
	lw  	t2, -140(s0)
	mul 	t0, t1, t2
	sw  	t0, -136(s0)
	lw  	t0, -132(s0)
	lw  	t1, -136(s0)
	lw  	t2, -120(s0)
	add 	t0, t1, t2
	sw  	t0, -132(s0)
	lw  	t0, -144(s0)
	lw  	t2, -132(s0)
	lw  	t0, 0(t2)
	sw  	t0, -144(s0)
	lw  	t0, -144(s0)
	lw  	t2, -20(s0)
	sw  	t0, 0(t2)
	sw  	t0, -144(s0)
	j    	find_return
find_return:
	lw  	t0, -148(s0)
	lw  	t2, -20(s0)
	lw  	t0, 0(t2)
	sw  	t0, -148(s0)
	lw  	t1, -148(s0)
	mv  	a0, t1
	lw  	s0, 144(sp)
	lw  	ra, 148(sp)
	addi	sp, sp, 152
	ret
	.globl	union
union:
	addi	sp, sp, -392
	sw  	ra, 388(sp)
	sw  	s0, 384(sp)
	addi	s0, sp, 392
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
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -12(s0)
	lw  	t0, -16(s0)
	lw  	t2, -48(s0)
	sw  	t0, 0(t2)
	sw  	t0, -16(s0)
	lw  	t0, -52(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -52(s0)
	lw  	t1, -52(s0)
	mv  	a0, t1
	call	find
	lw  	t0, -56(s0)
	mv  	t0, a0
	sw  	t0, -56(s0)
	lw  	t0, -56(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -56(s0)
	lw  	t0, -60(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -60(s0)
	lw  	t1, -60(s0)
	mv  	a0, t1
	call	find
	lw  	t0, -64(s0)
	mv  	t0, a0
	sw  	t0, -64(s0)
	lw  	t0, -64(s0)
	lw  	t2, -48(s0)
	sw  	t0, 0(t2)
	sw  	t0, -64(s0)
	lw  	t0, -68(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -68(s0)
	lw  	t0, -72(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -72(s0)
	lw  	t0, -76(s0)
	lw  	t1, -68(s0)
	lw  	t2, -72(s0)
	xor 	t0, t1, t2
	sw  	t0, -76(s0)
	lw  	t0, -76(s0)
	lw  	t1, -76(s0)
	seqz	t0, t1
	sw  	t0, -76(s0)
	lw  	t0, -80(s0)
	lw  	t1, -76(s0)
	mv  	t0, t1
	sw  	t0, -80(s0)
	lw  	t0, -84(s0)
	lw  	t1, -80(s0)
	mv  	t0, t1
	sw  	t0, -84(s0)
	lw  	t1, -84(s0)
	bnez	t1, union_if.then1
	j    	union_if.end1
union_if.then1:
	lw  	t0, -88(s0)
	li  	t0, 0
	sw  	t0, -88(s0)
	lw  	t0, -88(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -88(s0)
	j    	union_return
union_if.end1:
	lw  	t0, -96(s0)
	la  	t0, rk
	sw  	t0, -96(s0)
	lw  	t0, -92(s0)
	lw  	t2, -96(s0)
	lw  	t0, 0(t2)
	sw  	t0, -92(s0)
	lw  	t0, -100(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -100(s0)
	lw  	t0, -112(s0)
	li  	t0, 4
	sw  	t0, -112(s0)
	lw  	t0, -108(s0)
	lw  	t1, -100(s0)
	lw  	t2, -112(s0)
	mul 	t0, t1, t2
	sw  	t0, -108(s0)
	lw  	t0, -104(s0)
	lw  	t1, -108(s0)
	lw  	t2, -92(s0)
	add 	t0, t1, t2
	sw  	t0, -104(s0)
	lw  	t0, -116(s0)
	lw  	t2, -104(s0)
	lw  	t0, 0(t2)
	sw  	t0, -116(s0)
	lw  	t0, -124(s0)
	la  	t0, rk
	sw  	t0, -124(s0)
	lw  	t0, -120(s0)
	lw  	t2, -124(s0)
	lw  	t0, 0(t2)
	sw  	t0, -120(s0)
	lw  	t0, -128(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -128(s0)
	lw  	t0, -140(s0)
	li  	t0, 4
	sw  	t0, -140(s0)
	lw  	t0, -136(s0)
	lw  	t1, -128(s0)
	lw  	t2, -140(s0)
	mul 	t0, t1, t2
	sw  	t0, -136(s0)
	lw  	t0, -132(s0)
	lw  	t1, -136(s0)
	lw  	t2, -120(s0)
	add 	t0, t1, t2
	sw  	t0, -132(s0)
	lw  	t0, -144(s0)
	lw  	t2, -132(s0)
	lw  	t0, 0(t2)
	sw  	t0, -144(s0)
	lw  	t0, -148(s0)
	lw  	t1, -144(s0)
	lw  	t2, -116(s0)
	slt 	t0, t1, t2
	sw  	t0, -148(s0)
	lw  	t0, -152(s0)
	lw  	t1, -148(s0)
	mv  	t0, t1
	sw  	t0, -152(s0)
	lw  	t0, -156(s0)
	lw  	t1, -152(s0)
	mv  	t0, t1
	sw  	t0, -156(s0)
	lw  	t1, -156(s0)
	bnez	t1, union_if.then2
	j    	union_if.else2
union_if.then2:
	lw  	t0, -164(s0)
	la  	t0, fa
	sw  	t0, -164(s0)
	lw  	t0, -160(s0)
	lw  	t2, -164(s0)
	lw  	t0, 0(t2)
	sw  	t0, -160(s0)
	lw  	t0, -168(s0)
	lw  	t2, -48(s0)
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
	lw  	t2, -160(s0)
	add 	t0, t1, t2
	sw  	t0, -172(s0)
	lw  	t0, -184(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -184(s0)
	lw  	t0, -184(s0)
	lw  	t2, -172(s0)
	sw  	t0, 0(t2)
	sw  	t0, -184(s0)
	lw  	t0, -192(s0)
	la  	t0, rk
	sw  	t0, -192(s0)
	lw  	t0, -188(s0)
	lw  	t2, -192(s0)
	lw  	t0, 0(t2)
	sw  	t0, -188(s0)
	lw  	t0, -196(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -196(s0)
	lw  	t0, -208(s0)
	li  	t0, 4
	sw  	t0, -208(s0)
	lw  	t0, -204(s0)
	lw  	t1, -196(s0)
	lw  	t2, -208(s0)
	mul 	t0, t1, t2
	sw  	t0, -204(s0)
	lw  	t0, -200(s0)
	lw  	t1, -204(s0)
	lw  	t2, -188(s0)
	add 	t0, t1, t2
	sw  	t0, -200(s0)
	lw  	t0, -216(s0)
	la  	t0, rk
	sw  	t0, -216(s0)
	lw  	t0, -212(s0)
	lw  	t2, -216(s0)
	lw  	t0, 0(t2)
	sw  	t0, -212(s0)
	lw  	t0, -220(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -220(s0)
	lw  	t0, -232(s0)
	li  	t0, 4
	sw  	t0, -232(s0)
	lw  	t0, -228(s0)
	lw  	t1, -220(s0)
	lw  	t2, -232(s0)
	mul 	t0, t1, t2
	sw  	t0, -228(s0)
	lw  	t0, -224(s0)
	lw  	t1, -228(s0)
	lw  	t2, -212(s0)
	add 	t0, t1, t2
	sw  	t0, -224(s0)
	lw  	t0, -236(s0)
	lw  	t2, -224(s0)
	lw  	t0, 0(t2)
	sw  	t0, -236(s0)
	lw  	t0, -244(s0)
	la  	t0, rk
	sw  	t0, -244(s0)
	lw  	t0, -240(s0)
	lw  	t2, -244(s0)
	lw  	t0, 0(t2)
	sw  	t0, -240(s0)
	lw  	t0, -248(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -248(s0)
	lw  	t0, -260(s0)
	li  	t0, 4
	sw  	t0, -260(s0)
	lw  	t0, -256(s0)
	lw  	t1, -248(s0)
	lw  	t2, -260(s0)
	mul 	t0, t1, t2
	sw  	t0, -256(s0)
	lw  	t0, -252(s0)
	lw  	t1, -256(s0)
	lw  	t2, -240(s0)
	add 	t0, t1, t2
	sw  	t0, -252(s0)
	lw  	t0, -264(s0)
	lw  	t2, -252(s0)
	lw  	t0, 0(t2)
	sw  	t0, -264(s0)
	lw  	t0, -268(s0)
	lw  	t1, -236(s0)
	lw  	t2, -264(s0)
	add 	t0, t1, t2
	sw  	t0, -268(s0)
	lw  	t0, -268(s0)
	lw  	t2, -200(s0)
	sw  	t0, 0(t2)
	sw  	t0, -268(s0)
	j    	union_if.end2
union_if.else2:
	lw  	t0, -276(s0)
	la  	t0, fa
	sw  	t0, -276(s0)
	lw  	t0, -272(s0)
	lw  	t2, -276(s0)
	lw  	t0, 0(t2)
	sw  	t0, -272(s0)
	lw  	t0, -280(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -280(s0)
	lw  	t0, -292(s0)
	li  	t0, 4
	sw  	t0, -292(s0)
	lw  	t0, -288(s0)
	lw  	t1, -280(s0)
	lw  	t2, -292(s0)
	mul 	t0, t1, t2
	sw  	t0, -288(s0)
	lw  	t0, -284(s0)
	lw  	t1, -288(s0)
	lw  	t2, -272(s0)
	add 	t0, t1, t2
	sw  	t0, -284(s0)
	lw  	t0, -296(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -296(s0)
	lw  	t0, -296(s0)
	lw  	t2, -284(s0)
	sw  	t0, 0(t2)
	sw  	t0, -296(s0)
	lw  	t0, -304(s0)
	la  	t0, rk
	sw  	t0, -304(s0)
	lw  	t0, -300(s0)
	lw  	t2, -304(s0)
	lw  	t0, 0(t2)
	sw  	t0, -300(s0)
	lw  	t0, -308(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -308(s0)
	lw  	t0, -320(s0)
	li  	t0, 4
	sw  	t0, -320(s0)
	lw  	t0, -316(s0)
	lw  	t1, -308(s0)
	lw  	t2, -320(s0)
	mul 	t0, t1, t2
	sw  	t0, -316(s0)
	lw  	t0, -312(s0)
	lw  	t1, -316(s0)
	lw  	t2, -300(s0)
	add 	t0, t1, t2
	sw  	t0, -312(s0)
	lw  	t0, -328(s0)
	la  	t0, rk
	sw  	t0, -328(s0)
	lw  	t0, -324(s0)
	lw  	t2, -328(s0)
	lw  	t0, 0(t2)
	sw  	t0, -324(s0)
	lw  	t0, -332(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -332(s0)
	lw  	t0, -344(s0)
	li  	t0, 4
	sw  	t0, -344(s0)
	lw  	t0, -340(s0)
	lw  	t1, -332(s0)
	lw  	t2, -344(s0)
	mul 	t0, t1, t2
	sw  	t0, -340(s0)
	lw  	t0, -336(s0)
	lw  	t1, -340(s0)
	lw  	t2, -324(s0)
	add 	t0, t1, t2
	sw  	t0, -336(s0)
	lw  	t0, -348(s0)
	lw  	t2, -336(s0)
	lw  	t0, 0(t2)
	sw  	t0, -348(s0)
	lw  	t0, -356(s0)
	la  	t0, rk
	sw  	t0, -356(s0)
	lw  	t0, -352(s0)
	lw  	t2, -356(s0)
	lw  	t0, 0(t2)
	sw  	t0, -352(s0)
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
	lw  	t2, -352(s0)
	add 	t0, t1, t2
	sw  	t0, -364(s0)
	lw  	t0, -376(s0)
	lw  	t2, -364(s0)
	lw  	t0, 0(t2)
	sw  	t0, -376(s0)
	lw  	t0, -380(s0)
	lw  	t1, -348(s0)
	lw  	t2, -376(s0)
	add 	t0, t1, t2
	sw  	t0, -380(s0)
	lw  	t0, -380(s0)
	lw  	t2, -312(s0)
	sw  	t0, 0(t2)
	sw  	t0, -380(s0)
	j    	union_if.end2
union_if.end2:
	lw  	t0, -384(s0)
	li  	t0, 1
	sw  	t0, -384(s0)
	lw  	t0, -384(s0)
	lw  	t2, -24(s0)
	sw  	t0, 0(t2)
	sw  	t0, -384(s0)
	j    	union_return
union_return:
	lw  	t0, -388(s0)
	lw  	t2, -24(s0)
	lw  	t0, 0(t2)
	sw  	t0, -388(s0)
	lw  	t1, -388(s0)
	mv  	a0, t1
	lw  	s0, 384(sp)
	lw  	ra, 388(sp)
	addi	sp, sp, 392
	ret
	.globl	main
main:
	addi	sp, sp, -564
	sw  	ra, 560(sp)
	sw  	s0, 556(sp)
	addi	s0, sp, 564
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
	call	__mx_initialize_globalVar
	call	getInt
	lw  	t0, -60(s0)
	mv  	t0, a0
	sw  	t0, -60(s0)
	lw  	t0, -64(s0)
	la  	t0, n
	sw  	t0, -64(s0)
	lw  	t0, -60(s0)
	lw  	t2, -64(s0)
	sw  	t0, 0(t2)
	sw  	t0, -60(s0)
	call	getInt
	lw  	t0, -68(s0)
	mv  	t0, a0
	sw  	t0, -68(s0)
	lw  	t0, -72(s0)
	la  	t0, m
	sw  	t0, -72(s0)
	lw  	t0, -68(s0)
	lw  	t2, -72(s0)
	sw  	t0, 0(t2)
	sw  	t0, -68(s0)
	lw  	t0, -80(s0)
	la  	t0, m
	sw  	t0, -80(s0)
	lw  	t0, -76(s0)
	lw  	t2, -80(s0)
	lw  	t0, 0(t2)
	sw  	t0, -76(s0)
	lw  	t0, -88(s0)
	li  	t0, 4
	sw  	t0, -88(s0)
	lw  	t0, -84(s0)
	lw  	t1, -76(s0)
	lw  	t2, -88(s0)
	mul 	t0, t1, t2
	sw  	t0, -84(s0)
	lw  	t0, -96(s0)
	li  	t0, 4
	sw  	t0, -96(s0)
	lw  	t0, -92(s0)
	lw  	t1, -84(s0)
	lw  	t2, -96(s0)
	add 	t0, t1, t2
	sw  	t0, -92(s0)
	lw  	t1, -92(s0)
	mv  	a0, t1
	call	__malloc
	lw  	t0, -100(s0)
	mv  	t0, a0
	sw  	t0, -100(s0)
	lw  	t0, -104(s0)
	lw  	t1, -100(s0)
	mv  	t0, t1
	sw  	t0, -104(s0)
	lw  	t0, -76(s0)
	lw  	t2, -104(s0)
	sw  	t0, 0(t2)
	sw  	t0, -76(s0)
	lw  	t0, -112(s0)
	li  	t0, 4
	sw  	t0, -112(s0)
	lw  	t0, -108(s0)
	lw  	t1, -112(s0)
	lw  	t2, -100(s0)
	add 	t0, t1, t2
	sw  	t0, -108(s0)
	lw  	t0, -116(s0)
	lw  	t1, -108(s0)
	mv  	t0, t1
	sw  	t0, -116(s0)
	lw  	t0, -120(s0)
	la  	t0, e
	sw  	t0, -120(s0)
	lw  	t0, -116(s0)
	lw  	t2, -120(s0)
	sw  	t0, 0(t2)
	sw  	t0, -116(s0)
	lw  	t0, -124(s0)
	li  	t0, 0
	sw  	t0, -124(s0)
	lw  	t0, -124(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -124(s0)
	j    	main_for.begin1
main_for.begin1:
	lw  	t0, -128(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -128(s0)
	lw  	t0, -136(s0)
	la  	t0, m
	sw  	t0, -136(s0)
	lw  	t0, -132(s0)
	lw  	t2, -136(s0)
	lw  	t0, 0(t2)
	sw  	t0, -132(s0)
	lw  	t0, -140(s0)
	lw  	t1, -128(s0)
	lw  	t2, -132(s0)
	slt 	t0, t1, t2
	sw  	t0, -140(s0)
	lw  	t0, -144(s0)
	lw  	t1, -140(s0)
	mv  	t0, t1
	sw  	t0, -144(s0)
	lw  	t0, -148(s0)
	lw  	t1, -144(s0)
	mv  	t0, t1
	sw  	t0, -148(s0)
	lw  	t1, -148(s0)
	bnez	t1, main_for.body1
	j    	main_for.end1
main_for.body1:
	lw  	t0, -152(s0)
	li  	t0, 12
	sw  	t0, -152(s0)
	lw  	t1, -152(s0)
	mv  	a0, t1
	call	__malloc
	lw  	t0, -156(s0)
	mv  	t0, a0
	sw  	t0, -156(s0)
	lw  	t0, -160(s0)
	lw  	t1, -156(s0)
	mv  	t0, t1
	sw  	t0, -160(s0)
	lw  	t0, -160(s0)
	lw  	t2, -40(s0)
	sw  	t0, 0(t2)
	sw  	t0, -160(s0)
	lw  	t0, -164(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -164(s0)
	lw  	t0, -172(s0)
	li  	t0, 0
	sw  	t0, -172(s0)
	lw  	t0, -168(s0)
	lw  	t1, -172(s0)
	lw  	t2, -164(s0)
	add 	t0, t1, t2
	sw  	t0, -168(s0)
	call	getInt
	lw  	t0, -176(s0)
	mv  	t0, a0
	sw  	t0, -176(s0)
	lw  	t0, -176(s0)
	lw  	t2, -168(s0)
	sw  	t0, 0(t2)
	sw  	t0, -176(s0)
	lw  	t0, -180(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -180(s0)
	lw  	t0, -188(s0)
	li  	t0, 4
	sw  	t0, -188(s0)
	lw  	t0, -184(s0)
	lw  	t1, -188(s0)
	lw  	t2, -180(s0)
	add 	t0, t1, t2
	sw  	t0, -184(s0)
	call	getInt
	lw  	t0, -192(s0)
	mv  	t0, a0
	sw  	t0, -192(s0)
	lw  	t0, -192(s0)
	lw  	t2, -184(s0)
	sw  	t0, 0(t2)
	sw  	t0, -192(s0)
	lw  	t0, -196(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -196(s0)
	lw  	t0, -204(s0)
	li  	t0, 8
	sw  	t0, -204(s0)
	lw  	t0, -200(s0)
	lw  	t1, -204(s0)
	lw  	t2, -196(s0)
	add 	t0, t1, t2
	sw  	t0, -200(s0)
	call	getInt
	lw  	t0, -208(s0)
	mv  	t0, a0
	sw  	t0, -208(s0)
	lw  	t0, -208(s0)
	lw  	t2, -200(s0)
	sw  	t0, 0(t2)
	sw  	t0, -208(s0)
	lw  	t0, -216(s0)
	la  	t0, e
	sw  	t0, -216(s0)
	lw  	t0, -212(s0)
	lw  	t2, -216(s0)
	lw  	t0, 0(t2)
	sw  	t0, -212(s0)
	lw  	t0, -220(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -220(s0)
	lw  	t0, -232(s0)
	li  	t0, 4
	sw  	t0, -232(s0)
	lw  	t0, -228(s0)
	lw  	t1, -220(s0)
	lw  	t2, -232(s0)
	mul 	t0, t1, t2
	sw  	t0, -228(s0)
	lw  	t0, -224(s0)
	lw  	t1, -228(s0)
	lw  	t2, -212(s0)
	add 	t0, t1, t2
	sw  	t0, -224(s0)
	lw  	t0, -236(s0)
	lw  	t2, -40(s0)
	lw  	t0, 0(t2)
	sw  	t0, -236(s0)
	lw  	t0, -236(s0)
	lw  	t2, -224(s0)
	sw  	t0, 0(t2)
	sw  	t0, -236(s0)
	lw  	t0, -240(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -240(s0)
	lw  	t0, -248(s0)
	li  	t0, 1
	sw  	t0, -248(s0)
	lw  	t0, -244(s0)
	lw  	t1, -240(s0)
	lw  	t2, -248(s0)
	add 	t0, t1, t2
	sw  	t0, -244(s0)
	lw  	t0, -244(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -244(s0)
	j    	main_for.begin1
main_for.end1:
	lw  	t0, -256(s0)
	la  	t0, e
	sw  	t0, -256(s0)
	lw  	t0, -252(s0)
	lw  	t2, -256(s0)
	lw  	t0, 0(t2)
	sw  	t0, -252(s0)
	lw  	t0, -264(s0)
	la  	t0, m
	sw  	t0, -264(s0)
	lw  	t0, -260(s0)
	lw  	t2, -264(s0)
	lw  	t0, 0(t2)
	sw  	t0, -260(s0)
	lw  	t0, -272(s0)
	li  	t0, 1
	sw  	t0, -272(s0)
	lw  	t0, -268(s0)
	lw  	t1, -260(s0)
	lw  	t2, -272(s0)
	sub 	t0, t1, t2
	sw  	t0, -268(s0)
	lw  	t1, -252(s0)
	mv  	a0, t1
	lw  	t0, -276(s0)
	li  	t0, 0
	sw  	t0, -276(s0)
	lw  	t1, -276(s0)
	mv  	a1, t1
	lw  	t1, -268(s0)
	mv  	a2, t1
	call	qsort
	call	init
	lw  	t0, -280(s0)
	li  	t0, 0
	sw  	t0, -280(s0)
	lw  	t0, -280(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -280(s0)
	lw  	t0, -284(s0)
	li  	t0, 0
	sw  	t0, -284(s0)
	lw  	t0, -284(s0)
	lw  	t2, -48(s0)
	sw  	t0, 0(t2)
	sw  	t0, -284(s0)
	j    	main_while.begin2
main_while.begin2:
	lw  	t0, -288(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -288(s0)
	lw  	t0, -296(s0)
	la  	t0, n
	sw  	t0, -296(s0)
	lw  	t0, -292(s0)
	lw  	t2, -296(s0)
	lw  	t0, 0(t2)
	sw  	t0, -292(s0)
	lw  	t0, -304(s0)
	li  	t0, 1
	sw  	t0, -304(s0)
	lw  	t0, -300(s0)
	lw  	t1, -292(s0)
	lw  	t2, -304(s0)
	sub 	t0, t1, t2
	sw  	t0, -300(s0)
	lw  	t0, -308(s0)
	lw  	t1, -288(s0)
	lw  	t2, -300(s0)
	slt 	t0, t1, t2
	sw  	t0, -308(s0)
	lw  	t0, -312(s0)
	lw  	t1, -308(s0)
	mv  	t0, t1
	sw  	t0, -312(s0)
	lw  	t0, -316(s0)
	lw  	t1, -312(s0)
	mv  	t0, t1
	sw  	t0, -316(s0)
	lw  	t1, -316(s0)
	bnez	t1, main_while.body2
	j    	main_while.end2
main_while.body2:
	lw  	t0, -320(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -320(s0)
	lw  	t0, -328(s0)
	la  	t0, m
	sw  	t0, -328(s0)
	lw  	t0, -324(s0)
	lw  	t2, -328(s0)
	lw  	t0, 0(t2)
	sw  	t0, -324(s0)
	lw  	t0, -332(s0)
	lw  	t1, -320(s0)
	lw  	t2, -324(s0)
	slt 	t0, t1, t2
	sw  	t0, -332(s0)
	lw  	t0, -332(s0)
	lw  	t1, -332(s0)
	xori	t0, t1, 1
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
	bnez	t1, main_if.then3
	j    	main_if.end3
main_if.then3:
	lw  	t0, -348(s0)
	li  	t0, 0
	sw  	t0, -348(s0)
	lw  	t0, -352(s0)
	li  	t0, 1
	sw  	t0, -352(s0)
	lw  	t0, -344(s0)
	lw  	t1, -348(s0)
	lw  	t2, -352(s0)
	sub 	t0, t1, t2
	sw  	t0, -344(s0)
	lw  	t1, -344(s0)
	mv  	a0, t1
	call	printInt
	lw  	t0, -356(s0)
	li  	t0, 0
	sw  	t0, -356(s0)
	lw  	t0, -356(s0)
	lw  	t2, -16(s0)
	sw  	t0, 0(t2)
	sw  	t0, -356(s0)
	j    	main_return
main_if.end3:
	lw  	t0, -364(s0)
	la  	t0, e
	sw  	t0, -364(s0)
	lw  	t0, -360(s0)
	lw  	t2, -364(s0)
	lw  	t0, 0(t2)
	sw  	t0, -360(s0)
	lw  	t0, -368(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -368(s0)
	lw  	t0, -380(s0)
	li  	t0, 4
	sw  	t0, -380(s0)
	lw  	t0, -376(s0)
	lw  	t1, -368(s0)
	lw  	t2, -380(s0)
	mul 	t0, t1, t2
	sw  	t0, -376(s0)
	lw  	t0, -372(s0)
	lw  	t1, -376(s0)
	lw  	t2, -360(s0)
	add 	t0, t1, t2
	sw  	t0, -372(s0)
	lw  	t0, -384(s0)
	lw  	t2, -372(s0)
	lw  	t0, 0(t2)
	sw  	t0, -384(s0)
	lw  	t0, -384(s0)
	lw  	t2, -56(s0)
	sw  	t0, 0(t2)
	sw  	t0, -384(s0)
	lw  	t0, -388(s0)
	lw  	t2, -48(s0)
	lw  	t0, 0(t2)
	sw  	t0, -388(s0)
	lw  	t0, -396(s0)
	li  	t0, 1
	sw  	t0, -396(s0)
	lw  	t0, -392(s0)
	lw  	t1, -388(s0)
	lw  	t2, -396(s0)
	add 	t0, t1, t2
	sw  	t0, -392(s0)
	lw  	t0, -392(s0)
	lw  	t2, -48(s0)
	sw  	t0, 0(t2)
	sw  	t0, -392(s0)
	lw  	t0, -400(s0)
	lw  	t2, -56(s0)
	lw  	t0, 0(t2)
	sw  	t0, -400(s0)
	lw  	t0, -408(s0)
	li  	t0, 0
	sw  	t0, -408(s0)
	lw  	t0, -404(s0)
	lw  	t1, -408(s0)
	lw  	t2, -400(s0)
	add 	t0, t1, t2
	sw  	t0, -404(s0)
	lw  	t0, -412(s0)
	lw  	t2, -404(s0)
	lw  	t0, 0(t2)
	sw  	t0, -412(s0)
	lw  	t0, -416(s0)
	lw  	t2, -56(s0)
	lw  	t0, 0(t2)
	sw  	t0, -416(s0)
	lw  	t0, -424(s0)
	li  	t0, 4
	sw  	t0, -424(s0)
	lw  	t0, -420(s0)
	lw  	t1, -424(s0)
	lw  	t2, -416(s0)
	add 	t0, t1, t2
	sw  	t0, -420(s0)
	lw  	t0, -428(s0)
	lw  	t2, -420(s0)
	lw  	t0, 0(t2)
	sw  	t0, -428(s0)
	lw  	t1, -412(s0)
	mv  	a0, t1
	lw  	t1, -428(s0)
	mv  	a1, t1
	call	union
	lw  	t0, -432(s0)
	mv  	t0, a0
	sw  	t0, -432(s0)
	lw  	t0, -436(s0)
	lw  	t1, -432(s0)
	mv  	t0, t1
	sw  	t0, -436(s0)
	lw  	t1, -436(s0)
	bnez	t1, main_if.then4
	j    	main_if.end4
main_if.then4:
	lw  	t0, -440(s0)
	lw  	t2, -32(s0)
	lw  	t0, 0(t2)
	sw  	t0, -440(s0)
	lw  	t0, -448(s0)
	li  	t0, 1
	sw  	t0, -448(s0)
	lw  	t0, -444(s0)
	lw  	t1, -440(s0)
	lw  	t2, -448(s0)
	add 	t0, t1, t2
	sw  	t0, -444(s0)
	lw  	t0, -444(s0)
	lw  	t2, -32(s0)
	sw  	t0, 0(t2)
	sw  	t0, -444(s0)
	lw  	t0, -456(s0)
	la  	t0, ans
	sw  	t0, -456(s0)
	lw  	t0, -452(s0)
	lw  	t2, -456(s0)
	lw  	t0, 0(t2)
	sw  	t0, -452(s0)
	lw  	t0, -460(s0)
	lw  	t2, -56(s0)
	lw  	t0, 0(t2)
	sw  	t0, -460(s0)
	lw  	t0, -468(s0)
	li  	t0, 8
	sw  	t0, -468(s0)
	lw  	t0, -464(s0)
	lw  	t1, -468(s0)
	lw  	t2, -460(s0)
	add 	t0, t1, t2
	sw  	t0, -464(s0)
	lw  	t0, -472(s0)
	lw  	t2, -464(s0)
	lw  	t0, 0(t2)
	sw  	t0, -472(s0)
	lw  	t0, -476(s0)
	lw  	t1, -452(s0)
	lw  	t2, -472(s0)
	add 	t0, t1, t2
	sw  	t0, -476(s0)
	lw  	t0, -480(s0)
	la  	t0, ans
	sw  	t0, -480(s0)
	lw  	t0, -476(s0)
	lw  	t2, -480(s0)
	sw  	t0, 0(t2)
	sw  	t0, -476(s0)
	j    	main_if.end4
main_if.end4:
	j    	main_while.begin2
main_while.end2:
	lw  	t0, -488(s0)
	la  	t0, rk
	sw  	t0, -488(s0)
	lw  	t0, -484(s0)
	lw  	t2, -488(s0)
	lw  	t0, 0(t2)
	sw  	t0, -484(s0)
	lw  	t0, -492(s0)
	li  	t0, 1
	sw  	t0, -492(s0)
	lw  	t1, -492(s0)
	mv  	a0, t1
	call	find
	lw  	t0, -496(s0)
	mv  	t0, a0
	sw  	t0, -496(s0)
	lw  	t0, -508(s0)
	li  	t0, 4
	sw  	t0, -508(s0)
	lw  	t0, -504(s0)
	lw  	t1, -496(s0)
	lw  	t2, -508(s0)
	mul 	t0, t1, t2
	sw  	t0, -504(s0)
	lw  	t0, -500(s0)
	lw  	t1, -504(s0)
	lw  	t2, -484(s0)
	add 	t0, t1, t2
	sw  	t0, -500(s0)
	lw  	t0, -512(s0)
	lw  	t2, -500(s0)
	lw  	t0, 0(t2)
	sw  	t0, -512(s0)
	lw  	t0, -520(s0)
	la  	t0, n
	sw  	t0, -520(s0)
	lw  	t0, -516(s0)
	lw  	t2, -520(s0)
	lw  	t0, 0(t2)
	sw  	t0, -516(s0)
	lw  	t0, -524(s0)
	lw  	t1, -512(s0)
	lw  	t2, -516(s0)
	xor 	t0, t1, t2
	sw  	t0, -524(s0)
	lw  	t0, -524(s0)
	lw  	t1, -524(s0)
	seqz	t0, t1
	sw  	t0, -524(s0)
	lw  	t0, -528(s0)
	lw  	t1, -524(s0)
	mv  	t0, t1
	sw  	t0, -528(s0)
	lw  	t0, -532(s0)
	lw  	t1, -528(s0)
	mv  	t0, t1
	sw  	t0, -532(s0)
	lw  	t1, -532(s0)
	bnez	t1, main_if.then5
	j    	main_if.else5
main_if.then5:
	lw  	t0, -540(s0)
	la  	t0, ans
	sw  	t0, -540(s0)
	lw  	t0, -536(s0)
	lw  	t2, -540(s0)
	lw  	t0, 0(t2)
	sw  	t0, -536(s0)
	lw  	t1, -536(s0)
	mv  	a0, t1
	call	printInt
	j    	main_if.end5
main_if.else5:
	lw  	t0, -548(s0)
	li  	t0, 0
	sw  	t0, -548(s0)
	lw  	t0, -552(s0)
	li  	t0, 1
	sw  	t0, -552(s0)
	lw  	t0, -544(s0)
	lw  	t1, -548(s0)
	lw  	t2, -552(s0)
	sub 	t0, t1, t2
	sw  	t0, -544(s0)
	lw  	t1, -544(s0)
	mv  	a0, t1
	call	printInt
	j    	main_if.end5
main_if.end5:
	lw  	t0, -556(s0)
	li  	t0, 0
	sw  	t0, -556(s0)
	lw  	t0, -556(s0)
	lw  	t2, -16(s0)
	sw  	t0, 0(t2)
	sw  	t0, -556(s0)
	j    	main_return
main_return:
	lw  	t0, -560(s0)
	lw  	t2, -16(s0)
	lw  	t0, 0(t2)
	sw  	t0, -560(s0)
	lw  	t1, -560(s0)
	mv  	a0, t1
	lw  	s0, 556(sp)
	lw  	ra, 560(sp)
	addi	sp, sp, 564
	ret
	.section	.data
	.globl	n
n:
	.word	0
	.section	.data
	.globl	m
m:
	.word	0
	.section	.data
	.globl	ans
ans:
	.word	0
	.section	.data
	.globl	fa
fa:
	.word	0
	.section	.data
	.globl	rk
rk:
	.word	0
	.section	.data
	.globl	e
e:
	.word	0
