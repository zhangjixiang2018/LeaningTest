package net.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UDPSender {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9999);

            int i = 0;
            for(; ;){
                byte[] bytes = ("hello world " + i++).getBytes();
                DatagramPacket packet = new DatagramPacket(bytes,bytes.length);
                packet.setSocketAddress(new InetSocketAddress("192.168.11.1",8888));
                socket.send(packet);
                Thread.sleep(1000);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello word");
    }
}
