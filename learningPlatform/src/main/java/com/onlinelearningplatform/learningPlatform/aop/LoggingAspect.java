package com.onlinelearningplatform.learningPlatform.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Defines a pointcut that matches all methods in the service and controller packages.
     */
    @Pointcut("within(com.onlinelearningplatform.learningPlatform.service..*) || " +
            "within(com.onlinelearningplatform.learningPlatform.controller..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut.
        // The implementations are in the advices.
    }

    /**
     * Advice that logs method entry, exit, execution time, and exceptions.
     * It wraps around methods matched by the applicationPackagePointcut.
     */
    @Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", className, methodName, Arrays.toString(joinPoint.getArgs()));
        }

        try {
            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - startTime;
            log.debug("Exit: {}.{}() with result = {}. Execution time: {}ms", className, methodName, result, executionTime);
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()), className, methodName);
            throw e;
        }
    }
    
}