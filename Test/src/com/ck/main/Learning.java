package com.ck.main;

import javax.xml.crypto.Data;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 教程链接：https://www.liaoxuefeng.com/wiki/1252599548343744
 * 加密与安全
 * maven基础
 * json与xml
 * JDBC编程
 * 注解
 * 单元测试
 * 设计模式
 * 文件读取、写入
 * date时间、日期
 * 类加载器，类初始化，类装载顺序
 * 函数式编程，函数式接口
 * new关键字
 * jvm面试问题：
 *  谈谈你对jvm的理解？ java8虚拟机和之前的变化更新
 *  什么是oom，什么是栈溢出stackoverflowerror？怎么分析
 *  jvm的常用调优参数有哪些
 *  内存快照如何抓取，怎么分析Dump文件
 *  谈谈jvm中，你对类加载器的认识
 * 1、jvm的位置
 * 2、jvm的体系结构
 * 3、类加载器
 * 4、双亲委派机制
 * 5、沙箱安全机制
 * 6、Native
 * 7、PC寄存器
 * 8、方法区
 * 9、栈
 * 10、三种jvm：sun公司、orcale、IBM
 * 11、堆
 * 12、新生区、老年区
 * 13、永久区
 * 14、堆内存调优
 * jprofilter安装
 * java Dump文件
 * 15、Gc
 *  1、常用算法
 * 16、JMM
 * jvm思维导图
 * 异常
 *
 */
public class Learning {


    public static void main(String[] args) {
        long l = Runtime.getRuntime().maxMemory();
        System.out.println((double)l/1024/1024/1024);

    }
}

