package com.hai.javase.reflect;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;

import com.hai.javase.util.VerifyUtil;

/**
 * 反射练习
 * 
 * @author Administrator
 * 
 */
public class ReflectTest {

	public static void main(String[] args) {
		// System.out.println(int.class == Integer.TYPE);
		// System.out.println(String.class.isSynthetic());
		// System.out.println(new Teacher().getClass().isSynthetic());

		// fieldReflectTest();

		// 通过字段反射扫描改变符合某一条件的字段的值
		// changeFieldValue(new MyReflect(10, "zhangsan"), "a", "b");

		// hashcodeTest();

		configByReflect();
	}

	/**
	 * 通过反射的方式读取配置文件而冬天改变程序调用的类模拟实现框架的基本原理
	 */
	private static void configByReflect() {

		// 方式1：通过InputStream和Properties配合读取配置文件
		InputStream inStream = null;
		try {
			// inStream = new FileInputStream(
			// "src/cn/com/test/reflect/config.properties");

			// 方式2：通过本地类加载器加载资源文件
			// inStream =
			// ReflectTest.class.getClassLoader().getResourceAsStream(
			// "cn/com/test/reflect/config.properties");

			// 方式3：通过类默认的类加载器加载资源文件，此方法默认在当前类所在的路径下寻找要加载的资源文件
			inStream = ReflectTest.class
					.getResourceAsStream("config.properties");
			Properties conf = new Properties();

			try {
				conf.load(inStream);
				String className = conf.getProperty("className");

				if (VerifyUtil.isEmpty(className)) {
					return;
				}
				try {

					Collection<Object> collections = (Collection<Object>) Class
							.forName(className).newInstance();

					MyReflect myr1 = new MyReflect(10, "zhangsan");
					MyReflect myr2 = new MyReflect(10, "lisi");
					MyReflect myr3 = new MyReflect(20, "zhangsan");
					MyReflect myr4 = new MyReflect(30, "wangwu");
					MyReflect myr5 = new MyReflect(10, "zhangsan");

					collections.add(myr1);
					collections.add(myr2);
					collections.add(myr3);
					collections.add(myr4);
					collections.add(myr5);

					System.out.println(collections.size());
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} finally {
			if (null != inStream) {
				try {
					inStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * hashCode算法的左右及影响
	 */
	private static void hashcodeTest() {
		Collection<MyReflect> collections = /* new ArrayList<MyReflect>(); */new HashSet<MyReflect>();

		MyReflect myr1 = new MyReflect(10, "zhangsan");
		MyReflect myr2 = new MyReflect(10, "lisi");
		MyReflect myr3 = new MyReflect(20, "zhangsan");
		MyReflect myr4 = new MyReflect(30, "wangwu");
		MyReflect myr5 = new MyReflect(10, "zhangsan");

		collections.add(myr1);
		collections.add(myr2);
		collections.add(myr3);
		collections.add(myr4);
		collections.add(myr5);

		/*
		 * 如果一个对象重写了hash算法儿此对象又存入了hash集合之中，那么不要修改这个对象的值，
		 * 否则会导致修改后的对象无法从hash集合删除而导致程序存在内存泄漏的问题。 如下：
		 */
		myr5.setId(30);
		collections.remove(myr5);

		System.out.println(collections.size());

	}

	private static void fieldReflectTest() {
		MyReflect myre = new MyReflect(10, "zhangsan");
		myre.setTemp("my temp ..");
		System.out.println(myre.getId() + ", " + myre.getName());
		try {
			// 访问公有成员变量的字段反射
			Field tempField = myre.getClass().getField("temp");
			Object tempValue = tempField.get(myre);
			System.out.println(tempValue);

			// 访问私有成员变量的字段反射
			Field nameField = myre.getClass().getDeclaredField("name");
			nameField.setAccessible(true);
			System.out.println(nameField.get(myre));

		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***
	 * 通过反射改变属性值
	 * 
	 * @param classObject
	 * @param oldValue
	 * @param newValue
	 */
	private static void changeFieldValue(Object classObject, Object oldValue,
			Object newValue) {

		// if (!(classObject instanceof MyReflect)) {
		// try {
		// classObject = classObject.getClass().newInstance();
		// } catch (InstantiationException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		Field[] fields = classObject.getClass().getDeclaredFields();

		for (Field field : fields) {
			if (field.getType() == String.class) {

				if (!Modifier.isPublic(field.getModifiers())) {
					field.setAccessible(true);
				}

				try {
					Object fieldValue = field.get(classObject);
					if (VerifyUtil.isEmpty(fieldValue)) {
						continue;
					}

					String value = (String) fieldValue;
					if (VerifyUtil.isEmpty(fieldValue)) {
						continue;
					}

					field.set(classObject,
							value.replace((String) oldValue, (String) newValue));

				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		System.out.println(classObject);
	}
}
