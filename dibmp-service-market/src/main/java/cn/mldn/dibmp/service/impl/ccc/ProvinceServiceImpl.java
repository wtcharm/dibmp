package cn.mldn.dibmp.service.impl.ccc;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.ccc.service.IProvinceService;
import cn.mldn.dibmp.dao.ccc.IProvinceDAO;
import cn.mldn.dibmp.vo.Province;
@Service
public class ProvinceServiceImpl implements IProvinceService {
	@Resource
	private IProvinceDAO provinceDAO;
	@Override
	public List<Province> findAll() {
		return provinceDAO.findAll();
	}
	
}
