package cn.mldn.dibmp.dao.ccc;

import java.util.List;

import cn.mldn.dibmp.vo.Subtype;

public interface ISubtypeDAO {
	public List<Subtype> findAllByWitem(Long wiid);
}
