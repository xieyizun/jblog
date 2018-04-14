/**
 * @author xieyizun@163.com
 * @date 2018年4月14日
 * @version 1.0
 */
package org.xyz.jblog.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xyz.blog.interfaces.Paginatable;
import org.xyz.jblog.entity.Article;

import com.github.pagehelper.Page;

/**
 *
 */
public final class PaginatorUtils {
	public static final <T extends Paginatable>  Map<String, Object> getPainatorInfo(List<T> list, String nextPageUrl) {
		Map<String, Object> rsMap = new HashMap<>();
		
		Page<T> pageInfo = (Page<T>) list;
		rsMap.put("pages", pageInfo.getPages());
		rsMap.put("total", pageInfo.getTotal());
		rsMap.put("currentPage", pageInfo.getPageNum());
		rsMap.put("nextPageUrl", nextPageUrl);
		return rsMap;
	}
}
