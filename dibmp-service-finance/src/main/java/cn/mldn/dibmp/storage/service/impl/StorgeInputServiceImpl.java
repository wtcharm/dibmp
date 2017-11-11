package cn.mldn.dibmp.storage.service.impl;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IStorageApplyDAO;
import cn.mldn.dibmp.dao.IStorageApplyDetailsDAO;
import cn.mldn.dibmp.dao.IWarehouseDAO;
import cn.mldn.dibmp.dao.IWitemDAO;
import cn.mldn.dibmp.service.abc.AbstractStirageService;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.dibmp.wt.service.IStorgeInputService;
@Service
public class StorgeInputServiceImpl extends AbstractStirageService implements IStorgeInputService {
	@Resource
	private IStorageApplyDAO applyDAO;
	@Resource
	private IWarehouseDAO wareHouserDAO;
	@Resource
	private IWitemDAO witemDAO;
	@Resource
	private IStorageApplyDetailsDAO applyDetailsDAO;
	@Override
	public Map<String, Object> listInputBcke(Long said,String status) {
		Map<String, Object> map = super.StringObjectMap();
		Map<String, Object> statusMap = super.StringObjectMap();
		statusMap.put("said", said);
		statusMap.put("status", status);
		StorageApply apply = applyDAO.findBySaid(statusMap);
		Warehouse wareHouse = this.wareHouserDAO.findByWid(apply.getWid());
		map.put("allWareHouse", wareHouse);
		map.put("allWitem", this.witemDAO.findByWiid(wareHouse.getWiid()));
		map.put("applyAll",apply );
		map.put("details", applyDetailsDAO.findBySaid(said));
		return map;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean isSaidVo(Long said,String status) {
		Map<String, Object> statusMap = super.StringObjectMap();
		statusMap.put("said", said);
		statusMap.put("status",status);
		StorageApply apply = applyDAO.findBySaid(statusMap);
		if("".equals(apply)||apply == null) {
			return false;
		}
		return true;
	}
}
