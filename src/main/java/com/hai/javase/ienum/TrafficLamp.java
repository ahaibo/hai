/**
 * 
 */
package com.hai.javase.ienum;

/**
 * 以模拟交通灯来练习使用JDK枚举且带抽象方法
 * 
 * @author Administrator
 * 
 */
public enum TrafficLamp {
	RED(90) {
		@Override
		public TrafficLamp nextLamp() {
			return GREEN;
		}
	},
	GREEN(30) {
		@Override
		public TrafficLamp nextLamp() {
			return YELLOW;
		}
	},
	YELLOW(5) {
		@Override
		public TrafficLamp nextLamp() {
			return RED;
		}
	};

	private int time;

	private TrafficLamp() {
	}

	private TrafficLamp(int time) {
		this.time = time;
	}

	public abstract TrafficLamp nextLamp();

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

}
