package test;

import java.io.File;

public class listFile {
    public static void main(String[] args) {
        File f = new File("D:/java/hadoop/Archiver/test1");
        listChild(f,0);
    }

    public static void listChild(File f,int level)
    {
        String preStr = "";
        for(int i=0; i<level; i++) { preStr += "    "; }

        System.out.println(preStr + f.getName());

        //判断是否为目录
        if(!f.isDirectory()) { return ;}
        File[] filsList = f.listFiles();

        for(int i = 0 ; i < filsList.length ; i++)
        {
            listChild(filsList[i],level+1);
        }
    }
}
