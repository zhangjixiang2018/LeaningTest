package thread.ticket1;

public class TicketPool {
    private int ticketNum = 10 ; //票池中的总票数为10张

    public void salaTicket(){
        ticketNum--;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }
}
