; ModuleID = 'builtin.c'
source_filename = "builtin.c"
target datalayout = "e-m:e-p:32:32-p270:32:32-p271:32:32-p272:64:64-f64:32:64-f80:32-n8:16:32-S128"
target triple = "i386-pc-linux-gnu"

@BUFFER_LENGTH = dso_local constant i32 256, align 4
@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.1 = private unnamed_addr constant [4 x i8] c"%s\0A\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

; Function Attrs: noinline nounwind optnone
define dso_local void @print(i8* %str) #0 {
entry:
  %str.addr = alloca i8*, align 4
  store i8* %str, i8** %str.addr, align 4
  %0 = load i8*, i8** %str.addr, align 4
  %call = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i32 0, i32 0), i8* %0)
  ret void
}

declare dso_local i32 @printf(i8*, ...) #1

; Function Attrs: noinline nounwind optnone
define dso_local void @println(i8* %str) #0 {
entry:
  %str.addr = alloca i8*, align 4
  store i8* %str, i8** %str.addr, align 4
  %0 = load i8*, i8** %str.addr, align 4
  %call = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i32 0, i32 0), i8* %0)
  ret void
}

; Function Attrs: noinline nounwind optnone
define dso_local void @printInt(i32 %n) #0 {
entry:
  %n.addr = alloca i32, align 4
  store i32 %n, i32* %n.addr, align 4
  %0 = load i32, i32* %n.addr, align 4
  %call = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32 %0)
  ret void
}

; Function Attrs: noinline nounwind optnone
define dso_local void @printlnInt(i32 %n) #0 {
entry:
  %n.addr = alloca i32, align 4
  store i32 %n, i32* %n.addr, align 4
  %0 = load i32, i32* %n.addr, align 4
  %call = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i32 0, i32 0), i32 %0)
  ret void
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @getInt() #0 {
entry:
  %n = alloca i32, align 4
  %call = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32* %n)
  %0 = load i32, i32* %n, align 4
  ret i32 %0
}

declare dso_local i32 @__isoc99_scanf(i8*, ...) #1

; Function Attrs: noinline nounwind optnone
define dso_local i8* @getString() #0 {
entry:
  %str = alloca i8*, align 4
  %call = call noalias i8* @malloc(i32 256) #5
  store i8* %call, i8** %str, align 4
  %0 = load i8*, i8** %str, align 4
  %call1 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i32 0, i32 0), i8* %0)
  %1 = load i8*, i8** %str, align 4
  ret i8* %1
}

; Function Attrs: nounwind
declare dso_local noalias i8* @malloc(i32) #2

; Function Attrs: noinline nounwind optnone
define dso_local i8* @toString(i32 %i) #0 {
entry:
  %i.addr = alloca i32, align 4
  %str = alloca i8*, align 4
  store i32 %i, i32* %i.addr, align 4
  %call = call noalias i8* @malloc(i32 256) #5
  store i8* %call, i8** %str, align 4
  %0 = load i8*, i8** %str, align 4
  %1 = load i32, i32* %i.addr, align 4
  %call1 = call i32 (i8*, i8*, ...) @sprintf(i8* %0, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32 %1) #5
  %2 = load i8*, i8** %str, align 4
  ret i8* %2
}

; Function Attrs: nounwind
declare dso_local i32 @sprintf(i8*, i8*, ...) #2

; Function Attrs: noinline nounwind optnone
define dso_local signext i8 @___str_eq(i8* %s1, i8* %s2) #0 {
entry:
  %s1.addr = alloca i8*, align 4
  %s2.addr = alloca i8*, align 4
  store i8* %s1, i8** %s1.addr, align 4
  store i8* %s2, i8** %s2.addr, align 4
  %0 = load i8*, i8** %s1.addr, align 4
  %1 = load i8*, i8** %s2.addr, align 4
  %call = call i32 @strcmp(i8* %0, i8* %1) #6
  %cmp = icmp eq i32 %call, 0
  %conv = zext i1 %cmp to i32
  %conv1 = trunc i32 %conv to i8
  ret i8 %conv1
}

; Function Attrs: nounwind readonly
declare dso_local i32 @strcmp(i8*, i8*) #3

; Function Attrs: noinline nounwind optnone
define dso_local signext i8 @___str_ne(i8* %s1, i8* %s2) #0 {
entry:
  %s1.addr = alloca i8*, align 4
  %s2.addr = alloca i8*, align 4
  store i8* %s1, i8** %s1.addr, align 4
  store i8* %s2, i8** %s2.addr, align 4
  %0 = load i8*, i8** %s1.addr, align 4
  %1 = load i8*, i8** %s2.addr, align 4
  %call = call i32 @strcmp(i8* %0, i8* %1) #6
  %cmp = icmp ne i32 %call, 0
  %conv = zext i1 %cmp to i32
  %conv1 = trunc i32 %conv to i8
  ret i8 %conv1
}

