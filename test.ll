target datalayout = "e-m:e-p:32:32-p270:32:32-p271:32:32-p272:64:64-f64:32:64-f80:32-n8:16:32-S128"
target triple = "i386-pc-linux-gnu"

%struct.Queue_int = type { i32*, i32, i32 }

%struct.Edge = type { i32, i32, i32 }

%struct.EdgeList = type { %struct.Edge**, i32*, i32*, i32 }

@n = global i32 0
@m = global i32 0
@g = global %struct.EdgeList* null
@INF = global i32 0

@.str = private unnamed_addr constant [3 x i8] c "-1\00"
@.str.1 = private unnamed_addr constant [2 x i8] c " \00"
@.str.2 = private unnamed_addr constant [1 x i8] c "\00"
@.str.3 = private unnamed_addr constant [37 x i8] c "Warning: Queue_int::pop: empty queue\00"

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

define i32 @__Queue_int_pop (%struct.Queue_int* %this) {
entry:
  %retval = alloca i32
  %.phi = alloca i8
  %this.addr = alloca %struct.Queue_int*
  store %struct.Queue_int* %this, %struct.Queue_int** %this.addr
  %0 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %1 = call i32 @__Queue_int_size(%struct.Queue_int* %0)
  %2 = icmp eq i32 %1, 0
  %3 = zext i1 %2 to i8
  %4 = trunc i8 %3 to i1
  br i1 %4, label %if.then1, label %if.end1

if.then1:
  call void @println(i8* getelementptr inbounds ([37 x i8], [37 x i8]* @.str.3, i32 0, i32 0))
  br label %if.end1

if.end1:
  %res = alloca i32
  %5 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %6 = call i32 @__Queue_int_top(%struct.Queue_int* %5)
  store i32 %6, i32* %res
  %7 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %8 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %7, i32 0, i32 1
  %9 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %10 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %9, i32 0, i32 1
  %11 = load i32, i32* %10
  %12 = add i32 %11, 1
  %13 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %14 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %13, i32 0, i32 0
  %15 = load i32*, i32** %14
  %16 = bitcast i32* %15 to i32*
  %17 = getelementptr inbounds i32, i32* %16, i32 -1
  %18 = load i32, i32* %17
  %19 = srem i32 %12, %18
  store i32 %19, i32* %8
  %20 = load i32, i32* %res
  store i32 %20, i32* %retval
  br label %return

return:
  %21 = load i32, i32* %retval
  ret i32 %21
}

define i32 @__Queue_int_top (%struct.Queue_int* %this) {
entry:
  %retval = alloca i32
  %.phi = alloca i8
  %this.addr = alloca %struct.Queue_int*
  store %struct.Queue_int* %this, %struct.Queue_int** %this.addr
  %0 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %1 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %0, i32 0, i32 0
  %2 = load i32*, i32** %1
  %3 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %4 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %3, i32 0, i32 1
  %5 = load i32, i32* %4
  %6 = getelementptr inbounds i32, i32* %2, i32 %5
  %7 = load i32, i32* %6
  store i32 %7, i32* %retval
  br label %return

return:
  %8 = load i32, i32* %retval
  ret i32 %8
}

define i32 @__Queue_int_size (%struct.Queue_int* %this) {
entry:
  %retval = alloca i32
  %.phi = alloca i8
  %this.addr = alloca %struct.Queue_int*
  store %struct.Queue_int* %this, %struct.Queue_int** %this.addr
  %0 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %1 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %0, i32 0, i32 2
  %2 = load i32, i32* %1
  %3 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %4 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %3, i32 0, i32 0
  %5 = load i32*, i32** %4
  %6 = bitcast i32* %5 to i32*
  %7 = getelementptr inbounds i32, i32* %6, i32 -1
  %8 = load i32, i32* %7
  %9 = add i32 %2, %8
  %10 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %11 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %10, i32 0, i32 1
  %12 = load i32, i32* %11
  %13 = sub nsw i32 %9, %12
  %14 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %15 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %14, i32 0, i32 0
  %16 = load i32*, i32** %15
  %17 = bitcast i32* %16 to i32*
  %18 = getelementptr inbounds i32, i32* %17, i32 -1
  %19 = load i32, i32* %18
  %20 = srem i32 %13, %19
  store i32 %20, i32* %retval
  br label %return

return:
  %21 = load i32, i32* %retval
  ret i32 %21
}

