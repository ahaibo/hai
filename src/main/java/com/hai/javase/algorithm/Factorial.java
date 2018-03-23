package com.hai.javase.algorithm;

import java.math.BigInteger;

/**
 * 阶乘计算
 * 
 * @author Administrator
 * 
 */
public class Factorial {
	private Factorial() {
	}

	// 运用BigInteger类计算整数的阶乘，n的取值范围为任意正整数
	public static BigInteger getBigFactorial(int n) {
		if (n < 0) {
			System.err.println("n必须大于等于0！");
			return new BigInteger("-1");
		} else if (n == 0) {
			return new BigInteger("0");
		}
		BigInteger result = new BigInteger("1");
		for (; n > 0; n--) {
			result = result.multiply(new BigInteger(n + ""));
		}
		return result;
	}

	// 运用基本数据类型计算整数的阶乘，n的取值范围为1~17
	public static long getLongFactorial(int n) {
		if (n < 0) {
			System.err.println("n必须大于等于0！");
			return -1;
		} else if (n == 0) {
			return 0;
		}
		long result = 1;
		for (; n > 0; n--) {
			result *= (long) n;
		}
		return result;
	}
}