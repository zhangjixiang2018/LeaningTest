package collection.MapTest;

import java.util.*;

public class valueDemo {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String, String>();//创建map集合
        map.put("01","apple");                                //想结合中添加元素
        map.put("02","pear");
        map.put("03","orange");
        Collection<String> list = (Collection<String>) map.values();//获取Map集合中的所有value集合
        Iterator<String> it = list.iterator();//创建集合迭代器
        System.out.println("map集合中value的值为：");
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
