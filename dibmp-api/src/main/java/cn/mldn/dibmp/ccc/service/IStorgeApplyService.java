package cn.mldn.dibmp.ccc.service;

import cn.mldn.dibmp.vo.StorageApply;

public interface IStorgeApplyService {
	/**
	 * 实现待审核清单的数据添加
	 * @param vo 待审核清单的数据
	 * @param mid 申请人
	 * @return 添加成功 返回true否则返回false
	 */
	public boolean add(StorageApply vo,String mid);

}
