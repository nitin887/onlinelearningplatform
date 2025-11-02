package com.onlinelearningplatform.learningPlatform.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceMonitoringAspect {

    private static final Logger log = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);
    private static final long WARN_THRESHOLD_MS = 200L; // Warn if a method takes longer than 200ms

    /**
     * Pointcut for all public methods in the service layer.
     */
    @Pointcut("within(com.onlinelearningplatform.learningPlatform.service..*)")
    public void serviceLayerPointcut() {
        // Method is empty as this is just a Pointcut.
    }

    /**
     * Advice that logs a warning if method execution time exceeds a defined threshold.
     */
    @Around("serviceLayerPointcut()")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long executionTime = System.currentTimeMillis() - startTime;
            if (executionTime > WARN_THRESHOLD_MS) {
                log.warn("PERFORMANCE WARNING: Method {}.{}() took {}ms to execute, which is over the {}ms threshold.",
                        joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(),
                        executionTime,
                        WARN_THRESHOLD_MS);
            }
        }
    }
}