package cn.mldn.dibmp.dao;

import java.util.List;

import cn.mldn.dibmp.vo.Csource;

public interface CsourceMapper {
	/**
	 * 查询客户来源信息
	 * @return
	 */
   public List<Csource> findAll();
}
