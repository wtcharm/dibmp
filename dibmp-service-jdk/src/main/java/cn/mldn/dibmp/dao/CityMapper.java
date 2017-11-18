package cn.mldn.dibmp.dao;

import java.util.List;

import cn.mldn.dibmp.vo.City;

public interface CityMapper {
	/**
	 * 查询城市详情
	 * @return
	 */
   public List<City> findAll();
   /**
    * 根据省份编号查找对应城市
    * @return
    */
   public List<City> findByPid(Long id);
}