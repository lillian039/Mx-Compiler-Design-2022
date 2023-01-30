target datalayout = "e-m:e-p:32:32-p270:32:32-p271:32:32-p272:64:64-f64:32:64-f80:32-n8:16:32-S128"
target triple = "i386-pc-linux-gnu"

@N = global i32 0
@M = global i32 0
@i = global i32 0
@j = global i32 0
@primeCount = global i32 0
@resultCount = global i32 0
@b = global i32* null
@prime = global i32* null
@gps = global i32* null
@tmp = global i32* null
@result = global i32** null

@.str = private unnamed_addr constant [8 x i8] c "Total: \00"
@.str.1 = private unnamed_addr constant [2 x i8] c " \00"
@.str.2 = private unnamed_addr constant [2 x i8] c "\0A\00"

declare i32 @getInt ()

declare void @print (i8*)

declare void @println (i8*)

declare void @printInt (i32)

declare void @printlnInt (i32)

declare i8* @toString (i32)

declare i8* @getString ()

declare i32 @__string_parseInt (i8*)

declare i32 @__string_ord (i32, i8*)

declare i32 @__string_length (i8*)

declare i8* @__string_substring (i32, i32, i8*)

define void @__mx_initialize_globalVar () {
entry:
  %0 = mul i32 1001, 4
  %1 = add i32 %0, 4
  %2 = call i8* @__malloc(i32 %1)
  %3 = bitcast i8* %2 to i32*
  store i32 1001, i32* %3
  %4 = bitcast i8* %2 to i32*
  %5 = getelementptr inbounds i32, i32* %4, i32 1
  %.tmp6 = alloca i32*
  store i32* %5, i32** %.tmp6
  %6 = load i32*, i32** %.tmp6
  store i32* %6, i32** @b
  %7 = mul i32 170, 4
  %8 = add i32 %7, 4
  %9 = call i8* @__malloc(i32 %8)
  %10 = bitcast i8* %9 to i32*
  store i32 170, i32* %10
  %11 = bitcast i8* %9 to i32*
  %12 = getelementptr inbounds i32, i32* %11, i32 1
  %.tmp13 = alloca i32*
  store i32* %12, i32** %.tmp13
  %13 = load i32*, i32** %.tmp13
  store i32* %13, i32** @prime
  %14 = mul i32 1001, 4
  %15 = add i32 %14, 4
  %16 = call i8* @__malloc(i32 %15)
  %17 = bitcast i8* %16 to i32*
  store i32 1001, i32* %17
  %18 = bitcast i8* %16 to i32*
  %19 = getelementptr inbounds i32, i32* %18, i32 1
  %.tmp20 = alloca i32*
  store i32* %19, i32** %.tmp20
  %20 = load i32*, i32** %.tmp20
  store i32* %20, i32** @gps
  %21 = mul i32 1, 4
  %22 = add i32 %21, 4
  %23 = call i8* @__malloc(i32 %22)
  %24 = bitcast i8* %23 to i32*
  store i32 1, i32* %24
  %25 = bitcast i8* %23 to i32*
  %26 = getelementptr inbounds i32, i32* %25, i32 1
  %.tmp27 = alloca i32*
  store i32* %26, i32** %.tmp27
  %27 = load i32*, i32** %.tmp27
  store i32* %27, i32** @tmp
  ret void

}

declare i8* @__malloc (i32)

declare i8 @___str_eq (i8*, i8*)

declare i8 @___str_ne (i8*, i8*)

declare i8 @___str_slt (i8*, i8*)

declare i8 @___str_sle (i8*, i8*)

declare i8 @___str_sgt (i8*, i8*)

declare i8 @___str_sge (i8*, i8*)

declare i8* @___str_add (i8*, i8*)

