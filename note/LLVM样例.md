#### 二元运算

c++ 代码：

```c++
int main() {
    int n = 3;
    int m = 2;
    int l = m + n + 5;
    return 0;
}
```

对应 LLVM 代码：

```
define dso_local i32 @main() #4 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 3, i32* %2, align 4
  store i32 2, i32* %3, align 4
  %5 = load i32, i32* %3, align 4
  %6 = load i32, i32* %2, align 4
  %7 = add nsw i32 %5, %6
  %8 = add nsw i32 %7, 5
  store i32 %8, i32* %4, align 4
  %9 = load i32, i32* %4, align 4
  ret i32 0
}
```

#### 指针运算

c++代码：

```c++
int main() {
    int n[4][3];
    int a[4];
    int *b = a;
    int p = b[3];
    p = a[3];
    return 0;
}
```

对应 LLVM 代码：

```
define dso_local i32 @main() #4 {
  %1 = alloca i32, align 4
  %2 = alloca [4 x [3 x i32]], align 16
  %3 = alloca [4 x i32], align 16
  %4 = alloca i32*, align 8
  %5 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %6 = getelementptr inbounds [4 x i32], [4 x i32]* %3, i64 0, i64 0
  store i32* %6, i32** %4, align 8
  %7 = load i32*, i32** %4, align 8
  %8 = getelementptr inbounds i32, i32* %7, i64 3
  %9 = load i32, i32* %8, align 4
  store i32 %9, i32* %5, align 4
  %10 = getelementptr inbounds [4 x i32], [4 x i32]* %3, i64 0, i64 3
  %11 = load i32, i32* %10, align 4
  store i32 %11, i32* %5, align 4
  ret i32 0
}
```

全局：静态空间

非全局：allocate 栈空间

#### struct

```c++
void f1(int a){
    a=1;
}
struct A{
    int a;
    bool b;
    int c;
    void f1(int a){
        a=1;
    }
}gA;
int main() {
    A iA;
    int a=iA.a;
    int c=gA.c;
    iA=gA;
    f1(1);
    gA.f1(1);
    return 0;
}
```

对应 LLVM 代码：

```
%struct.A = type { i32, i8, i32 }

define dso_local void @_Z2f1i(i32 %0) #4 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  store i32 1, i32* %2, align 4
  ret void
}

define dso_local i32 @main() #5 {
  %1 = alloca i32, align 4
  %2 = alloca %struct.A, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %5 = getelementptr inbounds %struct.A, %struct.A* %2, i32 0, i32 0
  %6 = load i32, i32* %5, align 4
  store i32 %6, i32* %3, align 4
  %7 = load i32, i32* getelementptr inbounds (%struct.A, %struct.A* @gA, i32 0, i32 2), align 4
  store i32 %7, i32* %4, align 4
  %8 = bitcast %struct.A* %2 to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* align 4 %8, i8* align 4 bitcast (%struct.A* @gA to i8*), i64 12, i1 false)
  call void @_Z2f1i(i32 1)
  call void @_ZN1A2f1Ei(%struct.A* @gA, i32 1)
  ret i32 0
}

define linkonce_odr dso_local void @_ZN1A2f1Ei(%struct.A* %0, i32 %1) #4 comdat align 2 {
  %3 = alloca %struct.A*, align 8
  %4 = alloca i32, align 4
  store %struct.A* %0, %struct.A** %3, align 8
  store i32 %1, i32* %4, align 4
  %5 = load %struct.A*, %struct.A** %3, align 8
  store i32 1, i32* %4, align 4
  ret void
}

```

#### If 语句

c++ 代码：

```c++
int main() {
    int a;
    int b;
    cin >> a >> b;
    if (a == b + 1 && a < b) {
        a = b;
    }
    int c=12;
    return 0;
}
```

对应 LLVM 代码：

