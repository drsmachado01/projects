package br.com.darlan.tasks.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @SuppressWarnings("TooGenericExceptionCaught")
    @Around("@annotation(br.com.darlan.tasks.aspect.annotations.LogExecution)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object[] params = joinPoint.getArgs();

        String methodName = joinPoint.getSignature().getName();

        log.info("Starting execution method {}", methodName);

        for(Object param : params) {
            log.info("Method param {}", param);
        }

        Object result = joinPoint.proceed();

        log.info("Finished execution method {}", methodName);

        long duration = System.currentTimeMillis() - start;

        log.info("The execution has completed around {} ms", duration);

        return result;
    }
}
