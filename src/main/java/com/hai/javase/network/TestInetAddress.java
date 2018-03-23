/**
 * 
 */
package com.hai.javase.network;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author as
 *
 */
public class TestInetAddress {

	@Test
	public void test1() {
		try {
			InetAddress address = InetAddress.getByName("atguigu.com");
			System.out.println("getHostAddress: " + address.getHostAddress());
			System.out.println("getHostName: " + address.getHostName());
			// System.out.println("getCanonicalHostName: " + address.getCanonicalHostName());
			System.out.println("getAddress: " + address.getAddress());

			InetAddress local = InetAddress.getLocalHost();
			InetAddress loopback = InetAddress.getLoopbackAddress();
			System.out.println(local);
			System.out.println(loopback);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2() throws UnknownHostException {
		InetAddress[] addressArr = InetAddress.getAllByName("www.baidu.com");
		for (InetAddress inetAddress : addressArr) {
			System.out.println(inetAddress);
		}
	}
}
