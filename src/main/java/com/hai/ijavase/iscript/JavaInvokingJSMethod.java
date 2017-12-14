/**
 * 
 */
package com.hai.ijavase.iscript;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Java操作调用javascript示例
 * 
 * @author Administrator
 * 
 */
public class JavaInvokingJSMethod
{
	
	/** 脚本引擎类型/名称 */
	private static final String DEFAULT_SCRIPT_ENGIN_NAME = "javascript";
	/** 脚本文件目录 */
	private static final String JSFILE_DIRECTORY_NAME = "D:/TestDemo/ScriptInJava";
	
	public static void main(String[] args)
	{
		// 获取一个Javascript的执行引擎
		ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName(DEFAULT_SCRIPT_ENGIN_NAME);
		// 建立上下文变量
		Bindings bindings = (Bindings) scriptEngine.createBindings();
		// 此变量是在要调用的js函数中用到的变量，通过上下文绑定到允许环境
		String contextVariable = "factor";
		bindings.put(contextVariable, 2);
		// 绑定上下文，作用域是当前引擎范围
		scriptEngine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
		
		// 接受控制台输入的参数
		Scanner scanner = new Scanner(System.in);
		// 脚本文件路径
		String filepath = JSFILE_DIRECTORY_NAME.concat(File.separator).concat("calc.js");
		
		// 标识是否继续
		String flag = null;
		do
		{
			System.out.println("请输入第一个数值：");
			int first = scanner.nextInt();
			System.out.println("请输入第二个数值：");
			int sec = scanner.nextInt();
			System.out.println("first number is : " + first + ", " + sec);
			// 执行js代码
			try
			{
				Object resultt = scriptEngine.eval(new FileReader(filepath), bindings);
				System.out.println("scriptEngine.eval result: " + resultt);
				// 判断方法是否可调用
				if (scriptEngine instanceof Invocable)
				{
					Invocable invocable = (Invocable) scriptEngine;
					try
					{
						// 执行js中的函数
						Double result = (Double) invocable.invokeFunction("formula", first, sec);
						System.out.println("formula result: " + result);
					}
					catch (NoSuchMethodException e)
					{
						e.printStackTrace();
					}
				}
				
			}
			catch (ScriptException | FileNotFoundException e)
			{
				e.printStackTrace();
			}
			
			System.out.println("是否继续？【y/n】");
			flag = scanner.next();
			
		}
		while (flag.equals("y"));
		
		scanner.close();// 此句代码关闭控制台
		
		System.out.println("Scanner is closed!");
		
		// 下面的代码会执行，退出程序
		System.exit(0);
		
		// 下面一句代码不会执行
		System.out.println("System exited!");
		
	}
}
