package cn.mldn.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;

import cn.mldn.service.IDeptService;
import cn.mldn.vo.Dept;

public class MessageConsumerListener implements MessageListener {
	@Reference
	private IDeptService deptService;
	@Override
	public void onMessage(Message message) {
		// 1、接收到消息之后将消息的JSON格式转为对象的格式
		Dept dept = JSON.parseObject(new String(message.getBody()), Dept.class) ;
		System.out.println("【消费端处理】处理结果：" + this.deptService.add(dept) + "（实际开发此行提示不需要出现）");
	}

}
