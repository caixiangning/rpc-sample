package org.rpc.core.serialize.json;

import org.rpc.core.bean.RequestObject;
import org.rpc.core.serialize.Parser;

import com.alibaba.fastjson.JSON;

/**
 *
 * 将Json字符串报文解析成对象
 *
 * @author CaiXiangning
 * @date May 31, 2017
 * @email caixiangning@gmail.com
 */
public class JsonParser implements Parser {
	
	// 类变量随着类的加载而加载，即使此类还未new过对象，这个类变量也存在，而且仅一份;类变量的加载早于对象
	public static final Parser parser = new JsonParser();

	/**
	 * 将请求报文解析为请求参数对象
	 * 
	 * @param datagram 请求报文
	 * @return
	 */
	public RequestObject reqParse(String param) {
		// TODO Auto-generated method stub
		// 由于该方法解析成请求参数对象，所以这里使用RequestObject.class
		return JSON.parseObject(param, RequestObject.class);
	}

	/**
	 * 将响应报文解析成响应结果对象
	 * 
	 * @param datagram 响应报文
	 * @param clazz 响应结果类型
	 * @return
	 */
	public <T> T rsbParse(String result, Class<T> clazz) {
		// TODO Auto-generated method stub
		return JSON.parseObject(result, clazz);
	}
}
