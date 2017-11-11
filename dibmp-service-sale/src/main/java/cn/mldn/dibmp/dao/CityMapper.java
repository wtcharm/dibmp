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
    * 通过cid 查询城市信息
    * @param cid
    * @return
    */
   public City findById(Long cid);
   
   /***
    * 
    * @param pid
    * @return
    */
   public List<City> findByPid(Long pid);
}