package mr.secondSort;


import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 组合键排序对比器
 * 需要排序的时候要用到此对比器
 */
public class ComboKeyComparator extends WritableComparator {

    // WritableComparator没有泛型，需要注册
    protected ComboKeyComparator()
    {
        super(ComboKey.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {

        ComboKey k1 = (ComboKey)a;
        ComboKey k2 = (ComboKey)b;
        System.out.println("ComboKeyComparator========" + k1 + "  ==  " + k2);
        return k1.compareTo(k2);
    }
}
