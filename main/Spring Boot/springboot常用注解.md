# springboot项目中常用注解

## 1、@SpringBootApplication

这个注解是 Spring Boot 项目的基石，创建 SpringBoot 项目之后会默认在主类加上。

```typescript
@SpringBootApplication
public class SpringSecurityJwtGuideApplication {
      public static void main(java.lang.String[] args) {
        SpringApplication.run(SpringSecurityJwtGuideApplication.class, args);
    }
}
```

我们可以把 `@SpringBootApplication`看作是 `@Configuration`、`@EnableAutoConfiguration`、`@ComponentScan` 注解的集合。

```java
package org.springframework.boot.autoconfigure;
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
  @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
  @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
   ......


}

package org.springframework.boot;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
public @interface SpringBootConfiguration {

}
```

根据 SpringBoot 官网，这三个注解的作用分别是：

- `@EnableAutoConfiguration`：启用 SpringBoot 的自动配置机制
- `@ComponentScan`： 扫描被`@Component` (`@Service`,`@Controller`)注解的 bean，注解默认会扫描该类所在的包下所有的类。
- `@Configuration`：允许在 Spring 上下文中注册额外的 bean 或导入其他配置类

## 2、 Bean相关

### Bean定义	@Component、@Service、@Controller、@Repository 

我们一般使用 `@Autowired` 注解让 Spring 容器帮我们自动装配 bean。要想把类标识成可用于 `@Autowired` 注解自动装配的 bean 的类,可以采用以下注解实现：

- `@Component` ：通用的注解，可标注任意类为 `Spring` 组件。如果一个 Bean 不知道属于哪个层，可以使用`@Component` 注解标注。
- `@Repository` : 对应持久层即 Dao 层，主要用于数据库相关操作。
- `@Service` : 对应服务层，主要涉及一些复杂的逻辑，需要用到 Dao 层。
- `@Controller` : 对应 Spring MVC 控制层，主要用户接受用户请求并调用 Service 层返回数据给前端页面。

### @Bean @Configuration

@Configuration作用于类上面，表明这是一个配置类，@Bean产生一个Bean对象加入Spring IOC容器

**注意：@Configuration标注在类上，相当于把该类作为spring的xml配置文件中，作用为：配置spring容器(应用上下文)**

| 注解           | 说明                                                         |
| -------------- | ------------------------------------------------------------ |
| @Configuration | 作用于类上表示这是一个配置类，可理解为用spring的时候xml里面的< beans>标签 |
| @Bean          | 产生bean对象加入容器，作用于方法，可理解为用spring的时候xml里面的标签 |

一般这两个注解同时配合使用

新建配置类，将User加入容器，并自定义生命周期
![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172018562.png)
测试
![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70.png)

### **@ResponseBody**

@ResponseBody可以作用在方法上或类上，表示该方法的返回结果直接写入 HTTP response body 中，而不会被解析为跳转路径，即不会经过视图解析器，返回什么数据即在页面输入什么数据。

| 注解          | 说明                                                    |
| :------------ | :------------------------------------------------------ |
| @ResponseBody | @ResponseBody的作用其实是将java对象转为json格式的数据。 |

测试如下
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172344817.png)

## 

### **@RestController**

该注解是@Controller和@ResponseBody的结合体，一般用于类，作用等于在类上面添加了@ResponseBody和@Controller

### @Scope

声明 Spring Bean 的作用域，使用方法:

```java
@Bean
@Scope("singleton")
public Person personSingleton() {
    return new Person();
}
```

**四种常见的 Spring Bean 的作用域：**

- singleton : 唯一 bean 实例，Spring 中的 bean 默认都是单例的。
- prototype : 每次请求都会创建一个新的 bean 实例。
- request : 每一次 HTTP 请求都会产生一个新的 bean，该 bean 仅在当前 HTTP request 内有效。
- session : 每一次 HTTP 请求都会产生一个新的 bean，该 bean 仅在当前 HTTP session 内有效。

### @Configuration

一般用来声明配置类，可以使用 `@Component`注解替代，不过使用`Configuration`注解声明配置类更加语义化。

```java
@Configuration
public class AppConfig {
    @Bean
    public TransferService transferService() {
        return new TransferServiceImpl();
    }
}
```

## 3、依赖注入

### 依赖注入	@Autowired	@Qualifier	@Resource

