package com.hai.common.db.mydatasource.test.entity;

import java.util.Date;

/**
 * @author Administrator
 * 
 */
public class User {
	private int id;
	private String name;
	private String sex;
	private int age;
	private String email;
	private String hobby;
	private Date birthday;
	private Float money;

	public User() {
	}

	/**
	 * @param name
	 * @param sex
	 * @param age
	 */
	public User(String name, String sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	/**
	 * @param name
	 * @param sex
	 * @param age
	 * @param brithday
	 */
	public User(String name, String sex, int age, Date brithday) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.birthday = brithday;
	}

	/**
	 * @param name
	 * @param sex
	 * @param age
	 * @param brithday
	 * @param money
	 */
	public User(String name, String sex, int age, Date brithday, Float money) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.birthday = brithday;
		this.money = money;
	}

	/**
	 * @param name
	 * @param sex
	 * @param age
	 * @param email
	 * @param brithday
	 * @param money
	 */
	public User(String name, String sex, int age, String email, Date brithday, Float money) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.email = email;
		this.birthday = brithday;
		this.money = money;
	}

	/**
	 * @param id
	 * @param name
	 * @param sex
	 * @param age
	 * @param email
	 * @param brithday
	 * @param money
	 */
	public User(int id, String name, String sex, int age, String email, Date brithday, Float money) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.email = email;
		this.birthday = brithday;
		this.money = money;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the brithday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the brithday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the money
	 */
	public Float getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(Float money) {
		this.money = money;
	}

	/**
	 * @return the hobby
	 */
	public String getHobby() {
		return hobby;
	}

	/**
	 * @param hobby
	 *            the hobby to set
	 */
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

}
