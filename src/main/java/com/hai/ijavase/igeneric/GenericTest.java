package com.hai.ijavase.igeneric;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hai.ijavase.util.VerifyUtil;

/**
 * 泛型应用练习
 * 
 * @author Administrator
 * 
 */
public class GenericTest {

	public Map<String, List<Set<Map<String, Object>>>> map;

	/**
	 * 以字段为例通过反射获取一个泛型字段的参数实际类型
	 */
	private static void getGenericActualTypeArgumentsTest() {
		// TODO Auto-generated method stub
		try {
			Field mapField = GenericTest.class.getField("map");

			Class<?> declaringClass = mapField.getDeclaringClass();
			System.out.println("declaringClass: " + declaringClass);

			Type genericType = mapField.getGenericType();
			System.out.println("genericType: " + genericType);

			// 获取该字段的参数类型
			ParameterizedType parameterizedType = (ParameterizedType) genericType;
			Type[] paramTypes = parameterizedType.getActualTypeArguments();
			if (!VerifyUtil.isEmpty(paramTypes) && paramTypes.length == 1) {
				System.out.println("paramTypes: " + Arrays.toString(paramTypes));
			} else {
				for (Type type : paramTypes) {
					ParameterizedType pType = null;
					try {
						pType = (ParameterizedType) type;
						Type[] valTypeArgus = pType.getActualTypeArguments();
						System.out.println(valTypeArgus[0]);

					} catch (Exception e) {
						System.out.print("paramTypes key: " + type + ", value: ");
						continue;
					}
				}
			}

			// System.out.println("paramTypes: " + Arrays.toString(paramTypes));

			// System.out.println("\nparameterizedType: \n" +
			// parameterizedType.getClass() + ", "
			// + parameterizedType.getOwnerType() + ", " +
			// parameterizedType.getRawType() + ", "
			// + Arrays.toString(parameterizedType.getActualTypeArguments()));

			Type type = mapField.getType();
			System.out.println("type: " + type);

		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		getGenericActualTypeArgumentsTest();
		// TODO Auto-generated method stub
		genericByReflectTest();

		swap(new String[] { "a", "ab", "abc", "b", "bc", "bcd" }, 2, 4);// 可正确调用
		// 如下调用不行，int为基本类型
		// swap(new int[] { 1, 2, 3, 4 }, 2, 3);

		Map<Object, Object> map = new HashMap<>();
		map.put(10, 10);
		map.put(20, "ahai");
		map.put(30, 30.0);
		map.put("40", "lisi");
		map.put(50f, 50l);

		new GenericTest().new GenericByMap().printMap(map);

		System.out.println(Arrays.toString(copy(new ArrayList<>(), new Object[] { 1, 2, "3" }).toArray()));

		// 泛型类型的自动推断，如下代码编译通过，运行时出错
		// System.out.println(Arrays.toString(copy(new Integer[1], new String[]
		// { "abc" })));
	}

	class GenericByMap {
		/**
		 * 打印map元素，练习map的迭代
		 * 
		 * @param map
		 */
		void printMap(Map<Object, Object> map) {

			if (VerifyUtil.isEmpty(map)) {
				System.out.println("this map is empty!");
				return;
			}

			System.out.println("this map content as below:");

			Set<Map.Entry<Object, Object>> entrySet = map.entrySet();
			for (Map.Entry<Object, Object> entry : entrySet) {
				System.out.println(entry.getKey() + ", " + entry.getValue());
			}

		}
	}

	private static <E extends Exception> void genericByException() throws E {
		try {
			Object obj = 3;
		} catch (Exception e) {
			throw (E) e;
		}
	}

	/**
	 * 泛型的应用示例：类型自动转换
	 */
	private static <T> T autoConvert(Object obj) {
		// TODO Auto-generated method stub
		return (T) obj;
	}

	/**
	 * 将任一类型的数组填满相应类型的元素
	 */
	private static <T> void fillArray(T[] arr, T item) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = item;
		}
	}

	/**
	 * 泛型示例：相同类型的copy
	 */
	private static <E> Collection<E> copy(Collection<E> destination, E[] source) {
		for (int i = 0; i < source.length; i++) {
			destination.add(source[i]);
		}

		return destination;
	}

	/**
	 * 泛型示例：相同类型的copy
	 */
	private static <E> E[] copy(E[] dest, E[] src) {

		for (int i = 0; i < src.length; i++) {
			dest[i] = src[i];
		}

		return dest;
	}

	/**
	 * 泛型练习：java中的泛型是给编译器看的，通过反射可以绕过泛型
	 */
	private static void genericByReflectTest() {
		List<String> strList = new ArrayList<>();

		List<Integer> intList = new ArrayList<>();

		System.out.println(strList.getClass() == intList.getClass());

		// 通过反射可以绕过泛型
		try {
			Method reflectMethod = intList.getClass().getMethod("add", new Class<?>[] { Object.class });
			if (null != reflectMethod) {
				try {
					// 声明的Integer类型的List集合通过反射可根据方法的实际允许的类型自由添加元素，间接的解除了元素的类型限制
					reflectMethod.invoke(intList, "abc");

					System.out.println(intList.get(0));

				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 实现数组元素的交换：此例可证明泛型的实际参数只能是原型为引用类型的数据
	 */
	private static <T> void swap(T[] a, int i, int j) {
		System.out.println("\nbefore the swap: " + Arrays.toString(a));
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		System.out.println("\nafter the swap: " + Arrays.toString(a));
	}

	/**
	 * java中的泛型实质的模拟c++的模板，如下为c++模板示例
	 */
	class SimulateTemplete {
		// TODO Auto-generated method stub
		<T> T add(T a, T b) {

			return null;
		}
	}
}
