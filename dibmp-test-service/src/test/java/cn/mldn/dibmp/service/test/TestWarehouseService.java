package cn.mldn.dibmp.service.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.dibmp.fyh.service.IWarehouseService;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import junit.framework.TestCase;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)

public class TestWarehouseService extends TestCase {
	@Resource
	private IWarehouseService warehouseService ;

	@Test
	public void testWarehouseGet() {
		Map<String, Object> storageApply = warehouseService.listStorageApply(1L, 5, "", "","mldn-sale");
		Object object = storageApply.get("allWarehouseName");
		System.err.println(storageApply);
	}
	
	@Test
	public void testWareGet() {
		Map<String, Object> storageApply = warehouseService.listStorageApply(1L, 5, "", "","mldn-sale");
		System.err.println(storageApply.get("allWitem"));
	}
	
	@Test
	public void testaddBatch() {
		StorageApplyDetails vo1 = new StorageApplyDetails() ;
		vo1.setGid(3L);
		vo1.setName("楚琛琛");
		vo1.setNum(100);
		vo1.setPrice(99.99);
		vo1.setWeight(99.9);
		vo1.setSaid(10L);
		
		StorageApplyDetails vo2 = new StorageApplyDetails() ;
		vo2.setGid(3L);
		vo2.setName("楚琛琛");
		vo2.setNum(100);
		vo2.setPrice(99.99);
		vo2.setWeight(99.9);
		vo2.setSaid(10L);
		
		
		List<StorageApplyDetails> listsad = new ArrayList<>() ;
		listsad.add(vo1) ;
		listsad.add(vo2) ;
		 ;
		 System.err.println(warehouseService.testAddBatch(listsad));
		
	}
	@Test
	public void testInSubmit() {
		System.err.println(warehouseService.inSubmit(10L));
	}
	
	@Test
	public void testdeleteSA() {
		System.err.println(warehouseService.removeStorageApply(2L));
	}

}
