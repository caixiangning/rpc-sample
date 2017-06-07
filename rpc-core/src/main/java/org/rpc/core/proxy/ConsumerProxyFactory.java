package org.rpc.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.rpc.core.config.ConsumerConfig;
import org.rpc.core.invoke.Invoker;
import org.rpc.core.invoke.http.HttpInvoker;
import org.rpc.core.serialize.Formater;
import org.rpc.core.serialize.Parser;
import org.rpc.core.serialize.json.JsonFormater;
import org.rpc.core.serialize.json.JsonParser;

/**
 *
 * 消费者代理工厂类
 *
 * @author CaiXiangning
 * @date May 31, 2017
 * @email caixiangning@gmail.com
 */
public class ConsumerProxyFactory implements InvocationHandler {
	// 将响应报文封装成相应对象的工具类
	private Parser parser = JsonParser.parser;

	// 将对象封装成json报文的工具类
	private Formater formater = JsonFormater.formater;

	// 客户端与服务器交互工具类(服务器接收客户端请求、服务器向客户端响应请求)
	private Invoker invoker = HttpInvoker.invoker;

	// 消费者配置类
	private ConsumerConfig consumerConfig;

	/**
	 * 创建bean的工厂方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public Object create(String clazz) throws Exception {
		Class<?> interfaceClass = Class.forName(clazz);
		// Object java.lang.reflect.Proxy.newProxyInstance(ClassLoader loader,
		// Class<?>[] interfaces, InvocationHandler h)
		// 参数loader指定动态代理类的类加载器，参数interfaces指定动态代理类需要实现的所有接口，参数handler指定与动态代理类关联的InvocationHandler对象
		Object obj = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass }, this);
		return obj;
	}

	/**
	 * 当程序调用动态代理类的方法时，该方法会调用与它关联的InvocationHandler对象的invoke方法
	 * 
	 * @param proxy 指代我们所代理的那个真实对象
	 * @param method 指代的是我们所要调用真实对象的某个方法的Method对象
	 * @param args 指代的是调用真实对象某个方法时接受的参数
	 * @return
	 * @throws Throwable
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> interfaceClass = proxy.getClass().getInterfaces()[0];
		// 将请求的接口、方法、参数封装成json报文
		// {"@type":"org.codethink.httprpc.bean.RequestObject","clazz":"org.codethink.httpinterface.service.CalculateService","methodName":"add","paramTypes":["int","int"],"paramValues":[1,2]}
		String req = formater.reqFormat(interfaceClass, method.getName(), method.getParameterTypes(), args);
		
		// 发送报文和指定配置的服务器进行交互，调用请求
		String resb = invoker.request(req, consumerConfig);

		// 将响应的报文封装成响应的对象
		return parser.rsbParse(resb, method.getReturnType());
	}

	public ConsumerConfig getConsumerConfig() {
		return consumerConfig;
	}

	public void setConsumerConfig(ConsumerConfig consumerConfig) {
		this.consumerConfig = consumerConfig;
	}
}
