package org.rpc.core.bean;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *
 * 请求的接口、方法、参数类型、参数数值封装的类，便于序列化
 *
 * @author CaiXiangning
 * @date May 31, 2017
 * @email caixiangning@gmail.com
 */
public class RequestObject {
	// 请求的接口
	private Class<?> clazz;

	// 请求的方法名称
	private String methodName;

	// 方法参数类型数组
	private Class<?>[] paramTypes;

	// 方法参数数值数组
	private Object[] paramValues;

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class<?>[] getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(Class<?>[] paramTypes) {
		this.paramTypes = paramTypes;
	}

	public Object[] getParamValues() {
		return paramValues;
	}

	public void setParamValues(Object[] paramValues) {
		this.paramValues = paramValues;
	}

	/**
	 * 使用Java反射机制在运行时调用指定对象的指定方法
	 * 调用Class对象的getMethod方法获取指定方法的Method对象，两个参数分别是方法名和参数的Class类型：java.lang.Class.getMethod(String name, Class<?>... parameterTypes))
	 * 调用Method对象的invoke方法完成方法的调用，两个参数分别是服务对象和参数数值：java.lang.reflect.Method.invoke(Object obj, Object... args))
	 * @param obj 运行时调用的指定对象(实际调用对象而非RequestObject对象)
	 * @return
	 * @throws Exception
	 */
	public Object invoke(Object obj) throws Exception {
		Method method = this.clazz.getMethod(this.methodName, this.paramTypes);
		return method.invoke(obj, this.paramValues);
	}
	
	@Override
	public String toString() {
		return "RequestObject [clazz=" + clazz + ", methodName=" + methodName + ", paramTypes="
				+ Arrays.toString(paramTypes) + ", paramValues=" + Arrays.toString(paramValues) + "]";
	}
}
