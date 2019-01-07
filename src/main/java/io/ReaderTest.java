package io;

import java.io.IOException;
import java.io.InputStreamReader;

public class ReaderTest {

    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        char[] chars = new char[100];//创建字符数组
        inputStreamReader.read(chars);//将控制台中的数据读到字符数组
        String string = new String(chars);
        System.out.println("输入的字符串为： " + string);
        inputStreamReader.close();
    }
}
