package io.waveguide.social_media.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class GeneralLogging {


    @Pointcut("execution(* io.waveguide.social_media.post.*.*(..))")
    public void loggingPointcut(){}

    @Before("loggingPointcut()")
    public void beforeExecution(JoinPoint joinpoint){
        log.info("Entering method: {} with arguments: {} ,Class Name:{}",
                joinpoint.getSignature().getName(), joinpoint.getArgs(),
                joinpoint.getSignature().getDeclaringTypeName());

    }

    @AfterReturning(value = "loggingPointcut()", returning = "result")
    public void afterExecutionJoinPoint(JoinPoint joinpoint, Object result){
        log.info("Exiting method: {} ,with result: {} ,Class Name:{}",
                joinpoint.getSignature().getName(), result,
                joinpoint.getSignature().getDeclaringTypeName());

    }

    @AfterThrowing(pointcut = "execution(* io.waveguide.social_media.exception.*.*(..))", throwing = "e")
    public void afterException(JoinPoint joinpoint, Exception e){
        log.debug("After method {} :: ,was invoked from class {} :: ", e.getMessage(), e.getClass());
    }
}
