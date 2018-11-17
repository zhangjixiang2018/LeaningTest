package mr.maxTemp;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MaxTempMapper extends Mapper<LongWritable,Text,IntWritable,IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        IntWritable keyOut ;
        IntWritable valueOut;

        //将一行中的每个词分解出来
        String[] arr = value.toString().split(" ");

        keyOut = new IntWritable(Integer.parseInt(arr[0]));
        valueOut = new IntWritable(Integer.parseInt(arr[1]));

        //将keyOut,valueOut写入上下文
        context.write(keyOut,valueOut);
    }
}
