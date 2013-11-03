package com.inncretech.user.service.impl;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created with IntelliJ IDEA.
 * User: pranab
 * Date: 11/3/13
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserSaltProvider implements SaltSource{

  @Override
  public Object getSalt(UserDetails user) {
    return null;
  }
}
