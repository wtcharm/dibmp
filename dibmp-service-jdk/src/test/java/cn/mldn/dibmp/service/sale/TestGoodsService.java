package cn.mldn.dibmp.service.sale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestGoodsService {
	
	@Test
	public void testlist() throws Exception {
		//System.err.println(this.saleService.list(null, null, 1l, 5));
	} 
}
