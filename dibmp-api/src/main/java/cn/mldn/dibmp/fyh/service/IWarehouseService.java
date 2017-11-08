package cn.mldn.dibmp.fyh.service;

import java.util.Map;

import cn.mldn.dibmp.vo.Warehouse;

public interface IWarehouseService {
	/**
	 * 仓库增加前操作
	 * @param wiid 
	 * @return map
	 */
	public Map<String,Object> addPre(Long wiid);
	/**
	 * 创建仓库操作
	 * @param vo vo
	 * @return true
	 */
	public boolean add(Warehouse vo);
	/**
	 * 仓库信息列表显示操作
	 * @param currentPage 
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return 
	 */
	public Map<String,Object> list(long currentPage, int lineSize, String column, String keyWord) ;
	/**
	 * 设置仓库管理员操作
	 * @param vo vo
	 * @return true
	 */
	public boolean addAdmin(Warehouse vo) ;
}
