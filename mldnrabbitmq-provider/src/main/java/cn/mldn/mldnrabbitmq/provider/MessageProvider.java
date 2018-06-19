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
	// 定义消息的发送队列名称，如果此队列不存在则可以自动创建
	private static final String QUEUE_NAME = "mldn.msg.queue" ;
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory() ; // 创建连接工厂
		factory.setHost(HOST);	// 连接的主机名称
		factory.setPort(PORT);	// 连接端口号
		factory.setUsername(USERNAME);	// 连接用户名
		factory.setPassword(PASSWORD); 	// 连接密码
		Connection connection = factory.newConnection() ; // 创建一个新的连接
		Channel channel = connection.createChannel() ; // 创建连接通道
		// 根据通道来创建要使用的队列信息，在队列创建的时候需要设置有如下几个参数内容：
		// 【第1个参数】String queue：队列的名称
		// 【第2个参数】boolean durable：是否为持久存储（未消费不删除）
		// 【第3个参数】boolean exclusive：是否为专用的访问队列信息
		// 【第4个参数】boolean autoDelete：消费之后是否允许自动删除
		// 【第5个参数】Map<String, Object> arguments：设置的参数
		channel.queueDeclare(QUEUE_NAME, true, false, true, null) ;
		long start = System.currentTimeMillis() ;
		for (int x = 0 ; x < 10 ; x ++) {
			String msg = "mldnjava - " + x ;
			channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
		} 
		long end = System.currentTimeMillis() ;
		System.out.println("消息处理的时间：" + (end - start));
		channel.close();
		connection.close(); 
	}
}
