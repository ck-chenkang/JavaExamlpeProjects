# @RequestMapping

它可以**注解类**也可以**注解方法**，注解类时标注请求的路径，标注方法时表示将特定的URL映射到指定的方法。@RequestMapping中有多个属性来进一步匹配HTTP请求到方法：

value，请求的URL路径，支持URL模板，正则表达式。和Ant路径表达式。例如：

![在这里插入图片描述](Imag/20181025131341159)

则/test/tom，/test/123等路径都会被匹配到Hello方法中来

![在这里插入图片描述](Imag/2018102513115872)

加入正则表达式[a-z]表示匹配一位所有的小写字母。所以类似/test/a；/test/b等路径都会被映射到Hello方法中。

![img](Imag/20181025132003417)

使用Ant路径表达式时。“ * ”单个星号表示匹配任意字符：/test/ *.html，/test/hello. *

" ** "用两个星号表示任意路径如图中的/**/hello。使用？匹配单个字符，如/test/?.html

- method，指定HTTP请求的方法，GET,POST,PUT,DELETE等，只有匹配才能调用。Spring提供了简化的写法：

```
@GetMapping
@PostMapping
@PutMapping等

所以
 @RequestMapping(value = "/test/hello",method = RequestMethod.GET)和
 @GetMapping("/test/hello")表示的意思是一样的
```



- consumes，指定HTTP请求头中的Content-Type。

  例如。设置Content-Type=application/json，则对应接收ajax请求。可以在ajax中设置contentType="application/json"确保数据类型。



## 参数相关注解

### @PathVariable：

用于获取URL中的参数：一般{ }中的变量名与方法中的形参名一致(可以不加@PathVariable注解)

![在这里插入图片描述](Imag/20181025154455461)

如果名称不一致则写成：否则不单单是获取不到参数，连方法都不执行！

![在这里插入图片描述](Imag/20181025154620205)

### @RequestParam

@RequestParam，用来处理Content-Type为application/x-www-form-urlencoded（默认类型如果不指定），使用value属性可以指定获取参数的key。

### @RequestBody

@RequestBody 注解一般用来处理非Content-Type: application/x-www-form-urlencoded编码格式的数据，在GET请求中没有请求体所以一般不适用，在post请求中必须指定Content-Type后才能使用它，如ajax请求指定发送格式为application/json。