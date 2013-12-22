package com.inncretech.core.sharding;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.interceptor.TransactionAttribute;

import com.inncretech.core.model.IdEntity;

@Aspect
@Component
public class ShardingAspect extends TransactionAspectSupport {

  @Autowired
  private HibernateSessionFactoryManager sessionFactoryService;

  @Autowired
  private IdGenerator idGenService;

  public ShardingAspect() {
    setTransactionAttributeSource(new AnnotationTransactionAttributeSource(new ShardingAnnotationParser()));
  }

  @Around("@annotation(txObject)")
  public Object beforeStartTx(ProceedingJoinPoint jointPoint, ShardAware txObject) throws Throwable {
    try {
      MethodSignature methodSignature = (MethodSignature) jointPoint.getSignature();
      Method method = methodSignature.getMethod();
      createTransactionIfNecessary(method, txObject, jointPoint);
      Object result = jointPoint.proceed();
      commitTransactionAfterReturning(TransactionAspectSupport.currentTransactionInfo());
      return result;
    } catch (Throwable ex) {
      ex.printStackTrace();
      completeTransactionAfterThrowing(TransactionAspectSupport.currentTransactionInfo(), ex);
      throw ex;
    } finally {
      cleanupTransactionInfo(TransactionAspectSupport.currentTransactionInfo());
    }
  }

  protected TransactionInfo createTransactionIfNecessary(Method method, ShardAware txObject, JoinPoint jointPoint) {
    // If the transaction attribute is null, the method is
    // non-transactional.
    TransactionAttribute txAttr = getTransactionAttributeSource().getTransactionAttribute(method, txObject.getClass());
    PlatformTransactionManager tm = null;
    Long entityID = null;
    Object fParam = jointPoint.getArgs()[0];

    if (txObject.shardStrategy().equals("entityid")) {
      if (fParam instanceof Long) {
        entityID = (Long) fParam;
      } else if (fParam instanceof IdEntity) {
        entityID = ((IdEntity) fParam).getId();
      }

      tm = sessionFactoryService.getTransactionManager(idGenService.getShardId(entityID, txObject.shardType()));
    } else
      tm = sessionFactoryService.getTransactionManager((Integer) jointPoint.getArgs()[0]);

    return createTransactionIfNecessary(tm, txAttr, methodIdentification(method, txObject.getClass()));
  }

}
