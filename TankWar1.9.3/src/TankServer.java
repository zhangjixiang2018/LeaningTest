import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TankServer {

    public static final int TCP_PORT = 8888 ;
    List<Client> clients = new ArrayList<Client>();
    private int id = 100 ;                      //每辆坦克的编号，唯一标识

    public static void main(String[] args) {
        new TankServer().start();
    }


    public void start(){
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
}
