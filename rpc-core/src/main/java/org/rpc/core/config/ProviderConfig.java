package org.rpc.core.config;

/**
 *
 * 生产者端口号配置类
 *
 * @author CaiXiangning
 * @date May 31, 2017
 * @email caixiangning@gmail.com
 */
public class ProviderConfig {
	
	// 端口号
	private Integer port;

	public ProviderConfig() {
	}

	public ProviderConfig(Integer port) {
		super();
		this.port = port;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
}
