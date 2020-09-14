package com.example.book.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NullSpecAspect {

    @Pointcut("execution(* com.example.book.specification.BookSpecification.*(..))")
    public void allBookSpecifications() {};

    @Around("allBookSpecifications()")
    public Object handleNullSpecs(ProceedingJoinPoint pjp) throws Throwable {
        if(pjp.getArgs()[0] == null)  {
            return null;
        }
        return pjp.proceed();
    }
}