这3个注解都是基于注解方式进行自动装配，在容器里面将查找到的bean返回，一般@AutoWired用得最多，@Qualifier则需要配合@AutoWired使用，@Resource则是可以通过名字进行自动装配

| 注解       | 说明                                                         |
| :--------- | :----------------------------------------------------------- |
| @AutoWired | @Autowired默认按类型装配，如果发现找到多个bean，则按照name方式比对，如果还有多个，则报出异常 |
| @Qualifier | spring的注解，按名字注入 一般当出现两个及以上bean时,不知道要注入哪个，结合@AutoWired使用 |
| @Resource  | 默认按名称注入例如@Resource(name = “zhaozhao”)则根据name属性注入找不到则报错，若无name属性则根据属性名称注入，如果匹配不成功则按照类型匹配匹配不成功则报错。 |

**@AutoWired**
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172344767.png)
 **@Qualifier**
 当有一个接口的多个实现类时，只用@AutoWired会报错，因为它有多个接口的实现类，不知道你要找哪一个，这个时候就需要在注入bean的时候起个名字，然后用@Qualifier注解指定哪一个bean(按照名字注入与装配)
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172344777.png)
 **@Resource**
 该注解的使用相当于@AutoWired和@Qualifier配合使用的效果
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172344751.png)

## 4、处理Http请求

###  @RequestMapping

[【springboot】超详细 Spring @RequestMapping 注解使用技巧](https://blog.csdn.net/rocling/article/details/82903574?ops_request_misc=&request_id=&biz_id=102&utm_term=springboot%20requestmapping&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-1-82903574.nonecase&spm=1018.2226.3001.4187)

**@RequestMapping**
 发起get请求或者post请求都可以
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172344839.png)

### @GetMapping

GET 请求

```java
@GetMapping("users")` 等价于`@RequestMapping(value="/users",method=RequestMethod.GET)
@GetMapping("/users")
public ResponseEntity<List<User>> getAllUsers() {
 return userRepository.findAll();
}
```

### @PostMapping

Post请求

```
@PostMapping("users")` 等价于`@RequestMapping(value="/users",method=RequestMethod.POST)
```

关于`@RequestBody`注解的使用，在下面的“前后端传值”这块会讲到。

```kotlin
@PostMapping("/users")
public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
 return userRespository.save(user);
}
```

### @PutMapping 

PUT 请求

```java
@PutMapping("/users/{userId}")` 等价于`@RequestMapping(value="/users/{userId}",method=RequestMethod.PUT)
@PutMapping("/users/{userId}")
public ResponseEntity<User> updateUser(@PathVariable(value = "userId") Long userId,
  @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
  ......
}
```

### @DeleteMapping

**DELETE 请求**

```java
@DeleteMapping("/users/{userId}")`等价于`@RequestMapping(value="/users/{userId}",method=RequestMethod.DELETE)
@DeleteMapping("/users/{userId}")
public ResponseEntity deleteUser(@PathVariable(value = "userId") Long userId){
  ......
}
```

### @PatchMapping

**PATCH 请求**

一般实际项目中，我们都是 PUT 不够用了之后才用 PATCH 请求去更新数据。

```java
@PatchMapping("/profile")
public ResponseEntity updateStudent(@RequestBody StudentUpdateRequest studentUpdateRequest) {
  studentRepository.updateDetail(studentUpdateRequest);
  return ResponseEntity.ok().build();
}
```

## 5、获取请求参数

### **@RequestParam、@RequestBody、@PathVariable、@RequestHeader、@CookieValue**

这3个注解放在一起主要是经常在控制层用来接收参数的

| 注解           | 说明                                 |
| :------------- | :----------------------------------- |
| @RequestParam  | 获取查询参数。即url?name=这种形式    |
| @PathVariable  | 获取路径参数。即url/{id}这种形式。   |
| @RequestParam  | 获取Body的参数，一般用于post获取参数 |
| @RequestHeader | 获取请求头的信息                     |
| @CookieValue   | 获取Cookie的信息                     |

**@RequestParam**
 @RequestParam主要用于接收url?后面的参数，get或post请求，只要后面的url?有参数都可以获取到对应的参数

@RequestParam注解有几个比较重要的属性，required 表示是否必须，默认为 true，必须。defaultValue 可设置请求参数的默认值。value 为接收url的参数名（相当于key值）。

示例代码如下

