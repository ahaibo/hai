/**
 * 
 */
package com.hai.ijavase.file.serialize;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Administrator
 * 
 */
public class Person implements Serializable {

	private static final long serialVersionUID = -2087792776621536359L;

	private String id;
	private String name;
	private String sex;
	private int age;
	private Salary salary;

	public Person() {
	}

	/**
	 * @param id
	 * @param name
	 * @param sex
	 * @param age
	 * @param salary
	 */
	public Person(String id, String name, String sex, int age, Salary salary) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.salary = salary;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the salary
	 */
	public Salary getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private void writeObject(ObjectOutputStream os) {
		try {
			os.defaultWriteObject();
			os.writeInt(this.salary.getBasePay());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			salary = new Salary(ois.readInt(), 0);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuffer personStringBuffer = new StringBuffer("This person simple info: \n");
		personStringBuffer.append("name: " + this.name + "\tsex: " + this.sex + "\tsalary: " + this.salary.getBasePay()
				+ "\tbonus: " + this.salary.getBonus());

		return null == this ? super.toString() : personStringBuffer.toString();
	}

}
