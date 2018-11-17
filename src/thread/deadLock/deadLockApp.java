package thread.deadLock;

public class deadLockApp {
    public static void main(String[] args) {

        Pool pool = new Pool();

        produce p1 = new produce("p1",pool);
        consumer c1 = new consumer("c1",pool);
        consumer c2 =new consumer("c2",pool);

        p1.start();
        c1.start();
        c2.start();
    }
}
