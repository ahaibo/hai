/**
 * 
 */
package com.hai.javase.annotation;

/**
 * @author Administrator
 * 
 */
@MyAnnotation("val")
public class AnnotationTest {

	private static final String CLASS_NAME = AnnotationTest.class.getName();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		myAnnotationTest();

	}

	/**
	 * 
	 */
	private static void myAnnotationTest() {
		if (AnnotationTest.class.isAnnotationPresent(MyAnnotation.class)) {
			MyAnnotation myAnnotation = AnnotationTest.class.getAnnotation(MyAnnotation.class);
			System.out.println("color: " + myAnnotation.color());
			System.out.println("value: " + myAnnotation.value());
			System.out.println("annotationType: " + myAnnotation.annotationType());
			System.out.println("classAnnotation: " + myAnnotation.classAnnotation());
			System.out.println("lamp: " + myAnnotation.lamp());
			System.out.println("next lamp: " + myAnnotation.lamp().nextLamp());
			System.out.println("lamp time: " + myAnnotation.lamp().name() + ", " + myAnnotation.lamp().getTime());
		}
	}

}
