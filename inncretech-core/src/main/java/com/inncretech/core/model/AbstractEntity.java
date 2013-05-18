package com.inncretech.core.model;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable{

  public abstract Long getId();
  
  public abstract void setId(Long id);
}
