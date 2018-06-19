package cn.mldn.mldnrabbitmq.consumer;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class MessageConsumerC {
	private static final String HOST = "rabbitmq-single" ;
	private static final int PORT = 5672 ;
	private static final String USERNAME = "mldn" ;
	private static final String PASSWORD = "java" ;
	// 定义消息的发送队列名称，如果此队列不存在则可以自动创建
	private static final String QUEUE_NAME = "mldn.msg.queue.c" ;
	private static final String VHOST = "/mldnjava" ;
	private static final String ROUTING_KEY = "mldn-key" ;
	private static final String EXCHANGE_NAME = "mldn.exchange.topic" ;
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory() ; // 创建连接工厂
		factory.setVirtualHost(VHOST);
		factory.setHost(HOST);	// 连接的主机名称
		factory.setPort(PORT);	// 连接端口号
		factory.setUsername(USERNAME);	// 连接用户名
		factory.setPassword(PASSWORD); 	// 连接密码
		Connection connection = factory.newConnection() ; // 创建一个新的连接 
		Channel channel = connection.createChannel() ; // 创建连接通道
		channel.queueDeclare(QUEUE_NAME, true, false, true, null) ;	// 定义队列
		channel.exchangeDeclare(EXCHANGE_NAME, "topic") ; // 创建fanout交换空间
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY) ; 	// 队列需要与交换空间绑定
		channel.basicConsume(QUEUE_NAME, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {	// 进行消息的接收
				String msg = new String(body) ;
				System.err.println("【消费者 - C】" + msg);
			}
		}) ;
	}
}
