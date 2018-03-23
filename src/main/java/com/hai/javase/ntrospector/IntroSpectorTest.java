package com.hai.javase.ntrospector;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Java内省操作练习
 * 
 * @author Administrator
 * 
 */
public class IntroSpectorTest {

	public static void main(String[] args) {

		MyIntroSpector myIntroSpector = new MyIntroSpector("abc");
		System.out.println(getPropertyValue(myIntroSpector, "flag"));

		setPropertyValue(myIntroSpector, "flag", "def");
		System.out.println(myIntroSpector.getFlag());

		beanUtilsTest();
	}

	/**
	 * 通过工具类BeanUtils简化实现内省操作
	 */
	public static void beanUtilsTest() {
		MyIntroSpector myIntroSpector = new MyIntroSpector(10, "BeanUtilsABC",
				new Date());
		try {
			
			String flagValue = BeanUtils.getProperty(myIntroSpector, "flag");
			System.out.println(flagValue);

			BeanUtils.setProperty(myIntroSpector, "id", 20);
			
			// BeanUtils.setProperty()方法支持属性的级联设值
			BeanUtils.setProperty(myIntroSpector, "birthday.time",
					new Date().getTime() + 100000);
			
			System.out.println(myIntroSpector.getId() + "\n"
					+ myIntroSpector.getBirthday());
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 通过内省获取属性值
	 * 
	 * @param obj
	 * @param propName
	 * @return
	 */
	private static Object getPropertyValue(Object obj, String propName) {
		Object propValue = null;
		// 获取 MyIntroSpector 的属性 flag 的值
		try {
			/*
			 * 以Introspector获得BeanInfo的方式获取属性的值
			 * 该方式的缺点是通过introspector获取的属性描述只能是数组PropertyDescriptor
			 * []，所以要手动迭代找到自己要操作的属性
			 */

			// BeanInfo introBeanInfo =
			// Introspector.getBeanInfo(obj.getClass());
			// PropertyDescriptor[] propDesc = introBeanInfo
			// .getPropertyDescriptors();
			//
			// for (PropertyDescriptor propertyDescriptor : propDesc) {
			// String name = propertyDescriptor.getName();
			// if (name.equals(propName)) {
			// Method readMethod = propertyDescriptor.getReadMethod();
			// try {
			// propValue = readMethod.invoke(obj, new Object[] {});
			// } catch (IllegalAccessException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// } catch (IllegalArgumentException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// } catch (InvocationTargetException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// break;
			// }
			// }

			/*
			 * 直接以 PropertyDescriptor 的方式获取属性值
			 */

			PropertyDescriptor myIntroSpectorDescriptor = new PropertyDescriptor(
					propName, MyIntroSpector.class);

			try {
				propValue = myIntroSpectorDescriptor.getReadMethod().invoke(
						obj, new Object[] {});
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return propValue;
	}

	/**
	 * 通过内省设置属性值
	 * 
	 * @param obj
	 * @param propName
	 * @param propValue
	 */
	private static void setPropertyValue(Object obj, String propName,
			String propValue) {
		try {
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(
					propName, obj.getClass());

			try {
				propertyDescriptor.getWriteMethod().invoke(obj, propValue);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
