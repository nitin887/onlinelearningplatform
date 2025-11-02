package com.onlinelearningplatform.learningPlatform.aop;

import com.onlinelearningplatform.learningPlatform.exception.DataProcessingException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Pointcut for all public methods in the service layer.
     */
    @Pointcut("within(com.onlinelearningplatform.learningPlatform.service..*)")
    public void serviceLayerPointcut() {
        // Method is empty as this is just a Pointcut.
    }

    /**
     * Advice that translates DataAccessExceptions from the service layer
     * into a custom, application-specific exception.
     */
    @AfterThrowing(pointcut = "serviceLayerPointcut()", throwing = "ex")
    public void translateDataAccessException(JoinPoint joinPoint, DataAccessException ex) {
        log.error("DataAccessException caught in {}.{}() - Root cause: {}",
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), ex.getRootCause());

        throw new DataProcessingException("A database error occurred. Please check the logs for details.", ex);
    }
}