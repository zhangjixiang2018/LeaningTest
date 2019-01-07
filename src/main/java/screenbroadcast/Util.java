package screenbroadcast;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Util {

    //截屏，返回屏幕数据的字节数组
    public static byte[] captureSreen() throws Exception {
        Robot robot = new Robot();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //获取屏幕的大小，这里指的是分辨率
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int weight = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        //截屏
        BufferedImage image = robot.createScreenCapture(new Rectangle(0,0,weight,height));
        //将屏幕的数据写入baos中
        ImageIO.write(image,"jpg",baos);

        return baos.toByteArray();
    }

    /**
     *
     * @param l
     * @return
     */
    public static byte[] long2Bytes(long l)
    {
        byte[] lbytes = new byte[8];

        lbytes[0] = (byte) l ;
        lbytes[1] = (byte) (l >> 8) ;
        lbytes[2] = (byte) (l >> 16) ;
        lbytes[3] = (byte) (l >> 24) ;
        lbytes[4] = (byte) (l >> 32) ;
        lbytes[5] = (byte) (l >> 40) ;
        lbytes[6] = (byte) (l >> 48) ;
        lbytes[7] = (byte) (l >> 56) ;
        return lbytes;
    }


    public static long bytes2Long(byte[] lbytes)
    {
        long l ;

        l = (long)lbytes[0] & 0xFF
                | (long)(lbytes[1] & 0xFF ) << 8
                | (long)(lbytes[2] & 0xFF ) << 16
                | (long)(lbytes[3] & 0xFF ) << 24
                | (long)(lbytes[4] & 0xFF ) << 32
                | (long)(lbytes[5] & 0xFF ) << 40
                | (long)(lbytes[6] & 0xFF ) << 48
                | (long)(lbytes[7] & 0xFF ) << 56 ;
        return l;
    }


    public static byte[] int2Bytes(int n)
    {
        byte[] bytes = new byte[4];

        bytes[0] = (byte) n ;
        bytes[1] = (byte) (n >> 8) ;
        bytes[2] = (byte) (n >> 16) ;
        bytes[3] = (byte) (n >> 24) ;

        return bytes;
    }


    public static int bytes2Int(byte[] bytes)
    {
        int n ;

        n = bytes[0] & 0xFF
                | (bytes[1] & 0xFF ) << 8
                | (bytes[2] & 0xFF ) << 16
                | (bytes[3] & 0xFF ) << 24;

        return n;
    }

    public static int bytes2Int(byte[] bytes , int offset)
    {
        int n ;

        n = bytes[0 + offset] & 0xFF
                | (bytes[1 + offset] & 0xFF ) << 8
                | (bytes[2 + offset] & 0xFF ) << 16
                | (bytes[3 + offset] & 0xFF ) << 24;

        return n;
    }

    /**
     *压缩字节数组，返回压缩后的字节数组
     */
    public static byte[] zip(byte[] rawData)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        try {
            zos.putNextEntry(new ZipEntry("one"));
            zos.write(rawData);

            zos.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    /**
     * 解压
     */
    public static byte[] unZip(byte[] zipData) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(zipData);
        ZipInputStream zis = new ZipInputStream(bais);

        byte[] buf = new byte[1024];
        int len = 0;
        zis.getNextEntry();

        try {
            while( (len = zis.read(buf))!= -1)
            {
                baos.write(buf,0,len);
            }
            zis.closeEntry();
            zis.close();
            bais.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* while( (len = zis.read(buf))!= -1)
        {
            baos.write(buf,0,len);
        }*/


        return baos.toByteArray();
    }
}
