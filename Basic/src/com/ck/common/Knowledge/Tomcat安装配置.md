## Tomcat安装

1. 百度搜索Tomcat，第一个，进去之后，左侧侧边栏，选择下载，有不同的版本，10，9等，我选择了10，
   windows环境下，我选择了apache-tomcat-10.0.-windows-x64.zip
2. 解压，并放好
3. 进入安装包bin目录下，cmd，输入startup.bat
4. 浏览器输入127.0.0.1:8080，看到了tomcat的页面
5. 在Tomcat 4以前，用的就是TOMCAT_HOME来表示Tomcat的安装目录，在Tomcat 4以后，采用了新的Servlet容器Catalina，所以环境变量的名字也改为了CATALINA_HOME。
6. 系统环境变量添加CATALINA_HOME并指定Tomcat的目录
7. Path环境变量添加%CATALINA_HOME%\bin
8. 在任意位置cmd，输入startup.bat可以看到tomcat启动了就是成功了
9. 输入shutdown.bat停止tomcat

## IDEA配置tomcat

参考 创建MyFirstWebapp.md

## Tomcat目录结构

- bin 存放Tomcat的命令，启动、停止
- conf 配置目录 server.xml 设置端口号、域名、ip、默认加载的项目
  - web.xml 设置Tomcat支持的文件类型
  - context.xml 配置数据源
  - tomcat-user.xml配置管理tomcat的用户与权限
- lib Tomcat运行时需要的jar包
- logs 目录存放日志文件
- tem 运行过程中产生的临时文件
- webapps 存放应用程序
- works 存放tomcat在运行时的编译后文件，清空work目录，然后重启tomcat，可以达到清除缓存的作用