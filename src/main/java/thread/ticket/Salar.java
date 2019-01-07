package thread.ticket;

public class Salar extends Thread {

    private String salarName ;
    private TicketPool pool ;

    public Salar(String name,TicketPool pool) {
        super(name);//线程的名字
        this.salarName = name;
        this.pool = pool;
    }

    public void run() {
        while (true) {
            int ticketNo = pool.getTicketNo();
            if (ticketNo == 0) {
                System.out.println(salarName + ": 没有票了");
                return;
            } else {
                System.out.println(salarName + " : " + ticketNo);
            }
        }
    }

    public String getSalarName() {
        return salarName;
    }

    public void setSalarName(String salarName) {
        this.salarName = salarName;
    }
}
