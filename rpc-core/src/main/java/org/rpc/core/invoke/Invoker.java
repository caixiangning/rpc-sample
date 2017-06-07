package org.rpc.core.invoke;

import java.io.OutputStream;

import org.rpc.core.config.ConsumerConfig;

/**
 *
 * 客户端与服务器交互工具类接口
 * (服务器接收客户端请求、服务器向客户端响应请求)
 *
 * @author CaiXiangning
 * @date May 31, 2017
 * @email caixiangning@gmail.com
 */
public interface Invoker {
	
	/**
	 * 客户端发送请求报文和服务器进行交互
	 * @param requestDatagram 请求报文
	 * @param consumerConfig 消费者配置类
	 * @return
	 */
	String request(String requestDatagram, ConsumerConfig consumerConfig);

	/**
	 * 服务器响应客户端请求发送响应报文给客户端
	 * @param responseDatagram 响应报文
	 * @param outputStream 输出流
	 */
	void response(String responseDatagram, OutputStream outputStream);
}
