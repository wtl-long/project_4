package com.atguigu.java1;

/*
    UDP协议的网络编程
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPTest
{
    public static void main(String[] args)
    {
        sender();
    }

    //发送端
    public static void sender()
    {
        DatagramSocket datagramSocket = null;
        try
        {
            datagramSocket = new DatagramSocket();
            String str = "我是UDP方式发送的导弹";
            byte[] bytes = str.getBytes();
            InetAddress localHost = InetAddress.getLocalHost();
            DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length, localHost, 8888);
            datagramSocket.send(datagramPacket);
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            datagramSocket.close();
        }


    }

    //接收端
    public static void receiver()
    {
        DatagramSocket datagramSocket = null;
        try
        {
            datagramSocket = new DatagramSocket(8888);
            byte[] bytes = new byte[100];
            DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);
            datagramSocket.receive(datagramPacket);
            System.out.println(new String(datagramPacket.getData(), 0, datagramPacket.getLength()));
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            datagramSocket.close();
        }

    }


}
