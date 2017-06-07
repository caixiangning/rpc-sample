package org.rpc.consumer;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rpc.interfaces.service.CalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * 
 * 
 * @author CaiXiangNing
 * @date May 31, 2017
 * @email caixiangning@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring-rpc-consumer.xml" })
public class ConsumerTest {
private static final Logger logger = LoggerFactory.getLogger(ConsumerTest.class);
	
	@Autowired
	private CalculateService calculateService;
	
	@Test
	public void testOne1() throws InterruptedException {
		int result = calculateService.add(1, 2);
		logger.info("客户端接收到来自服务器端的响应结果是:{}", String.valueOf(result));
		CountDownLatch countDownLatch = new CountDownLatch(1);
		countDownLatch.await();
	}
	
	@Test
	public void testOne2() throws InterruptedException {
		int result = calculateService.sub(10, 2);
		logger.info("客户端接收到来自服务器端的响应结果是:{}", String.valueOf(result));
		CountDownLatch countDownLatch = new CountDownLatch(1);
		countDownLatch.await();
	}
}
