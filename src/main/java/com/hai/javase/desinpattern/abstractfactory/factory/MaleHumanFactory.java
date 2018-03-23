/**
 * 
 */
package com.hai.javase.desinpattern.abstractfactory.factory;

import com.hai.javase.desinpattern.abstractfactory.human.Human;
import com.hai.javase.desinpattern.abstractfactory.human.MaleBlackHuman;
import com.hai.javase.desinpattern.abstractfactory.human.MaleWhiteHuman;
import com.hai.javase.desinpattern.abstractfactory.human.MaleYellowHuman;

/**
 * @author Administrator
 * 
 */
public class MaleHumanFactory implements HumanFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hai.hai.javase.desinpattern.abstractfactory.HumanFactory#createWhiteHuman()
	 */
	@Override
	public Human createWhiteHuman() {
		return new MaleWhiteHuman();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hai.hai.javase.desinpattern.abstractfactory.HumanFactory#createBlackHuman()
	 */
	@Override
	public Human createBlackHuman() {
		return new MaleBlackHuman();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hai.hai.javase.desinpattern.abstractfactory.HumanFactory#createYellowHuman()
	 */
	@Override
	public Human createYellowHuman() {
		return new MaleYellowHuman();
	}

}
