package cn.mldn.dibmp.service.market;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.dibmp.ccc.service.IMarketService;
@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)

public class TestMarketAddPre {
	@Resource
	private IMarketService marketService;
	@Test
	public void testAddPre() {
		System.err.println(marketService.addPre(1L));
	}
}
