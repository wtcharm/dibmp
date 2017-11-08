package cn.mldn.dibmp.ccc.service;

import java.util.List;

import cn.mldn.dibmp.vo.Witem;

public interface IWitemService {
	/**
	 * 商品增加之前，商品类型的显示
	 * @return key=allWitem,value=witemDAO.findAll();
	 */
	public List<Witem> list();
}
