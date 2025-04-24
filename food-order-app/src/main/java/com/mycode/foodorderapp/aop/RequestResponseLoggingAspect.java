package com.mycode.foodorderapp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class RequestResponseLoggingAspect {

    @Around("execution(* com.mycode.foodorderapp.controller.*.*(..))")
    public Object logRequestResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        // Log the request details
        log.info("Request received for method: " + joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("Request parameter: " + arg.toString());
        }

        // Proceed with the method's execution
        Object result = joinPoint.proceed();

        // Log the response details
        log.info("Response from method: " + joinPoint.getSignature().getName());
        log.info("Response data: " + result);

        return result;
    }
}
