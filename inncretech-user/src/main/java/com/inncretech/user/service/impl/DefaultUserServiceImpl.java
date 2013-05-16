package com.inncretech.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.user.dao.UserDao;

public class DefaultUserServiceImpl {
  
  @Autowired
  private UserDao userDao;

}
