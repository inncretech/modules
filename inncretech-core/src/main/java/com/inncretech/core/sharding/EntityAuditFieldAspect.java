package com.inncretech.core.sharding;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

import com.inncretech.core.model.AbstractImmutatableEntity;
import com.inncretech.core.model.AbstractMutableEntity;

/**
 * Aspect responsible for setting common audit field values
 * 
 * @author Erik Hayes
 */
@Aspect
@Component
public class EntityAuditFieldAspect {

  @Before("preStoreEntity() && args(abstractMutableEntity)")
  public void preStoreMutableEntity(AbstractMutableEntity abstractMutableEntity) {
    if (abstractMutableEntity.getCreatedAt() == null) {
      abstractMutableEntity.setUpdatedAt(currentTimeWithoutFractionalSeconds());
    }
  }

  @Before("preStoreEntity() && args(abstractImmutableEntity)")
  public void preStoreImmutableEntity(AbstractImmutatableEntity abstractImmutableEntity) {
    if (abstractImmutableEntity.getCreatedAt() == null) {
      abstractImmutableEntity.setCreatedAt(currentTimeWithoutFractionalSeconds());
    }
  }

  /**
   * Current time that is compatible with <code>equals</code> before and after
   * an object is persisted.
   * 
   * @return Current time in UTC without fractional seconds
   */
  private DateTime currentTimeWithoutFractionalSeconds() {
    return new DateTime(DateTimeZone.UTC).secondOfMinute().roundFloorCopy();
  }

  @Pointcut("preSaveEntity() || preUpdateEntity() || preSaveOrUpdateEntity()")
  public void preStoreEntity() {
  }

  @Pointcut("execution(public * save(..))")
  public void preSaveEntity() {
  }

  @Pointcut("execution(public * update(..))")
  public void preUpdateEntity() {
  }

  @Pointcut("execution(public * saveOrUpdate(..))")
  public void preSaveOrUpdateEntity() {
  }

}
