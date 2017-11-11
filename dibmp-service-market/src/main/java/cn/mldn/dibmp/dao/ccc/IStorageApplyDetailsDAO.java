package cn.mldn.dibmp.dao.ccc;
/**
 * @author  定义入库商品请单详情，
 */

import java.util.List;

import cn.mldn.dibmp.vo.StorageApplyDetails;

public interface IStorageApplyDetailsDAO {
	/**
	 * 添加商品的详情
	 * @param vo  商品的信息
	 * @return 添加成功返回true否则返回false
	 */
	public boolean doCreate(StorageApplyDetails vo);
	/**
	 * 根据 storageApply中的said的值查询出当前用户所入库的商品信息
	 * @param sadid   storage_apply.said = storage_apply_details.said
	 * @return
	 */
	public List<StorageApplyDetails> findBySaid(Long said);
	/**
	 * 查询当前订单中商品的总价
	 * @param said 要查询订单编号
	 * @return
	 */
	public Double findSumPrice(Long said);
	/**
	 * 查询当前订单中商品数量的总数
	 * @param said  要查询的订单编号
	 * @return
	 */
	public Integer findCountNum(Long said);
	/**
	 * 查询当前订单中商品的总价
	 * @param sadid
	 * @return
	 */
	public Double findSumSadid(Long sadid);
}
