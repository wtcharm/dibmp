package cn.mldn.mldnredis.springdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.dibmp.vo.Goods;
@SuppressWarnings("serial")
class Member implements Serializable {
	private String mid ;
	private String sex ;
	private Integer age ;
	public Member(String mid,String sex,Integer age) {
		this.mid = mid ;
		this.sex = sex ;
		this.age = age ;
	}
	@Override
	public String toString() {
		return "Member [mid=" + mid + ", sex=" + sex + ", age=" + age + "]";
	}
}
@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRedisTemplateObjectDemo {	// Redis操作用RedisTemplate整体处理
	@Resource
	private RedisTemplate<String,Member> redisTemplate ;
	@Test
	public void testSet() {
		this.redisTemplate.opsForValue().set("mldn-member", new Member("5","dsf",232));
	}
	@Test  
	public void testGet() { 
		System.err.println(this.redisTemplate.opsForValue().get("mldn-member"));
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
}
