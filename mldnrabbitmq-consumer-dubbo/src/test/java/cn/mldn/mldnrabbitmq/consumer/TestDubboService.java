package cn.mldn.mldnrabbitmq.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.mldn.service.IDeptService;
import cn.mldn.vo.Dept;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDubboService {
	@Reference
	private IDeptService deptService;

	@Test
	public void testAdd() {
		Dept dept = new Dept();
		dept.setDeptno(98L);
		dept.setDname("测试");
		System.out.println(this.deptService.add(dept));
	}

}