; Function Attrs: noinline nounwind optnone
define dso_local signext i8 @___str_slt(i8* %s1, i8* %s2) #0 {
entry:
  %s1.addr = alloca i8*, align 4
  %s2.addr = alloca i8*, align 4
  store i8* %s1, i8** %s1.addr, align 4
  store i8* %s2, i8** %s2.addr, align 4
  %0 = load i8*, i8** %s1.addr, align 4
  %1 = load i8*, i8** %s2.addr, align 4
  %call = call i32 @strcmp(i8* %0, i8* %1) #6
  %cmp = icmp slt i32 %call, 0
  %conv = zext i1 %cmp to i32
  %conv1 = trunc i32 %conv to i8
  ret i8 %conv1
}

; Function Attrs: noinline nounwind optnone
define dso_local signext i8 @___str_sle(i8* %s1, i8* %s2) #0 {
entry:
  %s1.addr = alloca i8*, align 4
  %s2.addr = alloca i8*, align 4
  store i8* %s1, i8** %s1.addr, align 4
  store i8* %s2, i8** %s2.addr, align 4
  %0 = load i8*, i8** %s1.addr, align 4
  %1 = load i8*, i8** %s2.addr, align 4
  %call = call i32 @strcmp(i8* %0, i8* %1) #6
  %cmp = icmp sle i32 %call, 0
  %conv = zext i1 %cmp to i32
  %conv1 = trunc i32 %conv to i8
  ret i8 %conv1
}

; Function Attrs: noinline nounwind optnone
define dso_local signext i8 @___str_sgt(i8* %s1, i8* %s2) #0 {
entry:
  %s1.addr = alloca i8*, align 4
  %s2.addr = alloca i8*, align 4
  store i8* %s1, i8** %s1.addr, align 4
  store i8* %s2, i8** %s2.addr, align 4
  %0 = load i8*, i8** %s1.addr, align 4
  %1 = load i8*, i8** %s2.addr, align 4
  %call = call i32 @strcmp(i8* %0, i8* %1) #6
  %cmp = icmp sgt i32 %call, 0
  %conv = zext i1 %cmp to i32
  %conv1 = trunc i32 %conv to i8
  ret i8 %conv1
}

; Function Attrs: noinline nounwind optnone
define dso_local signext i8 @___str_sge(i8* %s1, i8* %s2) #0 {
entry:
  %s1.addr = alloca i8*, align 4
  %s2.addr = alloca i8*, align 4
  store i8* %s1, i8** %s1.addr, align 4
  store i8* %s2, i8** %s2.addr, align 4
  %0 = load i8*, i8** %s1.addr, align 4
  %1 = load i8*, i8** %s2.addr, align 4
  %call = call i32 @strcmp(i8* %0, i8* %1) #6
  %cmp = icmp sge i32 %call, 0
  %conv = zext i1 %cmp to i32
  %conv1 = trunc i32 %conv to i8
  ret i8 %conv1
}

; Function Attrs: noinline nounwind optnone
define dso_local i8* @___str_add(i8* %s1, i8* %s2) #0 {
entry:
  %s1.addr = alloca i8*, align 4
  %s2.addr = alloca i8*, align 4
  %str = alloca i8*, align 4
  store i8* %s1, i8** %s1.addr, align 4
  store i8* %s2, i8** %s2.addr, align 4
  %0 = load i8*, i8** %s1.addr, align 4
  %call = call i32 @strlen(i8* %0) #6
  %1 = load i8*, i8** %s2.addr, align 4
  %call1 = call i32 @strlen(i8* %1) #6
  %add = add i32 %call, %call1
  %add2 = add i32 %add, 1
  %mul = mul i32 1, %add2
  %call3 = call noalias i8* @malloc(i32 %mul) #5
  store i8* %call3, i8** %str, align 4
  %2 = load i8*, i8** %str, align 4
  %3 = load i8*, i8** %s1.addr, align 4
  %call4 = call i8* @strcpy(i8* %2, i8* %3) #5
  %4 = load i8*, i8** %str, align 4
  %5 = load i8*, i8** %s2.addr, align 4
  %call5 = call i8* @strcat(i8* %4, i8* %5) #5
  %6 = load i8*, i8** %str, align 4
  ret i8* %6
}

; Function Attrs: nounwind readonly
declare dso_local i32 @strlen(i8*) #3

