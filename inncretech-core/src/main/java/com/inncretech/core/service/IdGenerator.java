package com.inncretech.core.service;

import java.util.List;
import java.util.Map;

public interface IdGenerator {

  public Long getNewUserId();

  public Long getNewSourceId();

  Integer getShardId(Long id);

  public Map<Integer, List<Long>> bucketizeEntites(List<Long> entityIds);

}
