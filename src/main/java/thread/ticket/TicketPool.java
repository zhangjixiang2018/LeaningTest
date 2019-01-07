package thread.ticket;

public class TicketPool {
    private int ticketNo = 100;

    public synchronized int getTicketNo() {
        int tmp = ticketNo;

        if(tmp == 0)
        {
            return 0 ;//tmp==0,说明票池没有票了
        }
        ticketNo--;
        return tmp;
    }
}