define void @origin (i32 %N) {
entry:
  %.phi = alloca i32
  %N.addr = alloca i32
  store i32 %N, i32* %N.addr
  %0 = load i32, i32* %N.addr
  %1 = mul i32 %0, 4
  %2 = add i32 %1, 4
  %3 = call i8* @__malloc(i32 %2)
  %4 = bitcast i8* %3 to i32*
  store i32 %0, i32* %4
  %5 = bitcast i8* %3 to i32**
  %6 = getelementptr inbounds i32*, i32** %5, i32 1
  %.tmp7 = alloca i32**
  store i32** %6, i32*** %.tmp7
  %7 = load i32**, i32*** %.tmp7
  store i32** %7, i32*** @result
  store i32 0, i32* @i
  br label %for.begin1

for.begin1:
  %8 = load i32, i32* @i
  %9 = load i32, i32* %N.addr
  %10 = icmp slt i32 %8, %9
  %11 = zext i1 %10 to i32
  %12 = trunc i32 %11 to i1
  br i1 %12, label %for.body1, label %for.end1

for.body1:
  %13 = load i32**, i32*** @result
  %14 = load i32, i32* @i
  %15 = getelementptr inbounds i32*, i32** %13, i32 %14
  %16 = load i32, i32* %N.addr
  %17 = mul i32 %16, 4
  %18 = add i32 %17, 4
  %19 = call i8* @__malloc(i32 %18)
  %20 = bitcast i8* %19 to i32*
  store i32 %16, i32* %20
  %21 = bitcast i8* %19 to i32*
  %22 = getelementptr inbounds i32, i32* %21, i32 1
  %.tmp23 = alloca i32*
  store i32* %22, i32** %.tmp23
  %23 = load i32*, i32** %.tmp23
  store i32* %23, i32** %15
  store i32 0, i32* @j
  br label %for.begin2

for.begin2:
  %24 = load i32, i32* @j
  %25 = load i32, i32* %N.addr
  %26 = icmp slt i32 %24, %25
  %27 = zext i1 %26 to i32
  %28 = trunc i32 %27 to i1
  br i1 %28, label %for.body2, label %for.end2

for.body2:
  %29 = load i32**, i32*** @result
  %30 = load i32, i32* @i
  %31 = getelementptr inbounds i32*, i32** %29, i32 %30
  %32 = load i32*, i32** %31
  %33 = load i32, i32* @j
  %34 = getelementptr inbounds i32, i32* %32, i32 %33
  store i32 0, i32* %34
  %35 = load i32, i32* @j
  %36 = add i32 %35, 1
  store i32 %36, i32* @j
  br label %for.begin2

for.end2:
  %37 = load i32, i32* @i
  %38 = add i32 %37, 1
  store i32 %38, i32* @i
  br label %for.begin1

for.end1:
  br label %return

return:
  ret void
}

define void @getPrime (i32 %N) {
entry:
  %.phi = alloca i32
  %N.addr = alloca i32
  %count = alloca i32
  %i = alloca i32
  store i32 %N, i32* %N.addr
  store i32 2, i32* %count
  store i32 2, i32* %i
  br label %for.begin1

for.begin1:
  %0 = load i32, i32* %i
  %1 = load i32, i32* %N.addr
  %2 = icmp sle i32 %0, %1
  %3 = zext i1 %2 to i32
  %4 = trunc i32 %3 to i1
  br i1 %4, label %for.body1, label %for.end1

for.body1:
  %5 = load i32*, i32** @b
  %6 = load i32, i32* %i
  %7 = getelementptr inbounds i32, i32* %5, i32 %6
  %8 = load i32, i32* %7
  %9 = icmp eq i32 %8, 1
  %10 = zext i1 %9 to i32
  %11 = trunc i32 %10 to i1
  br i1 %11, label %if.then2, label %if.end2

if.then2:
  %12 = load i32*, i32** @tmp
  %13 = getelementptr inbounds i32, i32* %12, i32 0
  %14 = load i32*, i32** @tmp
  %15 = getelementptr inbounds i32, i32* %14, i32 0
  %16 = load i32, i32* %15
  %17 = add i32 %16, 1
  store i32 %17, i32* %13
  %18 = load i32*, i32** @prime
  %19 = load i32*, i32** @tmp
  %20 = getelementptr inbounds i32, i32* %19, i32 0
  %21 = load i32, i32* %20
  %22 = getelementptr inbounds i32, i32* %18, i32 %21
  %23 = load i32, i32* %i
  store i32 %23, i32* %22
  %24 = load i32*, i32** @gps
  %25 = load i32, i32* %i
  %26 = getelementptr inbounds i32, i32* %24, i32 %25
  %27 = load i32*, i32** @tmp
  %28 = getelementptr inbounds i32, i32* %27, i32 0
  %29 = load i32, i32* %28
  store i32 %29, i32* %26
  br label %if.end2

if.end2:
  br label %while.begin3

while.begin3:
  %30 = load i32, i32* %i
  %31 = load i32, i32* %count
  %32 = mul i32 %30, %31
  %33 = load i32, i32* %N.addr
  %34 = icmp sle i32 %32, %33
  %35 = zext i1 %34 to i32
  %36 = trunc i32 %35 to i1
  br i1 %36, label %while.body3, label %while.end3

while.body3:
  %37 = load i32*, i32** @b
  %38 = load i32, i32* %i
  %39 = load i32, i32* %count
  %40 = mul i32 %38, %39
  %41 = getelementptr inbounds i32, i32* %37, i32 %40
  store i32 0, i32* %41
  %42 = load i32, i32* %count
  %43 = add i32 %42, 1
  store i32 %43, i32* %count
  br label %while.begin3

while.end3:
  store i32 2, i32* %count
  %44 = load i32, i32* %i
  %45 = add i32 %44, 1
  store i32 %45, i32* %i
  br label %for.begin1

for.end1:
  br label %return

return:
  ret void
}

