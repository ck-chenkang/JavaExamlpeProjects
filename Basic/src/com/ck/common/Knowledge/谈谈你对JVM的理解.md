# 谈谈你对JVM的理解

参考链接： [谈谈你对JVM的理解](https://blog.csdn.net/github_37130188/article/details/96509028)

## 什么是JVM

JVM是Java Virtual Machine（Java虚拟机）的缩写，JVM是一种用于计算设备的规范，它是一个虚构出来的计算机，是通过在实际的计算机上仿真模拟各种计算机功能来实现的。

Java虚拟机包括一套字节码指令集、一组寄存器、一个栈、一个垃圾回收堆和一个存储方法域。 JVM屏蔽了与具体操作系统平台相关的信息，
使Java程序只需生成在Java虚拟机上运行的目标代码（字节码）,就可以在多种平台上不加修改地运行。JVM在执行字节码时，实际上最终还是把字节码解释成具体平台上的机器指令执行。

JVM是JRE的一部分。它是一个虚构出来的计算机，是通过在实际的计算机上仿真模拟各种计算机功能来实现的。
JVM有自己完善的硬件架构，如处理器、堆栈、寄存器等，还具有相应的指令系统。Java语言最重要的特点就是跨平台运行。使用JVM就是为了支持与操作系统无关，实现跨平台。

![](https://raw.githubusercontent.com/ck-chenkang/JavaExamlpeProjects/master/Basic/Imag/20190719225210879.png)

## JVM的作用

与平台无关性：JVM屏蔽了与具体操作系统平台相关的信息，使得Java程序只需生成在Java虚拟机上运行的目标代码（字节码），就可以在多种平台上不加修改地运行。

Java中的所有类，必须被装载到jvm中才能运行，这个装载工作是由jvm中的类装载器完成的，类装载器所做的工作实质是把类文件从硬盘读取到内存中JVM对中央处理器（CPU）所执行的一种软件操作，用于执行编译过的Java程序码（Applet与应用程序）。

JVM就是我们常说的java虚拟机，它是整个java实现跨平台的最核心的部分，所有的java程序会首先被编译为.class的类文件，这种类文件可以在虚拟机上执行。也就是说class并不直接与机器的操作系统相对应，而是经过虚拟机间接与操作系统交互，由虚拟机将程序解释给本地系统执行。当然只有JVM还不能成class的执行，因为在解释class的时候JVM需要调用解释所需要的类库lib，而jre包含lib类库。
    
## JVM结构示意图


## JVM重要特征

### 内存管理机制

Java虚拟机内存模型包括程序计数器、虚拟机栈、本地方法栈、方法区、堆，如图所示

![](https://raw.githubusercontent.com/ck-chenkang/JavaExamlpeProjects/master/Basic/Imag/20190719225256448.png)

