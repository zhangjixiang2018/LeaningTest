import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

public class NetClient {
    private int udpPort ;
    TankInterface ti ;
    DatagramSocket ds = null ;

    public NetClient(TankInterface ti){
        this.ti = ti;

    }

    public void connect(String IP ,int port){
        Socket s = null ;

        try {
            ds = new DatagramSocket(udpPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            s = new Socket(IP,port);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeInt(udpPort);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            int id = dis.readInt();
            ti.myTank.id = id ;
            System.out.println("Connetcted to Server and server give me a ID" + id);
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

        TankNewMsg msg = new TankNewMsg(ti.myTank);
        send(msg);

        new Thread(new UDPReceiveThread()).start();
    }

    public void send(Msg msg) {
        msg.send(ds,"127.0.0.1",TankServer.UDP_PORT);
    }

    public int getUdpPort() {
        return udpPort;
    }

    public void setUdpPort(int udpPort) {
        this.udpPort = udpPort;
    }

    public class UDPReceiveThread implements Runnable{
        byte[] buf = new byte[1024];

        public void run() {
            while (true) {
                DatagramPacket dp = new DatagramPacket(buf,buf.length);
                try {
                    ds.receive(dp);
                    parse(dp);
                    System.out.println("a packet received from server !");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void parse(DatagramPacket dp) {
            ByteArrayInputStream bais = new ByteArrayInputStream(buf,0,dp.getLength());
            DataInputStream dis = new DataInputStream(bais);
            int msgType = 0 ;
            try {
                 msgType = dis.readInt();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Msg msg = null ;
            switch (msgType){
                case Msg.TANK_NEW_MSG :
                    msg = new TankNewMsg(NetClient.this.ti);
                    msg.parse(dis);
                    break;
                case Msg.TANK_MOVE_MSG:
                    msg = new TankMoveMsg(NetClient.this.ti);
                    msg.parse(dis);
                    break;
                 case Msg.MISSILE_NEW_MSG:
                     msg = new MissileNewMsg(NetClient.this.ti);
                     msg.parse(dis);
                     break;
            }
        }

    }


}
