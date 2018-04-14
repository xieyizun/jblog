/**
 * @author xieyizun@163.com
 * @date 2018年2月10日
 * @version 1.0
 */
package org.xyz.jblog.base;

import java.io.Serializable;
import java.util.Date;

import org.xyz.jblog.interfaces.Paginatable;
import org.xyz.jblog.utils.MyDateUtils;

/**
 *
 */
public abstract class EntityBase<T> implements Serializable, Paginatable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String createdAt = MyDateUtils.formatDateTime(new Date());
	private String updatedAt = MyDateUtils.formatDateTime(new Date());
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
