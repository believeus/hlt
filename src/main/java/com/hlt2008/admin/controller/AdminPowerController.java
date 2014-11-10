package com.hlt2008.admin.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hlt2008.model.Admin;
import com.hlt2008.model.Authority;
import com.hlt2008.model.Role;
import com.hlt2008.service.BaseService;


@Controller
public class AdminPowerController {

	@Resource
	private BaseService baseService;
	
	@RequestMapping(value="/admin/addRoleLogic")
	public String addRoleLogic(ServletRequest request){
		
		Role role = new Role();
		String roleName = request.getParameter("roleName");
		String description = request.getParameter("description");
		role.setRoleName(roleName);
		role.setDescription(description);
		baseService.saveOrUpdata(role);
			
		// 获取被选中的checkbook 并且name="authority"
		String[] parameterValues = request.getParameterValues("authority");
		if(parameterValues !=null){
			for (String authorityName : parameterValues) {
				Authority authority = new Authority();
				authority.setAuthorityName(authorityName);
				authority.setRole(role);
				baseService.saveOrUpdata(authority);
			}
		}
		return "redirect:/admin/roleList.jhtml";
	}
	@RequestMapping(value="/admin/updateRole")
	public String editRoleLogic(ServletRequest request){
		String roleId = request.getParameter("roleId");
		String roleName=request.getParameter("roleName");
		String description = request.getParameter("description");
		Role role=(Role)baseService.findObject(Role.class, Integer.parseInt(roleId));
		role.setRoleName(roleName);
		role.setDescription(description);
		baseService.saveOrUpdata(role);
		List<Authority> authoritys = role.getAuthoritys();
		List<Integer> idList=new ArrayList<Integer>();
		if(authoritys!=null){
		 for (Authority authority : authoritys) {
			idList.add(authority.getId());
		 }
		}
		// 更新权限之前首先删除之前的权限
		baseService.delete(Authority.class, idList);
		// 获取被选中的checkbook 并且name="authority"
		String[] parameterValues = request.getParameterValues("authority");
		if(parameterValues!=null){
			for (String authorityName : parameterValues) {
				Authority authority = new Authority();
				authority.setAuthorityName(authorityName);
				authority.setRole(role);
				baseService.saveOrUpdata(authority);
			}
		}
		
		return "redirect:/admin/roleList.jhtml";
	}
	@RequestMapping(value="/admin/updateRoleForAdmin")
	public String updateRoleForAdmin(ServletRequest request){
		String adminId = request.getParameter("adminId");
		String adminName = request.getParameter("adminName");
		String description = request.getParameter("description");
		String roleId=request.getParameter("roleId");
		String repass = request.getParameter("repass");
		Role role=(Role)baseService.findObject(Role.class, Integer.parseInt(roleId));
		Admin admin=(Admin)baseService.findObject(Admin.class, Integer.parseInt(adminId));
		admin.setUsername(adminName);
		admin.setPassword(repass);
		admin.setDescription(description);
		role.setAdmin(admin);
		baseService.saveOrUpdata(role);
		return "redirect:/admin/adminList.jhtml";
		
		
	}
}