```java
    @GetMapping("/requestParam")
    @ResponseBody
    public Map<String, String> requestParam(
            UserDto userDto,//通过一个实体类来接收，字段名必须一致
            @RequestParam(value = "id", required = false) String userId,
            @RequestParam(value = "name", required = false) String userName,
            @RequestParam(value = "pageIndex", required = true, defaultValue = "1") String pageIndex,
            @RequestParam(value = "pageSize", required = true, defaultValue = "5") String pageSize) {
        Map<String, String> map = new HashMap<>();
        map.put("userDto",userDto.toString());
        map.put("id", userId);
        map.put("name", userName);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        return map;
    }
```

运行
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172344992.png)

**@PathVariable**
 该注解主要用于获取路径参数，像url/{id}/{name}这种形式的参数都可以，get获取post请求均可

示例代码如下：

```java
    @PostMapping("/pathVariable/{id}/{name}")
    @ResponseBody
    public Map<String, String> pathVariable(
            @PathVariable(name = "id") String userId,
            @PathVariable(name = "name") String userName) {

        Map<String, String> map = new HashMap<>();
        map.put("id", userId);
        map.put("name", userName);
        return map;
    }
```

运行结果

![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172345083.png)

**@RequestBody**
 该注解用于获取请求体数据（body）,get没有请求体，故而一般用于post请求

示例代码如下：

```java
    @PostMapping("/test01")
    @ResponseBody
    public UserDto test01(@RequestBody UserDto userDto) {
        return userDto;
    }

    @PostMapping("/test02")
    @ResponseBody
    public String test02(@RequestBody String str) {
        return str;
    }
```

运行结果
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172344970.png)
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172344953.png)

注意，如果要传多个参数过去只能将其封装成一个类，如果是出现了多个@RequestBody注解访问的时候会报400错误，例如下面这种代码就是错误的

```java
    @PostMapping("/requestBody")
    @ResponseBody
    public Map<String,String> requestBody(
            @RequestBody(required = true) String id,
            @RequestBody(required = true) String name,
            @RequestBody(required = false) String sex,
            @RequestBody(required = false) String age
            ){

        Map<String,String> map = new HashMap<>();
        map.put("id","id");
        map.put("name","name");

        return map;
    }
```

**@RequestHeader**
 示例代码如下

```java
    @PostMapping("/requestHeader")
    @ResponseBody
    public String requestBody03(@RequestHeader(name = "Content-Type") String contentType){
        return contentType;
    }
```

运行结果
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172345094.png)

**@CookieValue**
 由于postman模拟cookie本人不会弄，只能用别人的代码

```java
@GetMapping("/demo3")
public void demo3(@RequestHeader(name = "myHeader") String myHeader,
        @CookieValue(name = "myCookie") String myCookie) {
    System.out.println("myHeader=" + myHeader);
    System.out.println("myCookie=" + myCookie);
}
```

## 6、属性注入 

### **@Value、@ConfigurationProperties、@PropertySource**

| 注解                     | 说明                                                         |
| :----------------------- | :----------------------------------------------------------- |
| @Value                   | 用于获取bean的属性，一般用于读取配置文件的数据，作用在变量上 |
| @ConfigurationProperties | 用于注入Bean属性，然后再通过当前Bean获取注入值，作用在类上   |
| @PropertySource          | 用于指定要读取的配置文件，可以和@Value或@ConfigurationProperties配合使用 |

**注意：@PropertySource不支持yml文件读取。**

**@Value**
 这里用yml配置文件进行演示，propres配置文件也是同样的效果，在application.yml配置文件里设置开发环境的的配置文件（dev），这样用@Value获取到的就是开发环境的配置文件的数据，切换成生产环境（pro）则获取到的是生产环境的数据
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172344868.png)
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172344903.png)
 **@ConfigurationProperties**
 该注解可以直接注入整个类的数据，作用于类

配置文件如下，这里使用pro环境
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172344912.png)
 测试
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172344909.png)

**@PropertySource**

注意：@PropertySource不支持yml文件读取。
 配置文件如下：people.properties
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172344904.png)
 测试
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMjk4OTAy,size_16,color_FFFFFF,t_70-20220310172344931.png)
 当然@PropertySource还可以和@Value配合使用，即一个一个注入值。

## 7、参数校验

**数据的校验的重要性就不用说了，即使在前端对数据进行校验的情况下，我们还是要对传入后端的数据再进行一遍校验，避免用户绕过浏览器直接通过一些 HTTP 工具直接向后端请求一些违法数据。**

