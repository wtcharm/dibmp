package cn.mldn.dibmp.service.test;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.dibmp.ccc.service.ICityService;
import cn.mldn.dibmp.ccc.service.IGoodsService;
import cn.mldn.dibmp.ccc.service.IWitemService;
import cn.mldn.dibmp.fyh.service.IWarehouseService;
import cn.mldn.dibmp.service.IMemberService;
import cn.mldn.dibmp.vo.City;
import cn.mldn.dibmp.vo.Witem;
import junit.framework.TestCase;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)

public class TestWitemService extends TestCase {
	@Resource
	private IWitemService witemSerivce ;
	@Resource
	private ICityService city ;
	@Resource
	private IGoodsService goos ;

	@Test
	public void testWarehouseGet() {
		List<Witem> list = witemSerivce.list() ;
		System.err.println(list);
	}
	@Test
	public void testGoodsGet() {
		Map<String, Object> list = goos.list("", "", 1L, 5) ;
		System.err.println(list);
	}
	@Test
	public void testCityGet() {
		List<City> listByProvince = city.listByProvince(4L) ;
		System.err.println(listByProvince);
	}

}
