package com.ck.basicTypes;

import sun.tools.tree.DoubleExpression;

// java8种基本数据类型及他们的封装
public class EightDataTypes {

    public static void main(String[] args) {
        /**
         *  java共有8种基本类型，六种数字（4种整型，两种浮点），一种字符，一种布尔
         *  4种整型： byte，short，int，long
         *  2种浮点：float，double
         *  字符： char
         *  布尔： boolean
         *  封装类型：Byte、Short、Integer、Long、Float、Double、Character、Boolean
         *  Java中不存在无符号的数，取值范围固定，不会随着系统的变化而变化
         *  原始类型按值传递，封装类型按引用传递
         *
         *  Java基本类型存储在栈中，因此它们的存取速度要快于存储在堆中的对应包装类的实例对象。
         *  从Java5.0（1.5）开始，JAVA虚拟机（JavaVirtual Machine）
         *  可以完成基本类型和它们对应包装类之间的自动转换。
         *  因此我们在赋值、参数传递以及数学运算的时候像使用基本类型一样使用它们的包装类，
         *  但这并不意味着你可以通过基本类型调用它们的包装类才具有的方法。
         *  另外，所有基本类型（包括void）的包装类都使用了final修饰，
         *  因此我们无法继承它们扩展新的类，也无法重写它们的任何方法。
         *  *
         * 基本类型的优势：数据存储相对简单，运算效率比较高
         *
         * 包装类的优势：有的容易，比如集合的元素必须是对象类型，
         *
         */


        // 四种整型：
        // byte
        System.out.println(Byte.SIZE); // 8
        System.out.println(Byte.MAX_VALUE); // 127
        System.out.println(Byte.MIN_VALUE); // -128
        byte a1 = 100;

        // Short
        System.out.println(Short.SIZE); // 16
        System.out.println(Short.MAX_VALUE); // 32767
        System.out.println(Short.MIN_VALUE); // -32768
        short a2 = 100;

        // int
        System.out.println(Integer.SIZE); // 32
        System.out.println(Integer.MAX_VALUE); // 2147483647
        System.out.println(Integer.MIN_VALUE); // -2147483648
        int a3 = 100;

        // long
        System.out.println(Long.SIZE); // 64
        System.out.println(Long.MAX_VALUE); // 9223372036854775807
        System.out.println(Long.MIN_VALUE); // -9223372036854775808
        long a4 = 100L;
        Long a5 = 100L;

        // 十六进制整型常量：以十六进制表示时，需以0x或0X开头，如0xff,0X9A。
        // 八进制整型常量：八进制必须以0开头，如0123，034。

        // 两种浮点型
        // float
        System.out.println(Float.SIZE); // 32
        System.out.println(Float.MAX_VALUE); // 3.4028235E38
        System.out.println(Float.MIN_VALUE); // 1.4E-45
        float a6 = 1.0f;
        float a7 = (float)1.0;

        // double，默认小数类型是double类型的
        System.out.println(Double.SIZE); // 64
        System.out.println(Double.MAX_VALUE); // 1.7976931348623157E308
        System.out.println(Double.MIN_VALUE); //4.9E-324

        // char
        char a8 = 'H';

        /**
         *  数据类型之间的转换
         *
         *  1、自动转换：
         *  1）运算的时候，会将小的数转换成大的数
         *  2）实参与形参，形参大于实参，将实参转换成形参
         *
         *  2、表达式的数据类型自动提升, 关于类型的自动提升，注意下面的规则：
         *  1）所有的byte,short,char型的值将被提升为int型；
         *  2）如果有一个操作数是long型，计算结果是long型；
         *  3）如果有一个操作数是float型，计算结果是float型；
         *  4）如果有一个操作数是double型，计算结果是double型；
         */


        // 3、包装类型转换
        // 利用包装类的 xxValue() 函数
        Float a9 = new Float(1.0);
        Double a10 = a9.doubleValue();
        System.out.println(a10);

        Integer a11 = a10.intValue();
        System.out.println(a11);

        /**
         * 字符串及其他类型间的转换
         *
         * ①调用类的串转换方法:X.toString();
         * 注意：需要先判断X是否是空指针
         *
         * ②自动转换:X+"";
         *
         * ③使用String的方法:String.volueOf(X);
         *
         */









    }
}
