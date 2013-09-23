package com.inncretech.user.service.impl;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: pranab
 * Date: 9/22/13
 * Time: 8:13 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PasswordService {

  public String generateResetPasswordToken(){
    return new String(Base64.encode(UUID.randomUUID().toString().getBytes()));
  }
}
