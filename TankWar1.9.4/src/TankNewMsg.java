import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TankNewMsg {
    Tank tank ;
    TankInterface ti ;

    public TankNewMsg(TankInterface ti ) {
        this.ti = ti ;
    }
    public TankNewMsg(Tank tank) {
        this.tank = tank;
    }

    public void send(DatagramSocket ds , String ip ,int udpport){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        try {
            dos.writeInt(tank.id);
            dos.writeInt(tank.x);
            dos.writeInt(tank.y);
            dos.writeInt(tank.dir.ordinal());
            dos.writeInt(tank.groupId);
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
                System.out.println("zjx");
                return;
            }
            int x = dis.readInt();
            int y = dis.readInt();
            Direction dir = Direction.values()[dis.readInt()];
            int groupId = dis.readInt();

            Tank tank = new Tank(x,y,ti);
            tank.id = id ;
            ti.tanks.add(tank);
            System.out.println("id: " + id + "  x: "+ x + "  y: " + y +
                    "  dir: " + dir + "  groupId: " + groupId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
