package string;

import org.junit.Test;

/**
 * str＝str.substring(int beginIndex);截取掉str从首字母起长度为beginIndex的字符串，
 *  将剩余字符串赋值给str；
 *str＝str.substring(int beginIndex，int endIndex);截取str中从beginIndex开始至
 * endIndex结束时的字符串，并将其赋值给str;
 *
 *
 *
 */
public class TestSubstringIndex {


    @Test
    public void test()
    {
        String line= "2,tom,12";

        String id = line.substring(0,line.indexOf(','));

        System.out.println(id);
    }

    /**
     * 目的：得到最后的2
     * substring 前包后不包
     */
    @Test
    public void test2()
    {
        String dingdan = "1,no1,12.3,2";
        String cid = dingdan.substring(dingdan.lastIndexOf(",") + 1);
        System.out.println(cid);
    }

    /**
     * 目的：得到1,no1,12.3
     * substring 前包后不包
     */
    @Test
    public void test3()
    {
        String dingdan = "1,no1,12.3,2";
        String ordersInfo = dingdan.substring(0,dingdan.lastIndexOf(","));
        System.out.println(ordersInfo);
    }
}
