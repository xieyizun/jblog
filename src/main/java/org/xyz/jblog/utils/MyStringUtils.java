/**
 * @author xieyizun@163.com
 * @date 2018年4月14日
 * @version 1.0
 */
package org.xyz.jblog.utils;

/**
 *
 */
public final class MyStringUtils {
	public static boolean isEmptyOrNumm(String str) {
		return str==null || "".equals(str);
	}
	
	public static Integer strToInt(String str) {
		if (MyStringUtils.isEmptyOrNumm(str)) {
			return null;
		} else {
			return Integer.parseInt(str);
		}
	}
}
