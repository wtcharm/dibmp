package cn.mldn.dibmp.service.warehouse;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.dibmp.fyh.service.IWarehouseService;
import cn.mldn.dibmp.vo.Warehouse;

@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class WarehouseServiceTest {
	@Resource
	private IWarehouseService wareService ;
	@Test
	public void testAdd() {
		System.out.println(wareService);
		Warehouse vo = new Warehouse();
		vo.setName("仓库名字");
		vo.setPid(1L);
		vo.setCid(2L);
		vo.setAddress("北京 通州");
		System.err.println(this.wareService.add(vo));
	}
}
