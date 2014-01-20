package com.inncretech.user.service;

public interface EncryptDecryptService {

  String encrypt(String message);

  String decrypt(String encrptedMessage);
}