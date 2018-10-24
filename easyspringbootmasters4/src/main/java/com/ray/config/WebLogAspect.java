package com.ray.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * WebLogAspect
 *
 * @author ray
 * 
 *
 * 实现Web层的日志切面
 *
 */
@Aspect
@Component
public class WebLogAspect {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();


    @Pointcut("execution(* com.ray.service.impl.*.*(..))")

    public void webLog(){}


    @Before("webLog()")

    public void doBefore(JoinPoint joinPoint){

        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        logger.info("WebLogAspect.doBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        // 记录下请求内�?
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        //获取�?有参数方法一�?
        Enumeration<String> enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            System.out.println(paraName+": "+request.getParameter(paraName));
        }
    }

    @AfterReturning("webLog()")
    public void  doAfterReturning(JoinPoint joinPoint){

        // 处理完请求，返回内容
        logger.info("WebLogAspect.doAfterReturning()");
        logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));

    }

}
