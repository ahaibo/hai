/**
 * 
 */
package com.hai.ijavase.inetwork;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 * 发送图片
 * 
 * @author as
 *
 */
public class TestTCP3 {

	String host = "127.0.0.1";
	int port = 9090;
	int buffer = 1024;
	String path = "file/imgs/2016-05-31_221049";
	String imgSuffix = ".png";

	@Test
	public void client() {
		Socket socket = null;
		InputStream in = null;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedInputStream fbis = null;
		OutputStream out = null;
		BufferedOutputStream bos = null;
		try {
			socket = new Socket(InetAddress.getByName(host), port);
			out = socket.getOutputStream();
			bos = new BufferedOutputStream(out);

			fis = new FileInputStream(new File(path + imgSuffix));
			fbis = new BufferedInputStream(fis);
			byte[] b = new byte[buffer];
			int len;
			while ((len = fbis.read(b)) != -1) {
				bos.write(b, 0, len);
			}
			socket.shutdownOutput();// 通知服务端写入完毕

			// 接收服务端回写信息
			in = socket.getInputStream();
			bis = new BufferedInputStream(in);
			byte[] b2 = new byte[buffer];
			int len2;
			StringBuffer sb = new StringBuffer();
			while ((len2 = bis.read(b2)) != -1) {
				sb.append(new String(b2, 0, len2));
			}
			System.out.println("Client received server " + socket.getRemoteSocketAddress() + " msg: " + sb);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 注：此处只能关OutputStream，不能关BufferedOutputStream，原因暂时不明 TODO
			close(fis, fbis, null, null, null, null);
			close(null, null, out, null, socket, null);
		}
	}

	@Test
	public void server() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream in = null;
		BufferedInputStream bis = null;
		OutputStream out = null;
		BufferedOutputStream bos = null;// 回写至客户端流
		BufferedOutputStream fbos = null;// 文件写入流

		try {
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();
			System.out.println("Server1 main up...");
			in = socket.getInputStream();
			bis = new BufferedInputStream(in);
			byte[] b = new byte[buffer];
			int len;
			// 读取客户端图片流
			fbos = new BufferedOutputStream(new FileOutputStream(new File(path + "-" + System.currentTimeMillis() + imgSuffix)));
			long start = System.currentTimeMillis();
			while ((len = bis.read(b)) != -1) {
				fbos.write(b, 0, len);
				fbos.flush();
			}
			long end = System.currentTimeMillis();
			long diffTime = end - start;
			System.out.println("Server1 received client " + socket.getRemoteSocketAddress() + " img.");
			System.out.println("Read img time: " + diffTime + " milliseconds.");
			// 回写客户端信息
			out = socket.getOutputStream();
			bos = new BufferedOutputStream(out);
			bos.write("Server1 handle finished.".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(null, null, null, fbos, null, null);
			close(in, bis, out, bos, socket, serverSocket);
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