**JSR(Java Specification Requests）** 是一套 JavaBean 参数校验的标准，它定义了很多常用的校验注解，我们可以直接将这些注解加在我们 JavaBean 的属性上面，这样就可以在需要校验的时候进行校验了，非常方便！

校验的时候我们实际用的是 **Hibernate Validator** 框架。Hibernate Validator 是 Hibernate 团队最初的数据校验框架，Hibernate Validator 4.x 是 Bean Validation 1.0（JSR 303）的参考实现，Hibernate Validator 5.x 是 Bean Validation 1.1（JSR 349）的参考实现，目前最新版的 Hibernate Validator 6.x 是 Bean Validation 2.0（JSR 380）的参考实现。

SpringBoot 项目的 spring-boot-starter-web 依赖中已经有 hibernate-validator 包，不需要引用相关依赖。如下图所示（通过 idea 插件—Maven Helper 生成）：

![img](Imag/format,png-20220310172434022.png)

非 SpringBoot 项目需要自行引入相关依赖包，这里不多做讲解，具体可以查看我的这篇文章：《[如何在 Spring/Spring Boot 中做参数校验？你需要了解的都在这里！](https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247485783&idx=1&sn=a407f3b75efa17c643407daa7fb2acd6&chksm=cea2469cf9d5cf8afbcd0a8a1c9cc4294d6805b8e01bee6f76bb2884c5bc15478e91459def49&token=292197051&lang=zh_CN&scene=21#wechat_redirect)》。

👉 需要注意的是： **所有的注解，推荐使用 JSR 注解，即`javax.validation.constraints`，而不是`org.hibernate.validator.constraints`**

### 一些常用的字段验证的注解

- `@NotEmpty` 被注释的字符串的不能为 null 也不能为空
- `@NotBlank` 被注释的字符串非 null，并且必须包含一个非空白字符
- `@Null` 被注释的元素必须为 null
- `@NotNull` 被注释的元素必须不为 null
- `@AssertTrue` 被注释的元素必须为 true
- `@AssertFalse` 被注释的元素必须为 false
- `@Pattern(regex=,flag=)`被注释的元素必须符合指定的正则表达式
- `@Email` 被注释的元素必须是 Email 格式。
- `@Min(value)`被注释的元素必须是一个数字，其值必须大于等于指定的最小值
- `@Max(value)`被注释的元素必须是一个数字，其值必须小于等于指定的最大值
- `@DecimalMin(value)`被注释的元素必须是一个数字，其值必须大于等于指定的最小值
- `@DecimalMax(value)` 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
- `@Size(max=, min=)`被注释的元素的大小必须在指定的范围内
- `@Digits (integer, fraction)`被注释的元素必须是一个数字，其值必须在可接受的范围内
- `@Past`被注释的元素必须是一个过去的日期
- `@Future` 被注释的元素必须是一个将来的日期
- ......

### 验证请求体(RequestBody)

```kotlin
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @NotNull(message = "classId 不能为空")
    private String classId;

    @Size(max = 33)
    @NotNull(message = "name 不能为空")
    private String name;

    @Pattern(regexp = "((^Man$|^Woman$|^UGM$))", message = "sex 值不在可选范围")
    @NotNull(message = "sex 不能为空")
    private String sex;

    @Email(message = "email 格式不正确")
    @NotNull(message = "email 不能为空")
    private String email;
}
```

我们在需要验证的参数上加上了`@Valid`注解，如果验证失败，它将抛出`MethodArgumentNotValidException`。

```kotlin
@RestController
@RequestMapping("/api")
public class PersonController {
    @PostMapping("/person")
    public ResponseEntity<Person> getPerson(@RequestBody @Valid Person person) {
        return ResponseEntity.ok().body(person);
    }
}
```

###  验证请求参数(Path Variables 和 Request Parameters)

**一定一定不要忘记在类上加上 `Validated` 注解了，这个参数可以告诉 Spring 去校验方法参数。**

```kotlin
@RestController
@RequestMapping("/api")
@Validated
public class PersonController {
    @GetMapping("/person/{id}")
    public ResponseEntity<Integer> getPersonByID(@Valid @PathVariable("id") @Max(value = 5,message = "超过 id 的范围了") Integer id) {
        return ResponseEntity.ok().body(id);
    }
}
```

更多关于如何在 Spring 项目中进行参数校验的内容，请看《[如何在 Spring/Spring Boot 中做参数校验？你需要了解的都在这里！](https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247485783&idx=1&sn=a407f3b75efa17c643407daa7fb2acd6&chksm=cea2469cf9d5cf8afbcd0a8a1c9cc4294d6805b8e01bee6f76bb2884c5bc15478e91459def49&token=292197051&lang=zh_CN&scene=21#wechat_redirect)》这篇文章。

## 8、全局异常处理

### 全局处理 Controller 层异常

介绍一下我们 Spring 项目必备的全局处理 Controller 层异常。

**相关注解：**

1. `@ControllerAdvice` :注解定义全局异常处理类
2. `@ExceptionHandler` :注解声明异常处理方法

如何使用呢？拿我们在第 5 节参数校验这块来举例子。如果方法参数不对的话就会抛出`MethodArgumentNotValidException`，我们来处理这个异常。

```swift
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 请求参数异常处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
       ......
    }
}
```

更多关于 Spring Boot 异常处理的内容，请看我的这两篇文章：

1. [SpringBoot 处理异常的几种常见姿势](https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247485568&idx=2&sn=c5ba880fd0c5d82e39531fa42cb036ac&chksm=cea2474bf9d5ce5dcbc6a5f6580198fdce4bc92ef577579183a729cb5d1430e4994720d59b34&token=2133161636&lang=zh_CN&scene=21#wechat_redirect)
2. [使用枚举简单封装一个优雅的 Spring Boot 全局异常处理！](https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247486379&idx=2&sn=48c29ae65b3ed874749f0803f0e4d90e&chksm=cea24460f9d5cd769ed53ad7e17c97a7963a89f5350e370be633db0ae8d783c3a3dbd58c70f8&token=1054498516&lang=zh_CN&scene=21#wechat_redirect)

## 9、JPA

### 9.1. 创建表

`@Entity`声明一个类对应一个数据库实体。

`@Table` 设置表明

```kotlin
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
  
    private String description;
    省略getter/setter......
}
```

### 9.2. 创建主键

`@Id` ：声明一个字段为主键。

使用`@Id`声明之后，我们还需要定义主键的生成策略。我们可以使用 `@GeneratedValue` 指定主键生成策略。

**1.通过 `@GeneratedValue`直接使用 JPA 内置提供的四种主键生成策略来指定主键生成策略。**

```kotlin
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

JPA 使用枚举定义了 4 中常见的主键生成策略，如下：

*Guide 哥：枚举替代常量的一种用法*

```csharp
public enum GenerationType {

    /**
     * 使用一个特定的数据库表格来保存主键
     * 持久化引擎通过关系数据库的一张特定的表格来生成主键,
     */

    TABLE,

    /**

     *在某些数据库中,不支持主键自增长,比如Oracle、PostgreSQL其提供了一种叫做"序列(sequence)"的机制生成主键

     */

    SEQUENCE,

    /**
     * 主键自增长
   */
    IDENTITY,
    /**

     *把主键生成策略交给持久化引擎(persistence engine),

     *持久化引擎会根据数据库在以上三种主键生成 策略中选择其中一种
     */

    AUTO
}

@GeneratedValue`注解默认使用的策略是`GenerationType.AUTO
public @interface GeneratedValue {

    GenerationType strategy() default AUTO;
    String generator() default "";
}
```

一般使用 MySQL 数据库的话，使用`GenerationType.IDENTITY`策略比较普遍一点（分布式系统的话需要另外考虑使用分布式 ID）。

**2.通过 `@GenericGenerator`声明一个主键策略，然后 `@GeneratedValue`使用这个策略**

```kotlin
@Id
@GeneratedValue(generator = "IdentityIdGenerator")
@GenericGenerator(name = "IdentityIdGenerator", strategy = "identity")
private Long id;
```

等价于：

```kotlin
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

jpa 提供的主键生成策略有如下几种：

```csharp
public class DefaultIdentifierGeneratorFactory

  implements MutableIdentifierGeneratorFactory, Serializable, ServiceRegistryAwareService {

 @SuppressWarnings("deprecation")

 public DefaultIdentifierGeneratorFactory() {

  register( "uuid2", UUIDGenerator.class );

  register( "guid", GUIDGenerator.class );   // can be done with UUIDGenerator + strategy

  register( "uuid", UUIDHexGenerator.class );   // "deprecated" for new use

  register( "uuid.hex", UUIDHexGenerator.class );  // uuid.hex is deprecated

  register( "assigned", Assigned.class );

  register( "identity", IdentityGenerator.class );

  register( "select", SelectGenerator.class );

  register( "sequence", SequenceStyleGenerator.class );

  register( "seqhilo", SequenceHiLoGenerator.class );

  register( "increment", IncrementGenerator.class );

  register( "foreign", ForeignGenerator.class );

  register( "sequence-identity", SequenceIdentityGenerator.class );

  register( "enhanced-sequence", SequenceStyleGenerator.class );

  register( "enhanced-table", TableGenerator.class );
 }

 public void register(String strategy, Class generatorClass) {

  LOG.debugf( "Registering IdentifierGenerator strategy [%s] -> [%s]", strategy, generatorClass.getName() );

  final Class previous = generatorStrategyToClassNameMap.put( strategy, generatorClass );
   
  if ( previous != null ) {
   LOG.debugf( "    - overriding [%s]", previous.getName() );
  }
 }
}
```

### 9.3. 设置字段类型

`@Column` 声明字段。

**示例：**

设置属性 userName 对应的数据库字段名为 user_name，长度为 32，非空

```typescript
@Column(name = "user_name", nullable = false, length=32)
private String userName;
```

设置字段类型并且加默认值，这个还是挺常用的。

```sql
Column(columnDefinition = "tinyint(1) default 1")
private Boolean enabled;
```

### 9.4. 指定不持久化特定字段

`@Transient` ：声明不需要与数据库映射的字段，在保存的时候不需要保存进数据库 。

如果我们想让`secrect` 这个字段不被持久化，可以使用 `@Transient`关键字声明。

```typescript
Entity(name="USER")
public class User {
    ......

    @Transient
    private String secrect; // not persistent because of @Transient
}
```

除了 `@Transient`关键字声明， 还可以采用下面几种方法：

```rust
static String secrect; // not persistent because of static

final String secrect = “Satish”; // not persistent because of final
transient String secrect; // not persistent because of transient
```

一般使用注解的方式比较多。

### 9.5. 声明大字段

`@Lob`:声明某个字段为大字段。

```typescript
@Lob
private String content;
```

更详细的声明：

```kotlin
@Lob
//指定 Lob 类型数据的获取策略， FetchType.EAGER 表示非延迟 加载，而 FetchType. LAZY 表示延迟加载 ；
@Basic(fetch = FetchType.EAGER)
//columnDefinition 属性指定数据表对应的 Lob 字段类型
@Column(name = "content", columnDefinition = "LONGTEXT NOT NULL")
private String content;
```

### 9.6. 创建枚举类型的字段

可以使用枚举类型的字段，不过枚举字段要用`@Enumerated`注解修饰。

```rust
public enum Gender {
  
    MALE("男性"),

    FEMALE("女性");

    private String value;

    Gender(String str){

        value=str;

    }
}

@Entity
@Table(name = "role")
public class Role {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    省略getter/setter......
}
```

数据库里面对应存储的是 MAIL/FEMAIL。

### 9.7. 增加审计功能

只要继承了 `AbstractAuditBase`的类都会默认加上下面四个字段。

```typescript
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public abstract class AbstractAuditBase {

    @CreatedDate
    @Column(updatable = false)
    @JsonIgnore
    private Instant createdAt;

    @LastModifiedDate
    @JsonIgnore
    private Instant updatedAt;

    @CreatedBy
    @Column(updatable = false)
    @JsonIgnore
    private String createdBy;

    @LastModifiedBy
    @JsonIgnore
    private String updatedBy;
}
```

我们对应的审计功能对应地配置类可能是下面这样的（Spring Security 项目）:

```typescript
@Configuration
@EnableJpaAuditing
public class AuditSecurityConfiguration {
    @Bean
    AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getName);
    }
}
```

简单介绍一下上面设计到的一些注解：

1. `@CreatedDate`: 表示该字段为创建时间时间字段，在这个实体被 insert 的时候，会设置值

2. `@CreatedBy` :表示该字段为创建人，在这个实体被 insert 的时候，会设置值

   `@LastModifiedDate`、`@LastModifiedBy`同理。

`@EnableJpaAuditing`：开启 JPA 审计功能。

### 9.8. 删除/修改数据

`@Modifying` 注解提示 JPA 该操作是修改操作,注意还要配合`@Transactional`注解使用。

```java
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void deleteByUserName(String userName);
}
```

### 9.9. 关联关系

- `@OneToOne` 声明一对一关系
- `@OneToMany` 声明一对多关系
- `@ManyToOne`声明多对一关系
- `MangToMang`声明多对多关系

更多关于 Spring Boot JPA 的文章请看我的这篇文章：[一文搞懂如何在 Spring Boot 正确中使用 JPA](https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247485689&idx=1&sn=061b32c2222869932be5631fb0bb5260&chksm=cea24732f9d5ce24a356fb3675170e7843addbfcc79ee267cfdb45c83fc7e90babf0f20d22e1&token=292197051&lang=zh_CN&scene=21#wechat_redirect) 。

## 10. 事务 @Transactional

在要开启事务的方法上使用`@Transactional`注解即可!

```csharp
@Transactional(rollbackFor = Exception.class)



public void save() {



  ......



}



 
```

我们知道 Exception 分为运行时异常 RuntimeException 和非运行时异常。在`@Transactional`注解中如果不配置`rollbackFor`属性,那么事物只会在遇到`RuntimeException`的时候才会回滚,加上`rollbackFor=Exception.class`,可以让事物在遇到非运行时异常时也回滚。

`@Transactional` 注解一般用在可以作用在`类`或者`方法`上。

- **作用于类**：当把`@Transactional 注解放在类上时，表示所有该类的`public 方法都配置相同的事务属性信息。
- **作用于方法**：当类配置了`@Transactional`，方法也配置了`@Transactional`，方法的事务会覆盖类的事务配置信息。

## 11. json 数据处理

### 11.1. 过滤 json 数据

**`@JsonIgnoreProperties` 作用在类上用于过滤掉特定字段不返回或者不解析。**

```typescript
//生成json时将userRoles属性过滤
@JsonIgnoreProperties({"userRoles"})
public class User {

    private String userName;

    private String fullName;

    private String password;

    @JsonIgnore
    private List<UserRole> userRoles = new ArrayList<>();
}
```

**`@JsonIgnore`一般用于类的属性上，作用和上面的`@JsonIgnoreProperties` 一样。**

```typescript
public class User {

    private String userName;

    private String fullName;

    private String password;

   //生成json时将userRoles属性过滤
  
    @JsonIgnore
    private List<UserRole> userRoles = new ArrayList<>();

}
```

### 11.2. 格式化 json 数据

`@JsonFormat`一般用来格式化 json 数据。：

比如：

```sql
@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="GMT")
private Date date;
```

### 11.3. 扁平化对象

```typescript
@Getter
@Setter
@ToString
public class Account {
  
    @JsonUnwrapped
    private Location location;

    @JsonUnwrapped
    private PersonInfo personInfo;

  @Getter
  @Setter
  @ToString
  public static class Location {

     private String provinceName;

     private String countyName;
  }

  @Getter
  @Setter
  @ToString
  public static class PersonInfo {

    private String userName;

    private String fullName;

  }
}
```

未扁平化之前：

```json
{
    "location": {

        "provinceName":"湖北",

        "countyName":"武汉"
    },

    "personInfo": {

        "userName": "coder1234",
      
        "fullName": "shaungkou"
    }
}
```

使用`@JsonUnwrapped` 扁平对象之后：

```kotlin
@Getter
@Setter
@ToString
public class Account {

    @JsonUnwrapped
    private Location location;

    @JsonUnwrapped
    private PersonInfo personInfo;
  
    ......

}
{

  "provinceName":"湖北",

  "countyName":"武汉",

  "userName": "coder1234",

  "fullName": "shaungkou"

}
```

## 12. 测试相关

**`@ActiveProfiles`一般作用于测试类上， 用于声明生效的 Spring 配置文件。**

```kotlin
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@Slf4j
public abstract class TestBase {

  ......
  
}
```

**`@Test`声明一个方法为测试方法**

**`@Transactional`被声明的测试方法的数据会回滚，避免污染测试数据。**

**`@WithMockUser` Spring Security 提供的，用来模拟一个真实用户，并且可以赋予权限。**

```java
@Test
@Transactional
@WithMockUser(username = "user-id-18163138155", authorities = "ROLE_TEACHER")
void should_import_student_success() throws Exception {

  ......

}
```

## 其他

@ PostConstruct 自定义初始化

@ PreDestroy 自定义销毁

