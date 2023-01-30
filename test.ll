target datalayout = "e-m:e-p:32:32-p270:32:32-p271:32:32-p272:64:64-f64:32:64-f80:32-n8:16:32-S128"
target triple = "i386-pc-linux-gnu"

%struct.A = type { %struct.A*, %struct.B*, %struct.C***, i32 }

%struct.B = type { i32, %struct.C* }

%struct.C = type { i32, i8* }

@countA = global i32 0
@countB = global i32 0
@countC = global i32 0
@something = global %struct.C* null

@.str = private unnamed_addr constant [2 x i8] c ",\00"
@.str.1 = private unnamed_addr constant [1 x i8] c "\00"
@.str.2 = private unnamed_addr constant [6 x i8] c "Oops!\00"
@.str.3 = private unnamed_addr constant [2 x i8] c " \00"
@.str.4 = private unnamed_addr constant [2 x i8] c "\0A\00"
@.str.5 = private unnamed_addr constant [2 x i8] c ">\00"
@.str.6 = private unnamed_addr constant [3 x i8] c "<=\00"

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

define %struct.C** @__A_getc0 (%struct.A* %this) {
entry:
  %retval = alloca %struct.C**
  %.phi = alloca i32
  %this.addr = alloca %struct.A*
  store %struct.A* %this, %struct.A** %this.addr
  %0 = load %struct.A*, %struct.A** %this.addr
  %1 = getelementptr inbounds %struct.A, %struct.A* %0, i32 0, i32 2
  %2 = load %struct.C***, %struct.C**** %1
  %3 = getelementptr inbounds %struct.C**, %struct.C*** %2, i32 0
  %4 = load %struct.C**, %struct.C*** %3
  store %struct.C** %4, %struct.C*** %retval
  br label %return

return:
  %5 = load %struct.C**, %struct.C*** %retval
  ret %struct.C** %5
}

