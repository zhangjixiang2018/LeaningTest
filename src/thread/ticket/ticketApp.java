package thread.ticket;

public class ticketApp {
    public static void main(String[] args) {

        TicketPool pool = new TicketPool();
        Salar s1 = new Salar("s1",pool);
        Salar s2 = new Salar("s2",pool);
        Salar s3 = new Salar("s3",pool);
        Salar s4 = new Salar("s4",pool);
        Salar s5 = new Salar("s5",pool);

        s1.start();
        s2.start();
        s3.start();
        s4.start();
        s5.start();

        //System.out.println(s.getName());
    }
}
