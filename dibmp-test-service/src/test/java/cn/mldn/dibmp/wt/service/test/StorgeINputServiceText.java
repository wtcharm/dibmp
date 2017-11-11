package cn.mldn.dibmp.wt.service.test;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.dibmp.wt.service.IStorgeInputService;

@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class StorgeINputServiceText {
	@Resource
	private IStorgeInputService inputService;
	@Test
	public void listInputBckeText() {
		 Map<String, Object> map = inputService.listInputBcke(2L,"1");
		 if(map.isEmpty()) {
			 System.err.println("查询的仓库数据 --" + map.get("applyAll"));
			 System.err.println("查询的商品数据 --" + map.get("details"));
		 }else {
			 System.err.println("查询的仓库数据 aaa--" + map.get("applyAll"));
			 System.err.println("查询的商品数据 aaa--" + map.get("details"));
		 }
	}
}
