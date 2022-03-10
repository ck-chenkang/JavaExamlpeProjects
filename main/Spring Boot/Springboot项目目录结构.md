​                                            



### SpringBoot项目目录结构

- [一、代码层结构](#_4)
- - - [根目录：com.bajins](#combajins_5)
- [二、资源目录结构](#_38)
- - - [根目录：resources](#resources_40)
- [三、mybatis项目结构示例](#mybatis_57)



> ★ 
>
> 阿里巴巴Java开发手册见： https://gd.bajins.com/0:/Books/
>
> ”

# **一、代码层结构**

### 根目录：com.bajins

> ★ 
>
> 领域模型的相关命名：DO/BO/DTO/VO/DAO
>
> ”

- 启动类`BajinsApplication.java`推荐放在根目录com.bajins包下
- 数据[实体类](https://so.csdn.net/so/search?q=实体类&spm=1001.2101.3001.7020)`domain`：
  - jpa项目: com.bajins.domain
  - mybatis项目: com.bajins.entity
- 数据接口访问层`Dao`：
  - jpa项目： com.bajins.repository
  - mybatis项目： com.bajins.mapper
- 数据服务接口层`Service`：com.bajins.service
- 数据服务接口实现层`Service Implements`：com.bajins.service.impl
- 前端控制器层`Controller`：com.bajins.controller
- 工具类库`utils`：com.bajins.utils
- 配置类`config`：com.bajins.config
- 数据传输对象`dto`：com.bajins.dto

> ★ 
>
> 数据传输对象`Data Transfer Object`用于封装多个实体类`domain`之间的关系，不破坏原有的实体类结构
>
> ”

- 视图包装对象`vo`：com.bajins.vo

> ★ 
>
> 视图包装对象`View Object`用于封装客户端请求的数据，防止部分数据泄露`如：管理员ID`，保证数据安全，不破坏 原有的实体类结构
>
> ”

- 常量类`constant`：com.bajins.constant

# **二、资源目录结构**

### 根目录：resources

- 项目配置文件：resources/application.yml
- 静态资源目录：resources/static/

> ★ 
>
> 用于存放html、css、js、图片等资源
>
> ”

- 视图模板目录：resources/templates/

> ★ 
>
> 用于存放jsp、thymeleaf等模板文件
>
> ”

- [mybatis](https://so.csdn.net/so/search?q=mybatis&spm=1001.2101.3001.7020)映射文件：resources/mappers/
- mybatis配置文件：resources/spring-mybatis.xml

# **三、mybatis项目结构示例**

```
.
│  mvnw
│  mvnw.cmd
│  pom.xml
│  README.md
│  
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─bajins
    │  │          └─api
    │  │              │  BajinsApiApplication.java
    │  │              │      
    │  │              ├─config
    │  │              │  │  QuartzJob.java
    │  │              │  │  Swagger2.java
    │  │              │  │  TaskExecutorConfig.java
    │  │              │  │  
    │  │              │  └─websocket
    │  │              │          WebSocketConfig.java
    │  │              │          WebSocketHandler.java
    │  │              │          WebSocketInterceptor.java
    │  │              │          
    │  │              ├─constants
    │  │              │      WeChatConstants.java
    │  │              │      
    │  │              ├─mapper
    │  │              │      WechatLoginLogMapper.java
    │  │              │      WechatMsgMapper.java
    │  │              │      WechatUserOpenidMapper.java
    │  │              │      
    │  │              ├─domain
    │  │              │      WechatLoginLog.java
    │  │              │      WechatMsg.java
    │  │              │      WechatUserOpenid.java
    │  │              │      
    │  │              ├─service
    │  │              │  │  UserSignatureService.java
    │  │              │  │  WechatService.java
    │  │              │  │  WxMsgService.java
    │  │              │  │  
    │  │              │  └─impl
    │  │              │          WechatServiceImpl.java
    │  │              │          WxMsgServiceImpl.java
    │  │              │          
    │  │              ├─utils
    │  │              │  │  EmailUtil.java
    │  │              │  │  EncryptUtil.java
    │  │              │  │  StringUtil.java
    │  │              │  │          
    │  │              │  └─wechat
    │  │              │          SHA1.java
    │  │              │          WXBizMsgCrypt.java
    │  │              │          XMLParse.java
    │  │              │          
    │  │              ├─vo
    │  │              │      TemplateLibraryVO.java
    │  │              │      TemplateMessageVO.java
    │  │              │      
    │  │              └─controller
    │  │                      WechatController.java
    │  │                      WxMsgController.java
    │  │                      
    │  └─resources
    │      │  application.properties
    │      │  ehcache3.xml
    │      │  logback-spring.xml
    │      │  
    │      ├─mappers
    │      │      WechatLoginLogMapper.xml
    │      │      WechatMsgMapper.xml
    │      │      WechatUserOpenidMapper.xml
    │      │      
    │      ├─static
    │      └─templates
    └─test
        └─java
            └─com
                └─bajins
                    └─api
                            BajinsApiApplicationTests.java
                          
```