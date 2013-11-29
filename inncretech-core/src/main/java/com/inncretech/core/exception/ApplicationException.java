package com.inncretech.core.exception;

public class ApplicationException extends RuntimeException{

  private String code;

  public ApplicationException(String code , String message){
    super(message);
    this.code = code;
  }
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

}
