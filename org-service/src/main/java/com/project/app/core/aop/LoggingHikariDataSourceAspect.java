package com.project.app.core.aop;

import com.zaxxer.hikari.HikariDataSource;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component
public class LoggingHikariDataSourceAspect {

//  private static final Logger logger = LoggerFactory.getLogger(LoggingHikariDataSourceAspect.class);
//
//  @Autowired
//  private HikariDataSource hikariDataSource;
//
//  @Before("execution(* com.project.app..repository..*(..))")
//  public void logBeforeConnection(JoinPoint jp) throws Throwable {
//    logDataSourceInfos("Before", jp);
//  }
//
//  @After("execution(* com.project.app..repository..*(..))")
//  public void logAfterConnection(JoinPoint jp) throws Throwable {
//    logDataSourceInfos("After", jp);
//  }
//
//  public void logDataSourceInfos(final String time, final JoinPoint jp) {
//    final String method = String.format("%s:%s", jp.getTarget().getClass().getName(), jp.getSignature().getName());
//    logger.info(String.format("%s %s: number of total connections in the application (total): %d.", time, method, hikariDataSource.getHikariPoolMXBean().getTotalConnections()));
//    logger.info(String.format("%s %s: number of connections in use by the application (active): %d.", time, method, hikariDataSource.getHikariPoolMXBean().getActiveConnections()));
//    logger.info(String.format("%s %s: the number of established but idle connections: %d.", time, method, hikariDataSource.getHikariPoolMXBean().getIdleConnections()));
//    logger.info(String.format("%s %s: number of threads waiting for a connection: %d.", time, method, hikariDataSource.getHikariPoolMXBean().getThreadsAwaitingConnection()));
//  }
}
