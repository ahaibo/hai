package com.hai.javase.reflect;

/**
 * Java复合类练习<什么是Java复合类？未果>
 * 
 * @author Administrator
 * 
 */
public class SyntheticClass {

	private String id;

	private int count;

	public SyntheticClass() {
		this.count++;
	}

	public class Inner {
		private int innerCount;

		public Inner() {
			this.innerCount++;
		}

		/**
		 * @return the innerCount
		 */
		public int getInnerCount() {
			return innerCount + count;
		}

		/**
		 * @param innerCount
		 *            the innerCount to set
		 */
		public void setInnerCount(int innerCount) {
			this.innerCount = innerCount;
		}

	}

	public static void main(String[] args) {
		Inner inner = new SyntheticClass().new Inner();
		System.out.println(SyntheticClass.class.isSynthetic());
		System.out.println(inner.getInnerCount());
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
