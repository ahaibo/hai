/**
 * 
 */
package com.hai.ijavase.inetwork.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Administrator
 * 
 */
public class UDPSend
{
	public static void main(String[] args) throws UnknownHostException
	{
		try
		{
			DatagramSocket datagramSocket = new DatagramSocket();
			
			byte[] data = "UDP DatagramSocket Send TestDemo ...".getBytes();
			InetAddress inetAddress = InetAddress.getByName("192.168.1.102");
			int sendPort = 10000;
			DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, sendPort);
			datagramSocket.send(datagramPacket);
			
			datagramSocket.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
