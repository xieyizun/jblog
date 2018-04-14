/**
 * @author xieyizun@163.com
 * @date 2018年4月14日
 * @version 1.0
 */
package org.xyz.jblog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 */
public final class MyDateUtils {
	public static final String formatDateTime(Date dateTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateTime==null ? "" : format.format(dateTime);
	}
}