define void @__A_A (%struct.A* %this) {
entry:
  %.phi = alloca i32
  %this.addr = alloca %struct.A*
  %_new_i.0.3 = alloca i32
  %_new_i.1.4 = alloca i32
  %_new_i.2.5 = alloca i32
  store %struct.A* %this, %struct.A** %this.addr
  %0 = load %struct.A*, %struct.A** %this.addr
  %1 = getelementptr inbounds %struct.A, %struct.A* %0, i32 0, i32 3
  %2 = load i32, i32* @countA
  %3 = add i32 %2, 1
  store i32 %3, i32* @countA
  store i32 %2, i32* %1
  %4 = load %struct.A*, %struct.A** %this.addr
  %5 = getelementptr inbounds %struct.A, %struct.A* %4, i32 0, i32 3
  %6 = load i32, i32* %5
  %7 = srem i32 %6, 2
  %8 = icmp eq i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i1
  br i1 %10, label %if.then1, label %if.else1

if.then1:
  %11 = load %struct.A*, %struct.A** %this.addr
  %12 = getelementptr inbounds %struct.A, %struct.A* %11, i32 0, i32 0
  %13 = call i8* @__malloc(i32 16)
  %14 = bitcast i8* %13 to %struct.A*
  call void @__A_A(%struct.A* %14)
  %.tmp15 = alloca %struct.A*
  store %struct.A* %14, %struct.A** %.tmp15
  %15 = load %struct.A*, %struct.A** %.tmp15
  store %struct.A* %15, %struct.A** %12
  %16 = load i32, i32* @countB
  %17 = srem i32 %16, 2
  %18 = icmp eq i32 %17, 0
  %19 = zext i1 %18 to i32
  %20 = trunc i32 %19 to i1
  br i1 %20, label %if.then2, label %if.else2

if.then2:
  %21 = load %struct.A*, %struct.A** %this.addr
  %22 = getelementptr inbounds %struct.A, %struct.A* %21, i32 0, i32 1
  %23 = call i8* @__malloc(i32 8)
  %24 = bitcast i8* %23 to %struct.B*
  call void @__B_B(%struct.B* %24)
  %.tmp25 = alloca %struct.B*
  store %struct.B* %24, %struct.B** %.tmp25
  %25 = load %struct.B*, %struct.B** %.tmp25
  store %struct.B* %25, %struct.B** %22
  br label %if.end2

if.else2:
  %26 = load %struct.A*, %struct.A** %this.addr
  %27 = getelementptr inbounds %struct.A, %struct.A* %26, i32 0, i32 1
  store %struct.B* null, %struct.B** %27
  br label %if.end2

if.end2:
  br label %if.end1

if.else1:
  %28 = load %struct.A*, %struct.A** %this.addr
  %29 = getelementptr inbounds %struct.A, %struct.A* %28, i32 0, i32 0
  store %struct.A* null, %struct.A** %29
  br label %if.end1

if.end1:
  %30 = load %struct.A*, %struct.A** %this.addr
  %31 = getelementptr inbounds %struct.A, %struct.A* %30, i32 0, i32 2
  %32 = mul i32 2, 4
  %33 = add i32 %32, 4
  %34 = call i8* @__malloc(i32 %33)
  %35 = bitcast i8* %34 to i32*
  store i32 2, i32* %35
  %36 = bitcast i8* %34 to %struct.C***
  %37 = getelementptr inbounds %struct.C**, %struct.C*** %36, i32 1
  %.tmp38 = alloca %struct.C***
  store %struct.C*** %37, %struct.C**** %.tmp38
  %38 = load %struct.C***, %struct.C**** %.tmp38
  store %struct.C*** %38, %struct.C**** %31
  %39 = load %struct.A*, %struct.A** %this.addr
  %40 = getelementptr inbounds %struct.A, %struct.A* %39, i32 0, i32 2
  %41 = load %struct.C***, %struct.C**** %40
  %42 = getelementptr inbounds %struct.C**, %struct.C*** %41, i32 0
  %43 = mul i32 6, 4
  %44 = add i32 %43, 4
  %45 = call i8* @__malloc(i32 %44)
  %46 = bitcast i8* %45 to i32*
  store i32 6, i32* %46
  %47 = bitcast i8* %45 to %struct.C*****
  %48 = getelementptr inbounds %struct.C****, %struct.C***** %47, i32 1
  store i32 0, i32* %_new_i.0.3
  br label %array.for.begin3

array.for.begin3:
  %49 = load i32, i32* %_new_i.0.3
  %50 = icmp slt i32 %49, 6
  br i32 %50, label %array.for.body3, label %array.for.end3

array.for.body3:
  %51 = load i32, i32* %_new_i.0.3
  %52 = getelementptr inbounds %struct.C****, %struct.C***** %47, i32 %51
  %53 = mul i32 6, 4
  %54 = add i32 %53, 4
  %55 = call i8* @__malloc(i32 %54)
  %56 = bitcast i8* %55 to i32*
  store i32 6, i32* %56
  %57 = bitcast i8* %55 to %struct.C****
  %58 = getelementptr inbounds %struct.C***, %struct.C**** %57, i32 1
  store i32 0, i32* %_new_i.1.4
  br label %array.for.begin4

array.for.begin4:
  %59 = load i32, i32* %_new_i.1.4
  %60 = icmp slt i32 %59, 6
  br i32 %60, label %array.for.body4, label %array.for.end4

array.for.body4:
  %61 = load i32, i32* %_new_i.1.4
  %62 = getelementptr inbounds %struct.C***, %struct.C**** %57, i32 %61
  %63 = mul i32 6, 4
  %64 = add i32 %63, 4
  %65 = call i8* @__malloc(i32 %64)
  %66 = bitcast i8* %65 to i32*
  store i32 6, i32* %66
  %67 = bitcast i8* %65 to %struct.C***
  %68 = getelementptr inbounds %struct.C**, %struct.C*** %67, i32 1
  store i32 0, i32* %_new_i.2.5
  br label %array.for.begin5

array.for.begin5:
  %69 = load i32, i32* %_new_i.2.5
  %70 = icmp slt i32 %69, 6
  br i32 %70, label %array.for.body5, label %array.for.end5

array.for.body5:
  %71 = load i32, i32* %_new_i.2.5
  %72 = getelementptr inbounds %struct.C**, %struct.C*** %67, i32 %71
  %73 = mul i32 6, 4
  %74 = add i32 %73, 4
  %75 = call i8* @__malloc(i32 %74)
  %76 = bitcast i8* %75 to i32*
  store i32 6, i32* %76
  %77 = bitcast i8* %75 to %struct.C**
  %78 = getelementptr inbounds %struct.C*, %struct.C** %77, i32 1
  store %struct.C** %78, %struct.C*** %72
  %79 = load i32, i32* %_new_i.2.5
  %80 = add i32 %79, 1
  store i32 %80, i32* %_new_i.2.5
  br label %array.for.begin5

array.for.end5:
  store %struct.C*** %67, %struct.C**** %62
  %81 = load i32, i32* %_new_i.1.4
  %82 = add i32 %81, 1
  store i32 %82, i32* %_new_i.1.4
  br label %array.for.begin4

array.for.end4:
  store %struct.C**** %57, %struct.C***** %52
  %83 = load i32, i32* %_new_i.0.3
  %84 = add i32 %83, 1
  store i32 %84, i32* %_new_i.0.3
  br label %array.for.begin3

array.for.end3:
  %.tmp85 = alloca %struct.C*****
  store %struct.C***** %47, %struct.C****** %.tmp85
  %85 = load %struct.C*****, %struct.C****** %.tmp85
  %86 = getelementptr inbounds %struct.C****, %struct.C***** %85, i32 2
  %87 = load %struct.C****, %struct.C***** %86
  %88 = getelementptr inbounds %struct.C***, %struct.C**** %87, i32 3
  %89 = load %struct.C***, %struct.C**** %88
  %90 = getelementptr inbounds %struct.C**, %struct.C*** %89, i32 3
  %91 = load %struct.C**, %struct.C*** %90
  store %struct.C** %91, %struct.C*** %42
  %92 = load %struct.A*, %struct.A** %this.addr
  %93 = getelementptr inbounds %struct.A, %struct.A* %92, i32 0, i32 2
  %94 = load %struct.C***, %struct.C**** %93
  %95 = getelementptr inbounds %struct.C**, %struct.C*** %94, i32 1
  store %struct.C** null, %struct.C*** %95
  %96 = load %struct.A*, %struct.A** %this.addr
  %97 = getelementptr inbounds %struct.A, %struct.A* %96, i32 0, i32 2
  %98 = load %struct.C***, %struct.C**** %97
  %99 = bitcast %struct.C*** %98 to i32*
  %100 = getelementptr inbounds i32, i32* %99, i32 -1
  %101 = load i32, i32* %100
  %102 = icmp ne i32 %101, 2
  %103 = zext i1 %102 to i32
  %104 = trunc i32 %103 to i1
  br i1 %104, label %if.then6, label %if.end6

if.then6:
  call void @println(i8* getelementptr inbounds ([6 x i8], [6 x i8]* @.str.2, i32 0, i32 0))
  br label %if.end6

if.end6:
  br label %return

return:
  ret void
}

