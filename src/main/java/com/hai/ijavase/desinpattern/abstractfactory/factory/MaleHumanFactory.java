/**
 * 
 */
package com.hai.ijavase.desinpattern.abstractfactory.factory;

import com.hai.ijavase.desinpattern.abstractfactory.human.Human;
import com.hai.ijavase.desinpattern.abstractfactory.human.MaleBlackHuman;
import com.hai.ijavase.desinpattern.abstractfactory.human.MaleWhiteHuman;
import com.hai.ijavase.desinpattern.abstractfactory.human.MaleYellowHuman;

/**
 * @author Administrator
 * 
 */
public class MaleHumanFactory implements HumanFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hai.hai.ijavase.desinpattern.abstractfactory.HumanFactory#createWhiteHuman()
	 */
	@Override
	public Human createWhiteHuman() {
		return new MaleWhiteHuman();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hai.hai.ijavase.desinpattern.abstractfactory.HumanFactory#createBlackHuman()
	 */
	@Override
	public Human createBlackHuman() {
		return new MaleBlackHuman();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hai.hai.ijavase.desinpattern.abstractfactory.HumanFactory#createYellowHuman()
	 */
	@Override
	public Human createYellowHuman() {
		return new MaleYellowHuman();
	}

}
