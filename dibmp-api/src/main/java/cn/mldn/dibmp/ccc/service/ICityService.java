package cn.mldn.dibmp.ccc.service;

import java.util.List;

import cn.mldn.dibmp.vo.City;

public interface ICityService {
	/**
	 * 根据城市进行查询城市列表
	 * @param pid  cc
	 * @return cc
	 */
	public List<City> listByProvince(long pid);
}
