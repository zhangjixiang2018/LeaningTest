package mr.TestSamperAllsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TestSamperAllsortReducer extends Reducer<IntWritable,IntWritable,IntWritable,IntWritable> {

    @Override
    protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int Max = Integer.MIN_VALUE;

        for(IntWritable iw : values)
        {
            Max = iw.get() > Max ? iw.get() : Max ;
        }

        context.write(key,new IntWritable(Max));
    }
}
