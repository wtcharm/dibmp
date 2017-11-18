package cn.mldn.dibmp.dao;

import java.util.List;

import cn.mldn.dibmp.vo.Witem;

/**
 * 仓储分类接口
 * @author yaojia
 *
 */
public interface WritemMapper {

	public List<Witem> findAll();
}
