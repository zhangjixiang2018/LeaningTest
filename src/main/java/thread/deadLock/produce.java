package thread.deadLock;

public class produce extends Thread{

    private static int index = 0;

    private String proName ;
    private Pool pool ;

    public produce(String proName, Pool pool) {
        super(proName);
        this.proName = proName;
        this.pool = pool;
    }

    @Override
    public void run() {
        while(true)
        {

            pool.add(index++);
        }
    }

    public String getProName() {
        return proName;
    }
}
