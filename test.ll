@n = global i32 0, align 4
@p = global i32 0, align 4
@k = global i32 0, align 4
@i = global i32 0, align 4

@.str = private unnamed_addr constant [4 x i8] c"!!!\00", align 1
@.str.1 = private unnamed_addr constant [4 x i8] c"???\00", align 1
@.str.2 = private unnamed_addr constant [4 x i8] c"...\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"000\00", align 1
@.str.4 = private unnamed_addr constant [4 x i8] c"<< \00", align 1
@.str.5 = private unnamed_addr constant [2 x i8] c"(\00", align 1
@.str.6 = private unnamed_addr constant [3 x i8] c") \00", align 1
@.str.7 = private unnamed_addr constant [2 x i8] c" \00", align 1
@.str.8 = private unnamed_addr constant [4 x i8] c">> \00", align 1

declare i32 @getInt ()

declare void @print (i8*)

declare void @println (i8*)

declare void @printInt (i32)

declare i8* @toString (i32)

declare i8* @getString ()

declare i8* @__malloc (i32)

declare i1 @__str_eq (i8*, i8*)

declare i1 @__str_ne (i8*, i8*)

declare i1 @__str_slt (i8*, i8*)

declare i1 @__str_sle (i8*, i8*)

declare i1 @__str_sgt (i8*, i8*)

declare i1 @__str_sge (i8*, i8*)

declare i8* @__str_add (i8*, i8*)

define void @__mx_initialize_globalVar () {
entry:
  store i32 1, i32* @i, align 4
  ret void

}

define i32 @add (i32 %a, i32 %b) {
entry:
  %retval = alloca i32, align 4
  %a.addr = alloca i32, align 4
  store i32 %a, i32* %a.addr, align 4
  %b.addr = alloca i32, align 4
  store i32 %b, i32* %b.addr, align 4
  %0 = load i32, i32* %a.addr, align 4
  %1 = icmp sgt i32 %0, 0
  br i1 %1, label %if.then1, label %if.else1

if.then1:
  %2 = load i32, i32* %a.addr, align 4
  store i32 %2, i32* %retval, align 4
  br label %return

if.else1:
  %3 = load i32, i32* %b.addr, align 4
  store i32 %3, i32* %retval, align 4
  br label %return

if.end1:
  br label %return

return:
  %4 = load i32, i32* %retval, align 4
  ret i32 %4
}

