/**
 * 
 */
package com.hai.desinpattern.create.abstractfactory;

import com.hai.desinpattern.create.abstractfactory.factory.FemaleHumanFactory;
import com.hai.desinpattern.create.abstractfactory.factory.HumanFactory;
import com.hai.desinpattern.create.abstractfactory.factory.MaleHumanFactory;
import com.hai.desinpattern.create.abstractfactory.human.Human;

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
