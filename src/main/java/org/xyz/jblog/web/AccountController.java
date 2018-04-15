/**
 * @author xieyizun@163.com
 * @date 2018年4月5日
 * @version 1.0
 */
package org.xyz.jblog.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xyz.jblog.entity.Article;
import org.xyz.jblog.entity.Category;
import org.xyz.jblog.entity.User;
import org.xyz.jblog.service.ArticleService;
import org.xyz.jblog.service.CategoryService;
import org.xyz.jblog.service.UserService;
import org.xyz.jblog.utils.HttpUtils;
import org.xyz.jblog.utils.MyStringUtils;
import org.xyz.jblog.utils.PaginatorUtils;

/**
 *
 */
@Controller
@RequestMapping("/account")
public class AccountController {
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(ModelMap map) {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(User user, ModelMap map, RedirectAttributes ra, HttpServletRequest request) {
		logger.log(Level.INFO, user.toString()+"-"+user.getPassword());
		List<User> currentUser = userService.findUserByNameOrEmail(user);
		if (currentUser.size() == 1) {
			request.getSession().setAttribute("isLogin", true);
			request.getSession().setAttribute("currentUser", currentUser.get(0));
			return "redirect:/";
		} else {
			String error = null;
			if (currentUser.size() > 1) {
				error = "服务器异常，已通知管理员";
			} else {
				error = "登录失败，请检查用户名或密码是否正确";
			}
			logger.log(Level.WARN, error);
			map.addAttribute("error", error);
			return "login";
		}
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	/*
	 * RedirectAttributes 起到重定向后url不带参数
	 */
	public String register(User user, ModelMap map, RedirectAttributes ra, HttpServletRequest request) throws IOException {
		Integer userId = userService.saveUser(user);
		if (userId != null) {
			request.getSession().setAttribute("isLogin", true);
			request.getSession().setAttribute("currentUser", user);
			return "redirect:/";
		} else {
			map.addAttribute("error", "注册失败");
			return "login";
		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(RedirectAttributes ra, HttpServletRequest request) {
		request.getSession().setAttribute("isLogin", false);
		request.getSession().setAttribute("currentUser", null);
		return "redirect:/";
	}
	
	@RequestMapping(value="/manage", method=RequestMethod.GET)
	public String manage(HttpServletRequest request, @RequestParam(defaultValue="article") String type, ModelMap map) {
		User currentUser = HttpUtils.getCurrentLoginUser(request);
		
		switch(type) {
			case "article":
				Integer page = MyStringUtils.strToInt(request.getParameter("page"));
				List<Article> myArticles = articleService.getArticlesByUserId(currentUser.getId(), page);
				Map<String, Object> pageInfo = PaginatorUtils.getPainatorInfo(myArticles, "/account/manage/");
				map.addAllAttributes(pageInfo);
				map.addAttribute("list", myArticles);
				break;
			case "category":
				List<Category> categories = categoryService.getAllCategoriesByUserId(currentUser.getId());
				map.addAttribute("categories", categories);
				map.addAttribute("category", new Category());
				break;
			case "account":
				map.addAttribute("currentUser", currentUser);
				break;
			default:
				return "error404";
		}
		
		map.addAttribute("manageType", type);
		return "account/manage";
	}
}
