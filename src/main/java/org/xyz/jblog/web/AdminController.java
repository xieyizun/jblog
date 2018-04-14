/**
 * @author xieyizun@163.com
 * @date 2018年4月6日
 * @version 1.0
 */
package org.xyz.jblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping(value="/manage")
	public String manage() {
		return "admin/manage";
	}
}
