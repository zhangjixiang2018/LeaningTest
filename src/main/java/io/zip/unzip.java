package io.zip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class unzip {
    public static void main(String[] args) {
            try {
                //文件输入流
                FileInputStream fis = new FileInputStream("D:/java/hadoop/Archiver/test1/yasuo.xar");
                //解压流
                ZipInputStream zis = new ZipInputStream(fis);

                byte[] bytes = new byte[1024];
                int len = 0 ;
                //条目
                ZipEntry entry = null ;

                //当条目不为空时
                while((entry = zis.getNextEntry()) != null)
                {
                    //获取文件名
                    String name = entry.getName();
                    //文件输出流，解压的输出路径
                    FileOutputStream fos = new FileOutputStream("D:/java/hadoop/Archiver/test1/unzip/" + name);

                    while((len = zis.read(bytes))  != -1)
                    {
                        fos.write(bytes,0,len);
                    }
                    fos.close();
                    zis.closeEntry();
                    System.out.println("解压文件" + name + "完成");
                }
                zis.close();
                fis.close();
                System.out.println("解压文件完成");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
