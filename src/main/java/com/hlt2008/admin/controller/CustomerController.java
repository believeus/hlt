package com.hlt2008.admin.controller;



import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hlt2008.service.BaseService;


@Controller
public class CustomerController {
	@Resource
	private BaseService baseService;

	
	/**
	 * 客户感言列表
	 * @return
	 */
	@RequestMapping(value="/admin/customerList")
	public String newsList(){
		return "/WEB-INF/back/customer/list.jsp";
	}
	/**
	 * 客户感言添加
	 * @return
	 */
	@RequiresPermissions("customer:create")
	@RequestMapping(value="/admin/customerAdd")
	public String newsAdd(){
		return "/WEB-INF/back/customer/add.jsp";
	}
	/**
	 * 客户感言修改
	 * @return
	 */
	@RequiresPermissions("customer:update")
	@RequestMapping(value="/admin/customerEdit")
	public String newsEdit(){
		return "/WEB-INF/back/customer/edit.jsp";
	}
	/**
	 * 客户感言删除
	 * @return
	 */
	@RequiresPermissions("customer:delete")
	@RequestMapping(value="/admin/customerDel")
	public String newsDel(){
		return "/WEB-INF/back/customer/list.jsp";
	}
}
