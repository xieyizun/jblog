/**
 * @author xieyizun@163.com
 * @date 2018年4月14日
 * @version 1.0
 */
package org.xyz.jblog.entity;

import org.xyz.jblog.base.EntityBase;

/**
 *
 */
public class Comment extends EntityBase<Comment> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String content;
	private Article article;
	private User user;
	private Comment parentComment;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Comment getParentComment() {
		return parentComment;
	}
	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}
	

}
