package collection.SetTest;

import java.util.*;

public class SetTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("a");
        Set set = new HashSet();
        set.addAll(list);
        Iterator<String> it = set.iterator();//创建Set集合迭代器
        System.out.println("集合中的元素是：");
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
