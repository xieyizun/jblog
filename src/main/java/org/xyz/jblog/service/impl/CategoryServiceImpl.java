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
	public List<Category> getAllCategoriesByUserId(Integer userId) {
		return categoryDao.getAllCategoriesByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see org.xyz.jblog.service.CategoryService#getCategoryById(java.lang.Integer)
	 */
	@Override
	public Category getCategoryById(Integer id) {
		return categoryDao.getCategoryById(id);
	}

	/* (non-Javadoc)
	 * @see org.xyz.jblog.service.CategoryService#getCategoryByIdAndUserId(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Category getCategoryByIdAndUserId(Integer id, Integer userId) {
		// TODO Auto-generated method stub
		return categoryDao.getCategoryByIdAndUserId(id, userId);
	}

	/* (non-Javadoc)
	 * @see org.xyz.jblog.service.CategoryService#insertCategory(org.xyz.jblog.entity.Category)
	 */
	@Override
	public Integer insertCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.insertCategory(category);
	}

	/* (non-Javadoc)
	 * @see org.xyz.jblog.service.CategoryService#updateCategory(org.xyz.jblog.entity.Category)
	 */
	@Override
	public Integer updateCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.updateCategory(category);
	}

}
