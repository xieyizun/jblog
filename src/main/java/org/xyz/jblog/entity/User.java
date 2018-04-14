/**
 * @author xieyizun@163.com
 * @date 2018年2月10日
 * @version 1.0
 */
package org.xyz.jblog.entity;

import org.xyz.jblog.base.EntityBase;

/**
 *
 */
public class User extends EntityBase<User> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String password;
	private boolean isAdmin;
	private boolean locked;
	
	
	/**
	 * 
	 */
	public User() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		return this.name + "-" + this.email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
