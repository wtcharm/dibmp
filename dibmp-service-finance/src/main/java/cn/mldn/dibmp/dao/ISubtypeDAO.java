package cn.mldn.dibmp.dao;

import java.util.List;

import cn.mldn.dibmp.vo.Subtype;

public interface ISubtypeDAO {
	public List<Subtype> findAllByWitem(Long wiid);
}
