package collection.MapTest.TreeMapTest;

import java.util.*;

public class App {
    public static void main(String[] args) {
        Map<String,String> hashMap = new HashMap<String, String>();
        //新建EMP对象
        Emp emp1 = new Emp("001","张三");
        Emp emp2 = new Emp("002","李四");
        Emp emp3 = new Emp("003","王五");
        Emp emp4 = new Emp("004","赵六");
        //向HashMap中添加映射关系
        hashMap.put(emp1.getId(),emp1.getName());
        hashMap.put(emp2.getId(),emp2.getName());
        hashMap.put(emp3.getId(),emp3.getName());
        hashMap.put(emp4.getId(),emp4.getName());

        Set<Map.Entry<String,String>> entrySet = hashMap.entrySet();//取出map集合的映射关系
        Iterator<Map.Entry<String,String>> it = entrySet.iterator();//获取entrySet的迭代器
        System.out.println("HashMap集合中的元素为：(无序的)");
        while (it.hasNext()){
            System.out.println(it.next());
        }

        Map<String,String> treeMap = new TreeMap<String, String>();
        treeMap.putAll(hashMap);
        Iterator iterator = treeMap.keySet().iterator();
        System.out.println("TreeMap集合中的元素为：(有序的)");
        while (iterator.hasNext()){
            String id = (String) iterator.next();
            String name = treeMap.get(id);
            System.out.println(id + " " + name);
        }

    }
}
