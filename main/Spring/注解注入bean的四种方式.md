# 注解注入bean的四种方式

[Spring | Spring通过注解注入Bean的四种方式](https://blog.csdn.net/cafeshadow/article/details/104510837?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522163784951416780265420961%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=163784951416780265420961&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-104510837.first_rank_v2_pc_rank_v29&utm_term=spring%E6%B3%A8%E8%A7%A3%E6%B3%A8%E5%85%A5bean&spm=1018.2226.3001.4187)

# 注解的方式注入Bean

官方定义：IoC又叫依赖注入（DI）。它描述了对象的定义和依赖的一个过程。依赖的对象通过构造参数、工厂方法参数或者属性注入，当对象实例化后依赖的对象才被创建，当创建bean后容器注入这些依赖对象。这个过程基本上是反向的。

##### 1.在Spring中，一个类我们为它加上注解就可以被识别为一个Bean。

例如你在一个类上加上一个@Component注解，它在配置类的@ComponentScan注解扫描的包下，或者把这给个类在配置类中加上@Bean注解，那么它就被识别为Bean，加入到Spring容器里。

```
@Component
public class FirstBean {

}
```

或者直接把这个类在Spring配置类里实例化然后使用@Bean注解。

```
@Configuration
@ComponentScan("com.imooc.spring.demo")
public class SpringBeanConfiguration {

    @Bean
    public FirstBean firstBean() {
        return new FirstBean();
    }
}
```

##### 2.在一个Bean中调用另外一个Bean，就需要依赖注入。

```
@Component
public class SecondBean {
    private FirstBean firstBean;

    @Autowired
    public SecondBean(FirstBean firstBean) {
        this.firstBean = firstBean;
    }
    
    @Override
    public String toString() {
        return "SecondBean{firstBean=" + firstBean + '}';
    }
}
```

一个Bean要调用另一个Bean,直接调用是允许的，需要通过自动装配注解@Autowired进行依赖注入才能达成目的。

#### 常用注解

| 注解                 | 解释                                                  |
| -------------------- | ----------------------------------------------------- |
| @Configuration       | 标记的类是配置类                                      |
| @ComponentScan(“包”) | 指定哪个包，就扫描哪个包下的注解并识别。              |
| @Autowired           | Bean的自动装配,可以标注在类的属性、方法及构造函数上。 |
| @Component           | 把普通类标记为Bean，加入到容器里，并且是单例模式。    |
| @Bean                | 定义一个Bean对象，加入到Spring容器里                  |
| @Order(数字)         | 容器加载Bean的优先级，数字越小优先级越高              |

## 场景

我们将构造方法注入、工厂方法注入或者属性注入三种注入方式进行细化，依赖注入的方式具体如下：

- 构造方法注入Bean
- Set方法注入Bean
- 属性注入Bean
- 集合的方式注入

## 示例

首先通过Configuration和@ComponentScan创建一个Spring的配置类。

```
@Configuration
@ComponentScan("com.imooc.spring.demo.bean")//项目的包名
public class SpringBeanConfiguration {
}
```

新建一个类FirstBean，通过@Component注解标记为Bean，加入到Spring容器里。

```
@Component
public class FirstBean {

}
```

### 一、通过构造方法注入

```
@Component
public class SecondBean {
    private FirstBean firstBean;

    @Autowired
    public SecondBean(FirstBean firstBean) {
        this.firstBean = firstBean;
    }
    
    @Override
    public String toString() {
        return "SecondBean{firstBean=" + firstBean + '}';
    }
}
```

### 二、通过Set方法注入

```
@Component
public class ThirdBean {
    private FirstBean firstBean;

    @Autowired
    public void setFirstBean(FirstBean firstBean) {
        this.firstBean = firstBean;
    }

    @Override
    public String toString() {
        return "ThirdBean{firstBean=" + firstBean + '}';
    }
}
```

### 三、通过属性注入

```
@Component
public class FourthBean {
    @Autowired
    private FirstBean firstBean;

    @Override
    public String toString() {
        return "FourthBean{firstBean=" + firstBean + '}';
    }
}
```

### 四、通过集合注入Bean

新建一个类FirthBean，标记为Bean。 加入一个List类型 的属性，用@Autowired注入。
在配置类中对加入一个返回值为List的方法或者加两个返回String的方法，使用Bean注解，这样就完成集合的赋值。

```
@Component
public class FifthBean {
    @Autowired
    private List<String> stringList;

    public List<String> getStringList() {
        return stringList;
    }
}
```

```
@Configuration
@ComponentScan("com.imooc.spring.demo")
public class SpringBeanConfiguration {

    @Bean
    public List<String> stringList() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("imooc");
        return list;
    }
}
```

```
@Configuration
@ComponentScan("com.imooc.spring.demo")
public class SpringBeanConfiguration {

    @Bean
    public String firstString() {
        return "bye";
    }
    @Bean
    public String secondString() {
        return "imooc";
    }
}
```

第二种方式的优先级高于第一种，两种同时存在时Spring默认使用第二种，若要控制优先级要使用@Order注解来控制优先级顺序。

## 测试方法

```
class BeanDeoTest {

    @Test
    public void test() {
        ApplicationContext applicationContext =
        AnnotationConfigApplicationContext(SpringBeanConfiguration.class);
        SecondBean secondBean = applicationContext.getBean("secondBean", SecondBean.class);
        ThirdBean thirdBean = applicationContext.getBean("thirdBean", ThirdBean.class);
        System.out.println(secondBean.toString());
        System.out.println(thirdBean.toString());
    }
}
```

ApplicationContext用来获取配置类，getBean函数用来获取指定的Bean对象。

## Tips

##### 1. 只有@ComponentScan扫描的包下的被@Component标记的类才能识别为Bean。

##### 2. 测试时你会发现所有的FristBean对象都是一个地址，说明@Component是单例模式的。