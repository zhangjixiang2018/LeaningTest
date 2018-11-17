package mr.TestAllSortPartitioner;

import mr.maxTemp.YearPartitioner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class AllSortPartitionerApp {

    public static void main(String[] args) {

        try {

            Configuration conf = new Configuration();                   //创建配置对象
            conf.set("defaultFS","file:///");

            FileSystem fs = FileSystem.get(conf);


            Job job = Job.getInstance(conf);                            //创建job对象

            //设置各种属性
            job.setJobName("AllSort");                                  //设置作业名称

            job.setJarByClass(AllSortPartitionerApp.class);             //设置搜索类

            job.setInputFormatClass(SequenceFileInputFormat.class);     //设置输入文件格式

            FileInputFormat.addInputPath(job,new Path(args[0]));        //添加输入路径
            FileOutputFormat.setOutputPath(job,new Path(args[1]));      //设置输出路径
            if(fs.exists(new Path(args[1])))                            //如果存在输出文件则将其删除
            {
                fs.delete(new Path(args[1]));
            }

            job.setMapperClass(AllSortMapper.class);                    //设置Map类
            job.setReducerClass(AllSortReducer.class);                  //设置reducer类

            job.setNumReduceTasks(3);                                   //设置reducer个数
            job.setMapOutputKeyClass(IntWritable.class);                //设置map输出key类型
            job.setMapOutputValueClass(IntWritable.class);              //设置map输出value类型

            job.setOutputKeyClass(IntWritable.class);                   //设置reducer的输出key类型
            job.setOutputValueClass(IntWritable.class);                 //设置reducer的输出value类型

            job.setPartitionerClass(YearPartitioner.class);             //设置分区函数
            job.waitForCompletion(true);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
