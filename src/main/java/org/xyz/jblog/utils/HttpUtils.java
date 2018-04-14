/**
 * @author xieyizun@163.com
 * @date 2018年4月8日
 * @version 1.0
 */
package org.xyz.jblog.utils;

import javax.servlet.http.HttpServletRequest;

import org.xyz.jblog.entity.User;

/**
 *
 */
public final class HttpUtils {
	public static User getCurrentLoginUser(HttpServletRequest request) {
		return (User)request.getSession().getAttribute("currentUser");
	}
}
