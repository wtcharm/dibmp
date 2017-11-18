package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.CustomerRecord;
import cn.mldn.dibmp.vo.Member;

public interface CustomerRecordMapper {
	/**
	 * 根据cuid查找客户详情
	 * @param cuid 客户名字
	 * @return
	 */
	public Member findNameByMid(String mid);
	/**
	 * 增加客户联系记录
	 * @param cuid 客户id
	 * @return
	 */
	public boolean doCreateCustomerRecord(CustomerRecord vo);
	/**
	 * 根据销售人员的id查找到cdate,note
	 * @param cmid 销售人员id
	 * @return
	 */
	public List<CustomerRecord> findAllCrById(String cmid);
	/**
	 * 查找业务类型
	 * @return 所有业务
	 */
	public List<CustomerRecord> findAll();
	/**
	 * 客户联系记录分页
	 * @param param
	 * @return
	 */
	public List<CustomerRecord> findAllSplit(Map<String,Object> param);
	/**
	 * 客户联系记录分页总数
	 * @param param 
	 * @return
	 */
	public long getAllSplitCount(Map<String,Object> param);
	public List<CustomerRecord> findAllCrById(Long cuid);
	//public List<CustomerRecord> getByName(String crid);

}