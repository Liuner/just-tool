package com.liugs.tool.spring.aop;


import com.liugs.tool.constants.Console;
import org.aspectj.lang.annotation.*;

/**
 * @ClassName Logging
 * @Description
 * @Author liugs
 * @Date 2020/8/10 16:41:57
 */
@Aspect
public class Logging {

    /** Following is the definition for a pointcut to select
     *  all the methods available. So advice will be called
     *  for all the methods.
     */
    @Pointcut("execution(* com.liugs.tool.spring.aop.*.*(..))")
    public void selectAll() {

    }
    @Before("selectAll()")
    public void beforeAdvice() {
        Console.show("before advice");
    }

    @After("selectAll()")
    public void afterAdvice() {
        Console.show("after advice");
    }

    @AfterReturning(pointcut = "selectAll()", returning = "obj")
    public void afterReturningAdvice(Object obj) {
        Console.show("returning:" + obj.toString());
    }

    @AfterThrowing(pointcut = "selectAll()", throwing = "ex")
    public void afterThrowingAdvice(IllegalArgumentException ex) {
        Console.show("There has been an exception: " + ex.toString());
    }
}
