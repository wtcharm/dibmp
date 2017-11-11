package cn.mldn.dibmp.dao.ccc;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Witem;

public interface IWitemDAO {
	public List<Witem> findAll();
	public Map<Long,String> findByWiidMap(Long wiid);
	
}
