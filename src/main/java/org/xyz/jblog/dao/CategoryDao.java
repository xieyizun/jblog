/**
 * @author xieyizun@163.com
 * @date 2018年4月6日
 * @version 1.0
 */
package org.xyz.jblog.dao;

import java.util.List;

import org.xyz.jblog.entity.Category;

/**
 *
 */
public interface CategoryDao {
	List<Category> getAllCategoriesByUserId(Integer userId);
	Category getCategoryById(Integer id);
}
