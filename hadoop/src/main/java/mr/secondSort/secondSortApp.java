package mr.secondSort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class secondSortApp {

    public static void main(String[] args) {

        try {
            Configuration conf = new Configuration();
            conf.set("defaultFS","file:///");
            FileSystem fs = FileSystem.get(conf);
            Job job = Job.getInstance(conf);

            job.setJobName("secondSortApp");
            job.setJarByClass(secondSortApp.class);
            job.setInputFormatClass(SequenceFileInputFormat.class);

            FileInputFormat.addInputPath(job,
                    new Path("D:\\java\\hadoop\\Test_TXT\\seq\\temp3.seq"));
            FileOutputFormat.setOutputPath(job,
                    new Path("D:\\java\\hadoop\\Test_TXT\\out"));

            job.setMapperClass(SecondSortMapper.class);
            job.setReducerClass(SecondSortReducer.class);

            job.setNumReduceTasks(3);

            job.setMapOutputKeyClass(ComboKey.class);
            job.setMapOutputValueClass(NullWritable.class);

            job.setOutputKeyClass(IntWritable.class);
            job.setOutputValueClass(IntWritable.class);

            job.setPartitionerClass(YearPartitioner.class);
            job.setGroupingComparatorClass(GroupComparable.class);
            job.setSortComparatorClass(ComboKeyComparator.class);

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
