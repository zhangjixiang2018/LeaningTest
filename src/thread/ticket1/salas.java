package thread.ticket1;

public class salas implements Runnable {
    private int ticketNum ;
    private TicketPool ticketPool;
    private String salasName ;

    public salas(TicketPool ticketPool ,String salasName) {
        this.ticketPool = ticketPool;
        this.salasName = salasName ;
    }

    public void run() {
        while (true){
            try {
                if(ticketPool.getTicketNum() > 0){
                    System.out.println(salasName + ":  检测票数大于0，可以售票");
                    System.out.println(salasName + ":  正在收款大概需要3秒。。。");
                    Thread.sleep(3000);
                    System.out.println(salasName + ":  售票完成，票号为：" + ticketPool.getTicketNum());
                    ticketPool.salaTicket();//售票完成,将票池里面的票减1
                }
                else {
                    System.out.println(salasName + ":  没有票了");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
