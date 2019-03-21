package com.elgin.optmize.jvm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
@RestController
@Slf4j
public class OptmizeJvmDemoApplication {

	ReentrantLock lock1 = new ReentrantLock();
	ReentrantLock lock2 = new ReentrantLock();

	public static void main(String[] args) {
		SpringApplication.run(OptmizeJvmDemoApplication.class, args);
	}


	@ResponseBody
	@RequestMapping("/jvm-lock")
	public String jvmLock(){

		new Thread(new Runnable() {
			@Override
			public void run() {
				lock1.lock();
				log.info("lock1 lock success");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock2.lock();
				log.info("lock2 lock success");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock1.unlock();
				lock2.unlock();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				lock2.lock();
				log.info("lock2 lock success aaa");
				lock1.lock();
				log.info("lock1 lock success aaa");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock1.unlock();
				lock2.unlock();
			}
		}).start();

		return "ok";

	}



}
