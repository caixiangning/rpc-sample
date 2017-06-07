package org.rpc.core.config;


/**
 *
 * 消费者调用地址配置类
 *
 * @author CaiXiangning
 * @date May 31, 2017
 * @email caixiangning@gmail.com
 */
public class ConsumerConfig {
	
	private String ip;
	
	private String port;
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	// 获取完整url：(ip:port)形式
	public String getUrl(){
		StringBuffer sb = new StringBuffer();
		sb.append(ip);
		sb.append(":");
		sb.append(port);
		return sb.toString();
	}
}
