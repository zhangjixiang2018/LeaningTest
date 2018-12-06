package collection.MapTest.HashMapKeyTest;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Map<PK_people,People> map = new HashMap<PK_people, People>();
        PK_people pk_people = new PK_people();//新建键对象
        pk_people.setPrefix("tsoft");
        pk_people.setNumber(10);
        map.put(pk_people,new People("陈小姐",pk_people));//向map中添加元素
        PK_people pk_people1 = new PK_people();//再新建一个键对象，内容与上一个相同
        pk_people1.setPrefix("tsoft");
        pk_people1.setNumber(10);

        People people = map.get(pk_people1);//获取指定键对象映射的值对象
        if(people == null)
            System.out.println("该键对象不存在");
        else{
            System.out.println("该对象的key值是：" + people.getNumber().getPK());
            System.out.println("该对象的value值是： " + people.getName());
        }
    }
}
