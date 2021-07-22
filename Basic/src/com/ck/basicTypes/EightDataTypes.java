package com.ck.basicTypes;

public class EightDataTypes {

    public static void main(String[] args) {
        /**
         *  java共有8种基本类型，六种数字（4种整型，两种浮点），一种字符，一种布尔
         *  4种整型： byte，short，int，long
         *      默认值: 0, 0, 0，0L
         *      位数：8,16,32,64
         *      使用方法：
         *          byte a = 100;
         *          short a = 100;
         *          int a = 100;
         *          long a = 100L; 不用L会报错
         *          Long a = 100L;
         *  2种浮点：float，double
         *  字符： char
         *  布尔： boolean
         */
        // 获取位数、最大值、最小值
        // 数值类型才可获得最大值和最小值

        // 四种整型：

        // byte
        System.out.println(Byte.SIZE); // 8
        System.out.println(Byte.MAX_VALUE); // 127
        System.out.println(Byte.MIN_VALUE); // -128

        // Short
        System.out.println(Short.SIZE); // 16
        System.out.println(Short.MAX_VALUE); // 32767
        System.out.println(Short.MIN_VALUE); // -32768

        // int
        System.out.println(Integer.SIZE); // 32
        System.out.println(Integer.MAX_VALUE); // 2147483647
        System.out.println(Integer.MIN_VALUE); // -2147483648

        // long
        System.out.println(Long.SIZE); // 64
        System.out.println(Long.MAX_VALUE); // 9223372036854775807
        System.out.println(Long.MIN_VALUE); // -9223372036854775808

        // 两种浮点型
        // double
        System.out.println(Float.SIZE); // 32
        System.out.println(Float.MAX_VALUE); // 3.4028235E38
        System.out.println(Float.MIN_VALUE); // 1.4E-45





        // 使用方法：
        byte a1 = 100;
        short a2 = 100;
        int a3 = 100;
        long a4 = 100L;
        Long a5 = 100L;





    }
}
