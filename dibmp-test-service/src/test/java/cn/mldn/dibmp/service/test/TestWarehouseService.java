package cn.mldn.dibmp.service.test;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.dibmp.fyh.service.IWarehouseService;
import cn.mldn.dibmp.service.IMemberService;
import junit.framework.TestCase;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)

public class TestWarehouseService extends TestCase {
	@Resource
	private IWarehouseService warehouseService ;

	@Test
	public void testWarehouseGet() {
		Map<String, Object> storageApply = warehouseService.listStorageApply(1L, 5, "", "");
		System.err.println(storageApply);
	}

}
