package com.elgin.study.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 被监控的service
 * @author ADMIN
 */
@Service
@Slf4j
public class SomeService {

    private Random random = new Random(System.currentTimeMillis());

    public void someMethod1() {
        log.info("---SomeService: someMethod1 invoked---");
        try {
            // 模拟耗时任务
            Thread.sleep(random.nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void someMethod2() {
        log.info("---SomeService: someMethod2 invoked---");
        try {
            // 模拟耗时任务
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
