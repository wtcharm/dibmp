package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Customer;
/**
 * 客户业务接口
 * @author JDK
 *
 */
public interface CustomerMapper {
	/**
	 * 客户增加
	 * @param vo 客户信息
	 * @return 增加成功返回true
	 */
	public boolean doCreate(Customer vo) ;
	/**
	 * 查询所有的客户信息
	 * @return  所有的客户详情
	 */
	public List<Customer> findAll(Map<String,Object> param);
	/**
	 * 客户分页
	 * @return
	 */
	public List<Customer> findAllSplit(Map<String,Object> param) ;
	/**
	 * 客户分页的总量
	 * @return 总页数
	 */
	public long getAllSplitCount(Map<String,Object> param);
	
	public List<Customer> findAllCustomerByCmid(Long cuid);
}