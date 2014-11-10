package com.hlt2008.init;

import javax.annotation.Resource;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hlt2008.model.Admin;
import com.hlt2008.model.Authority;
import com.hlt2008.model.Role;
import com.hlt2008.service.BaseService;


@Component
public class InitAdmin implements ApplicationListener<ApplicationEvent>{
	
	@Resource
	private BaseService baseService;
	
	// tomcat第一次启动会调用这个方法
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// tomcat启动完毕调用该方法
		if(event instanceof ContextRefreshedEvent){
			Admin admin = (Admin)baseService.findObject(Admin.class, "username", "admin");
			if(admin == null){
				 admin = new Admin();
				 admin.setUsername("admin");
				 admin.setPassword("admin");
				 admin.setDescription("该管理员拥有所有权限");
				 baseService.saveOrUpdata(admin);
				 Role role=new Role();
				 role.setDescription("该角色拥有所有权限");
				 role.setRoleName("root");
				 role.setAdmin(admin);
				 baseService.saveOrUpdata(role);
				 Authority authority=new Authority();
				 authority.setAuthorityName("*");
				 authority.setRole(role);
				 baseService.saveOrUpdata(authority);
			}
		}
	}
}
