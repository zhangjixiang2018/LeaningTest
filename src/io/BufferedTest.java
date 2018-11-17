package io;

import java.io.*;

/**
 * 用
 */
public class BufferedTest {

    public static void main(String[] args) {

        try {
            writeFile();
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向文件里写数据
     */

    public static void writeFile() throws IOException {

        //注意FileWriter("d:/java/hadoopTest.txt",true)，第二参数表示的是追加还是覆盖，
        //默认是false也就是覆盖，要想在文件后面追加时要注意这个参数
        BufferedWriter bw = new BufferedWriter(new FileWriter("d:/java/1.txt",true));
        String s = new String();
        for(int i = 0 ; i < 10 ;i++)
        {

            s = ("zjx  " + i);
            try {
                bw.write(s);
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        bw.close();
    }

    /**
     * 将文件里面的数据读出来
     */
    public static void read() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("d:/java/1.txt"));
        String s = new String();
        int len = 0;
        while( ( s = br.readLine() ) != null)
        {
            System.out.println(s);
        }
        br.close();
    }
}
