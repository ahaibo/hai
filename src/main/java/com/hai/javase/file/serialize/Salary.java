/**
 * 
 */
package com.hai.javase.file.serialize;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 */
public class Salary implements Serializable {

	private static final long serialVersionUID = 6194229087833160160L;

	private int basePay;
	private int bonus;

	public Salary() {
	}

	/**
	 * @param basePay
	 * @param bonus
	 */
	public Salary(int basePay, int bonus) {
		this.basePay = basePay;
		this.bonus = bonus;
	}

	/**
	 * @return the basePay
	 */
	public int getBasePay() {
		return basePay;
	}

	/**
	 * @param basePay
	 *            the basePay to set
	 */
	public void setBasePay(int basePay) {
		this.basePay = basePay;
	}

	/**
	 * @return the bonus
	 */
	public int getBonus() {
		return bonus;
	}

	/**
	 * @param bonus
	 *            the bonus to set
	 */
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

}
