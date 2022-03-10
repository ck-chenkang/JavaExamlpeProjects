# springboot中配置文件及yml文件的使用  

[springboot中配置文件及yml文件的使用](https://blog.csdn.net/weixin_44853669/article/details/104642316?ops_request_misc=&request_id=&biz_id=102&utm_term=springboot%20yml&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-3-104642316.pc_search_result_control_group&spm=1018.2226.3001.4187)                                         

[springboot启动时是如何加载配置文件application.yml的](https://blog.csdn.net/chengkui1990/article/details/79866499?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522164688222916780271527427%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=164688222916780271527427&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-2-79866499.pc_search_result_control_group&utm_term=springboot+yml%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6&spm=1018.2226.3001.4187)

我们都知道springboot可以自动帮助我们配置好多东西，但是如果我们想要改变那些自动配置的属性，可以吗？当然可以，我们可以通过配置文件来更改。springboot默认的全局配置文件有两个，一个是application.[properties](https://so.csdn.net/so/search?q=properties&spm=1001.2101.3001.7020)，另一个是application.yml。根据官方文档的解释，yml不是一个标记文档，那如何理解yml不是标记文档呢？我们先来看看啥是标记文档？比如xml就是一个标记文档，比如我们在xml中配置一个端口号，

```markup
<server>
  <port>8820</port>
  <path>a/b/c</path>
</server>
```

像上面这些配置，假如我们在yml中写的话，应该

```yaml
server:
     port: 8820
     path: a/b/c
```

注意，在yml中，如果 **:** 后面有值得话，必须加一个空格，整理成公式就是k:空格v。
 下图是我们更改端口号后的测试；
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70.png)
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134655039.png)

## 实体类和配置文件中的值绑定

我们先来建立两个实体类，一个是学生，一个是宠物，然后通过yml来给学生的属性赋值；

```java
package com.example.demo.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Student {
	
	 private String name;
	 private int age;
	 private boolean sex;
	 private Date birthday;
	 //{province:广东,city:深圳,zone:龙华区}
	 private Map<String,Object> location;
	 private String[] hobbies;
	 private List<String> skills;
	 private Pet pet;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Map<String, Object> getLocation() {
		return location;
	}
	public void setLocation(Map<String, Object> location) {
		this.location = location;
	}
	public String[] getHobbies() {
		return hobbies;
	}
	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	@Override
	public String toString() {
	 
		return "名字是:"+this.name+",年龄是:"+this.age+",性别是:"+this.sex+",生日是"+this.birthday
				+",出生地是"+this.location+",爱好是"+this.hobbies+",技能是"+this.skills+",宠物名字是"+this.pet.getNickName()
				+",宠物strain是"+this.pet.getStrain();
	}

}

package com.example.demo.entity;

public class Pet {
	
	 private String nickName;
	 private String strain;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getStrain() {
		return strain;
	}
	public void setStrain(String strain) {
		this.strain = strain;
	}
	 

}

server:
    port: 8820
    
student:
    #简单类型
   name: zs
   age: 23
   sex: true
   birthday: 2020/03/03 
   #Map<String,Object>类型
   location: {province: 广东,city: 深圳,zone: 龙华区} 
   #String[]  数组类型
   hobbies:
       - 足球
       - 篮球
   #List<String> 集合类型写法    
   skills:
        - 编程
        - 金融
   #引用对象类型     
   pet:
       nickName: wc
       strain: hsq    
          
```

这里我们就已经把对象的属性的值都赋值完毕了，接下来我们还需要在实体类上配置一下，然后可以。
 如图所示，我们在实体类中加入下面两个注解就可以了
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654818.png)
 然后我们在测试类里面new一个student对象，然后打印出来
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654789.png)
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134655060.png)
 接下来，我们来看几个细节：
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654885.png)
 由上图可以看出来，在yml文件中，可以默认不加引号。
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134655028.png)
 从上图中可以看出来，转义符在双引号是好使的，在单引号和不加引号的情况下，是不好使的。
 Map<String,Object>类型,String[] 数组类型,List 集合类型第二种写法 都有第二种写法

