/**
 * @author xieyizun@163.com
 * @date 2018年4月14日
 * @version 1.0
 */
package org.xyz.jblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xyz.jblog.dao.UserDao;
import org.xyz.jblog.entity.User;
import org.xyz.jblog.service.UserService;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	/* (non-Javadoc)
	 * @see org.xyz.jblog.service.UserService#getAllUsers()
	 */
	@Override
	@Transactional(readOnly=true)
	public List<User> getAllUsers(int page) {
		return userDao.listUsers();
	}

	/* (non-Javadoc)
	 * @see org.xyz.jblog.service.UserService#saveUser(org.xyz.jblog.entity.User)
	 */
	@Override
	@Transactional
	public Integer saveUser(User user) {
		return userDao.insertUserAndGetId(user);
	}

	/* (non-Javadoc)
	 * @see org.xyz.jblog.service.UserService#findUserByNameOrEmail(org.xyz.jblog.entity.User)
	 */
	@Override
	@Transactional
	public List<User> findUserByNameOrEmail(User user) {
		return userDao.findUserByNameOrEmail(user);
	}

}
