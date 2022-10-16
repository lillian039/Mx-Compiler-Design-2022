**Visitor模式：**

通过在语法规则的每条分支后加上 `# Identifier` (注意不能和规则名冲突)这样类似`标签`的形式。使得对于每种输入我们都有不同的处理方法。

`MxVisitor`：分支处理函数的定义

`MxBaseVisitor`：分支处理函数的实现

我们可以重写`MxBaseVistor`，来自定义每个分支标签在输入情况匹配时的动作。

观察以下代码我们可以发现，Visitor生成的处理函数命名为 `visitor+标签名`。

**Listener模式：**

我们将以监听者模式抽取Java类中的方法，并整合成一个接口。

与Visitor模式不同的是，Listener无需`手动地`在各个分支后打上标签，当我们以监听者模式生成语法分析器时，语法分析器

每遍历一个规则都会有`两次响应事件`，分别是`enter`以及`exit`。

`MxListener.java `：前者是遍历整个语法分析树的全部响应事件定义。

`MxBaseListener.java`：后者是它的实现。

我们可以重写 `MxBaseListener`，选择性地编写那些响应事件。