/**
 * @author xieyizun@163.com
 * @date 2018年4月6日
 * @version 1.0
 */
package org.xyz.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xyz.jblog.dao.ArticleDao;
import org.xyz.jblog.entity.Article;

/**
 *
 */
@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	public List<Article> getAllArticles() {
		return articleDao.getAllArticles();
	}
	public List<Article> getArticlesByUserId(Integer userId) {
		return articleDao.getArticlesByUserId(userId);
	}
	public Article getArticleById(Integer articleId) {
		return articleDao.getArticleById(articleId);
	}
	public Article getArticleByIdAndUserId(Integer id, Integer userId) {
		return articleDao.getArticleByIdAndUserId(id, userId);
	}
	public Integer insertArticle(Article article) {
		return articleDao.insertArticle(article);
	}
	public Integer updateArticle(Article article) {
		return articleDao.updateArticle(article);
	}
	public Integer deleteArticle(Article article) {
		return articleDao.deleteArticle(article);
	}
}
