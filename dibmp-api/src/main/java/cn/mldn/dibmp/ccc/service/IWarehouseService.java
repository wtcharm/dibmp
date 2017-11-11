package cn.mldn.dibmp.ccc.service;

import java.util.List;

import cn.mldn.dibmp.vo.Warehouse;

public interface IWarehouseService {
	/**
	 * 根据仓库类型（也是商品类型）找到仓库位置
	 * @param wiid cc
	 * @return cc
	 */
	public List<Warehouse> listByWitem(long wiid);
}
