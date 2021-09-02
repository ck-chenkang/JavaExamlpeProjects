参考链接：[IntelliJ IDEA创建maven web项目（IDEA新手适用）](https://blog.csdn.net/czc9309/article/details/80304074)

1. 创建一个新项目或者module
2. 我这里是选择了module，并且我希望这个module存放的位置和我之前的module是同级别的目录
3. 选择Maven，勾选，Create from archetype，选择org.apache.maven.archetypes:maven-archetype-webapp
4. 选择next，位置：E:\Codes\JavaExamlpeProjects\MyFirstWebApp，名字 MyFirstWebApp，
5. Maven的G: com.ck，A：MyFirstWebApp，V：1.0-SNAPSHOT
6. Maven home directory：自己的maven安装目录
7. user settings file：勾选override后，选择 D:\apache-maven-3.8.1\conf\settings.xml
8. local repository选择本地maven仓库：D:\apache-maven-3.8.1\repository
9. 选择finish
10. 后面再下面Run窗口，会有一个BUILD SUCCESS出现
11. 打开project struct，查看modules里面是否有我们刚刚创建的项目
12. artifacts中也要有我们的项目
13. 没有的话，要点击加号，选择
14. 有的话，就开始配置tomcat了
15. 选择Run，选择Edit configuation，侧边栏，选择最外层目录，点击加号，选择tomcat server local
16. Name中输入，给tomcat起个名字
17. Application server 选择tomcat的地址
18. Deployment，加号，选择artifacts，选择刚刚的目录

