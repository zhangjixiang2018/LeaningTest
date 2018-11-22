package thread.producerCustomProblem;

public class producerCustomProblemApp {
    public static void main(String[] args) {
        Pool pool = new Pool();
        //Custom custom = new Custom(pool);
        new Thread(new Custom(pool),"custom1").start();
        new Thread(new Custom(pool),"custom2").start();
        new Thread(new Prodecer(pool),"prodecer1").start();
    }
}
