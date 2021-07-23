package com.ck.common.Knowledge;

// 装箱和拆箱
// 参考链接：https://www.cnblogs.com/dolphin0520/p/3780005.html
public class BoxingAndUnboxing {

    /**
     * 基本类型和包装器类型的对应关系：
     * int     Integer
     * byte    Byte
     * short    Short
     * long     Long
     * float    Float
     * double   Double
     * char     Character
     * boolean      Boolean
     */
    public static void main(String[] args) {
        // 手动装箱
        Integer integer = new Integer(10);

        // 自动装箱
        Integer i = 10;
        int n = i; // 拆箱

        /**
         * 装箱和拆箱的实现方法：
         * 拆箱：通过 装箱对象.xxValue(); 其中，xx代表着基本数据类型，如int、doule
         * 装箱：通过 装箱类型.valueOf; 其中，装箱类型如：Integer、Float
         */
        // 装箱
        int a1 = 10;
        Integer a2 = Integer.valueOf(a1);

        // 拆箱
        Integer a3 = new Integer(10);
        int a4 = a3.intValue();


        /**
         *  面试常问的问题
         */

        // == 判断两个数，是否相等，主要看装箱函数valueOf做了什么，如果返回新的对象，则是否false，否则是true

        // 1、整型 ==
        // 在通过valueOf方法创建Integer对象的时候，
        // 如果数值在[-128,127]之间，便返回指向IntegerCache.cache中已经存在的对象的引用；
        // 否则创建一个新的Integer对象。
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;

        System.out.println(i1==i2); // true
        System.out.println(i3==i4); // false

        //2、 boolean ==
        Boolean b1 = false;
        Boolean b2 = false;
        Boolean b3 = true;
        Boolean b4 = true;

        System.out.println(b1==b2); // true
        System.out.println(b3==b4); // true


        /**
         *   谈谈Integer i = new Integer(xxx)和Integer i =xxx;这两种方式的区别。
         *
         * 　　当然，这个题目属于比较宽泛类型的。但是要点一定要答上，我总结一下主要有以下这两点区别：
         *
         * 　　1）第一种方式不会触发自动装箱的过程；而第二种方式会触发；
         *
         * 　　2）在执行效率和资源占用上的区别。第二种方式的执行效率和资源占用在一般性情况下要优于第一种情况（注意这并不是绝对的）。
         */

        // 1、当 "=="运算符的两个操作数都是 包装器类型的引用，则是比较指向的是否是同一个对象，
        // 而如果其中有一个操作数是表达式（即包含算术运算）则比较的是数值（即会触发自动拆箱的过程）。
        // 2、另外，对于包装器类型，equals方法并不会进行类型转换。
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;

        System.out.println(c==d); // true
        System.out.println(e==f); // false
        System.out.println(c==(a+b)); // true
        System.out.println(c.equals(a+b)); // true
        System.out.println(g==(a+b)); // true
        System.out.println(g.equals(a+b)); // false 1、equals会进行类型判断，2、进行值判断
        System.out.println(g.equals(a+h)); // true

    }

}



