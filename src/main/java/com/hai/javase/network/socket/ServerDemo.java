/**
 * 
 */
package com.hai.javase.network.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author as
 * 
 */
public class ServerDemo {

	public static void main(String[] args) throws IOException {

		ServerSocket ss = new ServerSocket(11000);
		Socket s = ss.accept();

		String ip = s.getInetAddress().getHostAddress();
		String host = s.getInetAddress().getHostName();
		System.out.println(ip + " - " + host + " connected!");

		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

		pw.println("<font color='red'>Welcome!</font>");

		s.close();
		ss.close();
	}
}
