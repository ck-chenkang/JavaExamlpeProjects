# @Component注解和@Bean注解的作用，以及两者的区别：

程序猿的我们，开发中少不了使用Spring框架，虽然天天接触它，但有时就像一句话：世界上最远的距离仿佛是头到脚~~~那你是否有知道它的内部原理呢？下面跟大家分享一下@Compoent和@Bean两个注解的差异：

@Component注解表明一个类会作为组件类，并告知Spring要为这个类创建bean。

@Bean注解告诉Spring这个方法将会返回一个对象，这个对象要注册为Spring应用上下文中的bean。通常方法体中包含了最终产生bean实例的逻辑。

   两者的目的是一样的，都是注册bean到Spring容器中。

区别：

@Component（@Controller、@Service、@Repository）通常是通过类路径扫描来自动侦测以及自动装配到Spring容器中。

而@Bean注解通常是我们在标有该注解的方法中定义产生这个bean的逻辑。

@Component 作用于类，@Bean作用于方法。

总结：

@Component和@Bean都是用来注册Bean并装配到Spring容器中，但是Bean比Component的自定义性更强。可以实现一些Component实现不了的自定义加载类。