```yaml
server:
    port: 8820
    
student:
    #简单类型
   name: zs
   age: 23
   sex: true
   birthday: 2020/03/03 
   #Map<String,Object>类型
   #location: {province: "广\n东2",city: '深\n圳2',zone: 龙华\n区2} 
   #Map<String,Object>类型第二种写法
   location:
      province: 广东6
      city: 深证6
      zone: 龙华区6
   #String[]  数组类型
   #hobbies:
       #- 足球
       #- 篮球
   # String[]  数组类型第二种写法
      hobbies: [足球6,篮球6]
   #List<String> 集合类型写法    
   #skills:
       # - 编程
       #- 金融
   # List<String> 集合类型第二种写法 
      skills: [编程6,金融6]
        
   #引用对象类型     
   pet:
       nickName: wc
       strain: hsq    
          
```

![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654831.png)
 那yml中的引用对象是否可以有第二种写法呢？答案是当然可以啦，

```yaml
 #引用对象类型     
   pet: {nickName: wc66,strain: hsp66}
       #nickName: wc
       #strain: hsq    

1234
```

效果如下图所示：
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654751.png)
 简单总结就是，集合，数组用中括号，对象和map类型使用大括号。
 当然，上面总结是指行内写法(就是写在一行)。
 而且，中括号是可以省略的，但是大括号不可以省略；
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134655064.png)
 如果，我们想要在application.properties配置对象属性值，该怎么配置呢？
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654770.png)
 在上面中，我们是通过@ConfigurationProperties(prefix=“student”)将代码中的对象和配置文件中的对象属性值结合到一起的，其实当我们在代码中使用@ConfigurationProperties(prefix=“student”)的时候，代码会去application.properties或者application.yml中去寻找我们要找属性值。
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134655030.png)
 当然我们也可以直接在代码中，用value注解给属性赋值，
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134655004.png)
 这里有一个小细节，就是当我们同时使用@ConfigurationProperties(prefix=“student”)和@Value注解的时候，会优先使用哪个呢？
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654825.png)
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200304213923958.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70)
 结合上面两张图片，答案已经很明显了，优先使用@ConfigurationProperties(prefix=“student”)，所以他们也可以互补使用，比如某一个属性值在配置文件中没有配置，如果@Value中有的话，就可以互补了。
 @ConfigurationProperties 和@Value有五点区别；
 一、@ConfigurationProperties支持批量注入，而 @Value不支持。
 二、@ConfigurationProperties支持松散语法，而@Value不支持。
 三、@Value支持SpringEL语法，而@ConfigurationProperties不支持。
 四、@ConfigurationProperties支持JSR303数据校验，而@Value不支持。
 五、@ConfigurationProperties支持注入复杂类型，而@Value不支持。
 注意这里的复杂类型是指除了8个基本类型/String/Date的其他类型。
 关于松散语法，啥是松散语法呢？
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654987.png)
 说白了，松散语法其实就是指我们可以用- 来替代驼峰命名法。
 那啥是SpringEL表达式呢？
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654860.png)
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654857.png)
 那如何理解支持JSR303数据校验呢？请看下图：
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654983.png)
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134655008.png)
 springboot默认只会加载application.properties/application.yml这两个配置文件，但是如果我们想要调皮一下，想要给application换个名字，这时候就需要用到@PropertySource。
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654936.png)
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654752.png)
 在springboot的自动配置中，它只会认识application.properties和application.yml这两个配置文件。如果我们想要增加配置文件的话，可以吗？当然可以啦，但是我们需要用到一个注解@ImportResource
 首先，我们随便新建一个业务逻辑层；
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134655062.png)
 然后，再新建一个[xml](https://so.csdn.net/so/search?q=xml&spm=1001.2101.3001.7020)文件，把service放进去，
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134655049.png)
 然后把配置文件通过@ImportResource配置好
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654908.png)
 接下来，我们去test里面测试
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654859.png)
 总结：在sprngboot中如果需要识别我们自己的配置文件，则需要在spring boot主配置类上 通过@ImportResource指定配置文件的路径
 我们写配置文件，一般都有两种方式，一种是像上面那样在xml文件中写，然后再配置xml的路径。还有一种方式是用注解，其实就是配置类。
 当然，官方也是偏向于支持用配置类。下面是示例
 下面是我们写的一个配置类
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654931.png)
 下面是测试结果
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654727.png)

## 占位符表达式

