package mr.secondSort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupComparable  extends WritableComparator {

    //注册
    public GroupComparable() {
        super(ComboKey.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {

        ComboKey k1 = (ComboKey)a;
        ComboKey k2 = (ComboKey)b;
        System.out.println("GroupComparable========" + k1 + "  ==  " + k2);
        return k1.getYear() - k2.getYear();
    }

}
