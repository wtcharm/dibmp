package cn.mldn.mldnredis.springdata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.dibmp.vo.Goods;
import cn.mldn.dibmp.vo.StorageApplyDetails;
@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRedisStorageApplyDetailsDemo {	// Redis操作用RedisTemplate整体处理
	@Resource
	private RedisTemplate<String,StorageApplyDetails> redisTemplate ;
	@Test
	public void testSet() {
		StringBuffer key = new StringBuffer("ccc-") ;
		StorageApplyDetails vo = new StorageApplyDetails() ;
		vo.setGid(4L);
		vo.setName("楚琛琛");
		vo.setNum(100);
		vo.setPrice(99.99);
		vo.setWeight(99.9);
		vo.setSaid(10L);
		this.redisTemplate.opsForValue().set(key.append(vo.getSaid()).append("-").append(vo.getGid()).toString(), vo);
	}
	@Test  
	public void testGet() { 
		Long said = 10L ;
		StringBuffer key = new StringBuffer("ccc-") ;
		List<StorageApplyDetails> listsad = new ArrayList<StorageApplyDetails>() ;
		//System.err.println(this.redisTemplate.keys(key.append(said).append("-*").toString()));
		Set<String> keys = this.redisTemplate.keys(key.append(said).append("-*").toString()) ;
		Iterator<String> iterKey = keys.iterator() ;
		System.err.println(keys);
		while(iterKey.hasNext()) {
			String k = iterKey.next() ;
			StorageApplyDetails s = this.redisTemplate.opsForValue().get(k) ;
			listsad.add(s) ;
			System.err.println(k);
		}
		System.err.println(listsad);
	}
	@Test
	public void testGoods() {
		Goods goods1 = new Goods() ;
		goods1.setGid(10L);
		goods1.setName("楚大神1") ;
		
		Goods goods2 = new Goods() ;
		goods2.setGid(11L);
		goods2.setName("楚大神2") ;
		
//		this.redisTemplate.opsForValue().set("fyh-1-10", goods1);
//		this.redisTemplate.opsForValue().set("fyh-1-11", goods2);
	}
	
	@Test
	public void testGetGoods() {
		//List<Goods> all = new ArrayList<>() ;
		//all.add(this.redisTemplate.opsForValue().get("fyh-1-10")) ;
		//all.add(this.redisTemplate.opsForValue().get("fyh-1-11")) ;
		//System.err.println(all);
		
		System.err.println(this.redisTemplate.opsForValue().get("fyh-1-11"));
	}
	
	
	
	
	@Test
	public void testdelBatch() {
		Long said = 10L ;
		StringBuffer key = new StringBuffer("ccc-") ;
		//System.err.println(this.redisTemplate.keys(key.append(said).append("-*").toString()));
		Set<String> keys = this.redisTemplate.keys(key.append(said).append("-*").toString()) ;
		System.err.println(this.redisTemplate.delete(keys));
	}
}
