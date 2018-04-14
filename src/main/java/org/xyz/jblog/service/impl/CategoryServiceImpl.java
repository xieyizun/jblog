/**
 * @author xieyizun@163.com
 * @date 2018年4月14日
 * @version 1.0
 */
package org.xyz.jblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xyz.jblog.dao.CategoryDao;
import org.xyz.jblog.entity.Category;
import org.xyz.jblog.service.CategoryService;

/**
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	/* (non-Javadoc)
	 * @see org.xyz.jblog.service.CategoryService#getAllCategories()
	 */
	@Override
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}

	/* (non-Javadoc)
	 * @see org.xyz.jblog.service.CategoryService#getCategoryById(java.lang.Integer)
	 */
	@Override
	public Category getCategoryById(Integer id) {
		return categoryDao.getCategoryById(id);
	}

}
