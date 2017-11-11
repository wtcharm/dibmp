package cn.mldn.dibmp.fyh.service;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.StorageApplyDetails;
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
	/**
	 * 实现商品入库清单的（模糊）分页显示
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return
	 */
	public Map<String,Object> listStorageApply(long currentPage, int lineSize, String column, String keyWord,String appmid) ;
	/**
	 * 实现商品入库提交申请
	 * @param vo 商品信息详情表
	 * @return true
	 */
	public boolean inSubmit(Long said) ;
	
	public boolean testAddBatch(List<StorageApplyDetails> listsad) ;
	/**
	 * 删除入库申请表，采用物理删除
	 * @param said 入库单号
	 * @return true
	 */
	public boolean removeStorageApply(Long said) ;
	
}
