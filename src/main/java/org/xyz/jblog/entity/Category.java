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
public class Category extends EntityBase<Category> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Category parent;
	private User user;
	
	
	/**
	 * 
	 */
	public Category() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
