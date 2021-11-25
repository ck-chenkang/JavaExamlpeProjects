# @Controller和@RestController和@ResponseBody

## @Controller

```
@Controller注解标注类的方法，return时会被视图处理器识别成静态文件的路径。默认为templates文件夹下。如return "test/hello"表示的是默认路径下的test文件夹中的名叫hello的文件，带上后缀名.html或btl等也可以识别。
```

## @ResponseBody

```
@ResponseBody可以标注方法也可以标注类，当标注方法时表示该方法的返回值会被解析成json（字符串会不会被转换），直接写入HTTP Response Body中，视图处理器将不会将return的参数识别成路径。当它标注类时，类中所有方法的返回值都将直接返回值到页面，相当于给所有的类都加上@ResponseBody注解。
```

## @RestController

```
@RestController是@Controller和@ResponseBody的结合体，只能注解类，return返回的值将被转换成json，字符串除外，直接写入HTTP相应体返回到页面中。
```

需要注意的是，只在类上注解@ResponseBody类中方法映射的URL并不能起作用，加上@Controller注解后才可以接受处理HTTP请求，同时加上 @RestController是@Controller和只写@ResponseBody是一样的效果