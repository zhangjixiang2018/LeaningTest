package mr.wordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WCApp {

    public static void main(String[] args) throws Exception {

        if(args.length != 2)
        {
            System.out.println("Usage : Word Count <input path> <output path>");
            System.exit(-1);
        }

        //
        Configuration conf = new Configuration();
        //conf.set("fs.defaultFS","file:///");

        FileSystem fs = FileSystem.get(conf);

        if(fs.exists(new Path(args[1])))
        {
            fs.delete(new Path(args[1]),true);
        }

        //创建job
        Job job = Job.getInstance(conf);

        //设置各种属性

        //作业名称
        job.setJobName("word count");

        //搜索类
        job.setJarByClass(WCApp.class);

        //设置输入格式
        job.setInputFormatClass(TextInputFormat.class);

        //添加输入路径
        FileInputFormat.addInputPath(job,new Path(args[0]));

        //设置输出路径
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //设置map类
        job.setMapperClass(WCMapper.class);
        //设置reduce类
        job.setReducerClass(WCReducer.class);

        //设置reduc个数
        job.setNumReduceTasks(1);

        //设置map输出key的类型
        job.setMapOutputKeyClass(Text.class);
        //设置map输出value的类型
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce输出key的类型
        job.setOutputValueClass(Text.class);
        //设置reduce输出value的类型
        job.setOutputKeyClass(IntWritable.class);

        //打印过程
        job.waitForCompletion(true);
    }
}
