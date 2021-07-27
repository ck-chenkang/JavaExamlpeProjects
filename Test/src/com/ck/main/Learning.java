package com.ck.main;

import javax.xml.crypto.Data;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 教程链接：https://www.liaoxuefeng.com/wiki/1252599548343744
 * Java IO
 * 线程池
 * 线程同步
 * 别人的jar包，怎么用，创建lib目录，add as library
 * 静态代理，动态代理
 * lambda表达式
 * 静态内部类 局部内部类 匿名内部类，还有一个？
 * 范型
 * 加密与安全
 * maven基础
 * json与xml
 * jdbcb变成
 * 单元测试
 * 设计模式
 * 注解
 * date时间、日期
 */
public class Learning {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

