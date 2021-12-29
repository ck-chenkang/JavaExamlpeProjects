# spring boot 配置启动时数据库初始化

参考链接：我也没测，全当一个记录，后期用到时，知道有这么个玩意

[springboot 启动时数据库的初始化方法]: https://www.cnblogs.com/songxingzhu/p/9718240.html

一、简介

　　我们使用SpringBoot + JPA时，需要程序在启动时执行数据表的初始化或者数据库记录的初始化。一般数据表的初始化可以通过在Spring Boot的application.properties中进行配置spring.jpa.hibernate.ddl-auto=update来实现。但是数据记录的初始化，该怎么做呢？

　　下面，我们将使用SpringBoot2.0.4Release版本做实验

二、配置application.properties

　　连接字符串角配置：



```
#连接字符串
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://172.16.4.203:3306/mydatabase?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=123456
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.datasource.platform=org.hibernate.dialect.MySQL57Dialect
```

　　初始化数据：

```
#初始化数据
spring.datasource.schema=classpath:schema.sql
spring.datasource.data=classpath:data.sql
spring.datasource.sql-script-encoding=utf-8
spring.datasource.initialization-mode=ALWAYS
```

 

三、说明

　　因为SpringBoot在启动时，只有检测到spring.datasource.initialization-mode=ALWAYS配置，后再检测spring.datasource.schema之后，且配置的sql角本命令不为空，才会去执行schema和spring.datasource.data。因此需要在scheme.sql中随便写一句sql语句。

　　schema.sql

```
-- 这里是定义数据结构的SQL，每次运行都会执行，此文件不能为空，必须至少写一句Sql语句。
show tables;
```

　　data.sql

```
-- 下列角本是同步服务系统每次启动会自动执行的角本。编写时，请注意确保角本中不会变更现有记录。

-- SettingTab
INSERT setting_tab(id,name,value,description) SELECT 1, 'name', 'admin','用户名' FROM dual WHERE not EXISTS (select 1 from setting_tab where setting_tab.id = 1);
INSERT setting_tab(id,name,value,description) SELECT 2, 'key', '123456','密钥' FROM dual WHERE not EXISTS (select 1 from setting_tab where setting_tab.id=2);
```