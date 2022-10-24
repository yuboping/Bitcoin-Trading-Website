package com.gsww.qyws.gzbd.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面跳转路由
 * @author zhangjl
 *
 */
@Controller
@RequestMapping(value = "/route")
public class RouteController extends BaseController {

	/**
	 * @param path：文件夹
	 * @param page：页面
	 * @return
	 */
	@RequestMapping(value = "/{path}/{page}")
	public String swapMonitor(HttpServletRequest request, @PathVariable String path, @PathVariable String page) {
		return "/"+path+"/"+page;
	}
}
