# Spring中@component的使用

@component是spring中的一个注解，它的作用就是实现bean的注入，在探究@component前先了解一下注解？何为注解？注解本质上就是一个类，开发中我们可以使用注解 取代 xml配置文件。

web开发，提供3个@Component注解衍生注解（功能一样）取代
@Repository(“名称”)：dao层
@Service(“名称”)：service层
@Controller(“名称”)：web层

@Autowired：自动根据类型注入
@Qualifier(“名称”):指定自动注入的id名称

@Resource(“名称”)
@ PostConstruct 自定义初始化
@ PreDestroy 自定义销毁

下面直接看具体例子：

![20190609155213368.png (719×277)](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3NjA2OTAx,size_16,color_FFFFFF,t_70.png)

![20190609155227692.png (1538×747)](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3NjA2OTAx,size_16,color_FFFFFF,t_70-20211126223335986.png)

![20190609155241503.png (1100×359)](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3NjA2OTAx,size_16,color_FFFFFF,t_70-20211126223351225.png)

（2）案例二

![2019060915533558.png (849×313)](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3NjA2OTAx,size_16,color_FFFFFF,t_70-20211126223416537.png)

![20190609155345475.png (1021×366)](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3NjA2OTAx,size_16,color_FFFFFF,t_70-20211126223435378.png)

