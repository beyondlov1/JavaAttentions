package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

/**
 * @author beyondlov1
 * @date 2019/04/11
 */
@Aspect
@Configuration
public class TimeAspect {

    private Logger logger = LoggerFactory.getLogger(TimeAspect.class);

//    @Before("com.example.demo.aop.PointCutFactory.targetPointCut()")
//    public void before(JoinPoint joinPoint){
//        long start = System.currentTimeMillis();
//        logger.info(start+"");
//    }

//    @Around("com.example.demo.aop.PointCutFactory.targetPointCut()")
//    public Object around(ProceedingJoinPoint joinPoint){
//        long start = System.currentTimeMillis();
//
//        Target target = (Target)joinPoint.getTarget();
//        Object result = target.execute();
//
//        long end = System.currentTimeMillis();
//        logger.info((end-start)+"");
//        return result;
//    }

    @Around("com.example.demo.aop.PointCutFactory.timeTracePointCut()")
    public Object aroundAnnotation(ProceedingJoinPoint joinPoint){
        long start = System.currentTimeMillis();

        Target target = (Target)joinPoint.getTarget();
        Object result = target.execute();

        long end = System.currentTimeMillis();
        logger.info("运行时间: "+(end-start)+"ms");
        return result;
    }
}
