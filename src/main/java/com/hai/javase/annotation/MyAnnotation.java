/**
 * 
 */
package com.hai.javase.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.hai.javase.ienum.TrafficLamp;

/**
 * @author Administrator
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
//@Target(value = { ElementType.METHOD, ElementType.FIELD })
public @interface MyAnnotation {

	String value();

	String color() default "red";

	TrafficLamp lamp() default TrafficLamp.RED;

	Class<?> classAnnotation() default Object.class;
}
