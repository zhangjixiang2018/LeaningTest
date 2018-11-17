package thread.MonkBread;

import java.util.Random;

public class monk extends Thread {

    private String monkName ;
    private Pool pool;
    private int count = 0 ;//吃个馒头个数

    public monk( String monkName, Pool pool) {
        super(monkName);
        this.monkName = monkName;
        this.pool = pool;
    }

    public void run() {
        while (true)
        {
            int n = pool.eat(this);

            try {
                Thread.sleep(new Random().nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (n)
            {
                //已经吃了5个
                case -1:
                    return;
                //剩下的馒头不够一人一个了
                case -2:
                    System.out.println(monkName + " : 师弟不够吃了，我不能再吃了");
                    return;

                case 0 :
                    System.out.println(monkName + " : 没有馒头了，我吃了=========" + count + "个馒头");
                    return;

                default:
                    count++;
                    System.out.println(monkName + " : ,我吃了=========" +  n + "号馒头，我已经吃了"+count + "个馒头");
            }
        }
    }

    public int getCount() {
        return count;
    }
}
