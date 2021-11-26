# @Resource注解用法

@Resource用法与@Autowired 用法 用法相似，也是做依赖注入的，从容器中自动获取bean。但还是有一定的区别。

- 在启动spring的时候，首先要启动容器；

- 启动spring容器时，会默认寻找容器扫描范围内的可加载bean，

- 然后查找哪些bean上的属性和方法上有@Resource注解；

- 找到@Resource注解后，判断@Resource注解括号中的name属性是否为空，

- 如果为空：看spring容器中的bean的id与@Resource要注解的那个变量属性名是否相同，

- 如相同，匹配成功；

- 如果不相同，看spring容器中bean的id对应的类型是否与@Resource要注解的那个变量属性对应的类型是否相等，

- 若相等，匹配成功，若不相等，匹配失败。

- 如果@Resource注解括号中的name属性不为空，看name的属性值和容器中的bean的id名是否相等，

- 如相等，则匹配成功；如不相等，则匹配失败。

  示例如下：

## 一、@Resource注解注解的name属性不为空

**首先创建Person类，并纳入容器中管理：**

```java
package com.lzj.springboot.resource;
import org.springframework.stereotype.Component;

/*纳入容器中后，bean的id名字为ps*/
@Component(value="ps")
public class Person {

    public void say(){
        System.out.println("------say()------");
    }
}
```

**然后创建Man类，类中的属性依赖Person类型的bean**

```java
package com.lzj.springboot.resource;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class Man {

    /*从容器中取id名字为ps的bean，如果找不到该bean，spring启动过程中就会报错，表示把Man类型的bean注入到容器中不成功，因为person的属性依赖注入的时候就出错了，所以创建Man的bean的时候肯定不成功。*/
    @Resource(name="ps")
    private Person person;

    /*依赖注入失败，因为Person类型注入到容器中的bean的id指定为ps,所以从容器中获取id为person的bean就会失败*/
    //  @Resource(name="person")
    //  private Person ps;

    public void work(){
        person.say();
        System.out.println("------work()------");
    }
}
```

启动工程，输出如下：

```
------say()------
------work()------
```

## 二、@Resource注解注解的name属性为空

**1、@Resource要注解的那个变量属性与容器中的bean的id的名字相等**
启动类和Person的类与相面一样，下面直接修改Man类如下：

```java
@Component
public class Man {

    /*@Resource注解的属性变量ps与容器中的bean的id名字ps相等，可以匹配*/
    @Resource
    private Person ps;

    public void work(){
        ps.say();
        System.out.println("------work()------");
    }
}
```

**2、@Resource要注解的那个变量属性与容器中的bean的id的名字不相等**
启动类和Person的类与相面一样，下面直接修改Man类如下：

```java
@Component
public class Man {

    /*@Resource注解的属性变量ps与容器中的bean的id名字ps不相等，然后通过bean的类型判断：person变量属性的类型为Person类，容器中的id为ps的bean的类型也为Person类型，因此此种情况下也可以匹配*/
    @Resource
    private Person person;

    public void work(){
        person.say();
        System.out.println("------work()------");
    }
}
```

