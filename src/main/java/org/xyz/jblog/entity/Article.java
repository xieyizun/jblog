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
public class Article extends EntityBase<Article> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String subject;
	private String content;
	private User author;
	private Category category;
	
	
	
	/**
	 * 
	 */
	public Article() {
		
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
