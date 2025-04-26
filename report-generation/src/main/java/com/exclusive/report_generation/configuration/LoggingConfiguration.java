package com.exclusive.report_generation.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class LoggingConfiguration {
    private static final String CORRELATION_ID_HEADER = "X-Correlation-Id";
    @Around("execution(* com.exclusive.report_generation..*(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        String correlationId = MDC.get(CORRELATION_ID_HEADER);
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        log.info("[{}] Entering method: {} with arguments: {}", correlationId, methodName, args);

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis();
            log.info("[{}] Exiting method: {} with result: {} (Execution time: {} ms)", correlationId, methodName, result, (end - start));
            return result;
        } catch (Throwable ex) {
            log.error("[{}] Exception in method: {}", correlationId, methodName, ex);
            throw ex;
        }
    }
}
