/**
 * @author xieyizun@163.com
 * @date 2018年4月6日
 * @version 1.0
 */
package org.xyz.jblog.service;

import java.util.List;

import org.xyz.jblog.entity.Article;

/**
 *
 */
public interface ArticleService {
	List<Article> getAllArticles(int page, int pageSize);
	List<Article> getArticlesByUserId(Integer userId);
	public Article getArticleById(Integer articleId);
	public Article getArticleByIdAndUserId(Integer id, Integer userId);
	public Integer insertArticle(Article article);
	public Integer updateArticle(Article article);
	public Integer deleteArticle(Article article);
}
