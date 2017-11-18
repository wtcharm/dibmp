package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.Goods;

/**
 * 销售接口
 * @author yaojjia
 *
 */
public interface GoodsMapper {

	    public boolean doCreate(Goods vo) ;
		
		public boolean doUpdate(Goods vo) ;
		
		public boolean doRemoGoods(Integer id) ;
		
		public Goods findById(Long gid) ;
		
		public List<Goods> findAll() ;
		
		public List<Goods> findAllSplit(Map<String,Object> params) ;
		
		public List<Goods> findSplit(Long currentPage,Integer lineSize) ;
		
		public long getSplitCount(Map<String,Object> params) ;
		
		public long getAllCount() ;
}
