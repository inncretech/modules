package com.inncretech.core.sharding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.sharding.dao.IdEntryDao;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.model.ShardConfig;

@Service
public class IdGeneratorImpl implements IdGenerator {

  @Autowired
  private ShardConfigDao shardConfigDao;

  private Random random = new Random();

  private static final int TIME_BITS = 41;

  private static final int SHARD_BITS = 13;

  @Autowired
  private IdEntryDao idEntryDao;

  public Long getNewUserId() {
    return get(ShardType.USER);
  }

  public Long getNewSourceId() {
    return get(ShardType.SOURCE);
  }

  private Long get(ShardType shardType) {
    return getIdOnShard(selectShardId(shardType.getType()).getId() - shardType.getBaseId());
  }

  private ShardConfig selectShardId(int shardType) {
    List<ShardConfig> activeShards = shardConfigDao.getAllActiveShards(shardType);
    return activeShards.get(random.nextInt(activeShards.size()));

  }

  private Long nextSequence(Integer shardId) {
    return idEntryDao.getNextId(shardId);
  }

  @Override
  public Integer getShardId(Long id, ShardType shardType) {
    Long newId = new Long(id);
    newId = newId >> 10;
    newId = newId & 0X3FFL;
    return newId.intValue() + shardType.getBaseId();
  }

  public Long getNewIdOnSourceShard(Long entityId) {
    return getIdOnShard(getShardId(entityId, ShardType.SOURCE));
  }

  public Long getNewIdOnUserShard(Long entityId) {
    return getIdOnShard(getShardId(entityId, ShardType.USER));
  }

  public Long getIdOnShard(Integer shardId) {
    Long id = System.currentTimeMillis() << (64 - TIME_BITS);
    id |= ShardType.getLogicalShardId(shardId) << (64 - TIME_BITS - SHARD_BITS);
    id |= (nextSequence(shardId) % (int) Math.pow(2.0, (double) (64.0 - SHARD_BITS - TIME_BITS)));
    id &= 0X3FFFFFFFFFFFFFFFL;
    return id;
  }

  public Map<Integer, List<Long>> bucketizeEntites(List<Long> entityIds, ShardType shardType) {
    Map<Integer, List<Long>> buckets = new HashMap<Integer, List<Long>>();
    for (Long entityId : entityIds) {
      Integer shardId = getShardId(entityId, shardType);
      if (!buckets.containsKey(shardId))
        buckets.put(shardId, new ArrayList<Long>());
      buckets.get(shardId).add(entityId);
    }
    return buckets;
  }
}
