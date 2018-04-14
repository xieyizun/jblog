/**
 * @author xieyizun@163.com
 * @date 2018年2月10日
 * @version 1.0
 */
package org.xyz.jblog.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xyz.blog.constants.ConstantsNumber;
import org.xyz.blog.interfaces.Paginatable;
import org.xyz.jblog.entity.Article;
import org.xyz.jblog.service.ArticleService;
import org.xyz.jblog.utils.PaginatorUtils;

import com.github.pagehelper.Page;

/**
 *
 */
@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(HttpServletRequest request, @RequestParam(required=false, defaultValue="1") int page, ModelMap map) {
		List<Article> recentArticles = articleService.getAllArticles(page);
		// 分页
		Map<String, Object> pageInfo = PaginatorUtils.getPainatorInfo(recentArticles, "/");
		map.addAllAttributes(pageInfo);
		map.put("list", recentArticles);
		return "index";
	}
}
