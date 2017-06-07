package org.rpc.core.container.http;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.rpc.core.config.ProviderConfig;
import org.rpc.core.container.Container;
import org.rpc.core.container.http.handler.HttpContainerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 使用Jetty实现的Http容器的相关实现类
 *
 * @author CaiXiangning
 * @date May 31, 2017
 * @email caixiangning@gmail.com
 */
public class HttpContainer extends Container{
	
	private static final Logger logger = LoggerFactory.getLogger(HttpContainer.class);

	// 用于处理客户端请求并做出响应
	private AbstractHandler httpContainerHandler = new HttpContainerHandler();

	// 生产者配置类的引用
	private ProviderConfig providerConfig;

	public HttpContainer(ProviderConfig providerConfig) {
		this.providerConfig = providerConfig;
		Container.container = this;
	}

	// 使用默认端口8080
	public HttpContainer() {
		this(new ProviderConfig(8080));
	}

	// 启动容器
	@Override
	public void start() {
		// TODO Auto-generated method stub
		Server server = new Server();
		SelectChannelConnector selectChannelConnector = new SelectChannelConnector();
		selectChannelConnector.setPort(providerConfig.getPort());
		server.setConnectors(new Connector[] { selectChannelConnector });
		server.setHandler(httpContainerHandler);
		try {
			server.start();
			logger.info("Jetty容器正常启动");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("Jetty容器启动异常", e);
		}
	}
}
