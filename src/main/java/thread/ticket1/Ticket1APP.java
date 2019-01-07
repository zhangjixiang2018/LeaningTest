package thread.ticket1;

/**
 * 这个程序主要是为了产生售票问题，即两个售票员会出售同一张票或者会出售0号票即不存在的票。
 */
public class Ticket1APP {
    public static void main(String[] args) {
        TicketPool ticketPool = new TicketPool();
        new Thread(new salas(ticketPool,"李售票员")).start();
        new Thread(new salas(ticketPool,"王售票员")).start();
    }
}
