package com.inncretech.identity.service.utils.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.inncretech.identity.db.beans.Role;
import com.inncretech.identity.db.beans.User;
import com.inncretech.identity.dto.RoleDTO;
import com.inncretech.identity.dto.UserDTO;

@Component
public class IdentityServiceMapper {

	RoleDTO createRoleDTOFromRole(Role role) {
		RoleDTO roleDTO = new RoleDTO();
		if (role != null) {
			roleDTO.setRoleName(role.getRoleName());
			roleDTO.setRoleDescription(role.getRoleDescription());
		}
		return roleDTO;
	}

	public UserDTO createUserDTOFromUser(User user) {
		UserDTO userDTO = new UserDTO();
		if (user != null) {
			userDTO.setUserId(user.getUserId());
			userDTO.setEmail(user.getEmail());
			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setPassword(user.getPassword());
			userDTO.setUserName(user.getUserName());

			if (user.getRoles() != null && !user.getRoles().isEmpty()) {
				List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>(user.getRoles().size());
				for (Role role : user.getRoles()) {
					roleDTOs.add(createRoleDTOFromRole(role));
				}
				userDTO.setRoles(roleDTOs);
			}
		}
		return userDTO;
	}

	Role createRoleFromRoleDTO(RoleDTO roleDTO) {
		Role role = new Role();
		if (roleDTO != null) {
			role.setRoleName(roleDTO.getRoleName());
			role.setRoleDescription(roleDTO.getRoleDescription());
		}
		return role;
	}

	public User createUserFromUserDTO(UserDTO userDTO) {
		User user = new User();
		if (userDTO != null) {
			user.setUserId(userDTO.getUserId());
			user.setEmail(userDTO.getEmail());
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setPassword(userDTO.getPassword());
			user.setUserName(userDTO.getUserName());

			if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
				List<Role> roles = new ArrayList<Role>(userDTO.getRoles().size());
				for (RoleDTO roleDTO : userDTO.getRoles()) {
					roles.add(createRoleFromRoleDTO(roleDTO));
				}
				user.setRoles(roles);
			}
		}
		return user;
	}
}