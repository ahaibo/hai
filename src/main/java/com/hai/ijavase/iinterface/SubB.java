/**
 * 
 */
package com.hai.ijavase.iinterface;

import java.util.Random;

/**
 * @author as
 * 
 */
public interface SubB extends SubA, Base {
	int RAND_CONST = new Random().nextInt();

	void subB();

	void say();
}
