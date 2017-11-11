package cn.mldn.dibmp.wt.service;
import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.StorageApplyDetails;

/**
 * 负责商品详情清单的显示
 * @author Administrator
 */
public interface IStorgeApplyDetailsService {
	/**
	 * 添加商品的详细信息清单
	 * @param vo  商品详细实现 
	 * @return 添加成功返回true否则返回fasle 
	 */
	public boolean add(StorageApplyDetails vo);
	
	/**
	 * 更新当前用户的申请单的状态，1 未 未审核，5为审核通过 3为 审核失败
	 * @param said  当前仓库名称
	 * @param status 修改的状态
	 * @param autime 审核日期
	 * @param auname 审核人
	 * @return 成功返回true 否则返回会false
	 */
	public boolean editSaid(Long said,Integer status,String autime,String auname);
	/**
	 * 根据编号查询当前的商品详细信息
	 * @param sadid 当前商品编号
	 * @return 返回对象的集合信形式
	 */
	public  List<StorageApplyDetails> findBySadid(Long sadid);
	
	/**
	 * 根据审核清单中的said 查询出当前仓库对应的商品信息
	 * 1.key=allApplyDetails  保存数据详情的信息
	 * 2.key=SumNums  得到仓库中商品总价
	 * 4.key=SumNum   得到每个商品的总价
	 * 3.key=allApply  得到申请表中的信息
	 * @param said  仓库编号
	 * @return 商品信息
	 */
	public Map<String, Object> listGoodsBack(Long said);
	/**
	 * 查询审核中商品的信息
	 * @param said  要查询的商品编号
	 * @return
	 */
	public Map<String, Object> listEditNum(Long said);
	
}
