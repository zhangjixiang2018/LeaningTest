package mr.secondSort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SecondSortMapper extends Mapper<IntWritable,IntWritable,ComboKey,NullWritable> {

    @Override
    protected void map(IntWritable key, IntWritable value, Context context) throws IOException, InterruptedException {

        System.out.println("========map==========");
        int year = key.get();
        int temp = value.get();

        //组合k类型
        ComboKey keyOut = new ComboKey();
        keyOut.setYear(year);
        keyOut.setTemp(temp);

        //将输出的（k,v）写入context
        context.write(keyOut,NullWritable.get());
    }
}
