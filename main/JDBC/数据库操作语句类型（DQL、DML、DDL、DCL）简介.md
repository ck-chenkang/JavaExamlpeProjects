# 数据库操作语句类型（DQL、DML、DDL、DCL）简介

参考链接：

[[数据库操作语句类型（DQL、DML、DDL、DCL）简介](https://www.cnblogs.com/study-s/p/5287529.html)](https://www.cnblogs.com/study-s/p/5287529.html)

SQL语言共分为四大类：数据查询语言DQL，数据操纵语言DML，数据定义语言DDL，数据控制语言DCL。

## 数据查询语言DQL

数据查询语言DQL基本结构是由SELECT字句，FROM字句，WHERE子句组成的查询块：

SELECT <字段名表>

FROM <表或视图名>

WHERE <查询条件>

## 数据操纵语言DML

数据操纵语言DML主要有三种形式：

1）插入：INSERT

2）更新：UPDATE

3）删除：DELETE

## 数据定义语言DDL

数据定义语言DDL用来创建数据库中的各种对象——表、视图、索引、同义词、聚簇等；

如：

CREATE TABLE/VIEW/INDEX/SYN/CLUSTER

DDL操作是隐性提交的，不能roolback

## 数据控制语言DCL

数据控制语言DCL用来授予或回收访问数据库的某种特权，并控制数据库操纵实物发生的时间及效果，对数据库实行监视灯。

如：

1) GRANT：授权。


2) ROLLBACK [WORK] TO [SAVEPOINT]：回退到某一点。
回滚---ROLLBACK
回滚命令使数据库状态回到上次最后提交的状态。其格式为：
SQL>ROLLBACK;


3) COMMIT [WORK]：提交。


  在数据库的插入、删除和修改操作时，只有当事务在提交到数据
库时才算完成。在事务提交前，只有操作数据库的这个人才能有权看
到所做的事情，别人只有在最后提交完成后才可以看到。
提交数据有三种类型：显式提交、隐式提交及自动提交。下面分
别说明这三种类型。


(1) 显式提交
用COMMIT命令直接完成的提交为显式提交。其格式为：
SQL>COMMIT；


(2) 隐式提交
用SQL命令间接完成的提交为隐式提交。这些命令是：
ALTER，AUDIT，COMMENT，CONNECT，CREATE，DISCONNECT，DROP，
EXIT，GRANT，NOAUDIT，QUIT，REVOKE，RENAME。


(3) 自动提交
若把AUTOCOMMIT设置为ON，则在插入、修改、删除语句执行后，
系统将自动进行提交，这就是自动提交。其格式为：
SQL>SET AUTOCOMMIT ON；