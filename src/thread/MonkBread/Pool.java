package thread.MonkBread;

public class Pool {
    int breadNo = 100 ;

    private int monkCount = 0;//记录已经吃了馒头的和尚的个数
    private int Max = 5 ;     //每个和尚能吃馒头的最大个数
    private int monkTotalCount = 30;//和尚的人数

    public synchronized int eat(monk m) {
        //
        if(m.getCount() >= Max)
        {
            System.out.println(Thread.currentThread().getName() + "已经吃了5个馒头，不能再吃了");
            return -1;
        }

        //没有馒头了
        if(breadNo==0)
        {
            return 0;
        }

        //记录已经吃了馒头的和尚的个数
        if(m.getCount() == 0) { monkCount++; }

        //剩下的馒头个数大于还没有吃馒头的和尚的人数时才能继续吃
        if(breadNo > monkTotalCount - monkCount)
        {
            return breadNo--;
        }
        else
        {
            return -2;
        }


    }
}
