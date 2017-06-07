# rpc-sample
使用Java实现简单的RPC框架


一、模块划分
1、rpc-core RPC核心模块
2、rpc-interfaces RPC接口模块
3、rpc-provider RPC生产者模块
4、rpc-consumer RPC消费者模块

二、使用技术
1、fastjson作为请求/响应对象与请求/响应报文的转换
2、Jetty作为服务器端实现
3、HttpClient作为客户端请求实现
4、使用反射机制在运行时调用对象方法
5、使用动态代理创建对象
6、使用Spring作为IOC容器
