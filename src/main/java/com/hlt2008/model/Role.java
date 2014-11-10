package com.hlt2008.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Role extends TbaseEntity{

	private static final long serialVersionUID = 3691434950191147508L;
	
	private String roleName;
	private String description;
	private Admin admin;
	private List<Authority> authoritys=new ArrayList<Authority>();
	
	@OneToMany(mappedBy="role",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public List<Authority> getAuthoritys() {
		return authoritys;
	}
	public void setAuthoritys(List<Authority> authoritys) {
		this.authoritys = authoritys;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@OneToOne
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
