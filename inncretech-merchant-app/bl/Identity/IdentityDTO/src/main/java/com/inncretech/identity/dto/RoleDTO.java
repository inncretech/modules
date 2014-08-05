package com.inncretech.identity.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class RoleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2653386386805467367L;

	@NotNull(message = "role name is compulsory")
	private String roleName;

	private String roleDescription;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Override
	public String toString() {
		return "RoleDTO [roleName=" + roleName + ", roleDescription=" + roleDescription + "]";
	}

}