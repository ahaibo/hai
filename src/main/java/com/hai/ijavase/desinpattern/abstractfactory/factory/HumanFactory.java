/**
 * 
 */
package com.hai.ijavase.desinpattern.abstractfactory.factory;

import com.hai.ijavase.desinpattern.abstractfactory.human.Human;

/**
 * @author Administrator
 * 
 */
public interface HumanFactory {
	Human createWhiteHuman();

	Human createBlackHuman();

	Human createYellowHuman();
}
