/**
 * 
 */
package com.hai.desinpattern.create.abstractfactory.factory;

import com.hai.desinpattern.create.abstractfactory.human.Human;

/**
 * @author Administrator
 * 
 */
public interface HumanFactory {
	Human createWhiteHuman();

	Human createBlackHuman();

	Human createYellowHuman();
}
