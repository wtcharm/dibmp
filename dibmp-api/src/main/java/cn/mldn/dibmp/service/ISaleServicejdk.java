package cn.mldn.dibmp.service;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Customer;
import cn.mldn.dibmp.vo.CustomerRecord;
import cn.mldn.dibmp.vo.Goods;

/**
 * 定义销售业务接口
 * @author yaojia
 */
public interface ISaleServicejdk {
	/**
	 * 实现商品清单并分页
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return
	 */
	public Map<String,Object> list(String column, String keyWord, 
			Long currentPage, Integer lineSize);
	
	/**
	 * 更新商品信息
	 * @param vo
	 * @return
	 */
	public boolean edit(Goods vo);
	
	/**
	 * jdk
	 * 增加客户
	 * @param vo 
	 * @return 增加的新客户
	 */
	public boolean add(Customer vo);
	/**
	 * jdk
	 * 客户增加前的信息查询，该方法执行如下操作:<br>
	 * @return 返回一下数据类型<br>
	 * key = allCitys,value = 全部城市信息<br/>
	 * key = allProvince,value = 全部省份信息<br/>
	 * key = allCsource,value = 全部客户来源<br/>
	 * key = allCitem,value = 全部的客户等级信息<br/>
	 */
	public Map<String,Object> getAddPre();
	/**
	 * jdk
	 * 客户记录增加
	 * @param vo  客户记录
	 * @param cmid 当前增加者id
	 * @return
	 */
	public boolean addCustomerRecord(CustomerRecord vo,String cmid);
	/**
	 * jdk
	 * 根据省份pid查找对应的城市
	 * @param pid
	 * @return 对应的城市
	 */
	public Map<String,Object> listCity(Long pid);
	/**
	 * 列出指定客户业务分类的所有类型
	 * @param criid  客户记录单id
	 * @return
	 * key = allCritem,value=所有业务分类
	 */
	public Map<String,Object> listCritem(long criid);
	/**
	 * 列出指定客户业务分类的所有类型
	 * @return
	 * key = allCritem,value=所有业务分类
	 */
	public Map<String,Object> listCritem();
	/**
	 * jdk
	 * 更新客户联系次数
	 * @param vo 客户
	 * @return 成功返回true.
	 */
	public boolean doUpdateConnum(Customer vo);
	/**
	 * jdk
	 * 进行顾客信息数据列表显示处理，执行如下操作：<br>
	 * 1.调用CitemMapper.findAll()取的全部的顾客等级<br>
	 * 2.调用CustomerMapper.findAll()方法得到顾客的详情信息<br>
	 * 3.调用CustomerMapper.findAllSplit()方法进行顾客的分页查询<br>
	 * 4.调用CustomerMapper.getAllCount()方法进行顾客数量的查询.<br>
	 * @param column 模糊查询列
	 * @param keyWord 关键字
	 * @param currentPage 当前页
	 * @param lineSize 每页显示的行数
	 * @return
	 */
	
	public Map<String,Object> listC(Long currentPage, Integer lineSize,String column, String keyWord);
	public Map<String,Object> listD(Long cuid,String cmid,Long currentPage,Integer lineSize);
	/**
	 * 根据cuid查到相应的顾客记录
	 * @param cuid 顾客id
	 * @return
	 */
	public List<CustomerRecord> getAllCrById(Long cuid);
	/**
	 * 调用CustomerMapper.findById()方法根据name查询客户信息
	 * @param cuid 客户name
	 * @return 如果有顾客则返回对象
	 */
	public Customer getName(String name);
}
