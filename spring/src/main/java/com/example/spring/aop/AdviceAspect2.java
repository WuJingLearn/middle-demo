package com.example.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author majin.wj
 * @date 2023/6/28 2:14 PM
 * @desc
 */
@Aspect
//越小 优先级越大
/**
 *
 */
@Order(value = 1)
@Component
public class AdviceAspect2 {


    @Pointcut(value = "execution(public * com.example.spring.aop.OrderService.create(..))")
    public void pointCut() {
    }

//    @Pointcut(value = "execution(public * com.example.spring.aop.OrderService.payed(..))")
//    public void pointCut2(){}
//
//    @Pointcut(value = "execution(public * com.example.spring.aop.HelloService.*(..))")
//    public void pointCut3(){}

//    @Around(value = "pointCut()")
//    public Object around(JoinPoint joinPoint) {
//        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) joinPoint;
//        try {
//            System.out.println("创建环绕通知");
//            Object result = proceedingJoinPoint.proceed();
//            return result;
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Before(value = "pointCut()")
    public void beforeCreate() {
        System.out.println("aspect 2创建订单前置通知");
    }

    @After(value = "pointCut()")
    public void after() {
        System.out.println("aspect2 创建订单后置通知");
    }

 /*   @After(value = "pointCut()")
    public void after() {
        System.out.println("创建订单后置通知");
    }

    @AfterReturning(value = "pointCut()")
    public void afterReturn() {
        System.out.println("创建订单最红通知");
    }

    @AfterThrowing(value = "pointCut()")
    public void afterThrowing() {
        System.out.println("异常通知");
    }
*/

    //
//    @Before(value = "pointCut2()")
//    public void before() {
//        System.out.println("支付订单前置通知");
//    }
//
//    @Before(value = "pointCut3()")
//    public void before2() {
//        System.out.println("HelloService前置通知");
//    }
    public static void main(String[] args) {
        System.out.println("start");
    }

}