接下来，我们看下spring boot全局配置文件中的 占位符表达式
 常见的随机占位符如下
 **${random.value}
 ${random.int}
 ${random.int(max)}
 ${random.int(min,max)}
 ${random.long}
 ${random.long(max)}
 ${random.long(min,max)}
 ${random.uuid}**

```
下面我们来演示下${random.int}
```

![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654958.png)
 上图是在配置文件中的配置
 第一次执行结果如下图
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654979.png)
 连续第二次执行结果
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134655000.png)
 接下来，我们来演示下${random.value}
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/2020030523114825.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70)
 第一次执行结果
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134655230.png)
 第二次执行结果
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654777.png)
 接下来我们来看下application.yml如何引用application.properties中的值
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134655101.png)
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134655164.png)
 那假如我们写错了，会报错吗？
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134654882.png)
 显然不会报错，会我们的引用原样输出出来，我们可以给个默认值
 ![在这里插入图片描述](Imag/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg1MzY2OQ==,size_16,color_FFFFFF,t_70-20220310134655098.png)

## springboot是如何加载配置文件application.yml的

今天启动springboot时，明明在resources目录下面配置了application.yml的文件，但是却读不出来，无奈看了下源码，总结一下springboot查找配置文件路径的过程，能力有限，欢迎各位大牛指导！！！

spring加载配置文件是通过listener监视器实现的，在springboot启动时：

![img](Imag/70-20220310135241571.png)



在容器启动完成后会广播一个SpringApplicationEvent事件，而SpringApplicationEvent事件是继承自ApplicationEvent时间的，代码如下：

![img](https://img-blog.csdn.net/20180409164755721?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2NoZW5na3VpMTk5MA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)



![img](Imag/70-20220310135241456.png)






由于ConfigFileApplicationListener监听器实现了SmartApplicationListener接口，而SmartApplicationListener接口继承了ApplicationListener<ApplicationEvent>接口，所以能监听到上面广播出来的SpringApplicationEvent事件，类的继承图如下：

![img](Imag/70-20220310135241500.png)

SmartApplicationListener是继承自ApplicationListener：

![img](Imag/70-20220310135241546.png)

在ConfigFileApplicationListener中监听事件的方法：

![img](Imag/70-20220310135241580.png)

postProcessEnvironment方法是查找application.yml配置文件的入口方法：

![img](https://img-blog.csdn.net/20180409151335351?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2NoZW5na3VpMTk5MA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)



在load方法里面才是真正查找配置文件的过程：

![img](Imag/70-20220310135241567.png)

默认先读取的是location，然后是配置文件的名字“application”，最后才是文件类型“properties”或者“yml”；

localtion有多种，可以自行debug：有file:./和file:./config和classpath等目录；

文件类型默认的有四种：properties、[xml](https://so.csdn.net/so/search?q=xml&spm=1001.2101.3001.7020)、yml、yaml


最后查找的具体路径：location + name + **"-"** + profile + **"."** + ext



![img](Imag/70-20220310135241554.png)

根据拼出来的路径去查找配置文件，一般配置文件都放在classpath目录下面，当读取到classpath目录下的配置文件的时候，程序去加载配置文件：

![img](Imag/70-20220310135241596.png)

![img](Imag/70-20220310135241597.png)

当加载配置文件时，程序先会读取**配置文件的spring.profiles.active属性，确定加载什么环境的配置文件（我是加载dev的）：**

**![img](Imag/70-20220310135241613.png)**

然后在读取到的配置文件的属性加载到profiles队列中重新加载配置文件，代码如下，所以任何项目都必须现有一个基础的配置文件，如application.yml,然后在这个配置文件里面有一个active属性；

![img](Imag/70-20220310135241605.png)

程序会先拿到这个属性，放到profiles属性中，重新去加载配置文件如application-dev.yml，程序如下：

![img](Imag/70-20220310135241621.png)

![img](Imag/70-20220310135241719.png)



注：这篇文章是为了解决配置文件找不到而写的，是为了解决问题而写的，主要是解析查找配置文件路径的过程，具体的解析配置文件的过程，读者可以细看源码，最后我也找到了配置文件为什么找不到的原因，主要是因为idea编译时，classpath路径下面没有application.yml文件，复制配置文件到classpath目录下，问题就解决了！