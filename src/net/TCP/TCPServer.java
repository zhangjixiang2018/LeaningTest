package net.TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null ; //创建服务端套接字
        Socket clientSocket = null ;//创建客户端套接字
        DataInputStream dis = null ;//创建 DataInputStream 对象
        DataOutputStream dos = null ;//创建 DataOutputStream 对象
        BufferedReader br = null ;//创建BufferedReader对象

        try {
            serverSocket = new ServerSocket(8888);//实例化ServerSocket对象
            clientSocket = serverSocket.accept();//接收客户的套接字连接呼叫
            dis = new DataInputStream(clientSocket.getInputStream());//从客户端套接字得到输入流
            dos = new DataOutputStream(clientSocket.getOutputStream());//从客户端套接字得到输出流
            br = new BufferedReader(new InputStreamReader(System.in));//

            String str ;
            str = dis.readUTF();
            while(!str.equals("bye")){
                System.out.print("client: ");
                System.out.println(str);
                str = br.readLine();//从标准输入中读取字符串
                dos.writeUTF(str);//将字符串写入流中
                System.out.print("server: ");
                System.out.println(str);
                str = dis.readUTF();//从流中读取字符串
            }

            dis.close();
            dos.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
