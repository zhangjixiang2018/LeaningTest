package screenbroadcast.Test;

import java.awt.*;

public class Test {

   @org.junit.Test
    public  void TestScreenSize()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int dpi = Toolkit.getDefaultToolkit().getScreenResolution();

        System.out.println(width + "  " + height);
        System.out.println(dpi);
        System.out.println(width/dpi + "  " + height/dpi);
    }

    @org.junit.Test
    public void test()
    {
        long l = -12348976;
        byte[] bytes = long2Bytes(l);

        System.out.println(bytes2Long(bytes));

        int n = -8888 ;
        byte[] b = int2Bytes(n);
        System.out.println(bytes2Int(b));

    }

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

}
