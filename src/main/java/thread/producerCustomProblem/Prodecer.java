package thread.producerCustomProblem;

public class Prodecer implements Runnable {
    private Pool pool ;
    private static int num = 0 ;
    public Prodecer(Pool pool) {
        this.pool = pool;
    }

    public void run() {
        while (true){
            pool.add(new Integer(++num));
        }
    }
}
