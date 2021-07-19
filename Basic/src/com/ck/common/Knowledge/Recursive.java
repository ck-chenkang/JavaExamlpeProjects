package com.ck.common.Knowledge;

// 递归

/**
 * 边界条件
 * 前阶段
 * 返回阶段
 * 注意：java是栈机制，最好别用递归
 */
public class Recursive {
    public static void main(String[] args) {
        System.out.println(f1(5));
    }

    // 求n的阶乘
    // 5! = 5*4*3*2*1
    public static int f1(int n) {
        if (n == 1) {
            return 1;
        }else {
            return n*f1(n-1);
        }
    }
}
