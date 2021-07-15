package com.ck.scanner;

import java.util.Scanner;

//程序：输入很多个数，计算总和，计算输入数字的个数，计算平均值，输入字符结束
public class Example01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入要计算的数：");

        double in = 0.0;
        int counts = 0; //输入数字的个数
        double average = 0.0;
        double sum = 0.0;

        while (scanner.hasNextDouble()){
            in = scanner.nextFloat();
            counts++;
            sum += in;
        }

        average = sum/counts;

        System.out.println("和为：" + sum);
        System.out.println("个数为：" + counts);
        System.out.println("平均值为：" + average);

        scanner.close(); //必须要有！！
    }
}
