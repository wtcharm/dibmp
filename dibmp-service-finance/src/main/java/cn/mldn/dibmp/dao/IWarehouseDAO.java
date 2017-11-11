package cn.mldn.dibmp.dao;

import cn.mldn.dibmp.vo.Warehouse;

public interface IWarehouseDAO {
	/**
	 * 根据仓库的编号查询仓库所有的信息
	 * @param wid 仓库编号
	 * @return 返回仓库信息
	 */
	public Warehouse findByWid(Long wid);
}
