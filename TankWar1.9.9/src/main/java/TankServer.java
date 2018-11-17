import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class TankServer {

    public static final int TCP_PORT = 8888 ;
    public static final int UDP_PORT = 6666 ;
    List<Client> clients = new ArrayList<Client>();  //将所有连上来的客户端都放入这个容器里面。
    private int id = 100 ;                      //每辆坦克的编号，唯一标识

    public static void main(String[] args) {
        new TankServer().start();
    }


    public void start(){

        new Thread(new UDPThread()).start();

        ServerSocket serverSocket = null ;
        try {
             serverSocket = new ServerSocket(TCP_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            Socket s = null ;
            try {
                s = serverSocket.accept();
                DataInputStream dis = new DataInputStream(s.getInputStream());
                String IP = s.getInetAddress().getHostAddress();
                int udpPort = dis.readInt();

                Client c = new Client(IP , udpPort);
                clients.add(c);
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                dos.writeInt(id++);
                s.close();
                System.out.println("A Client Connet Addr- " + s.getInetAddress()
                        + s.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(s != null){
                    try {
                        s.close();
                        s = null ;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }
    private class Client{
        String IP ;
        int udpPort ;

        public Client(String IP , int udpPort){
            this.IP = IP ;
            this.udpPort  = udpPort ;
        }
    }

    private class UDPThread implements Runnable {
        byte[] buf = new byte[1024];

        public void run() {
            DatagramSocket ds = null ;
            try {
                ds =new DatagramSocket(UDP_PORT);
            } catch (SocketException e) {
                e.printStackTrace();
            }
            System.out.println("UDP thread started at port :" + UDP_PORT);

            while (ds != null){
                DatagramPacket dp = new DatagramPacket(buf,buf.length);
                try {
                    ds.receive(dp);             //接收到一个数据包

                    //将接收到的数据包发送给每一个客户端
                    for(int i=0 ; i<clients.size() ; i++){
                        Client c = clients.get(i);
                        System.out.println("zjx2");
                        dp.setSocketAddress(new InetSocketAddress(c.IP,c.udpPort));
                        ds.send(dp);
                    }
                    System.out.println("receive a packet");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
