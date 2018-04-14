/**
 * @author xieyizun@163.com
 * @date 2018年2月10日
 * @version 1.0
 */
package org.xyz.jblog.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xyz.jblog.entity.Article;
import org.xyz.jblog.entity.User;
import org.xyz.jblog.service.ArticleService;
import org.xyz.jblog.service.UserService;

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
	public String index(HttpServletRequest request, @RequestParam(required=false, defaultValue="1") Integer page, ModelMap map) {
		//Integer page = request.getParameter("page")==null ? 1 : Integer.valueOf(request.getParameter("page"));
		List<Article> recentArticles = articleService.getAllArticles(page, 5);
		
		Page<Article> pageInfo = (Page<Article>) recentArticles;
		List<Integer> pages = new ArrayList<Integer>();
		for (int i=1; i<=pageInfo.getPages(); i++) {
			pages.add(i);
		}
        map.put("pages", pages);
        map.put("total", pageInfo.getTotal());
        map.put("currentPage", pageInfo.getPageNum());
		map.put("recentArticles", recentArticles);
		return "index";
	}
}
