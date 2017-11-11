package cn.mldn.mldnredis.springdata;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.dibmp.vo.StorageApplyDetails;

@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRedisTemplateObjectDemo {	// Redis操作用RedisTemplate整体处理
	@Resource
	private RedisTemplate<String,StorageApplyDetails> redisTemplate ;
	@Test
	public void testSet() {
//		this.redisTemplate.opsForValue().set("ccc-5-1", new StorageApplyDetails(5L,1L,"给V的风格",5,5.0,5.0));
//		Boolean setIfAbsent = this.redisTemplate.opsForValue().setIfAbsent("ccc-5-3", new StorageApplyDetails(5L,1L,"给V的风格",5,5.0,5.0));
//		System.err.println(setIfAbsent);
	}
	@Test  
	public void testGet() { 
		System.err.println(this.redisTemplate.opsForValue().get("ccc-5-1"));
	}
}