define i32 @getResult (i32 %N, i32 %k1, i32 %k2) {
entry:
  %retval = alloca i32
  %.phi = alloca i32
  %N.addr = alloca i32
  %k1.addr = alloca i32
  %k2.addr = alloca i32
  store i32 %N, i32* %N.addr
  store i32 %k1, i32* %k1.addr
  store i32 %k2, i32* %k2.addr
  %0 = load i32**, i32*** @result
  %1 = load i32, i32* %k1.addr
  %2 = getelementptr inbounds i32*, i32** %0, i32 %1
  %3 = load i32*, i32** %2
  %4 = load i32, i32* %k2.addr
  %5 = getelementptr inbounds i32, i32* %3, i32 %4
  %6 = load i32, i32* %5
  %7 = sub nsw i32 0, 1
  %8 = icmp eq i32 %6, %7
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i1
  br i1 %10, label %if.then1, label %if.end1

if.then1:
  %11 = load i32*, i32** @prime
  %12 = load i32, i32* %k2.addr
  %13 = getelementptr inbounds i32, i32* %11, i32 %12
  %14 = load i32, i32* %13
  %15 = mul i32 %14, 2
  %16 = load i32*, i32** @prime
  %17 = load i32, i32* %k1.addr
  %18 = getelementptr inbounds i32, i32* %16, i32 %17
  %19 = load i32, i32* %18
  %20 = sub nsw i32 %15, %19
  %21 = load i32, i32* %N.addr
  %22 = icmp sle i32 %20, %21
  %23 = zext i1 %22 to i32
  %24 = trunc i32 %23 to i1
  br i1 %24, label %if.then2, label %if.end2

if.then2:
  %25 = load i32*, i32** @b
  %26 = load i32*, i32** @prime
  %27 = load i32, i32* %k2.addr
  %28 = getelementptr inbounds i32, i32* %26, i32 %27
  %29 = load i32, i32* %28
  %30 = mul i32 %29, 2
  %31 = load i32*, i32** @prime
  %32 = load i32, i32* %k1.addr
  %33 = getelementptr inbounds i32, i32* %31, i32 %32
  %34 = load i32, i32* %33
  %35 = sub nsw i32 %30, %34
  %36 = getelementptr inbounds i32, i32* %25, i32 %35
  %37 = load i32, i32* %36
  %38 = icmp ne i32 %37, 0
  %39 = zext i1 %38 to i32
  %40 = trunc i32 %39 to i1
  br i1 %40, label %if.then3, label %if.end3

if.then3:
  %41 = load i32**, i32*** @result
  %42 = load i32, i32* %k1.addr
  %43 = getelementptr inbounds i32*, i32** %41, i32 %42
  %44 = load i32*, i32** %43
  %45 = load i32, i32* %k2.addr
  %46 = getelementptr inbounds i32, i32* %44, i32 %45
  %47 = load i32, i32* %N.addr
  %48 = load i32, i32* %k2.addr
  %49 = load i32*, i32** @gps
  %50 = load i32*, i32** @prime
  %51 = load i32, i32* %k2.addr
  %52 = getelementptr inbounds i32, i32* %50, i32 %51
  %53 = load i32, i32* %52
  %54 = mul i32 %53, 2
  %55 = load i32*, i32** @prime
  %56 = load i32, i32* %k1.addr
  %57 = getelementptr inbounds i32, i32* %55, i32 %56
  %58 = load i32, i32* %57
  %59 = sub nsw i32 %54, %58
  %60 = getelementptr inbounds i32, i32* %49, i32 %59
  %61 = load i32, i32* %60
  %62 = call i32 @getResult(i32 %47, i32 %48, i32 %61)
  %63 = add i32 %62, 1
  store i32 %63, i32* %46
  br label %if.end3

if.end3:
  br label %if.end2

if.end2:
  br label %if.end1

if.end1:
  %64 = load i32**, i32*** @result
  %65 = load i32, i32* %k1.addr
  %66 = getelementptr inbounds i32*, i32** %64, i32 %65
  %67 = load i32*, i32** %66
  %68 = load i32, i32* %k2.addr
  %69 = getelementptr inbounds i32, i32* %67, i32 %68
  %70 = load i32, i32* %69
  %71 = sub nsw i32 0, 1
  %72 = icmp eq i32 %70, %71
  %73 = zext i1 %72 to i32
  %74 = trunc i32 %73 to i1
  br i1 %74, label %if.then4, label %if.end4

if.then4:
  %75 = load i32**, i32*** @result
  %76 = load i32, i32* %k1.addr
  %77 = getelementptr inbounds i32*, i32** %75, i32 %76
  %78 = load i32*, i32** %77
  %79 = load i32, i32* %k2.addr
  %80 = getelementptr inbounds i32, i32* %78, i32 %79
  store i32 1, i32* %80
  br label %if.end4

if.end4:
  %81 = load i32**, i32*** @result
  %82 = load i32, i32* %k1.addr
  %83 = getelementptr inbounds i32*, i32** %81, i32 %82
  %84 = load i32*, i32** %83
  %85 = load i32, i32* %k2.addr
  %86 = getelementptr inbounds i32, i32* %84, i32 %85
  %87 = load i32, i32* %86
  store i32 %87, i32* %retval
  br label %return

return:
  %88 = load i32, i32* %retval
  ret i32 %88
}

