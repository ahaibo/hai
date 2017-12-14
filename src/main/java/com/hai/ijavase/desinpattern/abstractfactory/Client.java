/**
 * 
 */
package com.hai.ijavase.desinpattern.abstractfactory;

import com.hai.ijavase.desinpattern.abstractfactory.factory.FemaleHumanFactory;
import com.hai.ijavase.desinpattern.abstractfactory.factory.HumanFactory;
import com.hai.ijavase.desinpattern.abstractfactory.factory.MaleHumanFactory;
import com.hai.ijavase.desinpattern.abstractfactory.human.Human;

/**
 * @author Administrator
 * 
 */
public class Client {

	public static void main(String[] args) {
		HumanFactory femaleHumanFactory = new FemaleHumanFactory();
		HumanFactory malehHumanFactory = new MaleHumanFactory();

		Human femaleBlackHuman = femaleHumanFactory.createWhiteHuman();
		Human maleBlackHuman = malehHumanFactory.createWhiteHuman();

		femaleBlackHuman.color();
		femaleBlackHuman.talk();
		femaleBlackHuman.sex();
		System.out.println("----------------");
		maleBlackHuman.color();
		maleBlackHuman.talk();
		maleBlackHuman.sex();
	}

}
