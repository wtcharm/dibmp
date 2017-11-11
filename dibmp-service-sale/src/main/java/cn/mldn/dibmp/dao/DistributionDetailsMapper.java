package cn.mldn.dibmp.dao;

import cn.mldn.dibmp.vo.DistributionDetails;

public interface DistributionDetailsMapper {

	/**
	 * 根据商品id查询商品出库申请详情
	 * @param gid
	 * @return
	 */
	public DistributionDetails findDistributionDetails(Long gid);
	
	/**
	 * 增加出库申请详情
	 * @param vo
	 * @return
	 */
	public boolean doCreate(DistributionDetails vo);
}
