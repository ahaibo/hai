/**
 * 
 */
package com.hai.ijavase.iinterface;

/**
 * @author as
 * 
 */
public interface SubA extends Base {
	void subA();

	Base BASE = new Base() {

		@Override
		public void say() {
			// TODO Auto-generated method stub

		}

		@Override
		public void base() {
			// TODO Auto-generated method stub

		}
	};
}
