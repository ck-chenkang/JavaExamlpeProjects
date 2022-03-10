# 各类MQTT代理服务器特性对比（译文）

​                                            

除了[Apache](https://so.csdn.net/so/search?q=Apache&spm=1001.2101.3001.7020) Apollo之外还有多款软件可作为MQTT代理服务器使用，同时这些代理服务器所支持的协议往往也并不是只有MQTT一种。本文就来看看各类MQTT代理服务器都有哪些特性。原文地址如下：

> ★ 
>
> https://github.com/mqtt/mqtt.github.io/wiki/server-support
>
> ”

译文如下:

------

本页试图记录各种MQTT服务器（代理）支持的特性。这是针对它们对MQTT的支持；其中很多服务器具有比MQTT更广泛的功能。

## **功能**

> ★ 
>
> **备注**
>  QoS 0：服务质量 0，最多传输一次。
>  QoS 1：服务质量1，至少传输一次。
>  QoS 2：服务质量2，仅仅传输一次。
>  auth：验证，身份验证授权。
>  bridge：桥接，服务器代理之间连接
>  $SYS：主题过滤器通配符，订阅后能够接收到所有以此通配符开头的主题的消息。
>  dynamic topics：动态主题
>  cluster：集群
>
> ”

| Server                          | QoS 0 | QoS 1 | QoS 2 | auth | bridge | $SYS | SSL  | dynamic topics | cluster | websockets | plugin system |
| :------------------------------ | :---- | :---- | :---- | :--- | :----- | :--- | :--- | :------------- | :------ | :--------- | :------------ |
| 2lemetry                        | ✔     | ✔     | ✔     | ✔    | ✔      | §    | ✔    | ✔              | ✔       | ✔          | ✘             |
| Apache ActiveMQ                 | ✔     | ✔     | ✔     | ✔    | ✘      | ✘    | ✔    | ✔              | ✔       | ✔          | ✔             |
| Apache ActiveMQ Artemis         | ✔     | ✔     | ✔     | ✔    | ✘      | ✘    | ✔    | ✔              | ✔       | ✔          | ✔             |
| Bevywise IoT Platform           | ✔     | ✔     | ✔     | ✔    | rm     | ✔    | ✔    | ✔              | ✔       | ✔          | rm            |
| emitter                         | ✔     | §     | ✘     | ✔    | ✘      | ✘    | ✔    | ✔              | ✔       | ✔          | ✘             |
| emqttd                          | ✔     | ✔     | ✔     | ✔    | ✔      | ✔    | ✔    | ✔              | ✔       | ✔          | ✔             |
| flespi                          | ✔     | ✔     | ✔     | ✔    | ✘      | ✘    | ✔    | ✔              | ✔       | ✔          | ✘             |
| GnatMQ                          | ✔     | ✔     | ✔     | ✔    | ✘      | ✘    | ✘    | ✔              | ✘       | ✘          | ✘             |
| HBMQTT                          | ✔     | ✔     | ✔     | ✔    | ✘      | ✔    | ✔    | ✔              | ✘       | ✔          | ✔             |
| HiveMQ                          | ✔     | ✔     | ✔     | ✔    | ✔      | ✔    | ✔    | ✔              | ✔       | ✔          | ✔             |
| IBM MessageSight                | ✔     | ✔     | ✔     | ✔    | ✘      | ✔    | ✔    | ✔              | §       | ✔          | ✘             |
| JoramMQ                         | ✔     | ✔     | ✔     | ✔    | ✔      | ✔    | ✔    | ✔              | ✔       | ✔          | ✔             |
| Mongoose                        | ✔     | ✔     | ?     | ?    | ?      | ?    | ?    | ?              | ?       | ?          | ?             |
| moquette                        | ✔     | ✔     | ✔     | ✔    | ?      | ?    | ✔    | ?              | rm      | ✔          | ✘             |
| mosca                           | ✔     | ✔     | ✘     | ✔    | ?      | ?    | ?    | ?              | ✘       | ✔          | ✘             |
| mosquitto                       | ✔     | ✔     | ✔     | ✔    | ✔      | ✔    | ✔    | ✔              | §       | ✔          | ✔             |
| MQTT.js                         | ✔     | ✔     | ✔     | §    | ✘      | ✘    | ✔    | ✔              | ✘       | ✔          | ✘             |
| MqttWk                          | ✔     | ✔     | ✔     | ✔    | ✔      | ?    | ✔    | ✔              | ✔       | ✔          | ✘             |
| RabbitMQ                        | ✔     | ✔     | ✘     | ✔    | ✘      | ✘    | ✔    | ✔              | ?       | ?          | ?             |
| RSMB                            | ✔     | ✔     | ✔     | ✔    | ✔      | ✔    | ✘    | ✔              | ✘       | ✘          | ?             |
| Software AG Universal Messaging | ✔     | ✔     | ✔     | ✔    | ✘      | ✘    | ✔    | ✔              | ✔       | rm         | ✘             |
| Solace                          | ✔     | ✔     | ✘     | ✔    | §      | ✔    | ✔    | ✔              | ✔       | ✔          | ✘             |
| SwiftMQ                         | ✔     | ✔     | ✔     | ✔    | ✔      | ✘    | ✔    | ✔              | ✔       | ✘          | ✔             |
| Trafero Tstack                  | ✔     | ✔     | ✔     | ✔    | ✘      | ✘    | ✔    | ✔              | ✘       | ✘          | ✘             |
| VerneMQ                         | ✔     | ✔     | ✔     | ✔    | ✔      | ✔    | ✔    | ✔              | ✔       | ✔          | ✔             |
| WebSphere MQ                    | ✔     | ✔     | ✔     | ✔    | ✔      | ✔    | ✔    | ✔              | ?       | ?          | ?             |

> ★ 
>
> 说明：✔表示支持，✘表示不支持，?表示未知，§表示支持但有限制，rm表示(roadmap)路线图规划中也就是计划支持。
>
> ”

**注：Apache Apollo的功能特性参考Apache ActiveMQ**

## **限制**

1. MQTT.js 接受提供用户名和密码的连接，但实际上并不对连接进行身份验证。
2. IBM MessageSight 支持一个高可用性模式能够提供集群的冗余优势，但是不支持针对MQTT的负载均衡。
3. 2lemetry 使用域，其中第一个主题段作为域的名称。 $SYS主题空间位于域中 。
4. Solace 没有提供一个代理之间专有的桥接方案.
5. mosquitto 的集群是在后端级别实现的(比如redis, amqp等)。
6. Software AG Universal Messaging提供主动/主动集群（通过专有协议）和桥接（通过专有协议）。

------

有了各类服务器代理的功能对比，就可以根据需要选择适合自己的服务器代理了。