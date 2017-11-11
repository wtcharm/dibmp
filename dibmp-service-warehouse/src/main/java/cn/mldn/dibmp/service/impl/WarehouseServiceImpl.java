package cn.mldn.dibmp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IStorageApplyDAO;
import cn.mldn.dibmp.dao.IStorageApplyDetailsDAO;
import cn.mldn.dibmp.dao.IStorageApplyStatusDAO;
import cn.mldn.dibmp.dao.IStorageRecordStatusDAO;
import cn.mldn.dibmp.dao.IWarehouseDAO;
import cn.mldn.dibmp.dao.IWitemDAO;
import cn.mldn.dibmp.fyh.service.IWarehouseService;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.dibmp.vo.StorageApplyStatus;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.dibmp.vo.Witem;
import cn.mldn.util.service.abs.AbstractService;

@Service
public class WarehouseServiceImpl extends AbstractService implements IWarehouseService {
	
	@Resource
	private IWarehouseDAO warehouseDAO ;
	@Resource
	private IStorageApplyDAO storageApplyDAO ;
	@Resource
	private IStorageApplyStatusDAO storageApplyStatusDAO ;
	@Resource
	private IStorageRecordStatusDAO storageRecordStatusDAO ;
	@Resource
	private IStorageApplyDetailsDAO storageApplyDetailsDAO ;
	
	@Resource
	private IWitemDAO witemDAO ;
	
	@Resource
	private RedisTemplate<String,StorageApplyDetails> redisTemplate ;
	@Override
	public Map<String, Object> addPre(Long wiid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Warehouse vo) {
		return warehouseDAO.doCerate(vo) ;
	}

	@Override
	public Map<String, Object> list(long currentPage, int lineSize, String column, String keyWord) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("allWarehouses", warehouseDAO.findAllSplit(super.paramToMap(currentPage, lineSize, column, keyWord))) ;
		map.put("allRecorders", warehouseDAO.getSplitCount(super.paramToMap(column, keyWord))) ;
		return map ;
	}

	@Override
	public boolean addAdmin(Warehouse vo) {
		return warehouseDAO.doCreateAdmin(vo) ;
	}

	@Override
	public Map<String, Object> listStorageApply(long currentPage, int lineSize, String column, String keyWord,String appmid) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		
		List<StorageApplyStatus> listsas = storageApplyStatusDAO.findAll();
		Map<Integer,String> mapsas = new HashMap<Integer,String> () ;
		Iterator<StorageApplyStatus> iterator = listsas.iterator() ;
		while(iterator.hasNext()){
			StorageApplyStatus s = iterator.next() ;
			mapsas.put(s.getSasid(), s.getSastitle()) ;
		}
		
		
		List<Warehouse> listwh = warehouseDAO.findAllName() ;
		Map<Long,String> mapwh = new HashMap<Long,String> () ;
		Iterator<Warehouse> iterwh = listwh.iterator() ;
		while(iterwh.hasNext()){
			Warehouse w = iterwh.next() ;
			mapwh.put(w.getWid(), w.getName()) ;
		}
		
		
		List<Witem> listwi = witemDAO.findAll() ;
		Map<Long,String> mapwi = new HashMap<Long,String> () ;
		Iterator<Witem> iterwi = listwi.iterator() ;
		while(iterwi.hasNext()){
			Witem w = iterwi.next() ;
			mapwi.put(w.getWiid(), w.getTitle()) ;
		}
		
		
		map.put("allStorageApply", storageApplyDAO.findSplit(super.paramToMap(currentPage, lineSize, column, keyWord,appmid))) ;
		map.put("allRecorders", storageApplyDAO.CountSplit(super.paramToMap(column, keyWord,appmid))) ;
		map.put("allSaStatus", mapsas) ;
		map.put("allWarehouseName", mapwh) ;
		map.put("allWitem", mapwi) ;
		return map ;
	}

	@Override
	public boolean inSubmit(Long said) {
		
		
//		return storageApplyDAO.findBySaid(said).getStatus() ;
		
		//首先要判断入库申请表状态，如果不等于0和等于3的不可提交，返回false
		if(!(storageApplyDAO.findBySaid(said).getStatus()==0||storageApplyDAO.findBySaid(said).getStatus()==3)) {
			return false;
			
		}
		
		
		// 判断redis中是否有数据
		
		
		// 1、取得Redis中保存的商品信息,存放到list集合之中
		// 1.1 通过ccc-said取得对应入库申请编号的keys
		StringBuffer baseKey = new StringBuffer("ccc-") ;
		Set<String> keys = this.redisTemplate.keys(baseKey.append(said).append("-*").toString()) ;  //得到对应入库申请编号的keys
		if(keys.size()==0) {  // 如果没有对应信息，返回false
			return false ;
		}
		
		Iterator<String> iterKey = keys.iterator() ;
		List<StorageApplyDetails> listsad = new ArrayList<StorageApplyDetails>() ;
		// 1.2、将redis中的商品信息取出，并放到list集合中（listsad）
		while(iterKey.hasNext()) {
			String k = iterKey.next() ;
			StorageApplyDetails s = this.redisTemplate.opsForValue().get(k) ;
			listsad.add(s) ;
			
		}
		
		// 2 、将以上存放的list集合批量添加到商品入库详情表中
		
		if(!storageApplyDetailsDAO.doCreateBatch(listsad)) { // 如果添加商品入库详情失败，返回false
			return false;
		}
		
		
		// 3、 修改入库申请表状态，提交申请修改为1，并追加提交日期
		StorageApply vosa= new StorageApply() ;
		vosa.setSaid(said);
		vosa.setStatus(1);
		vosa.setApptime(new Date());
		if(!storageApplyDAO.doEditStatus(vosa)) { //如果状态更新失败，返回false
			return false;
		}
		
		// 4、 将redis中对应的入库缓存信息删除
		
		if(this.redisTemplate.delete(keys)==keys.size()) {  
			return true;
		}
		
		
		return false;
	}

		@Override
	public boolean testAddBatch(List<StorageApplyDetails> listsad) {
		return storageApplyDetailsDAO.doCreateBatch(listsad) ;
	}

		@Override
		public boolean removeStorageApply(Long said) {
			if(storageApplyDAO.findBySaid(said)==null) {
				return false ;
			}
			
			if(storageApplyDAO.findBySaid(said).getStatus()==0||storageApplyDAO.findBySaid(said).getStatus()==3) {  // 申请单状态是未提交或是已拒绝时可以执行删除操作
				if(storageApplyDAO.doDelete(said)) {  // 入库申请表删除成功
					StringBuffer baseKey = new StringBuffer("ccc-") ;  
					Set<String> keys = this.redisTemplate.keys(baseKey.append(said).append("-*").toString()) ;  //得到对应入库申请编号的keys
					if(keys.size()>0) { //redis中有数据
						this.redisTemplate.delete(keys) ;
					}
					return true ;
				}
			}
			return false;
		}
}
