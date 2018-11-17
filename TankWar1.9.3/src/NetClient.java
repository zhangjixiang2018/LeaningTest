import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetClient {
    private static int UDP_PORT_START = 2222 ;
    private int udpPort ;
    TankInterface tc ;

    public NetClient(TankInterface tc){
        this.tc = tc;
        udpPort = UDP_PORT_START ++ ;

    }

    public void connect(String IP ,int port){
        Socket s = null ;
        try {
            s = new Socket(IP,port);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeInt(udpPort);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            int id = dis.readInt();
            tc.myTank.id = id ;
            System.out.println("Connet Server");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(s != null) {
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
