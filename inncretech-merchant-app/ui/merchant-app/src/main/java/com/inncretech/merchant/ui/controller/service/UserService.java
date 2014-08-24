package com.inncretech.merchant.ui.controller.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.inncretech.identity.dto.RoleDTO;
import com.inncretech.identity.dto.UserDTO;
import com.inncretech.identity.service.IdentityService;

public class UserService implements UserDetailsService {

	@Autowired
	private IdentityService identityService;

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO userDTO = null;
		try {
			userDTO = identityService.getUserByEmail(username);
		} catch (Exception e) {
			logger.error("Exception occured while loadingUserByUsername ", e);
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(userDTO);
	}

	public void signin(UserDTO userDTO) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(userDTO));
	}

	private Authentication authenticate(UserDTO userDTO) {
		return new UsernamePasswordAuthenticationToken(createUser(userDTO), null, createAuthority(userDTO));
	}

	private User createUser(UserDTO userDTO) {
		return new User(userDTO.getEmail(), userDTO.getPassword(), createAuthority(userDTO));
	}

	private List<GrantedAuthority> createAuthority(UserDTO userDTO) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
			for (RoleDTO roleDTO : userDTO.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(roleDTO.getRoleName()));
			}
		}
		return authorities;
	}
}