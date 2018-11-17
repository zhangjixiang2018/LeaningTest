package io.archiver;

import java.io.*;

/**
 * 归档也就是将多个文件整理为一个文件，为了能解归档，在将多个文件整理为一个文件时
 * 要保证每个文件是可独立识别的，因此这里设计一种写入格式以标记每一个文件。
 * 文件名数组长度
 * 文件名数组
 * 文件内容长度
 * 文件内容
 */
public class archiver {
    public static void main(String[] args){

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("D:/java/hadoop/Archiver/test1/guidang.xar");
            String[] file = {
                    "D:/java/hadoop/Archiver/test1/a.xlsx",
                    "D:/java/hadoop/Archiver/test1/b.txt",
                    "D:/java/hadoop/Archiver/test1/c.png",
                    "D:/java/hadoop/Archiver/test1/d.java"
            };

            for(String s : file)
            {
                try {
                    fos.write(addFile(s));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static byte[] addFile(String s) throws Exception {

        //文件
        File f = new File(s);

        //文件名
        String fileName = f.getName();
        //文件名长度
        int fileNameLen = fileName.length();
        //文件名数组
        byte[] fileNameBytes = fileName.getBytes();

        //文件长度，为了简单起见这里做了long 到 int 的强制转换，因此文件最大不能超过65535B
        int fileLen = (int)f.length();
        //文件内容
        FileInputStream fis = new FileInputStream(f);
        byte[] fileData = new byte[fis.available()];
        fis.read(fileData);
        fis.close();

        //总长度，两个4表示的是一个整形的数，用来存储fileNameLen和fileLen是多少，
        //下面就有多少的空间来存储文件名和文件的内容
        int totalLen = 4 + fileNameLen + 4 + fileLen ;
        //bytes 将格式规定的东西都写入这个数组
        byte[] bytes = new byte[totalLen];

        //将文件名长度写入bytes
        byte[] fileNameLenArr = Util.int2Bytes(fileNameLen);
        System.arraycopy(fileNameLenArr,0,bytes,0,4);

        //将文件名写入bytes
        System.arraycopy(fileNameBytes,0,bytes,4,fileNameLen);

        //将文件长度写入bytes
        byte[] fileLenArr = Util.int2Bytes(fileLen);
        System.arraycopy(fileLenArr,0,bytes,4+fileNameLen,4);

        //将文件写入bytes
        System.arraycopy(fileData,0,bytes,4+fileNameLen+4,fileLen);

        System.out.println("文件" + s + "归档完成");
        return bytes;
    }

}
