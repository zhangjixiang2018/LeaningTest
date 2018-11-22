package thread.ticketProblem;

public class TicketProble implements Runnable {
    private static int ticketNum = 1 ; //票数,注意保证票数的的唯一，也就是用要用static
    private String salaName ;
    private int currutTicketNum ;

    public TicketProble(String salaName) {
        this.salaName = salaName;
    }

    public void run() {
        while (true){
            try {
                System.out.println(salaName + ": 正在检测到当前车次的票数");
                if(ticketNum > 0){
                    System.out.println(salaName + ": 检测到当前车次的票数大于0");
                    System.out.println(salaName + ": 正在收款大约需要5秒完成");
                    Thread.sleep(3000);
                    System.out.println(salaName + ": 打印票据-----售票完成-----票号为" + ticketNum--);
                    //ticketNum--;
                }else {
                    System.out.println(salaName + ": 票已售完");
                    break;

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Thread(new TicketProble("李售票员")).start();
            Thread.sleep(1000);
            new Thread(new TicketProble("王售票员")).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
