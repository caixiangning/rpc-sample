package org.rpc.provider;

import java.util.concurrent.CountDownLatch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 启动容器并将生产者实现的服务对象注入到容器中
 *
 * @author CaiXiangning 
 * @date May 31, 2017 
 * @email caixiangning@gmail.com
 */
public class ContainerStart {
	/**
	 * 将生产者实现的服务对象注入到容器中
	 * @param args
	 * @throws InterruptedException
	 */
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring-rpc-provider.xml");
		CountDownLatch countDownLatch = new CountDownLatch(1);
		countDownLatch.await();
	}
}
