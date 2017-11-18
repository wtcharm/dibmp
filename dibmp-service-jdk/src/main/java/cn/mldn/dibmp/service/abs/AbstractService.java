package cn.mldn.dibmp.service.abs;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractService {
	
	protected Map<String, Object> handleParams(String column, String keyWord, Long currentPage, Integer lineSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		if ("".equals(column) || column == null || "null".equalsIgnoreCase(column)) {
		} else {
			map.put("column", column) ;
		}
		if ("".equals(keyWord) || "null".equalsIgnoreCase(keyWord) || keyWord == null) {
		} else { 
			map.put("keyWord", "%" + keyWord + "%") ;
		}
		if ((currentPage - 1) * lineSize < 0) {
			map.put("start", 0) ;
		} else {
			map.put("start", (currentPage - 1) * lineSize) ;
		}
		map.put("lineSize", lineSize > 0 ? lineSize : 5) ;
		return map;
	}
	protected Map<String, Object> handleMyParams(String cmid,Long cuid, Long currentPage, Integer lineSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cmid", cmid) ;
		map.put("cuid", cuid) ;
		if ((currentPage - 1) * lineSize < 0) {
			map.put("start", 0) ;
		} else {
			map.put("start", (currentPage - 1) * lineSize) ;
		}
		map.put("lineSize", lineSize > 0 ? lineSize : 5) ;
		return map;
	}
}