define void @__Queue_int_doubleStorage (%struct.Queue_int* %this) {
entry:
  %.phi = alloca i8
  %this.addr = alloca %struct.Queue_int*
  store %struct.Queue_int* %this, %struct.Queue_int** %this.addr
  %copy = alloca i32*
  %0 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %1 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %0, i32 0, i32 0
  %2 = load i32*, i32** %1
  store i32* %2, i32** %copy
  %begCopy = alloca i32
  %3 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %4 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %3, i32 0, i32 1
  %5 = load i32, i32* %4
  store i32 %5, i32* %begCopy
  %endCopy = alloca i32
  %6 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %7 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %6, i32 0, i32 2
  %8 = load i32, i32* %7
  store i32 %8, i32* %endCopy
  %9 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %10 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %9, i32 0, i32 0
  %11 = load i32*, i32** %copy
  %12 = bitcast i32* %11 to i32*
  %13 = getelementptr inbounds i32, i32* %12, i32 -1
  %14 = load i32, i32* %13
  %15 = mul i32 %14, 2
  %16 = mul i32 %15, 4
  %17 = add i32 %16, 4
  %18 = call i8* @__malloc(i32 %17)
  %19 = bitcast i8* %18 to i32*
  store i32 %15, i32* %19
  %20 = getelementptr inbounds i8, i8* %18, i32 4
  %21 = bitcast i8* %20 to i32*
  store i32* %21, i32** %10
  %22 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %23 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %22, i32 0, i32 1
  store i32 0, i32* %23
  %24 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %25 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %24, i32 0, i32 2
  store i32 0, i32* %25
  %i = alloca i32
  %26 = load i32, i32* %begCopy
  store i32 %26, i32* %i
  br label %while.begin1

while.begin1:
  %27 = load i32, i32* %i
  %28 = load i32, i32* %endCopy
  %29 = icmp ne i32 %27, %28
  %30 = zext i1 %29 to i8
  %31 = trunc i8 %30 to i1
  br i1 %31, label %while.body1, label %while.end1

while.body1:
  %32 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %33 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %32, i32 0, i32 0
  %34 = load i32*, i32** %33
  %35 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %36 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %35, i32 0, i32 2
  %37 = load i32, i32* %36
  %38 = getelementptr inbounds i32, i32* %34, i32 %37
  %39 = load i32*, i32** %copy
  %40 = load i32, i32* %i
  %41 = getelementptr inbounds i32, i32* %39, i32 %40
  %42 = load i32, i32* %41
  store i32 %42, i32* %38
  %43 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %44 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %43, i32 0, i32 2
  %45 = load i32, i32* %44
  %46 = add i32 %45, 1
  store i32 %46, i32* %44
  %47 = load i32, i32* %i
  %48 = add i32 %47, 1
  %49 = load i32*, i32** %copy
  %50 = bitcast i32* %49 to i32*
  %51 = getelementptr inbounds i32, i32* %50, i32 -1
  %52 = load i32, i32* %51
  %53 = srem i32 %48, %52
  store i32 %53, i32* %i
  br label %while.begin1

while.end1:
  br label %return

return:
  ret void
}

define void @__Queue_int_push (i32 %v, %struct.Queue_int* %this) {
entry:
  %.phi = alloca i8
  %v.addr = alloca i32
  store i32 %v, i32* %v.addr
  %this.addr = alloca %struct.Queue_int*
  store %struct.Queue_int* %this, %struct.Queue_int** %this.addr
  %0 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %1 = call i32 @__Queue_int_size(%struct.Queue_int* %0)
  %2 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %3 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %2, i32 0, i32 0
  %4 = load i32*, i32** %3
  %5 = bitcast i32* %4 to i32*
  %6 = getelementptr inbounds i32, i32* %5, i32 -1
  %7 = load i32, i32* %6
  %8 = sub nsw i32 %7, 1
  %9 = icmp eq i32 %1, %8
  %10 = zext i1 %9 to i8
  %11 = trunc i8 %10 to i1
  br i1 %11, label %if.then1, label %if.end1

if.then1:
  %12 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  call void @__Queue_int_doubleStorage(%struct.Queue_int* %12)
  br label %if.end1

if.end1:
  %13 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %14 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %13, i32 0, i32 0
  %15 = load i32*, i32** %14
  %16 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %17 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %16, i32 0, i32 2
  %18 = load i32, i32* %17
  %19 = getelementptr inbounds i32, i32* %15, i32 %18
  %20 = load i32, i32* %v.addr
  store i32 %20, i32* %19
  %21 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %22 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %21, i32 0, i32 2
  %23 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %24 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %23, i32 0, i32 2
  %25 = load i32, i32* %24
  %26 = add i32 %25, 1
  %27 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %28 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %27, i32 0, i32 0
  %29 = load i32*, i32** %28
  %30 = bitcast i32* %29 to i32*
  %31 = getelementptr inbounds i32, i32* %30, i32 -1
  %32 = load i32, i32* %31
  %33 = srem i32 %26, %32
  store i32 %33, i32* %22
  br label %return

return:
  ret void
}

