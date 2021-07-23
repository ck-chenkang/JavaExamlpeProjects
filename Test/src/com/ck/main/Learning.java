package com.ck.main;

// java 都是栈机制
// 匿名代码块 static代码快
// java面向对象，封装、继承、多态
//instanceof
//静态倒入包
//final
//static
//抽象类
//接口

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Learning {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        add(list);
        for (Integer j : list) {
            System.err.print(j+",");;
        }
        System.err.println("");
        System.err.println("*********************");
        String a="A";
        append(a);
        System.err.println(a);
        int num = 129;
        addNum(num);
        System.err.println(num);
    }

    static void add(List<Integer> list){
        list.add(100);
    }

    static void append(String str){
        str+="is a";
    }
    static void addNum(int a){
        a=a+10;
    }

}