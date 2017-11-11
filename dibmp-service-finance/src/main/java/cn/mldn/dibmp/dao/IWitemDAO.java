package cn.mldn.dibmp.dao;

import java.util.List;

import cn.mldn.dibmp.vo.Witem;

public interface IWitemDAO {
	public List<Witem> findAll();
	public Witem findByWiid(Long wiid);
}
