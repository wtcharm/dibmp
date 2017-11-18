package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Customer;
import cn.mldn.dibmp.vo.Member;
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
	 * 根据cuid查找客户详情
	 * @param cuid 客户id
	 * @return
	 */
	public Customer findById(long id);
	/**
	 * 根据cuid查找客户详情
	 * @param cuid 客户名字
	 * @return
	 */
	public Customer findByName(String name);
	
	/**
	 * 查询所有的客户信息
	 * @return  所有的客户详情
	 */
	public List<Customer> findAll();
	/**
	 * 更新客户联系次数
	 * @param vo 客户
	 * @return 成功返回true.
	 */
	public boolean editConnum(Customer vo);
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
	/**
	 * 查找雇员详情
	 * @param mid 雇员id
	 * @return
	 */
	public Member findById(String mid);
	/**
	 * 
	 * @param mid
	 * @return
	 */
	public String findDnameById(String mid);
}