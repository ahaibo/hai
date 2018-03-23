/**
 * 
 */
package com.hai.javase.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 * @author as
 *
 */
public class TestTCP2 {

	String host = "127.0.0.1";
	int port = 9090;
	int buffer = 100;

	@Test
	public void server() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream in = null;
		BufferedInputStream bis = null;
		OutputStream out = null;
		BufferedOutputStream bos = null;

		try {
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();
			System.out.println("Server1 main up...");
			in = socket.getInputStream();
			bis = new BufferedInputStream(in);
			byte[] b = new byte[buffer];
			int len;
			// 读取客户端信息
			StringBuffer sb = new StringBuffer();
			while ((len = bis.read(b)) != -1) {
				sb.append(new String(b, 0, len));
			}
			System.out.println("Server1 received client " + socket.getRemoteSocketAddress() + " msg: " + sb);
			// 回写客户端信息
			out = socket.getOutputStream();
			bos = new BufferedOutputStream(out);
			bos.write("Server1 handle finished.".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(in, bis, out, bos, socket, serverSocket);
		}
	}

	@Test
	public void client() {
		Socket socket = null;
		InputStream in = null;
		BufferedInputStream bis = null;
		OutputStream out = null;
		BufferedOutputStream bos = null;
		try {
			socket = new Socket(InetAddress.getByName(host), port);
			out = socket.getOutputStream();
			bos = new BufferedOutputStream(out);
			bos.write("I am client.".getBytes());
			// out.write("I am client.".getBytes());
			socket.shutdownOutput();// 通知服务端写入完毕

			in = socket.getInputStream();
			bis = new BufferedInputStream(in);
			byte[] b = new byte[buffer];
			int len;
			StringBuffer sb = new StringBuffer();
			while ((len = bis.read(b)) != -1) {
				sb.append(new String(b, 0, len));
			}
			System.out.println("Client received server " + socket.getRemoteSocketAddress() + " msg: " + sb);
		} catch (Exception e) {
		} finally {
			// 注：此处只能关OutputStream，不能关BufferedOutputStream，原因暂时不明 TODO
			close(null, null, out, null, socket, null);
		}
	}

	// 关闭连接，流
	private void close(InputStream in, BufferedInputStream bis, OutputStream out, BufferedOutputStream bos, Socket socket,
			ServerSocket serverSocket) {
		if (null != bos) {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (null != out) {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (null != bis) {
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (null != in) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (null != socket) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (null != serverSocket) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
