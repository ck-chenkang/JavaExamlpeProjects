# springboot项目中常用注解

## Bean定义	@component	@component	@Service	@Controller @Bean

web开发，提供3个@Component注解衍生注解（功能一样）取代

1. @Repository(“名称”)：dao层
2. @Service(“名称”)：service层
3. @Controller(“名称”)：web层

## 依赖注入	@Autowired	@Qualifier	@Resource	@PostConstruct	@PreDestroy

@Autowired：自动根据类型注入

@Qualifier(“名称”):指定自动注入的id名称



@Resource(“名称”)

@ PostConstruct 自定义初始化

@ PreDestroy 自定义销毁

