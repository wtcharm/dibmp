package cn.mldn.dibmp.dao;

import java.util.List;

import cn.mldn.dibmp.vo.Province;

public interface ProvinceMapper {
	/**
	 * 查找全部省份信息
	 * @return 全部省份
	 */
   public List<Province> findAll();
}
