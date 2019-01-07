package collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TestHashmap {

    @Test
    public void test1() {
        Map<String, String> map = new HashMap<String, String>();

        map.put("01", "zhangsan");
        map.put("02", "lisi");
        map.put("03", "wangwu");

        //通过entrySet()方法将map集合中的映射关系取出（这个关系就是Map.Entry类型）
        Set<Map.Entry<String, String>> entrySet = map.entrySet();

        //将关系集合entrySet进行迭代，存放到迭代器中
        Iterator<Map.Entry<String, String>> it2 = entrySet.iterator();

        while (it2.hasNext()) {
            Map.Entry<String, String> me = it2.next();//获取Map.Entry关系对象me
            String key2 = me.getKey();//通过关系对象获取key
            String value2 = me.getValue();//通过关系对象获取value

            System.out.println("key: " + key2 + "-->value: " + value2);

        }
    }

    @Test
    public void test2()
    {
        Map<String,String> map = new HashMap<String,String>();

        map.put("1","Tom1");
        map.put("2","Tom2");
        map.put("3","Tom3");
        map.put("4","Tom4");

        Set<Map.Entry<String,String>> entry = map.entrySet();
        Iterator<Map.Entry<String,String>> it = entry.iterator();

        for(Map.Entry<String,String> entry1 : map.entrySet())
        {
            String key = entry1.getKey();
            String value = entry1.getValue();

            System.out.println(key + value);
        }
        while(it.hasNext())
        {
            Map.Entry<String,String> en = it.next();

            System.out.println(en.getKey() + en.getValue());
        }
    }
}
