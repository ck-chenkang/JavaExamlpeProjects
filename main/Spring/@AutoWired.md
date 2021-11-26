

# @Autowired 用法

一、在spring中，可以自动装配具有 @Autowired 和 @Resource 、@Inject注解的属性.

因为元素自动注册了AutowiredAnnotationBeanPostProcessor 实例，该实例可以自动装配@Autowired 和 @Resource 、@Inject注解

的属性。@Autowired 和 @Resource 、@Inject实现的功能相似，@Autowired实现的比后两者更全面，因此大部分都用@Autowired注

解。另外@Resource 、@Inject注解中没有reqired 属性，该属性用法在下面进行举例。

@Autowired注解使用范围：

- 构造器,
- 普通字段(即使是非 public),
- 一切具有参数的方法都可以应用@Authwired 注解。

示例如下：

**spring配置文件bean.xml：**

```xml
<context:component-scan base-package="com.lzj.spring" ></context:component-scan>
```

**ControllerBean 类：**

```java
package com.lzj.spring.controller;

import org.springframework.stereotype.Controller;

@Controller
public class ControllerBean {

    public void show() {
        System.out.println("ControllerBean.......");
    }
}
```

**ComponentBean 类：**

```java
package com.lzj.spring.component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lzj.spring.controller.ControllerBean;

/*把ComponentBean 这个类的实例自动注入到IOC容器中，该实例在IOC容名字为beanComponent*/
@Component("beanComponent")
public class ComponentBean {

    /*如果IOC容器中有ControllerBean 类型的实例，就自动从IOC容器中获取该类的实例，赋给controllerBean属性。
    注意：只能从<context:component-scan>扫描的范围内自动获取*/
    @Autowired
    private ControllerBean controllerBean;

    /*也可以用下面的方法代替，通过属性的set方法注入*/
    /*
    @Autowired
    public void setControllerBean(ControllerBean controllerBean) {
        this.controllerBean = controllerBean;
    }
    */
    public void show() {
        System.out.println("ComponentBean........");
        controllerBean.show();
    }
}
```

**测试类：**

```java
public class SpringTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = 
                new ClassPathXmlApplicationContext("bean.xml");

        ComponentBean componentBean = (ComponentBean) ctx.getBean("beanComponent");
        componentBean.show();   
        ctx.close();
    }

}
```

**结果输出：**

```
ComponentBean........
ControllerBean.......
```

从上面的示例可以看出，@Autowired注解自动的把IOC容器的ControllerBean的实例注入到了controllerBean属性中。

## 二、下面再介绍@Autowired 注解的其它几种使用情况

**1、@Autowired 注解的 required 属性**

默认情况下，所有使用@Autowired 注解的属性，该属性对应的实例一定能在IOC容器中能找到才可以。如果IOC容器中不存在，则会抛

出找不到bean的实例错误。因为@Autowired 的required 属性默认为true，表示属性对应的bean的实例一定要存在才可以。如果要IOC

容器中不存在属性对应的bean的实例，在程序运行的时候也不报错，可以把required 置为false。例如：

```java
@Component("beanComponent")
public class ComponentBean {

    @Autowired(required=false)
    private ControllerBean controllerBean;

    public void show() {
        System.out.println("ComponentBean........");
        System.out.println(controllerBean);
    }
}
```

把ControllerBean类上的注解@Controller去掉，这样就不能把ControllerBean类的实例注入到IOC容器中了。运行上面的测试类，输

出：

```
ComponentBean........
null
```

可见controllerBean属性指向的对象为空。

**2、@Autowired 注解配合@Qualifier使用**

当用@Autowired为属性自动注入bean的实例时，如果IOC容器中有多个bean的实例，程序就会抛一个IOC中没有一个唯一bean实例的错

误。例如：

```java
package com.lzj.spring;
public interface Animal 
    public void eat();
}
```

```java
package com.lzj.spring;
import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal {

    @Override
    public void eat() {
        System.out.println("狗吃狗食。。。");
    }

}
```

```java
package com.lzj.spring;
import org.springframework.stereotype.Component;

@Component
public class Cat implements Animal {

    @Override
    public void eat() {
        System.out.println("猫吃猫食。。。");
    }

}
```

```java
package com.lzj.spring.component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lzj.spring.Animal;
import com.lzj.spring.controller.ControllerBean;

@Component("beanComponent")
public class ComponentBean {

    /*把IOC容器中Animal 类型的实例注入到animal属性中。注意：Animal是一个接口，所有实现该接口的类的实例都可以注入到animal属性中。*/
    @Autowired
    private Animal animal;

    public void show() {
        System.out.println("ComponentBean........");
        animal.eat();
    }
}
```

当运行测试类时，会抛出一个没有唯一bean实例的错误，因为此时IOC容器中有dog和cat的实例，两者都是Animal的实例。此时就可以

用@Qualifier注解指定具体使用哪个IOC容器中哪个实例了。例如：

```java
@Component("beanComponent")
public class ComponentBean {

    /*Qualifier注解指定把IOC容器中dog实例注入到animal属性中*/
    @Autowired
    @Qualifier("dog")
    private Animal animal;

    public void show() {
        System.out.println("ComponentBean........");
        animal.eat();
    }
}
```

运行测试类，输出：

```
ComponentBean........
狗吃狗食。。。
```

