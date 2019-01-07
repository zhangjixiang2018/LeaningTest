package thread.MonkBread;

/**
 * 和尚吃馒头问题
 * 		a)100个馒头
 * 		b)30个和尚
 * 		c)每个和尚至少吃一个馒头
 * 		d)最多不超过5馒头.
 */
public class MonkBreadApp{

    public static void main(String[] args) {
        Pool pool = new Pool();

        for(int i = 0 ;i < 30 ;i++)
        {
            new monk("monk" + i , pool).start();
        }
    }

}
