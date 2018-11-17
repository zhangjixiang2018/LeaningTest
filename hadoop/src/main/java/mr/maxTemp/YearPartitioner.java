package mr.maxTemp;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class YearPartitioner extends Partitioner<IntWritable,IntWritable> {
    public int getPartition(IntWritable year, IntWritable temp, int i) {
        return year.get()-1970 < 33 ? 0 : year.get()-1970 < 66 ? 1 : 2;
    }
}
