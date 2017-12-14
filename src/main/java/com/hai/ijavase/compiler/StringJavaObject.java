/**
 * 
 */
package com.hai.ijavase.compiler;

import java.io.IOException;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * @author Administrator
 * 
 */
public class StringJavaObject extends SimpleJavaFileObject {
	/** 源代码 */
	private String content;

	/**
	 * 遵循java规范的类名及文件
	 * 
	 * @param javaFileName
	 * @param content
	 */
	public StringJavaObject(String javaFileName, String content) {
		super(createStringJavaObjectURI(javaFileName), Kind.SOURCE);
		this.content = content;
	}

	/**
	 * 产生一个URI资源路径
	 * 
	 * @param name
	 */
	private static URI createStringJavaObjectURI(String name) {
		// 此处没有设置包名
		return URI.create("String:///" + name + Kind.SOURCE.extension);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.tools.SimpleJavaFileObject#getCharContent(boolean)
	 */
	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
		return content;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
