package cn.mldn.dibmp.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IStorageApplyDAO;
import cn.mldn.dibmp.dao.IWarehouseDAO;
import cn.mldn.dibmp.fyh.service.IWarehouseService;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.util.service.abs.AbstractService;

@Service
public class WarehouseServiceImpl extends AbstractService implements IWarehouseService {
	
	@Resource
	private IWarehouseDAO warehouseDAO ;
	@Resource
	private IStorageApplyDAO storageApplyDAO ;
	@Override
	public Map<String, Object> addPre(Long wiid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Warehouse vo) {
		return warehouseDAO.doCerate(vo) ;
	}

	@Override
	public Map<String, Object> list(long currentPage, int lineSize, String column, String keyWord) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("allWarehouses", warehouseDAO.findAllSplit(super.paramToMap(currentPage, lineSize, column, keyWord))) ;
		map.put("allCounts", warehouseDAO.getSplitCount(super.paramToMap(column, keyWord))) ;
		return map ;
	}

	@Override
	public boolean addAdmin(Warehouse vo) {
		return warehouseDAO.doCreateAdmin(vo) ;
	}

	@Override
	public Map<String, Object> listStorageApply(long currentPage, int lineSize, String column, String keyWord) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("allStorageApply", storageApplyDAO.findSplit(super.paramToMap(currentPage, lineSize, column, keyWord))) ;
		map.put("allCounts", storageApplyDAO.CountSplit(super.paramToMap(column, keyWord))) ;
		return map ;
	}

}
