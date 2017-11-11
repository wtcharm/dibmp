package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.StorageRecord;
/**
 * @author -- 定义入库记录信息
 */
public interface IStorageRecordDAO {
	/**
	 * 添加已经审核通过的清单数据
	 * @param vo  审核通过的数据
	 * @return 添加成功返回true否则返回false
	 */
	public boolean doCreate(StorageRecord vo);
	/**
	 * 查询当前审核过的信息中的数据
	 * @return 对象信息
	 */
	public List<StorageRecord> findBySaid(Long said);
	
}
