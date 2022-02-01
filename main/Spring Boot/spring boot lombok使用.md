# spring boot lombok的使用

参考链接：

[官方参考](https://projectlombok.org/features/all)

[spring boot lombok的使用](https://www.cnblogs.com/zhangyinhua/p/8623018.html)

[你不得不会的Lombok全面详细讲解，全网最详细的教程](https://baijiahao.baidu.com/s?id=1663718812859963066&wfr=spider&for=pc)

### lombok概述

- lombok简介

  Lombok想要解决了的是在我们实体Bean中大量的Getter/Setter方法，以及toString, hashCode等可能不会用到，但是某些时候仍然需要复写，以期方便使用的方法；在使用Lombok之后，将由其来自动帮你实现代码生成，注意，其是 **在运行过程中，帮你自动生成的** 。就是说，将极大减少你的代码总量。

- lombok作用

  消除模板代码

  getter、setter、构造器、toString()、equals()

  便捷的生成比较复杂的代码，例如一个POJO要转化成构建器模式的形式，只需要一个注解。

### SpringBoot中使用lombok

1. 添加依赖

```
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <version>1.16.10</version>
</dependency>
```

1. 编写一个实体类 User，使用@Data注解

```
@Data
public class User {  
 private String name;  
 private int age;
}
```

1. 编写测试方法，测试@Data的作用

```
@Test
public void testUser(){  
  User user = new User();  
  user.setName("AnyCode");  
  user.setAge(20);  
  System.err.println(user.toString());
}
```

![输入图片说明](Imag/527.png)

### lombok的注解介绍

@NonNull : 让你不在担忧并且爱上NullPointerException

@CleanUp : 自动资源管理：不用再在finally中添加资源的close方法

@Setter/@Getter : 自动生成set和get方法

@ToString : 自动生成toString方法

@EqualsAndHashcode : 从对象的字段中生成hashCode和equals的实现

@NoArgsConstructor/@RequiredArgsConstructor/@AllArgsConstructor
自动生成构造方法

@Data : 自动生成set/get方法，toString方法，equals方法，hashCode方法，不带参数的构造方法

@Value : 用于注解final类

@Builder : 产生复杂的构建器api类

@SneakyThrows : 异常处理（谨慎使用）

@Synchronized : 同步方法安全的转化

@Getter(lazy=true) :

@Log : 支持各种logger对象，使用时用对应的注解，如：@Log4j

### 推荐使用技巧

- 在 Bean / Entity 类上使用 @Data 注解。
- 需要使用 Log 对象的地方使用 @Log4j（依项目日志框架决定）。
- 注意：lombok 的注解不能被继承。

### IDEA中使用lombok

如果想让lombok生效我们还需要针对idea工具进行插件的安装，下面我们按照顺序打开Idea配置

File > Settings > Plugins > Browse repositories... > 输入lombok，插件就会被自动检索出来,如下图所示：
![输入图片说明](Imag/1240.png)

我的工具已经安装了该插件，所有在右侧是没有任何按钮的，如果你的工具没有安装该插件,

右侧会有一个绿色的按钮，按钮的内容则是Install，点击安装后重启Idea就可以了。

**同时设置** Preferences -> Compiler -> Annotation Processors -> Enable annotation processing勾选。

### lombok注解详解

#### **1. @Getter/@Setter**

为字段生成Getter和Setter方法，可以注解到字段或者类上(注解在类上会为类中的所有字段生成Getter和Setter方法)，默认是public类型的，如果需要的话可以修改方法的访问级别。

![img](Imag/cc11728b4710b912ed15fed8243883059345220f.jpeg)

结果解释：

id字段生成了Getter&Setter,访问修饰符是public

phone只生成了Getter方法，因为只使用了@Getter而没有使用@Setter, 并且访问修饰符是protected

password 上并没有注解，所以什么都不生成

注意：Lombok中的注解一般都会包含一个无参构造函数注解@NoArgsConstructor(用于生成无参构造函数的) ，所以还会额外生成一个无参构造函数

@Getter @Setter 注解在类上，表示为类中的所有字段生成Getter&Setter方法。

![img](Imag/58ee3d6d55fbb2fb27a9076da68f5fa24723dc1f.jpeg)

#### **2. @NonNull**

为字段赋值时(即调用字段的setter方法时)，如果传的参数为null，则会抛出空异常NullPointerException，生成setter方法时会对参数是否为空检查

![img](Imag/bd315c6034a85edfffff0cb5ac917625dc547505.jpeg)

官方的例子，注意里面**super()的位置**：

源码：

```java
import lombok.NonNull;

public class NonNullExample extends Something {
  private String name;
  
  public NonNullExample(@NonNull Person person) {
    super("Hello");
    this.name = person.getName();
  }
}
```

反编译：

```java
import lombok.NonNull;

public class NonNullExample extends Something {
  private String name;
  
  public NonNullExample(@NonNull Person person) {
    super("Hello");
    if (person == null) {
      throw new NullPointerException("person is marked @NonNull but is null");
    }
    this.name = person.getName();
  }
}
```

#### 3. @NoArgsConstructor

生成一个无参构造方法。当类中有final字段没有被初始化时，编译器会报错，此时可用@NoArgsConstructor(force = true)，然后就会为没有初始化的final字段设置默认值 0 / false / null, 这样编译器就不会报错。对于具有约束的字段（例如@NonNull字段），不会生成检查或分配，因此请注意，正确初始化这些字段之前，这些约束无效。

![img](Imag/7a899e510fb30f240f93ed902f50ae45ac4b0367.jpeg)

#### 4. @RequiredArgsConstructor

生成构造方法（可能带参数也可能不带参数），如果带参数，这参数只能是以final修饰的未经初始化的字段，或者是以@NonNull注解的未经初始化的字段。

@RequiredArgsConstructor(staticName = “of”)会生成一个of()的静态方法，并把构造方法设置为私有的

![img](Imag/8326cffc1e178a82a441caef10c60c8ba877e848.jpeg)

必要的构造函数只会生成final修饰的未经初始化的字段或者是以@NonNull注解的未经初始化的字段， 所以生成了public User(@NonNull String phone, Integer age)构造函数

![img](Imag/4b90f603738da977da6e0c8a5694871f8718e31f.jpeg)

#### **5. @AllArgsConstructor**

生成一个全参数的构造方法

![img](Imag/3b87e950352ac65c115cabb81337cd1791138ae8.jpeg)

#### 6. @ToString

生成toString()方法，默认情况下它会按顺序（以逗号分隔）打印你的类名称以及每个字段。可以这样设置不包含哪些字段,可以指定一个也可以指定多个@ToString(exclude = “id”) / @ToString(exclude = {“id”,“name”})

如果继承的有父类的话，可以设置callSuper 让其调用父类的toString()方法，例如：@ToString(callSuper = true)

![img](Imag/b3119313b07eca80985f0a4d74e6e8dba044830a.jpeg)

![img](Imag/a686c9177f3e6709378f2a92dd02e03bf9dc5518.jpeg)

#### 7. @EqualsAndHashCode

生成hashCode()和equals()方法，默认情况下，它将使用所有非静态，非transient字段。但可以通过在可选的exclude参数中来排除更多字段。或者，通过在of参数中命名它们来准确指定希望使用哪些字段。

// exclude 排除字段

@EqualsAndHashCode(exclude = {“password”, “salt”})

// of 指定要包含的字段

@EqualsAndHashCode(of = {“id”, “phone”, “password”})

![img](Imag/dbb44aed2e738bd414bc8345464ef8d0267ff975.jpeg)

![img](Imag/7c1ed21b0ef41bd5245c0065a11ffecd38db3d64.jpeg)

#### **8. @Data**

@Data 包含了 @ToString、@EqualsAndHashCode、@Getter / @Setter和@RequiredArgsConstructor的功能

![img](Imag/4610b912c8fcc3ce9a97f10d7780a98ed53f2080.jpeg)

#### **9. @Value**

@Value 将字段都变成不可变类型:使用final修饰， 同时还包含@ToString、@EqualsAndHashCode、@AllArgsConstructor 、@Getter(注意只有Getter没有Setter)

![img](Imag/b21bb051f81986183d184694ac2851758ad4e68b.jpeg)

![img](Imag/8718367adab44aed5e7ab3445bd9f807a08bfba2.jpeg)

#### 10. @Log

生成log对象，用于记录日志，可以通过topic属性来设置getLogger(String name)方法的参数 例如 @Log4j(topic = “com.xxx.entity.User”)，默认是类的全限定名，即 类名.class，log支持以下几种：

@Log java.util.logging.Logger

@Log4j org.apache.log4j.Logger

@Log4j2 org.apache.logging.log4j.Logger

@Slf4j org.slf4j.Logger

@XSlf4j org.slf4j.ext.XLogger

@CommonsLog org.apache.commons.logging.Log

@JBossLog org.jboss.logging.Logger

![img](Imag/5366d0160924ab18ef97d5add23f99cb79890bf6.jpeg)

![img](Imag/37d12f2eb9389b5034ec70f660f09adbe6116e4b.jpeg)

#### **11. @SneakyThrows**

![img](Imag/79f0f736afc37931d07908ce0e01c84342a91114.jpeg)

#### **12. @Synchronized**

给方法加上同步锁

![img](Imag/0eb30f2442a7d933133c78114b8eae1572f0010c.jpeg)

#### **13. @Cleanup**

主要用来修饰 IO 流相关类, 会在 finally 代码块中对该资源进行 close();

![img](Imag/bba1cd11728b4710c2502ec5240bbcfbfd03238c.jpeg)

#### **14. @Getter(lazy = true)**

@Getter(lazy = true)

标注字段为懒加载字段，懒加载字段在创建对象时不会进行初始化，而是在第一次访问的时候才会初始化，后面再次访问也不会重复初始化

![img](Imag/d788d43f8794a4c24530e483ea3164d3ac6e3956.jpeg)

![img](Imag/0823dd54564e9258d61884897b47ae5ecdbf4ef0.jpeg)

![img](Imag/11385343fbf2b211965d4b0b2f451a3e0dd78e54.jpeg)

#### **15. @Wither**

提供了给final字段赋值的一种方法

![img](Imag/b999a9014c086e0619f1bd67e7cd04f20bd1cb89.jpeg)

#### **16. @Builder**

@Builder注释为你的类生成复杂的构建器API。

![img](Imag/d50735fae6cd7b89ff1b117ae9e13da1d8330e1b.jpeg)

#### **17. @Delegate**

为List类型的字段生成一大堆常用的方法，其实这些方法都是List中的方法

注意：一个类中只能使用一个@Delegate注解，因为使用多个会生成多个size()方法，从而会编译报错。

![img](Imag/c2fdfc039245d6881f191a6c43070218d31b242f.jpeg)

lombok.config

lombok.config配置文件是通过一些设置来控制代码生成的规则或者称之为习惯，配置文件的位置应放在src/mian/java，不要放置在src/main/resources。

注意配置文件和要使用注解的类要在同一套代码中，要么同时在src/main/java 要么同时在 src/test/java中

lombok.config

![img](Imag/c2cec3fdfc039245c7f682026051dbc47c1e2543.jpeg)

![img](Imag/574e9258d109b3ded4bf243f267a1387810a4cb5.jpeg)

