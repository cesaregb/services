package com.il.sod.aop.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	private final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("within(com.il.sod.rest.api.impl..*) || within(com.il.sod.services..*)")
	public void loggingPointcut() {
	}

	@AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		LOGGER.error("[Aspect] Exception in {}.{}() with cause = {}", 
				joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), 
				e.getCause());
	}

//	@After("execution(* com.il.sod.rest.api.Health.*(..))")
	@After("execution(* com.il.sod.rest.api.*(..))")
	public void log(JoinPoint point) {
		LOGGER.info("[Aspect] Service Called" + point.getSignature().getName() + " called...");
	}
	
	@Around("loggingPointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		LOGGER.info("[Aspect] ************* AROUND CALL IN THE API **********");
		LOGGER.info("[Aspect] Enter: {}.{}() with argument[s] = {}", 
				joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), 
				Arrays.toString(joinPoint.getArgs()));
		if (LOGGER.isDebugEnabled()) {
			// TODO move content in here!!  
		}
		try {
			Object result = joinPoint.proceed();
			if (LOGGER.isDebugEnabled()) {
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
		}finally{
			LOGGER.info("[Aspect] ************* END AROUND CALL IN THE API **********");
		}
		
	}

}