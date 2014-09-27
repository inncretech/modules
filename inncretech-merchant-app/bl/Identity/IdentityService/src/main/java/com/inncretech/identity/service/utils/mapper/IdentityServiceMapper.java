package com.inncretech.identity.service.utils.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.inncretech.identity.db.beans.Role;
import com.inncretech.identity.db.beans.User;
import com.inncretech.identity.db.beans.UserIdentifier;
import com.inncretech.identity.dto.RoleDTO;
import com.inncretech.identity.dto.UserDTO;

enum Object_Type {
	SOURCE, DESTINATION;
}

@Component
public class IdentityServiceMapper {

	public void mapUserFromUserDTO(UserDTO userDTO, User user) {

		checkArgument(userDTO, Object_Type.SOURCE);
		checkArgument(user, Object_Type.DESTINATION);

		user.setUserId(userDTO.getUserId());
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPassword(userDTO.getPassword());
		user.setUserName(userDTO.getUserName());
		user.setIsActive(userDTO.getIsActive());
		user.setUserIdentifier(UserIdentifier.valueOf(userDTO.getUserIdentifier().name()));

		if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
			List<Role> roles = new ArrayList<Role>(userDTO.getRoles().size());
			for (RoleDTO roleDTO : userDTO.getRoles()) {
				Role role = new Role();
				mapRoleFromRoleDTO(roleDTO, role);
				roles.add(role);
			}
			user.setRoles(roles);
		}
	}

	public void mapUserDTOFromUser(User user, UserDTO userDTO) {

		checkArgument(user, Object_Type.SOURCE);
		checkArgument(userDTO, Object_Type.DESTINATION);

		userDTO.setUserId(user.getUserId());
		userDTO.setEmail(user.getEmail());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setPassword(user.getPassword());
		userDTO.setUserName(user.getUserName());

		userDTO.setUserIdentifier(com.inncretech.identity.dto.UserIdentifier.valueOf(user.getUserIdentifier().name()));

		if (user.getRoles() != null && !user.getRoles().isEmpty()) {
			List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>(user.getRoles().size());
			for (Role role : user.getRoles()) {
				RoleDTO roleDTO = new RoleDTO();
				mapRoleDTOFromRole(role, roleDTO);
				roleDTOs.add(roleDTO);
			}
			userDTO.setRoles(roleDTOs);
		}
	}

	void mapRoleDTOFromRole(Role role, RoleDTO roleDTO) {
		if (role != null) {
			roleDTO.setRoleName(role.getRoleName());
			roleDTO.setRoleDescription(role.getRoleDescription());
		}
	}

	void mapRoleFromRoleDTO(RoleDTO roleDTO, Role role) {
		if (roleDTO != null) {
			role.setRoleName(roleDTO.getRoleName());
			role.setRoleDescription(roleDTO.getRoleDescription());
		}
	}

	void checkArgument(Object object, Object_Type type) {
		if (object == null) {
			String message = null;
			if (type == Object_Type.DESTINATION) {
				message = "Destination object is null.";
			} else if (type == Object_Type.SOURCE) {
				message = "Source object is null.";
			} else {
				throw new RuntimeException("Invalid object type passed.");
			}
			throw new IllegalArgumentException(message);
		}
	}
}