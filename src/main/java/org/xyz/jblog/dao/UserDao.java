/**
 * @author xieyizun@163.com
 * @date 2018年2月10日
 * @version 1.0
 */
package org.xyz.jblog.dao;

import java.util.List;

import org.xyz.jblog.entity.User;

/**
 *
 */
public interface UserDao {
	List<User> listUsers();
}
