/**
 * @author xieyizun@163.com
 * @date 2018年4月14日
 * @version 1.0
 */
package org.xyz.jblog.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.xyz.jblog.entity.Article;
import org.xyz.jblog.entity.ArticleTag;

/**
 *
 */
public interface ArticleTagDao {
	void bulkInsertArticleTags(@Param("articleId")Integer articleId, @Param("tagNames")Set<String> tagNames);
	List<ArticleTag> listArticleTags(@Param("article")Article article);
	void bulkDeleteArticleTags(@Param("article")Article article, @Param("tags")List<ArticleTag> tags);
}
