package org.rpc.core.serialize;

/**
 *
 * 将对象封装成字符串报文接口
 *
 * @author CaiXiangning
 * @date May 31, 2017
 * @email caixiangning@gmail.com
 */
public interface Formater {
	/**
	 * 将请求对象封装成字符串报文
	 * @param clazz 请求的接口
	 * @param methodName 请求的方法名称
	 * @param paramTypes 请求的参数类型
	 * @param paramValues 请求的参数值
	 * @return
	 */
	String reqFormat(Class<?> clazz, String methodName, Class<?>[] paramTypes, Object[] paramValues);
	
	/**
	 * 将响应结果对象封装成字符串报文
	 * @param param 响应结果对象
	 * @return
	 */
	String rsbFormat(Object result);
}
