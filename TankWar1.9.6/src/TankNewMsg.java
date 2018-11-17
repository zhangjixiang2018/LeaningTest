import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TankNewMsg implements Msg{
    Tank tank ;
    TankInterface ti ;
    public int msgType = Msg.TANK_NEW_MSG ;

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
            dos.writeInt(msgType);
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
                System.out.println("TankNewMsg--parse--ti.myTank.id == id");
                return;
            }
            int x = dis.readInt();
            int y = dis.readInt();
            Direction dir = Direction.values()[dis.readInt()];
            int groupId = dis.readInt();

            //1.9.6收到TankNewMsg后判断这个坦克是否存在若不存在就在tanks
            //中加入这个坦克，并且自己也发送一个TankNewMsg,这样可以
            //防止tank之间无休止的发送tankNewMsg。
            boolean exist = false ;
            for(int i=0 ; i<ti.tanks.size() ; i++){
                Tank t = ti.tanks.get(i);
                if(t.id == id){
                    exist = true;
                }
            }
            if(exist == false){
                Tank tank = new Tank(x,y,ti);
                tank.id = id ;
                ti.tanks.add(tank);
                TankNewMsg msg = new TankNewMsg(ti.myTank);
                ti.nc.send(msg);
            }

            System.out.println("id: " + id + "  x: "+ x + "  y: " + y +
                    "  dir: " + dir + "  groupId: " + groupId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
