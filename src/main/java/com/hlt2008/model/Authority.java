package com.hlt2008.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Authority extends TbaseEntity{

	private static final long serialVersionUID = -9077588934802148130L;
	
	private String authorityName;
	private Role role;

	@ManyToOne
	@JoinColumn(name = "fk_roleId", referencedColumnName = "id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
}
