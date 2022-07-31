参考链接 ：[Java8.0下载安装以及配置环境](https://blog.csdn.net/li_junli/article/details/88360061)

- [下载链接](https://www.oracle.com/java/technologies/javase-downloads.html)
- JAVA8就是 Java SE 8
- 需要登录orcal的账号，我用163邮箱注册的，密码常用的那几个试一试，不行，就重置密码
- 下载好后，windows下直接双击，修改目录的时候，自己新建个JAVA8的目录，这样他就会
  把所有的文件安装到这个里面，也可以直接复制他默认的目录名字，记住一定要新建目录
  
  它还让我选一个jre的安装目录，也新建一个放进去就好了
- 设置环境变量：
  - JAVA_HOME把java的根目录放进去
  - Path里面 %JAVA_HOME%\bin
  - 重新打开一个cmd 输入java试一下

### 一、JDK 11（Java 11）之后 JRE 说明

在 JDK 11（Java 11）之后 JRE 是集成在 JDK 之中的，无需我们额外进行环境变量配置，仅需配置 JDK 的 JAVA_HOME 与 Path 变量即可。

JAVA_HOME 变量指向 JDK 根目录，即 bin 目录上一级。
PATH 变量指向 JDK 的 bin 目录。
如果切实需要 JRE 的同学或者是了解如何生成 JRE 目录的同学可以参考本文内容。

### 二、win7配置

上面是用户环境变量，下面是系统变量，注意：java11之后不需要配置JRE路径，之前需要配置。

![image-20220725145251279](E:\codes\JavaExamlpeProjects\main\JavaSE\Imag\image-20220725145251279.png)