package cn.mldn.dibmp.fyh.service;

import java.util.Map;

import cn.mldn.dibmp.vo.Warehouse;

public interface IWarehouseService {
	public Map<String,Object> addPre(Long wiid);
	public boolean add(Warehouse vo);
	public Map<String,Object> list(long currentPage, int lineSize, String column, String keyWord) ;
}
