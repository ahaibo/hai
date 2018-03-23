/**
 * 
 */
package com.hai.javase.servlet.iusermanager.dao;

import java.io.IOException;
import java.util.List;

import com.hai.javase.servlet.iusermanager.entity.User;

/**
 * @author Administrator
 * 
 */
public interface IUserService
{
	List<User> getAllUsers(String sql) throws IOException;
	
	List<User> getAllUsers(String sql, Object[] params) throws IOException;
	
	List<User> getAllUsers(String sql, int pageNow, int pageSize) throws IOException;
	
	User getUserById(int id) throws IOException;
	
	int deleteUserById(String sql, int id) throws IOException;
}