define void @__B_B (%struct.B* %this) {
entry:
  %.phi = alloca i32
  %this.addr = alloca %struct.B*
  store %struct.B* %this, %struct.B** %this.addr
  %0 = load %struct.B*, %struct.B** %this.addr
  %1 = getelementptr inbounds %struct.B, %struct.B* %0, i32 0, i32 0
  %2 = load i32, i32* @countB
  %3 = add i32 %2, 1
  store i32 %3, i32* @countB
  store i32 %2, i32* %1
  %4 = load %struct.B*, %struct.B** %this.addr
  %5 = getelementptr inbounds %struct.B, %struct.B* %4, i32 0, i32 1
  %6 = call i8* @__malloc(i32 16)
  %7 = bitcast i8* %6 to %struct.A*
  call void @__A_A(%struct.A* %7)
  %.tmp8 = alloca %struct.A*
  store %struct.A* %7, %struct.A** %.tmp8
  %8 = load %struct.A*, %struct.A** %.tmp8
  %9 = call %struct.C** @__A_getc0(%struct.A* %8)
  store %struct.C** %9, %struct.C*** %.tmp10
  %10 = load %struct.C**, %struct.C*** %.tmp10
  %11 = getelementptr inbounds %struct.C*, %struct.C** %10, i32 0
  %12 = load %struct.C*, %struct.C** %11
  %13 = call %struct.C* @__C_Me(%struct.C* %12)
  %14 = call %struct.C* @__C_Me(%struct.C* %13)
  store %struct.C* %14, %struct.C** %5
  br label %return

return:
  ret void
}

define %struct.C* @__C_Me (%struct.C* %this) {
entry:
  %retval = alloca %struct.C*
  %.phi = alloca i32
  %this.addr = alloca %struct.C*
  store %struct.C* %this, %struct.C** %this.addr
  %0 = load %struct.C*, %struct.C** %this.addr
  store %struct.C* %0, %struct.C** %retval
  br label %return

return:
  %1 = load %struct.C*, %struct.C** %retval
  ret %struct.C* %1
}

