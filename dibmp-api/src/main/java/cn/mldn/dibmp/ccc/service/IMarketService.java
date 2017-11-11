package cn.mldn.dibmp.ccc.service;

import java.util.Map;

import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;

public interface IMarketService {
	/**
	 * 实现待审核清单的数据添加
	 * @param vo 待审核清单的数据
	 * @param mid 申请人
	 * @return 添加成功 返回true否则返回false
	 */
	public boolean addStorageApply(StorageApply vo,String mid);
	/**
	 * 列出入库申请单的详细信息，包括申请入库的商品信息
	 * @param said cc
	 * @return cc
	 */
	public Map<String,Object> show(Long said);
	/**
	 * 向Redis中临时存储申请入库商品
	 * @param vo 申请商品信息
	 * @return 存数成功返回true
	 */
	public boolean saveGoods(StorageApplyDetails vo);
	/**
	 * 从Redis数据库中删除要申请入库的商品
	 * @param gid
	 * @param said
	 * @return
	 */
	public boolean removeGoods(Long said,Long gid);
}
