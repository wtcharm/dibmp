package cn.mldn.web.util;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import cn.mldn.util.exception.CacheException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import cn.mldn.dibmp.vo.GoodsNum;

/**
 * Redis工具类
 * @author yaojia
 *
 */
public class RedisUtil {

	private static final String REDIS_HOST = "192.168.28.68" ;
	private static final int REDIS_PORT = 6379 ;
	private static final int TIMEOUT = 2000 ; 
	private static final int MAX_TOTAL = 200 ;	
	private static final int MAX_IDLE = 20 ;	
	private static final int MAX_WAIT_MILLIS = 1000 ;
	private static final boolean TEST_ON_BORROW = true ;
	private static final String REDIS_AUTH = "mldnjava" ;	
	private static JedisPool pool = null ;	
	
	private static final Random random = new Random();
	
	static {
		JedisPoolConfig config = new JedisPoolConfig() ;
		config.setMaxTotal(MAX_TOTAL);
		config.setMaxIdle(MAX_IDLE);
		config.setMaxWaitMillis(MAX_WAIT_MILLIS);
		config.setTestOnBorrow(TEST_ON_BORROW); 
		pool = new JedisPool(config,REDIS_HOST,REDIS_PORT,TIMEOUT,REDIS_AUTH) ;
	}
	
	/*public RedisUtil() {
		JedisPoolConfig config = new JedisPoolConfig() ;
		config.setMaxTotal(MAX_TOTAL);
		config.setMaxIdle(MAX_IDLE);
		config.setMaxWaitMillis(MAX_WAIT_MILLIS);
		config.setTestOnBorrow(TEST_ON_BORROW); 
		pool = new JedisPool(config,REDIS_HOST,REDIS_PORT,TIMEOUT,REDIS_AUTH) ;
	}*/
	
	
	/**
     * 获取Jedis实例
     * @return
     */
    public static Jedis getJedis() {
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
    
    /**
     * 
     * @param key
     * @return
     */
    public static Object get(Object key) {
    	Object obj = null ;
    	Jedis cache = getJedis();
    	try {
    		if(null == cache) {
    			return null;
    		}
    		byte [] b = cache.get(key.getClass().getName().getBytes());
    		if(b !=null) {
    			 obj = new JavaSerializer().deserialize(b, cn.mldn.dibmp.vo.GoodsNum.class);
    		}
    	}finally {
    		close(cache);
    	} 
		return obj;
    }
    
    /**
     * 
     * @param key
     * @param value
     */
    public static void put(Object key, Object value,Long gid) {
    	Jedis cache = getJedis();
    	//String count = String.valueOf(random.nextInt(20));
    	String keys = "yaojia_"+key+":"+gid;
      try {
    	if(value == null) {
    		cache.del(keys.getBytes());
    	}else {
    		cache.set(keys.getBytes(), new JavaSerializer().serialize(value));
    	}
      }catch(Exception e){
    	  throw new CacheException(e);
      }finally {
    	  close(cache);
      }
    }
    
    /**
     * 
     * @param key
     * @param value
     * @param gid
     */
    public static void set(Object key, Object value,Long gid) {
    	Jedis cache = getJedis();
    	//String count = String.valueOf(random.nextInt(20));
    	String keys = "yaojia1_"+key+":"+gid;
      try {
    	if(value == null) {
    		cache.del(keys.getBytes());
    	}else {
    		cache.set(keys.getBytes(), new JavaSerializer().serialize(value));
    	}
      }catch(Exception e){
    	  throw new CacheException(e);
      }finally {
    	  close(cache);
      }
    }
    
    /**
     * 
     * @return
     */
    public static Set<String> getKeys() {
		Jedis cache = getJedis();
		try {
			Set<String> keys = cache.keys("yaojia1_*"); 
			return keys;
		} catch (Exception e) {
			throw new CacheException(e);
		} finally {
			cache.close();
		}
	}
    
    /**
     * 
     * @return
     */
    public static Set<String> keys() {
		Jedis cache = getJedis();
		try {
			Set<String> keys = cache.keys("yaojia_*"); 
			return keys;
		} catch (Exception e) {
			throw new CacheException(e);
		} finally {
			cache.close();
		}
	}
    /**
     * 
     * @param key
     * @param value
     */
    public static void update(Object key, Object value,Long gid) {
		put(key, value, gid);
	}
    
    /**
     * 
     * @param key
     * @param ids
     */
    public static void del(String key,String ids) {
    	Jedis cache = getJedis();
    	Set<Long> set = CastUtil.castLongSet(ids);
    	for(int x=0; x< set.size(); x++) {
    		Iterator<Long> iter = set.iterator();
    		while(iter.hasNext()) {
    			Long gid = iter.next();
    			String keys = "yaojia_"+key+":"+gid;
    			try {
    				cache.del(keys);
    			}catch(CacheException e) {
    				throw new RuntimeException(e);
    			}finally {
    				close(cache);
    			}
    		}
    	}
    }
    
    
    public static void main(String[] args) {
//    	GoodsNum vo = new GoodsNum();
//    	vo.setName("yaojiaiteye@163.com");
//    	vo.setGid(1l);
//    	vo.setPrice(100.0);
//    	vo.setNum(10);
//    	vo.setStornum(158);
//    	vo.setWeight(458.0);
//    	vo.setPrice(1000.0);
//    	vo.setName("老孙");
//    	GoodsNum vo1 = new GoodsNum();
//    	vo1.setName("yaoj63.com");
//    	vo1.setPrice(1123.0);
//    	vo1.setGid(1l);
//    	vo1.setPrice(100.0);
//    	vo1.setNum(10);
//    	vo1.setStornum(158);
//    	vo1.setWeight(458.0);
//    	vo1.setPrice(1000.0);
//    	RedisUtil.put("cc", vo);
//    	RedisUtil.put("cc1", vo1);
        Set<String> keys = RedisUtil.getKeys() ;
		Iterator<String> iter = keys.iterator() ;
		while (iter.hasNext()) {
			String keyss = iter.next() ;
		//byte b [] = new DefaultJavaSerializer().serialize(keyss);
			//byte bb [] =new DefaultJavaSerializer().serialize(RedisUtil.get(keyss.getBytes()));
		System.err.println(keyss + " = ");
		    try {
				System.out.println(new JavaSerializer().deserialize(RedisUtil.getJedis().get(keyss.getBytes()), GoodsNum.class));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
