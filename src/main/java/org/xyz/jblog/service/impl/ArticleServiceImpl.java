/**
 * @author xieyizun@163.com
 * @date 2018年4月14日
 * @version 1.0
 */
package org.xyz.jblog.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xyz.jblog.constants.ConstantsNumber;
import org.xyz.jblog.dao.ArticleDao;
import org.xyz.jblog.dao.ArticleTagDao;
import org.xyz.jblog.dao.CategoryDao;
import org.xyz.jblog.entity.Article;
import org.xyz.jblog.entity.ArticleTag;
import org.xyz.jblog.entity.Category;
import org.xyz.jblog.entity.User;
import org.xyz.jblog.service.ArticleService;
import org.xyz.jblog.utils.MyStringUtils;

import com.github.pagehelper.PageHelper;

/**
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ArticleTagDao articleTagDao;
	
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
		Integer articleId = articleDao.insertArticle(article);
		// 文章标签
		if (articleId != null) {
			String tags = article.getTags();
			if (tags != null && !"".equals(tags)) {
				Set<String> tagNames = new HashSet<>(Arrays.asList(tags.split(",")));
				articleTagDao.bulkInsertArticleTags(article.getId(), tagNames);
			}
		}
		
		return articleId;
	}
	
	@Override
	@Transactional
	public Integer updateArticle(Article article) {
		Integer articleId = articleDao.updateArticle(article);
		// 之前标签
		List<ArticleTag> preArticleTags = articleTagDao.listArticleTags(article);
		List<String> preTagsName = new ArrayList<String>();
		for (ArticleTag tag : preArticleTags) {
			preTagsName.add(tag.getName());
		}
		// 当前标签
		String tagsStr = article.getTags();
		List<String> currentTagNames = new ArrayList<String>();
		if (!MyStringUtils.isEmptyOrNull(tagsStr)) {
			currentTagNames = Arrays.asList(tagsStr.split(","));
			// 新增标签
			Set<String> newTags = new HashSet<>();
			for (String tagName : currentTagNames) {
				if (!preTagsName.contains(tagName)) {
					newTags.add(tagName);
				}
			}
			if (newTags.size() > 0) {
				articleTagDao.bulkInsertArticleTags(article.getId(), newTags);
			}
		}
		
		// 废弃标签
		List<ArticleTag> deletingTags = new ArrayList<>();
		for (ArticleTag preTag : preArticleTags) {
			if (!currentTagNames.contains(preTag.getName())) {
				preTag.setEnabled(false);
				deletingTags.add(preTag);
			}
		}
		if (deletingTags.size() > 0) {
			articleTagDao.bulkDeleteArticleTags(article, deletingTags);
		}
		
		return articleId;
	}
	
	@Override
	@Transactional
	public Integer deleteArticle(Article article) {
		return articleDao.deleteArticle(article);
	}

	/* (non-Javadoc)
	 * @see org.xyz.jblog.service.ArticleService#getArticleFromForm(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Article getArticleFromForm(HttpServletRequest request, User currentUser) {
		Integer articleId = MyStringUtils.strToInt(request.getParameter("articleId"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String tags = request.getParameter("tags");
		Integer status = MyStringUtils.strToInt(request.getParameter("status"));

		Integer categoryId = request.getParameter("categoryId")==null || "".equals(request.getParameter("categoryId")) ? null : Integer.parseInt(request.getParameter("categoryId"));
		Article article = null;
		Category category = categoryId==null ? null : categoryDao.getCategoryById(categoryId);
		// 更新
		if (articleId != null) {
			article = this.getArticleByIdAndUserId(articleId, currentUser.getId());
			if (article != null) {
				article.setContent(content);
				article.setSubject(subject);
				article.setTags(tags);
				article.setStatus(status);
				article.setCategory(category);
			}
		// 新建
		} else {
			article = new Article();
			article.setContent(content);
			article.setSubject(subject);
			article.setTags(tags);
			article.setStatus(status);
			article.setAuthor(currentUser);
			article.setCategory(category);
		}
		return article;
	}
}
