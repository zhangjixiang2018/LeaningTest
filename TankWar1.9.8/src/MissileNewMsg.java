import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class MissileNewMsg implements Msg {

    public int msgType = Msg.MISSILE_NEW_MSG ;
    //public int x ;
    //public int y ;
    //public int tankId ;
    //public Direction dir ;
    public Missile m ;
    public TankInterface ti ;

    public MissileNewMsg(Missile m) {
        this.m = m;
    }

    public MissileNewMsg(TankInterface ti) {
        this.ti = ti;
    }

    public void send(DatagramSocket ds, String ip, int udpport) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        try {
            dos.writeInt(msgType);
            dos.writeInt(m.tankId);
            dos.writeInt(m.x);
            dos.writeInt(m.y);
            dos.writeInt(m.dir.ordinal());
            dos.writeInt(m.groupId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] buf = baos.toByteArray();

        try {
            DatagramPacket dp = new DatagramPacket(buf,buf.length,new InetSocketAddress(ip,udpport));
            ds.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse(DataInputStream dis) {
        try {
            int id = dis.readInt();
            if(ti.myTank.id == id ){
                System.out.println("MissileNewMsg--parse--ti.myTank.id == id");
                return;
            }
            int x = dis.readInt();
            int y = dis.readInt();
            Direction dir = Direction.values()[dis.readInt()];
            int groupId = dis.readInt();

            Missile m = new Missile(x,y,dir,groupId,ti,id);
            ti.missiles.add(m);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
