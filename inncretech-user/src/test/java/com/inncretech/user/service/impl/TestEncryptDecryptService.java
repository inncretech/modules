package com.inncretech.user.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.user.service.EncryptDecryptService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationcontext-user.xml" })
public class TestEncryptDecryptService {

  @Autowired
  private EncryptDecryptService encryptDecryptService;

  private static final String TEST_STRING = "this is a word";

  @Test
  public void testEncryptor() {
    String encrypted = encryptDecryptService.encrypt(TEST_STRING);
    Assert.assertEquals(TEST_STRING, encryptDecryptService.decrypt(encrypted));
  }
}