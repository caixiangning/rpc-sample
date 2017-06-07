package org.rpc.core.container.memory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 服务注册/存储的内存容器
 *
 * @author CaiXiangning
 * @date Jun 2, 2017
 * @email caixiangning@gmail.com
 */
public class HttpContainerMemory {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpContainerMemory.class);
	
	// 饿汉式单例模式实现存储服务的内存容器
	private static HttpContainerMemory httpContainerMemory = new HttpContainerMemory();

	// providerHashMaps用于存储生产者服务对象，Class<?>表示类Class，Object表示相应的对象
	private Map<Class<?>, Object> serviceHashMaps = new ConcurrentHashMap<Class<?>, Object>();
	
	// 构造函数私有可以保证在程序运行期间只有一个内存容器实例
	private HttpContainerMemory() {
		
	}
	
	/**
	 * 注册服务
	 * @param clazz 服务类型
	 * @param object 服务对象
	 */
	public void registerService(Class<?> clazz, Object object) {
		serviceHashMaps.put(clazz, object);
		logger.info("注册并发布服务：{}", clazz.getSimpleName());
	}
	
	/**
	 * 获取服务
	 * @param clazz 服务类型
	 */
	public Object obtainService(Class<?> clazz) {
		Object bean = serviceHashMaps.get(clazz);
		if (bean != null) {
			return bean;
		}
		logger.error("{} 服务不存在", clazz.getSimpleName());
		return null;
	}
	
	/**
	 * 获取服务注册/存储的内存容器实例
	 * @return
	 */
	public static HttpContainerMemory getInstance() {
		return httpContainerMemory;
	}
}
