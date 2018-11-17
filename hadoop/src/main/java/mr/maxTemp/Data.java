package mr.maxTemp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Data {
    public static void main(String[] args) {
        try {
            FileWriter fw = new FileWriter("D:/java/hadoop/Test_TXT/maxtemp/temp.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            int year ;
            int temp ;
            for(int i =0 ;i < 10000 ; i++)
            {
                //产生1970~2070的随机数
                year = 1970 + new Random().nextInt(100);
                //产生-100~100的随机数
                temp = -100 + new Random().nextInt(200);

                String s = new String(year + " " + temp);

                bw.write(s);
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}