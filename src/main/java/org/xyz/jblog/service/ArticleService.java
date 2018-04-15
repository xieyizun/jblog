/**
 * @author xieyizun@163.com
 * @date 2018年4月6日
 * @version 1.0
 */
package org.xyz.jblog.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.xyz.jblog.entity.Article;
import org.xyz.jblog.entity.User;

/**
 *
 */
public interface ArticleService {
	List<Article> getAllArticles(int page);
	List<Article> getArticlesByUserId(Integer userId, Integer page);
	Article getArticleById(Integer articleId);
	Article getArticleByIdAndUserId(Integer id, Integer userId);
	Integer insertArticle(Article article);
	Integer updateArticle(Article article);
	Integer deleteArticle(Article article);
	
	Article getArticleFromForm(HttpServletRequest request, User currentUser);
}
