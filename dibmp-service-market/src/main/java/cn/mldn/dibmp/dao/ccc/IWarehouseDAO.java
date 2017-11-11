package cn.mldn.dibmp.dao.ccc;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Warehouse;

public interface IWarehouseDAO {
	/**
	 * 根据仓库类型（也是商品类型）找到仓库位置
	 * @param wiid
	 * @return
	 */
	public List<Warehouse> findByWitem(Long wiid);
	/**
	 * 根据仓库编号找到仓库名称
	 * @param wid
	 * @return
	 */
	public Map<Long,String> findByWidMap(Long wid);
}
