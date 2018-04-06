/**
 * @author xieyizun@163.com
 * @date 2018年4月6日
 * @version 1.0
 */
package org.xyz.jblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.xyz.jblog.entity.Article;

/**
 *
 */
public interface ArticleDao {
	List<Article> getAllArticles();
	List<Article> getArticlesByUserId(Integer userId);
	Article getArticleById(Integer articleId);
	Article getArticleByIdAndUserId(@Param("id")Integer id, @Param("userId")Integer userId);
	Integer insertArticle(Article article);
	Integer updateArticle(Article article);
	Integer deleteArticle(Article article);
}
