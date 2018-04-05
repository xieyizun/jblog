/**
 * @author xieyizun@163.com
 * @date 2018年4月5日
 * @version 1.0
 */
package org.xyz.jblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Controller
@RequestMapping("/account")
public class AccountController {
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(ModelMap map) {
		map.put("hideLogin", true);
		return "login";
	}
}
