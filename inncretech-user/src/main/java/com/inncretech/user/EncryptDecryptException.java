package com.inncretech.user;

import java.util.Collection;

public class EncryptDecryptException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public EncryptDecryptException(Throwable cause) {
    super(cause);
  }

  public EncryptDecryptException(Throwable cause, Collection<?> errorCodes) {
    super(cause);
  }

  public EncryptDecryptException(String message) {
    super(message);
  }

  public EncryptDecryptException(String message, Throwable cause) {
    super(message, cause);
  }
}
