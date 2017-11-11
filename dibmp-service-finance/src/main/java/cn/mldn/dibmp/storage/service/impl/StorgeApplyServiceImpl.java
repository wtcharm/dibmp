package cn.mldn.dibmp.storage.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IStorageApplyDAO;
import cn.mldn.dibmp.dao.IStorageApplyDetailsDAO;
import cn.mldn.dibmp.dao.ISubtypeDAO;
import cn.mldn.dibmp.dao.IWarehouseDAO;
import cn.mldn.dibmp.dao.IWitemDAO;
import cn.mldn.dibmp.service.abc.AbstractStirageService;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.dibmp.wt.service.IStorgeApplyService;
import cn.mldn.util.service.abs.AbstractService;
@Service
public class StorgeApplyServiceImpl extends AbstractStirageService implements IStorgeApplyService{
	@Resource
	private IStorageApplyDAO storagerApplyDAO;
	@Resource
	private IStorageApplyDetailsDAO applyDetailsDAO;
	@Resource
	private IWarehouseDAO wareHouserDAO;
	@Resource
	private IWitemDAO witemDAO;
	@Resource
	private ISubtypeDAO subtyDAO;
	@Override
	public boolean add(StorageApply vo) {
		return storagerApplyDAO.doCreate(vo);
	}
	@Override
	public boolean updateStatus(Long said, Integer status) {
		Map<String, Object> map = super.StringObjectMap();
		if(said == null || status ==null) {
			return false;
		}
		map.put("status",status);
		map.put("said", said);
		return this.storagerApplyDAO.doEditStatus(map);
	}

	@Override
	public Map<String, Object> listWareHouseAndApplyByWid(Long wid) {
		Map<String, Object> map = new HashMap<String,Object>();
		Warehouse wareHouse = this.wareHouserDAO.findByWid(wid);
		map.put("allWareHouse", wareHouse);
		map.put("allWitem", this.witemDAO.findByWiid(wareHouse.getWiid()));
		map.put("allSubtype",this.subtyDAO.findAllByWitem(wareHouse.getWiid()));
		return map;
	}

	@Override
	public Map<String,Object> listSplitFont(String column, String keyWord, Long currentPage, Integer lineSize) {
		Map<String,Object> map = super.StringObjectMap();
		if(super.isEmptyString(column, keyWord)) {
			map.put("column",null);
		}else {
			map.put("column",column);
			map.put("keyWord","%"+keyWord+"%");
		}
		map.put("startPage",(currentPage-1)*lineSize);
		map.put("lineSize", lineSize);
		map.put("status", 1);
		Map<String,Object> maps = new HashMap<String,Object>();
		Map<Long, Object> sumMap = super.LongObjectMap();
		Map<Long, Object> countMap = super.LongObjectMap();
		Map<Long, Object> warehouseMap = super.LongObjectMap();
		List<StorageApply> apply = this.storagerApplyDAO.findSplit(map);
		Iterator<StorageApply> rs = apply.iterator();
		while(rs.hasNext()){
			StorageApply sApply = new StorageApply();
			sApply = rs.next(); 
			countMap.put(sApply.getSaid(),this.applyDetailsDAO.findCountNum(sApply.getSaid()));	  
			warehouseMap.put(sApply.getWid(), this.wareHouserDAO.findByWid(sApply.getWid()));
			if(this.applyDetailsDAO.findSumPrice(sApply.getSaid()) != null) {
				sumMap.put(sApply.getSaid(),super.HandingBigDecimal(this.applyDetailsDAO.findSumPrice(sApply.getSaid())));
			}else {
				sumMap.put(sApply.getSaid(),0);
			}
			
		}
		maps.put("findSplit",apply);	//商品信息
		maps.put("CountNum",countMap);	//商品数量
		maps.put("SumPrice",sumMap);	//商品价格
		maps.put("allWarehosut", warehouseMap);
		maps.put("allRecorders",this.storagerApplyDAO.CountSplit(map));
		return maps;
	}
	@Override
	public Map<String, Object> getWarehouseGoodsBySaid(Long said,Integer status) {
		Map<String, Object> map = super.StringObjectMap();
		Map<String, Object> statusMap = super.StringObjectMap();
		statusMap.put("said", said);
		statusMap.put("status", status);
		StorageApply apply = this.storagerApplyDAO.findBySaid(statusMap);
		map.put("apply",apply);
		map.put("sum", super.HandingBigDecimal(this.applyDetailsDAO.findSumPrice(said))) ;
		map.put("applyDetails", this.applyDetailsDAO.findBySaid(said));
		map.put("allWarehosut", this.wareHouserDAO.findByWid(apply.getWid()));
		return map;
	}

}
