package org.rpc.core.proxy;

import java.util.Map;

import org.rpc.core.config.ProviderConfig;
import org.rpc.core.container.Container;
import org.rpc.core.container.http.HttpContainer;
import org.rpc.core.container.memory.HttpContainerMemory;

/**
 *
 * 生产者代理工厂类
 * (负责启动http容器并注册服务，服务是通过HashMap来注册的)
 *
 * @author CaiXiangning
 * @date May 31, 2017
 * @email caixiangning@gmail.com
 */
public class ProviderProxyFactory {

	/**
	 * 使用默认配置启动容器并注册服务
	 * 
	 * @param providerHashMaps 服务
	 */
	public ProviderProxyFactory(Map<Class<?>, Object> providerHashMaps) {
		// 判断容器是否启动，如果没启动则启动,监听指定端口
		if (Container.container == null) {
			new HttpContainer().start();
		}
		// 遍历Map集合然后注册服务
		for (Map.Entry<Class<?>, Object> entry : providerHashMaps.entrySet()) {
			HttpContainerMemory.getInstance().registerService(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 使用指定配置启动容器并注册服务
	 * 
	 * @param providerHashMaps 服务
	 * @param providerConfig 配置
	 */
	public ProviderProxyFactory(Map<Class<?>, Object> providerHashMaps, ProviderConfig providerConfig) {
		// 创建生产者代理工厂的时候判断容器是否启动如果没有启动则根据配置启动Jetty容器
		if (Container.container == null) {
			new HttpContainer(providerConfig).start();
		}
		// 遍历配置文件中Map集合然后注册在内存中的Map中(这里Class类型不定所以使用Class<?>而不是Class<T>)
		for (Map.Entry<Class<?>, Object> entry : providerHashMaps.entrySet()) {
			HttpContainerMemory.getInstance().registerService(entry.getKey(), entry.getValue());
		}
	}
}
