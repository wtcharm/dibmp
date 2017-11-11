package cn.mldn.dibmp.storage.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IStorageApplyDAO;
import cn.mldn.dibmp.dao.IStorageApplyDetailsDAO;
import cn.mldn.dibmp.dao.IStorageRecordDAO;
import cn.mldn.dibmp.dao.IWarehouseDAO;
import cn.mldn.dibmp.service.abc.AbstractStirageService;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageRecord;
import cn.mldn.dibmp.wt.service.IStorgeRecordService;
@Service
public class StorgeRecordServiceImpl extends AbstractStirageService implements IStorgeRecordService {
	@Resource
	private IStorageRecordDAO storageRecordDAO;
	@Resource
	private IStorageApplyDAO applyDAO;
	@Resource
	private IWarehouseDAO wareHouserDAO;
	@Resource
	private IStorageApplyDetailsDAO applyDetailsDAO;
	@Override
	public boolean add(StorageRecord vo) {
		if(storageRecordDAO.doCreate(vo)) {
			 Map<String, Object> map = super.StringObjectMap();
			 map.put("said", vo.getSaid());
			 map.put("status", 1);
			return applyDAO.doEditStatus(map);
		}
		return false;
	}
	
	@Override
	public Map<String, Object> listRecord(String column,String keyWord,Long currentPage,Integer lineSize) {
		Map<String,Object> map = super.StringObjectMap();
		if(super.isEmptyString(column, keyWord)) {
			map.put("column",null);
		}else {
			map.put("column",column);
			map.put("keyWord","%"+keyWord+"%");
		}
		map.put("startPage",(currentPage-1)*lineSize);
		map.put("lineSize", lineSize);
		map.put("status", 5);
		Map<String,Object> maps = new HashMap<String,Object>();
		Map<Long, Object> sumMap = super.LongObjectMap();
		Map<Long, Object> countMap = super.LongObjectMap();
		Map<Long, Object> warehouseMap = super.LongObjectMap();
		List<StorageApply> apply = applyDAO.findSplit(map);
		Iterator<StorageApply> rs = apply.iterator();
		while(rs.hasNext()){
			StorageApply sApply = new StorageApply();
			sApply = rs.next(); 
			warehouseMap.put(sApply.getWid(), this.wareHouserDAO.findByWid(sApply.getWid()));
			countMap.put(sApply.getSaid(),applyDetailsDAO.findCountNum(sApply.getSaid()));	  
			if(applyDetailsDAO.findSumPrice(sApply.getSaid())!=null) {
				sumMap.put(sApply.getSaid(),super.HandingBigDecimal(applyDetailsDAO.findSumPrice(sApply.getSaid())));
			}else {
				sumMap.put(sApply.getSaid(),0);
			}
		}
		maps.put("findSplit",apply);	//商品信息
		maps.put("CountNum",countMap);	//商品数量
		maps.put("SumPrice",sumMap);	//商品价格
		maps.put("allWarehosut", warehouseMap);
		maps.put("allRecorders",applyDAO.CountSplit(map));
		 
		return maps;
	}

}
