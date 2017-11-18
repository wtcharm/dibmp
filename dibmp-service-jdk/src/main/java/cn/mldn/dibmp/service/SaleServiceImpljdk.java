package cn.mldn.dibmp.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.CitemMapper;
import cn.mldn.dibmp.dao.CityMapper;
import cn.mldn.dibmp.dao.CritemMapper;
import cn.mldn.dibmp.dao.CsourceMapper;
import cn.mldn.dibmp.dao.CustomerMapper;
import cn.mldn.dibmp.dao.CustomerRecordMapper;
import cn.mldn.dibmp.dao.GoodsMapper;
import cn.mldn.dibmp.dao.IMemberDAO;
import cn.mldn.dibmp.dao.ProvinceMapper;
import cn.mldn.dibmp.exception.CustomerExistException;
import cn.mldn.dibmp.service.abs.AbstractService;
import cn.mldn.dibmp.vo.Customer;
import cn.mldn.dibmp.vo.CustomerRecord;
import cn.mldn.dibmp.vo.Goods;

@Service
public class SaleServiceImpljdk extends AbstractService implements ISaleServicejdk {

	@Resource
	private GoodsMapper goodsMapper;
	@Resource
	private CustomerMapper customerMapper;
	@Resource
	private CitemMapper citemMapper;
	@Resource
	private ProvinceMapper provinceMapper;
	@Resource
	private CityMapper cityMapper;
	@Resource
	private CsourceMapper csourceMapper;
	@Resource
	private IMemberDAO memberDAO;
	@Resource
	private CritemMapper critemMapper;
	@Resource
	private CustomerRecordMapper customerRecordMapper;
	
	@Override
	public Map<String, Object> list(String column, String keyWord, Long currentPage, Integer lineSize) {
		Map<String,Object> param = super.handleParams(column, keyWord, currentPage, lineSize);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allGoods", this.goodsMapper.findAllSplit(param));
		map.put("allRecorders", this.goodsMapper.getSplitCount(param));
		return map;
	}
	@Override
	public boolean edit(Goods vo) {
		return this.goodsMapper.doUpdate(vo);
	}
	@Override
	public Map<String, Object> listCity(Long pid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allCities",this.cityMapper.findByPid(pid));
		return map;
	}
	@Override
	public Map<String, Object> listCritem() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allCritems",this.critemMapper.findAll());
		return map;
	}
	@Override
	public Map<String, Object> listCritem(long criid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allCritems",this.critemMapper.findAll());
		return map;
	}
	@Override
	public Map<String, Object> getAddPre() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allCsources", this.csourceMapper.findAll());
		map.put("allProvinces",this.provinceMapper.findAll());
		map.put("allCitites", this.cityMapper.findAll());
		map.put("allCitems", this.citemMapper.findAll());
		return map;
	}
	@Override
	public boolean add(Customer vo) throws CustomerExistException{
		if(this.customerMapper.findByName(vo.getName())==null) {   //1.判断要追加的客户id是否存在，存在则无法保存
			vo.setIndate(new Date());
			vo.setConnum(1);
			return this.customerMapper.doCreate(vo);
		}else {
			throw new CustomerExistException("该客户已经存在，无法添加！");
		}
	}
	@Override
	public Map<String, Object> listC(Long currentPage, Integer lineSize, String column, String keyWord) {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> param = super.handleParams(column, keyWord, currentPage, lineSize);
		List<Customer> customerAll = this.customerMapper.findAllSplit(param);
		Iterator<Customer> iter = customerAll.iterator();
		Map<Long,Object> mapCitems = new HashMap<Long,Object>();
		Map<Long,Object> mapRecorders = new HashMap<Long,Object>();
		while (iter.hasNext()) {
			Customer cmer = iter.next();
			mapCitems.put(cmer.getCuid(), this.citemMapper.findById(cmer.getCiid()));
			mapRecorders.put(cmer.getCuid(),this.customerRecordMapper.findNameByMid(cmer.getRecorder()));
		}
		map.put("allCustomers",customerAll);
		map.put("allCitems",mapCitems);
		map.put("allRecords",mapRecorders);
		map.put("allRecorders", this.customerMapper.getAllSplitCount(param));
		return map;
	}
	@Override
	public boolean addCustomerRecord(CustomerRecord vo, String cmid) {
	    vo.setCmid(cmid);
		vo.setCdate(new Date());
		return this.customerRecordMapper.doCreateCustomerRecord(vo);
	}
	@Override
	public Map<String, Object> listD(Long cuid,String cmid,Long currentPage, Integer lineSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> param = super.handleMyParams(cmid, cuid, currentPage, lineSize);
		map.put("allCustomerRecords",this.customerRecordMapper.findAllSplit(param));
		map.put("allRecorders",this.customerRecordMapper.getAllSplitCount(param));
		map.put("member", memberDAO.findById(cmid));
		return map;
	}
	@Override
	public boolean doUpdateConnum(Customer vo) {
		vo.setIndate(new Date());
		return this.customerMapper.editConnum(vo);
	}
	@Override
	public List<CustomerRecord> getAllCrById(Long cuid) {
		return this.customerRecordMapper.findAllCrById(cuid);
	}
	@Override
	public Customer getName(String name) {
		return this.customerMapper.findByName(name);
	}
}
