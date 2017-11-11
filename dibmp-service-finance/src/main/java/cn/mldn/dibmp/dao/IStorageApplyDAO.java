package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.StorageApply;
/**
 * @author 定义商品入库申请单
 */
public interface IStorageApplyDAO {
	/**
	 * 实现商品入库申请单的填写
	 * @param vo  添加的vo 对象
	 * @return  成功返回true否则返回false
	 */
	public boolean doCreate(StorageApply vo);
	/**
	 * 修改当前入库申请单的状态
	 * 0为未处理申请，1为处理申请
	 * 根据said 和 status
	 * @param status  状态信息 0 ，1
	 * @return 修改成功返回true否则返回false
	 */
	public boolean doEditStatus(Map<String, Object> map);
	
	/**
	 * 查询所有的待审核清单数据
	 * @return
	 */
	public List<StorageApply> findAll();
	/**
	 * 实现待审核清单的分页和模糊查询
	 * @param map  要分页和模糊查询的数据
	 * @return
	 */
	public List<StorageApply> findSplit(Map<String,Object> map);
	/**
	 * 统计当前总个数
	 * @return
	 */
	public Long CountSplit(Map<String,Object> map);
	/**
	 * 通过 said 和 status 查询需要出要查询数据
	 * @param map status 1 未审核 ，5审核通过
	 * @return
	 */
	public StorageApply findBySaid (Map<String, Object> map);
}
