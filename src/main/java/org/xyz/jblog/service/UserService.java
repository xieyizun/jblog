/**
 * @author xieyizun@163.com
 * @date 2018年2月10日
 * @version 1.0
 */
package org.xyz.jblog.service;

import java.util.List;

import org.xyz.jblog.entity.User;

/**
 *
 */
public interface UserService {	
	List<User> getAllUsers();
	
	Integer saveUser(User user);
	
	List<User> findUserByNameOrEmail(User user);
}
