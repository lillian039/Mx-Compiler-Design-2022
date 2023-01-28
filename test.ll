target datalayout = "e-m:e-p:32:32-p270:32:32-p271:32:32-p272:64:64-f64:32:64-f80:32-n8:16:32-S128"
target triple = "i386-pc-linux-gnu"

@n = global i32 0
@p = global i32 0
@k = global i32 0
@i = global i32 0

@.str = private unnamed_addr constant [4 x i8] c "<< \00"
@.str.1 = private unnamed_addr constant [2 x i8] c "(\00"
@.str.2 = private unnamed_addr constant [3 x i8] c ") \00"
@.str.3 = private unnamed_addr constant [2 x i8] c " \00"
@.str.4 = private unnamed_addr constant [4 x i8] c ">> \00"

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

define i32 @main () {
entry:
  %retval = alloca i32
  %.phi = alloca i8
  call void @__mx_initialize_globalVar()
  %0 = call i32 @getInt()
  store i32 %0, i32* @n
  %1 = call i32 @getInt()
  store i32 %1, i32* @p
  %2 = call i32 @getInt()
  store i32 %2, i32* @k
  %3 = load i32, i32* @p
  %4 = load i32, i32* @k
  %5 = sub nsw i32 %3, %4
  %6 = icmp sgt i32 %5, 1
  %7 = zext i1 %6 to i8
  %8 = trunc i8 %7 to i1
  br i1 %8, label %if.then1, label %if.end1

if.then1:
  call void @print(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str, i32 0, i32 0))
  br label %if.end1

if.end1:
  %9 = load i32, i32* @p
  %10 = load i32, i32* @k
  %11 = sub nsw i32 %9, %10
  store i32 %11, i32* @i
  br label %for.begin2

for.begin2:
  %12 = load i32, i32* @i
  %13 = load i32, i32* @p
  %14 = load i32, i32* @k
  %15 = add i32 %13, %14
  %16 = icmp sle i32 %12, %15
  %17 = zext i1 %16 to i8
  %18 = trunc i8 %17 to i1
  br i1 %18, label %for.body2, label %for.end2

for.body2:
  %19 = load i32, i32* @i
  %20 = icmp sle i32 1, %19
  %21 = zext i1 %20 to i8
  store i8 %21, i8* %.phi
  br i1 %26, label %and.rhs3, label %and.end3

and.rhs3:
  %22 = load i32, i32* @i
  %23 = load i32, i32* @n
  %24 = icmp sle i32 %22, %23
  %25 = zext i1 %24 to i8
  store i8 %25, i8* %.phi
  %26 = trunc i8 %21 to i1
  br label %and.end3

and.end3:
  %27 = load i8, i8* %.phi
  %28 = trunc i8 %27 to i1
  br i1 %28, label %if.then4, label %if.end4

if.then4:
  %29 = load i32, i32* @i
  %30 = load i32, i32* @p
  %31 = icmp eq i32 %29, %30
  %32 = zext i1 %31 to i8
  %33 = trunc i8 %32 to i1
  br i1 %33, label %if.then5, label %if.else5

if.then5:
  call void @print(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.1, i32 0, i32 0))
  %34 = load i32, i32* @i
  %35 = call i8* @toString(i32 %34)
  call void @print(i8* %35)
  call void @print(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0))
  br label %if.end5

if.else5:
  %36 = load i32, i32* @i
  call void @printInt(i32 %36)
  call void @print(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.3, i32 0, i32 0))
  br label %if.end5

if.end5:
  br label %if.end4

if.end4:
  %37 = load i32, i32* @i
  %38 = add i32 %37, 1
  store i32 %38, i32* @i
  br label %for.begin2

for.end2:
  %39 = load i32, i32* @p
  %40 = load i32, i32* @k
  %41 = add i32 %39, %40
  %42 = load i32, i32* @n
  %43 = icmp slt i32 %41, %42
  %44 = zext i1 %43 to i8
  %45 = trunc i8 %44 to i1
  br i1 %45, label %if.then6, label %if.end6

if.then6:
  call void @print(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.4, i32 0, i32 0))
  br label %if.end6

if.end6:
  store i32 0, i32* %retval
  br label %return

return:
  %46 = load i32, i32* %retval
  ret i32 %46
}

