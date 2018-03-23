/**
 * 
 */
package com.hai.javase.desinpattern.abstractfactory.factory;

import com.hai.javase.desinpattern.abstractfactory.human.Human;

/**
 * @author Administrator
 * 
 */
public interface HumanFactory {
	Human createWhiteHuman();

	Human createBlackHuman();

	Human createYellowHuman();
}
