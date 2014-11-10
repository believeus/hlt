package com.hlt2008.controller;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页面
 * */
@Controller
public class ControllerIndex {
	private static Log log = LogFactory.getLog(ControllerIndex.class);

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		return "index.htm";
	}
	
}
