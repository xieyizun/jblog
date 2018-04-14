/**
 * @author xieyizun@163.com
 * @date 2018年4月8日
 * @version 1.0
 */
package org.xyz.jblog.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xyz.jblog.utils.HttpUtils;

/**
 *
 */
public class LoginFilter implements Filter {
	private FilterConfig filterConfig;
	private List<String> ignoreLoginUrls = new ArrayList<String>();
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		if (HttpUtils.getCurrentLoginUser(request) != null || isIgnoreUrl(request.getServletPath())) {
			chain.doFilter(req, resp);
		} else {
			HttpServletResponse response = (HttpServletResponse)resp;
			response.sendRedirect(request.getContextPath()+"/account/login");
			return;
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		String ignoreLoginUrlsStr = this.filterConfig.getInitParameter("ignoreLoginUrls");
		if (ignoreLoginUrlsStr != null && ignoreLoginUrlsStr.length() > 0) {
			ignoreLoginUrls = Arrays.asList(ignoreLoginUrlsStr.split(";"));
		}
	}
	
	private boolean isIgnoreUrl(String url) {
		for (String ignoreUrl : ignoreLoginUrls) {
			if (url.startsWith(ignoreUrl)) {
				return true;
			}
		}
		return false;
	}

}
