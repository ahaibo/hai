/**
 * 
 */
package com.hai.common.db.mydatasource.test;

import java.util.List;

import com.hai.common.db.mydatasource.MyDBUtil;
import com.hai.common.db.mydatasource.test.entity.User;

/**
 * @author Administrator
 * 
 */
public class Test
{
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception
	{
		// MyDataSource myDataSource = MyDataSource.getInstance();
		// Connection connection = null;
		// for (int i = 0; i < 22; i++)
		// {
		// connection = myDataSource.getConnection();
		// System.out.println(connection);
		// myDataSource.free(connection);
		// }
		
		MyDBUtil myDBUtil = MyDBUtil.getInstance();
		String sql = "select * from user";
		Object[] params = null;
		List list = myDBUtil.queryList(User.class, sql, params);
		if (null != list && !list.isEmpty())
		{
			int size = list.size();
			System.out.println(size);
			for (int i = 0; i < size; i++)
			{
				System.out.println(list.get(i));
			}
		}
		
	}
}
