/**
 * 
 */
package com.hai.desinpattern.create.factory;

/**
 * @author Administrator
 * 
 */
public class MethodFactoryDemo
{
	public static void main(String[] args)
	{
		AbstractHumanFactory humanFactory = new HumanFactory();
		
		WhiteHuman whiteHuman = humanFactory.createHuman(WhiteHuman.class);
		whiteHuman.getColor();
		whiteHuman.talk();
		
		BlackHuman blackHuman = humanFactory.createHuman(BlackHuman.class);
		blackHuman.getColor();
		blackHuman.talk();
		
		YellowHuman yellowHuman = humanFactory.createHuman(YellowHuman.class);
		yellowHuman.getColor();
		yellowHuman.talk();
	}
}
