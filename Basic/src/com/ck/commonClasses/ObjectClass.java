package com.ck.commonClasses;

// Object类
// 参考链接：https://blog.csdn.net/weixin_43232955/article/details/89705350
// 参考链接：https://blog.csdn.net/sugar_no1/article/details/88016599 【java常见常用类】
public class ObjectClass {
    public static void main(String[] args) {
        /**
         * Object类是所有类的父类，也就是说任何一个类在定义时如果没有明确地继承一个父类，
         * 那它就是Object类的子类，也就是说以下两种类定义的最终效果是完全相同的。
         *
         * class Book{
         *}
         * class Book extends Object{
         * }
         *
         * object 常用方法：
         * Object clone()	创建与该对象的类相同的新对象
         * boolean equals(Object)	比较两个对象是否相等。默认比较的是地址值(内容)。
         * void finalize()	当垃圾回收器确定不存在对该对象的更多引用时，对象的圾回收器调用该方法
         * Class getClass()	返回一个对象运行时的实例类（.class文件）
         * int hashCode()	返回该对象的散列码值
         * void notify()	激活等待在该对象的监视器上的一个线程
         * void notifyAll()	激活等待在该对象的监视器上的全部线程
         * String toString()	返回该对象的字符串表示，默认返回运行时类名+@+对象的hashCode的16进制数
         * void wait()	在其他线程调用此对象的 notify() 方法或 notifyAll() 方法前，导致当前线程等待
         *
         * clone()
         * 保护方法，实现对象的浅复制，只有实现了Cloneable接口才可以调用该方法，否则抛出CloneNotSupportedException异常。
         * 参考链接：https://www.jianshu.com/p/da8683e4d780 【cloneable接口】
         *
         * getClass()
         * final方法，返回Class类型的对象，反射来获取对象
         * String s = new String("");
         * System.out.println(s.getClass()); // class java.lang.String
         *
         *hashCode()
         * 如果两个对象的哈希码值不同，那这两个对象一定不等；
         * 如果两个对象的哈希码值相同，不能确保这两个对象一定相等。
         */

        Object o = new Object();


    }
}
