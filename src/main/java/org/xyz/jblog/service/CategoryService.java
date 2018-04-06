/**
 * @author xieyizun@163.com
 * @date 2018年4月6日
 * @version 1.0
 */
package org.xyz.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xyz.jblog.dao.CategoryDao;
import org.xyz.jblog.entity.Category;

/**
 *
 */
@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}
	
	public Category getCategoryById(Integer id) {
		return categoryDao.getCategoryById(id);
	}
}
