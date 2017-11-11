package cn.mldn.dibmp.ccc.service;

import java.util.List;

import cn.mldn.dibmp.vo.Subtype;

public interface ISubtypeService {
	/**
	 * 根据商品类型查看子类型
	 * @param wiid 商品类型编号
	 * @return 子类型集合
	 */
	public List<Subtype> findByWiid(Long wiid);
}
