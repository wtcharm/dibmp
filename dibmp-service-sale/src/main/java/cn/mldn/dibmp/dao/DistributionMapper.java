package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Distribution;
import cn.mldn.dibmp.vo.Goods;

/**
 * 定义出库申请
 * @author yaojia
 *
 */
public interface DistributionMapper {

	public boolean doCreate(Distribution vo);
	
	public List<Goods> findAllSplit(Map<String,Object> params) ;
	
	public long getSplitCount(Map<String,Object> params) ;
}
