package mr.maxTemp;

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

public class MaxTempApp {
    public static void main(String[] args) throws Exception {

        //创建配置对象
        Configuration conf = new Configuration();
        conf.set("defaultFs","file:///");

        //获取文件系统
        FileSystem fs = FileSystem.get(conf);
        //判断输出文件是否存在，存在则删除
        if(fs.exists(new Path(args[1])))
        {
            fs.delete(new Path(args[1]));
        }

        //创建job对象
        Job job = Job.getInstance(conf);

        //设置各种属性

        job.setJobName("MaxTemp");//设置作业名称

        job.setJarByClass(MaxTempApp.class);//设置搜索类

        job.setInputFormatClass(TextInputFormat.class);//设置输入格式

        FileInputFormat.addInputPath(job,new Path(args[0]));//添加输入路径

        FileOutputFormat.setOutputPath(job,new Path(args[1]));//设置输出路径

        job.setMapperClass(MaxTempMapper.class);//设置mapper类

        job.setReducerClass(MaxTempRedecer.class);//设置reducer类

        job.setNumReduceTasks(3);//设置reducer个数

        job.setMapOutputKeyClass(IntWritable.class);//设置map输出key类型

        job.setMapOutputValueClass(IntWritable.class);//设置map输出value类型

        job.setOutputKeyClass(IntWritable.class);//设置reducer输出key类型

        job.setOutputValueClass(IntWritable.class);//设置reducer输出value类型

        job.waitForCompletion(true);//运行流程
    }
}

/**
 * 可以得到一年中的最高气温，由于有3个reduce，所以输出文件有3个，
 * 每个文件中的年份是按照从小到大排的，但文件之间不是按顺序排的。
 * 可以通过全排序的方法使得文件之间也是有序的，全排序有三种方法，后面将会使用这些方法。
 * 第一种方法是只用一个reduce，这只适用于数据较少的情况下。
 * 第二种方法是自己重写分区函数，但可能会由于自己的经验不足对数据认识不够，
 *     使得分区不均匀，造成数据倾斜。TestAllsortPartitioner就是使用的这种方法。
 * 第三种方法是采用hadoop的自身的采样机制，通过采样使得分区均匀。TestSamperAllsort就是使用的这种方法。
 *     采样是在FileInputFormat之后map之前进行的，要求key值是可比较的且意义的，因此采用的是seqFile作为
 *     输入文件且key是IntWriter,不能用文本输入，文本的InputFormat输出的键值对是偏移量和文本，无法
 *     进行采样和比较,比较也是没有意义的。
 *
 * 在找每一年的最高气温时是通过遍历的方式，但这种方法的效率并不高，
 * 后面会通过二次排序对value也进行排序，并按照从大到小的顺序来排，
 * 这样只用找到Iterable的第一个元素即是最大值。
 *
 */
