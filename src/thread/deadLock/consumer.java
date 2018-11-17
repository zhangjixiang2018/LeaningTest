package thread.deadLock;

import thread.ticket.Salar;

public class consumer extends Thread{

    private Pool pool ;
    private String conName ;

    public consumer(String name, Pool p) {
        super(name);
        this.pool = p;
        this.conName = name;
    }

    @Override
    public void run() {
        while(true)
        {
           pool.remove();

        }
    }

    public String getconName() {
        return conName;
    }
}
