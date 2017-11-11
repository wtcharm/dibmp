package cn.mldn.dibmp.service.impl.ccc;

import java.util.List;

import javax.annotation.Resource;

import cn.mldn.dibmp.ccc.service.ICityService;
import cn.mldn.dibmp.dao.ccc.ICityDAO;
import cn.mldn.dibmp.vo.City;

public class CityServiceImpl implements ICityService {

	@Resource
	private ICityDAO cityDAO;
	@Override
	public List<City> listByProvince(long pid) {
		return cityDAO.findByProvince(pid);
	}

}
