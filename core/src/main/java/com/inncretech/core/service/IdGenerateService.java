package com.inncretech.core.service;

public interface IdGenerateService {
  
  Long get();
  Integer getShardId(Long id);

}
