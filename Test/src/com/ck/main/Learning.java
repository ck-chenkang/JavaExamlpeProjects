package com.ck.main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 教程链接：https://www.liaoxuefeng.com/wiki/1252599548343744
 * Java IO
 * 范型
 * 加密与安全
 * maven基础
 * json与xml
 * jdbcb变成
 * 单元测试
 * 设计模式
 * 注解
 *
 */
public class Learning {
    public static void main(String[] args) {
        DataOutputStream out = null;
        try {
            File file = new File("file.txt");
            out = new DataOutputStream(new FileOutputStream(file));

            out.writeBoolean(true);
            out.writeByte((byte)0x41);
            out.writeChar((char)0x4243);
            out.writeShort((short)0x4445);
            out.writeInt(0x12345678);
            out.writeLong(0x0FEDCBA987654321L);
            out.writeUTF("abcdefg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch(IOException e) {
            }
        }

    }
}