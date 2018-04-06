/**
 * @author xieyizun@163.com
 * @date 2018年2月10日
 * @version 1.0
 */
package org.xyz.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xyz.jblog.dao.UserDao;
import org.xyz.jblog.entity.User;

/**
 *
 */
@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly=true)
	public List<User> getAllUsers() {
		return userDao.listUsers();
	}
	
	@Transactional
	public Integer saveUser(User user) {
		return userDao.insertUserAndGetId(user);
	}
	
	@Transactional
	public List<User> findUserByNameOrEmail(User user) {
		return userDao.findUserByNameOrEmail(user);
	}
}
