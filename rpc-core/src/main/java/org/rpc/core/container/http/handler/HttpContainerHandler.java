package org.rpc.core.container.http.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.handler.AbstractHandler;
import org.rpc.core.bean.RequestObject;
import org.rpc.core.container.http.HttpContainer;
import org.rpc.core.container.memory.HttpContainerMemory;
import org.rpc.core.invoke.Invoker;
import org.rpc.core.invoke.http.HttpInvoker;
import org.rpc.core.serialize.Formater;
import org.rpc.core.serialize.Parser;
import org.rpc.core.serialize.json.JsonFormater;
import org.rpc.core.serialize.json.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 服务器端负责处理客户端请求并进行响应的类
 *
 * @author CaiXiangning
 * @date May 31, 2017
 * @email caixiangning@gmail.com
 */
public class HttpContainerHandler extends AbstractHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpContainer.class);

	// 将JSON字符串报文封装成对应对象的工具类
	private Parser parser = JsonParser.parser;

	// 将对象封装成JSON字符串报文的工具类
	private Formater formater = JsonFormater.formater;

	// 负责客户端和服务器之间请求和应答工具类
	private Invoker invoker = HttpInvoker.invoker;

	public void handle(String target, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			int dispatch) throws IOException, ServletException {
		// {"@type":"org.codethink.httprpc.bean.RequestObject","clazz":"org.codethink.httpinterface.service.CalculateService","methodName":"add","paramTypes":["int","int"],"paramValues":[1,2]}
		String reqStr = httpServletRequest.getParameter("data");
		logger.info("服务器接收到来自客户端的请求字符串是:{}", reqStr);
		try {
			// 将请求的报文封装成请求的对象
			RequestObject rpcRequest = parser.reqParse(reqStr);
			// 反射请求获取响应结果
			Object result = rpcRequest.invoke(HttpContainerMemory.getInstance().obtainService(rpcRequest.getClazz()));
			/*Method method = rpcRequest.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
			Object result = method.invoke(rpcRequest, rpcRequest.getParamValues());*/
			// 响应客户端的请求，请求应答，将响应结果通过输出流输出到客户端
			invoker.response(formater.rsbFormat(result), httpServletResponse.getOutputStream());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
