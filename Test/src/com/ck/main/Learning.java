package com.ck.main;

import javax.xml.crypto.Data;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 教程链接：https://www.liaoxuefeng.com/wiki/1252599548343744
 * 线程池
 * 静态代理，动态代理
 * lambda表达式
 * 静态内部类 局部内部类 匿名内部类，还有一个？
 * 静态代码块
 * 范型
 * 加密与安全
 * maven基础
 * json与xml
 * JDBC编程
 * 注解
 * 单元测试
 * 设计模式
 * 注解
 * 反射，去读注解
 * Java IO
 * date时间、日期
 * 类加载器，类初始化，类装载顺序
 */
public class Learning {

    static {
        System.out.println("执行了一次");
    }
    public static void main(String[] args) {
        Learning learning = new Learning();
        Learning learning1 = new Learning();
        Learning learning2 = new Learning();
    }
}

