package cn.mldn.dibmp.service.sale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.mldn.dibmp.vo.Goods;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis工具类
 * @author yaojia
 *
 */
public class RedisUtil {

	private static final String REDIS_HOST = "192.168.142.134" ;
	private static final int REDIS_PORT = 6379 ;
	private static final int TIMEOUT = 2000 ; 
	private static final int MAX_TOTAL = 200 ;	
	private static final int MAX_IDLE = 20 ;	
	private static final int MAX_WAIT_MILLIS = 1000 ;
	private static final boolean TEST_ON_BORROW = true ;
	private static final String REDIS_AUTH = "hello" ;	
	private static JedisPool pool = null ;	
	
	static {
		JedisPoolConfig config = new JedisPoolConfig() ;
		config.setMaxTotal(MAX_TOTAL);
		config.setMaxIdle(MAX_IDLE);
		config.setMaxWaitMillis(MAX_WAIT_MILLIS);
		config.setTestOnBorrow(TEST_ON_BORROW); 
		pool = new JedisPool(config,REDIS_HOST,REDIS_PORT,TIMEOUT,REDIS_AUTH) ;
	}
	
	/**
     * 获取Jedis实例
     * @return
     */
    private static Jedis getJedis() {
        try {
            if (pool != null) {
                Jedis resource = pool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void close(final Jedis jedis) {
        if (jedis != null) {
        	jedis.close();
        }
    }
    
    public static Object get(Object key) {
    	Object obj = null ;
    	Jedis cache = getJedis();
    	try {
    		if(null == cache) {
    			return null;
    		}
    		byte [] b = cache.get(key.getClass().getName().getBytes());
    		if(b !=null) {
    			 obj = new String(b);
    		}
    	}finally {
    		
    	}
		return obj;
    }
    
    /**
     * 
     * @param key
     * @param value
     */
    public static void put(Object key, Object value) {
    	Jedis cache = getJedis();
      try {
    	if(value == null) {
    		cache.del(key.getClass().getName().getBytes());
    	}else {
    		cache.set(key.getClass().getName().getBytes(), value.toString().getBytes());
    	}
      }catch(Exception e){
    	  throw new CacheException(e);
      }finally {
    	  cache.close();
      }
    }
    
    /**
     * 
     * @param key
     * @param value
     */
    public static void update(Object key, Object value) {
		put(key, value);
	}
    
    /**
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static List keys() {
		Jedis cache = getJedis();
		try {
			List<String> keys = new ArrayList<String>();
			keys.addAll(cache.keys("user-*"));
			for(int i=0;i<keys.size();i++){
				keys.set(i, keys.get(i));
			}
			return keys;
		} catch (Exception e) {
			throw new CacheException(e);
		} finally {
			cache.close();
		}
	} 
    
    public static void main(String[] args) {
    	//RedisUtil.getJedis();
    	System.out.println(RedisUtil.getJedis());
    	String key = "yaojia";
    	String value = "value";
    	RedisUtil.put(key, value);
    	Goods vo = new Goods();
    	vo.setDelflag(1);
    	vo.setGid(2l);
    	vo.setLastin(new Date());
    	vo.setName("yaojiaiteye@163.com");
    	vo.setNote("mmmmmm");
    	vo.setPrice(1000.0);
    	RedisUtil.put(key, vo);
    	System.err.println(RedisUtil.get(key));
	}
}
