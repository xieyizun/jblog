/**
 * @author xieyizun@163.com
 * @date 2018年4月14日
 * @version 1.0
 */
package org.xyz.jblog.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xyz.jblog.constants.ConstantsNumber;
import org.xyz.jblog.entity.Article;
import org.xyz.jblog.service.ArticleTagService;

/**
 *
 */
@Controller
@RequestMapping("/tag")
public class TagController {
	@Autowired
	private ArticleTagService articleTagService;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request, ModelMap map) {
		List<Map<String, Object>> userTags = articleTagService.listUserTags(ConstantsNumber.ME_ID);
		
		map.addAttribute("tagList", userTags);
		map.addAttribute("type", "tag");
		return "tags/index";
	}
	
	@RequestMapping("/{tagName}")
	public String show(HttpServletRequest request, @PathVariable String tagName, ModelMap map) {
		List<Article> articles = articleTagService.listArticlesByTagNameAndUserId(tagName, ConstantsNumber.ME_ID);
		
		map.addAttribute("title", "标签："+tagName);
		map.addAttribute("list", articles);
		map.addAttribute("type", "tag");
		
		return "tags/show";
	}
}
