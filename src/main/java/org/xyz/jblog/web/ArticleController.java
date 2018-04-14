/**
 * @author xieyizun@163.com
 * @date 2018年4月6日
 * @version 1.0
 */
package org.xyz.jblog.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xyz.jblog.entity.Article;
import org.xyz.jblog.entity.Category;
import org.xyz.jblog.entity.User;
import org.xyz.jblog.service.ArticleService;
import org.xyz.jblog.service.CategoryService;
import org.xyz.jblog.utils.HttpUtils;
import org.xyz.jblog.utils.PaginatorUtils;

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
	
	@RequestMapping(value="/show/{id}", method=RequestMethod.GET)
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
	public String newArticle(HttpServletRequest request, ModelMap map) {	
		Article article = new Article();
		User currentUser = HttpUtils.getCurrentLoginUser(request);
		List<Category> categories = categoryService.getAllCategoriesByUserId(currentUser.getId());
		
		map.addAttribute("article", article);
		map.addAttribute("categories", categories);
		
		return "articles/newArticle";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String editArticle(HttpServletRequest request, @PathVariable("id") Integer id, ModelMap map) {
		User currentUser = HttpUtils.getCurrentLoginUser(request);	
		Article article = articleService.getArticleByIdAndUserId(id, currentUser.getId());
		
		if (article != null) {
			List<Category> categories = categoryService.getAllCategoriesByUserId(currentUser.getId());
			map.addAttribute("article", article);
			map.addAttribute("categories", categories);
			
			return "articles/editArticle";
		} else {
			return "error404";
		}
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String createOrUpdateArticle(HttpServletRequest request, ModelMap map) {
		User currentUser = HttpUtils.getCurrentLoginUser(request);		
		Article article = articleService.getArticleFromForm(request, currentUser);
		
		if (article == null) {
			return "error404";
		} else {
			if (article.getId() != null) {
				// 更新
				articleService.updateArticle(article);
			} else {
				// 新建
				Integer dbArticleId = articleService.insertArticle(article);
				if (dbArticleId == null) {
					return "articles/editArticle";
				}
			}
		}
		// 返回我的博客列表
		return "redirect:/article/myArticles";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteArticle(HttpServletRequest request, @PathVariable("id") Integer id, ModelMap map) {
		User currentUser = HttpUtils.getCurrentLoginUser(request);	
		Article article = articleService.getArticleByIdAndUserId(id, currentUser.getId());
		if (article != null) {
			articleService.deleteArticle(article);
			// 返回我的博客列表
			return "redirect:/article/myArticles";
		} else {
			return "error404";
		}				
	}
	
	@RequestMapping(value="/myArticles", method=RequestMethod.GET)
	public String myArticles(HttpServletRequest request, @RequestParam(required=false, defaultValue="1") int page, ModelMap map) {
		User currentUser = HttpUtils.getCurrentLoginUser(request);
		List<Article> myArticles = articleService.getArticlesByUserId(currentUser.getId(), page);
		Map<String, Object> pageInfo = PaginatorUtils.getPainatorInfo(myArticles, "/article/myArticles/");
		map.addAllAttributes(pageInfo);
		map.addAttribute("list", myArticles);
		return "articles/myArticlesList";
	}
}