define void @__Queue_int_Queue_int (%struct.Queue_int* %this) {
entry:
  %.phi = alloca i8
  %this.addr = alloca %struct.Queue_int*
  store %struct.Queue_int* %this, %struct.Queue_int** %this.addr
  %0 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %1 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %0, i32 0, i32 1
  store i32 0, i32* %1
  %2 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %3 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %2, i32 0, i32 2
  store i32 0, i32* %3
  %4 = load %struct.Queue_int*, %struct.Queue_int** %this.addr
  %5 = getelementptr inbounds %struct.Queue_int, %struct.Queue_int* %4, i32 0, i32 0
  %6 = mul i32 16, 4
  %7 = add i32 %6, 4
  %8 = call i8* @__malloc(i32 %7)
  %9 = bitcast i8* %8 to i32*
  store i32 16, i32* %9
  %10 = getelementptr inbounds i8, i8* %8, i32 4
  %11 = bitcast i8* %10 to i32*
  store i32* %11, i32** %5
  br label %return

return:
  ret void
}

define void @__EdgeList_init (i32 %n, i32 %m, %struct.EdgeList* %this) {
entry:
  %.phi = alloca i8
  %n.addr = alloca i32
  store i32 %n, i32* %n.addr
  %m.addr = alloca i32
  store i32 %m, i32* %m.addr
  %this.addr = alloca %struct.EdgeList*
  store %struct.EdgeList* %this, %struct.EdgeList** %this.addr
  %0 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %1 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %0, i32 0, i32 0
  %2 = load i32, i32* %m.addr
  %3 = mul i32 %2, 4
  %4 = add i32 %3, 4
  %5 = call i8* @__malloc(i32 %4)
  %6 = bitcast i8* %5 to i32*
  store i32 %2, i32* %6
  %7 = getelementptr inbounds i8, i8* %5, i32 4
  %8 = bitcast i8* %7 to %struct.Edge**
  store %struct.Edge** %8, %struct.Edge*** %1
  %9 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %10 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %9, i32 0, i32 1
  %11 = load i32, i32* %m.addr
  %12 = mul i32 %11, 4
  %13 = add i32 %12, 4
  %14 = call i8* @__malloc(i32 %13)
  %15 = bitcast i8* %14 to i32*
  store i32 %11, i32* %15
  %16 = getelementptr inbounds i8, i8* %14, i32 4
  %17 = bitcast i8* %16 to i32*
  store i32* %17, i32** %10
  %18 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %19 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %18, i32 0, i32 2
  %20 = load i32, i32* %n.addr
  %21 = mul i32 %20, 4
  %22 = add i32 %21, 4
  %23 = call i8* @__malloc(i32 %22)
  %24 = bitcast i8* %23 to i32*
  store i32 %20, i32* %24
  %25 = getelementptr inbounds i8, i8* %23, i32 4
  %26 = bitcast i8* %25 to i32*
  store i32* %26, i32** %19
  %_i1 = alloca i32
  store i32 0, i32* %_i1
  br label %for.begin1

for.begin1:
  %27 = load i32, i32* %_i1
  %28 = load i32, i32* %m.addr
  %29 = icmp slt i32 %27, %28
  %30 = zext i1 %29 to i8
  %31 = trunc i8 %30 to i1
  br i1 %31, label %for.body1, label %for.end1

for.body1:
  %32 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %33 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %32, i32 0, i32 1
  %34 = load i32*, i32** %33
  %35 = load i32, i32* %_i1
  %36 = getelementptr inbounds i32, i32* %34, i32 %35
  %37 = sub nsw i32 0, 1
  store i32 %37, i32* %36
  %38 = load i32, i32* %_i1
  %39 = add i32 %38, 1
  store i32 %39, i32* %_i1
  br label %for.begin1

for.end1:
  store i32 0, i32* %_i1
  br label %for.begin2

for.begin2:
  %40 = load i32, i32* %_i1
  %41 = load i32, i32* %n.addr
  %42 = icmp slt i32 %40, %41
  %43 = zext i1 %42 to i8
  %44 = trunc i8 %43 to i1
  br i1 %44, label %for.body2, label %for.end2

for.body2:
  %45 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %46 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %45, i32 0, i32 2
  %47 = load i32*, i32** %46
  %48 = load i32, i32* %_i1
  %49 = getelementptr inbounds i32, i32* %47, i32 %48
  %50 = sub nsw i32 0, 1
  store i32 %50, i32* %49
  %51 = load i32, i32* %_i1
  %52 = add i32 %51, 1
  store i32 %52, i32* %_i1
  br label %for.begin2

for.end2:
  %53 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %54 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %53, i32 0, i32 3
  store i32 0, i32* %54
  br label %return

return:
  ret void
}

