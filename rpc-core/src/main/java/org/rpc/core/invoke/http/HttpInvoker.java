package org.rpc.core.invoke.http;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.rpc.core.config.ConsumerConfig;
import org.rpc.core.invoke.Invoker;

/**
 *
 * 客户端与服务器交互工具类
 * (服务器接收客户端请求、服务器向客户端响应请求)
 *
 * @author CaiXiangning
 * @date May 31, 2017
 * @email caixiangning@gmail.com
 */
public class HttpInvoker implements Invoker {
	
	private static final HttpClient httpClient = HttpInvoker.getHttpClient();

	// 客户端与服务器之间交互工具类，负责发起请求和请求应答
	// 类变量随着类的加载而加载，即使此类还未new过对象，这个类变量也存在，而且仅一份;类变量的加载早于对象
	public static final Invoker invoker = new HttpInvoker();

	/**
	 * 客户端发送请求报文和服务器进行交互
	 * 
	 * @param requestDatagram 请求报文
	 * @param consumerConfig 消费者配置类
	 * @return
	 */
	public String request(String requestDatagram, ConsumerConfig consumerConfig) {
		// TODO Auto-generated method stub
		HttpPost httpPost = new HttpPost(consumerConfig.getUrl());
		// 使用长连接的模式
		httpPost.setHeader("Connection", "Keep-Alive");
		// 建立一个NameValuePair数组，用于存储欲传递的参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("data", requestDatagram));
		try {
			// 设置请求的实体(参数与值)
			httpPost.setEntity(new UrlEncodedFormEntity(params, Charset.forName("UTF-8")));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 服务器响应客户端请求发送响应报文给客户端
	 * 
	 * @param responseDatagram 响应报文
	 * @param outputStream 输出流
	 */
	public void response(String responseDatagram, OutputStream outputStream) {
		// TODO Auto-generated method stub
		try {
			outputStream.write(responseDatagram.getBytes("UTF-8"));
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过连接池生成httpClient
	 * @return HttpClient对象
	 */
	public static HttpClient getHttpClient() {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// 连接池最大生成连接数
		cm.setMaxTotal(200);
		// 设置每个路由上的默认连接个数20
		cm.setDefaultMaxPerRoute(20);
		HttpHost localhost = new HttpHost("localhost", 8080);
		// 单独为某个站点设置最大连接个数50
		cm.setMaxPerRoute(new HttpRoute(localhost), 50);
		// 创建httpClient
		return HttpClients.custom().setConnectionManager(cm).build();
	}
}
