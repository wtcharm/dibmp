package cn.mldn.dibmp.dao.ccc;

import java.util.List;

import cn.mldn.dibmp.vo.City;

public interface ICityDAO {
	/**
	    * 根据省份编号找到城市列表
	    * @param pid
	    * @return
	    */
	   public List<City> findByProvince(Long pid);
}
