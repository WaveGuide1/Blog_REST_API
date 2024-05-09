package io.waveguide.social_media.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class GeneralLogging {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* io.waveguide.social_media.post.*.*(..))")
    public void loggingPointcut(){}

    @Before("loggingPointcut()")
    public void beforeExecution(JoinPoint joinpoint){
        logger.info("Entering method: {} with arguments: {} ,Class Name:{}",
                joinpoint.getSignature().getName(), joinpoint.getArgs(),
                joinpoint.getSignature().getDeclaringTypeName());

    }

    @AfterReturning(value = "loggingPointcut()", returning = "result")
    public void afterExecutionJoinPoint(JoinPoint joinpoint, Object result){
        logger.info("Exiting method: {} ,with result: {} ,Class Name:{}",
                joinpoint.getSignature().getName(), result,
                joinpoint.getSignature().getDeclaringTypeName());

    }

    @AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
    public void afterException(JoinPoint joinpoint, Exception e){
        logger.info("After method {} :: ,was invoked from class {} :: ", e.getMessage(), e.getClass());
    }
}
