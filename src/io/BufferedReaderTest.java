package io;

import java.io.*;
import java.util.Random;

public class BufferedReaderTest {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/BufferedReaderTest.txt");
        if(!file.exists()){
            file.createNewFile();
        }

        BufferedReader br = new BufferedReader(new FileReader(file));//创建BufferedReader
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        String s ;
        for(int i=0 ; i<100 ; i++){//产生随机数，并将随机数转化为字符串，最后将这个字符串写入文件
            s = String.valueOf(Math.random());
            bw.write(s);
            bw.newLine();
        }
        bw.flush();//刷新该流，将缓冲流中的数据都写入文件
        bw.close();

        while((s = br.readLine())!= null){
            System.out.println(s);
        }
        br.close();
    }
}
