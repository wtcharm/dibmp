package cn.mldn.dibmp.ccc.service;

import java.util.Map;

import cn.mldn.dibmp.vo.Goods;

public interface IGoodsService {
	/**
	 * 根据商品编号查询出商品信息
	 * @param gid 商品编号
	 * @return 商品信息
	 */
	public Goods findById(Long gid);
	/**
	 * 根据用户输入的商品名称，查询数据库是否有重复商品
	 * @param name cc
	 * @return cc
	 */
	public Goods findByName(String name);
	/**
	 * 增加新的商品
	 * @param vo 新增商品信息
	 * @param mid 增加商品的人
	 * @return 增加成功返回true
	 */
	public boolean add(Goods vo,String mid);
	/**
	 * 商品列表
	 * @param column cc
	 * @param keyWord cc
	 * @param currentPage cc
	 * @param lineSize cc
	 * @return cc
	 */
	public Map<String,Object> list(String column,String keyWord,Long currentPage,Integer lineSize);
	/**
	 * 修改前的回显
	 * @param gid cc
	 * @return cc
	 */
	public Goods editPre(long gid);
	/**
	 * 商品修改
	 * @param vo cc
	 * @param mid cc
	 * @return cc
	 */
	public boolean update(Goods vo,String mid);
	
}
