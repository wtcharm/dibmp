package cn.mldn.dibmp.service.impl.ccc;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.mldn.dibmp.ccc.service.IMarketService;
import cn.mldn.dibmp.dao.ccc.IStorageApplyDAO;
import cn.mldn.dibmp.dao.ccc.IStorageApplyDetailsDAO;
import cn.mldn.dibmp.dao.ccc.IWarehouseDAO;
import cn.mldn.dibmp.dao.ccc.IWitemDAO;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.util.service.abs.AbstractService;
@Service
public  class MarketServiceImpl  extends AbstractService implements IMarketService {
	@Resource
	private IStorageApplyDAO storagerApplyDAO;
	@Resource
	private IStorageApplyDetailsDAO storageApplyDetailsDAO;
	@Resource
	private IWarehouseDAO warehouseDAO;
	@Resource
	private IWitemDAO witemDAO;
	@Resource
	private RedisTemplate<String,StorageApplyDetails> redisTemplate ;
	@Override
	public boolean addStorageApply(StorageApply vo,String mid) {
		vo.setStatus(0);//待提交
		vo.setAppmid(mid);//申请人
		return storagerApplyDAO.doCreate(vo);
	}
	@Override
	public Map<String, Object> show(Long said) {
		Map<String, Object> map = new HashMap<String,Object>();
		StorageApply storageApply = storagerApplyDAO.findBySaid(said);
		map.put("storageApply",storageApply );
		map.put("storageApplyDetails", storageApplyDetailsDAO.findBySaid(said));
		map.put("warehouses", warehouseDAO.findByWidMap(storageApply.getWid()));
		map.put("witems", witemDAO.findByWiidMap(storageApply.getWiid()));
		return map;
	}
	@Override
	public boolean saveGoods(StorageApplyDetails vo) {
		this.redisTemplate.opsForValue().set("ccc-"+vo.getSaid()+"-"+vo.getGid(), vo);
		return true;
	}
	@Override
	public boolean removeGoods(Long said,Long gid ) {
		return this.redisTemplate.delete("ccc-"+said+"-"+gid);
	}
}