define void @printF (i32 %k1, i32 %k2, i32 %k3) {
entry:
  %.phi = alloca i32
  %k1.addr = alloca i32
  %k2.addr = alloca i32
  %k3.addr = alloca i32
  store i32 %k1, i32* %k1.addr
  store i32 %k2, i32* %k2.addr
  store i32 %k3, i32* %k3.addr
  %0 = load i32, i32* %k1.addr
  %1 = call i8* @toString(i32 %0)
  call void @print(i8* %1)
  br label %while.begin1

while.begin1:
  %2 = load i32, i32* %k3.addr
  %3 = icmp sgt i32 %2, 0
  %4 = zext i1 %3 to i32
  %5 = trunc i32 %4 to i1
  br i1 %5, label %while.body1, label %while.end1

while.body1:
  call void @print(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.1, i32 0, i32 0))
  %6 = load i32, i32* %k2.addr
  %7 = call i8* @toString(i32 %6)
  call void @print(i8* %7)
  %8 = load i32, i32* %k2.addr
  %9 = mul i32 %8, 2
  %10 = load i32, i32* %k1.addr
  %11 = sub nsw i32 %9, %10
  store i32 %11, i32* %k2.addr
  %12 = load i32, i32* %k1.addr
  %13 = load i32, i32* %k2.addr
  %14 = add i32 %12, %13
  %15 = sdiv i32 %14, 2
  store i32 %15, i32* %k1.addr
  %16 = load i32, i32* %k3.addr
  %17 = sub nsw i32 %16, 1
  store i32 %17, i32* %k3.addr
  br label %while.begin1

