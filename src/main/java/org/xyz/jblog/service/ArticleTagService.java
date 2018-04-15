/**
 * @author xieyizun@163.com
 * @date 2018年4月15日
 * @version 1.0
 */
package org.xyz.jblog.service;

import java.util.List;

import org.xyz.jblog.entity.Article;
import org.xyz.jblog.entity.ArticleTag;

/**
 *
 */
public interface ArticleTagService {
	List<ArticleTag> listArticleTags(Article article);
	String convertTagsToStr(List<ArticleTag> tags);
}
