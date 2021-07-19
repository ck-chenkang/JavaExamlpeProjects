package com.ck.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//自己写一个实现加减乘除的计算器
public class MyCompute {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入：");
        List list = new ArrayList();

        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            list.add(s);
            if(s == "="){
                break;
            }
        }

        for (Object s : list) {
            System.out.println(s);
            //            switch (s){
//                case "=":
//                    break;
//                case "+":
//                    break;
//                case "-":
//                    break;
//                case "*":
//                    break;
//                case  "/":
//                    break;
//                default :
//                    break;
//            }
        }


        scanner.close();
    }

}
