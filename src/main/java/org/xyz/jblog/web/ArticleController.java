/**
 * @author xieyizun@163.com
 * @date 2018年4月6日
 * @version 1.0
 */
package org.xyz.jblog.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xyz.jblog.entity.Article;
import org.xyz.jblog.entity.Category;
import org.xyz.jblog.entity.User;
import org.xyz.jblog.service.ArticleService;
import org.xyz.jblog.service.CategoryService;

/**
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String show(@PathVariable Integer id, ModelMap mmap) {
		Article article = articleService.getArticleById(id);
		if (article != null) {
			mmap.addAttribute("article", article);
			return "articles/article";
		} else {
			return "error404";
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String newArticle(ModelMap map) {
		Article article = new Article();
		List<Category> categories = categoryService.getAllCategories();
		
		map.addAttribute("article", article);
		map.addAttribute("categories", categories);
		
		return "articles/newArticle";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String editArticle(HttpServletRequest request, @PathVariable("id") Integer id, ModelMap map) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (currentUser != null) {
			logger.info("edit id="+id);
			Article article = articleService.getArticleByIdAndUserId(id, currentUser.getId());
			if (article != null) {
				List<Category> categories = categoryService.getAllCategories();
				map.addAttribute("article", article);
				map.addAttribute("categories", categories);
				
				return "articles/editArticle";
			} else {
				return "error404";
			}		
		} else {
			return "redirect:/account/login";
		}
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String createOrUpdateArticle(HttpServletRequest request, ModelMap map) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (currentUser != null) {
			Integer articleId = request.getParameter("articleId")==null || "".equals(request.getParameter("articleId")) ? null : Integer.parseInt(request.getParameter("articleId"));
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			Integer categoryId = request.getParameter("categoryId")==null || "".equals(request.getParameter("categoryId")) ? null : Integer.parseInt(request.getParameter("categoryId"));
			Article article = null;
			Category category = categoryId==null ? null : categoryService.getCategoryById(categoryId);
			// 更新
			if (articleId != null) {
				article = articleService.getArticleByIdAndUserId(articleId, currentUser.getId());
				if (article != null) {
					article.setContent(content);
					article.setSubject(subject);
					article.setCategory(category);
					// 更新
					articleService.updateArticle(article);
				} else {
					return "error404";
				}
			// 新建
			} else {
				article = new Article();
				article.setContent(content);
				article.setSubject(subject);
				article.setAuthor(currentUser);
				article.setCategory(category);
				// 插入
				Integer dbArticleId = articleService.insertArticle(article);
				if (dbArticleId == null) {
					return "articles/editArticle";
				}
			}
			// 返回我的博客列表
			return "redirect:/article/myArticles";
		} else {
			return "redirect:/account/login";
		}
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteArticle(HttpServletRequest request, @PathVariable("id") Integer id, ModelMap map) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (currentUser != null) {
			logger.info("delete article id="+id);
			Article article = articleService.getArticleByIdAndUserId(id, currentUser.getId());
			if (article != null) {
				articleService.deleteArticle(article);
				// 返回我的博客列表
				return "redirect:/article/myArticles";
			} else {
				return "error404";
			}		
		} else {
			return "redirect:/account/login";
		}
	}
	
	@RequestMapping(value="/myArticles", method=RequestMethod.GET)
	public String myArticles(HttpServletRequest request, ModelMap map) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (currentUser != null) {
			List<Article> myArticles = articleService.getArticlesByUserId(currentUser.getId());
			map.addAttribute("personalArticles", myArticles);
			return "articles/myArticlesList";
		} else {
			return "redirect:/account/login";
		}
	}
}
