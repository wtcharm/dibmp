package cn.mldn.dibmp.dao.ccc;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Goods;

public interface IGoodsDAO {
	
	public boolean doCreate(Goods vo);
	
//	public List<Goods> findAll(Long currentPage,Integer lineSize);
	
	public List<Goods> findAllSplit(Map<String,Object> params);
	
//	public Long getAllCount();
	
	public Long getSplitCount(Map<String,Object> params);
	/**
	 * 根据商品名称查询商品信息
	 * @param name 商品名称
	 * @return 商品信息
	 */
	public Goods findByName(String name);
	
	public Goods findById(Long gid);
	
	public boolean doEdit(Goods vo);
}
