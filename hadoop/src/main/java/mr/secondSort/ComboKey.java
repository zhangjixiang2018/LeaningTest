package mr.secondSort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 自定义组合key
 * key要可比较和串行化，那么就要实现WritableComparable接口
 */
public class ComboKey implements WritableComparable<ComboKey> {

    private int year ;
    private int temp ;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    /**
     * 比较，年份按升序排列，温度按降序排列
     * 当需要进行排序的时候，别的方法会调用此方法来比较两个值或对象的大小，别的方法默认是进行升序排序。
     * 当此方法返回正数，说明本对象大于传进来的对象，返回负数则相反，返回0，说明这两个对象相等。
     *
     */
    public int compareTo(ComboKey o) {

        int y0 = o.getYear();
        int t0 = o.getTemp();

        if(year == y0)//年份相同时，温度按照降序来排
        {
            return -(temp - t0) ;
        }
        else//年份不同时，将年份按照升序来排
        {
            return year - y0 ;
        }
    }

    /**
     * 串行化
     * 发送数据，把数据写到流中，发送到远端
     */
    public void write(DataOutput out) throws IOException {

        out.writeInt(year);
        out.writeInt(temp);
    }

    /**
     * 反串行化
     * 从远端接收数据，把数据从流中恢复
     */
    public void readFields(DataInput in) throws IOException {

        year = in.readInt();
        temp = in.readInt();
    }

    @Override
    public String toString() {
        return "ComboKey{" +
                "year=" + year +
                ", temp=" + temp +
                '}';
    }
}
