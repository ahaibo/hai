/**
 * 
 */
package com.hai.javase.array;

import java.util.Arrays;

/**
 * 数组浅拷贝应用
 * 
 * @author Administrator
 * 
 */
public class ArrayShallowCopy {

	public static void main(String[] args) {
		// arrShallowCopyTest();
		baseDataTypeArrShallowCopyTest();
	}

	/**
	 * 基本数据类型的数组浅拷贝测试
	 */
	private static void baseDataTypeArrShallowCopyTest() {

		int[] arr1 = { 1, 2, 3, 4, 5 };

		int[] arr2 = Arrays.copyOf(arr1, arr1.length);

		arr2[arr2.length - 1] = arr2[arr2.length - 1] + 1;

		int[] arr3 = { 1, 2, 3, 4, 5 };

		int[] arr4 = Arrays.copyOfRange(arr3, 0, arr3.length);

		arr4[arr4.length - 1] = arr4[arr4.length - 1] + 10;

		System.out.println("Array Shallow Copy");
		System.out.println("by Arrays.copyOf - before copy: " + Arrays.toString(arr1) + "\tafter copy: "
				+ Arrays.toString(arr2));
		System.out.println("by Arrays.copyOfRange - before copy: " + Arrays.toString(arr3) + "\tafter copy: "
				+ Arrays.toString(arr4));
	}

	/**
	 * 引用类型数据的数组浅拷贝测试
	 */
	private static void arrShallowCopyTest() {
		ArrayShallowCopy aShallowCopy = new ArrayShallowCopy();
		// 气球的数量
		int ballonNum = 7;
		// 第一个箱子
		Ballon[] box1 = new Ballon[ballonNum];
		// 初始化第一个箱子
		for (int i = 0; i < ballonNum; i++) {
			box1[i] = aShallowCopy.new Ballon(Color.values()[i], i);
		}

		// 第二个箱子的小球是拷贝的第一个箱子里的
		// Ballon[] box2 = Arrays.copyOfRange(box1, 0, box1.length);
		Ballon[] box2 = Arrays.copyOf(box1, box1.length);
		// 修改最后一个气球的颜色
		box2[box2.length - 1].setColor(Color.Blue);
		// 打印出第一个箱子中的气球颜色
		for (Ballon b : box1) {
			System.out.println(b);
		}
	}

	// 气球的颜色
	enum Color {
		Red, Orange, Yellow, Green, Indigo, Blue, Violet;
	}

	// 气球
	class Ballon {
		// 编号
		private int id;
		// 颜色
		private Color color;

		public Ballon(Color _color, int _id) {
			color = _color;
			id = _id;
		}

		/* id、color的getter/setter方法省略 */
		// apache-common包下的ToStringBuilder重写toString方法
		public String toString() {
			return new StringBuilder().append("编号: ").append(this.id).append("。 颜色: ").append(this.color).toString();
		}

		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * @return the color
		 */
		public Color getColor() {
			return color;
		}

		/**
		 * @param color
		 *            the color to set
		 */
		public void setColor(Color color) {
			this.color = color;
		}

	}
}
