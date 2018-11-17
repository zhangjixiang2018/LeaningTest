package io.archiver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class unArchiver {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("D:/java/hadoop/Archiver/test1/guidang.xar");
            //文件list，将文件都放进这个list
            List<FileBean> files = new ArrayList<FileBean>();

            FileBean file = null;
            //判断fis里面是否还有数据，fis读是有一个标记的，不是每次都从0位置开始读
            while( (file = readNextFile(fis)) != null)
            {
                //将文件放进list
                files.add(file);
            }

            FileOutputStream fos = null;
            String fName = null;
            for(FileBean f : files)
            {
                fName = f.getFileName();
                fos = new FileOutputStream("D:/java/hadoop/Archiver/test1/unArchiver/" + fName);
                fos.write(f.getFileContent());
                System.out.println(fName + "解归档完成");
                fos.close();
            }

            fis.close();
            System.out.println("解归档完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static FileBean readNextFile(FileInputStream fis) throws IOException {

        //文件名长度数组
        byte[] fileNameLenArr = new byte[4];
        int rea = fis.read(fileNameLenArr);
        if(rea == -1)//判断是否到达文件末尾
        {
            return null;
        }

        //文件名长度
        int fileNameLen = Util.bytes2Int(fileNameLenArr);
        //文件名数组
        byte[] fileNameArr = new byte[fileNameLen];
        fis.read(fileNameArr);
        //文件名
        String fileName = new String(fileNameArr);
        //String fileName = fileNameArr.toString();//这个返回的是内存地址

        //文件长度数组
        byte[] fileLenArr = new byte[4];
        fis.read(fileLenArr);
        //文件长度
        int fileLen = Util.bytes2Int(fileLenArr);
        //文件数组
        byte[] fileArr = new byte[fileLen];
        fis.read(fileArr);

        //fis.close();
        return new FileBean(fileName,fileArr);
    }
}
