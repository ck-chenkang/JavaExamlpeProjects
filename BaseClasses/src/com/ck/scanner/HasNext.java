package com.ck.scanner;

import java.util.Scanner;

public class HasNext {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("使用next方式接收：");

        if(scanner.hasNext()){
            String str = scanner.next();
            System.out.println("输出的内容为：" +str);
        }

        scanner.close(); //必须要有！！
    }
}


