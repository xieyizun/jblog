/**
 * @author xieyizun@163.com
 * @date 2018年4月14日
 * @version 1.0
 */
package org.xyz.jblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xyz.blog.constants.ConstantsNumber;
import org.xyz.jblog.dao.ArticleDao;
import org.xyz.jblog.entity.Article;
import org.xyz.jblog.service.ArticleService;

import com.github.pagehelper.PageHelper;

/**
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public List<Article> getAllArticles(int page) {
		PageHelper.startPage(page, ConstantsNumber.pageSize);
		return articleDao.getAllArticles();
	}
	
	@Override
	public List<Article> getArticlesByUserId(Integer userId, int page) {
		PageHelper.startPage(page, ConstantsNumber.pageSize);
		return articleDao.getArticlesByUserId(userId);
	}
	
	@Override
	public Article getArticleById(Integer articleId) {
		return articleDao.getArticleById(articleId);
	}
	
	@Override
	public Article getArticleByIdAndUserId(Integer id, Integer userId) {
		return articleDao.getArticleByIdAndUserId(id, userId);
	}
	
	@Override
	@Transactional
	public Integer insertArticle(Article article) {
		return articleDao.insertArticle(article);
	}
	
	@Override
	@Transactional
	public Integer updateArticle(Article article) {
		return articleDao.updateArticle(article);
	}
	
	@Override
	@Transactional
	public Integer deleteArticle(Article article) {
		return articleDao.deleteArticle(article);
	}
}
