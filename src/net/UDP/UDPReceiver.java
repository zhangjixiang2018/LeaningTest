package net.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPReceiver {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(8888);
            byte[] bytes = new byte[1024];
            DatagramPacket pack = new DatagramPacket(bytes,bytes.length);

            while (true){
                socket.receive(pack);
                int dataLen = pack.getLength();
                String str = new String(bytes,0,dataLen);
                System.out.println("收到了:" + str + "======长度为：" + dataLen);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
