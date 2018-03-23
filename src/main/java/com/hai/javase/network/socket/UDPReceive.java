/**
 * 
 */
package com.hai.javase.network.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Administrator
 * 
 */
public class UDPReceive
{
	public static void main(String[] args)
	{
		int receivePort = 10000;
		DatagramSocket datagramSocket;
		try
		{
			datagramSocket = new DatagramSocket(receivePort);
			byte[] buffer = new byte[1024];
			DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
			
			datagramSocket.receive(datagramPacket);
			
			InetAddress address = datagramPacket.getAddress();
			System.out.println(address.getHostAddress() + " - " + address.getHostName());
			
			String receiveData = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
			System.out.println("receiveData: " + receiveData);
			
			datagramSocket.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
