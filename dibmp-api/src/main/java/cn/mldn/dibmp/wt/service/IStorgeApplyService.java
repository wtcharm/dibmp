package cn.mldn.dibmp.wt.service;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.StorageApply;

/**
 * 负责商品待审核清单的处理待审核清单
 * @author Administrator
 */
public interface IStorgeApplyService {
	/**
	 * 实现待审核清单的数据添加
	 * @param vo 待审核清单的数据
	 * @return 添加成功 返回true否则返回false
	 */
	public boolean add(StorageApply vo);

	/**
	 * 更新当前待审核清单的状态
	 * 0 - 为待审核  1 - 审核完成
	 * @param said  当前申请编号
	 * @param status 申请状态
	 * @return  申请成功返回true否则返回false
	 */
	public boolean updateStatus(Long said,Integer status); 
	
	/**
	 *  显示所有的待审核清单的数据显示
	 *
	 * 1.key="allApply" value="当前待审核的所有信息";
	 * 2.key="CountNum" value="当前审核清单的总数量";
	 * 3.key="SumPrice" value="当前审核清单的总价格";
	 * 4.key="addRess" value="仓库的存储地址";
	 * 
	 * 实现清单的模糊查询带分页
	 * @param column  
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return
	 */
	public Map<String, Object> listSplitFont(String column,String keyWord,Long currentPage,Integer lineSize);
	/**
	 * 根据said 仓库的编号获取的到每一个仓库所对应的商品个数
	 * @param said 当前仓库的编号 status 当前仓库的状态
	 * @return  当前信息
	 */
	public Map<String, Object> getWarehouseGoodsBySaid(Long said,Integer status);
	/**
	 * 根据 wid 查询仓库的所有信息
	 * @param wid 当前仓库信息
	 * @return 当前查询有一下几个返回值
	 * 1.key="allWareHouse" value="当前仓库的所有信息"
	 * 2.key="allWitem" value="当前仓库中仓库的类型"
	 * 3.key="allSubtype" value="当前仓库类型中包含的种类"
	 */
	public Map<String, Object> listWareHouseAndApplyByWid(Long wid);
}
