target datalayout = "e-m:e-p:32:32-p270:32:32-p271:32:32-p272:64:64-f64:32:64-f80:32-n8:16:32-S128"
target triple = "i386-pc-linux-gnu"

@a = global i32* null


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
  %0 = mul i32 4, 4
  %1 = add i32 %0, 4
  %2 = call i8* @__malloc(i32 %1)
  %3 = bitcast i8* %2 to i32*
  store i32 4, i32* %3
  %4 = bitcast i8* %2 to i32*
  %5 = getelementptr inbounds i32, i32* %4, i32 1
  store i32* %5, i32** @a
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
  %b = alloca i32**
  %i = alloca i32
  call void @__mx_initialize_globalVar()
  %0 = mul i32 4, 4
  %1 = add i32 %0, 4
  %2 = call i8* @__malloc(i32 %1)
  %3 = bitcast i8* %2 to i32*
  store i32 4, i32* %3
  %4 = bitcast i8* %2 to i32**
  %5 = getelementptr inbounds i32*, i32** %4, i32 1
  store i32** %5, i32*** %b
  %6 = load i32**, i32*** %b
  %7 = getelementptr inbounds i32*, i32** %6, i32 0
  %8 = load i32*, i32** @a
  store i32* %8, i32** %7
  %9 = load i32**, i32*** %b
  %10 = getelementptr inbounds i32*, i32** %9, i32 1
  %11 = load i32*, i32** @a
  store i32* %11, i32** %10
  %12 = load i32**, i32*** %b
  %13 = getelementptr inbounds i32*, i32** %12, i32 2
  %14 = load i32*, i32** @a
  store i32* %14, i32** %13
  %15 = load i32**, i32*** %b
  %16 = getelementptr inbounds i32*, i32** %15, i32 3
  %17 = load i32*, i32** @a
  store i32* %17, i32** %16
  %18 = load i32**, i32*** %b
  %19 = bitcast i32** %18 to i32*
  %20 = getelementptr inbounds i32, i32* %19, i32 -1
  %21 = load i32, i32* %20
  %22 = call i8* @toString(i32 %21)
  call void @println(i8* %22)
  store i32 0, i32* %retval
  br label %return

return:
  %23 = load i32, i32* %retval
  ret i32 %23
}

