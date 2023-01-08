@n = global i32 0, align 4
@p = global i32 0, align 4
@k = global i32 0, align 4
@i = global i32 0, align 4

@.str = private unnamed_addr constant [4 x i8] c"!!!\00", align 1
@.str.1 = private unnamed_addr constant [4 x i8] c"???\00", align 1
@.str.2 = private unnamed_addr constant [4 x i8] c"<< \00", align 1
@.str.3 = private unnamed_addr constant [2 x i8] c"(\00", align 1
@.str.4 = private unnamed_addr constant [3 x i8] c") \00", align 1
@.str.5 = private unnamed_addr constant [2 x i8] c" \00", align 1
@.str.6 = private unnamed_addr constant [4 x i8] c">> \00", align 1

declare i32 @getInt () #0

declare void @print (*i8*) #0

declare void @println (*i8*) #0

declare void @printInt (*i32) #0

declare i8* @toString (*i32) #0

declare i8* @getString () #0

declare i1 @__str_eq (i8*, i8*) #0

declare i1 @__str_ne (i8*, i8*) #0

declare i1 @__str_slt (i8*, i8*) #0

declare i1 @__str_sle (i8*, i8*) #0

declare i1 @__str_sgt (i8*, i8*) #0

declare i1 @__str_sge (i8*, i8*) #0

declare i8* @__str_add (i8*, i8*) #0

define void @__mx_initialize_globalVar () {
entry:
  store i32 1, i32* @i, align 4
  ret void

}

define i32 @add (*i32 %a, *i32 %b) {
entry:
  %retval = alloca i32, align 4
  %a.addr = alloca i32, align 4
  %b.addr = alloca i32, align 4
  %0 = load *i32, *i32 %a.addr, align 8
  %1 = icmp sgt *i32 %0, 0
  br i1 %1, label %if.then1, label %if.else1

if.then1:
  store *i32 %a.addr, *i32* %retval, align 4
  br label %return

if.else1:
  store *i32 %b.addr, *i32* %retval, align 4
  br label %return

if.end1:
  br label %return

return:
  ret *i32 %retval
}

