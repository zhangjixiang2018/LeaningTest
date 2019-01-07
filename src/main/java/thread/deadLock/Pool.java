package thread.deadLock;

import java.util.LinkedList;
import java.util.List;

public class Pool {

    private LinkedList <Integer> pool = new LinkedList<Integer>();
    int Max = 1;

    public synchronized void add(int i) {

        try
        {
            while (pool.size() >= Max)
            {
                System.out.println(Thread.currentThread().getName() + ".wait()");
                this.wait();
            }

            pool.add(i);
            System.out.println(Thread.currentThread().getName() + " add " + i);
            System.out.println(Thread.currentThread().getName() + ".notify()");
            this.notify();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public synchronized void remove() {

        try
        {
            while(pool.isEmpty())
            {
                System.out.println(Thread.currentThread().getName() + ".wait()");
                wait();
            }

            int n = pool.removeFirst();
            System.out.println(Thread.currentThread().getName() + ".remove()" + n);
            System.out.println(Thread.currentThread().getName() + ".notify()");
            this.notify();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

