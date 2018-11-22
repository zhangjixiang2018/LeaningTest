package thread.producerCustomProblem;

public class Custom implements Runnable{
    public Pool pool = new Pool();

    public Custom(Pool pool) {
        this.pool = pool;
    }

    public void run() {
        while (true){
            pool.remove();
        }
    }
}