define i32 @main () {
entry:
  call void @__mx_initialize_globalVar()
  %.phi = alloca i1, align 1
  %retval = alloca i32, align 4
  %aaa = alloca i8*, align 1
  store *i8 %.str, *i8* %aaa, align 4
  %bbb = alloca i8*, align 1
  store *i8 %.str.1, *i8* %bbb, align 4
  %c = alloca i1, align 1
  %0 = call i1 @__str_sgt(*i8* %aaa, *i8* %bbb)
  store i1 %0, i1* %c, align 4
  %1 = call i32 @getInt()
  store i32 %1, i32* @n, align 4
  %2 = call i32 @getInt()
  store i32 %2, i32* @p, align 4
  %3 = call i32 @getInt()
  store i32 %3, i32* @k, align 4
  %a = alloca **i32, align 4
  %5 = mul i32 1, 32
  %6 = add i32 %5, 32
  %7 = @__malloc(%6)
  %8 = getelementptr inbounds **i32, ptr %7, i32 0
  store i32 1, i32* %_new_i.0.1, align 4
  br label %array.for.begin1

array.for.begin1:
  %9 = icmp slt i32 %_new_i.0.1, %6
  br i32 %9, label %array.for.body1, label %array.for.end1

array.for.body1:
  %10 = getelementptr inbounds **i32, ptr %7, i32 %_new_i.0.1
  %11 = mul i32 2, 32
  %12 = add i32 %11, 32
  %13 = @__malloc(%12)
  %14 = getelementptr inbounds *i32, ptr %13, i32 0
  store i32 1, i32* %_new_i.1.2, align 4
  br label %array.for.begin2

array.for.begin2:
  %15 = icmp slt i32 %_new_i.1.2, %12
  br i32 %15, label %array.for.body2, label %array.for.end2

array.for.body2:
  %16 = getelementptr inbounds *i32, ptr %13, i32 %_new_i.1.2
  %17 = mul i32 3, 32
  %18 = add i32 %17, 32
  %19 = @__malloc(%18)
  %20 = getelementptr inbounds i32, ptr %19, i32 0
  store *i32 %19, *i32* %16, align 4
  %21 = load i32, i32 %_new_i.1.2, align 8
  %21 = add i32 %_new_i.1.2, 1
  br label %array.for.begin2

array.for.end2:
  store **i32 %13, **i32* %10, align 4
  %22 = load i32, i32 %_new_i.0.1, align 8
  %22 = add i32 %_new_i.0.1, 1
  br label %array.for.begin1

array.for.end1:
  store ***i32 %7, ***i32* %4, align 4
  store **i32 %4, **i32* %a, align 4
  %23 = load *i32, *i32 @p, align 8
  %24 = load *i32, *i32 @k, align 8
  %25 = sub nsw *i32 %23, %24
  %26 = icmp sgt *i32 %25, 1
  store i1 %26, i1* %.phi, align 4
  br i1 %26, label %and.rhs3, label %and.end3

and.rhs3:
  %27 = load *i32, *i32 @p, align 8
  %28 = load *i32, *i32 @k, align 8
  %29 = sub nsw *i32 %27, %28
  %30 = icmp sgt *i32 %29, 1
  store i1 %30, i1* %.phi, align 4
  br i1 %30, label %or.end4, label %or.rhs4

or.rhs4:
  %31 = load *i32, *i32 @p, align 8
  %32 = load *i32, *i32 @k, align 8
  %33 = sub nsw *i32 %31, %32
  %34 = icmp sgt *i32 %33, 1
  store i1 %34, i1* %.phi, align 4
  br label %or.end4

or.end4:
  br label %and.end3

and.end3:
  br *i1 %.phi, label %or.end5, label %or.rhs5

or.rhs5:
  %35 = load *i32, *i32 @p, align 8
  %36 = icmp sgt *i32 %35, 1
  br label %or.end5

or.end5:
  br *i1 %.phi, label %if.then6, label %if.end6

if.then6:
  call void @print(*i8 %.str.2)
  br label %if.end6

if.end6:
  %37 = load *i32, *i32 @p, align 8
  %38 = load *i32, *i32 @k, align 8
  %39 = sub nsw *i32 %37, %38
  store *i32 %39, *i32* @i, align 4
  br label %for.begin7

for.begin7:
  %40 = add *i32 @i, 1
  store *i32 @i, *i32* %40, align 4
  %41 = load *i32, *i32 @i, align 8
  %42 = load *i32, *i32 @p, align 8
  %43 = load *i32, *i32 @k, align 8
  %44 = add *i32 %42, %43
  %45 = icmp sle *i32 %41, %44
  br i1 %45, label %for.body7, label %for.end7

for.body7:
  %46 = load i32, i32 @i, align 8
  %47 = icmp sle i32 1, %46
  store i1 %47, i1* %.phi, align 4
  br i1 %47, label %and.rhs8, label %and.end8

and.rhs8:
  %48 = load *i32, *i32 @i, align 8
  %49 = load *i32, *i32 @n, align 8
  %50 = icmp sle *i32 %48, %49
  store i1 %50, i1* %.phi, align 4
  br label %and.end8

and.end8:
  br *i1 %.phi, label %if.then9, label %if.end9

if.then9:
  %51 = load *i32, *i32 @i, align 8
  %52 = load *i32, *i32 @p, align 8
  %53 = icmp eq *i32 %51, %52
  br i1 %53, label %if.then10, label %if.else10

if.then10:
  call void @print(*i8 %.str.3)
  %54 = call i8* @toString(*i32 @i)
  call void @print(i8* %54)
  call void @print(*i8 %.str.4)
  br label %if.end10

if.else10:
  br label %while.begin11

while.begin11:
  %55 = load *i32, *i32 @i, align 8
  %56 = icmp slt *i32 %55, 2
  br i1 %56, label %while.body11, label %while.end11

while.body11:
  %57 = add *i32 @i, 1
  store *i32 @i, *i32* %57, align 4
  br label %while.begin11

while.end11:
  call void @printInt(*i32 @i)
  call void @print(*i8 %.str.5)
  br label %if.end10

if.end10:
  br label %if.end9

if.end9:
  br label %for.begin7

for.end7:
  %58 = load *i32, *i32 @p, align 8
  %59 = load *i32, *i32 @k, align 8
  %60 = add *i32 %58, %59
  %61 = load *i32, *i32 @n, align 8
  %62 = icmp slt *i32 %60, %61
  br i1 %62, label %if.then12, label %if.end12

if.then12:
  call void @print(*i8 %.str.6)
  br label %if.end12

if.end12:
  store i32 0, i32* %retval, align 4
  br label %return

return:
  ret *i32 %retval
}