```
define dso_local i32 @main() #4 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %5 = call dereferenceable(280) %"class.std::basic_istream"* @_ZNSirsERi(%"class.std::basic_istream"* @_ZSt3cin, i32* dereferenceable(4) %2)
  %6 = call dereferenceable(280) %"class.std::basic_istream"* @_ZNSirsERi(%"class.std::basic_istream"* %5, i32* dereferenceable(4) %3)
  %7 = load i32, i32* %2, align 4
  %8 = load i32, i32* %3, align 4
  %9 = add nsw i32 %8, 1
  %10 = icmp eq i32 %7, %9
  br i1 %10, label %11, label %17

11:                                               ; preds = %0
  %12 = load i32, i32* %2, align 4
  %13 = load i32, i32* %3, align 4
  %14 = icmp slt i32 %12, %13
  br i1 %14, label %15, label %17

15:                                               ; preds = %11
  %16 = load i32, i32* %3, align 4
  store i32 %16, i32* %2, align 4
  br label %17

17:                                               ; preds = %15, %11, %0
  store i32 12, i32* %4, align 4
  ret i32 0
}
```

#### For 语言

c++ 代码

```c++
int main() {
    int a;
    int b;
    cin >> a >> b;
    if (a == b + 1 && a < b) {
        int n=4;
        for(int i=1;i<=n;i++){
            a+=i;
        }
    }
    return 0;
}
```

LLVM 格式：

```
define dso_local i32 @main() #4 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %6 = call dereferenceable(280) %"class.std::basic_istream"* @_ZNSirsERi(%"class.std::basic_istream"* @_ZSt3cin, i32* dereferenceable(4) %2)
  %7 = call dereferenceable(280) %"class.std::basic_istream"* @_ZNSirsERi(%"class.std::basic_istream"* %6, i32* dereferenceable(4) %3)
  %8 = load i32, i32* %2, align 4
  %9 = load i32, i32* %3, align 4
  %10 = add nsw i32 %9, 1
  %11 = icmp eq i32 %8, %10
  br i1 %11, label %12, label %29

12:                                               ; preds = %0
  %13 = load i32, i32* %2, align 4
  %14 = load i32, i32* %3, align 4
  %15 = icmp slt i32 %13, %14
  br i1 %15, label %16, label %29

16:                                               ; preds = %12
  store i32 4, i32* %4, align 4
  store i32 1, i32* %5, align 4
  br label %17

17:                                               ; preds = %25, %16
  %18 = load i32, i32* %5, align 4
  %19 = load i32, i32* %4, align 4
  %20 = icmp sle i32 %18, %19
  br i1 %20, label %21, label %28

21:                                               ; preds = %17
  %22 = load i32, i32* %5, align 4
  %23 = load i32, i32* %2, align 4
  %24 = add nsw i32 %23, %22
  store i32 %24, i32* %2, align 4
  br label %25

25:                                               ; preds = %21
  %26 = load i32, i32* %5, align 4
  %27 = add nsw i32 %26, 1
  store i32 %27, i32* %5, align 4
  br label %17

28:                                               ; preds = %17
  br label %29

29:                                               ; preds = %28, %12, %0
  ret i32 0
}
```

#### 函数

```c++
int func(int a,int b){
    if(a>b)return a;
    else return b;
}
int main() {
    int n=func(1,2);
    int m=func(3,4);
    return 0;
}
```

对应 LLVM 代码：

```
define dso_local i32 @_Z4funcii(i32 %0, i32 %1) #4 {
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  store i32 %0, i32* %4, align 4
  store i32 %1, i32* %5, align 4
  %6 = load i32, i32* %4, align 4
  %7 = load i32, i32* %5, align 4
  %8 = icmp sgt i32 %6, %7
  br i1 %8, label %9, label %11

9:                                                ; preds = %2
  %10 = load i32, i32* %4, align 4
  store i32 %10, i32* %3, align 4
  br label %13

11:                                               ; preds = %2
  %12 = load i32, i32* %5, align 4
  store i32 %12, i32* %3, align 4
  br label %13

13:                                               ; preds = %11, %9
  %14 = load i32, i32* %3, align 4
  ret i32 %14
}
```

```
define dso_local i32 @main() #5 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %4 = call i32 @_Z4funcii(i32 1, i32 2)
  store i32 %4, i32* %2, align 4
  %5 = call i32 @_Z4funcii(i32 3, i32 4)
  store i32 %5, i32* %3, align 4
  ret i32 0
}
```