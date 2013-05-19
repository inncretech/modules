package com.inncretech.core.sharding;

import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.transaction.annotation.TransactionAnnotationParser;
import org.springframework.transaction.interceptor.NoRollbackRuleAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;

public class ShardingAnnotationParser implements TransactionAnnotationParser, Serializable {

  public TransactionAttribute parseTransactionAnnotation(AnnotatedElement ae) {
    ShardAware ann = AnnotationUtils.getAnnotation(ae, ShardAware.class);
    if (ann != null) {
      return parseTransactionAnnotation(ann);
    } else {
      return null;
    }
  }

  public TransactionAttribute parseTransactionAnnotation(ShardAware ann) {
    RuleBasedTransactionAttribute rbta = new RuleBasedTransactionAttribute();
    rbta.setPropagationBehavior(ann.propagation().value());
    rbta.setIsolationLevel(ann.isolation().value());
    rbta.setTimeout(ann.timeout());
    rbta.setReadOnly(ann.readOnly());
    rbta.setQualifier(ann.value());
    ArrayList<RollbackRuleAttribute> rollBackRules = new ArrayList<RollbackRuleAttribute>();
    Class[] rbf = ann.rollbackFor();
    for (Class rbRule : rbf) {
      RollbackRuleAttribute rule = new RollbackRuleAttribute(rbRule);
      rollBackRules.add(rule);
    }
    String[] rbfc = ann.rollbackForClassName();
    for (String rbRule : rbfc) {
      RollbackRuleAttribute rule = new RollbackRuleAttribute(rbRule);
      rollBackRules.add(rule);
    }
    Class[] nrbf = ann.noRollbackFor();
    for (Class rbRule : nrbf) {
      NoRollbackRuleAttribute rule = new NoRollbackRuleAttribute(rbRule);
      rollBackRules.add(rule);
    }
    String[] nrbfc = ann.noRollbackForClassName();
    for (String rbRule : nrbfc) {
      NoRollbackRuleAttribute rule = new NoRollbackRuleAttribute(rbRule);
      rollBackRules.add(rule);
    }
    rbta.getRollbackRules().addAll(rollBackRules);
    return rbta;
  }

  @Override
  public boolean equals(Object other) {
    return (this == other || other instanceof ShardingAnnotationParser);
  }

  @Override
  public int hashCode() {
    return ShardingAnnotationParser.class.hashCode();
  }

}
