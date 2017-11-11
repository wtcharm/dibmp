package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;

public interface IStorageApplyDetailsDAO {
	
	public boolean doCreateBatch(List<StorageApplyDetails> listsad);
	
}
