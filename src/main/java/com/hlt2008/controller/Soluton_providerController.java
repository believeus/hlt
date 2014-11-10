package com.hlt2008.controller;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 产品简介
 * */
@Controller
public class Soluton_providerController {

	@RequestMapping(value = "/total_soluton_provider")
	public String index(HttpServletRequest request) {
		return "total_soluton_provider.html";
	}
	
}
