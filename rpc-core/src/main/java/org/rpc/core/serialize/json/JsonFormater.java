package org.rpc.core.serialize.json;

import org.rpc.core.bean.RequestObject;
import org.rpc.core.serialize.Formater;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 *
 * 将对象封装成Json字符串报文
 *
 * @author CaiXiangning
 * @date May 31, 2017
 * @email caixiangning@gmail.com
 */
public class JsonFormater implements Formater {
	
	// 类变量随着类的加载而加载，即使此类还未new过对象，这个类变量也存在，而且仅一份;类变量的加载早于对象
	public static final Formater formater = new JsonFormater();

	/**
	 * 将请求对象封装成字符串报文
	 * 
	 * @param clazz 请求的接口
	 * @param methodName 请求的方法名称
	 * @param paramTypes 请求的参数类型
	 * @param paramValues 请求的参数值
	 * @return
	 */
	public String reqFormat(Class<?> clazz, String methodName, Class<?>[] paramTypes, Object[] paramValues) {
		// TODO Auto-generated method stub
		RequestObject requestObject = new RequestObject();
		requestObject.setClazz(clazz);
		requestObject.setMethodName(methodName);
		requestObject.setParamTypes(paramTypes);
		requestObject.setParamValues(paramValues);
		return JSON.toJSONString(requestObject, SerializerFeature.WriteClassName);
	}

	/**
	 * 将响应结果对象封装成字符串报文
	 * 
	 * @param param 响应结果对象
	 * @return
	 */
	public String rsbFormat(Object result) {
		// TODO Auto-generated method stub
		return JSON.toJSONString(result, SerializerFeature.WriteClassName);
	}
}
