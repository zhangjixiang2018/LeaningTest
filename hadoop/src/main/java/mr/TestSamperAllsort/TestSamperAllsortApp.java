package mr.TestSamperAllsort;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;

import java.io.IOException;

public class TestSamperAllsortApp {
    public static void main(String[] args) {

        try {
            Configuration conf = new Configuration();               //创建配置对象
            conf.set("defaultFS","file:///");
            FileSystem fs = FileSystem.get(conf);

            Job job = Job.getInstance(conf);                        //创建job对象

            //设置各种属性
            job.setJobName("TestSamperAllsortApp");                 //
            job.setJarByClass(TestSamperAllsortApp.class);          //
            job.setInputFormatClass(SequenceFileInputFormat.class); //

            FileInputFormat.addInputPath(job,new Path(args[0]));    //
            FileOutputFormat.setOutputPath(job,new Path(args[1]));  //
            if(fs.exists(new Path(args[1])))
            {
                fs.delete(new Path(args[1]));
            }

            job.setMapperClass(TestSamperAllsortMapper.class);      //
            job.setReducerClass(TestSamperAllsortReducer.class);    //

            job.setNumReduceTasks(3);                               //
            job.setMapOutputKeyClass(IntWritable.class);            //
            job.setMapOutputValueClass(IntWritable.class);          //
            job.setOutputKeyClass(IntWritable.class);               //
            job.setOutputValueClass(IntWritable.class);             //

            //创建随机采样器对象
            //freq：每个key被选中的概率
            //numSample：抽取样本的总数
            //maxSplitSample:最大切片数，与reduce个数相等
            InputSampler.Sampler<IntWritable,IntWritable> sampler =
                    new InputSampler.RandomSampler<IntWritable, IntWritable>
                            (1,1000,3);

            //设置分区文件的位置
            TotalOrderPartitioner.setPartitionFile(job.getConfiguration(),
                    new Path("D:/java/hadoop/Test_TXT/maxtemp/par.lst"));

            //设置全排序分区函数
            job.setPartitionerClass(TotalOrderPartitioner.class);

            //将sample数据写入分区文件
            InputSampler.writePartitionFile(job,sampler);

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
