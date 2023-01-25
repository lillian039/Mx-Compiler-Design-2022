target datalayout = "e-m:e-p:32:32-p270:32:32-p271:32:32-p272:64:64-f64:32:64-f80:32-n8:16:32-S128"
target triple = "i386-pc-linux-gnu"


@.str = private unnamed_addr constant [5 x i8] c "true\00"
@.str.1 = private unnamed_addr constant [6 x i8] c "false\00"

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
  call void @__mx_initialize_globalVar()
  %retval = alloca i32
  %.phi = alloca i8
  %x = alloca i8*
  %0 = mul i32 100, 4
  %1 = add i32 %0, 4
  %2 = call i8* @__malloc(i32 %1)
  %3 = bitcast i8* %2 to i32*
  store i32 100, i32* %3
  %4 = getelementptr inbounds i8, i8* %2, i32 4
  %5 = bitcast i8* %4 to i8*
  store i8* %5, i8** %x
  %i = alloca i32
  store i32 0, i32* %i
  br label %for.begin1

for.begin1:
  %6 = load i32, i32* %i
  %7 = icmp slt i32 %6, 100
  %8 = zext i1 %7 to i8
  %9 = trunc i8 %8 to i1
  br i1 %9, label %for.body1, label %for.end1

for.body1:
  %10 = load i32, i32* %i
  %11 = srem i32 %10, 2
  %12 = icmp eq i32 %11, 0
  %13 = zext i1 %12 to i8
  %14 = trunc i8 %13 to i1
  br i1 %14, label %if.then2, label %if.else2

if.then2:
  %15 = load i8*, i8** %x
  %16 = load i32, i32* %i
  %17 = getelementptr inbounds i8, i8* %15, i32 %16
  store i8 1, i8* %17
  br label %if.end2

if.else2:
  %18 = load i8*, i8** %x
  %19 = load i32, i32* %i
  %20 = getelementptr inbounds i8, i8* %18, i32 %19
  store i8 0, i8* %20
  br label %if.end2

if.end2:
  %21 = load i32, i32* %i
  %22 = add i32 %21, 1
  store i32 %22, i32* %i
  br label %for.begin1

for.end1:
  %_i1 = alloca i32
  store i32 0, i32* %_i1
  br label %for.begin3

for.begin3:
  %23 = load i32, i32* %_i1
  %24 = icmp slt i32 %23, 100
  %25 = zext i1 %24 to i8
  %26 = trunc i8 %25 to i1
  br i1 %26, label %for.body3, label %for.end3

for.body3:
  %27 = load i8*, i8** %x
  %28 = load i32, i32* %_i1
  %29 = getelementptr inbounds i8, i8* %27, i32 %28
  %30 = load i8, i8* %29
  %31 = trunc i8 %30 to i1
  br i1 %31, label %if.then4, label %if.else4

if.then4:
  call void @println(i8* getelementptr inbounds ([5 x i8], [5 x i8]* @.str, i32 0, i32 0))
  br label %if.end4

if.else4:
  call void @println(i8* getelementptr inbounds ([6 x i8], [6 x i8]* @.str.1, i32 0, i32 0))
  br label %if.end4

if.end4:
  %32 = load i32, i32* %_i1
  %33 = add i32 %32, 1
  store i32 %33, i32* %_i1
  br label %for.begin3

for.end3:
  store i32 0, i32* %retval
  br label %return

return:
  %34 = load i32, i32* %retval
  ret i32 %34
}

