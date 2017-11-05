package cn.mldn.dibmp.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.ccc.service.IMarketService;
import cn.mldn.dibmp.dao.ISubtypeDAO;
import cn.mldn.dibmp.dao.IWitemDAO;
@Service
public class MarketServiceImpl implements IMarketService {
	@Resource
	private IWitemDAO witemDAO;
	@Resource
	private ISubtypeDAO subtypeDAO;
	@Override
	public Map<String, Object> addPre(Long wiid) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("allWitem", witemDAO.findAll());
		map.put("allSubtype", subtypeDAO.findAllByWitem(wiid));
		System.err.println(map.get("allWitem"));
		return map;
	}

}
