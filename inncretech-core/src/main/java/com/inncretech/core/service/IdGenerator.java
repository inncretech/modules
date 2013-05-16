package com.inncretech.core.service;

public interface IdGenerator {
  
  Long get();
  Integer getShardId(Long id);

}