; Function Attrs: nounwind
declare dso_local i8* @strcpy(i8*, i8*) #2

; Function Attrs: nounwind
declare dso_local i8* @strcat(i8*, i8*) #2

; Function Attrs: noinline nounwind optnone
define dso_local i8* @__string_substring(i32 %left, i32 %right, i8* %str) #0 {
entry:
  %left.addr = alloca i32, align 4
  %right.addr = alloca i32, align 4
  %str.addr = alloca i8*, align 4
  %len = alloca i32, align 4
  %substr = alloca i8*, align 4
  store i32 %left, i32* %left.addr, align 4
  store i32 %right, i32* %right.addr, align 4
  store i8* %str, i8** %str.addr, align 4
  %0 = load i32, i32* %right.addr, align 4
  %1 = load i32, i32* %left.addr, align 4
  %sub = sub nsw i32 %0, %1
  %add = add nsw i32 %sub, 1
  store i32 %add, i32* %len, align 4
  %2 = load i32, i32* %len, align 4
  %mul = mul i32 1, %2
  %call = call noalias i8* @malloc(i32 %mul) #5
  store i8* %call, i8** %substr, align 4
  %3 = load i8*, i8** %substr, align 4
  %4 = load i8*, i8** %str.addr, align 4
  %5 = load i32, i32* %left.addr, align 4
  %add.ptr = getelementptr inbounds i8, i8* %4, i32 %5
  %6 = load i32, i32* %len, align 4
  call void @llvm.memcpy.p0i8.p0i8.i32(i8* align 1 %3, i8* align 1 %add.ptr, i32 %6, i1 false)
  %7 = load i8*, i8** %substr, align 4
  %8 = load i32, i32* %len, align 4
  %sub1 = sub nsw i32 %8, 1
  %arrayidx = getelementptr inbounds i8, i8* %7, i32 %sub1
  store i8 0, i8* %arrayidx, align 1
  %9 = load i8*, i8** %substr, align 4
  ret i8* %9
}

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.memcpy.p0i8.p0i8.i32(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i32, i1 immarg) #4

; Function Attrs: noinline nounwind optnone
define dso_local i32 @__string_length(i8* %str) #0 {
entry:
  %str.addr = alloca i8*, align 4
  store i8* %str, i8** %str.addr, align 4
  %0 = load i8*, i8** %str.addr, align 4
  %call = call i32 @strlen(i8* %0) #6
  ret i32 %call
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @__string_parseInt(i8* %str) #0 {
entry:
  %str.addr = alloca i8*, align 4
  %val = alloca i32, align 4
  store i8* %str, i8** %str.addr, align 4
  %0 = load i8*, i8** %str.addr, align 4
  %call = call i32 (i8*, i8*, ...) @__isoc99_sscanf(i8* %0, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32* %val) #5
  %1 = load i32, i32* %val, align 4
  ret i32 %1
}

; Function Attrs: nounwind
declare dso_local i32 @__isoc99_sscanf(i8*, i8*, ...) #2

; Function Attrs: noinline nounwind optnone
define dso_local i32 @__string_ord(i32 %num, i8* %str) #0 {
entry:
  %num.addr = alloca i32, align 4
  %str.addr = alloca i8*, align 4
  store i32 %num, i32* %num.addr, align 4
  store i8* %str, i8** %str.addr, align 4
  %0 = load i8*, i8** %str.addr, align 4
  %1 = load i32, i32* %num.addr, align 4
  %arrayidx = getelementptr inbounds i8, i8* %0, i32 %1
  %2 = load i8, i8* %arrayidx, align 1
  %conv = sext i8 %2 to i32
  ret i32 %conv
}

; Function Attrs: noinline nounwind optnone
define dso_local i8* @__malloc(i32 %size) #0 {
entry:
  %size.addr = alloca i32, align 4
  store i32 %size, i32* %size.addr, align 4
  %0 = load i32, i32* %size.addr, align 4
  %call = call noalias i8* @malloc(i32 %0) #5
  ret i8* %call
}

attributes #0 = { noinline nounwind optnone "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="i686" "target-features"="+cx8,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="i686" "target-features"="+cx8,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="i686" "target-features"="+cx8,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="i686" "target-features"="+cx8,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #4 = { argmemonly nounwind willreturn }
attributes #5 = { nounwind }
attributes #6 = { nounwind readonly }

!llvm.module.flags = !{!0, !1}
!llvm.ident = !{!2}

!0 = !{i32 1, !"NumRegisterParameters", i32 0}
!1 = !{i32 1, !"wchar_size", i32 4}
!2 = !{!"clang version 10.0.0-4ubuntu1 "}
