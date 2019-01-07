package net.TCP;

import java.io.*;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        Socket socket = null ; //创建客户端套接字
        DataInputStream dis = null ;//创建 DataInputStream 对象
        DataOutputStream dos = null ;//创建 DataOutputStream 对象
        BufferedReader br = null ;//创建BufferedReader对象

        try {
            socket = new Socket("127.0.0.1",8888);//实例化客户端套接字
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));

            String str = null ;
            str = br.readLine();//从标准输入中读取字符串
            while(!str.equals("bye")){
                System.out.print("client: ");
                System.out.println(str);
                dos.writeUTF(str);
                str = dis.readUTF();//从流中读取字符串
                System.out.print("server: ");
                System.out.println(str);

                str = br.readLine();//从标准输入中读取字符串
            }

            dis.close();
            dos.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
