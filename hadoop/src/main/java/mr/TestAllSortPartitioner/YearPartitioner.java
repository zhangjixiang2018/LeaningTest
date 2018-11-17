package mr.TestAllSortPartitioner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class YearPartitioner extends Partitioner<IntWritable,IntWritable> {
    public int getPartition(IntWritable year, IntWritable temp, int i) {
        return year.get()-1970 < 33 ? 1 : year.get()-1970 < 66 ? 2 : 3;
    }
}
