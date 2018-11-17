package mr.TestSeqFile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

public class TestSeqFile {

    /**
     * 往seq文件里面写数据
     * 这里的写是覆盖的，百度上说seqFile并不能追加
     */
    @Test
    public void writeFile()
    {
        try {
            Configuration conf = new Configuration();
            conf.set("defaultFS","file:///");
            FileSystem fs = FileSystem.get(conf);
            Path path = new Path("D:/java/hadoop/Test_TXT/seq/temp3.seq");

            //(FileSystem fs, Configuration conf, Path name, Class keyClass, Class valClass)
            SequenceFile.Writer writer = SequenceFile.createWriter(fs,conf,path,IntWritable.class,IntWritable.class);

           /* for(int i = 0 ; i < 1000 ; i++)
            {
                int year = 1970 + new Random().nextInt(100);
                int temp = -100 + new Random().nextInt(200);

                writer.append(new IntWritable(year) , new IntWritable(temp));
            }*/

            for(int i = 0 ; i < 10 ; i++)
            {
                int year = 2000 + new Random().nextInt(3);
                int temp = -100 + new Random().nextInt(200);

                writer.append(new IntWritable(year) , new IntWritable(temp));
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写操作
     */
    @Test
    public void zipGzip() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("d:/seq/1.seq") ;
        SequenceFile.Writer writer = SequenceFile.createWriter(fs,
                conf,
                p,
                IntWritable.class,
                Text.class,
                SequenceFile.CompressionType.BLOCK,
                new GzipCodec());
        for(int i = 0 ; i < 10 ; i ++){
            writer.append(new IntWritable(i),new Text("tom" + i));
            //添加一个同步点
            writer.sync();
        }
        for(int i = 0 ; i < 10 ; i ++){
            writer.append(new IntWritable(i),new Text("tom" + i));
            if(i % 2 == 0){
                writer.sync();
            }
        }
        writer.close();
    }

    /**
     *读操作，循环输出所有的key-value
     */
    @Test
    public void readFile()
    {
        try {
            Configuration conf = new Configuration();
            conf.set("defaultFS","file:///");
            FileSystem fs = FileSystem.get(conf);
            Path path = new Path("D:/java/hadoop/Test_TXT/seq/temp3.seq");
            SequenceFile.Reader reader = new SequenceFile.Reader(fs,path,conf);

            IntWritable key = new IntWritable();
            IntWritable vlaue = new IntWritable();

            while(reader.next(key,vlaue))
            {
                System.out.println(key.get() + "  " + vlaue.get());
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读操作,得到当前value
     */
    @Test
    public void read2() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("d:/seq/1.seq") ;
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, p , conf);

        IntWritable key = new IntWritable();
        Text value = new Text() ;
        while(reader.next(key)){
            reader.getCurrentValue(value);
            System.out.println(value.toString());
        }
        reader.close();
    }

    /**
     * 读操作
     */
    @Test
    public void read3() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("d:/seq/1.seq") ;
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, p , conf);
        IntWritable key = new IntWritable();
        Text value = new Text() ;
        reader.seek(288);

        reader.next(key,value);
        System.out.println(value.toString());
        reader.close();
    }

    /**
     *
     * 操纵同步点
     */
    @Test
    public void read4() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("d:/seq/1.seq") ;
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, p , conf);
        IntWritable key = new IntWritable();
        Text value = new Text() ;

        //
        reader.sync(648);
        while(reader.next(key,value)){
            System.out.println(reader.getPosition() + "   " + key.get() + "-" + value.toString());
        }
        reader.close();
    }
}
