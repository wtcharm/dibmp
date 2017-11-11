package cn.mldn.web.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 转型操作工具类
 * @author yaojia
 *
 */
public class CastUtil {

	
	public static String castString(Object obj) {
        return CastUtil.castString(obj, "");
    }
	
	/**
	 * Object对象转换String类型
	 * @param obj Object对象
	 * @param defaultValue
	 * @return
	 */
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }
    
    /**
     * 把数组转换为Set集合
     * @param objArray
     * @return
     */
	public static Set<Long> castLongSet(Object [] objArray){
		Set<Long> longSet = new HashSet<Long>();
		for(int x=0; x < objArray.length; x++) {
			String StrValue = CastUtil.castString(objArray[x]);
			if(StringUtil.isNotEmpty(StrValue)) {
				longSet.add(Long.parseLong(StrValue));
			}
		}
		return longSet;
	}
	
	/**
	 * 把String类型转换Long型
	 * @param obj
	 * @return
	 */
	public static long castLong(Object obj) {
		long longValue = 0 ;
		if(obj !=null) {
			String strValue = CastUtil.castString(obj);
			if(StringUtil.isNotEmpty(strValue)) {
				try {
				     longValue = Long.parseLong(strValue);
				}catch(NumberFormatException e) {
					longValue = 0;
				}
			}
		}
		return longValue;
	}
	
	/**
	 * 把字符串转换成整形数据
	 * @param obj
	 * @return
	 */
	public static Integer castInt(Object obj) {
		Integer intValue = 0 ;
		if(obj !=null) {
			String strValue = CastUtil.castString(obj);
			if(StringUtil.isNotEmpty(strValue)) {
				try {
				     intValue = Integer.parseInt(strValue);
				}catch(NumberFormatException e) {
					
				}
			}
		}
		return intValue;
	}
	
	/**
	 * 把字符串ids按|拆分,并转换成Set集合
	 * @param ids
	 * @return
	 */
	public static Set<Long> castLongSet(String ids){
		Set<Long> longSet = new HashSet<Long>();
		String result [] = ids.split("\\,");
		for(int x=0; x < result.length; x++) {
			String StrValue = CastUtil.castString(result[x]);
			if(StringUtil.isNotEmpty(StrValue)) {
				longSet.add(Long.parseLong(StrValue));
			}
		}
		return longSet;
	}
	
	
	/**
	 * 把字符串ids按|拆分,并转换成Set集合
	 * @param ids
	 * @return
	 */
	public static List<Long> castLongList(String ids){
		List<Long> longSet = new ArrayList<Long>();
		String result [] = ids.split("\\,");
		for(int x=0; x < result.length; x++) {
			String StrValue = CastUtil.castString(result[x]);
			if(StringUtil.isNotEmpty(StrValue)) {
				longSet.add(Long.parseLong(StrValue));
			}
		}
		return longSet;
	}
}