define void @__C_C (%struct.C* %this) {
entry:
  %.phi = alloca i32
  %this.addr = alloca %struct.C*
  store %struct.C* %this, %struct.C** %this.addr
  %0 = load %struct.C*, %struct.C** %this.addr
  %1 = getelementptr inbounds %struct.C, %struct.C* %0, i32 0, i32 0
  %2 = load i32, i32* @countC
  %3 = add i32 %2, 1
  store i32 %3, i32* @countC
  store i32 %2, i32* %1
  %4 = load %struct.C*, %struct.C** %this.addr
  %5 = getelementptr inbounds %struct.C, %struct.C* %4, i32 0, i32 1
  %6 = load %struct.C*, %struct.C** %this.addr
  %7 = getelementptr inbounds %struct.C, %struct.C* %6, i32 0, i32 0
  %8 = load i32, i32* %7
  %9 = call i8* @toString(i32 %8)
  store i8* %9, i8** %5
  %10 = load %struct.C*, %struct.C** %this.addr
  %11 = call %struct.C* @__C_Me(%struct.C* %10)
  store %struct.C* %11, %struct.C** @something
  br label %return

return:
  ret void
}

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

define void @count () {
entry:
  %.phi = alloca i32
  %b = alloca %struct.B*
  %temp = alloca i8*
  store i32 0, i32* @countA
  store i32 0, i32* @countB
  store i32 0, i32* @countC
  %0 = call i8* @__malloc(i32 8)
  %1 = bitcast i8* %0 to %struct.B*
  call void @__B_B(%struct.B* %1)
  %.tmp2 = alloca %struct.B*
  store %struct.B* %1, %struct.B** %.tmp2
  %2 = load %struct.B*, %struct.B** %.tmp2
  store %struct.B* %2, %struct.B** %b
  %3 = load i32, i32* @countA
  %4 = call i8* @toString(i32 %3)
  %5 = call i8* @___str_add(i8* %4, i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.3, i32 0, i32 0))
  %6 = load i32, i32* @countB
  %7 = call i8* @toString(i32 %6)
  %8 = call i8* @___str_add(i8* %5, i8* %7)
  %9 = call i8* @___str_add(i8* %8, i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.3, i32 0, i32 0))
  %10 = load i32, i32* @countC
  %11 = call i8* @toString(i32 %10)
  %12 = call i8* @___str_add(i8* %9, i8* %11)
  call void @println(i8* %12)
  store i32 1, i32* @countA
  store i32 1, i32* @countB
  store i32 1, i32* @countC
  %13 = call i8* @__malloc(i32 8)
  %14 = bitcast i8* %13 to %struct.B*
  call void @__B_B(%struct.B* %14)
  %.tmp15 = alloca %struct.B*
  store %struct.B* %14, %struct.B** %.tmp15
  %15 = load %struct.B*, %struct.B** %.tmp15
  store %struct.B* %15, %struct.B** %b
  %16 = load i32, i32* @countA
  %17 = sub nsw i32 %16, 1
  %18 = call i8* @toString(i32 %17)
  %19 = call i8* @___str_add(i8* %18, i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.3, i32 0, i32 0))
  %20 = load i32, i32* @countB
  %21 = sub nsw i32 %20, 1
  %22 = call i8* @toString(i32 %21)
  %23 = call i8* @___str_add(i8* %19, i8* %22)
  %24 = call i8* @___str_add(i8* %23, i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.3, i32 0, i32 0))
  %25 = load i32, i32* @countC
  %26 = sub nsw i32 %25, 1
  %27 = call i8* @toString(i32 %26)
  %28 = call i8* @___str_add(i8* %24, i8* %27)
  call void @print(i8* %28)
  call void @print(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.4, i32 0, i32 0))
  %29 = load %struct.C*, %struct.C** @something
  %30 = call %struct.C* @__C_Me(%struct.C* %29)
  %31 = getelementptr inbounds %struct.C, %struct.C* %30, i32 0, i32 1
  %32 = load i8*, i8** %31
  %33 = load %struct.C*, %struct.C** @something
  %34 = getelementptr inbounds %struct.C, %struct.C* %33, i32 0, i32 1
  %35 = load i8*, i8** %34
  %36 = call i32 @__string_length(i8* %35)
  %37 = sub nsw i32 %36, 1
  %38 = call i8* @__string_substring(i32 1, i32 %37, i8* %32)
  %39 = call i32 @__string_parseInt(i8* %38)
  %40 = call i8* @toString(i32 %39)
  call void @println(i8* %40)
  %41 = load %struct.C*, %struct.C** @something
  %42 = getelementptr inbounds %struct.C, %struct.C* %41, i32 0, i32 1
  %43 = load i8*, i8** %42
  %44 = and i32 42, 21
  %45 = call i32 @__string_ord(i32 %44, i8* %43)
  %46 = call i8* @toString(i32 %45)
  store i8* %46, i8** %temp
  %47 = load i8*, i8** %temp
  %48 = load %struct.C*, %struct.C** @something
  %49 = getelementptr inbounds %struct.C, %struct.C* %48, i32 0, i32 1
  %50 = load i8*, i8** %49
  %52 = zext i8 %51 to i32
  %51 = call i8 @___str_slt(i8* %47, i8* %50)
  %53 = trunc i32 %52 to i1
  br i1 %53, label %if.then1, label %if.else1

