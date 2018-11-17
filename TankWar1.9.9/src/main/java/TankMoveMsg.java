import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TankMoveMsg implements Msg{

    Tank tank ;
    public int tankId ;
    public Direction tankDir ;
    TankInterface ti ;
    public int msgType = Msg.TANK_MOVE_MSG ;

    public TankMoveMsg(Tank tank, int tankId, Direction tankDir) {
        this.tank = tank;
        this.tankId = tankId;
        this.tankDir = tankDir;
    }

    public TankMoveMsg(TankInterface ti) {
        this.ti = ti;
    }

    public TankMoveMsg(Tank tank) {
        this.tank = tank;
    }

    public void send(DatagramSocket ds, String ip, int udpport) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        try {
            dos.writeInt(msgType);
            dos.writeInt(tankId);
            dos.writeInt(tankDir.ordinal());
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
            System.out.println("TankMoveMsg--parse");
            int id = dis.readInt();
            if(ti.myTank.id == id ){
                System.out.println("TankMoveMsg--parse--ti.myTank.id == id");
                return;
            }

            Direction dir = Direction.values()[dis.readInt()];

            //遍历tanks集合判断发送消息的坦克是否存在，存在就改变它的方向
            boolean exist = false ;
            for(int i=0 ; i<ti.tanks.size() ; i++){
                Tank t = ti.tanks.get(i);

                if(id == t.id){
                    System.out.println("oldDir==" + t.dir);
                    t.dir = dir ;
                    exist = true ;
                    System.out.println("newDir==" + t.dir);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
