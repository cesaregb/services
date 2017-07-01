package com.il.sod.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SuppressWarnings("Duplicates")
@Component
@Aspect
public class LoggingAspect {
  private final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

  @Pointcut("within(com.il.sod.rest.api.model..*) || within(com.il.sod.services..*)")
  public void loggingPointcut() {
  }

  @Pointcut("within(com.il.sod.mapper..*)")
  public void converterPointcut() {
  }

  @AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
  public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
    LOGGER.error("[Aspect] Exception in {}.{}() with cause = {}",
            joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName(),
            e.getCause());
  }

  //	@After("execution(* com.il.sod.rest.api.Health.*(..))")
  @After("execution(* com.il.sod.rest.api.*.*(..))")
  public void log(JoinPoint point) {
    LOGGER.info("[Aspect] Service Called" + point.getSignature().getName() + " called...");
  }

  @Around("loggingPointcut()")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    return getJoinPointObject(joinPoint, "Api");
  }

  @Around("converterPointcut()")
  public Object logAroundMapper(ProceedingJoinPoint joinPoint) throws Throwable {
    return getJoinPointObject(joinPoint, "Mapper");
  }

  private Object getJoinPointObject(ProceedingJoinPoint joinPoint, String title) throws Throwable {
    try {
      Object result = joinPoint.proceed();
      if (LOGGER.isDebugEnabled()) {
        LOGGER.info("[Aspect] ************* AROUND {} **********", title);
        LOGGER.info("[Aspect] Enter: {}.{}() with argument[s] = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
        LOGGER.info("[Aspect] Exit: {}.{}() with result = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                result);
      }
      return result;
    } catch (IllegalArgumentException e) {
      LOGGER.error("[Aspect] Illegal argument: {} in {}.{}()",
              Arrays.toString(joinPoint.getArgs()),
              joinPoint.getSignature().getDeclaringTypeName(),
              joinPoint.getSignature().getName());

      throw e;

    } finally {
      LOGGER.info("[Aspect] ************* END AROUND {} **********", title);
    }
  }
}