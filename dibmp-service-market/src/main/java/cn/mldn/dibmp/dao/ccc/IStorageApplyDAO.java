package cn.mldn.dibmp.dao.ccc;

import cn.mldn.dibmp.vo.StorageApply;

public interface IStorageApplyDAO {
	/**
	 * 实现商品入库申请单的填写
	 * @param vo  添加的vo 对象
	 * @return  成功返回true否则返回false
	 */
	public boolean doCreate(StorageApply vo);
	/**
	 * 根据入库申请编号查询出详细信息
	 * @param said
	 * @return
	 */
	public StorageApply findBySaid(Long said);
}
