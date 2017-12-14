package com.hai.ijavase.introspector;

import java.util.Date;

public class MyIntroSpector {

	private int id;
	private String flag;
	private Date birthday;

	public MyIntroSpector() {
	}

	public MyIntroSpector(int id) {
		this.id = id;
	}

	public MyIntroSpector(String flag) {
		this.flag = flag;
	}

	public MyIntroSpector(int id, String flag) {
		this.id = id;
		this.flag = flag;
	}

	public MyIntroSpector(int id, String flag, Date birthday) {
		this.id = id;
		this.flag = flag;
		this.birthday = birthday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
