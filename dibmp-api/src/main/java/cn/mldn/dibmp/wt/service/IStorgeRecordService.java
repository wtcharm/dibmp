package cn.mldn.dibmp.wt.service;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.StorageRecord;

/**
 * 负责商品审核清单的处理待审核清单
 * @author Administrator
 */
public interface IStorgeRecordService {
	/**
	 * 添加已经通过审核的数据信息
	 * @param vo 已经通过的数据
	 * @return 添加成功返回true否则返回false
	 */
	public boolean add(StorageRecord vo);
	/**
	 * 查询通过审核的所有信息
	 * 显示在通过审核之后的页面
	 * 1.key=apply 根据said得到申请的姓名
	 * 2.key=allRecord 分页模糊得到record表中的数据
	 * 3.key=CountRecord  得到模糊统计的数据
	 * 4.key=SumPrice 得到所有的商品价格
	 * 5.key=CountNum 得到所有的商品数量
	 * @return 查询成功返回数据 否则返回null 
	 */
	public Map<String, Object>listRecord(String column,String keyWord,Long currentPage,Integer lineSize);
}
