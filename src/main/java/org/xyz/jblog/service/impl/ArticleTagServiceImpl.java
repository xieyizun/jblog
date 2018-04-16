/**
 * @author xieyizun@163.com
 * @date 2018年4月15日
 * @version 1.0
 */
package org.xyz.jblog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xyz.jblog.dao.ArticleTagDao;
import org.xyz.jblog.entity.Article;
import org.xyz.jblog.entity.ArticleTag;
import org.xyz.jblog.entity.User;
import org.xyz.jblog.service.ArticleTagService;

/**
 *
 */
@Service
public class ArticleTagServiceImpl implements ArticleTagService {
	@Autowired
	private ArticleTagDao articleTagDao;


	/* (non-Javadoc)
	 * @see org.xyz.jblog.service.ArticleTagService#listArticleTags(org.xyz.jblog.entity.Article)
	 */
	@Override
	public List<ArticleTag> listArticleTags(Article article) {
		return articleTagDao.listArticleTags(article);
	}


	/* (non-Javadoc)
	 * @see org.xyz.jblog.service.ArticleTagService#convertTagsToStr(java.util.List)
	 */
	@Override
	public String convertTagsToStr(List<ArticleTag> tags) {
		StringBuilder tagsStr = new StringBuilder();
		for (ArticleTag tag : tags) {
			tagsStr.append(tag.getName());
			tagsStr.append(",");
		}
		return tagsStr.length()==0 ? "" : tagsStr.substring(0, tagsStr.length()-1);
	}


	/* (non-Javadoc)
	 * @see org.xyz.jblog.service.ArticleTagService#listUserTags(org.xyz.jblog.entity.User)
	 */
	@Override
	public List<Map<String, Object>> listUserTags(Integer userId) {
		return articleTagDao.listUserTags(userId);
	}


	/* (non-Javadoc)
	 * @see org.xyz.jblog.service.ArticleTagService#listArticlesByTag(java.lang.Integer)
	 */
	@Override
	public List<Article> listArticlesByTagNameAndUserId(String tagName, Integer userId) {
		return articleTagDao.listArticlesByTagNameAndUserId(tagName, userId);
	}


	/* (non-Javadoc)
	 * @see org.xyz.jblog.service.ArticleTagService#getArticleTagById(java.lang.Integer)
	 */
	@Override
	public ArticleTag getArticleTagById(Integer ArticleTagId) {
		return articleTagDao.getArticleTagById(ArticleTagId);
	}

}
