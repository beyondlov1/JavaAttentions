package com.example.demo.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author beyondlov1
 * @date 2019/04/11
 */
public class PointCutFactory {

    @Pointcut("execution(* com.example.demo.aop.Target.*(..))")
    public void targetPointCut(){}

    @Pointcut("@annotation(com.example.demo.aop.TimeTrace)")
    public void timeTracePointCut(){}
}
