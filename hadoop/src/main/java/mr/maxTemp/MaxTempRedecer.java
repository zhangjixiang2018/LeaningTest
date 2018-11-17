package mr.maxTemp;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MaxTempRedecer extends Reducer<IntWritable,IntWritable,IntWritable,IntWritable> {

    @Override
    protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //获取整形的最小值,赋值给maxTemp
        int maxTemp = Integer.MIN_VALUE;

        for(IntWritable iw : values )
        {
            if(iw.get()>maxTemp)
            {
                maxTemp = iw.get();
            }
        }

        context.write(key,new IntWritable(maxTemp));
    }
}
