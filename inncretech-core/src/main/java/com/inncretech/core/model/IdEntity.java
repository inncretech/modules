package com.inncretech.core.model;

import java.io.Serializable;

public interface  IdEntity extends Serializable {

  public abstract Long getId();

  public abstract void setId(Long id);
}
