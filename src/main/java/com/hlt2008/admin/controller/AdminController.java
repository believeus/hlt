package com.hlt2008.admin.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hlt2008.model.Admin;
import com.hlt2008.model.Authority;
import com.hlt2008.model.Role;
import com.hlt2008.service.BaseService;


@Controller
public class AdminController {
	
	@Resource
	private BaseService baseService;

	@RequestMapping(value = "/admin/manager")
	public String manager() {
		return "/WEB-INF/back/index.jsp";
	}
	
	@RequestMapping(value="/admin/login")
	public String admin(){
		return "/WEB-INF/back/login.jsp";
	}
	
	// 更新管理员的密码
	@RequestMapping(value="/admin/updatePwd")
	public 	@ResponseBody String updatePwd(String newpass,HttpSession session){
		Admin admin=(Admin)session.getAttribute("sessionUser");
		admin = (Admin) baseService.findObject(Admin.class, "id",admin.getId());
		admin.setPassword(newpass);
		baseService.saveOrUpdata(admin);
		return newpass;
	}
	
	/**
	 * 权限管理:编辑角色权限
	 * @return
	 */
	@RequestMapping(value="/admin/power")
	public String power(Integer roleId,ServletRequest request){
		Role role=(Role)baseService.findObject(Role.class, roleId);
		List<Authority> authoritys = role.getAuthoritys();
		request.setAttribute("authoritys", authoritys);
		request.setAttribute("roleId", roleId);
		request.setAttribute("role", role);
		return "/WEB-INF/back/power/powerEdit.jsp";
	}
	
	/**
	 * 添加管理员
	 * @return
	 */
	@RequestMapping(value="/admin/addAdmin")
	public String addAdmin(HttpServletRequest request){
		@SuppressWarnings("unchecked")
		List<Role> roles = (List<Role>) baseService.findObjectList(Role.class);
		request.setAttribute("roles", roles);
		return "/WEB-INF/back/power/addAdmin.jsp";
	}
	/**
	 * 编辑管理员
	 * @return
	 */
	@RequestMapping(value="/admin/editAdmin")
	public String editAdmin(HttpServletRequest request,Integer adminId){
		@SuppressWarnings("unchecked")
		List<Role> roles = (List<Role>)baseService.findObjectList(Role.class);
		Admin admin=(Admin)baseService.findObject(Admin.class,adminId);
		request.setAttribute("roles", roles);
		request.setAttribute("admin", admin);
		request.setAttribute("adminId", adminId);
		return "/WEB-INF/back/power/editAdmin.jsp";
	}
	
	// ajax判断管理员用户名是否存在
	@RequestMapping(value="/admin/ajaxValidateAdmin")
	public @ResponseBody String ajaxValidateAdmin(String adminName){
		Admin admin = (Admin)baseService.findObject(Admin.class, "username", adminName);
		if(admin != null){
			return "exist";
		}
		return "success";
	}
	
	@RequestMapping(value="/admin/addRoleForAdmin")
	public String addRoleForAdmin(HttpServletRequest request){
		String adminName = request.getParameter("adminName");
		String roleId = request.getParameter("roleId");
		String description = request.getParameter("description");
		String repass = request.getParameter("repass");
		Admin admin = new Admin();
		admin.setUsername(adminName);
		admin.setPassword(repass);
		admin.setDescription(description);
		if(!StringUtils.isEmpty(roleId)){
		 Role role = (Role) baseService.findObject(Role.class, Integer.valueOf(roleId));
		 role.setAdmin(admin);
		}
		baseService.saveOrUpdata(admin);
		return "redirect:/admin/adminList.jhtml";
	}
	
	/**
	 * 添加角色
	 * @return
	 */
	@RequestMapping(value="/admin/addRole")
	public String addRole(){
		return "/WEB-INF/back/power/addRole.jsp";
	}
	
	/**
	 * 角色列表
	 * @return
	 */
	@RequestMapping(value="/admin/roleList")
	public String roleList(HttpServletRequest request){
		@SuppressWarnings("unchecked")
		List<Role> roles = (List<Role>) baseService.findObjectList(Role.class);
		request.setAttribute("roles", roles);
		return "/WEB-INF/back/power/roleList.jsp";
	}
	
	@RequestMapping(value="/admin/deleteRole")
	public String deleteRole(Integer roleId){
		baseService.delete(Role.class, roleId);
		return "redirect:/admin/roleList.jhtml";
	}
	
	/**
	 * 管理员列表
	 * @return
	 */
	@RequestMapping(value="/admin/adminList")
	public String adminList(HttpServletRequest request){
		@SuppressWarnings("unchecked")
		List<Admin> admins = (List<Admin>) baseService.findObjectList(Admin.class);
		request.setAttribute("admins", admins);
		return "/WEB-INF/back/power/adminList.jsp";
	}
	
	@RequestMapping(value="/admin/deleteAdmin")
	public String deleteAdmin(Integer adminId){
		baseService.delete(Admin.class, adminId);
		return "redirect:/admin/adminList.jhtml";
	}
	@RequestMapping(value="/admin/logout")
	public String adminLogin(){
		try {
			Subject currentUser = SecurityUtils.getSubject();
			if (SecurityUtils.getSubject().getSession() != null) {
				currentUser.logout();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/login.jhtml";
	}
	@RequestMapping(value="/authorException")
	public String authorException(){
		return "/WEB-INF/back/authorException.jsp";
	}
}
