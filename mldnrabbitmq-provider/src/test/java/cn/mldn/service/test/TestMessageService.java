package cn.mldn.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.service.IMessageService;
import cn.mldn.vo.Dept;

@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMessageService {
	@Autowired
	private IMessageService messageService ;
	@Test
	public void testSend() {
		Dept dept = new Dept() ;
		dept.setDeptno(77L);
		dept.setDname("高人的部门");
		this.messageService.send(dept);
	}
}
