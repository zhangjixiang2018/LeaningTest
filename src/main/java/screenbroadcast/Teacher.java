/**
 * 教师端
 * 1.截屏
 *
 * 2.切屏
 *
 * 3.发送
 */
package screenbroadcast;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Teacher {

    //帧单元的最大大小
    public static final int Frame_Until_Max = 60 * 1024;

    public static DatagramSocket sock ;

    static {
        try {
            sock = new DatagramSocket(9999);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        while(true)
        {
            sendOneScreenData();//发送一屏数据
        }
    }

   public static  void sendOneScreenData() {

       try {
           //1.截屏
           byte[] frame = Util.captureSreen();

           //压缩
           frame = Util.zip(frame);
           //2.切屏
           List<FUntil> frameUntilList = splitFrame(frame);

           //3.发送
           send(frameUntilList);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

    private static void send(List<FUntil> frameUntilList)  {

        for(FUntil fu : frameUntilList)
        {
            byte[] frameUntilDataPack = new  byte[fu.getDataLen() + 14];

            //frameId
            byte[] frameIdBytes = Util.long2Bytes(fu.getFrameId());
            System.arraycopy(frameIdBytes,0,frameUntilDataPack,0,8);

            //UntilCount
            frameUntilDataPack[8] = (byte)fu.getUntilCount();
            //
            frameUntilDataPack[9] = (byte)fu.getUntilNo();

            //DataLen
            byte[] dataLenBytes = Util.int2Bytes(fu.getDataLen());
            System.arraycopy(dataLenBytes,0,frameUntilDataPack,10,4);

            //data
            System.arraycopy(fu.getData(),0,
                    frameUntilDataPack,14,fu.getDataLen());

            DatagramPacket pack = new DatagramPacket(frameUntilDataPack,fu.getDataLen() + 14);
            pack.setSocketAddress(new InetSocketAddress("192.168.11.1",8888));

            try {
                sock.send(pack);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //data

        }
    }

    private static List<FUntil> splitFrame(byte[] frame) {

        List<FUntil> UtilList = new ArrayList<FUntil>();

        //帧单元的个数
        int count = 0;
        if(frame.length % Frame_Until_Max == 0)
        {
            count = frame.length / Frame_Until_Max;
        }
        else
        {
            count = frame.length / Frame_Until_Max + 1 ;
        }

        //获取时间戳作为FrameId
        long frameId = System.currentTimeMillis();

        FUntil until = null ;

        for(int i = 0 ; i < count ; i++)
        {
            until = new FUntil();
            until.setFrameId(frameId);
            until.setUntilCount(count);
            until.setUntilNo(i);

            //判断是否为最后一帧
            if(i == count - 1 )
            {
                //判断帧能不能被整分成单元
                if(frame.length % Frame_Until_Max == 0)
                {
                    until.setDataLen(Frame_Until_Max);
                    byte[] untilData = new byte[Frame_Until_Max];
                    System.arraycopy(frame,i * Frame_Until_Max ,untilData,0,Frame_Until_Max);
                    until.setData(untilData);
                }
                else
                {
                    until.setDataLen(frame.length % Frame_Until_Max);
                    byte[] untilData = new byte[frame.length % Frame_Until_Max];
                    System.arraycopy(frame,i * Frame_Until_Max ,untilData,
                            0,frame.length % Frame_Until_Max);
                    until.setData(untilData);
                }
            }
            else
            {
                until.setDataLen(Frame_Until_Max);
                byte[] untilData = new byte[Frame_Until_Max];
                System.arraycopy(frame,i * Frame_Until_Max ,untilData,0,Frame_Until_Max);
                until.setData(untilData);
            }

            UtilList.add(until);
        }
        return UtilList;
    }
}
