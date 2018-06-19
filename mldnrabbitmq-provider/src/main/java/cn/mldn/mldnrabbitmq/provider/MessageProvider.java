package cn.mldn.mldnrabbitmq.provider;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class MessageProvider {
	private static final String HOST = "rabbitmq-single" ;
	private static final int PORT = 5672 ;
	private static final String USERNAME = "mldn" ;
	private static final String PASSWORD = "java" ;
	private static final String VHOST = "/mldnjava" ;
	private static final String EXCHANGE_NAME = "mldn.exchange.fanout" ;
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory() ; // 创建连接工厂
		factory.setVirtualHost(VHOST);
		factory.setHost(HOST);	// 连接的主机名称
		factory.setPort(PORT);	// 连接端口号
		factory.setUsername(USERNAME);	// 连接用户名
		factory.setPassword(PASSWORD); 	// 连接密码
		Connection connection = factory.newConnection() ; // 创建一个新的连接
		Channel channel = connection.createChannel() ; // 创建连接通道
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout") ; // 创建fanout交换空间
		long start = System.currentTimeMillis() ;
		for (int x = 0 ; x < 10 ; x ++) {
			String msg = "mldnjava - " + x ;
			channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
		}  
		long end = System.currentTimeMillis() ;
		System.out.println("消息处理的时间：" + (end - start));
		channel.close();
		connection.close(); 
	}
}