define i32 @__EdgeList_nEdges (%struct.EdgeList* %this) {
entry:
  %retval = alloca i32
  %.phi = alloca i8
  %this.addr = alloca %struct.EdgeList*
  store %struct.EdgeList* %this, %struct.EdgeList** %this.addr
  %0 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %1 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %0, i32 0, i32 0
  %2 = load %struct.Edge**, %struct.Edge*** %1
  %3 = bitcast %struct.Edge** %2 to i32*
  %4 = getelementptr inbounds i32, i32* %3, i32 -1
  %5 = load i32, i32* %4
  store i32 %5, i32* %retval
  br label %return

return:
  %6 = load i32, i32* %retval
  ret i32 %6
}

define i32 @__EdgeList_nVertices (%struct.EdgeList* %this) {
entry:
  %retval = alloca i32
  %.phi = alloca i8
  %this.addr = alloca %struct.EdgeList*
  store %struct.EdgeList* %this, %struct.EdgeList** %this.addr
  %0 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %1 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %0, i32 0, i32 2
  %2 = load i32*, i32** %1
  %3 = bitcast i32* %2 to i32*
  %4 = getelementptr inbounds i32, i32* %3, i32 -1
  %5 = load i32, i32* %4
  store i32 %5, i32* %retval
  br label %return

return:
  %6 = load i32, i32* %retval
  ret i32 %6
}

