package com.hai.ijavase.ienum;

/**
 * 以Java类的形式模拟枚举2，修改成员方法nextDay()为抽象方法；用每个项的匿名类实现
 * 
 * @author Administrator
 * @since 2013/9/2
 * 
 */
public abstract class Weekday2 {

	private Weekday2() {
	}

	public static final Weekday2 Monday = new Weekday2() {

		@Override
		public Weekday2 nextDay() {
			return Tuesday;
		}

	};

	public static final Weekday2 Tuesday = new Weekday2() {

		@Override
		public Weekday2 nextDay() {
			return Wednesday;
		}

	};

	public static final Weekday2 Wednesday = new Weekday2() {

		@Override
		public Weekday2 nextDay() {
			return Thursday;
		}

	};

	public static final Weekday2 Thursday = new Weekday2() {

		@Override
		public Weekday2 nextDay() {
			return Friday;
		}

	};

	public static final Weekday2 Friday = new Weekday2() {

		@Override
		public Weekday2 nextDay() {
			return Saturday;
		}

	};

	public static final Weekday2 Saturday = new Weekday2() {

		@Override
		public Weekday2 nextDay() {
			return Sunday;
		}

	};

	public static final Weekday2 Sunday = new Weekday2() {

		@Override
		public Weekday2 nextDay() {
			return Monday;
		}

	};

	/**
	 * 模拟实现一个下一天的方法[原始版]
	 * 
	 * @return
	 */
	public abstract Weekday2 nextDay();

	// public Weekday2 nextDay() {
	// if (this == Monday) {
	// return Tuesday;
	// } else if (this == Tuesday) {
	// return Wednesday;
	// } else if (this == Wednesday) {
	// return Thursday;
	// } else if (this == Thursday) {
	// return Friday;
	// } else if (this == Friday) {
	// return Saturday;
	// } else if (this == Saturday) {
	// return Sunday;
	// } else {
	// return Monday;
	// }
	// }

	@Override
	public String toString() {

		return this == Monday ? "Mon." : this == Tuesday ? "Tues."
				: this == Wednesday ? "Weds." : this == Thursday ? "Thurs."
						: this == Friday ? "Fri." : this == Saturday ? "Sat."
								: this == Sunday ? "Sun." : "Mon.";
	}

}
