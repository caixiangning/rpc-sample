package org.rpc.core.serialize;

import org.rpc.core.bean.RequestObject;

/**
 *
 * 将字符串报文解析成对象接口
 *
 * @author CaiXiangning 
 * @date May 31, 2017 
 * @email caixiangning@gmail.com
 */
public interface Parser {
	/**
	 * 将请求报文解析为请求参数对象
	 * @param datagram 请求报文
	 * @param clazz 
	 * @return
	 */
	RequestObject reqParse(String datagram);
	
	/**
	 * 将响应报文解析成响应结果对象
	 * @param datagram 响应报文
	 * @param clazz 响应结果类型
	 * @return
	 */
	public <T> T rsbParse(String datagram, Class<T> clazz);
}
