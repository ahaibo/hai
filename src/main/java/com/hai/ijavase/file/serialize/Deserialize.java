/**
 * 
 */
package com.hai.ijavase.file.serialize;

/**
 * @author Administrator
 * 
 */
public class Deserialize {

	public static void main(String[] args) {
		String filepath = "D:/JacksonTest/File/person.bin";

		Person person = (Person) SerializationUtils.readObject(filepath);

		System.out.println(person);
	}
}
