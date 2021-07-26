package com.ck.commonClasses;

// File类
public class FileClass {

    public static void main(String[] args) {
        /**
         * File类用来获取文件磁盘，目录信息、文件名、修改日期、访问日期、访问权限、子目录结构等信息。
         * 不用来读取和写入文件，仅仅用来描述文件本身
         *
         * 一、构造方法：
         * File(String pathname) 通过将给定路径名字符串转换为抽象路径名来创建一个新 File 实例。
         * File(String parent,String child) 根据指定的父路径和文件路径创建一个新File对象实例
         * File(File parent,String child) 根据指定的父路径对象和文件路径创建一个新的File对象实例
         *
         * 1、
         *File file = new File("D:\\1.txt");
         *System.out.println(file); // D:\1.txt
         *
         *2、
         *File file1 =  new File("D:\\a","1.txt");
         *System.out.println(file1); // D:\a\1.txt
         *
         *3、
         *File parent = new File("D:\\a");
         *File file2 = new File(parent, "1.txt");
         *System.out.println(file2); // D:\a\1.txt
         *
         *4、
         *File file3 = new File(new File("D:\\a"),"1.txt");
         *System.out.println(file3); // D:\a\1.txt
         *
         *
         * 二、创建和删除：
         * boolean createNewFile();指定路径不存在该文件时创建文件，返回true 否则false
         * boolean mkdir（） 当指定的单击文件夹不存在时创建文件夹并返回true 否则false
         * boolean mkdirs（） 但指定的多级文件夹在某一级文件夹不存在时，创建多级文件夹并返回true 否则false
         * boolean delete（） 删除文件或者删除单级文件夹
         * 删除文件夹，这个文件夹下面不能有其他的文件和文件夹
         *
         * File file = new File("D:\\a\\1.txt");
         * File file1 = new File("1.txt");
         * boolean flag = file1.createNewFile();
         * System.out.println(flag);
         *
         * File file2 = new File("b");
         * boolean flag2 = file2.mkdir();
         * System.out.println(flag);
         *
         * File file3 = new File("c\\d\\e");
         * boolean d = file1.mkdir();
         * boolean c = file1.mkdirs();
         * System.out.println(d);
         * System.out.println(c);
         * File file4 = new File("c.txt");
         * System.out.println(file4.mkdir());
         *
         * File file5 = new File("b");
         * System.out.println(file2.delete());
         *
         *
         * 三、判断功能：
         * boolean exists() 判断指定路径的文件或文件夹是否为空
         * boolean isAbsolute() 判断当前路径是否是绝对路径
         * boolean isDirectory() 判断当前的目录是否存在
         * boolean isFile() 判断当前的目录是否是一个文件
         * boolean isHidden() 判断当前路径是否是一隐藏文件
         *
         * public static void main(String[] args) throws IOException {
         *   // method();
         *   //  method2();
         *   //   method3();
         *   //  method4();
         * }
         * //判断文件是否存在
         * public static void method() throws IOException {
         *     File file = new File("a.txt");
         *     file.createNewFile();
         *     boolean flag = file.exists();
         *     System.out.println(flag);
         * }
         * //判断当前路径是否为绝对路径
         * public static void method2() throws IOException{
         *     File file = new File("D:\\a\\1.txt");
         *     boolean flag = file.isAbsolute();
         *     System.out.println(flag);
         * }
         * //判断当前是文件夹还是文件
         * public static void method3() throws IOException{
         *     File file = new File("1.txt");
         *     File file1 = new File("b");
         *     file1.mkdir();
         *     boolean flag = file.isDirectory();
         *     boolean flag2 = file1.isFile();
         *     System.out.println(flag);
         *     System.out.println(flag2);
         * }
         * //判断当前路径是否为隐藏文件
         * public static void method4() throws IOException{
         *    File file =  new File("D:\\a\\1.txt");
         *     System.out.println(file.isHidden());
         * }
         *
         * 四、获取功能和修改名字功能：
         * File getAbsoluteFile() 获取文件的绝对路径，返回File对象
         * String getAbsolutePath() 获取文件的绝对路径，返回路径的字符串
         * String getParent() 获取当前路径的父级路径，以字符串形式返回该父级路径
         * String getName() 获取文件或文件夹的名称
         * String getPath() 获取File对象中封装的路径
         * long lastModified() 以毫秒值返回最后修改时间
         * long length() 返回文件的字节数
         * boolean renameTo(File dest) 将当前File对象所指向的路径修改为指定File所指向的路径
         *
         *
         * public static void main(String[] args) throws IOException {
         *    //  method();
         *    // method2();
         *    // method3();
         *    // method4();
         * }
         * public static void method(){
         *     File file = new File("D:\\a\\1.txt");
         *     File file1 = new File("1.txt");
         *     //以File对象返回的形式返回当前File对象所指向的绝对路径
         *     System.out.println(file1.getAbsoluteFile());
         *     //返回File对象所指向的绝对路径
         *     System.out.println(file1.getAbsolutePath());
         * }
         * public static void method2() throws IOException {
         *     File file = new File("a.txt");
         *     File file1 = new File("b","c.txt");
         *     System.out.println(file1.createNewFile());
         *
         *     File parent = new File("b");
         *     File file2 = new File(parent,"c.txt");
         *     if (!parent.exists()){
         *         parent.mkdirs();
         *     }
         *     System.out.println(file2.createNewFile());
         *     System.out.println(file2.getParent());
         *     System.out.println(file2.getParentFile());
         * }
         * public static void method3() throws IOException{
         *    File file = new File("1.txt");
         *    File file1 = new File("D:\\a\\1.txt");
         *    File file2 = new File("b");
         *
         *     System.out.println(file.getName());
         *     System.out.println(file1.getName());
         *     System.out.println(file2.getName());
         *
         *     System.out.println(file.getPath());
         *     System.out.println(file1.getPath());
         *     System.out.println(file2.getPath());
         *
         *     System.out.println(file.lastModified());
         *     Date date =  new Date(1556085068524L);
         *     System.out.println(date.toLocaleString());
         *
         *     System.out.println(file.length());
         *     System.out.println(file2.length());
         * }
         * public static void method4() throws IOException{
         *     File file = new File("a.txt");
         *     File file1 = new File("e.txt");
         *     System.out.println(file.renameTo(file1));
         * }
         *
         *
         * 五、其他获取功能：
         * String[] list(); 以字符串的形式返回当前路径下所有的文件和文件夹的名称
         * File[] listFile 以File对象的形式返回当前路径下的所有文件和文件夹名称
         * Static File[] listRoots() 获取计算机中的所有盘符
         *
         * public static void main(String[] args) {
         *     //method();
         *    // method2();
         *     //method3();
         * }
         *
         * public static void method(){
         *     File file = new File("b");
         *     File file1 = new File("D:\\QQ\\1916247350");
         *     File file2 = new File("e.txt");
         *     String[] files = file1.list();
         *     for (int i=0;i<files.length;i++){
         *         System.out.println(files[i]);
         *     }
         * }
         * public static void method2(){
         *     File file = new File("b");
         *     File file1 = new File("D:\\QQ\\1916247350");
         *     File file2 = new File("e.txt");
         *
         *     File[] files = file1.listFiles();
         *     for (File file3 : files) {
         *         System.out.println(file3.getName());
         *     }
         * }
         * public static void method3(){
         *     File[] files = File.listRoots();
         *     for (File file : files) {
         *         System.out.println(file);
         *     }
         * }
         *
         * 六、删除指定的目录及子目录：
         *
         *
         * public static void main(String[] args) {
         *     File file = new File("D:\\a");
         *     method(file);
         * }
         * //删除指定目录下的文件        *
         *
         * public static void method(File file){
         *     //删除自己所有的子文件和子目录
         *     //获取子文件和子目录
         *     if (file.isDirectory()){
         *         File[] files = file.listFiles();
         *         for (File file1 : files) {
         *             if (file1.isFile()){
         *                 System.out.println(file1.getName());
         *                 //干掉它
         *                 file1.delete();
         *             }else if (file1.isDirectory()){
         *                 //继续查看
         *                 method(file1);
         *             }
         *         }
         *         //干掉自己
         *         System.out.println(file.getName());
         *         file.delete();
         *     }
         * }
          */

        

    }

}
