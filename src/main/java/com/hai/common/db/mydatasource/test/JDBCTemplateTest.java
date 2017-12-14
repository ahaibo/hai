package com.hai.common.db.mydatasource.test;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hai.common.db.mydatasource.JDBCDataSource;
import com.hai.common.db.mydatasource.test.entity.User;

/**
 * @author Administrator
 * 
 */
public class JDBCTemplateTest
{
	public static void main(String[] args)
	{
		JdbcTemplate jdbc = new JdbcTemplate(JDBCDataSource.getInstance().getDataSource());
		
		List<User> users = jdbc.queryForList("select * from user", User.class);
		
		for (User user : users)
		{
			System.out.println(user);
		}
	}
}