define i32 @main () {
entry:
  call void @__mx_initialize_globalVar()
  %.phi = alloca i1, align 1
  %retval = alloca i32, align 4
  %aaa = alloca i8*, align 1
  store i8* %.str, i8** %aaa, align 4
  %bbb = alloca i8*, align 1
  store i8* %.str.1, i8** %bbb, align 4
  %c = alloca i1, align 1
  %0 = load i8*, i8** %aaa, align 1
  %1 = load i8*, i8** %bbb, align 1
  %2 = call i1 @__str_sgt(i8* %0, i8* %1)
  store i1 %2, i1* %c, align 1
  %dd = alloca i1, align 1
  %3 = call i1 @__str_sgt(i8* %.str.2, i8* %.str.3)
  store i1 %3, i1* %dd, align 1
  %4 = call i32 @getInt()
  store i32 %4, i32* @n, align 4
  %5 = call i32 @getInt()
  store i32 %5, i32* @p, align 4
  %6 = call i32 @getInt()
  store i32 %6, i32* @k, align 4
  %a = alloca i32**, align 4
  %7 = mul i32 2, 4
  %8 = add i32 %7, 4
  %9 = call i8* @__malloc(i32 %8)
  %10 = getelementptr inbounds i32**, i32*** %9, i32 0
  %_new_i.0.1 = alloca i32, align 4
  store i32 1, i32* %_new_i.0.1, align 4
  br label %array.for.begin1

array.for.begin1:
  %11 = icmp slt i32* %_new_i.0.1, %8
  br i32 %11, label %array.for.body1, label %array.for.end1

array.for.body1:
  %12 = getelementptr inbounds i32**, i32*** %9, i32* %_new_i.0.1
  %13 = mul i32 3, 4
  %14 = add i32 %13, 4
  %15 = call i8* @__malloc(i32 %14)
  %16 = getelementptr inbounds i32*, i32** %15, i32 0
  store i32** %15, i32* %12, align 4
  %17 = load i32, i32* %_new_i.0.1, align 4
  %17 = add i32* %_new_i.0.1, 1
  br label %array.for.begin1

array.for.end1:
  store i32*** %9, i32*** %a, align 4
  %18 = load i32, i32* @p, align 4
  %19 = load i32, i32* @k, align 4
  %20 = sub nsw i32 %18, %19
  %21 = icmp sgt i32* %20, 1
  store i1 %21, i1* %.phi, align 1
  br i1 %21, label %and.rhs2, label %and.end2

and.rhs2:
  %22 = load i32, i32* @p, align 4
  %23 = load i32, i32* @k, align 4
  %24 = sub nsw i32 %22, %23
  %25 = icmp sgt i32* %24, 1
  store i1 %25, i1* %.phi, align 1
  br i1 %25, label %or.end3, label %or.rhs3

or.rhs3:
  %26 = load i32, i32* @p, align 4
  %27 = load i32, i32* @k, align 4
  %28 = sub nsw i32 %26, %27
  %29 = icmp sgt i32* %28, 1
  store i1 %29, i1* %.phi, align 1
  br label %or.end3

or.end3:
  br label %and.end2

and.end2:
  br i1* %.phi, label %or.end4, label %or.rhs4

or.rhs4:
  %30 = load i32, i32* @p, align 4
  %31 = icmp sgt i32 %30, 1
  br label %or.end4

or.end4:
  br i1* %.phi, label %if.then5, label %if.end5

if.then5:
  call void @print(i8* %.str.4)
  br label %if.end5

if.end5:
  %32 = load i32, i32* @p, align 4
  %33 = load i32, i32* @k, align 4
  %34 = sub nsw i32 %32, %33
  store i32* %34, i32* @i, align 4
  br label %for.begin6

for.begin6:
  %35 = load i32, i32* @i, align 4
  %36 = add i32 %35, 1
  store i32 %36, i32* @i, align 4
  %37 = load i32, i32* @i, align 4
  %38 = load i32, i32* @p, align 4
  %39 = load i32, i32* @k, align 4
  %40 = add i32 %38, %39
  %41 = icmp sle i32 %37, %40
  br i1 %41, label %for.body6, label %for.end6

for.body6:
  %42 = load i32, i32* @i, align 4
  %43 = icmp sle i32 1, %42
  store i1 %43, i1* %.phi, align 1
  br i1 %43, label %and.rhs7, label %and.end7

and.rhs7:
  %44 = load i32, i32* @i, align 4
  %45 = load i32, i32* @n, align 4
  %46 = icmp sle i32 %44, %45
  store i1 %46, i1* %.phi, align 1
  br label %and.end7

and.end7:
  br i1* %.phi, label %if.then8, label %if.end8

if.then8:
  %47 = load i32, i32* @i, align 4
  %48 = load i32, i32* @p, align 4
  %49 = icmp eq i32 %47, %48
  br i1 %49, label %if.then9, label %if.else9

if.then9:
  call void @print(i8* %.str.5)
  %51 = load i32, i32* @i, align 4
  %50 = call i8* @toString(i32 %51)
  call void @print(i8* %50)
  call void @print(i8* %.str.6)
  br label %if.end9

if.else9:
  br label %while.begin10

while.begin10:
  %52 = load i32, i32* @i, align 4
  %53 = icmp slt i32 %52, 2
  br i1 %53, label %while.body10, label %while.end10

while.body10:
  %54 = load i32, i32* @i, align 4
  %55 = add i32 %54, 1
  store i32 %55, i32* @i, align 4
  br label %while.begin10

while.end10:
  %56 = load i32, i32* @i, align 4
  call void @printInt(i32 %56)
  call void @print(i8* %.str.7)
  br label %if.end9

if.end9:
  br label %if.end8

if.end8:
  br label %for.begin6

for.end6:
  %57 = load i32, i32* @p, align 4
  %58 = load i32, i32* @k, align 4
  %59 = add i32 %57, %58
  %60 = load i32, i32* @n, align 4
  %61 = icmp slt i32* %59, %60
  br i1 %61, label %if.then11, label %if.end11

if.then11:
  call void @print(i8* %.str.8)
  br label %if.end11

if.end11:
  store i32 0, i32* %retval, align 4
  br label %return

return:
  %62 = load i32, i32* %retval, align 4
  ret i32 %62
}

