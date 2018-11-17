package mr.secondSort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 重写分区函数
 * 如果不重写分区函数的话，按默认分区函数是按照hash来分的，又没有重写hashcode（）
 * 这样同一年可以会被分到不同的分区中去。因为这里使用的是ComboKey，其hash值为其内存地址。
 */
public class YearPartitioner extends Partitioner<ComboKey,NullWritable> {
    public int getPartition(ComboKey comboKey, NullWritable nullWritable, int i) {
        System.out.println("YearPartitioner============" + comboKey) ;
        int year = comboKey.getYear();
        return year - 1970 < 33 ? 0 : year - 1970 <66 ? 1 : 2 ;
    }
}

/**
 * 自己重写分区函数又会导致分区不均的问题，那么能不能用hadoop的采样器先进行采样了？
 * 感觉理论上肯定是可以的。但至于怎么去搞还不清楚。
 * 首先肯定是不能像TestAllSortPartitioner里面样，直接设置采样器和hadoop自带的全排列方法，
 * 因为采样器生成的分区文件中的key值和map之后的key值不一样了，分区函数要使用map之后的key值。
 */