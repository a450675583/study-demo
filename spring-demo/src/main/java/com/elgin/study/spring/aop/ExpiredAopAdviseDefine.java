package com.elgin.study.spring.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class ExpiredAopAdviseDefine {


    /**
     * 定义一个 Pointcut, 使用 切点表达式函数 来描述对哪些 Join point 使用 advice.
     */
    @Pointcut("within(SomeService)")
    public void pointcut() {
    }

    /**
     * 定义 advice
      */
    @Around("pointcut()")
    public Object methodInvokeExpiredTime(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 开始
        Object retVal = pjp.proceed();
        stopWatch.stop();
        // 结束

        // 上报到公司监控平台
        reportToMonitorSystem(pjp.getSignature().toShortString(), stopWatch.getTotalTimeMillis());

        return retVal;
    }


    public void reportToMonitorSystem(String methodName, long expiredTime) {
        log.info("---method {} invoked, expired time: {} ms---", methodName, expiredTime);
    }
}
