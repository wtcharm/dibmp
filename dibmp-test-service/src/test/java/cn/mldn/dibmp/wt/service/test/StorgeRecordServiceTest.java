package cn.mldn.dibmp.wt.service.test;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.dibmp.vo.StorageRecord;
import cn.mldn.dibmp.wt.service.IStorgeRecordService;
import junit.framework.TestCase;
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class StorgeRecordServiceTest {
	@Resource
	private IStorgeRecordService RecordService;
	@Test
	public void addTest() {
		StorageRecord vo = new StorageRecord();
		vo.setGid(1L);
		vo.setInmid("jiujiu");
		vo.setName("nishipa");
		vo.setNum(5);
		vo.setPrice(53.26);
		vo.setSaid(5L);
		vo.setStatus(6);
		vo.setWeight(56.3);
		boolean flage = RecordService.add(vo);
		System.out.println("增加数据 + " + flage);
		TestCase.assertTrue(flage);
	}
	@Test
	public void rdListTest() {
		Map<String, Object> map = RecordService.listRecord(null,null, 1L, 5);
		System.err.println("仓库 =="  +map.get("findSplit"));
		System.err.println("商品数量==" + map.get("CountNum"));
		System.err.println("商品价格 + " + map.get("SumPrice"));
	
	}
	
}