while.end1:
  call void @print(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.2, i32 0, i32 0))
  br label %return

return:
  ret void
}

define i32 @main () {
entry:
  %retval = alloca i32
  %.phi = alloca i32
  call void @__mx_initialize_globalVar()
  call void @origin(i32 170)
  store i32 1000, i32* @N
  %0 = call i32 @getInt()
  store i32 %0, i32* @M
  store i32 0, i32* @primeCount
  store i32 0, i32* @resultCount
  %1 = load i32*, i32** @tmp
  %2 = getelementptr inbounds i32, i32* %1, i32 0
  store i32 0, i32* %2
  store i32 0, i32* @i
  br label %for.begin1

for.begin1:
  %3 = load i32, i32* @i
  %4 = load i32, i32* @N
  %5 = add i32 %4, 1
  %6 = icmp slt i32 %3, %5
  %7 = zext i1 %6 to i32
  %8 = trunc i32 %7 to i1
  br i1 %8, label %for.body1, label %for.end1

for.body1:
  %9 = load i32*, i32** @b
  %10 = load i32, i32* @i
  %11 = getelementptr inbounds i32, i32* %9, i32 %10
  store i32 1, i32* %11
  %12 = load i32*, i32** @gps
  %13 = load i32, i32* @i
  %14 = getelementptr inbounds i32, i32* %12, i32 %13
  store i32 0, i32* %14
  %15 = load i32, i32* @i
  %16 = add i32 %15, 1
  store i32 %16, i32* @i
  br label %for.begin1

for.end1:
  store i32 0, i32* @i
  br label %for.begin2

for.begin2:
  %17 = load i32, i32* @i
  %18 = load i32, i32* @M
  %19 = add i32 %18, 1
  %20 = icmp slt i32 %17, %19
  %21 = zext i1 %20 to i32
  %22 = trunc i32 %21 to i1
  br i1 %22, label %for.body2, label %for.end2

for.body2:
  %23 = load i32*, i32** @prime
  %24 = load i32, i32* @i
  %25 = getelementptr inbounds i32, i32* %23, i32 %24
  store i32 0, i32* %25
  %26 = load i32, i32* @i
  %27 = add i32 %26, 1
  store i32 %27, i32* @i
  br label %for.begin2

for.end2:
  store i32 0, i32* @i
  br label %for.begin3

for.begin3:
  %28 = load i32, i32* @i
  %29 = load i32, i32* @M
  %30 = icmp sle i32 %28, %29
  %31 = zext i1 %30 to i32
  %32 = trunc i32 %31 to i1
  br i1 %32, label %for.body3, label %for.end3

for.body3:
  store i32 0, i32* @j
  br label %for.begin4

for.begin4:
  %33 = load i32, i32* @j
  %34 = load i32, i32* @M
  %35 = icmp sle i32 %33, %34
  %36 = zext i1 %35 to i32
  %37 = trunc i32 %36 to i1
  br i1 %37, label %for.body4, label %for.end4

for.body4:
  %38 = load i32**, i32*** @result
  %39 = load i32, i32* @i
  %40 = getelementptr inbounds i32*, i32** %38, i32 %39
  %41 = load i32*, i32** %40
  %42 = load i32, i32* @j
  %43 = getelementptr inbounds i32, i32* %41, i32 %42
  %44 = sub nsw i32 0, 1
  store i32 %44, i32* %43
  %45 = load i32, i32* @j
  %46 = add i32 %45, 1
  store i32 %46, i32* @j
  br label %for.begin4

for.end4:
  %47 = load i32, i32* @i
  %48 = add i32 %47, 1
  store i32 %48, i32* @i
  br label %for.begin3

for.end3:
  %49 = load i32, i32* @N
  call void @getPrime(i32 %49)
  %50 = load i32*, i32** @tmp
  %51 = getelementptr inbounds i32, i32* %50, i32 0
  %52 = load i32, i32* %51
  store i32 %52, i32* @primeCount
  store i32 1, i32* @i
  br label %for.begin5

for.begin5:
  %53 = load i32, i32* @i
  %54 = load i32, i32* @primeCount
  %55 = icmp slt i32 %53, %54
  %56 = zext i1 %55 to i32
  %57 = trunc i32 %56 to i1
  br i1 %57, label %for.body5, label %for.end5

for.body5:
  %58 = load i32, i32* @i
  %59 = add i32 %58, 1
  store i32 %59, i32* @j
  br label %for.begin6

for.begin6:
  %60 = load i32, i32* @j
  %61 = load i32, i32* @primeCount
  %62 = icmp sle i32 %60, %61
  %63 = zext i1 %62 to i32
  %64 = trunc i32 %63 to i1
  br i1 %64, label %for.body6, label %for.end6

for.body6:
  %65 = load i32**, i32*** @result
  %66 = load i32, i32* @i
  %67 = getelementptr inbounds i32*, i32** %65, i32 %66
  %68 = load i32*, i32** %67
  %69 = load i32, i32* @j
  %70 = getelementptr inbounds i32, i32* %68, i32 %69
  %71 = load i32, i32* %70
  %72 = sub nsw i32 0, 1
  %73 = icmp eq i32 %71, %72
  %74 = zext i1 %73 to i32
  %75 = trunc i32 %74 to i1
  br i1 %75, label %if.then7, label %if.end7

if.then7:
  %76 = load i32**, i32*** @result
  %77 = load i32, i32* @i
  %78 = getelementptr inbounds i32*, i32** %76, i32 %77
  %79 = load i32*, i32** %78
  %80 = load i32, i32* @j
  %81 = getelementptr inbounds i32, i32* %79, i32 %80
  %82 = load i32, i32* @N
  %83 = load i32, i32* @i
  %84 = load i32, i32* @j
  %85 = call i32 @getResult(i32 %82, i32 %83, i32 %84)
  store i32 %85, i32* %81
  %86 = load i32**, i32*** @result
  %87 = load i32, i32* @i
  %88 = getelementptr inbounds i32*, i32** %86, i32 %87
  %89 = load i32*, i32** %88
  %90 = load i32, i32* @j
  %91 = getelementptr inbounds i32, i32* %89, i32 %90
  %92 = load i32, i32* %91
  %93 = icmp sgt i32 %92, 1
  %94 = zext i1 %93 to i32
  %95 = trunc i32 %94 to i1
  br i1 %95, label %if.then8, label %if.end8

if.then8:
  %96 = load i32*, i32** @prime
  %97 = load i32, i32* @i
  %98 = getelementptr inbounds i32, i32* %96, i32 %97
  %99 = load i32, i32* %98
  %100 = load i32*, i32** @prime
  %101 = load i32, i32* @j
  %102 = getelementptr inbounds i32, i32* %100, i32 %101
  %103 = load i32, i32* %102
  %104 = load i32**, i32*** @result
  %105 = load i32, i32* @i
  %106 = getelementptr inbounds i32*, i32** %104, i32 %105
  %107 = load i32*, i32** %106
  %108 = load i32, i32* @j
  %109 = getelementptr inbounds i32, i32* %107, i32 %108
  %110 = load i32, i32* %109
  call void @printF(i32 %99, i32 %103, i32 %110)
  %111 = load i32, i32* @resultCount
  %112 = add i32 %111, 1
  store i32 %112, i32* @resultCount
  br label %if.end8

if.end8:
  br label %if.end7

if.end7:
  %113 = load i32, i32* @j
  %114 = add i32 %113, 1
  store i32 %114, i32* @j
  br label %for.begin6

for.end6:
  %115 = load i32, i32* @i
  %116 = add i32 %115, 1
  store i32 %116, i32* @i
  br label %for.begin5

for.end5:
  call void @print(i8* getelementptr inbounds ([8 x i8], [8 x i8]* @.str, i32 0, i32 0))
  %117 = load i32, i32* @resultCount
  %118 = call i8* @toString(i32 %117)
  call void @println(i8* %118)
  store i32 0, i32* %retval
  br label %return

return:
  %119 = load i32, i32* %retval
  ret i32 %119
}

