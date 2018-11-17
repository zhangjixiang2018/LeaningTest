package screenbroadcast;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * 接收
 * 1.接收数据包
 *
 * 2.将数据包转化为数据单元，组装数据单元，形成一帧
 *
 * 3.显示
 */
public class Receiver extends Thread {

    private DatagramSocket sock = null ;
    private DatagramPacket pack = null ;
    private byte[] buf = new byte[Teacher.Frame_Until_Max + 14];
    private Map<Integer , FUntil> unitMap = new HashMap<Integer , FUntil>();
    private StudentUI studentUI = null ;

    public Receiver(StudentUI UI )
    {
        try {
            sock = new DatagramSocket(8888);
            pack = new DatagramPacket(buf,buf.length);
            this.studentUI = UI;
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
    public void run() {

        //1.
        try {
            long curredFrameId = 0;
            while (true)
            {

                //1.
                sock.receive(pack);
                //获取包中数据的长度
                int packLen = pack.getLength();

                //2.将数据包转化为数据单元
                FUntil dataUnit = converData2Unit(buf,packLen);

                long newFrameId = dataUnit.getFrameId();
                //判断是否为同一帧的不同数据单元，将同一帧的不同单元放入同一map中
                if(curredFrameId == newFrameId)
                {
                    unitMap.put(dataUnit.getUntilNo(),dataUnit);
                }
                else if(newFrameId > curredFrameId)
                {
                    curredFrameId = newFrameId ;
                    unitMap.clear();
                    unitMap.put(dataUnit.getUntilNo(),dataUnit);
                }

                processUnit(unitMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对帧单元进行处理
     * 若map的大小等于帧单元的个数，说明已经收到完整的一帧，
     * 那么就将这一帧组装起来，并调用UI进行显示
     * @param unitMap
     */
    private void processUnit(Map<Integer,FUntil> unitMap) {

        //得到一帧画面中单元的个数
        int count = unitMap.values().iterator().next().getUntilCount();
        ByteArrayOutputStream baos = null ;

        if(unitMap.size() == count)
        {
            baos = new ByteArrayOutputStream() ;
            for(int i =0 ; i < count ; i++)
            {
                byte[] unitData = new byte[unitMap.get(i).getDataLen()];
                unitData = unitMap.get(i).getData();
                baos.write(unitData,0,unitMap.get(i).getDataLen());
            }

            unitMap.clear();

            //
            try {
                studentUI.updateIcon(Util.unZip(baos.toByteArray()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //studentUI.updateIcon(baos.toByteArray());
        }
    }

    private FUntil converData2Unit(byte[] buf, int packLen) {

        //复制数据包中有效数据
        byte[] dataBytes = new byte[packLen];
        System.arraycopy(buf,0,dataBytes,0,packLen);

        FUntil unit = new FUntil();

        //
        unit.setFrameId(Util.bytes2Long(dataBytes));
        //
        unit.setUntilCount(dataBytes[8]);
        unit.setUntilNo(dataBytes[9]);

        //
        unit.setDataLen(Util.bytes2Int(dataBytes,10));

        //
        byte[] dataUnitBytes = new byte[unit.getDataLen()];

        System.arraycopy(dataBytes,14,dataUnitBytes,0 ,dataUnitBytes.length);
        unit.setData(dataUnitBytes);

        return unit;
    }

}
