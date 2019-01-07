package io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriterTest {
    public static void main(String[] args) throws IOException {
        char[] chars = "登录密码是: 123456".toCharArray();//定义写入文件的数组登录密码
        int n=0;
        File file = new File("D:/test.txt");
        if(!file.exists()){//如果文件不存在则创建文件
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);//创建FileWriter对象
        fw.write(chars);//向文件中写入数据
        fw.close();

        FileReader fr = new FileReader(file);//创建FileReader对象
        char[] buf = new char[1024];
        while((n=fr.read(buf))!=-1){//将文件中的数据读入到数组buf
            System.out.println(n);//n等于13，证明read()方法是一个字符一个字符去读的
            //String str = buf.toString();
            String str = new String(buf);
            //String str = new String(buf,0,buf.length);
            System.out.println("文件中的内容为： \n" + str);
        }
    }
}
