package com.hai.ijavase.ienum;

public class EnumTest {

	public static void main(String[] args) {
		Weekday currDay = Weekday.Wednesday;
		System.out.println("今天：" + currDay + "，明天：" + currDay.nextDay());

		Weekday2 currDay1 = Weekday2.Wednesday;
		System.out.println("今天：" + currDay1 + "，明天：" + currDay1.nextDay());

		TrafficLamp trafficLamp = TrafficLamp.GREEN;
		System.out.println("当前交通指示灯：" + trafficLamp + "，马上是 "
				+ trafficLamp.nextLamp() + " 灯。");

		System.out.println(Weekday2.Friday.getClass().isSynthetic());
	}
}
