package com.shareit.ocr.aspect;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class RequestAspect {
	
	private final static Logger logger = LoggerFactory.getLogger(RequestAspect.class);
	
	@Pointcut("execution(public * com.shareit.ocr.controller.SimulatorController.*(..))")
    public void requestLog() {
    }

    @Before("requestLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("接收到请求:url={},method={},ip={},args={}",request.getRequestURL(),
        		request.getMethod(),request.getRemoteAddr(),joinPoint.getArgs());
       
    }

   
    
    @AfterReturning(returning = "object", pointcut = "requestLog()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object);
    }
}
