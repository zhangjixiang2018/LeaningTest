/**
 * 压缩流本质上是一个输出流，解压缩流本质上是一个输入流。
 * 不管是压缩还是解压都要通过一个中间的字节数组，
 * 压缩是将这个数组中的数据，输出到某一个地方，这个地方可以是文件输出流，字节数组输出流，
 * 具体要看压缩刘套接在那里，这个数组也可以通过多种方式得到，比如从磁盘文件读入的数组。
 * 解压缩：
 * 通过解压缩流将数据先解压到一个字节数组，再把这个字节数组中的数据转到别的地方，比如磁盘文件，，
 * 字节数组输出流。
 */
package io.zip;

import javafx.scene.shape.Path;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.sun.javafx.fxml.expression.Expression.add;

public class zipTest {

    public static void main(String[] args) {

        try {
            FileOutputStream fos = new FileOutputStream("D:/java/hadoop/Archiver/test1/yasuo.xar");
            ZipOutputStream zos = new ZipOutputStream(fos);

            String[] file = {
              "D:/java/hadoop/Archiver/test1/a.xlsx",
              "D:/java/hadoop/Archiver/test1/b.txt",
              "D:/java/hadoop/Archiver/test1/c.png",
              "D:/java/hadoop/Archiver/test1/d.java"
            };

            for(String s : file)
            {
                addFile(s,zos);
            }
            zos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("压缩完成");
    }

    /**
     * 向压缩包里面添加文件
     * @param s
     * @param zos
     */
    private static void addFile(String s, ZipOutputStream zos) {

        try {
            FileInputStream fis = new FileInputStream(s);
            byte[] data = new byte[fis.available()];
            File file = new File(s);
            fis.read(data);

            //新建条目
            zos.putNextEntry(new ZipEntry(file.getName()));
            zos.write(data);

            fis.close();
            zos.closeEntry();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("压缩文件" + s + "完成");
    }
}
