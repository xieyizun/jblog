/**
 * @author xieyizun@163.com
 * @date 2018年4月15日
 * @version 1.0
 */
package org.xyz.jblog.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xyz.jblog.entity.Category;
import org.xyz.jblog.entity.User;
import org.xyz.jblog.service.CategoryService;
import org.xyz.jblog.utils.HttpUtils;
import org.xyz.jblog.utils.MyStringUtils;

/**
 *
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String edit(HttpServletRequest request, @PathVariable("id")Integer id, ModelMap map) {
		User currentUser = HttpUtils.getCurrentLoginUser(request);
		Category category = categoryService.getCategoryByIdAndUserId(id, currentUser.getId());
		if (category != null) {
			List<Category> categories = categoryService.getAllCategoriesByUserId(currentUser.getId());
			map.addAttribute("categories", categories);
			map.addAttribute("category", category);
			map.addAttribute("manageType", "category");
			
			return "account/manage";
		} else {
			return "error404";
		}
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(HttpServletRequest request, ModelMap map) {
		User currentUser = HttpUtils.getCurrentLoginUser(request);
		
		Integer categoryId = MyStringUtils.strToInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Integer parentId = MyStringUtils.strToInt(request.getParameter("parentId"));
		Category parent = null;
		if (parentId != null) {
			parent = categoryService.getCategoryByIdAndUserId(parentId, currentUser.getId());
		}
		
		// 更新
		if (categoryId != null) {
			Category category = categoryService.getCategoryByIdAndUserId(categoryId, currentUser.getId());
			if (category != null) {
				category.setName(name);
				category.setParent(parent);
				
				categoryService.updateCategory(category);
			} else {
				return "error404";
			}
		} else {
		// 创建
			Category category = new Category();
			category.setName(name);
			category.setParent(parent);
			category.setUser(currentUser);
			
			categoryService.insertCategory(category);
		}
		
		return "redirect:/account/manage?type=category";
	}
}