define void @__EdgeList_addEdge (i32 %u, i32 %v, i32 %w, %struct.EdgeList* %this) {
entry:
  %.phi = alloca i8
  %u.addr = alloca i32
  store i32 %u, i32* %u.addr
  %v.addr = alloca i32
  store i32 %v, i32* %v.addr
  %w.addr = alloca i32
  store i32 %w, i32* %w.addr
  %this.addr = alloca %struct.EdgeList*
  store %struct.EdgeList* %this, %struct.EdgeList** %this.addr
  %e = alloca %struct.Edge*
  %0 = call i8* @__malloc(i32 12)
  %1 = bitcast i8* %0 to %struct.Edge*
  store %struct.Edge* %1, %struct.Edge** %e
  %2 = load %struct.Edge*, %struct.Edge** %e
  %3 = getelementptr inbounds %struct.Edge, %struct.Edge* %2, i32 0, i32 0
  %4 = load i32, i32* %u.addr
  store i32 %4, i32* %3
  %5 = load %struct.Edge*, %struct.Edge** %e
  %6 = getelementptr inbounds %struct.Edge, %struct.Edge* %5, i32 0, i32 1
  %7 = load i32, i32* %v.addr
  store i32 %7, i32* %6
  %8 = load %struct.Edge*, %struct.Edge** %e
  %9 = getelementptr inbounds %struct.Edge, %struct.Edge* %8, i32 0, i32 2
  %10 = load i32, i32* %w.addr
  store i32 %10, i32* %9
  %11 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %12 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %11, i32 0, i32 0
  %13 = load %struct.Edge**, %struct.Edge*** %12
  %14 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %15 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %14, i32 0, i32 3
  %16 = load i32, i32* %15
  %17 = getelementptr inbounds %struct.Edge*, %struct.Edge** %13, i32 %16
  %18 = load %struct.Edge*, %struct.Edge** %e
  store %struct.Edge* %18, %struct.Edge** %17
  %19 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %20 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %19, i32 0, i32 1
  %21 = load i32*, i32** %20
  %22 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %23 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %22, i32 0, i32 3
  %24 = load i32, i32* %23
  %25 = getelementptr inbounds i32, i32* %21, i32 %24
  %26 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %27 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %26, i32 0, i32 2
  %28 = load i32*, i32** %27
  %29 = load i32, i32* %u.addr
  %30 = getelementptr inbounds i32, i32* %28, i32 %29
  %31 = load i32, i32* %30
  store i32 %31, i32* %25
  %32 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %33 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %32, i32 0, i32 2
  %34 = load i32*, i32** %33
  %35 = load i32, i32* %u.addr
  %36 = getelementptr inbounds i32, i32* %34, i32 %35
  %37 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %38 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %37, i32 0, i32 3
  %39 = load i32, i32* %38
  store i32 %39, i32* %36
  %40 = load %struct.EdgeList*, %struct.EdgeList** %this.addr
  %41 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %40, i32 0, i32 3
  %42 = load i32, i32* %41
  %43 = add i32 %42, 1
  store i32 %43, i32* %41
  br label %return

return:
  ret void
}

