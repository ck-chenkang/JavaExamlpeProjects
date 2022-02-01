# 什么是IOC容器？为什么需要IOC容器？

参考链接：

[什么是IOC容器 为什么需要IOC容器](https://www.jianshu.com/p/1fa7b15a4828)

假设我们现在正在使用三层架构开发一个项目，其中有一个用户模块，包含登录、注册等功能。现在已经写好了`User`实体类和`UserDao`数据访问层：

```java
public class User
{
    private Integer id;
    private String username;
  
    private String password;

    // 以下是getter和setter方法
}

public interface UserDao
{
    // 查找用户
    User get(String username, String password);

    // 插入用户
    void insert(User user);
}

public class UserDaoImpl implements UserDao
{
    @Override
    public User getByUsername(String username, String password)
    {
        ...
    }

    @Override
    public void insert(User user)
    {
        ...
    }
}
```

`UserDao`封装了对数据库中的用户表进行操作。现在，需要一个`UserService`来封装登录、注册这两个业务逻辑：



```java
public interface UserService
{
    // 登录
    User login(String username, String password);

    // 注册
    void register(User user);
}

public class UserServiceImpl implements UserService
{
    @Override
    public User login(String username, String password)
    {
        User user = userDao.get(username, password); // userDao从哪里来？
        if (user == null)
        {
            // 用户名或密码错误
        }
        return user;
    }

    @Override
    public void register(User user)
    {
        userDao.insert(user); // userDao从哪里来？
    }
}
```

显然，`UserServiceImpl`需要一个`UserDao`的实例`userDao`来访问数据库，那么问题来了：这个`userDao`如何该获取呢？

 很多人都会用如下代码来获取`userDao`：



```java
public class UserServiceImpl implements UserService
{
    private UserDao userDao = new UserDaoImpl();
    ...
}
```

直接在`UserServiceImpl`内部new一个`UserDaoImpl`，看起来很方便，也可以正常工作，但是它存在一些问题：

1. 现在`UserServiceImpl`依赖于`UserDaoImpl`，如果这两个类是由两个不同的人开发的，则他们无法同时工作，因为在`UserDaoImpl`完成之前，`UserServiceImpl`无法通过编译
2. `UserServiceImpl`无法被测试，因为它与某个特定的`UserDao`实现类绑定在了一起，我们不能把它替换成一个用于单元测试的`MockUserDao`
3. 如果我们有多套数据库实现（即多个`UserDao`实现类），那么不能很方便地切换

为了解决上面几个问题，可以使用一种被称为**依赖注入**的技巧：



```java
public class UserServiceImpl implements UserService
{
    private UserDao userDao;

    // 构造函数注入
    public UserServiceImpl(UserDao userDao)
    {
        this.userDao = userDao;
    }
    ...
}

// 外部程序
UserService userService = new UserServiceImpl(new UserDaoImpl());
```

现在，`userDao`不是由`UserServiceImpl`本身构造，而是让外部程序通过`UserServiceImpl`的构造函数传入进来，这种操作称为**构造函数注入**。

 还可以使用另一种注入方式——**setter方法注入**：



```java
public class UserServiceImpl implements UserService
{
    private UserDao userDao;

    // setter方法注入
    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }
    ...
}

// 外部程序
UserService userService = new UserServiceImpl();
userService.setUserDao(new UserDaoImpl());
```

不论哪种注入方式，其基本逻辑都是一样的：**组件不负责创建自己依赖的组件，而是让外部程序创建依赖组件，然后通过构造函数或setter函数注入进来**。其实，这里也蕴含着**控制反转**的思想，因为**创建依赖组件的任务从组件内部转移到了外部程序**。

 使用了依赖注入，前面的几个问题就迎刃而解了，因为`UserServiceImpl`不再依赖`UserDao`的具体实现类，我们可以轻松地替换`UserDao`的实现。

 但是问题又来了：该由谁负责对象的组装呢？

 答案是：应该由应用的最外层负责对象的组装。例如，在三层架构中，可以在controller层负责service类的组装；如果我们的程序有main函数，也可以在main函数中进行相关组件的组装。



```java
public class UserController
{
    private UserService userService = new UserServiceImpl(new UserDaoImpl());

    public void handleLoginRequest(...)
    {
        userService.login(...);
        ...
    }
}
```

按照这种方式写程序，项目中的所有组件都按照依赖注入的方式管理自己的依赖，所有组件都由最外层统一组装，如果想替换掉某个组件的实现也很方便，看起来很美好。但是，当项目逐渐变得庞大，组件之间的依赖变多的时候，某个组件可能需要依赖于几十个大大小小的其它组件，创建这样的组件就成了一种折磨：



```java
// 创建一个复杂的组件
Component1 c1 = new Component1(new Component2(new Component3()), new Component4(new Component5(), new Component6()), new Component7());
```

如果这个组件只需要被使用一次，看起来还是可以接受，但是如果这个组件在很多地方都要使用，那么在每个使用的地方都需要写一遍上面创建的代码，这将会产生大量的代码冗余：



```java
public class A
{
    Component1 c1 = new Component1(new Component2(new Component3()), new Component4(new Component5(), new Component6()), new Component7());

    public void f1()
    {
        // 使用c1
        ...
    }
}

public class B
{
    Component1 c1 = new Component1(new Component2(new Component3()), new Component4(new Component5(), new Component6()), new Component7());

    public void f2()
    {
        // 使用c1
        ...
    }
}

public class C
{
    Component1 c1 = new Component1(new Component2(new Component3()), new Component4(new Component5(), new Component6()), new Component7());

    public void f3()
    {
        // 使用c1
        ...
    }
}
```

更糟糕的是，如果组件`c1`依赖的其中一个组件将要被替换，那么上面所有创建`c1`的代码都要修改，这简直是维护的噩梦！

 为了避免这个问题，可以把系统中所有的组件放进一个“容器”中统一管理：



```java
public class Container
{
    public static Component1 getComponent1()
    {
        ...
    }

    public static Component2 getComponent2()
    {
        ...
    }

    public static Component3 getComponent3()
    {
        ...
    }
    ...
}
```

然后，系统中所有需要使用组件的地方都通过`Container`类来获取：



```java
public class A
{
    Component1 c1 = Container.getComponent1();

    public void f1()
    {
        // 使用c1
        ...
    }
}

public class B
{
    Component1 c1 = Container.getComponent1();

    public void f2()
    {
        // 使用c1
        ...
    }
}

public class C
{
    Component1 c1 = Container.getComponent1();

    public void f3()
    {
        // 使用c1
        ...
    }
}
```

使用这种方法，不论是获取组件还是替换组件都非常方便。但是，现在`Container`类是通过Java代码来实现的，如果系统中的组件有任何变动，就需要修改代码，然后重新编译项目。在某些场景下，我们可能需要在项目运行时动态地添加、移除或者替换组件。

 为了实现组件的动态管理，可以将如何创建组件以及组件之间的依赖关系等信息写入配置文件中，然后项目启动时通过读取配置文件来动态创建所有组件，再放到`Container`中。这样就可以在项目运行时修改配置文件中的组件信息，而无需重新编译，甚至无需重启服务器：



```java
// 创建Container
Container container = new ContainerFactory("container.xml").create();

// 获取Component1
Component1 c1 = (Component1) container.create("c1");
```

其实，上面的Container就是一个简单的IOC容器。IOC表示**控制反转**，意思是**创建组件的工作不再由程序员控制，而是由IOC容器控制，程序员只负责告诉IOC容器如何创建某个组件，如果想要这个组件，直接从容器中取就是了**，这就是IOC容器的基本逻辑。

