target datalayout = "e-m:e-p:32:32-p270:32:32-p271:32:32-p272:64:64-f64:32:64-f80:32-n8:16:32-S128"
target triple = "i386-pc-linux-gnu"

%struct.Edge = type { i32, i32, i32 }

@n = global i32 0
@m = global i32 0
@ans = global i32 0
@fa = global i32* null
@rk = global i32* null
@e = global %struct.Edge** null


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
  %.phi = alloca i32
  %i = alloca i32
  %ed = alloca %struct.Edge*
  call void @__mx_initialize_globalVar()
  %0 = call i32 @getInt()
  store i32 %0, i32* @n
  %1 = call i32 @getInt()
  store i32 %1, i32* @m
  %2 = load i32, i32* @m
  %3 = mul i32 %2, 4
  %4 = add i32 %3, 4
  %5 = call i8* @__malloc(i32 %4)
  %6 = bitcast i8* %5 to i32*
  store i32 %2, i32* %6
  %7 = getelementptr inbounds i8, i8* %5, i32 4
  %8 = bitcast i8* %7 to %struct.Edge**
  store %struct.Edge** %8, %struct.Edge*** @e
  store i32 0, i32* %i
  br label %for.begin1

for.begin1:
  %9 = load i32, i32* %i
  %10 = load i32, i32* @m
  %11 = icmp slt i32 %9, %10
  %12 = zext i1 %11 to i32
  %13 = trunc i32 %12 to i1
  br i1 %13, label %for.body1, label %for.end1

for.body1:
  %14 = call i8* @__malloc(i32 12)
  %15 = bitcast i8* %14 to %struct.Edge*
  store %struct.Edge* %15, %struct.Edge** %ed
  %16 = load %struct.Edge*, %struct.Edge** %ed
  %17 = getelementptr inbounds %struct.Edge, %struct.Edge* %16, i32 0, i32 0
  %18 = call i32 @getInt()
  store i32 %18, i32* %17
  %19 = load %struct.Edge*, %struct.Edge** %ed
  %20 = getelementptr inbounds %struct.Edge, %struct.Edge* %19, i32 0, i32 1
  %21 = call i32 @getInt()
  store i32 %21, i32* %20
  %22 = load %struct.Edge*, %struct.Edge** %ed
  %23 = getelementptr inbounds %struct.Edge, %struct.Edge* %22, i32 0, i32 2
  %24 = call i32 @getInt()
  store i32 %24, i32* %23
  %25 = load %struct.Edge**, %struct.Edge*** @e
  %26 = load i32, i32* %i
  %27 = getelementptr inbounds %struct.Edge*, %struct.Edge** %25, i32 %26
  %28 = load %struct.Edge*, %struct.Edge** %ed
  store %struct.Edge* %28, %struct.Edge** %27
  %29 = load i32, i32* %i
  %30 = add i32 %29, 1
  store i32 %30, i32* %i
  br label %for.begin1

for.end1:
  store i32 0, i32* %i
  br label %for.begin2

for.begin2:
  %31 = load i32, i32* %i
  %32 = load i32, i32* @m
  %33 = icmp slt i32 %31, %32
  %34 = zext i1 %33 to i32
  %35 = trunc i32 %34 to i1
  br i1 %35, label %for.body2, label %for.end2

for.body2:
  %36 = load %struct.Edge**, %struct.Edge*** @e
  %37 = load i32, i32* %i
  %38 = getelementptr inbounds %struct.Edge*, %struct.Edge** %36, i32 %37
  %39 = load %struct.Edge*, %struct.Edge** %38
  %40 = getelementptr inbounds %struct.Edge, %struct.Edge* %39, i32 0, i32 0
  %41 = load i32, i32* %40
  call void @printlnInt(i32 %41)
  %42 = load %struct.Edge**, %struct.Edge*** @e
  %43 = load i32, i32* %i
  %44 = getelementptr inbounds %struct.Edge*, %struct.Edge** %42, i32 %43
  %45 = load %struct.Edge*, %struct.Edge** %44
  %46 = getelementptr inbounds %struct.Edge, %struct.Edge* %45, i32 0, i32 1
  %47 = load i32, i32* %46
  call void @printlnInt(i32 %47)
  %48 = load %struct.Edge**, %struct.Edge*** @e
  %49 = load i32, i32* %i
  %50 = getelementptr inbounds %struct.Edge*, %struct.Edge** %48, i32 %49
  %51 = load %struct.Edge*, %struct.Edge** %50
  %52 = getelementptr inbounds %struct.Edge, %struct.Edge* %51, i32 0, i32 2
  %53 = load i32, i32* %52
  call void @printlnInt(i32 %53)
  %54 = load i32, i32* %i
  %55 = add i32 %54, 1
  store i32 %55, i32* %i
  br label %for.begin2

for.end2:
  store i32 0, i32* %retval
  br label %return

return:
  %56 = load i32, i32* %retval
  ret i32 %56
}