define void @__mx_initialize_globalVar () {
entry:
  store i32 10000000, i32* @INF
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

define void @init () {
entry:
  %.phi = alloca i8
  %0 = call i32 @getInt()
  store i32 %0, i32* @n
  %1 = call i32 @getInt()
  store i32 %1, i32* @m
  %2 = call i8* @__malloc(i32 16)
  %3 = bitcast i8* %2 to %struct.EdgeList*
  store %struct.EdgeList* %3, %struct.EdgeList** @g
  %4 = load %struct.EdgeList*, %struct.EdgeList** @g
  %5 = load i32, i32* @n
  %6 = load i32, i32* @m
  call void @__EdgeList_init(i32 %5, i32 %6, %struct.EdgeList* %4)
  %_i2 = alloca i32
  store i32 0, i32* %_i2
  br label %for.begin1

for.begin1:
  %7 = load i32, i32* %_i2
  %8 = load i32, i32* @m
  %9 = icmp slt i32 %7, %8
  %10 = zext i1 %9 to i8
  %11 = trunc i8 %10 to i1
  br i1 %11, label %for.body1, label %for.end1

for.body1:
  %u = alloca i32
  %12 = call i32 @getInt()
  store i32 %12, i32* %u
  %v = alloca i32
  %13 = call i32 @getInt()
  store i32 %13, i32* %v
  %w = alloca i32
  %14 = call i32 @getInt()
  store i32 %14, i32* %w
  %15 = load %struct.EdgeList*, %struct.EdgeList** @g
  %16 = load i32, i32* %u
  %17 = load i32, i32* %v
  %18 = load i32, i32* %w
  call void @__EdgeList_addEdge(i32 %16, i32 %17, i32 %18, %struct.EdgeList* %15)
  %19 = load i32, i32* %_i2
  %20 = add i32 %19, 1
  store i32 %20, i32* %_i2
  br label %for.begin1

for.end1:
  br label %return

return:
  ret void
}

define i32* @spfa (i32 %s) {
entry:
  %retval = alloca i32*
  %.phi = alloca i8
  %s.addr = alloca i32
  store i32 %s, i32* %s.addr
  %d = alloca i32*
  %0 = load i32, i32* @n
  %1 = mul i32 %0, 4
  %2 = add i32 %1, 4
  %3 = call i8* @__malloc(i32 %2)
  %4 = bitcast i8* %3 to i32*
  store i32 %0, i32* %4
  %5 = getelementptr inbounds i8, i8* %3, i32 4
  %6 = bitcast i8* %5 to i32*
  store i32* %6, i32** %d
  %_i3 = alloca i32
  store i32 0, i32* %_i3
  br label %for.begin1

for.begin1:
  %7 = load i32, i32* %_i3
  %8 = load i32, i32* @n
  %9 = icmp slt i32 %7, %8
  %10 = zext i1 %9 to i8
  %11 = trunc i8 %10 to i1
  br i1 %11, label %for.body1, label %for.end1

for.body1:
  %12 = load i32*, i32** %d
  %13 = load i32, i32* %_i3
  %14 = getelementptr inbounds i32, i32* %12, i32 %13
  %15 = load i32, i32* @INF
  store i32 %15, i32* %14
  %16 = load i32, i32* %_i3
  %17 = add i32 %16, 1
  store i32 %17, i32* %_i3
  br label %for.begin1

for.end1:
  %18 = load i32*, i32** %d
  %19 = load i32, i32* %s.addr
  %20 = getelementptr inbounds i32, i32* %18, i32 %19
  store i32 0, i32* %20
  %q = alloca %struct.Queue_int*
  %21 = call i8* @__malloc(i32 12)
  %22 = bitcast i8* %21 to %struct.Queue_int*
  call void @__Queue_int_Queue_int(%struct.Queue_int* %22)
  store %struct.Queue_int* %22, %struct.Queue_int** %q
  %23 = load %struct.Queue_int*, %struct.Queue_int** %q
  %24 = load i32, i32* %s.addr
  call void @__Queue_int_push(i32 %24, %struct.Queue_int* %23)
  br label %while.begin2

while.begin2:
  %25 = load %struct.Queue_int*, %struct.Queue_int** %q
  %26 = call i32 @__Queue_int_size(%struct.Queue_int* %25)
  %27 = icmp ne i32 %26, 0
  %28 = zext i1 %27 to i8
  %29 = trunc i8 %28 to i1
  br i1 %29, label %while.body2, label %while.end2

while.body2:
  %_u1 = alloca i32
  %30 = load %struct.Queue_int*, %struct.Queue_int** %q
  %31 = call i32 @__Queue_int_pop(%struct.Queue_int* %30)
  store i32 %31, i32* %_u1
  %k = alloca i32
  %32 = load %struct.EdgeList*, %struct.EdgeList** @g
  %33 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %32, i32 0, i32 2
  %34 = load i32*, i32** %33
  %35 = load i32, i32* %_u1
  %36 = getelementptr inbounds i32, i32* %34, i32 %35
  %37 = load i32, i32* %36
  store i32 %37, i32* %k
  br label %for.begin3

for.begin3:
  %38 = load i32, i32* %k
  %39 = sub nsw i32 0, 1
  %40 = icmp ne i32 %38, %39
  %41 = zext i1 %40 to i8
  %42 = trunc i8 %41 to i1
  br i1 %42, label %for.body3, label %for.end3

for.body3:
  %_e1 = alloca %struct.Edge*
  %43 = load %struct.EdgeList*, %struct.EdgeList** @g
  %44 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %43, i32 0, i32 0
  %45 = load %struct.Edge**, %struct.Edge*** %44
  %46 = load i32, i32* %k
  %47 = getelementptr inbounds %struct.Edge*, %struct.Edge** %45, i32 %46
  %48 = load %struct.Edge*, %struct.Edge** %47
  store %struct.Edge* %48, %struct.Edge** %_e1
  %_v1 = alloca i32
  %49 = load %struct.Edge*, %struct.Edge** %_e1
  %50 = getelementptr inbounds %struct.Edge, %struct.Edge* %49, i32 0, i32 1
  %51 = load i32, i32* %50
  store i32 %51, i32* %_v1
  %52 = load i32*, i32** %d
  %53 = load i32, i32* %_v1
  %54 = getelementptr inbounds i32, i32* %52, i32 %53
  %55 = load i32, i32* %54
  %56 = load i32*, i32** %d
  %57 = load i32, i32* %_u1
  %58 = getelementptr inbounds i32, i32* %56, i32 %57
  %59 = load i32, i32* %58
  %60 = load %struct.Edge*, %struct.Edge** %_e1
  %61 = getelementptr inbounds %struct.Edge, %struct.Edge* %60, i32 0, i32 2
  %62 = load i32, i32* %61
  %63 = add i32 %59, %62
  %64 = icmp sgt i32 %55, %63
  %65 = zext i1 %64 to i8
  %66 = trunc i8 %65 to i1
  br i1 %66, label %if.then4, label %if.end4

if.then4:
  %67 = load i32*, i32** %d
  %68 = load i32, i32* %_v1
  %69 = getelementptr inbounds i32, i32* %67, i32 %68
  %70 = load i32*, i32** %d
  %71 = load i32, i32* %_u1
  %72 = getelementptr inbounds i32, i32* %70, i32 %71
  %73 = load i32, i32* %72
  %74 = load %struct.Edge*, %struct.Edge** %_e1
  %75 = getelementptr inbounds %struct.Edge, %struct.Edge* %74, i32 0, i32 2
  %76 = load i32, i32* %75
  %77 = add i32 %73, %76
  store i32 %77, i32* %69
  %78 = load %struct.Queue_int*, %struct.Queue_int** %q
  %79 = load i32, i32* %_v1
  call void @__Queue_int_push(i32 %79, %struct.Queue_int* %78)
  br label %if.end4

if.end4:
  %80 = load %struct.EdgeList*, %struct.EdgeList** @g
  %81 = getelementptr inbounds %struct.EdgeList, %struct.EdgeList* %80, i32 0, i32 1
  %82 = load i32*, i32** %81
  %83 = load i32, i32* %k
  %84 = getelementptr inbounds i32, i32* %82, i32 %83
  %85 = load i32, i32* %84
  store i32 %85, i32* %k
  br label %for.begin3

for.end3:
  br label %while.begin2

while.end2:
  %86 = load i32*, i32** %d
  store i32* %86, i32** %retval
  br label %return

return:
  %87 = load i32*, i32** %retval
  ret i32* %87
}

define i32 @main () {
entry:
  call void @__mx_initialize_globalVar()
  %retval = alloca i32
  %.phi = alloca i8
  call void @init()
  %_i4 = alloca i32
  %j = alloca i32
  store i32 0, i32* %_i4
  br label %for.begin1

for.begin1:
  %0 = load i32, i32* %_i4
  %1 = load i32, i32* @n
  %2 = icmp slt i32 %0, %1
  %3 = zext i1 %2 to i8
  %4 = trunc i8 %3 to i1
  br i1 %4, label %for.body1, label %for.end1

for.body1:
  %_d1 = alloca i32*
  %5 = load i32, i32* %_i4
  %6 = call i32* @spfa(i32 %5)
  store i32* %6, i32** %_d1
  store i32 0, i32* %j
  br label %for.begin2

for.begin2:
  %7 = load i32, i32* %j
  %8 = load i32, i32* @n
  %9 = icmp slt i32 %7, %8
  %10 = zext i1 %9 to i8
  %11 = trunc i8 %10 to i1
  br i1 %11, label %for.body2, label %for.end2

for.body2:
  %12 = load i32*, i32** %_d1
  %13 = load i32, i32* %j
  %14 = getelementptr inbounds i32, i32* %12, i32 %13
  %15 = load i32, i32* %14
  %16 = load i32, i32* @INF
  %17 = icmp eq i32 %15, %16
  %18 = zext i1 %17 to i8
  %19 = trunc i8 %18 to i1
  br i1 %19, label %if.then3, label %if.else3

if.then3:
  call void @print(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i32 0, i32 0))
  br label %if.end3

if.else3:
  %20 = load i32*, i32** %_d1
  %21 = load i32, i32* %j
  %22 = getelementptr inbounds i32, i32* %20, i32 %21
  %23 = load i32, i32* %22
  %24 = call i8* @toString(i32 %23)
  call void @print(i8* %24)
  br label %if.end3

if.end3:
  call void @print(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.1, i32 0, i32 0))
  %25 = load i32, i32* %j
  %26 = add i32 %25, 1
  store i32 %26, i32* %j
  br label %for.begin2

for.end2:
  call void @println(i8* getelementptr inbounds ([1 x i8], [1 x i8]* @.str.2, i32 0, i32 0))
  %27 = load i32, i32* %_i4
  %28 = add i32 %27, 1
  store i32 %28, i32* %_i4
  br label %for.begin1

for.end1:
  store i32 0, i32* %retval
  br label %return

return:
  %29 = load i32, i32* %retval
  ret i32 %29
}

