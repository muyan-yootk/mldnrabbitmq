package cn.mldn.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.mldn.service.IMessageService;
@Service
@PropertySource("classpath:config/rabbitmq.properties")
public class MessageServiceImpl implements IMessageService {
	@Autowired
	private AmqpTemplate amqpTemplate ;
	@Value("${mq.rabbit.routing.key}") 
	private String routingKey ;
	@Override
	public void send(String msg) {
		this.amqpTemplate.convertAndSend(this.routingKey, msg);
	}
	@Override
	public void send(Object obj) {
		String sendMsg = JSON.toJSONString(obj) ;	// 利用FastJSON实现对象转换
		this.amqpTemplate.convertAndSend(this.routingKey, sendMsg);
	}

}
