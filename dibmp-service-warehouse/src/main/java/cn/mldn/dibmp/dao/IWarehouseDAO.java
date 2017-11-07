package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Warehouse;

public interface IWarehouseDAO {
	public boolean doCerate(Warehouse vo) ;
	public List<Warehouse> findAllSplit(Map<String,Object> params) ;
	public Long getSplitCount(Map<String,Object> params) ;
}
