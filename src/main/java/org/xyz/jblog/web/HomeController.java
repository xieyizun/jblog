/**
 * @author xieyizun@163.com
 * @date 2018年2月10日
 * @version 1.0
 */
package org.xyz.jblog.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xyz.jblog.entity.Article;
import org.xyz.jblog.entity.User;
import org.xyz.jblog.service.UserService;

/**
 *
 */
@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/hello/{name}", method=RequestMethod.GET)
	public String hello(@PathVariable String name, ModelMap map) {
		List<User> users = userService.getAllUsers();
		StringBuilder usersStr = new StringBuilder();
		for (User user:users) {
			System.out.println(user.getName());
			usersStr.append(user.getName()).append("\r\n");
		}
		map.put("name", name);
		map.put("user", usersStr);
		return "hello";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(ModelMap map) {
		List<Article> recentArticles = new ArrayList<Article>();
		Article article = null;
		for (int i = 0; i < 5; i++) {
			article = new Article();
			article.setContent("test"+i);
			article.setId(i+1);
			article.setSubject("subject"+i);
			article.setCreatedAt(new Date());
			article.setUpdatedAt(new Date());
			recentArticles.add(article);
		}
		map.put("recentArticles", recentArticles);
		return "index";
	}
}
