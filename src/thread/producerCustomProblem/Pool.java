package thread.producerCustomProblem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pool {
    LinkedList<Integer> poolList = new LinkedList<Integer>();

    synchronized public void add(Integer i)
    {
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name + ": 我向pool中添加了元素====" + i);
            Thread.sleep(2000);
            poolList.add(new Integer(i));
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void remove(){
        try {
            String name = Thread.currentThread().getName();
            if(poolList.isEmpty()){
                System.out.println(name + ": pool中没有东西，等待中。。。。。。。");
                wait();
                notifyAll();
            }else {
                System.out.println(name + ": 我在pool中拿走了元素====" + poolList.removeFirst());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
