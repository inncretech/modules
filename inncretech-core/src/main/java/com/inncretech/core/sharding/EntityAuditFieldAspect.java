package com.inncretech.core.sharding;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
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
    abstractMutableEntity.setUpdatedAt(new DateTime());
  }
  
  @Before("preStoreEntity() && args(abstractImmutableEntity)")
  public void preStoreImmutableEntity(AbstractImmutatableEntity abstractImmutableEntity) {
    if (abstractImmutableEntity.getCreatedAt() == null) {
      abstractImmutableEntity.setCreatedAt(new DateTime());
    }
  }

  @Pointcut("preSaveEntity() || preUpdateEntity() || preSaveOrUpdateEntity()")
  public void preStoreEntity() { }
  
  @Pointcut("execution(public * save(..))")
  public void preSaveEntity() { }
  
  @Pointcut("execution(public * update(..))")
  public void preUpdateEntity() { }
  
  @Pointcut("execution(public * saveOrUpdate(..))")
  public void preSaveOrUpdateEntity() { }
  
}
