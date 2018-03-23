package com.hai.javase.ienum;

/**
 * 以Java类的形式模拟枚举
 * 
 * @author Administrator
 * @since 2013/9/2
 * 
 */
public class Weekday {

	private Weekday() {
	}

	public static final Weekday Monday = new Weekday();
	public static final Weekday Tuesday = new Weekday();
	public static final Weekday Wednesday = new Weekday();
	public static final Weekday Thursday = new Weekday();
	public static final Weekday Friday = new Weekday();
	public static final Weekday Saturday = new Weekday();
	public static final Weekday Sunday = new Weekday();

	/**
	 * 模拟实现一个下一天的方法[原始版]
	 * 
	 * @return
	 */
	public Weekday nextDay() {
		if (this == Monday) {
			return Tuesday;
		} else if (this == Tuesday) {
			return Wednesday;
		} else if (this == Wednesday) {
			return Thursday;
		} else if (this == Thursday) {
			return Friday;
		} else if (this == Friday) {
			return Saturday;
		} else if (this == Saturday) {
			return Sunday;
		} else {
			return Monday;
		}
	}

	@Override
	public String toString() {

		return this == Monday ? "Mon." : this == Tuesday ? "Tues."
				: this == Wednesday ? "Weds." : this == Thursday ? "Thurs."
						: this == Friday ? "Fri." : this == Saturday ? "Sat."
								: this == Sunday ? "Sun." : "Mon.";
	}

}
