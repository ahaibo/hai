/**
 * 
 */
package com.hai.javase.desinpattern.abstractfactory.factory;

import com.hai.javase.desinpattern.abstractfactory.human.FemaleBlackHuman;
import com.hai.javase.desinpattern.abstractfactory.human.FemaleWhiteHuman;
import com.hai.javase.desinpattern.abstractfactory.human.FemaleYellowHuman;
import com.hai.javase.desinpattern.abstractfactory.human.Human;

/**
 * @author Administrator
 * 
 */
public class FemaleHumanFactory implements HumanFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hai.hai.javase.desinpattern.abstractfactory.HumanFactory#createWhiteHuman()
	 */
	@Override
	public Human createWhiteHuman() {
		return new FemaleWhiteHuman();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hai.hai.javase.desinpattern.abstractfactory.HumanFactory#createBlackHuman()
	 */
	@Override
	public Human createBlackHuman() {
		return new FemaleBlackHuman();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hai.hai.javase.desinpattern.abstractfactory.HumanFactory#createYellowHuman()
	 */
	@Override
	public Human createYellowHuman() {
		return new FemaleYellowHuman();
	}

}
