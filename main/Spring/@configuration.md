# Spring @Configuration 注解介绍

[原文](https://www.jianshu.com/p/721c76c1529c)

**提前说明: 下文是我根据官方文档进行自己的描述和扩展, 写下来了我觉得比较值得关注的地方, 并附带了自己的介绍, 并不是原封不动的拿过来了api翻译了下,越往后越精彩**

指示一个类声明一个或多个@Bean方法，并且可以由Spring容器处理，以便在运行时为这些bean生成BeanDefinition和服务请求
 使用方式如下：



```java
@Configuration
public class AppConfig {

    @Bean
    public MyBean myBean() {
        // instantiate, configure and return bean ...
    }
}
```

简单看一下`@Configuration`的内部实现



```kotlin
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Configuration { ... }
```

可以看到这个 `@Component` 注解, 意味也将会注册为bean, 其内部也可以依赖注入。 (换个方式说,一般Bean能用的东西,它也能用) 例如: @Autowired、@Inject、@Scope等

@Configuration类 通常 使用`AnnotationConfigApplicationContext`或 其支持Web的变体`AnnotationConfigWebApplicationContext`进行引导。
 前者的一个简单示例如下：



```java
AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
ctx.register(AppConfig.class);
ctx.refresh();
MyBean myBean = ctx.getBean(MyBean.class);
// use myBean ...
```

如果你看过`ClassXmlPathApplicationContext`的 工作过程, 你应该就能明白。
 @Configuration 注释的类 类似于于一个 xml 配置文件的存在(针对于 ClassXmlPathApplicationContext 来说)。
 不过它是由`AnnotationConfigApplicationContext`类进行解析并注册到Bean的注册表中。
 关于`AnnotationConfigApplicationContext`的工作过程就不说明了。  和`ClassXmlPathApplicationContext`差不多,换汤不换药。 真有兴趣自己调试。

这里提一下, 上面说我们可以将其理解为一个Xml内容的Java版本, 那么,一些Xml中使用的其他写法在其中能用吗? 答案是能。 我也没试过其余用法,写出来自己知道的几个。
 如下:

1. xml中: <bean/> 对应了Java中@Bean
2. xml中: <context:component-scan/> 对应了Java中@ComponentScan



```kotlin
 @Configuration
 @ComponentScan(...)
 public class AppConfig {
     // various @Bean definitions ...
 }
```

1. xml中: <import/> 对应了Java中[@Import](https://www.jianshu.com/p/56d4cadbe5c9)

------

### Via Spring <beans> XML

除了上面写的引导方式外, 也可以使用将该类作为一个 bean 写到 xml 中, 交由`ClassXmlPathApplicationContext`或其他类 进行解析, 如下:



```xml
 <beans>
    <context:annotation-config/>
    <bean class="com.acme.AppConfig"/>
 </beans>
```

这里这个<context:annotation-config/>说明一下, 它的作用是启用一些其他的后处理器(Post Processor), 例如: ConfigurationClassPostProcessor类
 下面会对`ConfigurationClassPostProcessor`进行稍微的提及

------

### With the @Import annotation

**使用`@Import`注解**

我的一篇描述@Import的文章, [点击进入](https://www.jianshu.com/p/56d4cadbe5c9)

------

### With the @Profile annotation

**使用@Profile注释**

这个地方也挺简单的, 我的另一文章简单说了下@Profile注解,[点击查看](https://www.jianshu.com/p/75de79fba705)

关于原文档关于``@Configuration和`@Profile`的联合使用, 自己去看一下[描述](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/Configuration.html)就好了, 一共2个代码例子, 3行话..

------

### With Spring XML using the @ImportResource annotation

**使用`@ImportResource`注解**

这个地方只是拿过来翻译了一下, 因为我觉得有必要写在这提醒一下可以这么用。挺简单的
 [@ImportResource](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/ImportResource.html)

可以使用@ImportResource注释将Spring XML配置文件导入@Configuration类。 可以注入从 XML导入的Bean定义
 例如，使用@Inject：



```kotlin
 @Configuration
 @ImportResource("classpath:/com/acme/database-config.xml")
 public class AppConfig {

     @Inject DataSource dataSource; // from XML

     @Bean
     public MyBean myBean() {
         // inject the XML-defined dataSource bean
         return new MyBean(this.dataSource);
     }
 }
```

------

### With nested @Configuration classes

**使用嵌套的@Configuration类**



```kotlin
这里不写示例了, 可以看下面 Constraints when authoring @Configuration classes
里面的第4个约束的代码示例.. 因为这个帖子我是看着原文从下往上写的.
```

**这里还是要说一下几个点(结合下面的代码示例看)**  
 **1.在引导这样的安排时, 只需要针对应用程序上下文注册AppConfig。**  
 (注册`AppConfig`就自动注册了它的嵌套配置了, 你可以看看关于`@Configuration`相关的PostProcessor是如何处理的, 最下面有提到关于如何使用PostProcessor实现cglib代理的)
 **2.由于是一个嵌套的`@Configuration`类, 其子类也是`@Configuration`的加载模式。**  
 但是AppConfigAux类生成的bean的id是 (AppConfig文件的路径).AppConfig$AppConfigAux。

当然你也可以使用上面说到的[@Import](https://www.jianshu.com/p/56d4cadbe5c9)去搞, 这随你意。

另请注意，嵌套的@Configuration类可以通过@Profile注释使用, 以便为封闭的@Configuration类提供相同bean的两个选项。

------

### Configuring lazy initialization

**配置延迟初始化**

如果在@Configuring注解旁边也是用@Lazy, 如下所示:



```java
@Configuration
@Lazy
public class AppConfig { ... }
```

其内部的所有bean都将会使用该属性, 默认情况下bean是non-lazy的。

------

### Enabling built-in Spring features using @Enable annotations

**使用`@EnableXX`注释启用内置Spring功能**

可以使用各自的`@EnableXXX`注释从`@Configuration`类启用和配置Spring等功能。
 例如：
 1.异步方法执行  [@EnableAsync](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/annotation/EnableAsync.html)  
 2.计划任务执行  [@EnableScheduling](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/annotation/EnableScheduling.html)  
 3.注释驱动事务管理  [@EnableTransactionManagement](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/EnableTransactionManagement.html)、[@EnableAspectJAutoProxy](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/EnableAspectJAutoProxy.html)  
 4.Spring WebMVC  [@EnableWebMvc](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/EnableWebMvc.html)

**稍微提一下 Spring Boot 的启动类注解`@SpringBootApplication`也有元注解`@Configuration`。 你在用 Sprng Cloud 的时候会写很多`@EnableXXX`到你用的时候可以注意一下**

------

### Constraints when authoring @Configuration classes

**创建`@Configuration`类时的约束**

1.必须以类的形式提供配置类, 允许通过生成的子类进行运行时增强(Cglib动态代理)。



```undefined
这里的后半句将在下面说明...有点长,为了不影响格式
```

2.配置类必须是non-final的。



```undefined
可以使用第4个约束的代码对 AppConfigAux 进行修改并测试, 编译器(IDEA)会直接指出这种写法是错误的
```

3.配置类必须是non-local的（即不能在方法中声明）。



```cpp
non-local是不是翻译成非局部的比较好
可以使用第4个约束的代码对 AppConfigAux 的访问级别修改成private试一试,会报错的, 这一步的测试体现该类不能是private级别。

不能在方法中声明 表示的是不能是局部内部类
```

4.必须将任何嵌套配置类声明为static。



```java
@Configuration
public class AppConfig {

    @Bean
    public Chinese getChinese() { ... }

    @Configuration
    public static class AppConfigAux {
        @Bean
        public English getEnglish() { ... }
    }
}
```

5.@Bean方法可能不会反过来创建进一步的配置类（任何此类实例都将被视为常规bean，其配置注释仍未被检测到）。



```java
@Configuration
public class AppConfig {
    // 这个也将被视为一个bean, 并不会有什么特殊处理
    @Bean
    public AppConfig getAppConfig() {
        return new AppConfig();
    }
}
```

------

**对约束1后半句的说明**  
 不知道你们还记得`ConfigurationClassPostProcessor`这个类不. 上面有提到过。
 这个类是用来处理带有`@Configuration`注解的类的, 看一下它的源码:



```java
public class ConfigurationClassPostProcessor implements BeanDefinitionRegistryPostProcessor,
        PriorityOrdered, ResourceLoaderAware, BeanClassLoaderAware, EnvironmentAware { ... }


// 这里再跟进去看看BeanDefinitionRegistryPostProcessor的源码, 如下

public interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor { ... }
```

**重点是它是BeanFactoryPostProcessor接口的实现 这个现象**  
 这里开始往下,如果你有关于IOC的源码只是的话,就很简单了。如果没有,跟着调试。

然后我们就可以跟到`org.springframework.context.support.AbstractApplicationContext#refresh()`方法中, 可以看到`invokeBeanFactoryPostProcessors()`。

到这, 我们知道`ConfigurationClassPostProcessor`工作在BeanDefinitions加载之后, bean实例化之前的结论。
 BeanDefinitions加载是在try外面那2步中(`invokeBeanFactoryPostProcessors()`的上面)
 实例化是在`finishBeanFactoryInitialization(beanFactory)`方法中

到这一步就不跟下去了, 最终这一步会调用`ConfigurationClassPostProcessor.class`中的`postProcessBeanFactory(...)`方法。
 **重点是其中调用的`enhanceConfigurationClasses(ConfigurableListableBeanFactory)`**  
 这个方法中你会看到对@Configuration注解的类的增强操作。 在细节就不讲了, 可以自己跟一下看看如何加载其中@Bean的。

附上文档地址, [点击进入](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/Configuration.html)