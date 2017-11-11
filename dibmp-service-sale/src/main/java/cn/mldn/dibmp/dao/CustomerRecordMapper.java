package cn.mldn.dibmp.dao;

import java.util.List;

import cn.mldn.dibmp.vo.CustomerRecord;

public interface CustomerRecordMapper {

	/**
	 * 根据销售人员的id取得联系人信息
	 * @param cmid
	 * @return
	 */
	public List<CustomerRecord>findByCmid(String cmid);
}
