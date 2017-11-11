package cn.mldn.dibmp.service.abc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStirageService {
	/**
	 * 建立一个String,Object类型的的map
	 * @return
	 */
	protected Map<String, Object> StringObjectMap() {
		 HashMap<String, Object> map = new HashMap<String,Object>();
		return map;
	}
	protected Map<Long, Object> LongObjectMap() {
		 HashMap<Long, Object> map = new HashMap<Long,Object>();
		return map;
	}
	/**
	 * 模糊查询实现数据判断
	 * @param culumn
	 * @param keyWord
	 * @return
	 */
	public boolean  isEmptyString(String culumn,String keyWord) {
		if("".equals(culumn)||"".equals(keyWord)) {
			return true;
		}
		return false;
	}
	public Double HandingBigDecimal(Double handing) {
		 BigDecimal bs = new BigDecimal(new Double(handing).toString());
		 Double hand = bs.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		 
		return hand;
		
	}
	
}
