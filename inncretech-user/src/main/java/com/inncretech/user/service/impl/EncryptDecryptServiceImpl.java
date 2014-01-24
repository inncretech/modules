package com.inncretech.user.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.annotation.PostConstruct;
import javax.crypto.KeyGenerator;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Service;

import com.inncretech.user.EncryptDecryptException;
import com.inncretech.user.service.EncryptDecryptService;

@Service
public class EncryptDecryptServiceImpl implements EncryptDecryptService {

  private StandardPBEStringEncryptor standardPBEStringEncryptor;

  public EncryptDecryptServiceImpl() {
    Security.addProvider(new BouncyCastleProvider());
  }
  
  @PostConstruct
  public void init() {
    KeyGenerator generator;
    try {
      generator = KeyGenerator.getInstance("AES", "BC");
      generator.init(256);
      byte[] tokenKey = generator.generateKey().getEncoded();
      standardPBEStringEncryptor = new StandardPBEStringEncryptor();
      standardPBEStringEncryptor.setProvider(Security.getProvider(BouncyCastleProvider.PROVIDER_NAME));
      standardPBEStringEncryptor.setAlgorithm("PBEWITHSHA256AND256BITAES-CBC-BC");
      standardPBEStringEncryptor.setPassword(new String(tokenKey));
    } catch (NoSuchAlgorithmException e) {
      throw new EncryptDecryptException(e);
    } catch (NoSuchProviderException e) {
      throw new EncryptDecryptException(e);
    }
  }

  @Override
  public String encrypt(String message) {
    return standardPBEStringEncryptor.encrypt(message);
  }

  @Override
  public String decrypt(String encrptedMessage) {
    return standardPBEStringEncryptor.decrypt(encrptedMessage);
  }
}