if.then1:
  %54 = load %struct.C*, %struct.C** @something
  %55 = getelementptr inbounds %struct.C, %struct.C* %54, i32 0, i32 1
  %56 = load i8*, i8** %55
  %57 = call i8* @___str_add(i8* %56, i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.5, i32 0, i32 0))
  %58 = load i8*, i8** %temp
  %59 = call i8* @___str_add(i8* %57, i8* %58)
  call void @println(i8* %59)
  br label %if.end1

if.else1:
  %60 = load %struct.C*, %struct.C** @something
  %61 = getelementptr inbounds %struct.C, %struct.C* %60, i32 0, i32 1
  %62 = load i8*, i8** %61
  %63 = call i8* @___str_add(i8* %62, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.6, i32 0, i32 0))
  %64 = load i8*, i8** %temp
  %65 = call i8* @___str_add(i8* %63, i8* %64)
  call void @println(i8* %65)
  br label %if.end1

if.end1:
  br label %return

return:
  ret void
}

define i32 @main () {
entry:
  %retval = alloca i32
  %.phi = alloca i32
  %i = alloca i32
  %_i1 = alloca i32
  %_i2 = alloca i32
  call void @__mx_initialize_globalVar()
  store i32 0, i32* %i
  br label %for.begin1

for.begin1:
  br label %for.body1

for.body1:
  %0 = load i32, i32* %i
  %1 = and i32 891, 759
  %2 = xor i32 %0, %1
  %3 = icmp eq i32 %2, 666
  %4 = zext i1 %3 to i32
  %5 = trunc i32 0 to i1
  %6 = xor i1 %5, 1
  %7 = zext i1 %6 to i32
  %8 = icmp eq i32 %4, %7
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i1
  br i1 %10, label %if.then2, label %if.end2

if.then2:
  %11 = load i32, i32* %i
  %12 = call i8* @toString(i32 %11)
  call void @println(i8* %12)
  store i32 0, i32* %_i1
  %13 = load i32, i32* %_i1
  %14 = call i8* @toString(i32 %13)
  call void @println(i8* %14)
  store i32 1, i32* %_i2
  %15 = load i32, i32* %_i2
  %16 = call i8* @toString(i32 %15)
  call void @println(i8* %16)
  call void @count()
  br label %for.end1

if.end2:
  %17 = load i32, i32* %i
  %18 = add i32 %17, 1
  store i32 %18, i32* %i
  br label %for.begin1

for.end1:
  br label %while.begin3

while.begin3:
  %19 = trunc i32 1 to i1
  br i1 %19, label %while.body3, label %while.end3

while.body3:
  %20 = load i32, i32* %i
  %21 = srem i32 %20, 2
  %22 = icmp eq i32 %21, 0
  %23 = zext i1 %22 to i32
  %24 = trunc i32 %23 to i1
  br i1 %24, label %if.then4, label %if.end4

if.then4:
  br label %while.begin3

if.end4:
  %25 = load i32, i32* %i
  %26 = call i8* @toString(i32 %25)
  %27 = call i8* @___str_add(i8* %26, i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str, i32 0, i32 0))
  call void @print(i8* %27)
  br label %while.begin3

while.end3:
  call void @println(i8* getelementptr inbounds ([1 x i8], [1 x i8]* @.str.1, i32 0, i32 0))
  store i32 0, i32* %retval
  br label %return

return:
  %28 = load i32, i32* %retval
  ret i32 %28
}

