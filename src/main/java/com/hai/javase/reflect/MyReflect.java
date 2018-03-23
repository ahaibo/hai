package com.hai.javase.reflect;

import java.util.List;
import java.util.Set;

/**
 * 反射连续类
 * 
 * @author Administrator
 * 
 */
public class MyReflect {

	private int id;
	private String name;
	private String sex;
	private int age;
	private int[] nums;
	private List<Object> lists;
	private Set<Object> sets;
	public String temp;
	// 字段反射示例
	public String str1 = "abc";
	public String str2 = "action";
	private String str3 = "annotation";

	public MyReflect() {
	}

	public MyReflect(int id) {
		this.id = id;
	}

	public MyReflect(String temp) {
		this.temp = temp;
	}

	public MyReflect(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public MyReflect(int id, String name, String sex) {
		this.id = id;
		this.name = name;
		this.sex = sex;
	}

	public MyReflect(int id, String name, String sex, int age) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public MyReflect(int id, String name, String sex, int age, int[] nums) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.nums = nums;
	}

	public MyReflect(int id, String name, String sex, int age, int[] nums, List<Object> lists) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.nums = nums;
		this.lists = lists;
	}

	public MyReflect(int id, String name, String sex, int age, int[] nums, List<Object> lists, Set<Object> sets) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.nums = nums;
		this.lists = lists;
		this.sets = sets;
	}

	@Override
	public String toString() {
		return "str1: " + str1 + ", str2: " + str2 + ", str3: " + str3;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int[] getNums() {
		return nums;
	}

	public void setNums(int[] nums) {
		this.nums = nums;
	}

	public List<Object> getLists() {
		return lists;
	}

	public void setLists(List<Object> lists) {
		this.lists = lists;
	}

	public Set<Object> getSets() {
		return sets;
	}

	public void setSets(Set<Object> sets) {
		this.sets = sets;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyReflect other = (MyReflect) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
