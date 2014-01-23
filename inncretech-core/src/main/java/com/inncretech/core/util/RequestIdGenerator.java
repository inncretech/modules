package com.inncretech.core.util;

import com.inncretech.core.sharding.ShardType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class RequestIdGenerator {

  private static final int TIME_BITS = 41;

  private static final int SHARD_BITS = 8;

  @Value("${app.server.id:1}")
  private int serverId;

  private AtomicLong counter = new AtomicLong(1000);

  public String generateRequestId(){
    Long id = System.currentTimeMillis() << (64 - TIME_BITS);
    id |= serverId << (64 - TIME_BITS - SHARD_BITS);
    id |= (counter.incrementAndGet() % (int) Math.pow(2.0, (double) (64.0 - SHARD_BITS - TIME_BITS)));
    id &= 0X3FFFFFFFFFFFFFFFL;
    return id.toString();
  }
}
