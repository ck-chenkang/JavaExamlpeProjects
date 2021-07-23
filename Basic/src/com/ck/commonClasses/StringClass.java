package com.ck.commonClasses;

// java string
// 参考链接：https://blog.csdn.net/justloveyou_/article/details/52556427 【Java string 综述 上篇】
// 参考链接：https://blog.csdn.net/justloveyou_/article/details/60983034 【Java string 综述 下篇】
// 参考链接：https://blog.csdn.net/ifwinds/article/details/80849184 【深入理解Java string 类】
public class StringClass {
    public static void main(String[] args) {
        /**
         * 常量与变量：
         * 变量：内存地址不变,值可以改变的东西
         * 常量：内存地址不变，值也不可以改变
         *
         * string是常量
         */

        /**
         * String 声明
         */
        // string 的实例是一个对象，因此它的默认值是 null
        // new String()与 new String("")都是声明一个空串，不是null
        String str1 = new String(); // 在堆中创建一个字符串
        System.out.println(str1 == null); // false
        System.out.println(str1 == ""); // false
        System.out.println(str1.equals("")); // true

        String str2 = ""; // 在常量池中创建一个字符串
        System.out.println(str2 == null); // false
        System.out.println(str2 == ""); // true
        System.out.println(str2.equals("")); // true

        String str3 = new String(""); // 在常量池中创建一个字符串
        System.out.println(str3 == null); // false
        System.out.println(str3 == ""); // false
        System.out.println(str3.equals("")); // true

        /**
         * 字符串常用属性、方法
         *
         * string.length 返回字符串的长度，int类型。
         *
         * String.contains(";")——————>判断String里面是否包含;号。返回boolen类型
         * String.split(";")——————————>根据";"号来分割String,返回的是字符串数组
         * string.indexOf(";")——————————>查找";"在string出现的位置。没出现返回-1,出现返回出现的下标
         * string.subString(int a)————————————>如果是一个参数，从string[a]开始截取到最后
         * string.subString(int a,int b)—————————————————>从string[a]截取到string[b]。含a不包含b
         * string.toUpperCase()——————————>转大写
         * string.toLowerCase()————————————>转小写
         * string.endsWith(".txt")——————————>判断string是否是以.txt结尾
         * string.startsWith("a")————————————>判断string是否以a开头
         * string.charAt(int a)————————————>string[a]
         * string.compareTo("String")————————————>忽略大小写，然后做比较。返回的是boolen类型
         * string.replace('h','w');——————————>string中所有的h都替换成w
         * string.replaceFirst('h','w')————————>string第一个h替换成w
         * string.trim() ——————————————————> 去掉起始和结尾的空格
         * string.toCharArray()————————————>把string转成char类型数组
         */



        /**
         * 字符串常量池
         * Java虚拟机(JVM)中存在着一个字符串常量池，其中保存着很多String对象，
         * 并且这些String对象可以被共享使用，因此提高了效率。之所以字符串具有字符串常量池，
         * 是因为String对象是不可变的，因此可以被共享。
         * 字符串常量池由String类维护，我们可以通过intern()方法使字符串池手动入池。
         */

        String s1 = "abc";
        //↑ 在字符串池创建了一个对象
        String s2 = "abc";
        //↑ 字符串pool已经存在对象“abc”(共享),所以创建0个对象，累计创建一个对象
        System.out.println("s1 == s2 : "+(s1==s2));
        //↑ true 指向同一个对象，
        System.out.println("s1.equals(s2) : " + (s1.equals(s2)));
        //↑ true  值相等

        /**
         * 关于new String("")
         */

        String s3 = new String("abc");
        //↑ 创建了两个对象，一个存放在字符串池中，一个存在与堆区中；
        //↑ 还有一个对象引用s3存放在栈中
        String s4 = new String("abc");
        //↑ 字符串池中已经存在“abc”对象，所以只在堆中创建了一个对象
        System.out.println("s3 == s4 : "+(s3==s4));
        //↑false   s3和s4栈区的地址不同，指向堆区的不同地址；
        System.out.println("s3.equals(s4) : "+(s3.equals(s4)));
        //↑true  s3和s4的值相同
        System.out.println("s1 == s3 : "+(s1==s3));
        //↑false 存放的地区都不同，一个方法区，一个堆区
        System.out.println("s1.equals(s3) : "+(s1.equals(s3)));
        //↑true  值相同

        /**
         * 字符串连接符“+”
         * String str4 = "ab";  //1个对象
         * String str5 = "cd";  //1个对象
         * String str6 = str4+str5;
         * String str7 = "abcd";
         * System.out.println("str4 = str5 : " + (str6==str7)); // false
         *
         * 我们看这个例子，局部变量 str4，str5 指向字符串常量池中的两个对象。
         * 在运行时，第三行代码(str4+str5)实质上会被分解成五个步骤，分别是：
         *
         * 　(1). 调用 String 类的静态方法 String.valueOf() 将 str4 转换为字符串表示；
         *
         * 　(2). JVM 在堆中创建一个 StringBuilder对象，同时用str4指向转换后的字符串对象进行初始化；　
         *
         * 　(3). 调用StringBuilder对象的append方法完成与str5所指向的字符串对象的合并；
         *
         * 　(4). 调用 StringBuilder 的 toString() 方法在堆中创建一个 String对象；
         *
         * 　(5). 将刚刚生成的String对象的堆地址存赋给局部变量引用str6。
         *
         * 而引用str7指向的是字符串常量池中字面值”abcd”所对应的字符串对象。
         * 由上面的内容我们可以知道，引用str6和str7指向的对象的地址必定不一样。这时，内存中实际上会存在五个字符串对象：
         * *  三个在字符串常量池中的String对象、
         * * 一个在堆中的String对象和一个在堆中的StringBuilder对象。
         *
         */




        /**
         * 字符串的编译期优化
         *
         * String str10 = "ab" + "cd";  //1个对象
         * String str11 = "abcd";
         * System.out.println("str1 = str11 : "+ (str10 == str11));   // true
         *
         * final String str8 = "cd";
         * String str9 = "ab" + str8;
         * String str89 = "abcd";
         * System.out.println("str9 = str89 : "+ (str9 == str89));     // true
         * //↑str8为常量变量，编译期会被优化
         *
         * String st6 = "b";
         * String st7 = "a" + str6;
         * String st67 = "ab";
         * System.out.println("str7 = str67 : "+ (str7 == st67));     // false
         * //↑str6为变量，在运行期才会被解析。
         *
         * //常量+字面值”的组合，其值在编译的时候就能够被确定了
         * //Java 编译器对于含有 “String引用”的组合，
         * // 则在运行期会产生新的对象 (通过调用StringBuilder类的toString()方法)，因此这个对象存储在堆中。
         *
         */

        /**
         * 编译时优化与字符串连接符的本质
         *
         * public class Test2 {
         *     public static void main(String[] args) {
         *         String s = "a" + "b" + "c";
         *         String s1 = "a";
         *         String s2 = "b";
         *         String s3 = "c";
         *         String s4 = s1 + s2 + s3;
         *
         *         System.out.println(s);
         *         System.out.println(s4);
         *     }
         * }
         *
         * 反编译：
     *    //将上述 Test2 的 class 文件反编译
         * public class Test2
         * {
         *     public Test2(){}
         *     public static void main(String args[])
         *     {
         *         String s = "abc";            // 编译期优化
         *         String s1 = "a";
         *         String s2 = "b";
         *         String s3 = "c";
         *
         *         //底层使用 StringBuilder 进行字符串的拼接
         *         String s4 = (new StringBuilder(String.valueOf(s1))).append(s2).append(s3).toString();
         *         System.out.println(s);
         *         System.out.println(s4);
         *     }
         * }
         *
         * 由上面的分析结果，我们不难推断出 String 采用连接运算符（+）效率低下原因分析，形如这样的代码：
         * public class Test {
         *     public static void main(String args[]) {
         *         String s = null;
         *             for(int i = 0; i < 100; i++) {
         *                 s += "a";
         *             }
         *     }
         * }
         *
         * 会被编译器编译为：
         * public class Test
         * {
         *     public Test(){}
         *     public static void main(String args[])
         *     {
         *         String s = null;
         *         for (int i = 0; i < 100; i++)
         *             s = (new StringBuilder(String.valueOf(s))).append("a").toString();
         *     }
         * }
         * 也就是说，每做一次 字符串连接操作 “+” 就产生一个 StringBuilder 对象，然后 append 后就扔掉。
         *
         * 下次循环再到达时，再重新 new 一个 StringBuilder 对象，然后 append 字符串，如此循环直至结束。
         *
         * 事实上，如果我们直接采用 StringBuilder 对象进行 append 的话，我们可以节省 N - 1 次创建和销毁对象的时间。
         *
         * 所以，对于在循环中要进行字符串连接的应用，一般都是用StringBulider对象来进行append操作。
         */

        /**
         * 三大字符串类 ： String、StringBuilder 和 StringBuffer
         *
         * 1. String 与 StringBuilder
         *
         * 　　简要的说， String 类型 和 StringBuilder 类型的主要性能区别在于 String 是不可变的对象。
         *
         *    事实上，在对 String 类型进行“改变”时，实质上等同于生成了一个新的 String 对象，
         *
         *    然后将指针指向新的 String 对象。由于频繁的生成对象会对系统性能产生影响，特别是当内存中没有引用指向的对象多了以后，
         *
         *    JVM 的垃圾回收器就会开始工作，继而会影响到程序的执行效率。所以，对于经常改变内容的字符串，最好不要声明为 String 类型。
         *
         *    但如果我们使用的是 StringBuilder 类，那么情形就不一样了。因为，我们的每次修改都是针对 StringBuilder 对象本身的，
         *
         *    而不会像对String操作那样去生成新的对象并重新给变量引用赋值。所以，在一般情况下，推荐使用 StringBuilder ，
         *
         *    特别是字符串对象经常改变的情况下。
         *
         * 　　在某些特别情况下，String 对象的字符串拼接可以直接被JVM 在编译期确定下来，这时，StringBuilder 在速度上就不占任何优势了。
         *
         * 　　因此，在绝大部分情况下， 在效率方面：StringBuilder > String .
         *
         * 2.StringBuffer 与 StringBuilder
         *
         * 　　首先需要明确的是，StringBuffer 始于 JDK 1.0，而 StringBuilder 始于 JDK 5.0；此外，从 JDK 1.5 开始，
         *
         *    对含有字符串变量 (非字符串字面值) 的连接操作(+)，JVM 内部是采用 StringBuilder 来实现的，而在这之前，
         *
         *    这个操作是采用 StringBuffer 实现的。
         *
         * 　　JDK的实现中 StringBuffer 与 StringBuilder 都继承自 AbstractStringBuilder。
         *
         *    AbstractStringBuilder的实现原理为：AbstractStringBuilder中采用一个 char数组 来保存需要append的字符串，
         *
         *    char数组有一个初始大小，当append的字符串长度超过当前char数组容量时，则对char数组进行动态扩展，
         *
         *    即重新申请一段更大的内存空间，然后将当前char数组拷贝到新的位置，因为重新分配内存并拷贝的开销比较大，
         *
         *    所以每次重新申请内存空间都是采用申请大于当前需要的内存空间的方式，这里是 2 倍。
         *
         * 　　StringBuffer 和 StringBuilder 都是可变的字符序列，但是二者最大的一个不同点是：StringBuffer 是线程安全的，而 StringBuilder 则不是。StringBuilder 提供的API与StringBuffer的API是完全兼容的，即，StringBuffer 与 StringBuilder 中的方法和功能完全是等价的，但是后者一般要比前者快。因此，可以这么说，StringBuilder 的提出就是为了在单线程环境下替换 StringBuffer 。
         *
         * 　　在单线程环境下，优先使用 StringBuilder。
         */


    }

}
