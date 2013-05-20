package com.inncretech.core.sharding;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.interceptor.TransactionAttribute;


@Aspect
@Component
public class ShardingAspect extends TransactionAspectSupport {

  @Autowired
  private HibernateSessionFactoryManager sessionFactoryService;

  @Autowired
  private IdGenerator idGenService;

  @Pointcut("execution(public * ((@ShardAware *)+).*(..)) && within(@ShardAware *)")
  public void anyPublicMethod() {
  }

  public ShardingAspect() {
    setTransactionAttributeSource(new AnnotationTransactionAttributeSource(new ShardingAnnotationParser()));
  }

  @Around("@annotation(txObject)")
  public Object beforeStartTx(ProceedingJoinPoint jointPoint, ShardAware txObject) {
    try {
      MethodSignature methodSignature = (MethodSignature) jointPoint.getSignature();
      Method method = methodSignature.getMethod();
      TransactionInfo txInfo = createTransactionIfNecessary(method, txObject, jointPoint);
      System.out.println("I m inside" + method.getName());
      Object result = jointPoint.proceed();
      commitTransactionAfterReturning(TransactionAspectSupport.currentTransactionInfo());
      return result;
    } catch (Throwable ex) {
      completeTransactionAfterThrowing(TransactionAspectSupport.currentTransactionInfo(), ex);
      throw new RuntimeException(ex);
    } finally {
      cleanupTransactionInfo(TransactionAspectSupport.currentTransactionInfo());
    }
  }

  protected TransactionInfo createTransactionIfNecessary(Method method, ShardAware txObject, JoinPoint jointPoint) {
    // If the transaction attribute is null, the method is non-transactional.
    TransactionAttribute txAttr = getTransactionAttributeSource().getTransactionAttribute(method, txObject.getClass());
    PlatformTransactionManager tm = null;
    if(txObject.shardStrategy().equals("entityid"))
      tm = sessionFactoryService.getTransactionManager(idGenService.getShardId((Long) jointPoint.getArgs()[0], txObject.shardType()));
    else
      tm = sessionFactoryService.getTransactionManager((Integer) jointPoint.getArgs()[0]);

    return createTransactionIfNecessary(tm, txAttr, methodIdentification(method, txObject.getClass()));
  }

}
