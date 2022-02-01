# Spring @Import注解 —— 导入资源

在应用中，有时没有把某个类注入到IOC容器中，但在运用的时候需要获取该类对应的bean，此时就需要用到@Import注解。*示例如下：* 

先创建两个类，不用注解注入到IOC容器中，在应用的时候在导入到当前容器中。 

1、创建Dog和Cat类 

Dog类

```java
package com.example.demo;
 
public class Dog {
 
}
```

Cat类：

```java
package com.example.demo;
 
public class Cat {
 
}
```

2、在启动类中需要获取Dog和Cat对应的bean，需要用注解@Import注解把Dog和Cat的bean注入到当前容器中。

```java
package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
 
//@SpringBootApplication
@ComponentScan
/*把用到的资源导入到当前容器中*/
@Import({Dog.class, Cat.class})
public class App {
 
    public static void main(String[] args) throws Exception {
 
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        System.out.println(context.getBean(Dog.class));
        System.out.println(context.getBean(Cat.class));
        context.close();
    }
}
```

3、运行该启动类，输出结果：

```
com.example.demo.Dog@4802796d
com.example.demo.Cat@34123d65
```

从输出结果知，@Import注解把用到的bean导入到了当前容器中。

另外，也可以导入一个配置类 
还是上面的Dog和Cat类，现在在一个配置类中进行配置bean，然后在需要的时候，只需要导入这个配置就可以了，最后输出结果相同。

MyConfig 配置类：

```java
package com.example.demo;
import org.springframework.context.annotation.Bean;
 
public class MyConfig {
 
    @Bean
    public Dog getDog(){
        return new Dog();
    }
 
    @Bean
    public Cat getCat(){
        return new Cat();
    }
 
}
```

比如若在启动类中要获取Dog和Cat的bean，如下使用：

```java
package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
 
//@SpringBootApplication
@ComponentScan
/*导入配置类就可以了*/
@Import(MyConfig.class)
public class App {
 
    public static void main(String[] args) throws Exception {
 
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        System.out.println(context.getBean(Dog.class));
        System.out.println(context.getBean(Cat.class));
        context.close();
    }
}
```

