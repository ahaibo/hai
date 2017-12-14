/**
 * 
 */
package com.hai.ijavase.compiler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * @author Administrator
 * 
 */
public class JavaDynamicCompiler {

	public static void main(String[] args) {
		// java 源代码
		String sourceStr = new StringBuffer("public class Hello{").append("public void sayHello(String name){")
				.append("System.out.println(\"Hello,\"+name+\"!\");").append("}}").toString();

		String className = "Hello";

		String methodName = "sayHello";

		// 获取java编译器
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
		// java编制文件管理器
		StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null,
				Locale.getDefault(), null);
		// 创建java文件对象
		JavaFileObject javaFileObject = new StringJavaObject(className, sourceStr);

		// 设置编译参数，类似于javac <options> 中的options
		List<String> optionList = new ArrayList<>();
		// 编译文件存放的地方，注意：此处是为Eclipse工具特设的？？不解
		optionList.addAll(Arrays.asList("-d", "./bin"));
		// 要编译的单元
		List<JavaFileObject> jfos = Arrays.asList(javaFileObject);
		// 设置编译环境
		JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardJavaFileManager, null, optionList, null,
				jfos);

		// 如果编译成功则反射调用
		if (task.call()) {
			// 反射，生成示例，调用方法
			try {
				Object object = Class.forName(className).newInstance();
				Class<? extends Object> clazz = object.getClass();

				// 调用sayHello方法
				Method method = clazz.getMethod(methodName, String.class);

				method.invoke(object, "Java Dynamic Compilation TestDemo");
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException | InstantiationException e) {
				e.printStackTrace();
			}

		}
	}
}
