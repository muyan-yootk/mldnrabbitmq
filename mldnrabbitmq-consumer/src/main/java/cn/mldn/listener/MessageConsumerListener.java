package cn.mldn.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MessageConsumerListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("【消息消费】" + new String(message.getBody()));
	}

}
