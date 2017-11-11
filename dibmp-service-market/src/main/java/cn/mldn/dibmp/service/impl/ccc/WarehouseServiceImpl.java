package cn.mldn.dibmp.service.impl.ccc;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.ccc.service.IWarehouseService;
import cn.mldn.dibmp.dao.ccc.IWarehouseDAO;
import cn.mldn.dibmp.vo.Warehouse;
@Service
public class WarehouseServiceImpl implements IWarehouseService {
	@Resource
	private IWarehouseDAO warehouseDAO;
	@Override
	public List<Warehouse> listByWitem(long wiid) {
		return warehouseDAO.findByWitem(wiid);
	}
}
