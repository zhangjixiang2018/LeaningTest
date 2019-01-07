package io.archiver;

import org.junit.Test;

public class Util {


    @Test
    public  void tset()
    {
        System.out.println(
                bytes2Int(int2Bytes(-12345))
        );
    }

    public static byte[] int2Bytes(int n) {
        byte[] bytes = new byte[4];

        bytes[0] = (byte) n ;
        bytes[1] = (byte) (n >> 8) ;
        bytes[2] = (byte) (n >> 16) ;
        bytes[3] = (byte) (n >> 24) ;
        return bytes;
    }

    public static int bytes2Int(byte[] bytes) {
        int n ;

        n = bytes[0] & 0xFF
                | (bytes[1] & 0xFF) << 8
                | (bytes[2] & 0xFF) << 16
                | (bytes[3] & 0xFF) << 24 ;
        return n;
    }
}
