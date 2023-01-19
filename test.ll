target datalayout = "e-m:e-p:32:32-p270:32:32-p271:32:32-p272:64:64-f64:32:64-f80:32-n8:16:32-S128"
target triple = "i386-pc-linux-gnu"
%struct.Edge = type { i32, i32, i32 }

@n = global i32 0
@m = global i32 0
@ans = global i32 0
@fa = global i32* null
@rk = global i32* null
@e = global %struct.Edge** null

@.str = private unnamed_addr constant [2 x i8] c " \00"

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
  store i32 0, i32* @ans
  ret void

}

declare i8* @__malloc (i32)

declare i1 @___str_eq (i8*, i8*)

declare i1 @___str_ne (i8*, i8*)

declare i1 @___str_slt (i8*, i8*)

declare i1 @___str_sle (i8*, i8*)

declare i1 @___str_sgt (i8*, i8*)

declare i1 @___str_sge (i8*, i8*)

declare i8* @___str_add (i8*, i8*)

define i32 @main () {
entry:
  call void @__mx_initialize_globalVar()
  %retval = alloca i32
  %.phi = alloca i1
  %0 = call i32 @getInt()
  store i32 %0, i32* @m
  %1 = load i32, i32* @m
  %2 = mul i32 %1, 4
  %3 = add i32 %2, 4
  %4 = call i8* @__malloc(i32 %3)
  %5 = bitcast i8* %4 to i32*
  store i32 %1, i32* %5
  %6 = getelementptr inbounds i8, i8* %4, i32 4
  %7 = bitcast i8* %6 to %struct.Edge**
  store %struct.Edge** %7, %struct.Edge*** @e
  %i = alloca i32
  store i32 0, i32* %i
  br label %for.begin1

for.begin1:
  %8 = load i32, i32* %i
  %9 = load i32, i32* @m
  %10 = icmp slt i32 %8, %9
  br i1 %10, label %for.body1, label %for.end1

for.body1:
  %ed = alloca %struct.Edge*
  %11 = call i8* @__malloc(i32 12)
  %12 = bitcast i8* %11 to %struct.Edge*
  store %struct.Edge* %12, %struct.Edge** %ed
  %13 = load %struct.Edge*, %struct.Edge** %ed
  %14 = getelementptr inbounds %struct.Edge, %struct.Edge* %13, i32 0, i32 0
  %15 = call i32 @getInt()
  store i32 %15, i32* %14
  %16 = load %struct.Edge*, %struct.Edge** %ed
  %17 = getelementptr inbounds %struct.Edge, %struct.Edge* %16, i32 0, i32 1
  %18 = call i32 @getInt()
  store i32 %18, i32* %17
  %19 = load %struct.Edge*, %struct.Edge** %ed
  %20 = getelementptr inbounds %struct.Edge, %struct.Edge* %19, i32 0, i32 2
  %21 = call i32 @getInt()
  store i32 %21, i32* %20
  %22 = load %struct.Edge**, %struct.Edge*** @e
  %23 = load i32, i32* %i
  %24 = getelementptr inbounds %struct.Edge*, %struct.Edge** %22, i32 %23
  %25 = load %struct.Edge*, %struct.Edge** %ed
  store %struct.Edge* %25, %struct.Edge** %24
  %26 = load i32, i32* %i
  %27 = add i32 %26, 1
  store i32 %27, i32* %i
  br label %for.begin1

for.end1:
  store i32 0, i32* %i
  br label %for.begin2

for.begin2:
  %28 = load i32, i32* %i
  %29 = load i32, i32* @m
  %30 = icmp slt i32 %28, %29
  br i1 %30, label %for.body2, label %for.end2

for.body2:
  %31 = load %struct.Edge**, %struct.Edge*** @e
  %32 = load i32, i32* %i
  %33 = getelementptr inbounds %struct.Edge*, %struct.Edge** %31, i32 %32
  %34 = load %struct.Edge*, %struct.Edge** %33
  %35 = getelementptr inbounds %struct.Edge, %struct.Edge* %34, i32 0, i32 0
  %36 = load i32, i32* %35
  call void @printInt(i32 %36)
  call void @print(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str, i32 0, i32 0))
  %37 = load %struct.Edge**, %struct.Edge*** @e
  %38 = load i32, i32* %i
  %39 = getelementptr inbounds %struct.Edge*, %struct.Edge** %37, i32 %38
  %40 = load %struct.Edge*, %struct.Edge** %39
  %41 = getelementptr inbounds %struct.Edge, %struct.Edge* %40, i32 0, i32 1
  %42 = load i32, i32* %41
  call void @printInt(i32 %42)
  call void @print(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str, i32 0, i32 0))
  %43 = load %struct.Edge**, %struct.Edge*** @e
  %44 = load i32, i32* %i
  %45 = getelementptr inbounds %struct.Edge*, %struct.Edge** %43, i32 %44
  %46 = load %struct.Edge*, %struct.Edge** %45
  %47 = getelementptr inbounds %struct.Edge, %struct.Edge* %46, i32 0, i32 2
  %48 = load i32, i32* %47
  call void @printlnInt(i32 %48)
  %49 = load i32, i32* %i
  %50 = add i32 %49, 1
  store i32 %50, i32* %i
  br label %for.begin2

for.end2:
  store i32 0, i32* %retval
  br label %return

return:
  %51 = load i32, i32* %retval
  ret i32 %51
}

